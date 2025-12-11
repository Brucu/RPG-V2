import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private World world;
    private Player player;
    private List<Enemy> enemies; // Lista wrogów

    private Scanner scanner;
    private CombatSystem combatSystem;
    private boolean isRunning;

    public Game() {
        // 1. Inicjalizacja świata i gracza
        world = new World("level.txt");
        player = new Player("Bohater", 1, 1, 100);

        // 2. Inicjalizacja wrogów
        enemies = new ArrayList<>();
        spawnEnemies(5); // Tworzymy 3 wrogów

        // 3. Narzędzia
        scanner = new Scanner(System.in);
        combatSystem = new CombatSystem();
        isRunning = true;
    }

    // Metoda pomocnicza do losowania wrogów
    private void spawnEnemies(int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            // Losujemy pozycję (unikając ścian na krawędziach)
            int ex = rand.nextInt(13) + 1;
            int ey = rand.nextInt(8) + 1;
            enemies.add(new Enemy("Goblin " + (i+1), ex, ey, 40, 10));
        }
    }

    public void start() {
        System.out.println("Witaj w RPG v2.0!");

        while (isRunning) {
            // 1. Rysowanie
            world.print(player, enemies);
            System.out.println(player.getStats());

            // 2. Pobieranie ruchu
            System.out.print("Ruch (WASD) lub Q(wyjście): ");
            // Pobieramy znak i zamieniamy na małą literę
            char input = scanner.next().toLowerCase().charAt(0);

            // 3. Logika
            handleInput(input);
        }
        System.out.println("Koniec gry.");
    }

    private void handleInput(char cmd) {
        int dx = 0;
        int dy = 0;

        switch (cmd) {
            case 'w': dy = -1; break; // Góra
            case 's': dy = 1; break;  // Dół
            case 'a': dx = -1; break; // Lewo
            case 'd': dx = 1; break;  // Prawo
            case 'q': isRunning = false; return;
            default:
                System.out.println("Nieznana komenda! Użyj W, A, S, D.");
                return;
        }

        // Próba wykonania ruchu
        tryMove(dx, dy);
    }

    private void tryMove(int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        // 1. Sprawdzamy co leży na ziemi
        char tile = world.getTile(newX, newY);

        if (tile == '$') {
            System.out.println(">> Znalazłeś Złoto! (+10 XP)");
            player.gainExp(10); // Złoto daje trochę XP (lub stwórz pole 'gold' w Playerze)
            world.removeItem(newX, newY); // Usuwamy $ z mapy
            // Nie robimy return, bo chcemy wejść na to pole!
        } else if (tile == '+') {
            if (player.getHp() < player.getMaxHp()) {
                System.out.println(">> Wypijasz Miksture! (+20 HP)");

                player.setHp(player.getHp() + 20);

                world.removeItem(newX, newY);
            } else {
                System.out.println(">> Masz pełne zdrowie, nie potrzebujesz mikstury");
            }
        }

        if (tile == '#') {
            System.out.println(">> Bum! Ściana.");
            try { Thread.sleep(500); } catch (Exception e) {} // Mała pauza na przeczytanie
            return; // Blokujemy ruch
        }

        // B. Sprawdzamy wrogów (czy wchodzimy na jakiegoś?)
        Enemy target = null;
        for (Enemy e : enemies) {
            if (e.getX() == newX && e.getY() == newY) {
                target = e;
                break;
            }
        }

        if (target != null) {
            // -- WALKA --
            boolean won = combatSystem.startBattle(player, target);
            if (won) {
                enemies.remove(target); // Usuwamy wroga z listy
                if (enemies.isEmpty()) {
                    System.out.println(">>> WYGRAŁEŚ! OCZYŚCIŁEŚ POZIOM! <<<");
                    isRunning = false;
                }
            } else {
                if (player.getHp() <= 0) isRunning = false;
            }
        } else {
            // -- RUCH (To tutaj pewnie brakowało kodu wcześniej) --
            player.move(dx, dy);
        }
    }
}