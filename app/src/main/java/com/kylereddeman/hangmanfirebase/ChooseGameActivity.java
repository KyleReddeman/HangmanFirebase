package com.kylereddeman.hangmanfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChooseGameActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "ChooseGameActivity";
    private List<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        games = new ArrayList<Game>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Constants.GAMES_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        games.add(document.toObject(Game.class));
                        Log.d(TAG, "Loaded game: " + document.getId() + " ==> " + document.getData());
                    }
                    initRecycler();
                    populateRecycler();
                }
                else {
                    Log.d(TAG, "Error loading documents", task.getException());
                }
            }
        });
    }

    private void initRecycler() {
        mRecyclerView = (RecyclerView)findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GamesRecyclerAdapter(games, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void populateRecycler() {

    }
}
