package com.terabyte.fitnesslist.json

import com.squareup.moshi.Json

data class Tab(
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String
)
