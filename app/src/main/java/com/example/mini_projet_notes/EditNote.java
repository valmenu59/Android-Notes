package com.example.mini_projet_notes;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EditNote extends AppCompatActivity {

    private TextView inputTitre;
    private TextView inputContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        inputTitre = findViewById(R.id.textInputEditText);
        inputContenu = findViewById(R.id.editTextTextMultiLine);

        inputTitre.setText("");
        inputContenu.setText("");

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("TITLE");
            String content = intent.getStringExtra("CONTENT");
            inputTitre.setText(title);
            inputContenu.setText(content);
        }
    }

}
