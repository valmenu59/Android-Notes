package com.example.mini_projet_notes.notes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.mini_projet_notes.R;

import java.io.Serializable;

public class StyleNote extends LinearLayout implements Serializable {
    private TextView textTitle;
    private TextView textContent;


    public StyleNote(Context context){
        super(context);
    }

    public StyleNote(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StyleNote(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.style_note, this);
        textTitle = findViewById(R.id.title);
        textContent = findViewById(R.id.content);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StyleNote);
        String titre = a.getString(R.styleable.StyleNote_Titre);
        String texte = a.getString(R.styleable.StyleNote_Texte);
        a.recycle();

        textTitle.setText(titre);
        textContent.setText(texte);
    }

    // Méthodes pour définir le titre et le contenu
    public void setTitle(String title) {
        textTitle.setText(title);
    }

    public void setContent(String content) {
        textContent.setText(content);
    }

    public String getTitle(){
        return textTitle.getText().toString();
    }

    public String getContent(){
        return textContent.getText().toString();
    }

}
