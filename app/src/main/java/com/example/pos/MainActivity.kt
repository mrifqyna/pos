package com.example.pos

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.adapters.ProductAdapter
import com.example.pos.adapters.SelectedProductAdapter
import com.example.pos.viewmodels.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottomsheet_cart.*

class MainActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var bottomSheet: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        rv_product.apply {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(this@MainActivity, 2)
                } else {
                    GridLayoutManager(this@MainActivity, 4)
                }
            adapter = ProductAdapter(mutableListOf(), this@MainActivity, productViewModel)
        }
        rv_selected_product.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SelectedProductAdapter(mutableListOf(), this@MainActivity, productViewModel)
        }
        bottomSheet = BottomSheetBehavior.from(bottomsheet_detail_order)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        btn_detail.setOnClickListener {
            if (bottomSheet.state == BottomSheetBehavior.STATE_COLLAPSED || bottomSheet.state == BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
        btn_checkout.setOnClickListener {
            Toast.makeText(this@MainActivity, "Checkout", Toast.LENGTH_LONG).show()
        }
        productViewModel.fetchDummyProduct()
        productViewModel.listenToProducts().observe(this, Observer {
            rv_product.adapter?.let { a ->
                if (a is ProductAdapter) {
                    a.updateList(it)
                }
            }
        })
        productViewModel.listenToSelectedProduct().observe(this, Observer {
            rv_selected_product.adapter?.let { a ->
                if (a is SelectedProductAdapter) {
                    a.updateList(it)
                }
            }
            val totalPrice = if (it.isEmpty()) {
                0
            } else {
                it.sumBy { p ->
                    p.price!! * p.selectedQuantity!!
                }
            }
            total_price.text = totalPrice.toString()
        })
    }
}