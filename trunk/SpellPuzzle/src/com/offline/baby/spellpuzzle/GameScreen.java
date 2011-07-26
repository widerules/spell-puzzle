package com.offline.baby.spellpuzzle;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.actors.Button;
import com.badlogic.gdx.scenes.scene2d.actors.Button.ClickListener;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.offline.baby.spellpuzzle.config.Settings;
import com.offline.baby.spellpuzzle.data.CardInfo;
import com.offline.baby.spellpuzzle.data.DBManager;
import com.offline.baby.spellpuzzle.widget.Letter;
import com.offline.baby.spellpuzzle.widget.Letter.OnTouchDown;
import com.offline.baby.spellpuzzle.widget.ListedGroup;
import com.offline.baby.spellpuzzle.widget.MovableButton;
import com.offline.baby.spellpuzzle.widget.MovableButton.DragListener;
import com.offline.baby.spellpuzzle.widget.Word;
import com.offline.baby.spellpuzzle.widget.Word.LetterClickListener;
import com.offline.baby.spellpuzzle.widget.actions.Read;

public class GameScreen extends BaseScreen<Game> {

	public static enum State {
		INIT, RUNNING, DONE, WAITING_SPELL, FLUSH, NEXT, WAITING_ACTION;
	}

	private State state = State.INIT;

	private List<CardInfo> cardList;

	private Image bg;

	private CardInfo card;
	private Button cutline;
	private List<Letter> letterList;
	private Word word;

	private ListedGroup letterGroup;

	private int currentIndex = 0;

	private Image font;

	public GameScreen(Game game) {
		super(game);
		bg = new Image("bg", Assets.GAME_BG);
		setBackground(bg);

		cardList = DBManager.CardResultSet.getCardInfo();
		letterGroup = new ListedGroup("LETTER_GROUP");
	}

	@Override
	public void update(float delta) {
		switch (state) {
		case INIT:
			updateInit();
			break;
		case RUNNING:
			updateRunning();
			break;
		case DONE:
			updateDone();
			break;
		case WAITING_SPELL:
			updateWaitingSpell();
			break;
		case FLUSH:
			updateFlush();
			break;
		case NEXT:
			updateNext();
			break;
		case WAITING_ACTION:
			// nothing to do , just waiting current action;
			break;
		default:
			break;
		}
	}

	private void updateInit() {
		state = State.WAITING_ACTION;
		// TODO 在这里可以设置card顺序，过滤等等

		currentIndex = 0;

		state = State.NEXT;
	}

	private void updateRunning() {
		if (word.isCompleted()) {
			state = State.WAITING_ACTION;
			for (Letter ltr : letterList) {
				ltr.touchable = false;
			}
			word.disable();
			cutline.touchable = false;
			state = State.DONE;
		}
	}

	private void updateDone() {
		state = State.WAITING_ACTION;
		letterGroup.sortChildren(new Comparator<Actor>() {

			@Override
			public int compare(Actor act0, Actor act1) {
				if (letterList.contains(act0) && letterList.contains(act1)) {
					return letterList.indexOf(act0) > letterList.indexOf(act1) ? -1
							: 1;
				}
				return 0;
			}
		});

		if (Settings.CHINESE_DISPLAY_ENABLED) {
			font = new Image("chinese", new TextureRegion(Assets.loadTexture(
					card.getChinese(), true), 0, 0, 200, 77));
			font.x = stage.centerX() - font.originX;
			font.y = Gdx.graphics.getHeight() * 0.2f;
			font.action(FadeIn.$(0.5f).setCompletionListener(
					new OnActionCompleted() {

						@Override
						public void completed(Action action) {
							currentIndex += 1;

							float offsetDuration = 0;

							for (int i = 0; i < letterList.size(); i++) {
								final Letter ltr = letterList.get(i);
								ltr.action(Delay.$(
										Sequence.$(
												ScaleTo.$(1.2f, 1.2f, 0.05f),
												Read.$(),
												ScaleTo.$(1f, 1f, 0.05f)),
										offsetDuration).setCompletionListener(
										new OnActionCompleted() {

											@Override
											public void completed(Action action) {
												makeLetterTop(ltr);
											}
										}));

								offsetDuration += Read.$().getDuration();
							}

							word.action(Delay.$(Read.$(2f), offsetDuration)
									.setCompletionListener(
											new OnActionCompleted() {

												@Override
												public void completed(
														Action action) {
													// for (Letter ltr :
													// letterList) {
													// ltr.action(Sequence.$(ScaleTo.$(
													// 1.2f, 1.2f, 0.05f),
													// Delay.$(ScaleTo.$(1f, 1f,
													// 0.05f), 1f)));
													// }
												}
											}));
						}
					}));

			addFunctionActor(font);
		} else {
			currentIndex += 1;

			float offsetDuration = 0;

			for (int i = 0; i < letterList.size(); i++) {
				final Letter ltr = letterList.get(i);
				ltr.action(Delay.$(
						Sequence.$(ScaleTo.$(1.2f, 1.2f, 0.05f), Read.$(),
								ScaleTo.$(1f, 1f, 0.05f)), offsetDuration)
						.setCompletionListener(new OnActionCompleted() {

							@Override
							public void completed(Action action) {
								makeLetterTop(ltr);
							}
						}));

				offsetDuration += Read.$().getDuration();
			}

			word.action(Delay.$(Read.$(2f), offsetDuration)
					.setCompletionListener(new OnActionCompleted() {

						@Override
						public void completed(Action action) {
							// for (Letter ltr :
							// letterList) {
							// ltr.action(Sequence.$(ScaleTo.$(
							// 1.2f, 1.2f, 0.05f),
							// Delay.$(ScaleTo.$(1f, 1f,
							// 0.05f), 1f)));
							// }
						}
					}));
		}
		state = State.WAITING_SPELL;
	}

	private void updateWaitingSpell() {
		if (isSpellCompleted()) {
			state = State.FLUSH;
		}
	}

	private void updateFlush() {
		state = State.WAITING_ACTION;
		cutline.action(FadeOut.$(0.6f).setCompletionListener(
				new OnActionCompleted() {

					@Override
					public void completed(Action action) {
						state = State.NEXT;
					}
				}));
		List<Letter> wordLetters = word.getLetterList();
		for (Letter ltr : wordLetters) {
			ltr.action(FadeOut.$(0.5f));
		}
		for (Letter ltr : letterList) {
			ltr.action(FadeOut.$(0.5f));
		}
		if (Settings.CHINESE_DISPLAY_ENABLED) {
			font.action(FadeOut.$(0.5f));
		}
	}

	private void updateNext() {
		state = State.WAITING_ACTION;
		if (currentIndex == cardList.size()) {
			state = State.INIT;
			return;
		}

		card = cardList.get(currentIndex);

		// cutline picture
		cutline = new Button("cutline", new TextureRegion(
				Assets.loadTexture(card.getFilePath()), 0, 0,
				CardInfo.CARTOON_WIDTH, CardInfo.CARTOON_HEIGHT));

		cutline.x = stage.centerX() - cutline.originX;
		cutline.y = stage.centerY() + 50;
		if (Settings.CUTLINE_SOUND_ENABLED) {
			cutline.clickListener = new ClickListener() {
				@Override
				public void clicked(Button button) {
					word.play();
				}
			};
		}
		cutline.action(FadeIn.$(0.5f));
		setAboveBackground(cutline);

		// word
		word = new Word("word", card);
		word.x = stage.centerX() - word.originX;
		word.y = stage.centerY() - word.originY - 40;
		if (Settings.WORD_SHAKE_ENABLED) {
			word.setLetterClickListener(new LetterClickListener() {

				@Override
				public void clicked(Letter clickedLetter) {
					for (Letter ltr : letterList) {
						if (!ltr.locked
								&& ltr.touchable
								&& ltr.getLetter().equals(
										clickedLetter.getLetter())) {
							ltr.action(Sequence.$(RotateTo.$(10f, 0.02f),
									RotateTo.$(-10f, 0.02f),
									RotateTo.$(10f, 0.02f),
									RotateTo.$(-10f, 0.02f),
									RotateTo.$(10f, 0.02f),
									RotateTo.$(-10f, 0.02f),
									RotateTo.$(0f, 0.02f)));
						}
					}
				}
			});
		}

		word.action(FadeIn.$(0.5f));

		List<Letter> wordLetters = word.getLetterList();
		for (Letter ltr : wordLetters) {
			ltr.setCanPlay(Settings.WORD_SOUNDS_ENABLED);
			addFunctionActor(ltr);
		}

		addFunctionActor(word);

		// letters
		letterList = Letter.from(card.getLetters());
		Rectangle rect = new Rectangle();
		rect.set(0, 0, stage.width() - Letter.LETTER_WIDTH, word.y
				- Letter.LETTER_HEIGHT);

		for (Letter ltr : letterList) {
			ltr.x = (float) (rect.width * Math.random());
			ltr.y = (float) (rect.height * Math.random());
			ltr.onTouchDown = new OnTouchDown() {
				@Override
				public void touchDown(final Letter letter) {
					makeLetterTop(letter);
				}
			};

			ltr.dragListener = new DragListener() {

				@Override
				public void dragged(MovableButton button) {
					Letter ltr = (Letter) button;
					List<Letter> wordLetters = word.getLetterList();
					for (int i = 0; i < wordLetters.size(); i++) {
						Letter target = wordLetters.get(i);

						// 判断word字母未被填充，并且字母相同
						if (!target.isFilled()
								&& ltr.getLetter().equals(target.getLetter())) {
							Rectangle rect = ltr.overlap(target);
							// 判断重叠部分是否超过对应字母的1/4面积
							if (rect != null
									&& rect.width * rect.height * 4 > target.width
											* target.height) {
								if (i != letterList.indexOf(ltr)) {
									Collections.swap(letterList, i,
											letterList.indexOf(ltr));
								}
								ltr.dragged = false;
								ltr.scaleX = 1.0f;
								ltr.scaleY = 1.0f;
								ltr.canScale = false;
								ltr.action(MoveTo.$(target.x, target.y, 0.1f));
								target.setFilled(true);
								ltr.locked = true;
								break;
							}
						}
					}
				}

			};
			ltr.action(FadeIn.$(0.5f));
			letterGroup.addActor(ltr);
		}
		addFunctionActor(letterGroup);

		state = State.RUNNING;
	}

	private boolean isSpellCompleted() {
		if (!word.isRead()) {
			return false;
		}

		for (Letter ltr : letterList) {
			if (!ltr.isRead()) {
				return false;
			}
		}

		return true;
	}

	private void makeLetterTop(Actor actor) {
		letterGroup.makeLast(actor);
	}

	@Override
	public void dispose() {
		super.dispose();

		cardList.clear();

		bg = null;
		card = null;
		cutline = null;
		letterList.clear();
		word = null;
		font = null;
	}
}
