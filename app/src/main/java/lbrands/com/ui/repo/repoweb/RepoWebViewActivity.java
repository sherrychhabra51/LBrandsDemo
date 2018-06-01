package lbrands.com.ui.repo.repoweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import lbrands.com.lbrandsassignment.R;
import lbrands.com.ui.base.BaseActivity;

public class RepoWebViewActivity extends BaseActivity {
    private static final String KEY_REPO_URL ="repoURL";

    public static Intent intentFor(Context mContext, String repoURL) {
        Intent intent = new Intent(mContext, RepoWebViewActivity.class);
        intent.putExtra(KEY_REPO_URL, repoURL);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_we_view);
        ImageView iv_backBtn = findViewById(R.id.iv_backBtn);
        iv_backBtn.setVisibility(View.VISIBLE);
        iv_backBtn.setOnClickListener(view -> finish());

        WebView webView = findViewById(R.id.webView);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        String webURL = getIntent().getStringExtra(KEY_REPO_URL);
        webView.loadUrl(webURL);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}