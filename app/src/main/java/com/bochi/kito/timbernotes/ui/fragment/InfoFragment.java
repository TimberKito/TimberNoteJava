package com.bochi.kito.timbernotes.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import com.bochi.kito.timbernotes.App;
import com.bochi.kito.timbernotes.R;
import com.bochi.kito.timbernotes.databinding.FragmentInfoBinding;

public class InfoFragment extends BaseFragment implements View.OnClickListener {
    private FragmentInfoBinding binding;

    @Override
    protected View getFragmentContentView() {
        binding = FragmentInfoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
        initButton();
        initAppVersion();
    }

    private void initAppVersion() {
        binding.infoVersion.setText(getAppVersion());
    }

    private String getAppVersion() {
        PackageInfo packageInfo;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageInfo = App.appContext.getPackageManager().getPackageInfo(App.appContext.getPackageName(), PackageManager.PackageInfoFlags.of(0));
            } else {
                packageInfo = App.appContext.getPackageManager().getPackageInfo(App.appContext.getPackageName(), 0);
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
        return "Version:" + packageInfo.versionName;
    }

    private void initButton() {
        binding.infFind.setOnClickListener(this);
        binding.infoShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.infFind) {
            intoStore();
        } else if (v == binding.infoShare) {
            intoShare();
        }
    }

    private void intoShare() {
        String url = getString(R.string.info_store_link) + App.appContext.getPackageName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(intent);
    }

    private void intoStore() {
        String url = getString(R.string.info_store_link) + App.appContext.getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
