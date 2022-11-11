package ru.scg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {
    //Constants
    StudentCardGame game;
    private Stage stage;
    private Skin skin;
    private TextButton exitButton;
    private TextButton playButton;
    //Textures
    Texture background;
    Texture logo;
    Image logoImg;
    Image backgroundImg;
    public  MainMenuScreen(StudentCardGame game){
        this.game = game;
        this.stage = new Stage();
    }

    @Override
    public void show() {
        System.out.println("Show Menu");
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin(new TextureAtlas("UI/MainSkin.atlas"));
        this.skin.load(Gdx.files.internal("UI/MainSkin.json"));
        stage.clear();

        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                game.setScreen(game.mainMenuScreen);
            }
        };

        logo = new Texture("Logo.png");
        background = new Texture("MenuBackground.jpg");

        backgroundImg = new Image(background);
        stage.addActor(backgroundImg);

        logoImg = new Image(logo);
        stage.addActor(logoImg);



        logoImg.setBounds(0, 180,500, 450);
        logoImg.addAction(sequence(alpha(0), parallel(moveBy(70, 0, .5f), fadeIn(1f)) /*run(transitionRunnable)*/));
        //logoImg.addAction(run(transitionRunnable));

        initButtons();
    }

    private void initButtons() {
        playButton = new TextButton("Play", skin, "default");
        playButton.setPosition(750,400);
        playButton.setSize(400, 150);
        playButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0,-30, .5f, Interpolation.pow5Out))));
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(game.gameScreen);
            }
        });
        stage.addActor(playButton);

        exitButton = new TextButton("Exit", skin, "default");
        exitButton.setPosition(750, 150);
        exitButton.setSize(400, 150);
        exitButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0,-30, .5f, Interpolation.pow5Out))));
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

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
        background.dispose();
        logo.dispose();
        stage.dispose();
        skin.dispose();
    }
}
