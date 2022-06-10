package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GridView implements FXComponent {
  private final Controller controller;

  public GridView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane board = new GridPane();
    board.setStyle("-fx-background-color: #EDF6E5;");

    GridPane user = new GridPane();
    GridPane.setHgrow(user, Priority.ALWAYS);
    user.setAlignment(Pos.CENTER_LEFT);
    user.getStyleClass().add("board");

    VBox cluesRow = new VBox();
    VBox.setVgrow(board, Priority.ALWAYS);
    cluesRow.setStyle("-fx-background-color:#f4c2c2;");
    cluesRow.setPadding(new Insets(2, 0, 2, 75));
    cluesRow.setSpacing(0.75);

    int height = controller.getClues().getHeight();
    int width = controller.getClues().getWidth();

    for (int i = 0; i < height; i++) {
      cluesRow.getChildren().add(makeHBox(i));
    }
    board.add(cluesRow, 0, 1);

    HBox cluesCol = new HBox();
    HBox.setHgrow(board, Priority.ALWAYS);
    cluesCol.setAlignment(Pos.TOP_LEFT);
    cluesCol.setSpacing(0.75);

    cluesCol.setStyle("-fx-background-color:#f4c2c2;");
    for (int i = 0; i < width; i++) {
      cluesCol.getChildren().add(makeVBox(i));
    }
    board.add(cluesCol, 1, 0);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Rectangle tile = new Rectangle(20, 20);
        GridPane.setRowIndex(tile, i);
        GridPane.setColumnIndex(tile, j);
        tile.setStroke(Color.BLACK);
        tile.setFill(Color.WHITE);

        int rowIndex = GridPane.getRowIndex(tile);
        int colIndex = GridPane.getColumnIndex(tile);

        if (controller.isShaded(rowIndex, colIndex)) {
          tile.setFill(Color.web("#E0BBE4"));
        }
        if (controller.isEliminated(rowIndex, colIndex)) {
          tile.setFill(Color.GRAY);
        }

        user.add(new StackPane(tile), j, i);
        user.getChildren().addAll(tile);

        tile.setOnMouseClicked(
            event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                if (!controller.isShaded(rowIndex, colIndex)
                    && !controller.isEliminated(rowIndex, colIndex)) {
                  controller.toggleShaded(rowIndex, colIndex);
                } else if (controller.isEliminated(rowIndex, colIndex)) {
                  controller.toggleEliminated(rowIndex, colIndex);
                } else if (controller.isShaded(rowIndex, colIndex)) {
                  controller.toggleShaded(rowIndex, colIndex);
                }
              } else if (event.getButton() == MouseButton.SECONDARY) {
                if (!controller.isEliminated(rowIndex, colIndex)
                    && !controller.isShaded(rowIndex, colIndex)) {
                  controller.toggleEliminated(rowIndex, colIndex);
                } else if (controller.isShaded(rowIndex, colIndex)) {
                  controller.toggleShaded(rowIndex, colIndex);
                } else if (controller.isEliminated(rowIndex, colIndex)) {
                  controller.toggleEliminated(rowIndex, colIndex);
                }
              }
            });
      }
    }
    board.add(user, 1, 1);
    return board;
  }

  private HBox makeHBox(int index) {
    HBox clue = new HBox();
    int length = controller.getClues().getRowCluesLength();
    Label label;
    int i = 0;
    while (i < length) {
      if (controller.getClues().getRowClues(index)[i] != 0) {
        String value = String.valueOf(controller.getClues().getRowClues(index)[i]);
        label = new Label(value);
      } else {
        label = new Label(" ");
      }
      label.setPrefSize(20, 20);
      label.setFont(Font.font("Verdana"));
      label.setAlignment(Pos.CENTER);
      clue.getChildren().add(label);
      i++;
    }
    return clue;
  }

  private VBox makeVBox(int index) {
    VBox clue = new VBox();
    Label label;
    int length = controller.getClues().getColCluesLength();
    int i = 0;
    while (i < length) {
      if (controller.getClues().getColClues(index)[i] != 0) {
        String value = String.valueOf(controller.getClues().getColClues(index)[i]);
        label = new Label(value);
      } else {
        label = new Label(" ");
      }
      label.setPrefSize(20, 20);
      label.setFont(Font.font("Verdana"));
      label.setAlignment(Pos.CENTER);
      clue.getChildren().add(label);
      i++;
    }
    return clue;
  }
}
