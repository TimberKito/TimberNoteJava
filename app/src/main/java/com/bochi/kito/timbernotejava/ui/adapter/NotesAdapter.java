package com.bochi.kito.timbernotejava.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bochi.kito.timbernotejava.R;
import com.bochi.kito.timbernotejava.data.entity.Notes;
import com.bochi.kito.timbernotejava.databinding.ItemNotesBinding;
import com.bochi.kito.timbernotejava.ui.listener.ClickActionListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesVH> {
    public NotesAdapter(Context context, ClickActionListener listener) {
    }

    private int TYPE_HEAD = 0;
    private int TYPE_CONTENT = 1;
    private List<Notes> notesList = new ArrayList<>();
    private int[] notesBgColor = new int[]{
            R.drawable.shape_notes_bg_00,
            R.drawable.shape_notes_bg_01,
            R.drawable.shape_notes_bg_02,
            R.drawable.shape_notes_bg_03,
            R.drawable.shape_notes_bg_04,
            R.drawable.shape_notes_bg_05,
            R.drawable.shape_notes_bg_06,
    };

    @SuppressLint("NotifyDataSetChanged")
    private void setData(List<Notes> data) {
        notesList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else {
            return TYPE_CONTENT;
        }
    }

    @NonNull
    @Override
    public NotesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNotesBinding binding = ItemNotesBinding.inflate(inflater, parent, false);
        return new NotesVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NotesVH holder, int position) {
        if (position > 0) {
            Notes notes = notesList.get(position - 1);
        }
    }

    class NotesVH extends RecyclerView.ViewHolder {
        public NotesVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return notesList.size() + 1;
    }


}
