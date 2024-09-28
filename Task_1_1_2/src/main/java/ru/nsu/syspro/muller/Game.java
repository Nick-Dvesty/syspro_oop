package ru.nsu.syspro.muller;

import java.util.Scanner;

/**
 * baseclass game.
 */
public class Game {

    private Gamer player;

    private Gamer dealer;

    private DeskCard deskCard;

    private int CountDesk;

    private int CountRound;

    private int CountPointPlayer;

    private int CountPointDealer;

    /**
     * Base constructor.
     *
     * @param countDesk count desk card
     * @param countRound count round game
     */
    Game(int countDesk, int countRound) {
        this.CountDesk = countDesk;
        this.CountRound = countRound;
        CountPointDealer = 0;
        CountPointPlayer = 0;
    }

    /**
     * method start game.
     */
    public void startGame() {
        System.out.println("Добро пожаловать в Блэкджек!");
        for (int i = 0; i < CountRound; i++) {
            startRound(i);
        };
    }

    private void startRound(int round) {
        deskCard = new DeskCard(CountDesk);
        player = new Gamer();
        dealer = new Gamer();
        System.out.println("Раунд " + round);
        System.out.println("Дилер раздал карты");
        player.takeCard(deskCard, false);
        player.takeCard(deskCard, false);
        dealer.takeCard(deskCard, false);
        dealer.takeCard(deskCard, true);
        printSituation();
        boolean continuum =  movePlayer();
        if (continuum) moveDealer();
        results();

    }

    private boolean movePlayer() {
        System.out.println("Ваш ход \n -------");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (player.getSum() >= 21) return false;
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
            char action = scanner.next().charAt(0);
            switch (action) {
                case '1': {
                    var thisCard = player.takeCard(deskCard, false);
                    System.out.println("Вы открыли карту " + thisCard.printText());
                    printSituation();
                    System.out.println();
                    continue;
                }
                case '0': return true;
            }
        }
    }

    private void moveDealer() {
        System.out.println("Ход дилера \n -------");
        System.out.println("Дилер открывает закрытую карту " + dealer.openLastCards());
        printSituation();
        while (dealer.getSum() < 17) {
            var thisCard = dealer.takeCard(deskCard, false);
            System.out.println("Дилер открывает карту " + thisCard.printText());
            printSituation();
            System.out.println();
        }
    }

    private void results() {
        if (dealer.getSum() > 21) CountPointPlayer += 1;
        else if (player.getSum() > 21) CountPointDealer += 1;
        else if (player.getSum() == 21) CountPointPlayer += 1;
        else if (dealer.getSum() == 21) CountPointDealer += 1;
        else if (player.getSum() > dealer.getSum()) CountPointPlayer += 1;
        else if (player.getSum() < dealer.getSum()) CountPointDealer += 1;
        System.out.println("Очки игрока: " + CountPointPlayer + " Очки дилера: " + CountPointDealer);
    }

    private void printSituation() {
        System.out.println("    Ваши карты: " + player.printCards());
        System.out.println("    Карты дилера: " + dealer.printCards());
    }
}
