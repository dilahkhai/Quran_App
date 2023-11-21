package com.dilah.quran_app.presentation.quran

import com.dilah.quran_app.network.NetworkBoundResource
import com.dilah.quran_app.network.NetworkResponse
import com.dilah.quran_app.network.RemoteDataSource
import com.dilah.quran_app.network.Resource
import com.dilah.quran_app.network.quran.IQuranRepository
import com.dilah.quran_app.network.quran.QuranEditionItem
import com.dilah.quran_app.network.quran.SurahItem
import com.dilah.quran_app.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class QuranRepository (private val remoteDataSource: RemoteDataSource) : IQuranRepository {
    override fun getListSurah(): Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>() {
            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
                return remoteDataSource.getListSurah()
            }
        }.asFlow()
    }

    override fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItem>>() {
            override fun fetchFromNetwork(data: List<QuranEditionItem>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItem>>> {
                return remoteDataSource.getDetailSurahWithQuranEditions(number)
            }
        }.asFlow()
    }
}