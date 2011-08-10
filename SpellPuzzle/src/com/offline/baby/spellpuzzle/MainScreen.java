package com.offline.baby.spellpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actors.Button;
import com.badlogic.gdx.scenes.scene2d.actors.Button.ClickListener;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.offline.baby.spellpuzzle.config.Settings;
import com.offline.baby.spellpuzzle.widget.AccordionLayer;
import com.offline.baby.spellpuzzle.widget.AccordionLayer.Direction;
import com.offline.baby.spellpuzzle.widget.ButtonEx;
import com.offline.baby.spellpuzzle.widget.OnTouchDown;
import com.offline.baby.spellpuzzle.widget.SwitchButton;
import com.offline.baby.spellpuzzle.widget.Wheel;
import com.offline.baby.spellpuzzle.widget.Wheel.Overflow;
import com.offline.baby.spellpuzzle.widget.actions.FoldIn;
import com.offline.baby.spellpuzzle.widget.actions.FoldOut;

public class MainScreen extends BaseScreen<SpellPuzzle> {

	private Wheel wheel;
	private ButtonEx animalBtn;
	private ButtonEx colorBtn;
	private ButtonEx jobBtn;
	private ButtonEx vegetableBtn;
	private ButtonEx vehicleBtn;
	private Image bg;
	private Image bgGrass;

	private boolean waitingSettingInitial;
	private Image settingBg;
	private Image settingTitle;
	private Image settingTips;
	private Image settingAppTitle;
	private Button settingSave;
	private SwitchButton wordShakeEnabled;
	private SwitchButton wordSoundsEnabled;
	private SwitchButton wordLetterEnabled;
	private SwitchButton cutlineSoundEnabled;
	private SwitchButton chineseDisplayEnabled;

	private Group settingGroup;

	private boolean showSettings = false;

	public MainScreen(final SpellPuzzle game) {
		super(game);

		bg = new Image("MAIN_BG", Assets.MAIN_BG);
		setBackground(bg);

		bgGrass = new Image("MAIN_GRASS", Assets.MAIN_GRASS);
		setAboveBackground(bgGrass);

		wheel = new Wheel("Wheel", Overflow.X, Gdx.graphics.getWidth(),
				Assets.BTN_ANIMAL_UNPRESSED.getRegionHeight(), 50);

		animalBtn = new ButtonEx("CARD_BUTTON_ANIMAL",
				Assets.BTN_ANIMAL_UNPRESSED, Assets.BTN_ANIMAL_PRESSED);
		// animalBtn.x = stage.centerX() - animalBtn.originX;
		// animalBtn.y = (int) (stage.height() * 0.1f);
		animalBtn.onTouchDown = new OnTouchDown() {

			@Override
			public void pressed(ButtonEx button) {
				Assets.CLICK.play();
			}
		};

		animalBtn.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY()
								- animalBtn.y) != null) {
					game.setScreen(new GameScreen(game));
				}
			}
		};
		
		wheel.addActor(animalBtn);
		
		animalBtn = new ButtonEx("CARD_BUTTON_ANIMAL",
				Assets.BTN_ANIMAL_UNPRESSED, Assets.BTN_ANIMAL_PRESSED);
		// animalBtn.x = stage.centerX() - animalBtn.originX;
		// animalBtn.y = (int) (stage.height() * 0.1f);
		animalBtn.onTouchDown = new OnTouchDown() {

			@Override
			public void pressed(ButtonEx button) {
				Assets.CLICK.play();
			}
		};

		animalBtn.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY()
								- animalBtn.y) != null) {
					game.setScreen(new GameScreen(game));
				}
			}
		};
		
		animalBtn = new ButtonEx("CARD_BUTTON_ANIMAL",
				Assets.BTN_ANIMAL_UNPRESSED, Assets.BTN_ANIMAL_PRESSED);
		// animalBtn.x = stage.centerX() - animalBtn.originX;
		// animalBtn.y = (int) (stage.height() * 0.1f);
		animalBtn.onTouchDown = new OnTouchDown() {

			@Override
			public void pressed(ButtonEx button) {
				Assets.CLICK.play();
			}
		};

		animalBtn.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY()
								- animalBtn.y) != null) {
					game.setScreen(new GameScreen(game));
				}
			}
		};
		
		animalBtn = new ButtonEx("CARD_BUTTON_ANIMAL",
				Assets.BTN_ANIMAL_UNPRESSED, Assets.BTN_ANIMAL_PRESSED);
		// animalBtn.x = stage.centerX() - animalBtn.originX;
		// animalBtn.y = (int) (stage.height() * 0.1f);
		animalBtn.onTouchDown = new OnTouchDown() {

			@Override
			public void pressed(ButtonEx button) {
				Assets.CLICK.play();
			}
		};

		animalBtn.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY()
								- animalBtn.y) != null) {
					game.setScreen(new GameScreen(game));
				}
			}
		};
		
		animalBtn = new ButtonEx("CARD_BUTTON_ANIMAL",
				Assets.BTN_ANIMAL_UNPRESSED, Assets.BTN_ANIMAL_PRESSED);
		// animalBtn.x = stage.centerX() - animalBtn.originX;
		// animalBtn.y = (int) (stage.height() * 0.1f);
		animalBtn.onTouchDown = new OnTouchDown() {

			@Override
			public void pressed(ButtonEx button) {
				Assets.CLICK.play();
			}
		};

		animalBtn.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY()
								- animalBtn.y) != null) {
					game.setScreen(new GameScreen(game));
				}
			}
		};

		// btn.action(FadeIn.$(0.3f));

		// addFunctionActor(btn);

		loadMenu();
	}

	private void loadMenu() {

		// 初始化 menu 内容
		menuSwitch = new SwitchButton("MENU_SWITCH", Assets.MENU_SWITCH);
		menuSwitchBg = new Button("MENU_SWITCH_BG", Assets.MENU_SWITCH_BG);
		menuBg = new AccordionLayer("MENU_BG", Assets.MENU_BG,
				Direction.BOTTOM, 25f);
		menuInfoBtn = new Button("MENU_INFO_BTN", Assets.MENU_INFO);
		menuHelpBtn = new Button("MENU_HELP_BTN", Assets.MENU_HELP);
		menuSettingsBtn = new Button("MENU_SETTINGS_BTN", Assets.MENU_SETTING);

		// 设置 menu 位置
		menuBg.x = 378 + 8;
		menuBg.y = stage.height() - 34 - menuSwitchBg.height / 2;
		menuBg.drawableHeight = 0;
		menuSwitchBg.x = 378;
		menuSwitchBg.y = stage.height() - 34 - menuSwitchBg.height;
		menuSwitch.x = 378 + menuSwitchBg.originX - menuSwitch.originX;
		menuSwitch.y = stage.height() - 34 - menuSwitchBg.height
				+ menuSwitchBg.originY - menuSwitch.originY + 5;

		// 描述 menu 事件，动作
		menuSwitchBg.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				menuSwitch.clearActions();
				menuSwitch.setChecked(!menuSwitch.isChecked());
				if (menuSwitch.isChecked()) {
					menuSwitch.action(RotateTo.$(180f, 0.3f));
					menuBg.action(FoldIn.$(0.3f));
				} else {
					menuSwitch.action(RotateTo.$(0f, 0.3f));
					menuBg.action(FoldOut.$(0.3f));
				}
			}
		};

		menuSwitch.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				SwitchButton btn = (SwitchButton) button;
				menuSwitch.clearActions();
				if (btn.isChecked()) {
					menuSwitch.action(RotateTo.$(180f, 0.3f));
					menuBg.action(FoldIn.$(0.3f));
				} else {
					menuSwitch.action(RotateTo.$(0f, 0.3f));
					menuBg.action(FoldOut.$(0.3f));
				}
			}
		};

		menuSettingsBtn.clickListener = new ClickListener() {
			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY() - button.y) != null) {

					if (!waitingSettingInitial) {
						loadSettings();
						for (Actor actor : settingGroup.getActors()) {
							actor.action(FadeIn.$(0.3f));
						}
					}
				}
			}
		};

		// 添加控件：先添加先画
		overflowGroup.addActor(menuBg);
		menuBg.addActor(menuSettingsBtn);
		menuBg.addActor(menuHelpBtn);
		menuBg.addActor(menuInfoBtn);
		overflowGroup.addActor(menuInfoBtn);
		overflowGroup.addActor(menuHelpBtn);
		overflowGroup.addActor(menuSettingsBtn);
		overflowGroup.addActor(menuSwitchBg);
		overflowGroup.addActor(menuSwitch);
	}

	private void loadSettings() {
		waitingSettingInitial = true;

		// 创建 setting组， 理论上保持在 overflow 的最顶层
		settingGroup = new Group("SETTING_GROUP");

		int settingOffsetY = 30;

		settingBg = new Image("SETTING_BG", Assets.SETTING_BG);
		settingTitle = new Image("SETTING_TITLE", Assets.SETTING_TITLE);
		settingTips = new Image("SETTING_TITLE", Assets.SETTING_TIPS);
		settingAppTitle = new Image("SETTING_TITLE", Assets.SETTING_APP_TITLE);
		wordShakeEnabled = new SwitchButton("SETTING_WORD_SHAKE_BTN",
				Assets.SETTING_SWITCH_OFF, Assets.SETTING_SWITCH_ON,
				Settings.WORD_SHAKE_ENABLED);
		wordSoundsEnabled = new SwitchButton("SETTING_WORD_SOUNDS_BTN",
				Assets.SETTING_SWITCH_OFF, Assets.SETTING_SWITCH_ON,
				Settings.WORD_SOUNDS_ENABLED);
		wordLetterEnabled = new SwitchButton("SETTING_WORD_LETTER_BTN",
				Assets.SETTING_SWITCH_OFF, Assets.SETTING_SWITCH_ON,
				Settings.WORD_LETTER_ENABLED);
		cutlineSoundEnabled = new SwitchButton("SETTING_WORD_LETTER_BTN",
				Assets.SETTING_SWITCH_OFF, Assets.SETTING_SWITCH_ON,
				Settings.CUTLINE_SOUND_ENABLED);
		chineseDisplayEnabled = new SwitchButton("SETTING_WORD_LETTER_BTN",
				Assets.SETTING_SWITCH_OFF, Assets.SETTING_SWITCH_ON,
				Settings.CHINESE_DISPLAY_ENABLED);
		settingSave = new Button("SETTING_SAVE_BTN",
				Assets.SETTING_BUTTON_UNPRESSED, Assets.SETTING_BUTTON_PRESSED);

		// settingBg.color.a = 0;
		// settingTitle.color.a = 0;
		// settingTips.color.a = 0;
		// settingAppTitle.color.a = 0;
		// wordShakeEnabled.color.a = 0;
		// wordSoundsEnabled.color.a = 0;
		// wordLetterEnabled.color.a = 0;
		// cutlineSoundEnabled.color.a = 0;
		// chineseDisplayEnabled.color.a = 0;
		// settingSave.color.a = 0;

		// 设置 setting 各组件位置
		settingBg.x = stage.centerX() - settingBg.originX;
		settingBg.y = stage.centerY() - settingBg.originY;
		settingTitle.x = stage.centerX() - settingTitle.originX;
		settingTitle.y = 638;
		settingTips.x = stage.centerX() - settingTips.width;
		settingTips.y = stage.centerY() - settingTips.originY + settingOffsetY;
		settingAppTitle.x = settingBg.x + settingBg.width
				- settingAppTitle.width - 20;
		settingAppTitle.y = settingBg.y + 10;

		wordShakeEnabled.x = stage.centerX() + 10;
		wordShakeEnabled.y = 505 + settingOffsetY;

		wordSoundsEnabled.x = stage.centerX() + 10;
		wordSoundsEnabled.y = 445 + settingOffsetY;

		wordLetterEnabled.x = stage.centerX() + 10;
		wordLetterEnabled.y = 385 + settingOffsetY;

		cutlineSoundEnabled.x = stage.centerX() + 10;
		cutlineSoundEnabled.y = 325 + settingOffsetY;

		chineseDisplayEnabled.x = stage.centerX() + 10;
		chineseDisplayEnabled.y = 265 + settingOffsetY;

		settingSave.x = stage.centerX() - settingSave.originX;
		settingSave.y = 180;

		// 配置 setting 各组件事件，动作
		wordShakeEnabled.clickListener = new ClickListener() {
			@Override
			public void clicked(Button button) {
				SwitchButton btn = (SwitchButton) button;
				Settings.WORD_SHAKE_ENABLED = btn.isChecked();
				Settings.save();
			}
		};

		wordSoundsEnabled.clickListener = new ClickListener() {
			@Override
			public void clicked(Button button) {
				SwitchButton btn = (SwitchButton) button;
				Settings.WORD_SOUNDS_ENABLED = btn.isChecked();
				Settings.save();
			}
		};

		wordLetterEnabled.clickListener = new ClickListener() {
			@Override
			public void clicked(Button button) {
				SwitchButton btn = (SwitchButton) button;
				Settings.WORD_LETTER_ENABLED = btn.isChecked();
				Settings.save();
			}
		};

		cutlineSoundEnabled.clickListener = new ClickListener() {
			@Override
			public void clicked(Button button) {
				SwitchButton btn = (SwitchButton) button;
				Settings.CUTLINE_SOUND_ENABLED = btn.isChecked();
				Settings.save();
			}
		};

		chineseDisplayEnabled.clickListener = new ClickListener() {
			@Override
			public void clicked(Button button) {
				SwitchButton btn = (SwitchButton) button;
				Settings.CHINESE_DISPLAY_ENABLED = btn.isChecked();
				Settings.save();
			}
		};

		settingSave.clickListener = new ClickListener() {

			@Override
			public void clicked(Button button) {
				if (button.hit(Gdx.input.getX() - button.x,
						Gdx.graphics.getHeight() - Gdx.input.getY() - button.y) != null) {

					for (final Actor actor : settingGroup.getActors()) {
						actor.action(FadeOut.$(0.3f).setCompletionListener(
								new OnActionCompleted() {

									@Override
									public void completed(Action action) {
										actor.markToRemove(true);
									}
								}));
					}
				}
			}
		};

		// 添加控件：先添加先画
		settingGroup.addActor(settingBg);
		settingGroup.addActor(settingTitle);
		settingGroup.addActor(settingTips);
		settingGroup.addActor(settingAppTitle);
		settingGroup.addActor(wordShakeEnabled);
		settingGroup.addActor(wordSoundsEnabled);
		settingGroup.addActor(wordLetterEnabled);
		settingGroup.addActor(cutlineSoundEnabled);
		settingGroup.addActor(chineseDisplayEnabled);
		settingGroup.addActor(settingSave);
		overflowGroup.addActor(settingGroup);

		waitingSettingInitial = false;
	}

	@Override
	public void update(float delta) {
		if (showSettings) {

		}
	}

	@Override
	public void showMenu() {
		if (menuSwitch.isChecked()) {
			menuSwitch.action(RotateTo.$(0f, 0.3f));
			menuBg.action(FoldOut.$(0.3f));
		} else {
			menuSwitch.action(RotateTo.$(180f, 0.3f));
			menuBg.action(FoldIn.$(0.3f));
		}
		menuSwitch.setChecked(!menuSwitch.isChecked());
	}

	@Override
	public void dispose() {
		super.dispose();
		animalBtn = null;
		bg = null;
		bgGrass = null;
	}
}
