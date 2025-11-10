package com.terabyte.fitnesslist.json

import com.squareup.moshi.Json

data class Trainer(
    @Json(name = "id") val id: String,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "name") val name: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "position") val position: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "image_url_small") val imageUrlSmall: String,
    @Json(name = "image_url_medium") val imageUrlMedium: String,
    @Json(name = "description") val description: String,
)
