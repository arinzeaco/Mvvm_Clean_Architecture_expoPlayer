package com.obi.cleanarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel


abstract class BaseFragment<B: ViewDataBinding, V: AndroidViewModel>: Fragment() {

    protected lateinit var viewDataBinding: B
    abstract fun initView()
    private lateinit var viewModel: V

    abstract fun getViewModel(): V


    @LayoutRes
    abstract fun getLayoutId(): Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()

    }
}