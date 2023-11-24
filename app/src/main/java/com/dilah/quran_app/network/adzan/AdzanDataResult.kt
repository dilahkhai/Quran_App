package com.dilah.quran_app.network.adzan

import com.dilah.quran_app.network.Resource


data class AdzanDataResult(
    val listLocation: List<String>,
    val dailyAdzan: Resource<DailyAdzan>,
    val listCalendar: List<String>
)
