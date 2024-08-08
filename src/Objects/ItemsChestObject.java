package use_case;

import OutsideEntities.Items.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemsChestObject extends AbstractObject {

    Item containedItem;

    public ItemsChestObject(Item i) {

        name = "Item Chest";
        containedItem = i;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/objects/Chest.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;

    }

    public Item getContainedItem() {
        return containedItem;
    }
    public void setContainedItem(Item i) {
        containedItem = i;
    }
}
