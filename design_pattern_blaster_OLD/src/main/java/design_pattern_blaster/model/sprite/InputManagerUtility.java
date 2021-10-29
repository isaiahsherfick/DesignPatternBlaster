/**
 * @Author: Maazin Jawad
 * @CreationDate: 10/27/2021
 * @Editors:
 * @EditedDate:
 **/
package design_pattern_blaster.model.sprite;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.UnaryOperator;



/**
 * Utility class for reading key input and creating a response to it
 */
public final class InputManagerUtility {

    private static Scene scene;

    private InputManagerUtility(){

    }

    public static void setScene(Scene gameScene){
       scene = gameScene;
    }

    public static void respondToInput(EventType<? extends InputEvent> eventType, EventHandler<Event> eventHandler){
        scene.addEventHandler(eventType, eventHandler);
    }





}
