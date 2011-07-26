package com.offline.baby.spellpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actors.Button;
import com.badlogic.gdx.scenes.scene2d.actors.Button.ClickListener;
import com.badlogic.gdx.scenes.scene2d.actors.Label;
import com.offline.baby.spellpuzzle.config.Settings;
import com.offline.baby.spellpuzzle.widget.AccordionLayer;
import com.offline.baby.spellpuzzle.widget.AccordionLayer.Direction;
import com.offline.baby.spellpuzzle.widget.SwitchButton;
import com.offline.baby.spellpuzzle.widget.actions.FoldIn;
import com.offline.baby.spellpuzzle.widget.actions.FoldOut;

public abstract class BaseScreen<G extends Game> implements Screen {

	abstract public void update(float delta);

	protected G game;
	protected SpriteBatch batcher;
	protected OrthographicCamera guiCam;
	protected Stage stage;

	protected SwitchButton menuSwitch;
	protected Button menuSwitchBg;
	protected AccordionLayer menuBg;
	protected Button menuInfoBtn;
	protected Button menuHelpBtn;
	protected Button menuSettingsBtn;

	protected Group backGroup;
	protected Group middleGroup;
	protected Group overflowGroup;

	protected Label framePerSecordsLbl;

	public BaseScreen(G game) {
		this.game = game;
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		batcher = new SpriteBatch();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		Gdx.input.setInputProcessor(stage);

		backGroup = new Group("GROUP_BACKGROUND");
		middleGroup = new Group("GROUP_FUNCATION");
		overflowGroup = new Group("GROUP_OVERFLOW");

		stage.addActor(backGroup);
		stage.addActor(middleGroup);
		stage.addActor(overflowGroup);

		

		if (Settings.SHOW_FRAME) {
			loadFrameLabel();
		}
	}

	public void showMenu() {

	}

	public void showBackConfirm() {
		if (game.getScreen() instanceof MainScreen) {
			
		} else {
			game.setScreen(new MainScreen((SpellPuzzle) game));
		}
	}

	private void loadFrameLabel() {
		framePerSecordsLbl = new Label("FRAME_LABEL", new BitmapFont());
		overflowGroup.addActor(framePerSecordsLbl);
	}

	protected void setBackground(Actor actor) {
		if (backGroup.getActors().size() != 0
				&& backGroup.getActors().get(0) != null) {
			backGroup.removeActor(backGroup.getActors().get(0));
		}
		backGroup.addActorAt(0, actor);
	}

	protected void setAboveBackground(Actor actor) {
		backGroup.addActorAt(1, actor);
	}

	protected void addOverflowActor(Actor actor) {
		overflowGroup.addActor(actor);
	}

	protected void addFunctionActor(Actor actor) {
		middleGroup.addActor(actor);
	}

	@Override
	public void resize(int width, int height) {
		// Gdx.app.log("debug", this + "on resized");
	}

	@Override
	public void hide() {
		// Gdx.app.log("debug", this + "on hided");
	}

	@Override
	public void pause() {
		// Gdx.app.log("debug", this + "on paused");
	}

	@Override
	public void resume() {
		// Gdx.app.log("debug", this + "on resumed");
	}

	@Override
	public void dispose() {
	}

	@Override
	public void show() {
		// Gdx.app.log("debug", this + "on showed");
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	public void draw(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (Settings.SHOW_FRAME) {
			framePerSecordsLbl.setText(String.valueOf(Gdx.graphics
					.getFramesPerSecond()));
		}

		stage.act(delta);
		stage.draw();
	}
}
