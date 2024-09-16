package ru.nsu.syspro.Muller;

import java.util.Scanner;

public class Game {
    private Gamer player;
    private Gamer dealer;
    private DeskCard deskCard;
    private int CountDesk;
    private int CountRound;
    private int CountPointPlayer;
    private int CountPointDealer;


    Game(int countDesk, int countRound) {
        this.CountDesk = countDesk;
        this.CountRound = countRound;
        player = new Gamer();
        dealer = new Gamer();
        CountPointDealer = 0;
        CountPointPlayer = 0;
    }
    public void StartGame() {
        System.out.println("Добро пожаловать в Блэкджек!");
        for (int i = 0; i < CountDesk; i++) {
            StartRound(i);
        };
    }
    private void StartRound(int round) {
        deskCard = new DeskCard(CountDesk);
        System.out.println("Раунд 1");
        System.out.println("Дилер раздал карты");
        player.TakeCard(deskCard, false);
        player.TakeCard(deskCard, false);
        dealer.TakeCard(deskCard, false);
        dealer.TakeCard(deskCard, true);
        printSituation();
        boolean continuum =  movePlayer();
        if (continuum) moveDealer();
        results();

    }
    private boolean movePlayer() {
        System.out.println("Ваш ход \n -------");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (player.GetSum() >= 21) return false;
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
            char action = scanner.next().charAt(0);
            switch (action) {
                case '1': {
                    var thisCard = player.TakeCard(deskCard, false);
                    System.out.println("Вы открыли карту" + thisCard.PrintText());
                    printSituation();
                    continue;
                }
                case '0': return true;
            }
        }
    }
    private void moveDealer() {
        System.out.println("Ход дилера \n -------");
        System.out.println("Дилер открывает закрытую карту" + dealer.OpenLastCards());
        while (dealer.GetSum() >= 17) {
            var thisCard = dealer.TakeCard(deskCard, false);
            System.out.println("Дилер открывает карту " + thisCard.PrintText());
            printSituation();
        }
    }
    private void results() {
        if (dealer.GetSum() > 21) CountPointPlayer += 1;
        else if (player.GetSum() > 21) CountPointDealer += 1;
        else if (player.GetSum() == 21) CountPointPlayer += 1;
        else if (dealer.GetSum() == 21) CountPointDealer += 1;
        System.out.println("Очки игрока: " + CountPointPlayer + "Очки дилера" + CountPointDealer);
    }
    private void printSituation() {
        System.out.println("    Ваши карты: " + player.PrintCards());
        System.out.println("    Карты дилера: " + dealer.PrintCards());
    }
}
