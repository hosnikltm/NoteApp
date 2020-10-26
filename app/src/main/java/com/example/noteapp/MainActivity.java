package com.example.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.noteapp.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private static final int REQUEST_CODE = 111;

    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateNote.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        gridLayoutManager = new GridLayoutManager(this,2);
        notes = new ArrayList<>();
        adapter = new MyAdapter(notes);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null){
            if (resultCode == RESULT_OK){
                String nameFile = data.getStringExtra("read");
                try {
                    FileInputStream fileInputStream = new FileInputStream(nameFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    Note note = (Note) objectInputStream.readObject();
                    Note noteRead = new Note();
                    noteRead.setTitle(note.getTitle());
                    noteRead.setSubTitle(note.getSubTitle());
                    notes.add(noteRead);
                    adapter.notifyDataSetChanged();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}