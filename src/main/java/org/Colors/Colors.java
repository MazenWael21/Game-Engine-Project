package org.Colors;

public  class Colors {
    //this class must have R G B colors of the current monitor of the game engine and has some sort of
//control over the colors of the monitor and transperancy.
    private static float red, green, blue;

    public static float getRed() {
        return red;
    }

    public static void setRed(float red) {
        Colors.red = red;
    }

    public static float getGreen() {
        return green;
    }

    public static void setGreen(float green) {
        Colors.green = green;
    }

    public static float getBlue() {
        return blue;
    }

    public static void setBlue(float blue) {
        Colors.blue = blue;
    }


}
