package com.muhammhassan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammhassan.core.api.Status
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.utils.UiState
import com.muhammhassan.newsapp.R
import com.muhammhassan.newsapp.adapter.NewsAdapter
import com.muhammhassan.newsapp.databinding.FragmentListBinding
import com.muhammhassan.newsapp.ui.viewmodel.MainViewModel
import com.muhammhassan.newsapp.utils.Extension.hide
import com.muhammhassan.newsapp.utils.Extension.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        viewModel.getData()

        binding.apply{
            toolbar.apply{
                inflateMenu(R.menu.home_menu)
                title = "Recent News"
            }
            adapter = NewsAdapter{
                view.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
            }
            rvList.adapter = adapter
            rvList.layoutManager = LinearLayoutManager(requireContext())
        }


    }

    private fun observeViewModel(){
        viewModel.data.observe(viewLifecycleOwner){
            when(it){
                is UiState.Error -> showError(message = it.errorMessage, showAction = true, action = { viewModel.getData() })
                is UiState.Loading -> showLoading()
                is UiState.NoData -> showError(message = requireContext().resources.getString(R.string.empty_data), showAction = false, action = {})
                is UiState.Success<*> -> {
                    it.data?.let {data ->
                        showData(data as List<NewsModel>)
                    }
                }
            }
        }
    }

    private fun showError(message: String?, showAction: Boolean = true, action : () -> Unit){
        binding.apply {
            rvList.hide()
            stopShimmer()
            layoutError.apply {
                root.show()
                tvMessage.text = message ?: getString(R.string.error_occured)
                if(showAction){
                    btnAction.show()
                    btnAction.setOnClickListener {
                        action()
                    }
                }else btnAction.hide()
            }
        }
    }

    private fun showLoading(){
        showShimmer()
        binding.apply {
            rvList.hide()
            layoutError.root.hide()
        }
    }

    private fun showData(data: List<NewsModel>){
        stopShimmer()
        binding.apply {
            rvList.show()
            layoutError.root.hide()
            adapter.setData(data)
        }
    }

    private fun showShimmer(){
        binding.shimmer.apply {
            show()
            startShimmer()
        }
    }
    private fun stopShimmer(){
        binding.shimmer.apply {
            hide()
            stopShimmer()
        }
    }
}