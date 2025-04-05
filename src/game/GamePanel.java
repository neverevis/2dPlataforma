package game;

import entities.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel {

    //configurações do jogo
    public double FPS = 60.0;
    private int originalTileSize = 32;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;
    public int maxCols = 16;
    public int maxRows = 9;
    public int screenWidth = tileSize* maxCols;
    public int screenHeigth = tileSize* maxRows;

    public int realFps;

    //entidades
    public Player player = new Player(this);

    //background
    BufferedImage img;
    int imgX = 0;

    //keyhandler
    public InputManager input = new InputManager();

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeigth));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(input);
        Thread gameLoop = new Thread(new GameLoop(this));
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/fundo_placeholder.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; //fazendo casting de Graphics para Graphics2D para usar métodos mais avançados dessa classe

        g2d.drawImage(img,imgX,0,screenWidth,screenHeigth,null);
        g2d.drawImage(img,(int)imgX+screenWidth,0,screenWidth,screenHeigth,null);

        /*g2d.setColor(Color.darkGray);
        for(int i = 0; i < maxRows; i++) {
            g2d.drawLine(0,i*tileSize,screenWidth,i*tileSize);
        }
        for(int i = 0; i < maxCols; i++) {
            g2d.drawLine(i*tileSize,0,i*tileSize,screenHeigth);
        }*/
        player.render(g2d);
        g2d.setFont(new Font("Arial",Font.BOLD,30));
        g2d.setColor(Color.white);
        g2d.drawString("FPS:" + realFps,10,40);
        g2d.drawString("Speed:" + (int)player.realVelocity,10,80);

        g2d.fillRect(0,tileSize*8,screenWidth,tileSize);
    }

    public void getFrames(int realFps)
    {
        this.realFps = realFps;
    }
}
