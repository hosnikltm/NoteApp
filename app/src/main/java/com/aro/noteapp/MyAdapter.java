package com.aro.noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Adapter> {
    private ArrayList<Note> notes;

    public MyAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Adapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle());
        holder.subTitle.setText(note.getSubTitle());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class Adapter extends RecyclerView.ViewHolder {
        TextView title,subTitle;
        public Adapter(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            subTitle = itemView.findViewById(R.id.textViewSubTitle);
        }
    }
}
