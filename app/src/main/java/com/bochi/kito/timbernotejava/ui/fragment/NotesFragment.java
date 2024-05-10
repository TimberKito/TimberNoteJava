package com.bochi.kito.timbernotejava.ui.fragment;

import android.view.View;

import com.bochi.kito.timbernotejava.databinding.FragmentNotesBinding;

public class NotesFragment extends BaseFragment {
    private FragmentNotesBinding binding;

    @Override
    protected View getFragmentContentView() {
        binding = FragmentNotesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
    }
}
