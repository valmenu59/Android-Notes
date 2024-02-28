package com.example.mini_projet_notes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mini_projet_notes.notes.NoteAdapter;
import com.example.mini_projet_notes.R;
import com.example.mini_projet_notes.Save;
import com.example.mini_projet_notes.notes.StyleNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity implements NoteAdapter.OnNoteClickListener {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<StyleNote> noteList;
    private FloatingActionButton buttonAdd;
    protected static final String NOTE_CHOISIE = "index";


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Récupération des id
        buttonAdd = findViewById(R.id.bouton_nouvelle_note);
        recyclerView = findViewById(R.id.main_list_view);

        // Initialisation des variables
        noteList = new ArrayList<>();
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Permet de lire la sauvegarde
        Save.readNotes(Main.this, noteList);

        // Ajout des actions
        adapter.setOnNoteClickListener(this);
        addNote();
    }


    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        // permet de sauvegarder l'état du inputText
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        // permet de lire les données sauvegardées localement avec SharedPreferences

        // Récupération des sharedPreferences de l'autre activity
        SharedPreferences prefs = getSharedPreferences("NoteData", MODE_PRIVATE);
        Log.e("PREF", prefs.toString());
        String savedTitle = prefs.getString("TITLE", "");
        String savedContent = prefs.getString("CONTENT", "");
        int index = prefs.getInt("INDEX", 0);

        if (!noteList.isEmpty()){
            if (index >= 0 && index < noteList.size()){
                // Permet de modifier les variables
                noteList.get(index).setTitle(savedTitle);
                noteList.get(index).setContent(savedContent);

                // Sauvegarde le nouvel état
                Save.writeNotes(Main.this, noteList);

                // Notofie les modifications
                adapter.notifyDataSetChanged();
            }
        }

    }

    /**
     * Méthode qui permet de rajouter des notes en cliquant sur le FloatingActionButton
     */

    public void addNote(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                StyleNote s1 = new StyleNote(Main.this);
                s1.init(Main.this, null);
                s1.setTitle("Note n°" + noteList.size());

                // Ajout de la note et notifie les changements
                noteList.add(s1);
                adapter.notifyDataSetChanged();

                // Sauvegarde le nouvel état
                Save.writeNotes(Main.this, noteList);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Permet de rajouter un menu dans l'activity
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void resetAction(MenuItem item) {
        // Permet de supprimer toutes les notes lorsqu'on clique sur le bouton "Tout supprimer"
        noteList.clear();
        adapter.notifyDataSetChanged();
        Save.writeNotes(Main.this, noteList);
    }


    /**
     * Permet de passer à l'activity EditNote lorsqu'on clique sur une note
     * (en plus des autres actions qui sont dans NoteAdapter)
     * @param position index de la note
     */
    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, EditNote.class);
        intent.putExtra("TITLE", noteList.get(position).getTitle());
        intent.putExtra("CONTENT", noteList.get(position).getContent());
        intent.putExtra(NOTE_CHOISIE, position);
        startActivity(intent);
    }

    /**
     * Permet de faire une sauvegarde lorsqu'on fait un click long
     * (en plus des autres actions qui sont dans NoteAdapter)
     * @param position index de la note
     */

    @Override
    public void onNoteLongClick(int position) {
        Save.writeNotes(Main.this, noteList);
    }


}
