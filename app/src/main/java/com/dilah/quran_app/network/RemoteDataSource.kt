package com.dilah.quran_app.network

import android.util.Log
import com.dilah.quran_app.network.adzan.AdzanApiService
import com.dilah.quran_app.network.quran.QuranApiService
import com.dilah.quran_app.network.quran.QuranEditionItem
import com.dilah.quran_app.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource {
    fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> {

    }

    fun getListSurah(): Flow<NetworkResponse<List<SurahItem>>> {

    }

    private val quran: QuranApiService,
    private val adzanApiService: AdzanApiService
} (
        suspend fun getListSurah(): Flow<NetworkResponse<List<SurahItem>>> =
            flow {
                try {
                    val surahResponse = quranApiService.getListSurah()
                    val listSurah = surahResponse.listSurah
                    emit(NetworkResponse.Success(listSurah))
                } catch (e: Exception) {
                    emit(NetworkResponse.Error(e.toString()))
                    Log.e(
                        RemoteDataSource::class.java.simpleName,
                        "getListSurah: " + e.localizedMessage
                    )
                }
            }.flowOn(Dispatchers.IO)

        suspend fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
            flow {
                try {
                    val ayahResponse = quranApiService.getDetailSurahWithQuranEditions(number)
                    val quranEdition = ayahResponse.quranEdition
                    emit(NetworkResponse.Success(quranEdition))
                } catch (e: Exception) {
                    emit(NetworkResponse.Error(e.toString()))
                    Log.e(
                        RemoteDataSource::class.java.simpleName,
                        "getListSurah: " + e.localizedMessage
                    )
                }
            }.flowOn(Dispatchers.IO)
        suspend fun searchCity(city: String): Flow<NetworkResponse<List<CityItem>>> =
            flow {
                try {
                    val cityResponse = adzanApiService.searchCity(city)
                    val listCity = cityResponse.dataCity
                    emit(NetworkResponse.Success(listCity))
                } catch (e: Exception) {
                    RemoteDataSource::class.java.simpleName,
                    ""
                }
            }
        )