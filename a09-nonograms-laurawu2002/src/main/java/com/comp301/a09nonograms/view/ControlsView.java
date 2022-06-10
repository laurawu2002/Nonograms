package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ControlsView implements FXComponent {
  private final Controller controller;
  private VBox logoContainer = new VBox();

  public ControlsView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    logoContainer = new VBox();
    Label logo = new Label("NONOGRAMS");
    logo.getStyleClass().add("logo");
    logoContainer.getChildren().add(logo);
    logoContainer.setAlignment(Pos.CENTER);
    VBox.setVgrow(logoContainer, Priority.ALWAYS);
    logo.setTextFill(Color.web("#f4c2c2"));
    logo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

    Label instructions =
        new Label("Left click to make it purple. Right click to make it gray(eliminate).");
    instructions.setFont(Font.font("Comic Sans MS"));
    instructions.getStyleClass().add("instructions");
    logoContainer.getChildren().add(instructions);

    HBox buttonContainer = new HBox();
    Button button = new Button("Previous");
    button.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        });
    buttonContainer.getChildren().add(button);

    Button buttonRand = new Button("Random");
    buttonRand.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
        });
    buttonContainer.getChildren().add(buttonRand);

    Button button1 = new Button("Next");
    button1.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });
    buttonContainer.getChildren().add(button1);

    Button button2 = new Button("CLEAR");
    button2.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });
    buttonContainer.getChildren().add(button2);

    buttonContainer.setAlignment(Pos.BASELINE_CENTER);
    logoContainer.getChildren().add(buttonContainer);

    if (controller.isSolved()) {
      update();
    }
    return logoContainer;
  }

  public void update() {
    VBox success = new VBox();
    success.setAlignment(Pos.BASELINE_CENTER);
    Label label = new Label("SUCCESS!");
    VBox.setVgrow(label, Priority.ALWAYS);
    label.setTextFill(Color.web("#A6C1EE"));
    label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    success.getChildren().add(label);
    logoContainer.getChildren().add(success);
  }
}
