package org.iosdevcamp.mermadia.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.iosdevcamp.mermadia.MermaniaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Mermania";
		// Set width/height to 2/5 iPhone 6s Plus size
        config.resizable = false;
		config.width = 1080*2/5;
		config.height = 1920*2/5;

		new LwjglApplication(new MermaniaGame(), config);
	}
}
