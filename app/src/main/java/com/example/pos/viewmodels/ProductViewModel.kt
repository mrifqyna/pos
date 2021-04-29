package com.example.pos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pos.models.Product

class ProductViewModel : ViewModel(){
    private var products = MutableLiveData<List<Product>>()
    private var selectedProducts = MutableLiveData<List<Product>>()

    init {
        products.postValue(mutableListOf())
        selectedProducts.postValue(mutableListOf())
    }

    fun fetchDummyProduct(){
        //pura2nya dari API
        val dummies = mutableListOf<Product>().apply {
            add(Product(1, "Whiskas Ocean Fish", 3000, "whiskasoceanfish.webp"))
            add(Product(2, "Whiskas Tuna", 3500, "whiskastuna.jpg"))
            add(Product(3, "Oricat Adult", 3000, ""))
            add(Product(4, "Oricat Kitten", 8000, ""))
            add(Product(5, "Royal Cannin Mother & Baby", 6000, ""))
            add(Product(6, "Royal Cannin Kitten", 6000, ""))
            add(Product(7, "Royal Cannin Persia", 6000, ""))
            add(Product(8, "Snack Me-O", 6000, ""))
            add(Product(9, "Kandang", 6000, ""))
            add(Product(10, "Vitamin Hewan", 6000, ""))
            add(Product(11, "Litter Box", 6000, ""))
            add(Product(12, "Pasir", 6000, ""))
        }
        products.postValue(dummies)

    }

    fun addSelectedProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity?.plus(1)
        } ?: kotlin.run {
            tempSelectedProducts.add(product)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun decrementQuantityProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            if(it.selectedQuantity?.minus(1) == 0){
                tempSelectedProducts.remove(it)
            }else{
                it.selectedQuantity = it.selectedQuantity!!.minus(1)
            }
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun incrementQuantityProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity!!.plus(1)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun deleteSelectedProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            tempSelectedProducts.remove(it)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun listenToProducts() = products
    fun listenToSelectedProduct() = selectedProducts

}