package com.dilah.quran_app.network.quran

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("surah")
    suspend fun getListSurah(): SurahResponse

    @GET("surah/{number}/editions/quran-uthmani,ar.alafasyi,id.indonesian")
    fun getDetailSurahWithQuranEdition(
        @Path("number") numberSurah: Int
    ): AyahResponse

    fun getListAyahBySurah(@Path("number") numberSurah: Int): retrofit2.Call<AyahResponse>
}