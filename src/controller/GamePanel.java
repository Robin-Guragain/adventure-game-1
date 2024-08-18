package controller;

import OutsideEntities.Monsters.Bat;
import OutsideEntities.Monsters.Goblin;
import OutsideEntities.Player;
import controller.EventController.*;
import data_access.LoadPlayer;
import data_access.SavePlayer;
import entities.*;
import entities.PlayerController;
import Objects.Object;
import view.TileViewManager;
import Presenter.MapPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    //Attributes declare
    final int originalTilesSize = 16;
    final int scale = 3;
    SavePlayer savePlayer;

    //Note the maxScreenCow/Row represents the number of tiles displayed on screen
    public final int tileSize = originalTilesSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxMap = 10;
    public int currentMap = 0;


    int FPS = 60;

    //System Manager
    public TileViewManager tileA = new TileViewManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter setter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    public static LoadPlayer playerLoader = new LoadPlayer();

    //UI
    public MapPresenter ui = new MapPresenter(this);

    //Entity and Object
    //Both object and entity are in an array of max 10 elements
    public PlayerController playerController = new PlayerController(this,keyH);
    public Object[][] obj = new Object[maxMap][10];
    public Entity[][] npc = new Entity[maxMap][10];

    //add if statement to inject saved player
    public static Player player;

    static {
        try {
            player = playerLoader.loadPlayer();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Generate events
    public ShopEvent shop = new ShopEvent(player);
    public BattleEvent bat1 = new BattleEvent(player, new Bat());
    public BattleEvent bat2 = new BattleEvent(player, new Bat());
    public BattleEvent bat3 = new BattleEvent(player, new Bat());
    public BattleEvent goblin = new BattleEvent(player, new Goblin());
    public CursedFlowerEvent cursedFlower = new CursedFlowerEvent(player);
    public CursedTreeEvent cursedTree = new CursedTreeEvent(player);
    public GuidingEvent guide = new GuidingEvent(player);
    public MysteryBoxEvent mystery = new MysteryBoxEvent(player);
    public QueenSlimeEvent slime = new QueenSlimeEvent(player);
    public SlotMachineEvent machine = new SlotMachineEvent(player);
    public PlayerInfo playInfo = new PlayerInfo(player, this);

    //Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    //public final int eventState = 4;
    public final int shopState = 5;
    public final int battleState = 6;
    public final int bossState = 7;
    public final int bat1State = 8;
    public final int bat2State = 9;
    public final int bat3State = 10;
    public final int infoState = 11;



    //Constructor method
    public GamePanel() throws IOException {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame() {
        setter.setObject();
        setter.setNPC();
        player.setMoney(999999999);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    //update each element of the list of entities(npc) as well as the player entity
    public void update() {

        if(gameState == playState) {
            playerController.update();
        }

        if(gameState == pauseState) {
            //nothing
        }

        if(gameState == playState && !goblin.fighting && !bat1.fighting && !bat2.fighting && !bat3.fighting && !shop.shopping) {
            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
        }
            }
        }

        if(gameState == shopState) {
            //pause game
            //set J panel to ve visible
            //call eventHandler

        }

        if(gameState == battleState) {
            //nothing
        }


    }

    //Draw each component of the game in the order of tile > object > player > NPC
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == titleState) {
            ui.draw(g2);

        }
        else {
            tileA.draw(g2);

            //Draw each object in the array
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2, this);
                }
            }


            //Draw each npc in the array
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);

                }
            }

            //Draw player
            playerController.draw(g2);

            //UI
            ui.draw(g2);

        }

        g2.dispose();

    }
}
