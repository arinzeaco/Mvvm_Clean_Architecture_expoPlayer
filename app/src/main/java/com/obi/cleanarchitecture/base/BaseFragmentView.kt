package com.obi.cleanarchitecture.base

import androidx.lifecycle.LifecycleOwner

interface BaseFragmentView {
   fun handleError(error: Throwable, option: Any? = null)
   fun showProgressDialog(isShow: Boolean)
}