package com.bochi.kito.timbernotes.ui.activity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bochi.kito.timbernotes.R;
import com.bochi.kito.timbernotes.databinding.ActivityMainBinding;
import com.bochi.kito.timbernotes.ui.adapter.MainVpAdapter;
import com.bochi.kito.timbernotes.ui.fragment.InfoFragment;
import com.bochi.kito.timbernotes.ui.fragment.NotesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ActivityMainBinding binding;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    View getActivityContentView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
        initButton();
        initViewPager();
    }

    private void initViewPager() {
        fragmentList.add(new NotesFragment());
//        fragmentList.add(new TaskFragment());
        fragmentList.add(new InfoFragment());
        ViewPager mainVp = binding.mainVp;
        mainVp.setAdapter(new MainVpAdapter(fragmentList, getSupportFragmentManager()));
        mainVp.addOnPageChangeListener(this);
        mainVp.setOffscreenPageLimit(0);
        setVpSelect(0);
    }

    private void setVpSelect(int i) {
        switch (i) {
            case 0:
                binding.mainBtNote.setSelected(true);
                binding.mainBtTask.setSelected(false);
                binding.mainBtInfo.setSelected(false);
                binding.mainVp.setCurrentItem(0);
                break;

//            case 1:
//                binding.mainBtNote.setSelected(false);
//                binding.mainBtTask.setSelected(true);
//                binding.mainBtInfo.setSelected(false);
//                binding.mainVp.setCurrentItem(1);
//                break;
            case 1:
                binding.mainBtNote.setSelected(false);
                binding.mainBtTask.setSelected(false);
                binding.mainBtInfo.setSelected(true);
                binding.mainVp.setCurrentItem(2);
                break;
        }
    }

    private void initButton() {
        binding.mainBtNote.setOnClickListener(this);
        binding.mainBtTask.setOnClickListener(this);
        binding.mainBtInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.mainBtNote) {
            setVpSelect(0);
        } else if (v == binding.mainBtTask) {
            setVpSelect(1);
        } else if (v == binding.mainBtInfo) {
            setVpSelect(1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setVpSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}