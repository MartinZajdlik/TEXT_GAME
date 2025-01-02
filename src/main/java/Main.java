import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to GLADIATOR GAME");
        System.out.println("Enter your name: ");
        final String name = scanner.nextLine();
        final Hero hero = new Hero(name);
        System.out.println("Hello "+hero.getName()+". Let's start the game!");
    }
}