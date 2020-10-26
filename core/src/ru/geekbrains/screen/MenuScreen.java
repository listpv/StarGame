package ru.geekbrains.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ExitButton;
import ru.geekbrains.sprite.Face;
import ru.geekbrains.sprite.PlayButton;
import ru.geekbrains.sprite.Star;

public class MenuScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private Game game;

    private TextureAtlas atlas;
    private Texture bg;
//    private Texture fc;
    private Background background;
    private Star[] stars;
    private ExitButton exitButton;
    private PlayButton playButton;
//    private Face face;


    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\menuAtlas.tpack");
        bg = new Texture("textures\\bg.png");

        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);

//        fc = new Texture("badlogic.jpg");
//        face = new Face(fc);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }


    @Override
    public boolean keyDown(int keycode) {
//        face.keyDown(keycode);
        return super.keyDown(keycode);
    }


    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);

//        face.resize(worldBounds);

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
//        face.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public void dispose()
    {
//        fc.dispose();

        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    private void update(float delta)
    {
        for (Star star : stars) {
            star.update(delta);
        }
//        face.update(delta);
    }

    private void draw()
    {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);
//        face.draw(batch);
        batch.end();
    }
}
