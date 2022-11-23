package ru.scg;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static ru.scg.StudentCardGame.batch;

public class GameScreen implements Screen {
    StudentCardGame game;
    private Stage stage;
    private int movedStatus;
    private Skin skin;
    private Label mainScene;
    private Label cardText;
    private Label lifeDuration;
    private Label additionalInfo;
    private Label rightAnswer;
    private Label leftAnswer;
    private Label mental;
    private Label study;
    private Label health;
    private Label money;
    private Texture change;
    private Texture mask;
    private Texture cardTexture;
    private Image cardImage;
    private Image mentalChangeI, studyChangeI, healthChangeI, moneyChangeI;
    private Image maskImg;
    private Button right;
    private Button left;
    private Card card;

    public GameScreen(StudentCardGame game){
        this.game = game;
        this.stage = new Stage();
    }

    private void initButtons() {
        right = new Button(skin, "invisible");
        right.setBounds(640, 200, 220, 390);
        right.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Left Choice");
                cardImage.addAction(parallel(moveBy(150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                rightAnswer.addAction(parallel(moveBy(150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                PlayerStatus.update(card.getStatsR());
                // if(card.getNextCardL().equals("end!")) game.setScreen(game.gameOverScreen); // End Game Check
                updCard(false);
                initButtons();
            }
        });
        stage.addActor(right);

        left = new Button(skin, "invisible");
        left.setBounds(420, 200, 220, 390);
        left.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Right Choice");
                cardImage.addAction(parallel(moveBy(-150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                leftAnswer.addAction(parallel(moveBy(-150, 0, .5f, Interpolation.pow2), alpha(0, .5f, Interpolation.pow2)));
                PlayerStatus.update(card.getStatsL());
                // if(card.getNextCardR().equals("end!")) game.setScreen(game.gameOverScreen); // End Game Check
                updCard(true);
                initButtons();
            }
        });
        stage.addActor(left);
    }

    private void updCard(boolean leftOption) {
        if(AssetManager.isEnding()) {
            game.setScreen(game.gameOverScreen); // End Game Check
            return;
        }
        card = AssetManager.getNextCard(leftOption);
        System.out.println(card.toString());
        cardTexture = new Texture(Gdx.files.internal(card.getSpritePath()));
        PlayerStatus.incrementDuration();
        cardImage.setDrawable(new SpriteDrawable(new Sprite(cardTexture)));
        if (movedStatus == 2) {
            cardImage.addAction(parallel(moveBy(140, 0, .5f), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
            leftAnswer.addAction(parallel(moveBy(90, -80, .5f, Interpolation.pow2), alpha(0, .5f)));
            movedStatus = 0;
        } else if (movedStatus == 1) {
            cardImage.addAction(parallel(moveBy(-140, 0, .5f), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
            rightAnswer.addAction(parallel(moveBy(-90, 60, .5f, Interpolation.pow2), alpha(0, .5f)));
            movedStatus = 0;
        }
        cardImage.addAction(alpha(0));
        cardImage.addAction(sequence(delay(.5f), alpha(1, .5f, Interpolation.pow2)));
        cardImage.setSize(440, 390);
        cardImage.setPosition(420, 200);
        stage.addActor(cardImage);

        lifeDuration.setText(String.format(Localizator.getString("day"), PlayerStatus.getDuration()));

        rightAnswer = new Label(card.getLineR(), skin, "LRInfo");
        rightAnswer.addAction(alpha(0));
        rightAnswer.setSize(420, 50);
        rightAnswer.setPosition(430, 530);
        rightAnswer.setWrap(true);
        rightAnswer.addAction(alpha(0));
        movedStatus = 0;
        stage.addActor(rightAnswer);

        leftAnswer = new Label(card.getLineL(), skin, "LRInfo");
        leftAnswer.addAction(alpha(0));
        leftAnswer.setSize(420, 50);
        leftAnswer.setPosition(430, 450);
        leftAnswer.setWrap(true);
        leftAnswer.addAction(alpha(0));
        stage.addActor(leftAnswer);
    }

    private void initCard() {
        cardTexture =new Texture(Gdx.files.internal(card.getSpritePath()));
        cardImage = new Image(cardTexture);
        cardImage.setSize(440, 390);
        cardImage.setOrigin(220, -500);
        cardImage.setPosition(420, 200);
        stage.addActor(cardImage);

        rightAnswer = new Label(card.getLineR(), skin, "LRInfo");
        rightAnswer.setSize(420, 50);
        rightAnswer.setOrigin(210, -500);
        rightAnswer.setPosition(430, 530);
        rightAnswer.setWrap(true);
        rightAnswer.addAction(alpha(0));
        stage.addActor(rightAnswer);

        leftAnswer = new Label(card.getLineL(), skin, "LRInfo");
        leftAnswer.setSize(420, 50);
        leftAnswer.setOrigin(210, -500);
        leftAnswer.setPosition(430, 450);
        leftAnswer.setWrap(true);
        leftAnswer.addAction(alpha(0));
        stage.addActor(leftAnswer);
        System.out.println(card.toString());
    }

    private void initSpecs() {
        mental = new Label("", skin, "spec");
        mental.setPosition(525, 600);
        mental.setSize(90, 15+75);
        stage.addActor(mental);

        study = new Label("", skin, "spec");
        study.setPosition(640, 600);
        study.setSize(130, 15+80);
        stage.addActor(study);

        health = new Label("", skin, "spec");
        health.setPosition(418, 600);
        health.setSize(100, 15+80);
        stage.addActor(health);

        money = new Label("", skin, "spec");
        money.setPosition(750, 600);
        money.setSize(100, 15+80);
        stage.addActor(money);

        mask = new Texture(Gdx.files.internal("UI/Mask.png"));
        maskImg = new Image(mask);
        maskImg.setPosition(420, 600);
        maskImg.setSize(440, 100);
        stage.addActor(maskImg);

        change = new Texture(Gdx.files.internal("cyan-dodgeball.png"));
        healthChangeI = new Image(change);
        healthChangeI.setPosition(510, 610);
        healthChangeI.setSize(20,15);
        stage.addActor(healthChangeI);
        healthChangeI.addAction(alpha(0));

        mentalChangeI = new Image(change);
        mentalChangeI.setPosition(610, 610);
        mentalChangeI.setSize(20,15);
        stage.addActor(mentalChangeI);
        mentalChangeI.addAction(alpha(0));

        studyChangeI = new Image(change);
        studyChangeI.setPosition(728, 610);
        studyChangeI.setSize(20,15);
        stage.addActor(studyChangeI);
        studyChangeI.addAction(alpha(0));

        moneyChangeI = new Image(change);
        moneyChangeI.setPosition(820, 610);
        moneyChangeI.setSize(20,15);
        stage.addActor(moneyChangeI);
        moneyChangeI.addAction(alpha(0));
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


        lifeDuration = new Label("Day#0", skin, "sideInfo");
        lifeDuration.setPosition(20, 600);
        lifeDuration.setSize(200, 80);
        lifeDuration.setWrap(true);
        stage.addActor(lifeDuration);

        additionalInfo = new Label("*Some Additional Info*", skin, "sideInfo");
        additionalInfo.setPosition(20, 20);
        additionalInfo.setSize(200, 80);
        stage.addActor(additionalInfo);
    }

    private void updateLabels() {
        cardText.setText(card.getLineM());
        additionalInfo.setText(card.getCharacter());
        short[] stats = PlayerStatus.getStats();
        if (stats[0] == 0) health.setVisible(false);
        else health.setHeight(15+(int)(80*(stats[0] / 100.0)));
        if (stats[1] == 0) mental.setVisible(false);
        else mental.setHeight(15+(int)(75*(stats[1] / 100.0)));
        if (stats[2] == 0) study.setVisible(false);
        else study.setHeight(15+(int)(80*(stats[2] / 100.0)));
        if (stats[3] == 0) money.setVisible(false);
        else money.setHeight(15+(int)(80*(stats[3] / 100.0)));
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
        card = AssetManager.getCard("wu");

        PlayerStatus.resetStats();
        initLabels();
        initCard();
        initSpecs();
        initButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        batch.begin();
        // TODO: add Stacks w labels and card image
        if (Gdx.input.getX() > 400 && Gdx.input.getX() < 600 && movedStatus != 2 && Gdx.input.getY() > 120 && Gdx.input.getY() < 520) {
            cardImage.addAction(parallel(moveBy(-70, 0, .5f, Interpolation.pow2), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
            leftAnswer.addAction(parallel(moveBy(-130, 65, .5f, Interpolation.pow2), alpha(1, .5f)));
            byte[] stats = card.getStatsL();
            if(stats[0] != 0) healthChangeI.addAction(alpha(1, .5f));
            if(stats[1] != 0) mentalChangeI.addAction(alpha(1, .5f));
            if(stats[2] != 0) studyChangeI.addAction(alpha(1, .5f));
            if(stats[3] != 0) moneyChangeI.addAction(alpha(1, .5f));
            movedStatus = 2;
        } else if (Gdx.input.getX() < 880 && Gdx.input.getX() > 680 && movedStatus != 1 && Gdx.input.getY() > 120 && Gdx.input.getY() < 520) {
            cardImage.addAction(parallel(moveBy(70, 0, .5f, Interpolation.pow2), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
            rightAnswer.addAction(parallel(moveBy(150, -50, .5f, Interpolation.pow2), alpha(1, .5f)));
            byte[] stats = card.getStatsR();
            if(stats[0] != 0) healthChangeI.addAction(alpha(1, .5f));
            if(stats[1] != 0) mentalChangeI.addAction(alpha(1, .5f));
            if(stats[2] != 0) studyChangeI.addAction(alpha(1, .5f));
            if(stats[3] != 0) moneyChangeI.addAction(alpha(1, .5f));
            movedStatus = 1;
        } else if ((Gdx.input.getX() < 400 || Gdx.input.getX() > 880)||(Gdx.input.getX() > 600 && Gdx.input.getX() < 680)) {// FIXME: collision bug
            if (movedStatus == 2) {
                healthChangeI.addAction(alpha(0, .5f));
                mentalChangeI.addAction(alpha(0, .5f));
                studyChangeI.addAction(alpha(0, .5f));
                moneyChangeI.addAction(alpha(0, .5f));
                cardImage.addAction(parallel(moveBy(70, 0, .5f), rotateBy(-5f, .5f))/*run(transitionRunnable)*/);
                leftAnswer.addAction(parallel(moveBy(130, -65, .5f, Interpolation.pow2), alpha(0, .5f)));
                movedStatus = 0;
            } else if (movedStatus == 1) {
                healthChangeI.addAction(alpha(0, .5f));
                mentalChangeI.addAction(alpha(0, .5f));
                studyChangeI.addAction(alpha(0, .5f));
                moneyChangeI.addAction(alpha(0, .5f));
                cardImage.addAction(parallel(moveBy(-70, 0, .5f), rotateBy(5f, .5f))/*run(transitionRunnable)*/);
                rightAnswer.addAction(parallel(moveBy(-150, 50, .5f, Interpolation.pow2), alpha(0, .5f)));
                movedStatus = 0;
            }
        }

        batch.end();
        updateLabels();
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
        stage.dispose();
        skin.dispose();
    }
}
