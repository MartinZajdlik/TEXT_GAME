package martingame.service;

import martingame.domain.Enemy;
import martingame.domain.Hero;
import martingame.utility.InputUtils;
import martingame.utility.PrintUtils;

import java.sql.SQLOutput;

public class BattleService {
    public boolean isHeroReadyToBattle(Hero hero, Enemy enemy) {
        System.out.println(hero.getName() + " VS " + enemy.getName());
        System.out.println("View your abilities:");
        PrintUtils.printAbilities(hero);
        System.out.println("View enemy abilities:");
        PrintUtils.printAbilities(enemy);

        System.out.println("Are you ready to fight");
        System.out.println("0. No");
        System.out.println("1. Yes");

        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> {
                System.out.println("You have escaped from battle");
                return false;
            }
            case 1 -> {
                System.out.println("Let the battle begin");
                return true;
            }
            default -> {
                System.out.println("Invalid choice");
                return false;
            }
        }


    }
}
