package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Face;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture fc;
    private Background background;
    private Face face;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures\\bg.png");
        background = new Background(bg);
        fc = new Texture("badlogic.jpg");
        face = new Face(fc);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }


    @Override
    public boolean keyDown(int keycode) {
        face.keyDown(keycode);
        return super.keyDown(keycode);
    }


    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        face.resize(worldBounds);

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        face.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public void dispose()
    {
        fc.dispose();
        bg.dispose();
        super.dispose();
    }

    private void update(float delta)
    {
        face.update(delta);
    }

    private void draw()
    {
        batch.begin();
        background.draw(batch);
        face.draw(batch);
        batch.end();
    }
}
