package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float point;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		point = 0f;
//		Vector2 v1 = new Vector2(5, 8);
//		Vector2 v2 = new Vector2(2, 4);
//		Vector2 v3 = v1.cpy().add(v2);
//		System.out.println("vec: " + v3.x + "; " + v3.y);
//		v3.add(1, 1);
//		System.out.println("vec: " + v3.x + "; " + v3.y);
//		Vector2 v1 = new Vector2(1, 1);
//		Vector2 v2 = new Vector2(3, 1);
//		System.out.println("dist: " + v2.cpy().sub(v1).len());
//		Vector2 v = new Vector2(4, 0);
//		v.nor();
//		System.out.println("x y: " + v.x + " " + v.y);
		Vector2 v1 = new Vector2(1, 1);
		Vector2 v2 = new Vector2(-1, 1);
		v1.nor();
		v2.nor();
		System.out.println(Math.acos(v1.dot(v2)));



	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
//		batch.draw(img, 0, 0 , Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		batch.draw(img, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(img, point, point, 100, 100);
		batch.end();
		point += 1;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
