package com.example.mini_projet_notes;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public TextView textTitle;
    public TextView textContent;

    public NoteViewHolder(View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.title);
        textContent = itemView.findViewById(R.id.content);
    }
}
