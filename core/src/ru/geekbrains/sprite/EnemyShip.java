package ru.geekbrains.sprite;

import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.base.EnemySettingsDto;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

public class EnemyShip extends Ship {

    private Vector2 tempV;                //  переменная для изменения скорости.

    public static final float BIG_INTERVAL =10000f;   //    для стрельбы.
    protected float reloadIntervalTmp;                //    для стрельбы.



    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        tempV = new Vector2();
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getBottom());

        if(getTop() > worldBounds.getTop()){
            v.set(v0);
        }
        else {
            v.set(tempV);
            reloadInterval = reloadIntervalTmp;
        }

        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(EnemySettingsDto settings) {
        this.regions = settings.getRegions();
        tempV.set(settings.getV0());                   //     изменение скорости.
        this.v0.set(0f, -0.3f);                          //
//        this.v.set(settings.getV0());
        this.bulletRegion = settings.getBulletRegion();
        this.bulletHeight = settings.getBulletHeight();
        this.bulletV.set(settings.getBulletV());
        this.bulletSound = settings.getBulletSound();
        this.damage = settings.getDamage();
        this.reloadInterval = settings.getReloadInterval();
        setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
        this.reloadTimer = reloadInterval;             //    для начала стрельбы при появлении.
        reloadIntervalTmp = reloadInterval;
        reloadInterval = BIG_INTERVAL;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(
                bullet.getRight() < getLeft()
                        || bullet.getLeft() > getRight()
                        || bullet.getBottom() > getTop()
                        || bullet.getTop() < pos.y
        );
    }
}
