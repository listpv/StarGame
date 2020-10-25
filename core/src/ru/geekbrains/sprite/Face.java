package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Face extends Sprite {

    private Vector2 touch;
    private Vector2 v;
    private Vector2 tempPos;    // вектор для временного хранения touch, дабы не делать cpy() в render.
    private Vector2 tempV;    //  вспомогательная переменная для обработки keyDown.

    public static final float V_LEN = 0.01f;

    public Face(Texture region)
    {
        super(new TextureRegion(region));
        touch = new Vector2();
        v = new Vector2();
        tempPos = new Vector2();
        tempV = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {
        tempPos.set(touch);
        if(tempPos.sub(pos).len() <= V_LEN)
        {
            pos.set(touch);
        }
        else
            {
                pos.add(v);
            }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.sub(pos).setLength(V_LEN));
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Input.Keys.SPACE != keycode) {
            return super.keyDown(keycode);
        }
        if(v.isZero()){
            v.set(tempV).cpy();
        }else {
            tempV.set(v).cpy();
            v.setZero();
        }
        return super.keyDown(keycode);
    }
}

