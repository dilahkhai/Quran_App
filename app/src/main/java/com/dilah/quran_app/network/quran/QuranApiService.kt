package com.dilah.quran_app.network.quran

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("surah")
    suspend fun getListSurah(): SurahResponse

    @GET("surah/{number}/editions/quran-uthmani,ar.alafasyi,id.indonesian")
    suspend fun getDetailSurahWithQuranEdition(
        @Path("number") numberSurah: Int
    ): AyahResponse
}