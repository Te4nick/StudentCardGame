package ru.scg;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static ru.scg.StudentCardGame.batch;

public class GameScreen implements Screen {
    StudentCardGame game;
    private Stage stage;
    private int movedStatus;
    private Skin skin;
    private Skin skinPB;
    private Label mainScene;
    private Label cardText;
    private Label lifeDuration;
    private Label additionalInfo;
    private ProgressBar mental1;
    private ProgressBar study;
    private ProgressBar health;
    private ProgressBar money;
    private Texture mask;
    private Texture cardTextue;
    private Image cardImage;
    private Image maskImg;
    private Card card;
    public GameScreen(StudentCardGame game){
        this.game = game;
        this.stage = new Stage();
    }
    @Override
    public void show() {
        System.out.println("Show Game");
        movedStatus = 0;
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin(new TextureAtlas("UI/MainSkin.atlas"));
        this.skin.load(Gdx.files.internal("UI/MainSkin.json"));
        stage.clear();

        AssetManager.getCardKeys();
        card = AssetManager.getCard("bf");

        initLabels();
        initSpecs();
        initCard();

        mask = new Texture(Gdx.files.internal("Mask.png"));
        maskImg = new Image(mask);
        maskImg.setPosition(420, 600);
        maskImg.setSize(440, 100);
        stage.addActor(maskImg);
    }

    private void initCard() {
        cardTextue =new Texture(Gdx.files.internal(card.getSpritePath()));
        cardImage = new Image(cardTextue);
        cardImage.setSize(440, 390);
        cardImage.setPosition(420, 200);
        stage.addActor(cardImage);
    }

    private void initSpecs() {
        mental1 = new ProgressBar(0, 100, 1, true, skin, "default-vertical");
        mental1.setPosition(100, 500);
        mental1.setSize(200, 100);
        mental1.setValue(100);
        stage.addActor(mental1);

    }

    private void initLabels() {
        mainScene = new Label("", skin, "default");
        mainScene.setPosition(400, 0);
        mainScene.setSize(480, 720);
        stage.addActor(mainScene);

        cardText = new Label("", skin, "cardText");
        cardText.setPosition(405, 0);
        cardText.setSize(470, 200);
        cardText.setText("SAMPLE CHARACTER TEXT HERE");
        cardText.setAlignment(Align.center);
        cardText.layout();
        cardText.setWrap(true);
        stage.addActor(cardText);


        lifeDuration = new Label("*#WEEKS SURVIVED*", skin, "sideInfo");
        lifeDuration.setPosition(20, 600);
        lifeDuration.setSize(200, 80);
        lifeDuration.setWrap(true);
        stage.addActor(lifeDuration);

        additionalInfo = new Label("*Some Additional Info*", skin, "sideInfo");
        additionalInfo.setPosition(20, 20);
        additionalInfo.setSize(200, 80);
        stage.addActor(additionalInfo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        updateLabels(delta);
        batch.begin();
        // TODO: add Stacks w labels and card image
        if (Gdx.input.getX() > 400 && Gdx.input.getX() < 600 && movedStatus != 2 && Gdx.input.getY() > 120 && Gdx.input.getY() < 520)
        {
            cardImage.addAction(parallel(moveBy(-70, 0, .5f, Interpolation.pow2), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
            movedStatus = 2;
        } else if (Gdx.input.getX() < 880 && Gdx.input.getX() > 680 && movedStatus != 1 && Gdx.input.getY() > 120 && Gdx.input.getY() < 520) {
            cardImage.addAction(parallel(moveBy(70, 0, .5f, Interpolation.pow2), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
            movedStatus = 1;
        } else if ((Gdx.input.getX() < 400 || Gdx.input.getX() > 880)||(Gdx.input.getX() > 600 && Gdx.input.getX() < 680)) {// FIXME: collision bug
            if (movedStatus == 2)
            {
                cardImage.addAction(parallel(moveBy(70, 0, .5f), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
                movedStatus = 0;
            } else if (movedStatus == 1) {
                cardImage.addAction(parallel(moveBy(-70, 0, .5f), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
                movedStatus = 0;
            }
        }
        batch.end();
    }

    private void updateLabels(float delta) {
        cardText.setText(card.getLineM());
        additionalInfo.setText(card.getCharacter());
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
        game.dispose();
        stage.dispose();
        skin.dispose();
    }
}
