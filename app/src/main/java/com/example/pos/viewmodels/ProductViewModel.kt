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
            add(Product(1, "Whiskas Ocean Fish", 299900, "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//98/MTA-4592555/whiskas_whiskas_dry_pockets_junior_ocean_fish_flavour_with_milk_dry_cat_food_-1-1_kg-_1_bag-_full02_trdo7vir.jpg"))
            add(Product(2, "Whiskas Tuna", 449850, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVOsFfGKQ-nYpAc-E-5nx0gI4FViBGEuPf6w&usqp=CAU"))
            add(Product(3, "Oricat Adult", 22000, "https://ecs7.tokopedia.net/img/cache/500-square/VqbcmM/2020/12/13/5aa47e50-a3e4-4608-a244-e8d2e4abd293.jpg"))
            add(Product(4, "Oricat Kitten", 20600, "https://id-test-11.slatic.net/p/8218ebd1a06e1ab9bdb8e36444c02215.jpg_720x720q80.jpg_.webp"))
            add(Product(5, "Royal Cannin Mother & Baby", 303000, "https://id-test-11.slatic.net/p/29378f4cf021061087638b400d075e8a.jpg"))
            add(Product(6, "Royal Cannin Kitten", 230000, "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full/MTA-2905145/royal-canin_royal-canin-kitten-makanan-anak-kucing--dry--2-kg-_full09.jpg"))
            add(Product(7, "Royal Cannin Persia", 455100, "https://cf.shopee.co.id/file/e3f87c099a85ef06927f4f7217d51a30"))
            add(Product(8, "Snack Me-O", 19499, "https://cf.shopee.co.id/file/dae243e2c202f3cf9f38d32e714db557"))
            add(Product(9, "Kandang", 425000, "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//93/MTA-2554409/petshop-murah_petshop-murah-k02-kandang-lipat--tanpa-roda-_full03.jpg"))
            add(Product(10, "Vitamin Hewan", 40000, "https://s1.bukalapak.com/img/14072370971/large/data.png"))
            add(Product(11, "Litter Box", 22750, "https://assets.petco.com/petco/image/upload/f_auto,q_auto/2535922-center-7"))
            add(Product(12, "Pasir", 105000, "https://s0.bukalapak.com/img/0409522402/large/Pasir_kucing_Kawan_Cat_Litter_20kg___Pasir_gumpal_wangi.png"))
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