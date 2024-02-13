package com.example.mini_projet_notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main extends AppCompatActivity {
    private FloatingActionButton boutonAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void resetAction(MenuItem item) {

    }

    public void nouvelleNote(View view) {
        Intent intent = new Intent(this, creationNote.class);
        startActivityForResult(intent, 1);
    }
}
