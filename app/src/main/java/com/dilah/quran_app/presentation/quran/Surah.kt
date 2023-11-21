package com.dilah.quran_app.presentation.quran

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Surah(
    val number: Int? = null,
    val name: String? = null,
    val englishName: String? = null,
    val englishNameTranslation: String? = null,
    val numberOfAyahs: Int? = null,
    val revelationType: String? = null
):Parcelable
