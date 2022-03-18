package com.obi.cleanarchitecture.screen.network

import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.adapter.VideoAdapter
import com.obi.cleanarchitecture.base.BaseFragment
import com.obi.cleanarchitecture.databinding.NetworkVideoFragmentBinding
import com.obi.cleanarchitecture.screen.mainact.MainActivity
import com.obi.cleanarchitecture.util.Resource


class NetworkVideoFragment : BaseFragment<NetworkVideoFragmentBinding, AndroidViewModel>()  {
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false

    private lateinit var newsAdapter: VideoAdapter

    private val networkVideoViewModel: NetworkVideoViewModel by activityViewModels()

    override fun getLayoutId() = R.layout.network_video_fragment

    override fun getViewModel(): NetworkVideoViewModel = networkVideoViewModel


    override fun initView() {

        newsAdapter= (activity as MainActivity).videoAdapter
//        newsAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("selected_article", it)
//            }
//        }
        initRecyclerView()
        viewNewsList(page)

    }
    private fun initRecyclerView() {
        viewDataBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NetworkVideoFragment.onScrollListener)
        }

    }


    private fun viewNewsList(page: Int) {

        networkVideoViewModel.getVideo(page,5)
        networkVideoViewModel.videsData.observe(viewLifecycleOwner,{response->
            when(response){
                is Resource.Success->{

                    hideProgressBar()

                    response.data?.let {

                        Log.i("MYTAG","came here ${it.videoDataResponses.toList().size}")
                        newsAdapter.differ.submitList(it.videoDataResponses.toList())
//                        if(it.totalResults%20 == 0) {
//                            pages = it.totalResults / 20
//                        }else{
//                            pages = it.totalResults/20+1
//                        }
//                        isLastPage = page == pages
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
        viewDataBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        viewDataBinding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = viewDataBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
//               viewNewsList(page)
                }


                isScrolling = false

            }


        }
    }



















