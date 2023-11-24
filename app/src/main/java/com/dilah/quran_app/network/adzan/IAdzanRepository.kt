package com.dilah.quran_app.network.adzan

import androidx.lifecycle.LiveData
import com.dilah.quran_app.network.Resource
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {
    fun getLastKnownLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>

    fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<Resource<DailyAdzan>>
}