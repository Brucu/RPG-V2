public class Enemy extends Entity {

    // Unikalne pola tylko dla wroga
    private int expReward;

    public Enemy(String name, int x, int y, int hp, int expReward) {
        super(name, x, y, hp); // Konstruktor matki
        this.expReward = expReward; // Ustawienie pola unikalnego
    }

    @Override
    public void onDeath() {
        System.out.println(getName() + " nie żyje! Zdobywasz " + expReward + " XP");
    }

    public int getExpReward() {
        return expReward;
    }
}