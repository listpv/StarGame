package ru.geekbrains.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.BaseButton;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class NewGameButton extends BaseButton {

    private static final float HEIGHT = 0.05f;
    private static final float TOP_MARGIN = -0.012f;

    private GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(HEIGHT);
        setTop(TOP_MARGIN);
        this.gameScreen = gameScreen;
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }

}
