import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean appRunning = true;

        while (appRunning) {
            // 1. Rysujemy ładne logo i menu
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("==========================");
            System.out.println("     JAVA CONSOLE RPG     ");
            System.out.println("==========================");
            System.out.println("1. Nowa Gra");
            System.out.println("2. O Autorze");
            System.out.println("3. Wyjście");
            System.out.print("\nWybierz opcję: ");

            // 2. Pobieramy wybór
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    // Startujemy grę!
                    Game game = new Game();
                    game.start();
                    // Jak gra się skończy (game.start wróci), wracamy do menu
                    break;

                case "2":
                    System.out.println("\nGra stworzona podczas nauki Javy.");
                    System.out.println("Wersja 2.0 - Czysta Architektura");
                    System.out.println("\nNaciśnij cokolwiek + Enter, aby wrócić...");
                    scanner.next();
                    break;

                case "3":
                    System.out.println("Do zobaczenia!");
                    appRunning = false;
                    break;

                default:
                    System.out.println("Nie ma takiej opcji.");
            }
        }
        scanner.close();
    }
}