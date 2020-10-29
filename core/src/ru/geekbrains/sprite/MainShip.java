package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class MainShip extends Sprite {

    public static final float SHIP_HEIGHT = 0.15f;
    private static final float MARGIN = 0.05f;

    private static final int INVALID_POINTER = -1;

    private Rect worldBounds;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;

    private final Vector2 v = new Vector2();
    private final Vector2 v0 = new Vector2(0.5f, 0);
    private final Vector2 bulletV = new Vector2(0, 0.5f);
    private final Vector2 bulletPos = new Vector2();


    private boolean pressedLeft;
    private boolean pressedRight;

    private boolean shootPosition;   // переменная для постоянной стрельбы.

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    Sound sound;                     // звук выстрела.

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        shootPosition = false;
    }

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, Sound sound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.sound = sound;
        shootPosition = false;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SHIP_HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        // обработка  стрельбы.
        if(shootPosition) {
            shoot();
            sound.play(0.2f);
        }
        ///
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        } else if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
//        2-й пример ограничений
//        if (getLeft() > worldBounds.getRight()) {
//            setRight(worldBounds.getLeft());
//        } else if (getRight() < worldBounds.getLeft()) {
//            setLeft(worldBounds.getRight());
//        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x <  worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                // обработка  стрельбы.
                if(shootPosition){
                    shootPosition = false;
                }
                else {
                    shootPosition = true;
                }
//                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(pos.x, getTop());
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, 1, 0.01f);
    }


    //    private Vector2 v;
//    private Rect worldBounds;
//
//    public MainShip(TextureAtlas atlas) {
//        super( new TextureRegion(atlas.findRegion("main_ship"),
//                0, 0,
//            atlas.findRegion("main_ship").getRegionWidth() / 2,
//                atlas.findRegion("main_ship").getRegionHeight()));
//        v = new Vector2(0.01f, 0);
//    }
//
//    @Override
//    public void resize(Rect worldBounds) {
//        this.worldBounds = worldBounds;
//        setHeightProportion(0.2f);
//        setBottom(worldBounds.getBottom() + 0.02f);
//    }
//
//    @Override
//    public boolean keyDown(int keycode){
//        if(Input.Keys.DPAD_LEFT == keycode || Input.Keys.A == keycode) {
//            if(getLeft() > worldBounds.getLeft()) {
//                pos.sub(v);
//            }
//        }
//        if(Input.Keys.DPAD_RIGHT == keycode || Input.Keys.D == keycode){
//            if(getRight() < worldBounds.getRight()){
//                pos.add(v);
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(Vector2 touch, int pointer, int button) {
//        if(touch.x < 0 && getLeft() > worldBounds.getLeft()) {
//            pos.sub(v);
//        }else if(touch.x > 0 && getRight() < worldBounds.getRight()){
//            pos.add(v);
//        }
//        return false;
//    }
}
