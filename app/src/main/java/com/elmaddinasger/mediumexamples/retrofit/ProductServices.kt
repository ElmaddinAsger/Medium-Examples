package com.elmaddinasger.mediumexamples.retrofit

import com.elmaddinasger.mediumexamples.Order
import com.elmaddinasger.mediumexamples.models.Product
import com.elmaddinasger.mediumexamples.models.ProductListModel
import com.elmaddinasger.mediumexamples.models.ProductUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductServices {

    @GET("products")
    suspend fun getProducts () : Response<ProductListModel>
    
    @GET("products/{id}")
    suspend fun getProductById (
        @Path("id") id:Int
    ) : Response<Product>


    @GET("products/search")
    suspend fun getProductBySearch (
        @Query("q") searchKey:String
    ) : Response<ProductListModel>

    @GET("products")
    suspend fun productSortByTitleWithOrder (
        @Query("sortBy") sortBy:String,
        @Query("order") order: String
    ) : Response<ProductListModel>

    @PUT("products/{id}")
    suspend fun updateProduct (
        @Path ("id") id: Int,
        @Body request: Product
    ) : Response<Product>

    @POST("products/add")
    suspend fun addProduct (
        @Body request: Product
    ) : Response<Product>

    @DELETE("products/{id}")
    suspend fun deleteProduct (
        @Path ("id") id: Int
    ) : Response<Product>
}