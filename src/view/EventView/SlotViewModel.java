package view.EventView;

public class SlotViewModel extends EventViewModel {

    public SlotViewModel() {// Constructor
        super();
        getChoice1().setActionCommand("c1");
        getChoice2().setActionCommand("c2");
        getChoice3().setActionCommand("c3");
        getChoice4().setActionCommand("c4");
    }
}
