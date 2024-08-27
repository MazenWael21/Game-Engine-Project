package org.KeyEvents;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[GLFW_KEY_LAST + 1];
    private boolean keyBeginPress[] = new boolean[GLFW_KEY_LAST + 1];

    private KeyListener() {}

    public static void endFrame() {
        Arrays.fill(getKeyListenerInstance().keyBeginPress, false);
    }

    public static KeyListener getKeyListenerInstance() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (key <= GLFW_KEY_LAST && key >= 0) {
            if (action == GLFW_PRESS) {
                getKeyListenerInstance().keyPressed[key] = true;
                getKeyListenerInstance().keyBeginPress[key] = true;
            } else if (action == GLFW_RELEASE) {
                getKeyListenerInstance().keyPressed[key] = false;
                getKeyListenerInstance().keyBeginPress[key] = false;
            }
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        if (keyCode <= GLFW_KEY_LAST && keyCode >= 0) {
            return getKeyListenerInstance().keyPressed[keyCode];
        }

        return false;
    }

    public static boolean keyBeginPress(int keyCode) {
        if (keyCode <= GLFW_KEY_LAST && keyCode >= 0) {
            return getKeyListenerInstance().keyBeginPress[keyCode];
        }

        return false;
    }
}