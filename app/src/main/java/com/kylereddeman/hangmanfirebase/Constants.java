package com.kylereddeman.hangmanfirebase;

public class Constants {
    public static final String GAMES_COLLECTION = "games";
    public static final DataManager dataManager = new DataManager();
    public static final int MAX_INCORRECT = 8;
    public static final int STATUS_CORRECT = 0;
    public static final int STATUS_INCORRECT = 1;
    public static final int STATUS_WON = 2;
    public static final int STATUS_LOST = 3;
    public static final int STATUS_REPEAT = 4;
}
