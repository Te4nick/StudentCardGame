package ru.scg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {
    //Constants
    public static final int BUTTON_WIDTH = 150;
    public static final int BUTTON_HEIGHT = 75;
    public static final int EXIT_BUTTON_Y = 100;
    public static final int PLAY_BUTTON_Y = 300;
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
        Gdx.input.setInputProcessor(stage);
        System.out.println("Show Menu");
        this.skin = new Skin();
        skin.add("default-font", game.defaultFont);

        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                game.setScreen(game.mainMenuScreen);
            }
        };

        initButtons();

        logo = new Texture("Logo.png");
        background = new Texture("MenuBackground.jpg");

        backgroundImg = new Image(background);
        stage.addActor(backgroundImg);

        logoImg = new Image(logo);
        stage.addActor(logoImg);

        logoImg.setBounds(0, 100,350, 350);
        logoImg.addAction(sequence(alpha(0), parallel(moveBy(20, 0, 1f), fadeIn(1f))));
        //logoImg.addAction(run(transitionRunnable));
    }

    private void initButtons() {
//        playButton = new TextButton("Играть", new TextButton.TextButtonStyle());
//        exitButton = new TextButton("Выход", skin);
//        playButton.setPosition(500,350);
//        playButton.setSize(100, 30);
//        stage.addActor(playButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        game.batch.begin();

//        game.batch.draw(background, 0, 0, 640, 480);
//        game.batch.draw(logo, 20, 100, 350, 350);
        //game.gameMenuFont.draw(game.batch, "Sample Text", 300, 300);

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
        game.dispose();

    }
}
