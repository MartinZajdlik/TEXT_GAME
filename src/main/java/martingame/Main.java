package martingame;

import martingame.service.GameManager;

public class Main {


    public static void main(String[] args) {
        final GameManager gamemanager = new GameManager();
        gamemanager.startGame();

    }
}