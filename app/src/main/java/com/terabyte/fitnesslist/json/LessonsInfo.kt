package com.terabyte.fitnesslist.json

import com.squareup.moshi.Json

data class LessonsInfo(
    @Json(name = "trainers") val trainers: List<Trainer>,
    @Json(name = "tabs") val tabs: List<Tab>,
    @Json(name = "lessons") val lessons: List<Lesson>
)