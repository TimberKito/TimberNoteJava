package com.bochi.kito.timbernotes.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bochi.kito.timbernotes.R;
import com.bochi.kito.timbernotes.databinding.ItemColorsBinding;
import com.bochi.kito.timbernotes.databinding.ItemNotesBinding;
import com.bochi.kito.timbernotes.ui.listener.SelectColorsListener;

public class SelectColorsAdapter extends RecyclerView.Adapter<SelectColorsAdapter.SelectColorsVH> {

    private int selected_id = 0;
    private int[] colorsData;
    private Context context;
    private SelectColorsListener listener;

    public SelectColorsAdapter(Context context, SelectColorsListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(int[] data) {
        colorsData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SelectColorsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemColorsBinding binding = ItemColorsBinding.inflate(inflater, parent, false);
        return new SelectColorsVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectColorsVH holder, int position) {
        int colorsDatum = colorsData[position];
        holder.setBinding(context, colorsDatum, position);
    }

    @Override
    public int getItemCount() {
        return colorsData.length;
    }

    class SelectColorsVH extends RecyclerView.ViewHolder {
        private final ItemColorsBinding binding;

        public SelectColorsVH(ItemColorsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    selected_id = adapterPosition;
                    listener.colorsSelect(adapterPosition);
                    notifyDataSetChanged();
                }
            });
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void setBinding(Context context, int colorDraw, int position) {
            if (selected_id == position) {
                binding.itemColorsLayout.setBackground(context.getDrawable(R.drawable.shape_colors_border));
            } else {
                binding.itemColorsLayout.setBackground(null);
            }
            binding.itemColor.setImageResource(colorDraw);
        }
    }
}
