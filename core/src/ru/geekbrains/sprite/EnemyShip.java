package ru.geekbrains.sprite;

import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.base.EnemySettingsDto;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class EnemyShip extends Ship {

    private Vector2 tempV;                //  переменная для изменения скорости.

    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
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
        }

        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(EnemySettingsDto settings) {
        this.regions = settings.getRegions();
        tempV.set(settings.getV0());                   //     изменение скорости.
        this.v0.set(0f, -1f);                          //
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
    }
}
