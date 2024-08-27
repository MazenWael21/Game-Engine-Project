package org.MouseEvents;

import org.MainModule.Window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

public class MouseListener {
   private static MouseListener mouseListener;
   private double scrollX ,scrollY;
   private double xPosition , yPosition , lastXPosition, lastYPosition;
   private boolean mouseButtonPress[] = new boolean[3];
   private boolean isDragging;

   private MouseListener(){
       this.scrollX =0.0;
       this.scrollY = 0.0;
       this.lastXPosition = 0.0;
       this.lastYPosition =0.0;
       this.xPosition =0.0;
       this.yPosition =0.0;
   }

   public static MouseListener getMouseListenerInstance(){
       if(MouseListener.mouseListener==null){
           MouseListener.mouseListener = new MouseListener();
       }
       return mouseListener;
   }

    public static MouseListener getMouseListener() {
        return mouseListener;
    }

    public static void setMouseListener(MouseListener mouseListener) {
        MouseListener.mouseListener = mouseListener;
    }

    public double getScrollX() {
        return scrollX;
    }

    public void setScrollX(double scrollX) {
        this.scrollX = scrollX;
    }

    public double getScrollY() {
        return scrollY;
    }

    public void setScrollY(double scrollY) {
        this.scrollY = scrollY;
    }

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getLastXPosition() {
        return lastXPosition;
    }

    public void setLastXPosition(double lastXPosition) {
        this.lastXPosition = lastXPosition;
    }

    public double getLastYPosition() {
        return lastYPosition;
    }

    public void setLastYPosition(double lastYPosition) {
        this.lastYPosition = lastYPosition;
    }

    public boolean[] getMouseButtonPress() {
        return mouseButtonPress;
    }

    public void setMouseButtonPress(boolean[] mouseButtonPress) {
        this.mouseButtonPress = mouseButtonPress;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }


    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public static void mousePosCallBack(long WindowMemoryAdress, double xPosition, double yPosition){
       getMouseListenerInstance().lastXPosition = getMouseListener().xPosition;
       getMouseListenerInstance().lastYPosition = getMouseListener().yPosition;
       getMouseListenerInstance().xPosition = xPosition;
       getMouseListenerInstance().yPosition = yPosition;
       getMouseListenerInstance().isDragging =isSomethingDragged();
    }
    private static boolean isSomethingDragged(){
       return getMouseListenerInstance().mouseButtonPress[0] ||  getMouseListenerInstance().mouseButtonPress[1] || getMouseListenerInstance().mouseButtonPress[2];
    }

   public static void mouseButtonCallBacks(long windowMemoryAdress,int action, int button ,int mods){

        if(GLFW_PRESS==action) {

            if(isButtonInTheScopeOFButtonArray(button)) {
                getMouseListenerInstance().getMouseButtonPress()[button] = true;
            }

        }
        else if(GLFW_RELEASE==action){
            if(isButtonInTheScopeOFButtonArray(button)) {
                getMouseListenerInstance().getMouseButtonPress()[button] = false;
            }
        }
   }

   public static void mouseScrollCallBack(long windowMemoryAdress,double xffset,double yOffset){
       getMouseListenerInstance().scrollX = xffset;
       getMouseListenerInstance().scrollY = yOffset;

   }

   public static void endFrame(){
      getMouseListenerInstance().setScrollX(0.0);
      getMouseListenerInstance().setScrollY(0.0);
      getMouseListenerInstance().setLastXPosition(getMouseListenerInstance().getXPosition());
      getMouseListener().setLastYPosition(getMouseListenerInstance().getLastYPosition());

   }
  public static float getX(){
       return (float)getMouseListenerInstance().getXPosition();
  }
  public static float getY(){
       return (float)getMouseListenerInstance().getYPosition();
  }
 public static float getDX(){
       return (float) ((float) getMouseListenerInstance().getLastXPosition()-getMouseListenerInstance().getXPosition());
 }
    public static float getDY(){
        return (float) ((float) getMouseListenerInstance().getLastYPosition()-getMouseListenerInstance().getYPosition());
    }

    private static boolean isButtonInTheScopeOFButtonArray(int button){
         return (button<getMouseListenerInstance().mouseButtonPress.length);

    }
   public boolean isMouseButtonDown(int button){
       if(isButtonInTheScopeOFButtonArray(button))
     return getMouseListenerInstance().mouseButtonPress[button];
     else return false;
   }

    public double getYPosition(MouseListener mouseListener) {
        return mouseListener.yPosition;
    }

    public static void mouseEventsCallBacks(){
        glfwSetMouseButtonCallback(Window.getWindowInstance().getGlfwWindowAdress(), MouseListener::mouseButtonCallBacks);
        glfwSetCursorPosCallback(Window.getWindowInstance().getGlfwWindowAdress(),MouseListener::mousePosCallBack);
        glfwSetScrollCallback(Window.getWindowInstance().getGlfwWindowAdress(),MouseListener::mouseScrollCallBack);
    }

}
