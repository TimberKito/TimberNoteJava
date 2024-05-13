package com.bochi.kito.timbernotejava.ui.fragment;

import android.view.View;

import com.bochi.kito.timbernotejava.databinding.FragmentTaskBinding;

public class TaskFragment extends BaseFragment implements View.OnClickListener {
    private FragmentTaskBinding binding;

    @Override
    protected View getFragmentContentView() {
        binding = FragmentTaskBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
        initButton();
    }

    private void initButton() {
        binding.taskAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.taskAdd) {

        }
    }
}
