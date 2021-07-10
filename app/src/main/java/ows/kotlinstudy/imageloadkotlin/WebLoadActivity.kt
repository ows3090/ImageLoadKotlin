package ows.kotlinstudy.imageloadkotlin

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import ows.kotlinstudy.imageloadkotlin.databinding.ActivityWebLoadBinding

class WebLoadActivity : AppCompatActivity() {

    private var binding: ActivityWebLoadBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityWebLoadBinding = ActivityWebLoadBinding.inflate(layoutInflater)
        binding = activityWebLoadBinding
        setContentView(activityWebLoadBinding.root)

        activityWebLoadBinding.WebView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            intent.getStringExtra("link")?.let{
                if(URLUtil.isNetworkUrl(intent.getStringExtra("link"))){
                    loadUrl(it)
                }else{
                    loadUrl("https://${it}")
                }
            }
        }
    }

    inner class WebViewClient: android.webkit.WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            binding?.let{
                it.WebProgressBar.isEnabled = true
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            binding?.let{
                it.WebProgressBar.isEnabled = false
            }
        }
    }
}