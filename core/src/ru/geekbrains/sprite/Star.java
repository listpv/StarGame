package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class Star extends Sprite {

    protected  final Vector2 v;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.3f, -0.1f));
//        v = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.3f * getHeight(), -0.1f));
        setHeightProportion(Rnd.nextFloat(0.005f, 0.015f));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
//        if(getRight() < worldBounds.getLeft()) {
//            setLeft(worldBounds.getRight());
//        }
//        if(getLeft() > worldBounds.getRight()) {
//            setRight(worldBounds.getLeft());
//        }
//        if(getTop() < worldBounds.getBottom()) {
//            setBottom(worldBounds.getTop());
//        }
//        if(getBottom() > worldBounds.getTop()) {
//            setTop(worldBounds.getBottom());
//        }
    }

    protected void checkBounds() {
//        if (getRight() < worldBounds.getLeft()) {
//            setLeft(worldBounds.getRight());
        if(getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if(getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if(getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if(getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
        }

}
