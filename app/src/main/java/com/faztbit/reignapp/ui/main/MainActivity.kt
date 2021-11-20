package com.faztbit.reignapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.faztbit.domain.models.HitsDomain
import com.faztbit.reignapp.BR
import com.faztbit.reignapp.R
import com.faztbit.reignapp.databinding.ActivityMainBinding
import com.faztbit.reignapp.ui.detail.DetailActivity
import com.faztbit.reignapp.ui.dialog.GeneralErrorDialog
import com.faztbit.reignapp.ui.main.adapter.ItemTouchHelperCallback
import com.faztbit.reignapp.ui.main.adapter.ViewAdapter
import com.faztbit.reignapp.utils.Constant
import com.faztbit.reignapp.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { ViewAdapter(::gonnaToDetail, mainViewModel::deleteHits) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpLayout()
        setUpRecyclerView()
    }

    private fun setUpLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mainViewModel, mainViewModel)
    }


    private fun setUpRecyclerView() {
        binding.recyclerViewHits.adapter = adapter
        val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(itemTouchHelperCallback)
        touchHelper.attachToRecyclerView(binding.recyclerViewHits)
        val divider = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        binding.recyclerViewHits.addItemDecoration(divider)
    }

    override fun onStart() {
        super.onStart()
        setUpObserver()
    }

    private fun setUpObserver() {
        mainViewModel.messageError.observe(this, EventObserver {
            handleDialogServerErrorWithMessage(it)
        })
    }

    private fun gonnaToDetail(data: HitsDomain) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("url", data.url)
        startActivity(intent)
    }

    @SuppressLint("RestrictedApi")
    fun handleDialogServerErrorWithMessage(message: String?) {
        val dialog = GeneralErrorDialog()
        if (!dialog.isVisible) {
            val args = Bundle()
            args.putString("message", message)
            dialog.arguments = args
            dialog.show(supportFragmentManager, Constant.DIALOG)
        }
    }
}