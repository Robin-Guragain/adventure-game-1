package data_access;

import entities.stat_entities.Player;
import entities.stat_entities.Items.Life_Potion;
import entities.stat_entities.Items.PiggyBank;
import entities.stat_entities.Weapons.Flame_Crossbow;
import entities.stat_entities.Weapons.Spear;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

class LoadPlayerTest {
    Player player = new Player("Testing", 1234);
    Player player2;

    @Test
    void load() throws IOException {
        player.setKey(99);
        player.setMoney(888);
        player.setWeapon(new Spear());
        player.getInventory().addItem(new Life_Potion());
        player.getInventory().addItem(new PiggyBank());
        player.getInventory().addItem(new Life_Potion());
        player.getInventory().addItem(new Flame_Crossbow());
        SavePlayer savePlayer = new SavePlayer(player);

        savePlayer.save();

        LoadPlayer loadPlayer = new LoadPlayer();

        player2 = new Player("Champ", 114514);
        loadPlayer.load(player2);

        assert Objects.equals(player2.getName(), "Testing");
        assert player2.getHealth() == 1234;
        assert player2.getKey() == 99;
        assert player2.getMoney() == 888;
        assert Objects.equals(player2.getWeaponName(), "Spear");
        assert Objects.equals(player2.getInventory().getItem(0).getName(), "Life Potion");
        assert Objects.equals(player2.getInventory().getItem(1).getName(), "Piggy Bank");
        assert Objects.equals(player2.getInventory().getItem(2).getName(), "Life Potion");
        assert Objects.equals(player2.getInventory().getItem(3).getName(), "Flame Crossbow");
    }
}