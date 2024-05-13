package com.bochi.kito.timbernotejava.ui.activity;

import static android.widget.LinearLayout.HORIZONTAL;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bochi.kito.timbernotejava.App;
import com.bochi.kito.timbernotejava.R;
import com.bochi.kito.timbernotejava.data.db.DBHelper;
import com.bochi.kito.timbernotejava.data.entity.Notes;
import com.bochi.kito.timbernotejava.databinding.ActivityEditNotesBinding;
import com.bochi.kito.timbernotejava.tools.AppConstString;
import com.bochi.kito.timbernotejava.tools.SpacingItemDecoration;
import com.bochi.kito.timbernotejava.ui.adapter.SelectColorsAdapter;
import com.bochi.kito.timbernotejava.ui.listener.SelectColorsListener;

public class EditNoteActivity extends BaseActivity implements View.OnClickListener {
    private ActivityEditNotesBinding binding;
    private Notes notes;
    private int note_type;
    private String newTitle;
    private String newContent;
    private int selectedColor;

    private int[] notesBgColor = new int[]{
            R.drawable.shape_notes_bg_00,
            R.drawable.shape_notes_bg_01,
            R.drawable.shape_notes_bg_02,
            R.drawable.shape_notes_bg_03,
            R.drawable.shape_notes_bg_04,
            R.drawable.shape_notes_bg_05,
            R.drawable.shape_notes_bg_06,
    };

    @Override
    View getActivityContentView() {
        binding = ActivityEditNotesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    void initView() {
        super.initView();
        notes = (Notes) getIntent().getSerializableExtra(AppConstString.EXTRA_KEY);
        note_type = getIntent().getIntExtra(AppConstString.EDIT_TYPE_KEY, 0);
        initPage();
        initButton();
        initSelectColors();
    }

    private void initPage() {
        if (notes != null) {
            binding.editTitle.setText(notes.getTitle());
            binding.editContent.setText(notes.getContent());
            binding.editLayout.setBackground(getApplicationContext().getDrawable(notesBgColor[notes.getColor()]));
        }
    }

    private void initSelectColors() {
        RecyclerView recyclerView = binding.recyclerColors;
        recyclerView.addItemDecoration(new SpacingItemDecoration(6));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        SelectColorsAdapter colorsAdapter = new SelectColorsAdapter(this, new SelectColorsListener() {
            @Override
            public void colorsSelect(int i) {
                selectedColor = i;
                binding.editLayout.setBackground(getApplicationContext().getDrawable(notesBgColor[selectedColor]));
            }
        });
        colorsAdapter.setData(notesBgColor);
        recyclerView.setAdapter(colorsAdapter);
    }

    private void initButton() {
        binding.editBack.setOnClickListener(this);
        binding.editConfirm.setOnClickListener(this);
        binding.editDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == binding.editBack) {
            finish();
        } else if (v == binding.editConfirm) {
            newTitle = binding.editTitle.getText().toString();
            newContent = binding.editContent.getText().toString();
            if (newTitle.isEmpty()) {
                Toast.makeText(this, "The title can not be blank!", Toast.LENGTH_SHORT).show();
                return;
            }
            action();
        } else if (v == binding.editDelete) {
            if (note_type != 0) {
                if (notes != null) {
                    DBHelper.INSTANCE.deleteNote(notes);
                }
            }
            finish();
        }

    }

    private void action() {
        if (note_type == 0) {
            DBHelper.INSTANCE.insertNote(new Notes(newTitle, newContent, selectedColor));
        } else {
            if (notes != null) {
                notes.setTitle(newTitle);
                notes.setContent(newContent);
                notes.setColor(selectedColor);
                DBHelper.INSTANCE.updateNote(notes);
            }
        }
        finish();
    }
}
