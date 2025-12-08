public abstract class Entity {
    // Pola protected (widoczne dla dzieci, np Playera)
    protected String name;
    protected int x, y;
    protected int hp, maxHp;

    // Konstruktor
    public Entity(String name, int x, int y, int hp) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.maxHp = hp;
    }

    // Metody wspólne dla wszystkich
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public abstract void onDeath();


    // Gettery
    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    // Settery

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
}