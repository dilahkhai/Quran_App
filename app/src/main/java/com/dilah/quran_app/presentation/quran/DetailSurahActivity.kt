package com.dilah.quran_app.presentation.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dilah.quran_app.R
import com.dilah.quran_app.adapter.SurahAdapter
import com.dilah.quran_app.databinding.ActivityDetailSurahBinding
import com.dilah.quran_app.network.quran.SurahItem

class DetailSurahActivity : AppCompatActivity() {

    private var _binding: ActivityDetailSurahBinding? = null

    private val binding get() = _binding as ActivityDetailSurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detail_surah)

        val data = intent.getParcelableExtra(EXTRA_DATA, SurahItem::class.java)

        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        data?.number?.let { quranViewModel.getListAyah(it) }

        binding.apply {
            val revelationType = data?.revelationType
            val numberOfAyahs = data?.numberOfAyahs
            val resultOfAyah = "$revelationType - $numberOfAyahs Ayahs"
            tvDetailAyah.text = resultOfAyah
            tvDetailName.text = data?.name
            tvDetailSurah.text = data?.englishName
            tvDetailNameTranslation.text = data?.englishNameTranslation
        }

        quranViewModel.listAyah.observe(this) {
            binding.rvSurah.apply {
                val mAdapter = SurahAdapter()
                mAdapter.setData(it.quranEdition?.get(0)?.listAyahs, it.quranEdition)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@DetailSurahActivity)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "number"
    }
}