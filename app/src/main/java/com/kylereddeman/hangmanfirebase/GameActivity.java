package com.kylereddeman.hangmanfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    private GameManager gameManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //this.game = Constants.dataManager.getGame();
        gameManager = new GameManager(Constants.dataManager.getGame());
        updateUI();
        db = FirebaseFirestore.getInstance();

    }

    public void onClickSubmitLetter(View view) {
        EditText guessTextField = (EditText) findViewById(R.id.editText);
        String guess = guessTextField.getText().toString();
        gameManager.guess(guess);
        updateUI();
        guessTextField.setText("");
        guessTextField.setHint(R.string.text_EnterGuess);
    }

    private void updateUI() {
        updateBoardText();
        updateStatusText();
        updateInorrectText();
        updateUsedLettersText();
    }
    private void updateBoardText() {
        TextView boardText = findViewById(R.id.textView);
        boardText.setText(gameManager.boardToString());
    }

    private void updateUsedLettersText() {
        TextView usedLettersText = findViewById(R.id.textView5);
        usedLettersText.setText(gameManager.usedLettersToString());
    }

    private void updateInorrectText() {
        TextView incorrectText = findViewById(R.id.textView4);
        String text = getString(R.string.text_incorrect_guesses);
        incorrectText.setText(text + gameManager.getGame().getIncorrectGuessCount() + "/" + Constants.MAX_INCORRECT);

    }

    private void updateStatusText() {
        TextView statusText = findViewById(R.id.textView2);
        statusText.setVisibility(View.VISIBLE);
        //should replace with switch. eh, maybe later
        if(gameManager.getGame().getGameStatus() == Constants.STATUS_CORRECT) {
            statusText.setBackgroundResource(R.color.colorCorrect);
            statusText.setText(R.string.status_correct);
        }
        else if(gameManager.getGame().getGameStatus() == Constants.STATUS_INCORRECT) {
            statusText.setBackgroundResource(R.color.colorIncorrect);
            statusText.setText(R.string.status_incorrect);
        }
        else if(gameManager.getGame().getGameStatus() == Constants.STATUS_WON) {
            statusText.setBackgroundResource(R.color.colorCorrect);
            statusText.setText(R.string.status_won);
        }
        else if(gameManager.getGame().getGameStatus() == Constants.STATUS_LOST) {
            statusText.setBackgroundResource(R.color.colorIncorrect);
            statusText.setText(R.string.status_lost);
        }
        else if(gameManager.getGame().getGameStatus() == Constants.STATUS_REPEAT) {
            statusText.setBackgroundResource(R.color.colorRepeat);
            statusText.setText(R.string.status_repeat);
        }


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
