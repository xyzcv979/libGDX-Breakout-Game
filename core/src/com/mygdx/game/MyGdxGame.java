/*
Starter libGdx project
reference: https://colourtann.github.io/HelloLibgdx/index.html
 */


package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shape; // shapeRendered used to draw shapes
	Ball ball;

	@Override
	public void create() { // runs when game starts
		shape = new ShapeRenderer();
		ball = new Ball(50, 50, 50, 5, 5);
	}

	@Override
	public void render() { // runs every frame, 60 fps
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // sets screen back to black at start of render
		ball.update();
		shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.draw(shape);
		shape.end();
	}
}




