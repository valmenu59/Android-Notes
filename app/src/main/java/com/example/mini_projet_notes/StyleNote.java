package com.example.mini_projet_notes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StyleNote extends FrameLayout {

    private String titre;
    private String texte;

    public StyleNote(@NonNull Context context) {
        super(context);
    }

    public StyleNote(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StyleNote(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StyleNote(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
