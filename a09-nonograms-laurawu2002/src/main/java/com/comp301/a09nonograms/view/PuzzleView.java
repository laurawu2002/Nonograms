package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.util.List;

public class PuzzleView implements FXComponent {
  private final Controller controller;

  public PuzzleView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    List<Clues> clues = PuzzleLibrary.create();
    Model model = new ModelImpl(clues);

    VBox layout = new VBox();
    // Controls view
    ControlsView controlsView = new ControlsView(controller);
    layout.getChildren().add(controlsView.render());

    // Shows the grid
    GridView gridView = new GridView(controller);
    layout.getChildren().add(gridView.render());

    // Shows the index and position
    MessageView messageView = new MessageView(controller);
    layout.getChildren().add(messageView.render());

    return layout;
  }
}
