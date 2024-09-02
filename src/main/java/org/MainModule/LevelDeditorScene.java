package org.MainModule;

import java.awt.event.KeyEvent;

import org.Colors.Colors;
import org.KeyEvents.KeyListener;

public class LevelDeditorScene extends Scene {
     private boolean changingScene =false;
     private float timeToChangeTheScene = 2.0f;
    @Override
    public void update(float deltaTime) {
        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){

            changingScene= true;
        }

         if(changingScene && timeToChangeTheScene > 0){
            timeToChangeTheScene -= deltaTime;
             Colors.setRed(deltaTime * 5.0f);
             Colors.setGreen(deltaTime - 5.0f);
             Colors.setRed(deltaTime - 5.0f);


         }else if(changingScene){
            Window.changeScene(1);

         }

    }

}
