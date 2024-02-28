package com.example.mini_projet_notes.notes;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mini_projet_notes.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public TextView textTitle;
    public TextView textContent;

    public NoteViewHolder(View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.title);
        textContent = itemView.findViewById(R.id.content);
    }


}
