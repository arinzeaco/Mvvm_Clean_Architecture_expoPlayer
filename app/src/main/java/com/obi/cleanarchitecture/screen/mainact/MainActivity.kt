package com.obi.cleanarchitecture.screen.mainact

import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.adapter.VideoAdapter
import com.obi.cleanarchitecture.base.BaseActivity
import com.obi.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity< ActivityMainBinding,AndroidViewModel>() {


    @Inject
    lateinit var newsAdapter: VideoAdapter

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun getViewModel(): MainActivityViewModel = mainActivityViewModel


    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
                val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        viewDataBinding.bnvNews.setupWithNavController(
            navController
        )

    }



}