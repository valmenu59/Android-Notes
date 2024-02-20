package com.example.mini_projet_notes;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<StyleNote> noteList;
    private FloatingActionButton buttonAdd;

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


        addNote();




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
    }
}
