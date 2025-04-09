package com.elmaddinasger.mediumexamples.models

data class ProductListModel(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)