package pamo.bmicalc_kotlin.ui.chart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import pamo.bmicalc_kotlin.R

class ChartFragment : Fragment() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_chart, container, false)

        // String url = "https://www.worldometers.info/coronavirus/";
        val wv = view.findViewById<WebView>(R.id.chart_webView_container)
        wv.settings.javaScriptEnabled = true
        wv.webViewClient = WebViewClient()
        wv.loadUrl("file:///android_asset/chart.html")
        return view
    }
}