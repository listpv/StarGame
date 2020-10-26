package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class MainShip extends Sprite {

    private Vector2 v;
    private Rect worldBounds;

//    public MainShip(TextureAtlas atlas) {
//        super(atlas.findRegion("main_ship"));
//
//    }

    public MainShip(TextureAtlas atlas) {
        super( new TextureRegion(atlas.findRegion("main_ship"),
                0, 0,
            atlas.findRegion("main_ship").getRegionWidth() / 2,
                atlas.findRegion("main_ship").getRegionHeight()));
        v = new Vector2(0.01f, 0);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.2f);
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public boolean keyDown(int keycode){
        if(Input.Keys.DPAD_LEFT == keycode || Input.Keys.A == keycode) {
            if(getLeft() > worldBounds.getLeft()) {
                pos.sub(v);
            }
        }
        if(Input.Keys.DPAD_RIGHT == keycode || Input.Keys.D == keycode){
            if(getRight() < worldBounds.getRight()){
                pos.add(v);
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if(touch.x < 0 && getLeft() > worldBounds.getLeft()) {
            pos.sub(v);
        }else if(touch.x > 0 && getRight() < worldBounds.getRight()){
            pos.add(v);
        }
        return false;
    }
}
