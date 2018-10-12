package com.kylereddeman.hangmanfirebase;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameManager {
    private Game game;
    private List<String> boardLetters;


    public GameManager(Game game) {
        this.game = game;
        if(game == null) {
            initNewGame();
        }
        //might need to remove space at end of board
        //board could be null
        boardLetters = Arrays.asList(game.getBoard().split(" "));
    }

    private void initNewGame() {

    }

    public void guess(String userGuess) {
        if(!letterGuessed(userGuess)) {
            String oldBoard = game.getBoard();
            addLetter(userGuess);
            if(game.getBoard().equalsIgnoreCase(oldBoard)) {
                game.setIncorrectGuessCount(game.getIncorrectGuessCount() + 1);
                if(game.getIncorrectGuessCount() >= Constants.MAX_INCORRECT) {
                    game.setGameStatus(Constants.STATUS_LOST);
                }
                else {
                    game.setGameStatus(Constants.STATUS_INCORRECT);
                }
            }
            else {
                if(game.getWord().equalsIgnoreCase(getWorkingWord())) {
                    game.setGameStatus(Constants.STATUS_WON);
                }
                else {
                    game.setGameStatus(Constants.STATUS_CORRECT);
                }
            }
        }
        else {
            game.setGameStatus(Constants.STATUS_REPEAT);
        }
    }

    public Game getGame() {
        return this.game;
    }

    public String boardToString() {
        String s = "";
        for (int i = 0; i < game.getWord().length(); i++) {
            s += boardLetters.get(i) + " ";
        }
        return s;
    }

    public String usedLettersToString() {
        String letters = "";
        if (game.getUsedLetters().size() > 0) {
            letters += game.getUsedLetters().get(0);
        }
        for (int i = 1; i < game.getUsedLetters().size(); i++) {
            letters += ", " + game.getUsedLetters().get(i);
        }
        return letters;
    }

    private String getWordFromWordBank(Context context) {
        List<String> wordBank = createWordBank(context);
        Random random	= new Random();
        String word = "Hello";
        if(wordBank.size() > 0) {
            word = wordBank.get(random.nextInt(wordBank.size())).trim();
        }
        return word;
    }

    private List<String> createWordBank(Context context) {
        List<String> wordBank = null;
        return wordBank;
    }

    public void addLetter(String s) {
        set(s);
        game.getUsedLetters().add(0, String.valueOf(Character.toLowerCase(s.trim().charAt(0))));
    }

    public boolean letterGuessed(String s) {
        return game.getUsedLetters().contains(String.valueOf(s.toLowerCase().trim().charAt(0)));
    }

    private void set(String s) {
        s = s.trim();
        char c = s.charAt(0);
        c = Character.toLowerCase(c);
        for (int i = 0; i < game.getWord().length(); i++) {
            if (c == Character.toLowerCase(game.getWord().charAt(i))) {
                boardLetters.set(i, String.valueOf(game.getWord().charAt(i)));
            }
        }
        game.setBoard(boardToString());
    }

    private String getWorkingWord() {
        String workingWord = "";
        for (int i = 0; i < game.getWord().length(); i++) {
            workingWord += boardLetters.get(i);
        }
        return workingWord;
    }
}
