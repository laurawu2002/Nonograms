package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageView implements FXComponent {
  private final Controller controller;

  public MessageView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox bottom = new VBox();
    bottom.setAlignment(Pos.BOTTOM_CENTER);
    VBox.setVgrow(bottom, Priority.ALWAYS);

    int index = controller.getPuzzleIndex() + 1;
    Label count = new Label("puzzle " + index + " of " + controller.getPuzzleCount());
    count.getStyleClass().add("count");
    HBox.setHgrow(bottom, Priority.ALWAYS);
    count.setTextFill(Color.web("#FF9AA2"));
    count.setFont(Font.font("Verdana", 15));
    bottom.getChildren().add(count);
    return bottom;
  }
}
