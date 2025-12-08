import java.util.Scanner;

public class Game {
    // Obiekty naszej gry
    private World world;
    private Player player;
    private Enemy enemy;
    private CombatSystem combatSystem;

    // Narzędzia
    private Scanner scanner;
    private boolean isRunning;

    // Konstruktor - tu przegotowujemy gre (resetujemy stan)
    public Game() {
        world = new World(12,8);
        player = new Player("Bohater", 2, 2, 100);
        enemy = new Enemy("Goblin", 6, 4, 50, 100);
        combatSystem = new CombatSystem();

        scanner = new Scanner(System.in);
        isRunning = true;
    }

    // Główna pętla gry (Game loop)
    public void start() {
        System.out.println("Witaj w RPG v2.0!");

        while (isRunning) {
            // 1. Rysuj
            // Opcjonalnie wyczyść ekran (możesz dodac tu kod ANSI jeśli chcesz)
            world.print(player, enemy);
            System.out.println(player.getStats());

            // 2. Pobierz ruch
            System.out.print("Ruch (WSAD) lub Q(WYJŚCIE): ");
            char input  = scanner.next().toLowerCase().charAt(0);

            // 3. Aktualizuj (Logika)
            handleInput(input);
        }
        System.out.print("Koniec gry");
    }

    private void handleInput(char cmd) {
        int dx = 0;
        int dy = 0;

        switch (cmd) {
            case 'w': dy = -1; break;
            case 's': dy = 1; break;
            case 'a': dx = -1; break;
            case 'd': dx = 1; break;
            case 'q': isRunning = false; return;
            default:
                System.out.println("Nieznana komenda");;
        }
        tryMove(dx, dy);
    }

    private void tryMove(int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        // Pytamy Świat: "Co jest na polu, na które chcę wejść?"
        char tile = world.getTile(newX, newY);

        if (tile == '#') {
            System.out.println("Bum! Sciana.");
        }else if (enemy != null && newX == enemy.getX() && newY == enemy.getY()) {
            // Uruchamiamy walke
            boolean won = combatSystem.startBattle(player, enemy);

            if (won) {
                enemy = null; // USUWAMY WROGRA (znika z mapy)
                // Możemy wejść na pole, na którym stał
            } else {
                // Jeśli zgineliśmy lub uciekliśmy
                if(player.getHp() <= 0) {
                    isRunning = false; // Koniec gry
                }
            }
        } else {
            // Droga wolna - ruszamy się
            player.move(dx, dy);
        }

    }
}