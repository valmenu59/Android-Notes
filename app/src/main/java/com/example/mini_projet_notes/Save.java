package com.example.mini_projet_notes;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.util.List;

public final class Save {

    private static final String SAVE_FILE = "note.txt";

    public static void writeNotes(AppCompatActivity activity, List<StyleNote> noteList){
        try {
            FileOutputStream fos = activity.openFileOutput(SAVE_FILE, Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos));
            for (StyleNote note : noteList) {
                writer.println(note.getTitle() + "," + note.getContent());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void readNotes(AppCompatActivity activity, List<StyleNote> noteList){
        try {
            FileInputStream fis = activity.openFileInput(SAVE_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                String content = parts[1];
                // Créez une nouvelle note avec le titre et le contenu lus depuis le fichier
                StyleNote styleNote = new StyleNote(activity);
                styleNote.init(activity, null);
                styleNote.setTitle(title);
                styleNote.setContent(content);
                noteList.add(styleNote);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
