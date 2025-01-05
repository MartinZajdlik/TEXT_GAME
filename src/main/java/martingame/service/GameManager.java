package martingame.service;

import martingame.ability.Ability;
import martingame.ability.HeroAbilityManager;
import martingame.domain.Hero;
import martingame.utility.InputUtils;
import martingame.utility.PrintUtils;

import java.util.Map;

public class GameManager {

    private final Hero hero;
    private final HeroAbilityManager heroAbilityManager;

    public GameManager(){
        this.hero = new Hero("");
        this.heroAbilityManager = new HeroAbilityManager(this.hero);
    }

    public void startGame () {
        System.out.println("Welcome to GLADIATOR GAME");
        System.out.println("Enter your name: ");
        final String name = InputUtils.readString();
        this.hero.setName(name);
        System.out.println("Hello "+hero.getName()+". Let's start the game!");
        PrintUtils.printDivider();
        System.out.println("Your abilities: ");
        PrintUtils.printAbilities(hero);
        PrintUtils.printDivider();
        this.heroAbilityManager.spendHeroAvailablePoints();
    }

}
