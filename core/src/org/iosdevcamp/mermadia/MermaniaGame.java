package org.iosdevcamp.mermadia;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

public class MermaniaGame extends ApplicationAdapter implements GestureListener {
	public static final int WORLD_WIDTH = 16200;
	public static final int WORLD_HEIGHT = 1920;
	SpriteBatch batch;
	Sprite keySprite;
	Sprite chestSprite;
	Sprite NPCSprite;
	Sprite monsterSprite;

	Key key;
	Chest chest;
	NPC friend;
	Monster monster;

	Texture BGImg[];

	private OrthographicCamera camera;
	private int cameraX;

	@Override
	public void create () {
		camera = new OrthographicCamera(1080, 1920);
		batch = new SpriteBatch();
		BGImg = new Texture[8];
		for(int i = 0; i < BGImg.length; i++) {
			BGImg[i] = new Texture("Background.png");
		}

		Gdx.input.setInputProcessor(new GestureDetector(this));

		keySprite = new Sprite(new Texture("Seahorse.png"));
		chestSprite = new Sprite(new Texture("badlogic.jpg"));
		NPCSprite = new Sprite(new Texture("Mermaid.png"));
		monsterSprite = new Sprite(new Texture("monsterone.png"));

		key = new Key(0, 100);
		chest = new Chest(400, 400);
		friend = new NPC(NPCSprite.getRegionWidth(), NPCSprite.getRegionHeight());
		monster = new Monster(monsterSprite.getRegionWidth(), monsterSprite.getRegionHeight(), key, chest);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		NPCSprite.rotate((float) Math.toDegrees((float) friend.move()));
		monsterSprite.rotate((float) Math.toDegrees((float) monster.move()));

		if(chest.isFound()){
			return;
		}

		keySprite.setPosition(key.getX(), key.getY());
		chestSprite.setPosition(chest.getX(), chest.getY());
		NPCSprite.setPosition(friend.getX(), friend.getY());
		monsterSprite.setPosition(monster.getX(), monster.getY());

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for(int i = 0; i < BGImg.length; i++) {
			batch.draw(BGImg[i], BGImg[i].getWidth() * i - BGImg[i].getWidth() * 3 / 2, -BGImg[i].getHeight() / 2);
		}
		if(!key.isFound()) {
			keySprite.draw(batch);
		}
		chestSprite.draw(batch);
		NPCSprite.draw(batch);
		monsterSprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(int i = 0; i < BGImg.length; i++) {
			BGImg[i].dispose();
		}
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
		System.out.println("Touch X: " + x + "\tY: " + y +"\tCamera X: " + cameraX);
		if(x + cameraX == key.getX() &&  WORLD_HEIGHT - y == key.getY()) {
			key.setFound();
		}
		if(x + cameraX == chest.getX() && WORLD_HEIGHT - y == chest.getY()) {
			chest.setFound();
		}
		return false;
	}
	@Override
	public boolean tap(float x, float y, int count, int button) {
// TODO Auto-generated method stub
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
	public boolean pan(float x, float y, float deltaX, float deltaY) {		//Wrapping world w/ deadspot at seam
		// TODO Auto-generated method stub
		camera.translate(-deltaX * 2, 0);
		cameraX -= (deltaX * 2);
		if(cameraX < -BGImg[0].getWidth() / 2) {
			camera.translate(WORLD_WIDTH + BGImg[0].getWidth(), 0);
			cameraX += WORLD_WIDTH + BGImg[0].getWidth();
		} else if(cameraX > WORLD_WIDTH + BGImg[5].getWidth() / 2){
			camera.translate(-WORLD_WIDTH - BGImg[0].getWidth(), 0);
			cameraX += -WORLD_WIDTH - BGImg[0].getWidth();
		}
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
	public void pinchStop(){}

	@Override
	public boolean panStop(float x, float y, int a, int b)
	{
		return false;
	}

}
