package Main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    final int originaltileSize = 16; // 16 x 16
    final int scale = 3;

    public final int tileSize = originaltileSize * scale;  // 48x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    // world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    //System settings
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound(); // calling sound efects
    public CollisionChecker cChcker = new CollisionChecker(this); // colllision chekcer
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; // keeps game running


    //Entity and Objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; // display 10 objects at the same time


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame() {
        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // passing game panel
        gameThread.start(); // calls the run method

    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // around a tenth of a second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update(); // updates info
                repaint(); // draw screen with new info
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                //System.out.print("FPS = " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2); //  background

        for (int i = 0; i < obj.length; i++) { // objects
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2); // player

        ui.draw(g2);

        g2.dispose(); // saves memory

    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) { // sound effect
        se.setFile(i);
        se.play();
    }


}
