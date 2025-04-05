package entities;

import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public double x;
    public double y;
    public double speed;
    private BufferedImage spritesheet;

    public GamePanel gp;
    private Rectangle collision;

    public Entity(GamePanel gp){
        this.gp = gp;
        setDefault();
    }

    public abstract void setDefault(); //definir coordenadas, tamanho e spritesheet

    public abstract void update(double deltaTime);

    public void render(Graphics2D g){
        g.setColor(Color.BLUE);
        g.fillOval((int)x,(int)y,gp.tileSize,gp.tileSize); //desenha o player
    }

}
