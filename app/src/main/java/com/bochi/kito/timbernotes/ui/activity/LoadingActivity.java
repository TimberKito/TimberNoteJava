package com.bochi.kito.timbernotes.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;

import com.bochi.kito.timbernotes.databinding.ActivityLoadingBinding;

public class LoadingActivity extends BaseActivity {
    private ActivityLoadingBinding binding;

    private final long LOADING_TIME = 2000;

    @Override
    View getActivityContentView() {
        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
        new CountDownTimer(LOADING_TIME, LOADING_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                intoMainActivity();
                finish();
            }
        }.start();
    }

    private void intoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
