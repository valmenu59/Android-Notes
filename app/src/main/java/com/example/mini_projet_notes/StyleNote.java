package com.example.mini_projet_notes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StyleNote extends LinearLayout {
    private TextView textTitle;
    private TextView textContent;

    public StyleNote(Context context) {
        super(context);
        init(context);
    }

    public StyleNote(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StyleNote(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.style_note, this);
        textTitle = findViewById(R.id.text_title);
        textContent = findViewById(R.id.text_content);
    }

    // Méthodes pour définir le titre et le contenu
    public void setTitle(String title) {
        textTitle.setText(title);
    }

    public void setContent(String content) {
        textContent.setText(content);
    }
}
