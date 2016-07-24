package org.iosdevcamp.mermadia;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import org.iosdevcamp.mermadia.util.CompassStabilizingQueue;

import java.util.Random;

public class MermaniaGame extends ApplicationAdapter implements GestureListener {
	public static final int WORLD_WIDTH = 16200;
	public static final int WORLD_HEIGHT = 1920;
	public static final float COMPASS_DEBOUNCE = 1;
	private float compassFactor;
	private int numChecksSinceLastPrint;
	SpriteBatch batch;
	Sprite keySprite;
	Sprite chestSprite;
	Sprite NPCSprite;
	Sprite monsterSprite;
	private CompassStabilizingQueue stabilizer = new CompassStabilizingQueue();

	Key key;
	Chest chest;
	NPC friend;
	Monster monster;

	Texture BGImg[];
	Sprite vegitation[];

	float deltaAzimuth, lastAzimuth;

	private OrthographicCamera camera;
	private int cameraX;

	Random r;

	@Override
	public void create () {
		r = new Random();
		camera = new OrthographicCamera(1080, 1920);
		batch = new SpriteBatch();
		BGImg = new Texture[8];
		vegitation = new Sprite[20];
		for(int i = 0; i < BGImg.length; i++) {
			BGImg[i] = new Texture("Background.png");
		}
		compassFactor = (WORLD_WIDTH + BGImg[0].getWidth()) / 360 ;
		for(int i = 0; i < vegitation.length; i++){
			if(i % 2 == 0){
				vegitation[i] = new Sprite(new Texture("kelpo no. 1.gif"));
				vegitation[i].setPosition(r.nextFloat() * WORLD_WIDTH, r.nextFloat() * WORLD_HEIGHT / 4 - WORLD_HEIGHT / 2);
				vegitation[i].scale(7);
			} else {
				vegitation[i] = new Sprite(new Texture("kelpono.2.gif"));
				vegitation[i].setPosition(r.nextFloat() * WORLD_WIDTH, r.nextFloat() * WORLD_HEIGHT / 4 - WORLD_HEIGHT / 2);
				vegitation[i].scale(7);
			}
		}

		Gdx.input.setInputProcessor(new GestureDetector(this));

		keySprite = new Sprite(new Texture("key.gif"));
		keySprite.scale((float) 5);
		chestSprite = new Sprite(new Texture("treasurechest.gif"));
		chestSprite.scale((float) 7);
		NPCSprite = new Sprite(new Texture("Mermaid.png"));
		NPCSprite.scale((float) 5);
		monsterSprite = new Sprite(new Texture("monsterone.png"));

		key = new Key(keySprite.getRegionWidth(), keySprite.getRegionHeight());
		chest = new Chest(chestSprite.getRegionWidth(), chestSprite.getRegionHeight());
		friend = new NPC(NPCSprite.getRegionWidth(), NPCSprite.getRegionHeight());
		monster = new Monster(monsterSprite.getRegionWidth(), monsterSprite.getRegionHeight(), key, chest);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		checkCompass();
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
        for (Sprite aVegitation : vegitation) {
            aVegitation.draw(batch);
        }
		chestSprite.draw(batch);
		NPCSprite.draw(batch);
		monsterSprite.draw(batch);
		batch.end();
	}

	public void checkCompass(){
		float newAzimuth = Gdx.input.getAzimuth();
		float stableAzimuth = stabilizer.stabilizeAzimuth(newAzimuth);
		deltaAzimuth = (stableAzimuth - lastAzimuth);
		if (numChecksSinceLastPrint++ > 200) {
			System.out.println("checking stable/new/last: " + stableAzimuth + " " + newAzimuth + " " + lastAzimuth);
			numChecksSinceLastPrint = 0;
		}
		if (Math.abs(deltaAzimuth) > COMPASS_DEBOUNCE) { //debounces compass changes
			System.out.println("moving: " + stableAzimuth + " " + lastAzimuth);
			moveCamera(-deltaAzimuth * compassFactor);
			lastAzimuth = stableAzimuth;
		}
	}

	public void moveCamera(float deltaX) {
		camera.translate(deltaX, 0);
		cameraX += deltaX;
		if(cameraX < -BGImg[0].getWidth() / 2) {
			camera.translate(WORLD_WIDTH + BGImg[0].getWidth(), 0);
			cameraX += WORLD_WIDTH + BGImg[0].getWidth();
		} else if(cameraX > WORLD_WIDTH + BGImg[5].getWidth() / 2){
			camera.translate(-WORLD_WIDTH - BGImg[0].getWidth(), 0);
			cameraX += -WORLD_WIDTH - BGImg[0].getWidth();
		}
		camera.update();
	}

	@Override
	public void dispose () {
		batch.dispose();
        for (Texture aBGImg : BGImg) {
            aBGImg.dispose();
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
		moveCamera(-deltaX * 2);
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
