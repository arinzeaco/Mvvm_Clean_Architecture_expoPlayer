package com.obi.cleanarchitecture.screen.network

import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.VideoDataResponse
import com.obi.cleanarchitecture.adapter.VideoAdapter
import com.obi.cleanarchitecture.base.BaseFragment
import com.obi.cleanarchitecture.databinding.NetworkVideoFragmentBinding
import com.obi.cleanarchitecture.screen.mainact.MainActivity


class NetworkVideoFragment : BaseFragment<NetworkVideoFragmentBinding, AndroidViewModel>()  {
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false

    private lateinit var videoAdapter: VideoAdapter
    private var listMerchants = mutableListOf<VideoDataResponse>()

    private val networkVideoViewModel: NetworkVideoViewModel by activityViewModels()

    override fun getLayoutId() = R.layout.network_video_fragment

    override fun getViewModel(): NetworkVideoViewModel = networkVideoViewModel


    override fun initView() {

        videoAdapter= (activity as MainActivity).videoAdapter

        initRecyclerView()
        viewNewsList(page)
    }

    private fun showProgressBar(){
        networkVideoViewModel.progressBar.observe(this) {
            if(it) viewDataBinding.progressBar.visibility = View.VISIBLE
            else viewDataBinding.progressBar.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        showProgressBar()
        viewDataBinding.rvNews.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NetworkVideoFragment.onScrollListener)
        }
    }

    private fun viewNewsList(page: Int) {
        networkVideoViewModel.getVideo(page,5)

        networkVideoViewModel.videsData.observe(viewLifecycleOwner,{response->
            listMerchants.addAll(response.videoDataResponses)
            videoAdapter.differ.submitList(listMerchants.toList())

        })

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
            if(shouldPaginate) {
//                if (page < 2) {
//                    page++
                    viewNewsList(page)
//                }
            }


                isScrolling = false

            }


        }
    }



















