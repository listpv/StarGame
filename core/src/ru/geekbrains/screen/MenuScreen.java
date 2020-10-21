package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    public static final float V_LEN = 0.5f;

    private Texture img;
    private Vector2 pos;
    private Vector2 destPos;  //  точка клика мышью.
    private Vector2 v;
    private Vector2 tempV;    //  вспомогательная переменная для обработки keyDown.
//    private float distance;   //  расстояние между клика мышью и картинкой.               1-ый способ
    private Vector2 tempPos;    // вектор для временного хранения destPos, дабы не делать cpy() в render.   2-ой способ

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        destPos = new Vector2();
        v = new Vector2();
        tempPos = new Vector2();
        tempV = v.cpy();
//        distance = 0;            // 1-ый способ.
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();

//      1-ый способ. мой
//        if(distance <= v.len())
//        {
//            v.setZero();
//        }
//        distance -= v.len();
//        pos.add(v);

//      2-ой способ.
        tempPos.set(destPos);
        if(tempPos.sub(pos).len() <= v.len())
        {
            pos.set(destPos);
        }else
            {
                pos.add(v);
            }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        destPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = destPos.cpy().sub(pos);
//        distance = v.len();      //  1-ый способ.
        v.setLength(V_LEN);        //  скорость не меняется.
//        v.scl(0.01f);           //  скорость меняется.
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
