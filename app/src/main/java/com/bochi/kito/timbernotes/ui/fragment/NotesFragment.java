package com.bochi.kito.timbernotes.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bochi.kito.timbernotes.data.db.DBHelper;
import com.bochi.kito.timbernotes.data.entity.Notes;
import com.bochi.kito.timbernotes.databinding.FragmentNotesBinding;
import com.bochi.kito.timbernotes.tools.AppConstString;
import com.bochi.kito.timbernotes.tools.SpacingItemDecoration;
import com.bochi.kito.timbernotes.ui.activity.EditNoteActivity;
import com.bochi.kito.timbernotes.ui.adapter.NotesAdapter;
import com.bochi.kito.timbernotes.ui.listener.ClickActionListener;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends BaseFragment {
    private FragmentNotesBinding binding;
    private NotesAdapter adapter;
    private List<Notes> allNotesList = new ArrayList<>();
    private Handler mHandler;

    @Override
    protected View getFragmentContentView() {
        binding = FragmentNotesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
        initRecycler();
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Notes> allNotesList = new ArrayList<>();

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                adapter.updateData(allNotesList);
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                allNotesList.addAll(DBHelper.INSTANCE.getNotesDatabase().noteDao().getAllNotes());
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                adapter.updateData(allNotesList);
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                allNotesList.addAll(DBHelper.INSTANCE.getNotesDatabase().noteDao().getAllNotes());
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initRecycler() {
        adapter = new NotesAdapter(requireActivity(), new ClickActionListener() {
            @Override
            public void clickAction(int i, Notes notes) {
                if (i == 0) {
                    action(0, null);
                } else {
                    action(1, notes);
                }
            }
        });

        RecyclerView notesRecyclerView = binding.notesRecyclerView;
//        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager
//                (2, StaggeredGridLayoutManager.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        notesRecyclerView.setLayoutManager(layoutManager);


        notesRecyclerView.setAdapter(adapter);
        notesRecyclerView.addItemDecoration(new SpacingItemDecoration(6));
    }

    private void action(int type, Notes notes) {
        switch (type) {
            case 0: {
                Intent intent = new Intent(requireActivity(), EditNoteActivity.class);
                intent.putExtra(AppConstString.EDIT_TYPE_KEY, 0);
                startActivity(intent);
                break;
            }
            case 1: {
                Intent intent = new Intent(requireActivity(), EditNoteActivity.class);
                intent.putExtra(AppConstString.EXTRA_KEY, notes);
                intent.putExtra(AppConstString.EDIT_TYPE_KEY, 1);
                startActivity(intent);
                break;
            }
        }
    }
}
