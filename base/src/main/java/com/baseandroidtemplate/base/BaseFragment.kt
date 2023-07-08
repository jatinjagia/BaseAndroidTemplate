package com.baseandroidtemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(){

    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
        baseObserverData()
    }


    private fun baseObserverData() {
        mViewModel.showToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        mViewModel.showInternetError.observe(viewLifecycleOwner) {
            if (it)
                showNoInternetDialog()
        }

    }


    private fun showNoInternetDialog() {
      //  show internet error standard here
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    abstract fun getBindingVariable(): Int

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

}