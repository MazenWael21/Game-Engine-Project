package org.MainModule;

import java.awt.event.KeyEvent;

import org.Colors.Colors;
import org.KeyEvents.KeyListener;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

public class LevelDeditorScene extends Scene {
     private boolean changingScene =false;
     private float timeToChangeTheScene = 2.0f;
     private String vertexShaderSRC = "";
     private String fragmentShaderSRC = "";

     private int vertexID , fragmentID , shaderProgram;


public void init(){

    vertexID = glCreateShader(GL_VERTEX_SHADER);
    
    glShaderSource(vertexID, vertexShaderSRC);

     int success = glGetShaderi(vertexID,GL_COMPILE_STATUS);
     if(success ==GLFW_FALSE){
        int length = glGetShaderi(vertexID,GL_INFO_LOG_LENGTH);
       System.out.println("ERROR: defualt shader.glsl vertex shader compilation failed");

     }

}

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
