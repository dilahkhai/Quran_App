package com.dilah.quran_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dilah.quran_app.databinding.ItemAyahBinding
import com.dilah.quran_app.network.quran.AyahsItem
import com.dilah.quran_app.presentation.quran.Ayah
import com.dilah.quran_app.presentation.quran.QuranEdition

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.MyViewHolder>() {
    private val listAyah = ArrayList<Ayah>()
    private val listEdition = ArrayList<QuranEdition>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(dataAyah: List<Ayah>?, dataEdition: List<QuranEdition>?) {
        if (dataAyah == null || dataEdition == null) return
        listAyah.clear()
        listAyah.addAll(dataAyah)
        listEdition.clear()
        listEdition.addAll(dataEdition)
    }

    class MyViewHolder (val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder (
        ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listAyah.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listAyah = listAyah[position]
        val quranAudio = listEdition[1].listAyahs.get(position)
        val quranTranslationIndo = listEdition[2].listAyahs.get(position)

        holder.binding.apply {
            tvItemNumberAyah.text = listAyah.numberInSurah.toString()
            tvItemAyah.text = listAyah.text
            tvItemTranslation.text = quranTranslationIndo?.text
            this.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(quranAudio)
            }
        }
    }

    interface OnItemClickCallback {

        fun onItemClicked(data: Ayah)
    }
}


