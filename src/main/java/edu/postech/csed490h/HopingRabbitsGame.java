package edu.postech.csed490h;

/**
 * Two teams of N rabbits each are placed facing each other in a row with 2N + 1
 * positions. Initially, the x team occupies the first N positions, the o team
 * occupies the last N positions, and the middle position is empty. The goal is
 * to swap the positions of the two teams by moving the rabbits. A rabbit can
 * move to an empty position or jump over a rival to an empty position.
 */
public class HopingRabbitsGame {
    private final int numRabbitsOnEachTeam; // stores N
    private final int numPositionInGame; // stores 2N + 1

    /**
     * Represents the state of game as an array of length 2N + 1.
     * For example, rabbit on team x positioned at the first position
     * corresponds to Rabbit.X stored in index 0.
     */
    private Rabbit[] gameState;

    /**
     * Create a game with n rabbits on each team. For example, if n = 3, the initial
     * state is xxx_yyy, where x represents a rabbit from the x team, y represents a
     * rabbit from the o team, and _ represents an empty position.
     *
     * @param n the number of rabbits on each team
     */
    HopingRabbitsGame(int n) {
        numRabbitsOnEachTeam = n;
        numPositionInGame = 2 * n + 1;

        // Initialize game state
        gameState = new Rabbit[numPositionInGame];
        for (int position = 0; position < numRabbitsOnEachTeam; position++)
        {
            // x team occupies the first N positions
            gameState[position] = Rabbit.X;

            // o team occupies the last N positions
            gameState[(numPositionInGame - 1) - position] = Rabbit.O;
        }
    }

    /**
     * Return true if position is valid i.e. 0 <= position < numPositionInGame, false otherwise
     *
     * @param position the checking position
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidPosition(int position) {
        return 0 <= position && position < numPositionInGame; // position starts from zero
    }

    /**
     * Try to update a rabbit to a new position.
     * If the original or new position is invalid, return fase..
     * Move a rabbit to an empty position. Rabbits from the x team can only move to
     * the right, and rabbits from the o team can only move to the left. A rabbit is
     * allowed to advance one position if that position is empty. A rabbit can jump
     * over a rival if the position behind the rival is empty. For example, if the
     * current state is xxxo_oo, the x team can move to xx_oxoo.
     *
     * @param rabbit a rabbit
     * @return true if the rabbit can move, false otherwise
     */
    private boolean tryUpdatingRabbitPosition(Rabbit rabbit, int originalPosition, int newPosition) {
        if (isValidPosition(originalPosition) && isValidPosition((newPosition))) {
            gameState[originalPosition] = null; // original position is now empty
            gameState[newPosition] = rabbit;
            return true;
        }
        return false;
    }

    /**
     * Move a rabbit to an empty position. Rabbits from the x team can only move to
     * the right, and rabbits from the o team can only move to the left. A rabbit is
     * allowed to advance one position if that position is empty. A rabbit can jump
     * over a rival if the position behind the rival is empty. For example, if the
     * current state is xxxo_oo, the x team can move to xx_oxoo.
     *
     * @param rabbit a rabbit
     * @return true if the rabbit can move, false otherwise
     */
    boolean move(Rabbit rabbit) {
        int movableRabbitPosition = -1;
        String currentGameState = this.getState();

        // Check if rabbit can advance (empty position in front of x or o)
        // And if rabbit can advance, advance and update game state
        if (rabbit == Rabbit.X) {
            movableRabbitPosition = currentGameState.indexOf("x_");
            if (tryUpdatingRabbitPosition(rabbit, movableRabbitPosition, movableRabbitPosition + 1)) {
                return true;
            }
        } else {
            movableRabbitPosition = currentGameState.indexOf("_o");
            if (tryUpdatingRabbitPosition(rabbit, movableRabbitPosition + 1, movableRabbitPosition)) {
                return true;
            }
        }

        // Check if rabbit can jump (o/x and empty position in front of x/o)
        // And if rabbit can jump, jump and update game state

        return false;
    }

    /**
     * Return true if the game is over. The game is over if the two teams have swapped
     * their initial positions: e.g., ooo_xxx, when N = 3.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGoal() {
        for (int position = 0; position < numRabbitsOnEachTeam; position++)
        {
            // o team should occupy the first N positions
            if (gameState[position] != Rabbit.O) { return false; }

            // x team should occupy the last N positions
            if (gameState[(numPositionInGame - 1) - position] != Rabbit.X) { return false; }
        }
        return true;
    }

    /**
     * Return true if the game is stuck. The game is stuck if no rabbit can move.
     *
     * @return true if the game is stuck, false otherwise
     */
    boolean isStuck() {
        return true;
    }

    /**
     * Return the current state of the game. The state is represented as a string of
     * length 2N + 1 consisting of x, o, and _.
     *
     * @return a string of length 2N + 1 consisting of x, o, and _.
     */
    String getState() {
        StringBuilder state = new StringBuilder();

        return state.toString();
    }

    public static void main(String[] args) {
        var game = new HopingRabbitsGame(3);

        System.out.println(game.getState());
        game.move(Rabbit.X);
        System.out.println(game.getState());
        game.move(Rabbit.O);
        System.out.println(game.getState());
    }

}
