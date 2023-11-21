package com.dilah.quran_app.presentation.quran

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dilah.quran_app.R
import com.dilah.quran_app.adapter.SurahAdapter
import com.dilah.quran_app.databinding.ActivityDetailSurahBinding
import com.dilah.quran_app.network.quran.SurahItem

class DetailSurahActivity : AppCompatActivity() {

    private var _binding: ActivityDetailSurahBinding? = null

    private val binding get() = _binding as ActivityDetailSurahBinding

    private var _surah: Surah? = null

    private val surah get() = _surah as Surah

    private var _mediaPlayer: MediaPlayer? = null

    private val mediaPlayer get() = _mediaPlayer as MediaPlayer

    private var quranViewModel: QuranViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _surah = intent.getParcelableExtra(EXTRA_DATA, Surah::class.java)

        initView()

        val mAdapter = SurahAdapter()
        mAdapter.setOnItemClickCallback(object : SurahAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Ayah) {
                showCustomAlertDialog(data, surah)
            }
        })

        val numberSurah = surah.number
        if (numberSurah != null) {
            quranViewModel.getDetailSurahWithQuranEdition(numberSurah).observe(this) {
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        binding.rvSurah.apply {
                            mAdapter.setData(it.data?.get(0)?.listAyahs, it.data)
                            adapter = mAdapter
                            layoutManager = LinearLayoutManager(this@DetailSurahActivity)
                        }
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                }
            }
        } else {
            Toast.makeText(this, "Number Surah not Found.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {  }
    }

    companion object {
        const val EXTRA_DATA = "number"
    }
}