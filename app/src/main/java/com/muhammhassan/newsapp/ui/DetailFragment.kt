package com.muhammhassan.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.muhammhassan.newsapp.R
import com.muhammhassan.newsapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailFragmentArgs.fromBundle(arguments as Bundle).item

        binding.apply{
            toolbar.apply{
                title = data.title
                inflateMenu(R.menu.detail_menu)
                toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_24)
                toolbar.menu.getItem(0).setOnMenuItemClickListener {
                    Toast.makeText(requireContext(), "Test", Toast.LENGTH_SHORT).show()
                    true
                }
            }

        }
    }
}