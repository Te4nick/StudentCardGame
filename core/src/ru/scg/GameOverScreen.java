package ru.scg;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class GameOverScreen implements Screen{
    StudentCardGame game;
    private Stage gameOverStage;
    private Skin skin;
    private Label mainSceneLabel;
    private Label gameOvr;
    private Label lifeDurationLabel;
    private TextButton returnToMenu;
    public  GameOverScreen(StudentCardGame game){
        this.game = game;
        this.gameOverStage = new Stage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameOverStage);
        this.skin = new Skin(new TextureAtlas("UI/MainSkin.atlas"));
        this.skin.load(Gdx.files.internal("UI/MainSkin.json"));
        gameOverStage.clear();

        initLabels();
        initButtons();
    }

    private void initButtons() {
        returnToMenu = new TextButton("Back to menu", skin, "bigBtn");
        returnToMenu.setPosition(300, 100);
        returnToMenu.setSize(680, 220);
        returnToMenu.addAction(sequence(alpha(0), delay(4f), fadeIn(2.5f, Interpolation.pow2)));
        returnToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenuScreen);
            }
        });
        gameOverStage.addActor(returnToMenu);
    }

    private void initLabels() {

        gameOvr = new Label("Game Over!", skin, "bigInfo");
        gameOvr.setPosition(400, 600);
        gameOvr.setSize(800, 180);
        gameOvr.setWrap(true);
        gameOvr.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(2f, 2f, 2.5f, Interpolation.pow5),
                        moveTo(400, 400, 2f, Interpolation.swing))));

        gameOverStage.addActor(gameOvr);

        lifeDurationLabel = new Label("You Survived "+ PlayerStatus.getDuration()+" Days", skin, "sideInfo");
        lifeDurationLabel.setPosition(475, 420);
        lifeDurationLabel.addAction(sequence(alpha(0), delay(2f), fadeIn(4f, Interpolation.pow2)));
        gameOverStage.addActor(lifeDurationLabel);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameOverStage.act(delta);
        gameOverStage.draw();

        game.batch.begin();


        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameOverStage.dispose();
        skin.dispose();
    }
}