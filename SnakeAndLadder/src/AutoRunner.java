import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoRunner {
    public static void main(String[] args) {
        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();
        
        Scanner inputScanner = new Scanner(System.in);
        int numPlayers = inputScanner.nextInt();

        int numSnakes = inputScanner.nextInt();
        int[] snakeHeads = new int[numSnakes];
        int[] snakeTails = new int[numSnakes];
        for(int snakeIndex=0; snakeIndex<numSnakes; snakeIndex++) {
            snakeHeads[snakeIndex] = inputScanner.nextInt();
        }
        for(int snakeIndex=0; snakeIndex<numSnakes; snakeIndex++) {
            snakeTails[snakeIndex] = inputScanner.nextInt();
        }
        for(int snakeIndex=0; snakeIndex<numSnakes; snakeIndex++) {
            snakes.add(new Snake(snakeHeads[snakeIndex], snakeTails[snakeIndex]));
        }

        int numLadders = inputScanner.nextInt();
        int[] laddersStart = new int[numLadders];
        int[] laddersEnd = new int[numLadders];

        for(int ladderIndex=0; ladderIndex<numLadders; ladderIndex++) {
            laddersStart[ladderIndex] = inputScanner.nextInt();
        }
        for(int ladderIndex=0; ladderIndex<numLadders; ladderIndex++) {
            laddersEnd[ladderIndex] = inputScanner.nextInt();
        }

        for(int ladderIndex=0; ladderIndex<numLadders; ladderIndex++) {
            ladders.add(new Ladder(laddersStart[ladderIndex], laddersEnd[ladderIndex]));
        }

        inputScanner.close();

        Game game = Game.getInstance(numPlayers, snakes, ladders);
        
        while(!game.isGameEnded()) {
            game.takeTurn();
        }

        System.out.println("Game ends.\nRankings:");
            
        List<Player> rankings = game.getRankings();
        int rank = 1;
        for(Player player : rankings) {
            System.out.printf("%d. Player %d\n", rank++, player.getId());
        }
    }
}
