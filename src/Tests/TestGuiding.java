package Tests;

import OutsideEntities.Player;
import controller.EventController.GuidingEvent;

public class TestGuiding {
    public static void main(String[] args) {
        Player player = new Player("Vergil", 30);
        GuidingEvent event = new GuidingEvent(player);
        event.run_event();
    }
}
