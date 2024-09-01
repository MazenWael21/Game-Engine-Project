package org.util;

public class Time {
    private static float timeStarted =  System.nanoTime();
    public static float getTimeInSec(){return (float) ((timeStarted - System.nanoTime())-1E-9);}
    public static float getTimeInMills(){return (timeStarted - System.nanoTime());}

}
