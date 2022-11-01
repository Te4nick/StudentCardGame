package ru.scg;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.awt.event.MouseListener;

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
    private Label leftAnswer;
    private Label rightAnswer;
    private Label mental;
    private Label study;
    private Label health;
    private Label money;
    private Texture mask;
    private Texture cardTextue;
    private Image cardImage;
    private Image maskImg;
    private Button left;
    private Button right;
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
        initCard();
        initSpecs();
        initButtons();

        mask = new Texture(Gdx.files.internal("Mask.png"));
        maskImg = new Image(mask);
        maskImg.setPosition(420, 600);
        maskImg.setSize(440, 100);
        stage.addActor(maskImg);
    }

    private void initButtons() {
        left = new Button(skin, "invisible");
        left.setBounds(640, 200, 220, 390);
        left.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Left Choice");
                cardImage.addAction(parallel(moveBy(150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                leftAnswer.addAction(parallel(moveBy(150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                PlayerStatus.update(card.getStatsL());
                String nc = card.getNextCardL();
                card = AssetManager.getCard(nc);
                updCard();
                initButtons();
            }
        });
        stage.addActor(left);

        right = new Button(skin, "invisible");
        right.setBounds(420, 200, 220, 390);
        right.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Right Choice");
                cardImage.addAction(parallel(moveBy(-150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                rightAnswer.addAction(parallel(moveBy(-150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                PlayerStatus.update(card.getStatsR());
                String nc = card.getNextCardR();
                card = AssetManager.getCard(nc);
                updCard();
                initButtons();
            }
        });
        stage.addActor(right);
    }

    private void updCard() {
        cardTextue =new Texture(Gdx.files.internal(card.getSpritePath()));
        cardImage = new Image(cardTextue);
        cardImage.addAction(alpha(0));
        movedStatus = 0;
        cardImage.addAction(sequence(delay(.5f), alpha(1, .5f, Interpolation.pow2)));
        cardImage.setSize(440, 390);
        cardImage.setPosition(420, 200);
        stage.addActor(cardImage);

        leftAnswer = new Label(card.getLineL(), skin, "LRInfo");
        leftAnswer.addAction(alpha(0));
        leftAnswer.setSize(420, 50);
        leftAnswer.setPosition(430, 530);
        leftAnswer.setWrap(true);
        leftAnswer.addAction(alpha(0));
        movedStatus = 0;
        stage.addActor(leftAnswer);

        rightAnswer = new Label(card.getLineR(), skin, "LRInfo");
        rightAnswer.addAction(alpha(0));
        rightAnswer.setSize(420, 50);
        rightAnswer.setPosition(430, 450);
        rightAnswer.setWrap(true);
        rightAnswer.addAction(alpha(0));
        stage.addActor(rightAnswer);
    }
    private void initCard() {
        cardTextue =new Texture(Gdx.files.internal(card.getSpritePath()));
        cardImage = new Image(cardTextue);
        cardImage.setSize(440, 390);
        cardImage.setPosition(420, 200);
        stage.addActor(cardImage);

        leftAnswer = new Label(card.getLineL(), skin, "LRInfo");
        leftAnswer.setSize(420, 50);
        leftAnswer.setPosition(430, 530);
        leftAnswer.setWrap(true);
        leftAnswer.addAction(alpha(0));
        stage.addActor(leftAnswer);

        rightAnswer = new Label(card.getLineR(), skin, "LRInfo");
        rightAnswer.setSize(420, 50);
        rightAnswer.setPosition(430, 450);
        rightAnswer.setWrap(true);
        rightAnswer.addAction(alpha(0));
        stage.addActor(rightAnswer);
    }

    private void initSpecs() {
        mental = new Label("", skin, "spec");
        mental.setPosition(430, 600);
        mental.setSize(90, 15+75);
        stage.addActor(mental);

        study = new Label("", skin, "spec");
        study.setPosition(520, 600);
        study.setSize(130, 15+80);
        stage.addActor(study);

        health = new Label("", skin, "spec");
        health.setPosition(650, 600);
        health.setSize(100, 15+80);
        stage.addActor(health);

        money = new Label("", skin, "spec");
        money.setPosition(750, 600);
        money.setSize(100, 15+80);
        stage.addActor(money);
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

        batch.begin();
        // TODO: add Stacks w labels and card image
        if (Gdx.input.getX() > 400 && Gdx.input.getX() < 600 && movedStatus != 2 && Gdx.input.getY() > 120 && Gdx.input.getY() < 520)
        {
            cardImage.addAction(parallel(moveBy(-70, 0, .5f, Interpolation.pow2), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
            rightAnswer.addAction(parallel(moveBy(-90, 80, .5f, Interpolation.pow2), alpha(1, .5f)));
            movedStatus = 2;
        } else if (Gdx.input.getX() < 880 && Gdx.input.getX() > 680 && movedStatus != 1 && Gdx.input.getY() > 120 && Gdx.input.getY() < 520) {
            cardImage.addAction(parallel(moveBy(70, 0, .5f, Interpolation.pow2), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
            leftAnswer.addAction(parallel(moveBy(90, -60, .5f, Interpolation.pow2), alpha(1, .5f)));
            movedStatus = 1;
        } else if ((Gdx.input.getX() < 400 || Gdx.input.getX() > 880)||(Gdx.input.getX() > 600 && Gdx.input.getX() < 680)) {// FIXME: collision bug
            if (movedStatus == 2)
            {
                cardImage.addAction(parallel(moveBy(70, 0, .5f), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
                rightAnswer.addAction(parallel(moveBy(90, -80, .5f, Interpolation.pow2), alpha(0, .5f)));
                movedStatus = 0;
            } else if (movedStatus == 1) {
                cardImage.addAction(parallel(moveBy(-70, 0, .5f), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
                leftAnswer.addAction(parallel(moveBy(-90, 60, .5f, Interpolation.pow2), alpha(0, .5f)));
                movedStatus = 0;
            }
        }

        batch.end();
        updateLabels(delta);
    }

    private void updateLabels(float delta) {
        cardText.setText(card.getLineM());
        additionalInfo.setText(card.getCharacter());
        mental.setSize(90, 15+(int)(75*(PlayerStatus.getMental() / 100.0)));
        study.setSize(130, 15+(int)(80*(PlayerStatus.getStudy() / 100.0)));
        health.setSize(100, 15+(int)(80*(PlayerStatus.getHealth() / 100.0)));
        money.setSize(100, 15+(int)(80*(PlayerStatus.getMoney() / 100.0)));
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
