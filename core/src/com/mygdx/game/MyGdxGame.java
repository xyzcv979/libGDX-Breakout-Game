/*
Starter libGdx project
reference: https://colourtann.github.io/HelloLibgdx/index.html
 */


package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;


public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shape; // shapeRendered used to draw shapes
	Ball ball;
	Paddle paddle;
	List<Block> blocks;

	@Override
	public void create() { // runs when game starts
		shape = new ShapeRenderer();
		ball = new Ball(100, 100, 15, 5, 5);
		paddle = new Paddle(25, 25, 100, 10);

		int blockWidth = 63;
		int blockHeight = 20;
		blocks = new ArrayList<>();

		for(int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
			for(int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
			}
		}
	}

	@Override
	public void render() { // runs every frame, 60 fps
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // sets screen back to black at start of render to prevent dragging effect
		int cursorPos = Gdx.input.getX(); // returns x pos of cursor in pixels
		shape.begin(ShapeRenderer.ShapeType.Filled);

		paddle.update(cursorPos);
		paddle.draw(shape);

		for(Block block : blocks) {
			block.draw(shape);
			ball.checkCollision(block);
		}

		for(int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			if(block.isDestroyed()) {
				blocks.remove(i);
				i--;
			}
		}
		ball.update();
		ball.draw(shape);
		ball.checkCollision(paddle);
		shape.end();
	}
}




