package com.example.mini_projet_notes;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mini_projet_notes.notes.StyleNote;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class Save {

    private static final String SAVE_FILE = "note";

    /**
     * Méthode statique qui permet d'écrire dans le fichier texte les notes créées
     * @param context : Context en cours
     * @param noteList : Liste des notes à sauvegarder
     */

    public static void writeNotes(Context context, List<StyleNote> noteList){
        try {
            FileOutputStream fos = context.openFileOutput(SAVE_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            List<Note> notes = new ArrayList<>();
            for (StyleNote styleNote: noteList){
                notes.add(new Note(styleNote.getTitle(), styleNote.getContent()));
            }

            Gson gson = new Gson();
            String json = gson.toJson(notes);

            writer.write(json);
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
        StringBuilder jsonBuilder = new StringBuilder();
        try {
            FileInputStream fis = activity.openFileInput(SAVE_FILE);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            fis.close();

            String json = jsonBuilder.toString();
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Note>>(){}.getType();
            List<Note> notes = gson.fromJson(json, listType);

            for (Note note: notes){
                StyleNote styleNote = new StyleNote(activity);
                styleNote.init(activity, null);
                styleNote.setTitle(note.titre);
                styleNote.setContent(note.contenu);
                noteList.add(styleNote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Note{
        private final String titre;
        private final String contenu;

        private Note(String t, String c){
            titre = t;
            contenu = c;
        }
    }
}
