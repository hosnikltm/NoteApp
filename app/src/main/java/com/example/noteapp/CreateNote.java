package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.noteapp.databinding.ActivityCreateNoteBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class CreateNote extends AppCompatActivity {
    private ImageView imageBack, imageSave;

    EditText mEditTextTitle,mEditTextSubTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        mEditTextTitle = findViewById(R.id.inputNoteTitle);
        mEditTextSubTitle = findViewById(R.id.inputNote);

        imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEditTextTitle.getText().toString();
                if (title.contentEquals("")){
                    title = "UNTITLED";
                }
                String subTitle = mEditTextSubTitle.getText().toString();
                File file = new File("C:\\Users\\hosni\\AndroidStudioProjects"+title);

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
                    output.writeObject(title);
                    output.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CreateNote.this,MainActivity.class);
                intent.putExtra(String.valueOf(file),"read");
                setResult(RESULT_OK,intent);
                finish();
            }


        });
    }
}