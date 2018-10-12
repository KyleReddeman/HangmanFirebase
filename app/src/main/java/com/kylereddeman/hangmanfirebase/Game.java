package com.kylereddeman.hangmanfirebase;

import java.util.List;

public class Game {

    private String creator;
    private int incorrectGuessCount;
    private String player;
    private int gameStatus;
    private List<String> usedLetters;
    private String word;
    private String board;

    public Game() {

    }
    public Game(String word, String creator, String player, int gameStatus, List<String> usedLetters) {
        this.word = word;
        this.creator = creator;
        this.player = player;
        this.gameStatus = gameStatus;
        this.usedLetters = usedLetters;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getIncorrectGuessCount() {
        return this.incorrectGuessCount;
    }

    public void setIncorrectGuessCount(int incorrectGuessCount) {
        this.incorrectGuessCount = incorrectGuessCount;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<String> getUsedLetters() {
        return this.usedLetters;
    }

    public void setUsedLetters(List<String> usedLetters) {
        this.usedLetters = usedLetters;
    }

    public String getBoard() {
        return this.board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
