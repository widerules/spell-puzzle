package com.offline.baby.spellpuzzle.widget;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.offline.baby.spellpuzzle.data.CardInfo;

public class Word extends Actor implements IPlay {

	public interface LetterClickListener{
		void clicked(Letter clickedLetter);
	}
	
	private String word;
	private List<Letter> letterList;
	private Sound sound;
	private boolean isRead = false;
	private LetterClickListener letterClickListener;

	public Word(String name, CardInfo card) {
		super(name);
		this.word = card.getLetters();
		letterList = Letter.emptyWordFrom(this.word);

		for (Letter ltr : letterList) {
			ltr.canScale = false;
			ltr.locked = true;
		}

		originX = Letter.LETTER_WIDTH * letterList.size() / 2.0f;
		originY = Letter.LETTER_HEIGHT / 2.0f;
		width = Letter.LETTER_WIDTH * letterList.size();
		height = Letter.LETTER_HEIGHT;
		if (null != card.getVoiceFilePath()
				&& !"".equals(card.getVoiceFilePath())) {
			sound = Gdx.audio.newSound(Gdx.files.internal(card
					.getVoiceFilePath()));
		}
	}

	public String getWord() {
		return this.word;
	}

	@Override
	protected void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		float offsetX = x;
		float offsetY = y;
		for (Letter ltr : letterList) {
			ltr.x = offsetX;
			ltr.y = offsetY;
			offsetX += Letter.LETTER_WIDTH;
			ltr.draw(batch, parentAlpha);
		}
	}

	@Override
	protected boolean touchDown(float x, float y, int pointer) {
		// 首先判断是否点中自己
		boolean result = x > 0 && y > 0 && x < width && y < height;
		
		if (result) {
			int index = (int) x / Letter.LETTER_WIDTH;
			Letter ltr = letterList.get(index);
			if (ltr.touchable) {
				ltr.play();
				if (letterClickListener != null){
					letterClickListener.clicked(ltr);
				}
			}
		}
		return result;
	}

	@Override
	protected boolean touchUp(float x, float y, int pointer) {
		boolean result = x > 0 && y > 0 && x < width && y < height;
		return result;
	}

	@Override
	protected boolean touchDragged(float x, float y, int pointer) {
		return false;
	}

	@Override
	public Actor hit(float x, float y) {
		return x > 0 && y > 0 && x < width && y < height ? this : null;
	}

	public List<Letter> getLetterList() {
		return letterList;
	}

	public boolean isCompleted() {
		for (Letter ltr : letterList) {
			if (!ltr.isFilled()) {
				return false;
			}
		}

		return true;
	}

	public LetterClickListener getLetterClickListener() {
		return letterClickListener;
	}

	public void setLetterClickListener(LetterClickListener letterClickListener) {
		this.letterClickListener = letterClickListener;
	}

	public void disable() {
		for (Letter ltr : letterList) {
			ltr.touchable = false;
		}
	}

	@Override
	public void play() {
		sound.play();
	}

	@Override
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public boolean isRead() {
		return this.isRead;
	}
}
