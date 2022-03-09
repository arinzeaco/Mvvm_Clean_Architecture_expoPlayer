package com.obi.cleanarchitecture.screen.network

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.adapter.VideoAdapter
import com.obi.cleanarchitecture.base.BaseFragment
import com.obi.cleanarchitecture.databinding.NetworkVideoFragmentBinding
import com.obi.cleanarchitecture.screen.mainact.MainActivity
import com.obi.cleanarchitecture.util.Resource


class NetworkVideoFragment : Fragment() {
    private var page = 1
    private var pages = 0
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private lateinit var newsAdapter: VideoAdapter
    private lateinit var fragmentNewsBinding: NetworkVideoFragmentBinding

    private val networkVideoViewModel: NetworkVideoViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentNewsBinding = NetworkVideoFragmentBinding.inflate(layoutInflater)
        val view = fragmentNewsBinding.root

        return view


    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _fragmentNewsBinding = NetworkVideoFragmentBinding.inflate(layoutInflater)
//        val view = fragmentNewsBinding.root
//
//        return view
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    fragmentNewsBinding = NetworkVideoFragmentBinding.bind(view)

    newsAdapter= (activity as MainActivity).newsAdapter

        initRecyclerView()
        viewNewsList()

    }
    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
//            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
//        fragmentNewsBinding = null
    }


    private fun viewNewsList() {

        networkVideoViewModel.getVideo(1,1)
        networkVideoViewModel.videsData.observe(viewLifecycleOwner,{response->
            when(response){
                is Resource.Success->{

                    hideProgressBar()

                    response.data?.let {

                        Log.i("MYTAG","came here ${it}")
                        newsAdapter.differ.submitList(it.videoDataResponses.toList())
                        if(it.totalResults%20 == 0) {
                            pages = it.totalResults / 20
                        }else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,"An error occurred : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading->{
                    showProgressBar()
                }

            }
        })
    }
    private fun showProgressBar(){
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }

}
















