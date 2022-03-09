package com.obi.cleanarchitecture.screen.mainact

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.adapter.VideoAdapter
import com.obi.cleanarchitecture.databinding.ActivityMainBinding
import com.obi.cleanarchitecture.screen.network.NetworkVideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var newsAdapter: VideoAdapter


    private lateinit var binding: ActivityMainBinding
    //    private lateinit var viewModelFactory:MainActivityViewModelFactory
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(
            navController
        )

        this.viewModel = mainActivityViewModel
    }
//
//    override fun navigateToLogin() {
//        TODO("Not yet implemented")
//    }
//
//    override fun navigateToSignUp() {
//        TODO("Not yet implemented")
//    }


}