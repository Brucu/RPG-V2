import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class World {
    private char[][] tiles;
    private int width;
    private int height;

    public World(String fileName) {
        try {
            loadLevel(fileName);
        } catch (IOException e) {
            System.out.println("BŁĄD KRYTYCZNY: Nie znaleziono pliku mapy" + fileName);
            System.out.println("Generuje mapę awaryjną...");
            this.width = 10;
            this.height = 10;
            this.tiles = new char[width][height];
            generateArena(); // awaryjny kwadrat
        }
    }

    private void loadLevel(String fileName) throws IOException {
        // Czytamy wszystkie linie z pliku do listy
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        this.height  = lines.size();
        this.width  = lines.get(0).length();
        this.tiles = new char[height][width];

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    tiles[y][x] = line.charAt(x);
                } else {
                    tiles[y][x] = '.';
                }
            }
        }

    }

    private void generateArena() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    tiles[y][x] = '#';
                } else {
                    tiles[y][x] = '.';
                }
            }
        }
    }

    public char getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return '#';
        }
        return tiles[y][x];
    }

    public void print(Player player, List<Enemy> enemies) {
        // Pusta linia odstępu
        System.out.println();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // 1. Rysujemy GRACZA
                if (player.getX() == x && player.getY() == y) {
                    System.out.print("@ "); // Zwykła "małpa" zamiast buźki
                }
                // 2. Rysujemy WROGA (jeśli żyje)
                else if (isEnemyAt(x, y, enemies)) {
                    System.out.print("E "); // Zwykła litera E
                }
                // 3. Rysujemy MAPĘ
                else {
                    char tile = tiles[y][x];
                    // Jeśli masz tam jakieś specjalne znaki, to tutaj je podmieniamy przy wyświetlaniu
                    if (tile == '#') {
                        System.out.print("# ");
                    }
                    else if (tile == '$') {
                        System.out.print("$ ");
                    }
                    else if (tile == '+') {
                        System.out.print("+ ");
                    }
                    else {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void removeItem(int x, int y) {
        tiles[y][x] = '.';
    }

    private boolean isEnemyAt(int x, int y, List<Enemy> enemies) {
        for (Enemy e: enemies) {
            if (e.getX() == x && e.getY() == y) {
                return true;
            }
        }
        return false;
    }
}