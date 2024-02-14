package com.example.mini_projet_notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<StyleNote> noteList;

    // Constructeur prenant la liste des notes
    public NoteAdapter(List<StyleNote> noteList) {
        this.noteList = noteList;
    }

    // Méthode pour créer de nouveaux ViewHolder
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style_note, parent, false);
        return new NoteViewHolder(view);
    }

    @NonNull
    @NotNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    // Méthode pour lier les données aux vues
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        StyleNote note = noteList.get(position);
        holder.bind(note);
    }

    // Méthode pour retourner le nombre total d'éléments dans la liste
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    // Classe ViewHolder pour maintenir les références aux vues
    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textContent;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            textContent = itemView.findViewById(R.id.content);
        }

        // Méthode pour lier une note spécifique aux vues ViewHolder
        public void bind(StyleNote note) {
            textTitle.setText(note.getTitle());
            textContent.setText(note.getContent());
        }
    }
}