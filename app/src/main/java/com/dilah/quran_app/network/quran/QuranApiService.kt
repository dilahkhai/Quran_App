package com.dilah.quran_app.network.quran

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("surah")
    fun getListSurah(): retrofit2.Call<SurahResponse>

    @GET("surah/{number}/editions/quran-uthmani,ar.alafasyi,id.indonesian")
    fun getListAyahFromSurah(@Path("number") numberSurah: Int): retrofit2.Call<AyahResponse>

    fun getListAyahBySurah(@Path("number") numberSurah: Int): retrofit2.Call<AyahResponse>
}