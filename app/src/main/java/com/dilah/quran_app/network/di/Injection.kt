package com.dilah.quran_app.network.di

import android.content.Context
import com.dilah.quran_app.local.CalendarPreferences
import com.dilah.quran_app.local.LocationPreferences
import com.dilah.quran_app.network.ApiConfig
import com.dilah.quran_app.network.RemoteDataSource
import com.dilah.quran_app.network.adzan.AdzanRepository
import com.dilah.quran_app.presentation.quran.QuranRepository

object Injection {
    val quranApiService = ApiConfig.getQuranService
    val adzanApiService = ApiConfig.getAdzanTimeService
    val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)
    fun provideQuranRepository(): QuranRepository {
        return QuranRepository(remoteDataSource)
    }

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences, calendarPreferences)
    }
}