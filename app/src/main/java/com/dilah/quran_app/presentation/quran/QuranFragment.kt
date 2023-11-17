package com.dilah.quran_app.presentation.quran


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dilah.quran_app.adapter.QuranAdapter
import com.dilah.quran_app.databinding.FragmentQuranBinding


class QuranFragment : Fragment() {
    private var _binding : FragmentQuranBinding? = null
    private val binding get() = _binding as FragmentQuranBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        viewModel.getListSurah()

        viewModel.listSurah.observe(viewLifecycleOwner) {
            binding.rvQuran.apply {
                val mAdapter = QuranAdapter()
                mAdapter.setData(it.listSurah)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}