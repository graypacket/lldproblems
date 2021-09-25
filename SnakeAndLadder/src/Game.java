import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.PathGraph;

public class Game {
    private static Game gameInstance;
    private Player currPlayer;
    private Dice dice;
    private Board board;
    private boolean gameEnded;

    private List<Player> activePlayers;
    private List<Player> rankings;

    private Game(int numPlayers, List<Snake> snakes, List<Ladder> ladders) {
        dice = new Dice();
        board = new Board(snakes, ladders);

        activePlayers = new ArrayList<>();
        rankings = new ArrayList<>();

        for(int player=0; player<numPlayers; player++) {
            activePlayers.add(new Player(player+1));
        }

        currPlayer = activePlayers.get(0);
    }

    public static Game getInstance(int numPlayers, List<Snake> snakes, List<Ladder> ladders) {
        if(numPlayers < 2 || !validateSnakesAndLadders(snakes, ladders)) {
            System.out.println("Input validation failed!!!");
            return null;
        }
        return gameInstance != null ? gameInstance : (gameInstance = new Game(numPlayers, snakes, ladders));
    }

    public static boolean validateSnakesAndLadders(List<Snake> snakes, List<Ladder> ladders) {
        Set<Integer> pathStart = new HashSet<>();
        PathGraph graph = new PathGraph();

        for(Snake snake : snakes) {
            if  (!(
                snake.getStart() >= 1 && snake.getStart() < 100 //cannot put snake at 100th cell
                && snake.getEnd() >= 1 && snake.getEnd() <= 100
                &&  snake.getStart() > snake.getEnd()   // snake's tail must be on a lower cell
                && Board.getRow(snake.getStart()) != Board.getRow(snake.getEnd()) // should not start and end in the same row
            )) return false;
            if(pathStart.contains(snake.getStart())) return false;
            pathStart.add(snake.getStart());
            graph.addEdge(snake.getStart(), snake.getEnd());
        }

        for(Ladder ladder : ladders) {
            if  (!(
                ladder.getStart() >= 1 && ladder.getStart() <= 100
                && ladder.getEnd() >= 1 && ladder.getEnd() <= 100
                &&  ladder.getStart() < ladder.getEnd() // ladder's end must be on a higher cell
                && Board.getRow(ladder.getStart()) != Board.getRow(ladder.getEnd())
            )) return false;
            if(pathStart.contains(ladder.getStart())) return false;
            pathStart.add(ladder.getStart());
            graph.addEdge(ladder.getStart(), ladder.getEnd());
        }

        return !graph.isCyclic();
    }

    public void takeTurn() {
        int diceNumber = this.dice.roll();
        System.out.printf("Player %d takes turn and got %d.\n", currPlayer.getId(), diceNumber);

        int saveOldPos = currPlayer.getPos();
        currPlayer.setPos(this.getNewPos(currPlayer.getPos(), diceNumber));

        int consecutiveSixes = 0;
        if(diceNumber == 6) consecutiveSixes++;

        if(!hasPlayerWon())
            while(diceNumber == 6) {
                diceNumber = this.dice.roll();
                System.out.printf("Player %d takes turn and got %d.\n", currPlayer.getId(), diceNumber);

                if(diceNumber == 6) consecutiveSixes++;
                if(consecutiveSixes == 3) {
                    System.out.printf("-- Player %d got 3 consecutive sixes, moving Player %d back from %d to %d.\n", currPlayer.getId(), currPlayer.getId(), currPlayer.getPos(), saveOldPos);
                    currPlayer.setPos(saveOldPos);
                    break;
                }
                currPlayer.setPos(this.getNewPos(currPlayer.getPos(), diceNumber));

                if(hasPlayerWon()) break;
            }

        if(isGameEnded()) return;
        
        //change current player in round robin fashion
        currPlayer = this.activePlayers.get((this.activePlayers.indexOf(currPlayer) + 1)% this.activePlayers.size());
    }

    public int getNewPos(int oldPos, int diceNumber) {
        int newPos = oldPos + diceNumber > 100 ? 100 : oldPos + diceNumber;

        System.out.printf("-- Moves from %d to %d.\n", oldPos, newPos);

        Cell newCell = this.board.getCells().get(newPos);
        while(newCell.isLadderPresent() || newCell.isSnakePresent()) { 
            if(newCell.isLadderPresent())
                System.out.printf("-- Took a ladder from %d to %d.\n", newCell.getId(), newCell.getConnectedCellId());
            else System.out.printf("-- Bitten by a Snake, slides from %d to %d.\n", newCell.getId(), newCell.getConnectedCellId());

            newCell = this.board.getCells().get(newCell.getConnectedCellId());
        }
        return newCell.getId();
    }

    public boolean hasPlayerWon() {
        if(currPlayer.getPos() == 100) {
            rankings.add(currPlayer);
            activePlayers.remove(currPlayer);
            if(activePlayers.size() <= 1) {
                rankings.add(activePlayers.get(0));
                gameEnded = true;
            }
            return true;
        }
        return false;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public List<Player> getRankings() {
        return this.rankings;
    }
}