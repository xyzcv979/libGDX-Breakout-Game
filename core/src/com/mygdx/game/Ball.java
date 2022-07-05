package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    private int x, y, size, xSpeed, ySpeed;
    Color color;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        color = Color.WHITE;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;

        // Bouncing off walls
        if(x - size < 0 || x + size > Gdx.graphics.getWidth())
            xSpeed = -xSpeed; // Inverting speed to reverse dir
        if(y - size < 0 || y + size > Gdx.graphics.getHeight())
            ySpeed = -ySpeed;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        if(collidesWith(paddle)) {
            ySpeed = -ySpeed;
        }
    }

    private boolean collidesWith(Paddle paddle) {
        // Colliding with ball

        // paddle x, y, width, height
        int paddleX = paddle.getX();
        int paddleY = paddle.getY();
        int paddleWidth = paddle.getWidth();
        int paddleHeight = paddle.getHeight();

        int currHeight = paddleY + paddleHeight;
        int currWidth = paddleX + paddleWidth;

        // ball x, y, size
        int posX = x + size;
        int negX = x - size;
        int posY = y + size;
        int negY = y - size;

        if((posY <= currHeight &&
                posX <= currWidth &&
                posX >= paddleX &&
                posY >= paddleY)
                || (negY <= currHeight &&
                negX <= currWidth &&
                negX >= paddleX &&
                negY >= paddleY)) {
            return true;
        }
        return false;
    }

    public void checkCollision(Block block) {
        if(collidesWith(block)) {
            ySpeed = -ySpeed;
            block.setDestroyed(true);
        }
    }
    private boolean collidesWith(Block block) {
        // Colliding with block
        int blockX = block.getX();
        int blockY = block.getY();
        int blockWidth = block.getWidth();
        int blockHeight = block.getHeight();

        int currWidth = blockX + blockWidth;
        int currHeight = blockY + blockHeight;

        // ball x, y, size
        int posX = x + size;
        int negX = x - size;
        int posY = y + size;
        int negY = y - size;

        if((posY <= currHeight &&
                posX <= currWidth &&
                posX >= blockX &&
                posY >= blockY)
                || (negY <= currHeight &&
                negX <= currWidth &&
                negX >= blockX &&
                negY >= blockY)) {
            return true;
        }

        return false;
    }

}
