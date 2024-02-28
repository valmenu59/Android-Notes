package com.example.mini_projet_notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;

import java.io.*;
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

        buttonAdd = findViewById(R.id.bouton_nouvelle_note);

        recyclerView = findViewById(R.id.main_list_view);
        noteList = new ArrayList<>();
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter.setOnNoteClickListener(this);

        Save.readNotes(Main.this, noteList);

        addNote();
    }


    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        // permet de sauvegarder l'état du inputText
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Lisez les données sauvegardées localement avec SharedPreferences

        SharedPreferences prefs = getSharedPreferences("NoteData", MODE_PRIVATE);
        String savedTitle = prefs.getString("TITLE", "");
        String savedContent = prefs.getString("CONTENT", "");

        int index = prefs.getInt("INDEX", 0);

        noteList.get(index).setTitle(savedTitle);
        noteList.get(index).setContent(savedContent);

        Save.writeNotes(Main.this, noteList);
        adapter.notifyDataSetChanged();
    }

    public void addNote(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                StyleNote s1 = new StyleNote(Main.this);
                s1.init(Main.this, null);
                s1.setTitle("TITRE");
                s1.setContent("CONTENT");

                System.out.println(s1.isLongClickable());

                noteList.add(s1);

                adapter.notifyDataSetChanged();

                Save.writeNotes(Main.this, noteList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void resetAction(MenuItem item) {
        noteList.clear();
        adapter.notifyDataSetChanged();
        Save.writeNotes(Main.this, noteList);
    }

    @Override
    public void onNoteClick(int position) {
        /*
        Intent intent = new Intent(v.getContext(), EditNote.class);
        startActivityForResult(intent, 1);
         */
        Intent intent = new Intent(this, EditNote.class);
        intent.putExtra("TITLE", noteList.get(position).getTitle());
        intent.putExtra("CONTENT", noteList.get(position).getContent());
        intent.putExtra(NOTE_CHOISIE, position);
        startActivity(intent);
    }

    @Override
    public void onNoteLongClick(int position) {
        Save.writeNotes(Main.this, noteList);
    }


}
