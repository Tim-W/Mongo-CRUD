package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.SwipeEvent;

public class MyButton extends Button {

    int counter = 0;

    public MyButton() {
        super();

        this.setOnSwipeDown(new EventHandler<SwipeEvent>() {
            @Override
            public void handle(SwipeEvent event) {
                setText("swiped");
            }
        });
    }
}
