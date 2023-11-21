package com.dilah.quran_app.network.quran

import com.dilah.quran_app.network.Resource
import com.dilah.quran_app.presentation.quran.QuranEdition
import com.dilah.quran_app.presentation.quran.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah() : Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int) : Flow<Resource<List<QuranEdition>>>
}