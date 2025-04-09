package com.elmaddinasger.mediumexamples.retrofit

import com.elmaddinasger.mediumexamples.models.ProductListModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductServices {

    @GET("products")
    suspend fun getProducts () : Response<ProductListModel>
}