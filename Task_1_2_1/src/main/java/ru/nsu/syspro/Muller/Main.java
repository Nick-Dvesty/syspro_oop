package ru.nsu.syspro.Muller;

/**
 * start point class.
 */
public class Main {

    /**
     * without this, the tests on the git fail.
     */
    public Main(){};

    /**
     * start point method.
     *
     * @param args arguments command liner
     */
    public static void main(String[] args) {
    Game game = new Game(1,2);
    game.StartGame();
    }
}