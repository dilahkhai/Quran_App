package com.dilah.quran_app.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dilah.quran_app.network.Resource
import com.dilah.quran_app.network.adzan.AdzanDataResult
import com.dilah.quran_app.network.adzan.AdzanRepository

class AdzanViewModel (
    private val adzanRepository: AdzanRepository
) : ViewModel() {
    fun getDetailAdzanTime() :
            LiveData<Resource<AdzanDataResult>> = adzanRepository
        .getResultDailyAdzanTime()
}