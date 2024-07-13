package entities;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.function.Predicate.not;

public class Map{
    /**
     * A Map is a hashmap of rooms as nodes of a graph and the values will
     * be the room to go to from one room. The graph will
     * be bidirectional. The map indicates either daytime or
     *     nighttime <day>.
     */

    //public static HashMap<Room, HashMap<String, Room>> world_map = new HashMap<>();
    private boolean day;
    private Room startRoom;
    private Room playerRoom;
    private JButton c1, c2, c3, c4;
    private JTextArea mainTextArea;
    private Container con;
    private Player player;
    private JPanel choiceButtonPanel;
    MapHandler mapHandler  = new MapHandler();
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    String info;

    public Map(Container con, Room startRoom,Player player){
        JPanel mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);

        con.add(mainTextPanel);
        mainTextArea = new JTextArea("Fix this bug!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        con.add(choiceButtonPanel);
        JButton c1 = new JButton("Choice 1");
        c1.setBackground(Color.black);
        c1.setForeground(Color.white);
        c1.setFont(normalFont);
        c1.setFocusPainted(false);
        c1.addActionListener(mapHandler);
        c1.setActionCommand("c1");
        choiceButtonPanel.add(c1);
        JButton c2 = new JButton("Choice 2");
        c2.setBackground(Color.black);
        c2.setForeground(Color.white);
        c2.setFont(normalFont);
        c2.setFocusPainted(false);
        c2.addActionListener(mapHandler);
        c2.setActionCommand("c2");
        choiceButtonPanel.add(c2);
        JButton c3 = new JButton("Choice 3");
        c3.setBackground(Color.black);
        c3.setForeground(Color.white);
        c3.setFont(normalFont);
        c3.setFocusPainted(false);
        c3.addActionListener(mapHandler);
        c3.setActionCommand("c3");
        choiceButtonPanel.add(c3);
        JButton c4 = new JButton("Choice 4");
        c4.setBackground(Color.black);
        c4.setForeground(Color.white);
        c4.setFont(normalFont);
        c4.setFocusPainted(false);
        c4.addActionListener(mapHandler);
        c4.setActionCommand("c4");
        choiceButtonPanel.add(c4);
        this.c1 = c1;this.c2 = c2;this.c3 = c3;this.c4 = c4;
        this.day = true;
        this.con = con;
        this.startRoom = startRoom;
        this.playerRoom = startRoom;
        this.player = player;
        c1.addActionListener(mapHandler);
        c2.addActionListener(mapHandler);
        c3.addActionListener(mapHandler);
        c4.addActionListener(mapHandler);
        player.add_map(this);
    }

    public boolean getDay () {return day;}

    public void changeDay () {this.day = !this.day;}

    public void displayMap () {
        mainTextArea.setVisible(true);choiceButtonPanel.setVisible(true);
        choose();
    }

    private void choose(){
        info = "";
        if (playerRoom.getN() == null){info += "North: Dead End\n";}
        else {info += "North: "+playerRoom.getN().getName()+"\n";}
        if (playerRoom.getS() == null){info += "South: Dead End\n";}
        else {info += "South: "+playerRoom.getS().getName()+"\n";}
        if (playerRoom.getW() == null){info += "West: Dead End\n";}
        else {info += "West: "+playerRoom.getW().getName()+"\n";}
        if (playerRoom.getE() == null){info += "East: Dead End";}
        else {info += "East: "+playerRoom.getE().getName();}
        mainTextArea.setText(info);
        c1.setText("-"); if (playerRoom.getN() != null){c1.setText("Go North");}
        c2.setText("-"); if (playerRoom.getS() != null){c2.setText("Go South");}
        c3.setText("-"); if (playerRoom.getW() != null){c3.setText("Go West");}
        c4.setText("-"); if (playerRoom.getE() != null){c4.setText("Go East");}
    }

    public class MapHandler implements ActionListener{
        /*This handles the action of moving around the map*/
        public void actionPerformed(ActionEvent event){

            String yourChoice = event.getActionCommand();
            switch (yourChoice){
                case "c1":
                    if (Objects.equals(c1.getText(), "-")){break;}
                    else{ playerRoom = playerRoom.getN();
                        mainTextArea.setVisible(false);choiceButtonPanel.setVisible(false);
                        playerRoom.run_room();break;}
                case "c2":
                    if (Objects.equals(c2.getText(), "-")){break;}
                    else{ playerRoom = playerRoom.getS();
                        mainTextArea.setVisible(false);choiceButtonPanel.setVisible(false);
                        playerRoom.run_room();break;}
                case "c3":
                    if (Objects.equals(c3.getText(), "-")){break;}
                    else{ playerRoom = playerRoom.getW();
                        mainTextArea.setVisible(false);choiceButtonPanel.setVisible(false);
                        playerRoom.run_room();break;}
                case "c4":
                    if (Objects.equals(c4.getText(), "-")){break;}
                    else{ playerRoom = playerRoom.getE();
                        mainTextArea.setVisible(false);choiceButtonPanel.setVisible(false);
                        playerRoom.run_room();break;}
            }
        }
    }

}



class Map0 extends Map{

    private JTextArea mainTextArea;
    private JButton c1; JButton c2; JButton c3; JButton c4;
    private Player player;
    private Container con;
    Room startRoom, playerRoom, boss, desert, shop, forest, forestmiddle, forestleft, forestright, hallway;

    public Map0(Container con, Player player) {
        Room Boss = new Room("Boss", "This is the boss room", new Battle_Event0(player,con), player, con);
        Room hallway = new Room("Hallway", "It's a long path", new Shop_Event0(player,con),
                 player,con);
        Room startRoom = new Room("Start", "We started here", new Shop_Event0(player, con), player, con);
        Room shop = new Room("Shop", "There's a business man called Frank.", new Shop_Event0(player, con), player,con);
        Room desert = new Room("Desert", "Desert", new Battle_Event0(player,con), player,con);
        Room forest = new Room("Forest", "You are surrounded by trees.", new Shop_Event0(player,con), player, con);
        Room forestmiddle = new Room("Forest Centre", "It's hard to find the way out.", new Shop_Event0(
                player, con), player, con);
        Room forestleft = new Room("Forest Left", "Dead end.", new Battle_Event0(player,con), player,con);
        Room forestright = new Room("Forest Right", "Dead end", new Shop_Event0(player, con), player,con);
        hallway.setN(Boss); hallway.setS(startRoom);
        startRoom.setW(shop);startRoom.setE(desert);
        forest.setN(startRoom); forest.setS(forestmiddle);
        forestmiddle.setW(forestleft); forestmiddle.setE(forestright);

        super(con, startRoom, player);
    }

}

