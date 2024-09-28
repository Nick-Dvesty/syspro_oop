package ru.nsu.syspro.Muller;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество колод, которые будут использоваться для игры: ");
        int countDesks = scanner.nextInt();

        System.out.print("Введите количество раундов в игре: ");
        int countRound = scanner.nextInt();
        Game game = new Game(countDesks,countRound);
        game.StartGame();
        scanner.close();
    }
}