package martingame.service;

import martingame.ability.Ability;
import martingame.ability.HeroAbilityManager;
import martingame.constant.Constant;
import martingame.domain.Enemy;
import martingame.domain.Hero;
import martingame.domain.LoadedGame;
import martingame.utility.EnemyGenerator;
import martingame.utility.InputUtils;
import martingame.utility.PrintUtils;

import java.util.Map;

public class GameManager {

    private Hero hero;
    private final HeroAbilityManager heroAbilityManager;
    private int currentLevel;
    private final FileService fileService;
    private final Map<Integer, Enemy> enemiesByLevel;
    private final BattleService battleService;



    public GameManager() {
        this.hero = new Hero("");
        this.heroAbilityManager = new HeroAbilityManager(this.hero);
        this.currentLevel = Constant.INITIAL_LEVEL;
        this.fileService = new FileService();
        this.enemiesByLevel = EnemyGenerator.createEnemies();
        this.battleService = new BattleService();
    }

    public void startGame() throws InterruptedException {
        this.initGame();

        while (this.currentLevel <= this.enemiesByLevel.size()) {
            final Enemy enemy = this.enemiesByLevel.get(this.currentLevel);
            System.out.println("0. Fight " + enemy.getName() + "(Level " + this.currentLevel+")");
            System.out.println("1. Upgrade abilities (" + hero.getHeroAvailablePoints() + " points to spend)");
            System.out.println("2. Save Game");
            System.out.println("3. Exit game");

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> {

                    if (this.battleService.isHeroReadyToBattle(this.hero, enemy)) {
                        final int heroHealthBeforeBattle = this.hero.getAbilities().get(Ability.HEALTH);

                        final boolean hasHeroWon = this.battleService.battle(this.hero, enemy);
                        if (hasHeroWon) {
                            PrintUtils.printDivider();
                            System.out.println("You have won this battle! You have gained "+ this.currentLevel+ " ability points.");
                            this.hero.updateAvailablePoints(this.currentLevel);
                            this.currentLevel++;
                        }else{
                            System.out.println("You have lost!");
                        }
                        this.hero.setAbility(Ability.HEALTH, heroHealthBeforeBattle);
                        System.out.println("You have full health now");
                        PrintUtils.printDivider();


                    }
                }
                case 1 -> {

                    this.upgradeAbilites();
                }
                case 2 -> {

                    this.fileService.saveGame(this.hero, this.currentLevel);
                }
                case 3 -> {
                    System.out.println("Are you sure?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    final int exitChoice = InputUtils.readInt();
                    if (exitChoice == 1) {
                        System.out.println("Bye!");
                        return;
                    }
                    System.out.println("Continuing...");
                    PrintUtils.printDivider();
                }
                default -> System.out.println("Invalid input");

            }

        }
        System.out.println("You have won the game! Congratulations!");
    }

    private void upgradeAbilites() {
        System.out.println("Your abilities are: ");
        PrintUtils.printAbilities(this.hero);

        System.out.println("0. Go back");
        System.out.println("1. Spend points (" + this.hero.getHeroAvailablePoints() + " points to spend)");
        System.out.println("2. Remove points");

        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> {
            }
            case 1 -> this.heroAbilityManager.spendHeroAvailablePoints();
            case 2 -> this.heroAbilityManager.removeHeroAvailablePoints();
            case 3 -> System.out.println("Invalid choice!");
        }

    }


    private void initGame() {
        System.out.println("Welcome to GLADIATOR GAME");
        System.out.println("0. Start new game");
        System.out.println("1. Load game");
        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> System.out.println("Lets go then");
            case 1 -> {
                final LoadedGame loadGame = fileService.loadGame();
                if (loadGame != null) {
                    this.hero = loadGame.getHero();
                    this.currentLevel = loadGame.getLevel();
                    return;
                }
            }
            default -> System.out.println("Invalid choice");
        }

        System.out.println("Enter your name: ");
        final String name = InputUtils.readString();
        this.hero.setName(name);
        System.out.println("Hello " + hero.getName() + ". Let's start the game!");
        PrintUtils.printDivider();
        System.out.println("Your abilities: ");
        PrintUtils.printAbilities(hero);
        PrintUtils.printDivider();
        this.heroAbilityManager.spendHeroAvailablePoints();
    }
}
