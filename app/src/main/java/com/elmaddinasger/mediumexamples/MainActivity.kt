package com.elmaddinasger.mediumexamples

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.elmaddinasger.mediumexamples.databinding.ActivityMainBinding
import com.elmaddinasger.mediumexamples.models.Product
import com.elmaddinasger.mediumexamples.models.ProductListModel
import com.elmaddinasger.mediumexamples.models.ProductUpdateRequest
import com.elmaddinasger.mediumexamples.retrofit.RetrofitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productCurrent: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //updateProduct()
        binding.button.setOnClickListener {
            deleteProduct(1)
        }
    }

    fun updateProduct ( ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitRepository.productApi.getProductById(1)
            withContext(Dispatchers.Main) {
                Log.e("PRODUCTS",response.toString())
                if (response.isSuccessful) {
                    val nbProduct = response.body()
                    nbProduct?.let { product ->
                        productCurrent = product
                        Log.e("Product",productCurrent.toString())
                    }
                }
            }
        }
    }

        fun addProduct (product: Product) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitRepository.productApi.addProduct(product)
                withContext(Dispatchers.Main) {
                    Log.e("PRODUCTS",response.toString())
                    if (response.isSuccessful) {
                        val nbProduct = response.body()
                        nbProduct?.let { product ->
                            productCurrent = product
                            Log.e("Product",productCurrent.toString())
                        }
                    }
                }
            }
        }

    fun deleteProduct (id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitRepository.productApi.deleteProduct(id)
            withContext(Dispatchers.Main) {
                Log.e("PRODUCTS",response.toString())
                if (response.isSuccessful) {
                    val nbProduct = response.body()
                    nbProduct?.let { product ->
                        productCurrent = product
                        Log.e("Product",productCurrent.toString())
                    }
                }
            }
        }
    }
}
