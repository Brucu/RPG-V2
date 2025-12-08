public class World {
    private char[][] tiles;
    private int width;
    private int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new char[height][width];

        generateArena(); // <--- TO JEST KLUCZOWE! Bez tego mapa jest pusta.
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

    public void print(Player player, Enemy enemy) {
        // Pusta linia odstępu
        System.out.println();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // 1. Rysujemy GRACZA
                if (player.getX() == x && player.getY() == y) {
                    System.out.print("@ "); // Zwykła "małpa" zamiast buźki
                }
                // 2. Rysujemy WROGA (jeśli żyje)
                else if (enemy != null && enemy.getX() == x && enemy.getY() == y) {
                    System.out.print("E "); // Zwykła litera E
                }
                // 3. Rysujemy MAPĘ
                else {
                    char tile = tiles[y][x];
                    // Jeśli masz tam jakieś specjalne znaki, to tutaj je podmieniamy przy wyświetlaniu
                    if (tile == '#') {
                        System.out.print("# "); // Zwykły hasz
                    } else {
                        System.out.print(". "); // Zwykła kropka
                    }
                }
            }
            System.out.println();
        }
    }
}