package com.obi.cleanarchitecture.base


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel

abstract class BaseActivity<B : ViewDataBinding, V: AndroidViewModel> : AppCompatActivity(){

    protected lateinit var viewDataBinding: B
    private lateinit var viewModel: V

    abstract fun getViewModel(): V

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner= this
        viewModel = getViewModel()

    }


}