package com.example.baseandroidtempalte

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.baseandroidtemplate.base.BaseFragment
import com.example.baseandroidtempalte.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding,MainViewModel>() {


    private lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun getViewModel(): MainViewModel {
        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireActivity().application)
            .create(MainViewModel::class.java)
        return viewModel
    }
}