/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own personal use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
import java.util.Random;

public class Dice {
    private static final int DICE_SIZE = 6;
    private Random random;

    Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(DICE_SIZE) + 1; // random number between 1 and 6
    }
}
