package com.example.mini_projet_notes;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mini_projet_notes.notes.StyleNote;

import java.io.*;
import java.util.List;

public final class Save {

    private static final String SAVE_FILE = "note.txt";

    /**
     * Méthode statique qui permet d'écrire dans le fichier texte les notes créées
     * @param activity : Activity en cours
     * @param noteList : Liste des notes à sauvegarder
     */

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

    /**
     * Méthode statique qui permet de récupérer les notes écrites dans le fichier texte
     * @param activity : Activity en cours
     * @param noteList : Liste des notes à ajouter
     */


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
