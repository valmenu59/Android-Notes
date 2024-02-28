package com.example.mini_projet_notes.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.InputFilter;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mini_projet_notes.R;

public class EditNote extends AppCompatActivity {

    private TextView inputTitre;
    private TextView inputContenu;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        // Récupération des ID
        inputTitre = findViewById(R.id.textInputEditText);
        inputContenu = findViewById(R.id.editTextTextMultiLine);

        // PERMET D'IMPOSER UNE LIMITE DE 40 CARACTERES POUR LE TITRE
        InputFilter[] filter = new InputFilter[1];
        filter[0] = new InputFilter.LengthFilter(40);
        inputTitre.setFilters(filter);

        // Récupération des intents
        Intent intent = getIntent();
        index = intent.getIntExtra(Main.NOTE_CHOISIE, 0);
        String title = intent.getStringExtra("TITLE");
        String content = intent.getStringExtra("CONTENT");

        // Ajout des textes
        inputTitre.setText(title);
        inputContenu.setText(content);

        // Ajout des contraintes au niveau du texte
        writeKeyBoard();
    }


    /**
     * Permet de sauvegarder les données de l'activity en cours
     */
    @Override
    protected void onPause() {
        super.onPause();
        // Récupération du titre et du contenu
        String updatedTitle = inputTitre.getText().toString();
        String updatedContent = inputContenu.getText().toString();

        // sauvegarde les données localement avec SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("NoteData", MODE_PRIVATE).edit();
        editor.putString("TITLE", updatedTitle);
        editor.putString("CONTENT", updatedContent);
        editor.putInt("INDEX", index);
        editor.apply();
    }

    /**
     * Ajout des contraintes supplémentaires sur les actions avec les notes
     */

    public void writeKeyBoard(){
        inputTitre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    // Permet d'ignorer la touche ENTER
                    return event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
                }
                return false;
            }
        });
    }

}
