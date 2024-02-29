package com.example.mini_projet_notes.notes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mini_projet_notes.R;
import com.example.mini_projet_notes.Save;

import java.util.List;

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
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Confirmation")
                        .setMessage("Voulez-vous vraiment supprimer cette note ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int clickedPosition = holder.getLayoutPosition();
                                noteList.remove(clickedPosition);
                                notifyItemRemoved(clickedPosition);
                                Save.writeNotes(holder.itemView.getContext(), noteList);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }





}