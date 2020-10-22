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


    public static final float V_LEN = 0.01f;

    private Texture img;
    private Vector2 pos;
    private Vector2 destPos;  //  точка клика мышью.
    private Vector2 v;
    private Vector2 tempV;    //  вспомогательная переменная для обработки keyDown.
    private Vector2 tempPos;    // вектор для временного хранения destPos, дабы не делать cpy() в render.

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures\\bg.png");
        background = new Background(new TextureRegion(bg));
        fc = new Texture("badlogic.jpg");
        face = new Face(new TextureRegion(fc));

        img = new Texture("badlogic.jpg");
        pos = new Vector2(face.getLeft(), face.getBottom());
        destPos = new Vector2();
        v = new Vector2();
        tempPos = new Vector2();
        tempV = v.cpy();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        face.draw(batch);
        batch.end();

        tempPos.set(destPos);
        if(tempPos.sub(pos).len() <= v.len())
        {
            pos.set(destPos);
        }
        else
            {
                pos.add(v);

            }
        face.setLeft(pos.x - face.getHalfWidth());
        face.setBottom(pos.y - face.getHalfHeight());



    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        destPos.set(screenX, Gdx.graphics.getHeight() - screenY);
//        v = destPos.cpy().sub(pos);
//        v.setLength(V_LEN);        //  скорость не меняется.
////        v.scl(0.01f);           //  скорость меняется.
//        return super.touchDown(screenX, screenY, pointer, button);
//    }

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
    public void resize(Rect worldBounds) {
        face.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        destPos.set(touch.x, touch.y);
        v = destPos.cpy().sub(pos);
        v.setLength(V_LEN);        //  скорость не меняется.
//        v.scl(0.01f);            //  скорость меняется.
        return false;
    }

    @Override
    public void dispose()
    {
        fc.dispose();
        bg.dispose();
        super.dispose();
    }
}
