public class Player extends Entity {
    private int level;
    private int exp;
    private int expToNextLevel;

    // Konstruktor
    public Player(String name, int x, int y, int hp) {
        super(name, x, y, hp);
        this.level = 1;
        this.exp = 0;
        this.expToNextLevel = 100;
    }

    public void gainExp(int amount) {
        this.exp += amount;
        System.out.println(">> Zdobyłeś " + amount + " XP!");

        // Sprawdzamy czy awansowaliśmy
        if (this.exp >= this.expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.exp = 0; // Resetujemy pasek
        this.expToNextLevel += 50; // Następny poziom jest trudniejszy

        this.maxHp += 50;
        this.hp = this.maxHp;

        System.out.println("========================");
        System.out.println("!!! LEVEL UP !!!");
        System.out.println("Jesteś teraz na poziomie " + level);
        System.out.println("Twoje Max HP wzrosło do " + maxHp);
        System.out.println("========================");
    }

    @Override
    public void onDeath() {
        System.out.println("GAME OVER! Gracz " + getName() + " zginął");
    }

    public String getStats() {
        return  "Level: " + level + " | XP: " + exp + "/" + expToNextLevel + " | HP: " + hp + "/" + maxHp;
    }
}