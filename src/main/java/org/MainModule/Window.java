package org.MainModule;

import org.KeyEvents.KeyListener;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static java.sql.Types.NULL;
import static org.MouseEvents.MouseListener.mouseEventsCallBacks;
import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11C.glClearColor;


public class Window {
    private static Window window;
    private static int windowWidth , windowHight;
    private final static  int  defaultWindowWidth = 800 , defaultWidowHight=600 ;
    private static String windowTitle;
    private long glfwWindowAdress;
    int swapInterval= 1;   // Enabling V-SYNC
//singleton impelmentation.
private  Window(){
    this.windowWidth = defaultWindowWidth;
    this.windowHight = defaultWidowHight;
}
private Window(int windowWidth,int windowHight){
      setWindowTitle(windowTitle);
      setWindowHight(windowHight);
      setWindowWidth(windowWidth);
}
    private Window(int windowWidth,int windowHight,String windowTitle){
        setWindowTitle(windowTitle);
        setWindowHight(windowHight);
        setWindowWidth(windowWidth);
    }
    private Window(String windowTitle){
        setWindowTitle(windowTitle);
    }

    public static Window getWindow() {
        return window;
    }

    public static void setWindow(Window window) {
        Window.window = window;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static void setWindowWidth(int windowWidth) {
        Window.windowWidth = windowWidth;
    }

    public static int getWindowHight() {
        return windowHight;
    }

    public static void setWindowHight(int windowHight) {
        Window.windowHight = windowHight;
    }

    public static String getWindowTitle() {
        return windowTitle;
    }

    public static void setWindowTitle(String windowTitle) {
        Window.windowTitle = windowTitle;
    }

    public long getGlfwWindowAdress() {
        return glfwWindowAdress;
    }

    public void setGlfwWindowAdress(long glfwWindowAdress) {
        this.glfwWindowAdress = glfwWindowAdress;
    }

    public static Window getWindowInstance(int windowWidth, int windowHight, String windowTitle) {

        if(window==null){

            window=new Window(windowWidth,windowHight,windowTitle);
        }
        return window;

    }

    public int getSwapInterval() {
        return swapInterval;
    }

    public void setSwapInterval(int swapInterval) {
        this.swapInterval = swapInterval;
    }

    public static Window getWindowInstance(){
    if(window==null){
        window=new Window();
    }
    return window;
}

    public static Window getWindowInstance(int windowWidth,int windowHight){

        if(window==null){

            window=new Window(windowWidth,windowHight);
        }
        return window;
    }

    public static Window getWindowInstance(String windowTitle){

        if(window==null){
            setWindowWidth(defaultWindowWidth);
            setWindowHight(defaultWidowHight);
            window=new Window(windowTitle);

        }
        return window;
    }
    public void runWindow() {
        System.out.println("LWGL Version:"+ Version.getVersion()+".");
        System.out.println("Window Title:" +" "+getWindowTitle());


        init();
        loop();
    }

    private void terminatingWindow(){
     glfwDestroyWindow(glfwWindowAdress);
     glfwTerminate();
     glfwSetErrorCallback(null).free();
    }

    private void init(){
        //setup error call back.
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit()){
             new IllegalStateException("unable to initialize GLFW.");
        }
        //SIMPLE WINDOW CONFIGURATION THIS CAN BE EDIT OTHER PART OF THE CODE.
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE);

        //intializing window.
       glfwWindowAdress = glfwCreateWindow(this.windowWidth,this.windowWidth,this.windowTitle,NULL,NULL);

       if(glfwWindowAdress ==NULL){
           throw new IllegalStateException("FAILED TO CREATE GLFW WINDOW!");
       }

        mouseEventsCallBacks();//mouse Call Back it should be here a src code but I decide to put it as a sprite method just for more orgranization.
         glfwSetKeyCallback(glfwWindowAdress,KeyListener::keyCallback);
        glfwMakeContextCurrent(glfwWindowAdress);
        glfwSwapInterval(swapInterval);
        OpeningGlfwlWindow();
    }

    private void OpeningGlfwlWindow(){
      glfwShowWindow(glfwWindowAdress);
      GL.createCapabilities();
    }



    private void loop(){
       while(!glfwWindowShouldClose(glfwWindowAdress)){
           glfwPollEvents();
           glClearColor(1.0f,0.0f,0.0f,1.0f);
           glClear(GL_COLOR_BUFFER_BIT);
           glfwSwapBuffers(glfwWindowAdress);

           if(KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
               System.out.println("space pressed!!");
           }
       }
        terminatingWindow();
    }

    public static void main(String[] args) {
        //testing the window and it's functionality.
        System.out.println("Testing the GLFW Lib");
        Window window = org.MainModule.Window.getWindowInstance("Title");
        window.runWindow();


    }


}






