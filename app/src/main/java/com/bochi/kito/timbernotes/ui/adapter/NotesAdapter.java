package com.bochi.kito.timbernotes.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bochi.kito.timbernotes.R;
import com.bochi.kito.timbernotes.data.entity.Notes;
import com.bochi.kito.timbernotes.databinding.ItemNotesBinding;
import com.bochi.kito.timbernotes.ui.listener.ClickActionListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesVH> {

    private final Context context;
    private final ClickActionListener listener;

    public NotesAdapter(Context context, ClickActionListener listener) {
        this.context = context;
        this.listener = listener;
    }

    private final int TYPE_HEAD = 0;
    private final int TYPE_CONTENT = 1;
    private List<Notes> notesList = new ArrayList<>();
    private final int[] notesBgColor = new int[]{
            R.drawable.shape_notes_bg_00,
            R.drawable.shape_notes_bg_01,
            R.drawable.shape_notes_bg_02,
            R.drawable.shape_notes_bg_03,
            R.drawable.shape_notes_bg_04,
            R.drawable.shape_notes_bg_05,
            R.drawable.shape_notes_bg_06,
    };

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Notes> data) {
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
        return new NotesVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesVH holder, int position) {
        if (position > 0) {
            Notes notes = notesList.get(position - 1);
            holder.setBinding(context, notes, position);
        } else {
            holder.setBinding(context, null, position);
        }
    }

    class NotesVH extends RecyclerView.ViewHolder {
        private final ItemNotesBinding binding;

        public NotesVH(ItemNotesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (position > 0) {
                        Notes item = notesList.get(position - 1);
                        listener.clickAction(position, item);
                    } else {
                        listener.clickAction(position, null);
                    }
                }
            });
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void setBinding(Context context, Notes notes, Integer position) {
            int itemViewType = NotesAdapter.this.getItemViewType(position);
            binding.itemLayout.setBackground(context.getDrawable(R.drawable.shape_notes_black));
            if (itemViewType == TYPE_HEAD) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.itemTitle.setTextColor(context.getColor(R.color.theme_color));
                }
                binding.itemTitle.setText(context.getString(R.string.notes_blank));
                binding.itemAdd.setVisibility(View.VISIBLE);
            } else {
                if (notes != null) {
                    binding.itemLayout.setBackground(context.getDrawable(notesBgColor[notes.getColor()]));
                    binding.itemTitle.setText(notes.getTitle());
                    binding.itemContent.setText(notes.getContent());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.itemTitle.setTextColor(context.getColor(R.color.color_222222));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.itemContent.setTextColor(context.getColor(R.color.color_222222));
                }
                binding.itemAdd.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return notesList.size() + 1;
    }

}
