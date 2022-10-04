package ru.scg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;

public class StudentCardGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		System.out.println(Localizator.getString("game"));
		// Localizator.setLocaleDefault(Localizator.LOCALE_RU);
		// System.out.println(Localizator.getString("game"));
		HashMap<String, Card> deck = AssetManager.buildDeck();
		String[] cKeys = AssetManager.getCardKeys();
		System.out.println(deck.get(cKeys[2]).toString());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
