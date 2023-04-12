/*
*author @graypacket
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
