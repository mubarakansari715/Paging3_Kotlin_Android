package com.example.paging3.Data

import com.squareup.moshi.Json

data class Dogs(
    @Json(name = "url")
    val url: String,
)