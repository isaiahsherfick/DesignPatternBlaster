package design_pattern_blaster.model;

import design_pattern_blaster.interfaces.Observable;
import design_pattern_blaster.interfaces.Observer;
import javafx.animation.AnimationTimer;

import java.io.File;
import java.util.ArrayList;

public class GameEngine extends AnimationTimer
{

    private float totalTime = 0;
    private long lastFrameTimeNanos = 0;
    private double secondsSincePreviousFrame = 0; // AKA TimeDelta
    private boolean isPaused = false;
    private boolean isPauseScheduled = false;
    private boolean isPlayScheduled = false;

    public GameEngine() {

    }

    /************************************
     *
     * Getters/Setters
     *
     ************************************/

    /************************************
     *
     * Game Loop Functionality
     *
     ************************************/

    public boolean isPaused() {
        return isPaused;
    }

    public void pause() {
        if (!isPaused) {
            isPlayScheduled = false;
            isPauseScheduled = true;
        }
    }

    public void play() {
        if (isPaused) {
            super.start();
            isPlayScheduled = true;
            isPauseScheduled = false;
        }
    }

    @Override
    public void start() {
        super.start();
        isPlayScheduled = false;
        isPauseScheduled = true;
        isPaused = true;
    }

    @Override
    public void stop() {
        // Stop the game loop
        super.stop();

        // Restart pause/play flags
        isPaused = false;
        isPauseScheduled = false;
        isPlayScheduled = false;

    }

    @Override
    public void handle(long now) {
        if (isPauseScheduled) {
            isPaused = true;
            isPauseScheduled = false;
            secondsSincePreviousFrame = (now - lastFrameTimeNanos) / 1e9;
        }

        if (isPlayScheduled) {
            lastFrameTimeNanos = now;
            isPaused = false;
            isPlayScheduled = false;
        }

        if (!isPaused) {
            secondsSincePreviousFrame = (now - lastFrameTimeNanos) / 1e9;
            totalTime += secondsSincePreviousFrame;
            lastFrameTimeNanos = now;
        }
    }

    public float getTotalTime() {
        return totalTime;
    }

    public double getSecondsSincePreviousFrame() {
        return secondsSincePreviousFrame;
    }




}
