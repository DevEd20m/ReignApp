package com.faztbit.reignapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.faztbit.data.utils.ConnectionUtils
import com.faztbit.reignapp.BR
import com.faztbit.reignapp.R
import com.faztbit.reignapp.databinding.ActivityDetailBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("SetJavaScriptEnabled")
class DetailActivity : AppCompatActivity() {
    private val connectionUtils: ConnectionUtils by inject()
    private val navArgs: DetailActivityArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpLayout()
        configWebView()
    }

    private fun setUpLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mainViewModel, detailViewModel)
    }

    private fun configWebView() {
        if (connectionUtils.isNetworkAvailable()) {
            binding.webView.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(navArgs.url)
            }
        } else {
            detailViewModel.setErrorTrue()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else super.onBackPressed()
    }

}