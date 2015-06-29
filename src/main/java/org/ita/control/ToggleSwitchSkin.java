package org.ita.control;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ToggleSwitchSkin extends SkinBase<ToggleSwitch> implements Skin<ToggleSwitch> {

    private static final String ON = "ON";
    private static final String OFF   = "OFF";

    private HBox container;
    private final Button button;

    // ******************** Constructors **************************************
    protected ToggleSwitchSkin(ToggleSwitch control) {
        super(control);

        container = new HBox();
        button = new Button();

        container.getChildren().add(button);
        getChildren().addAll(container);

        // Add Event Listeners
        button.setOnMouseReleased(event -> mousePressedOnToggleSwitch(control));
    }

    private void mousePressedOnToggleSwitch(ToggleSwitch toggleSwitch) {
        toggleSwitch.setSelected(!toggleSwitch.isSelected());
    }

    private void selectedStateChanged() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(100), button);
        double containerArea = snapSize(container.prefWidth(-1));
        double buttonArea = snapSize(button.prefWidth(-1));

        if (!getSkinnable().isSelected())
            transition.setByX(-(containerArea - buttonArea));
        else {
            transition.setByX(containerArea - buttonArea);
        }
        transition.setCycleCount(1);
        transition.play();
    }
}
