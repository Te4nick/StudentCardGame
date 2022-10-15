package ru.scg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StudentCardGame extends Game {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static SpriteBatch batch;
	public BitmapFont defaultFont;
	public BitmapFont gameMenuFont;

	public MainMenuScreen mainMenuScreen;

	@Override
	public void create() {
		batch = new SpriteBatch();

		defaultFont = new BitmapFont();
		//gameMenuFont = new BitmapFont(Gdx.files.internal("font.ttf"));

		mainMenuScreen = new MainMenuScreen(this);
		this.setScreen(mainMenuScreen);
	}

	/*public static SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Plug.jpg");
		System.out.println(Localizator.getString("game"));
		Gdx.graphics.setWindowedMode(1280,720);
		// Localizator.setLocaleDefault(Localizator.LOCALE_RU);
		// System.out.println(Localizator.getString("game"));
		//HashMap<String, Card> deck = AssetManager.buildDeck();
		//String[] cKeys = AssetManager.getCardKeys();
		//System.out.println(deck.get(cKeys[2]).toString());
		//AssetManager.startTextGame();


	}
*/
	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		defaultFont.dispose();
		//this.getScreen().dispose();
	}


}
