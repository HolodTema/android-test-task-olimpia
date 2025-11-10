package com.terabyte.fitnesslist.json

import com.squareup.moshi.Json

data class Lesson(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "place") val place: String,
    @Json(name = "coach_id") val coachId: String,
    @Json(name = "startTime") val startTime: String,
    @Json(name = "endTime") val endTime: String,
    @Json(name = "date") val date: String,
    @Json(name = "appointment_id") val appointmentId: String,
    @Json(name = "service_id") val serviceId: String,
    @Json(name = "available_slots") val availableSlots: Int,
    @Json(name = "commercial") val commercial: Boolean,
    @Json(name = "client_recorded") val clientRecorded: Boolean,
    @Json(name = "is_cancelled") val isCancelled: Boolean,
    @Json(name = "tab") val tab: String,
    @Json(name = "color") val color: String,
    @Json(name = "tab_id") val tabId: Int,
)
