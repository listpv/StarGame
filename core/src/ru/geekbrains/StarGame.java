package ru.geekbrains;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}

//	float point;
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0 , Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
//		batch.draw(img, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		batch.draw(img, point, point, 100, 100);
//		batch.end();
//		point += 1;
//	}