package com.example.mini_projet_notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EditNote extends AppCompatActivity {

    private TextView inputTitre;
    private TextView inputContenu;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        inputTitre = findViewById(R.id.textInputEditText);
        inputContenu = findViewById(R.id.editTextTextMultiLine);

        // PERMET D'IMPOSER UNE LIMITE DE 40 CARACTERES POUR LE TITRE
        InputFilter[] filter = new InputFilter[1];
        filter[0] = new InputFilter.LengthFilter(40);
        inputTitre.setFilters(filter);

        inputTitre.setText("");
        inputContenu.setText("");


        Intent intent = getIntent();

        index = intent.getIntExtra(Main.NOTE_CHOISIE, 0);
        String title = intent.getStringExtra("TITLE");
        String content = intent.getStringExtra("CONTENT");

        inputTitre.setText(title);
        inputContenu.setText(content);




        writeKeyBoard();
    }



    @Override
    protected void onPause() {
        super.onPause();
        // Récupérez le titre et le contenu actuels de la note
        String updatedTitle = inputTitre.getText().toString();
        String updatedContent = inputContenu.getText().toString();

        // Sauvegardez les données localement avec SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("NoteData", MODE_PRIVATE).edit();
        editor.putString("TITLE", updatedTitle);
        editor.putString("CONTENT", updatedContent);
        editor.putInt("INDEX", index);
        editor.apply();
    }

    public void writeKeyBoard(){
        inputTitre.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    return event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
                }
                return false;
            }
        });
        inputTitre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
