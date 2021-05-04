package pamo.bmicalc.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import pamo.bmicalc.R;

public class ChartFragment extends Fragment {

    private ChartViewModel chartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chartViewModel = new ViewModelProvider(this).get(ChartViewModel.class);
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        // String url = "https://www.worldometers.info/coronavirus/";
        WebView wv = view.findViewById(R.id.chart_webView_container);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("file:///android_asset/chart.html");

        return view;
    }
}