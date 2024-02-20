package com.example.mini_projet_notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<StyleNote> noteList;

    public interface OnNoteClickListener {
        void onNoteClick(int position);
    }

    private OnNoteClickListener listener;



    public NoteAdapter(List<StyleNote> noteList) {
        this.noteList = noteList;
    }

    public void setOnNoteClickListener(OnNoteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StyleNote note = noteList.get(position);
        holder.textTitle.setText(note.getTitle());
        holder.textContent.setText(note.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNoteClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("LONG_CLICK", "j'ai cliqué longtemps sur la note");
                int clickedPosition = holder.getLayoutPosition();

                noteList.remove(clickedPosition);
                notifyItemRemoved(clickedPosition);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }





}