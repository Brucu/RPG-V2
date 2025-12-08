import java.util.Scanner;

public class CombatSystem {
    private Scanner scanner = new Scanner(System.in);

    // Zwraca true jeśli wygraliśmy, false jeśli przegraliśmy/uciekliśmy
    public boolean startBattle(Player player, Enemy enemy) {
        System.out.println("\n!!! WALKA ROZPOCZĘTA Z " + enemy.getName() + " !!!");

        boolean fighting = true;
        while (fighting) {
            System.out.println("Ty: " + player.getHp() + " HP | Wróg: " + enemy.getHp() + " HP" );
            System.out.print("Akcja: (A)takuj | (U)ciekaj");

            char choice = scanner.next().toLowerCase().charAt(0);

            if (choice == 'a') {
                // 1. Ty atakujesz
                int damage = 15;
                enemy.setHp(enemy.getHp() - damage);
                System.out.println(">> Uderzasz za " + damage + " obrażeń");

                if (enemy.getHp() <= 0) {
                    System.out.println(">> ZWYCIĘSTWO! Pokonałeś " + enemy.getName());
                    player.gainExp(enemy.getExpReward());
                    try { Thread.sleep(2000); } catch (Exception e) {}
                    return  true;
                }

                int enemyDmg = 10;
                player.setHp(player.getHp() - enemyDmg);
                System.out.println("<< Wróg gryzie cię za " + enemyDmg + " obrażeń");

                if (player.getHp() <= 0) {
                    System.out.println(">> ZGINĄŁEŚ...");
                    return false; // Przegrana
                }
            } else if (choice == 'u') {
                System.out.println("Uciekłeś jak tchórz!");
                return false;
            }
        }
        return false;
    }
}