package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 pos;
    private Vector2 destPos;  //  точка клика мышью.
    private Vector2 v;
    private Vector2 tempV;    //  вспомогательная переменная для обработки keyDown.
    private float distance;   //  расстояние между клика мышью и картинкой.

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(100, 100);
        destPos = pos.cpy();
        v = new Vector2(0, 0);
        tempV = v.cpy();
        distance = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if(distance <= v.len())
        {
            v.setZero();
        }
        distance -= v.len();
        pos.add(v);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        destPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = destPos.cpy().sub(pos);
        distance = v.len();
        v.scl(0.01f);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Input.Keys.SPACE != keycode) {
            return super.keyDown(keycode);
        }
        if(v.isZero()){
            v = tempV.cpy();
        }else {
            tempV = v.cpy();
            v.setZero();
        }
        return super.keyDown(keycode);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
