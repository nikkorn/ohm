package com.dumbpug.ohm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dumbpug.ohm.Ohm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new Ohm(), new LwjglApplicationConfiguration());
	}
}
