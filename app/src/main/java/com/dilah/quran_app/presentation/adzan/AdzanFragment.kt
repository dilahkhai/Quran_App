package com.dilah.quran_app.presentation.adzan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dilah.quran_app.ViewModelFactory
import com.dilah.quran_app.databinding.FragmentAdzanBinding
import com.dilah.quran_app.network.Resource

class AdzanFragment : Fragment() {
    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding

    private val adzanViewModel: AdzanViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adzanViewModel.getDetailAdzanTime().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    binding.apply {
                        it.data?.let { adzanDataResult ->
                            tvLocation.text = adzanDataResult.listLocation[1]
                            tvDate.text = adzanDataResult.listCalendar[3]
                        }
                    }
                    when (val adzanTime = it.data?.dailyAdzan) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            binding.apply {
                                adzanTime.data?.let { time ->
                                    tvTimeImsak.text = time.imsak
                                    tvTimeSubuh.text = time.subuh
                                    tvTimeDzuhur.text = time.dzuhur
                                    tvTimeAshar.text = time.ashar
                                    tvTimeMaghrib.text = time.maghrib
                                    tvTimeIsya.text = time.isya
                                }
                            }
                        }

                        is Resource.Error -> {
                            Log.e("AdzanFragment", "error getting schedule: ${adzanTime.message}", )
                            Toast.makeText(context, "Error: ${adzanTime.message}", Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Log.e("AdzanFragment", "error getting location: ${it.message}", )
                            Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                is Resource.Error -> {
                    Log.e("AdzanFragment", "error observing AdzanViewModel: ${it.message}", )
                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
