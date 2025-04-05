package entities;

import game.GamePanel;

public class Player extends Entity{

    int ground = gp.tileSize*8;
    double gravity = gp.tileSize*32;
    double velocityY = 0;
    double RvelocityX = 0;
    double LvelocityX = 0;
    public double realVelocity;
    boolean onGround = false;
    double jumpForce = -(gp.tileSize*16);
    double acceleration = gp.tileSize/6;

    public Player(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void setDefault() {
        this.x = 0;
        this.y = 0;
        this.speed = gp.tileSize*13;

    }

    public void update(double deltaTime){

        if(gp.input.upKey && onGround){
            velocityY = jumpForce;
            onGround = false;
            }
        /*if(gp.input.downKey)
            y += speed*deltaTime;*/
        if(gp.input.leftKey) {
            if(LvelocityX < speed*deltaTime)
                LvelocityX += acceleration * deltaTime;
        }else{
            if(LvelocityX > 0)
                LvelocityX -= acceleration*2 * deltaTime;
            else
                LvelocityX = 0;
        }


        if(gp.input.rightKey) {
            if(RvelocityX < speed*deltaTime)
                RvelocityX += acceleration * deltaTime;
        }else{
            if(RvelocityX > 0)
                RvelocityX -= acceleration*2 * deltaTime;
            else
                RvelocityX = 0;
        }

        //gravidade
        velocityY += gravity*deltaTime;
        y += velocityY*deltaTime;

        //andar
        x += RvelocityX;
        x -= LvelocityX;

        if(onGround) {
            velocityY = 0;
        }

        //mudar estado de onGround
        if(y+ gp.tileSize >= ground) {
            onGround = true;
            y = ground-gp.tileSize;
        }
        else
            onGround = false;

        realVelocity = (LvelocityX  - RvelocityX);
    }
}
