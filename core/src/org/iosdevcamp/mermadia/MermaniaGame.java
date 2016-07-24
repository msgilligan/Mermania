package org.iosdevcamp.mermadia;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.Vector2;

public class MermaniaGame implements ApplicationListener, GestureListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private int phoneWidth = 1920 * 2/5;
	private int phoneHeight = 1080 * 2/5;

	float deltaAzimuth, lastAzimuth;

	@Override
	public void create () {
		camera = new OrthographicCamera(phoneWidth, phoneHeight);
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("Background.png"));
		//texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		//or MirroredRepeat
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setOrigin(0,0);
		sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		//using azimuth data
		deltaAzimuth = (Gdx.input.getAzimuth() - lastAzimuth);
		camera.rotate(camera.up, -deltaAzimuth);
		camera.update();
		lastAzimuth = Gdx.input.getAzimuth();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
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
	public boolean touchDown(float x, float y, int pointer, int button) {
// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean tap(float x, float y, int count, int button) {
// TODO Auto-generated method stub
		if (count == 1 ){
			camera.translate(0,1);
		}else{
			camera.translate(0,10);
		}
		camera.update();
		return false;
	}
	@Override
	public boolean longPress(float x, float y) {
// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		camera.translate(deltaX,0);
		camera.update();
		return false;
	}


	@Override
	public boolean zoom(float initialDistance, float distance) {
// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
						 Vector2 pointer1, Vector2 pointer2) {
// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void pinchStop(){

	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button){
		return false;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void restartCamara(){

	}

}
