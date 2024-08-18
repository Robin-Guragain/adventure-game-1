package UseCaseInteracter;

import OutsideEntities.Items.Item;
import OutsideEntities.Skills.Skill;
import OutsideEntities.States.State;
import OutsideEntities.Player;
import Presenter.PlayerInfoPresenter;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerInfoInteracter extends EventInteracter{
    /**
     * The use case interacter of Player info page
     */
    private PlayerInfoPresenter presenter;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int index = 0;
    private ArrayList<Skill> skills;
    private ArrayList<State> states;

    public PlayerInfoInteracter(Player player, JTextArea mainTextArea, JButton choice1, JButton choice2, JButton choice3,
                                JButton choice4) {// Constructor
        presenter = new PlayerInfoPresenter(mainTextArea, choice1, choice2, choice3, choice4, choice5);
        inventory.addAll(player.getInventory().getItems());
        inventory.add(player.getWeapon());
        skills = (ArrayList) player.getSkills();
        states = (ArrayList) player.getStates();
    }

    public String start(){// The start use case
        presenter.start();
        return "start";
    }

    public String inventory(){// The inventory use case
        index = 0;
        presenter.describe((index + 1) + ". " + inventory.get(index).getDescription());
        return "inventory";
    }

    public String upInv(){// The use case rolling up the inventory
        if (index <= 0){
            return "inventory";
        }
        index -= 1;
        presenter.describe((index + 1) + ". " + inventory.get(index).getDescription());
        return "inventory";
    }

    public String downInv(){// The use case rolling down the inventory
        if (index >= inventory.size() - 1){
            return "inventory";
        }
        index += 1;
        presenter.describe((index + 1) + ". " + inventory.get(index).getDescription());
        return "inventory";
    }

    public String skills(){// The Skills use case
        index = 0;
        presenter.describe((index + 1) + ". " + skills.get(index).getDescription());
        return "skills";
    }

    public String upSkill(){// The use case rolling up the skill set
        if (index <= 0){
            return "skills";
        }
        index -= 1;
        presenter.describe((index + 1) + ". " + skills.get(index).getDescription());
        return "skills";
    }

    public String downSkill(){// The use case rolling down the skill set
        if (index >= skills.size() - 1){
            return "skills";
        }
        index += 1;
        presenter.describe((index + 1) + ". " + skills.get(index).getDescription());
        return "skills";
    }

    public String states(){// The states use case
        if (states.isEmpty()){
            presenter.describe("-");
            return "states";
        }
        index = 0;
        presenter.describe((index + 1) + ". " + states.get(index).getDescription());
        return "states";
    }

    public String upState(){// The use case rolling up the state list
        if (states.isEmpty()){
            presenter.describe("-");
            return "states";
        }
        if (index <= 0){
            return "states";
        }
        index -= 1;
        presenter.describe((index + 1) + ". " + states.get(index).getDescription());
        return "states";
    }

    public String downState(){// The use case rolling down the state list
        if (states.isEmpty()){
            presenter.describe("-");
            return "states";
        }
        if (index >= states.size() - 1){
            return "states";
        }
        index += 1;
        presenter.describe((index + 1) + ". " + states.get(index).getDescription());
        return "states";
    }
}
