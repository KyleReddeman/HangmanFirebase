package com.kylereddeman.hangmanfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private static final String TAG = "GameActivity";
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.game = Constants.dataManager.getGame();


        db = FirebaseFirestore.getInstance();

    }

    public void createGame(View view) {
        Map<String, Object> docData = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Game game = new Game("Quartz", "Gary", "Bob", 3, list);
       // db.collection("games").document("Game3").set(game);
        db.collection("games").add(game).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Game added with id: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding Game", e);
            }
        });

    }
}
