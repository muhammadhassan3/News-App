package com.muhammhassan.newsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.muhammhassan.newsapp.R
import com.muhammhassan.newsapp.databinding.FragmentDetailBinding
import com.muhammhassan.newsapp.ui.viewmodel.DetailViewModel
import com.muhammhassan.newsapp.utils.Extension.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val viewModel: DetailViewModel by viewModel()
    private var isBookmarked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailFragmentArgs.fromBundle(arguments as Bundle).item

        binding.apply {
            toolbar.apply {
                title = data.title
                content.text = data.content
                if (data.image == null) imgHeader.setImageResource(R.drawable.baseline_image_not_supported_24)
                data.image?.let {
                    imgHeader.loadImage(it)
                }
                inflateMenu(R.menu.detail_menu)
                menu.apply {
                    getItem(0).setOnMenuItemClickListener {
                        if (isBookmarked) {
                            viewModel.removeBookmarkedData(data)
                        } else {
                            viewModel.addBookmarkedData(data)
                        }
                        true
                    }
                    getItem(1).setOnMenuItemClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
                        startActivity(intent)
                        true
                    }
                }
                setNavigationIcon(R.drawable.baseline_arrow_back_ios_24)
                setNavigationOnClickListener {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }

        }

        observeViewModel(data.title)
    }

    private fun observeViewModel(title: String) {
        viewModel.getIsBookmarked(title).observe(viewLifecycleOwner) {
            isBookmarked = it
            if (it) {
                binding.toolbar.menu.getItem(0).icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_24)
            } else
                binding.toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.baseline_bookmark_border_24
                )
        }
    }
}