package com.faztbit.reignapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.R
import com.faztbit.reignapp.BR
import com.faztbit.reignapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { MainAdapter(::handleListenerAdapter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpLayout()
        setUpRecyclerView()
    }

    private fun setUpLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mainViewModel,mainViewModel)
    }


    private fun setUpRecyclerView() {
        binding.recyclerViewHits.adapter = adapter
    }

    private fun handleListenerAdapter(data: HitsDomain) {

    }
}