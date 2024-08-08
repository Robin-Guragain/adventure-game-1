package use_case;

import OutsideEntities.Weapons.*;



import javax.imageio.ImageIO;
import java.io.IOException;

public class WeaponChestObject extends AbstractObject{

    Weapon containedWeapon;

    public WeaponChestObject (Weapon w) {
        containedWeapon = w;

        name = "Weapon Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/objects/Chest.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;

    }

    public Weapon getContainedWeapon() {
        return containedWeapon;
    }

    public void setContainedWeapon(Weapon containedWeapon) {
        this.containedWeapon = containedWeapon;
    }
}
