package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AppLauncher extends Application {

  @Override
  public void start(Stage stage) {
    List<Clues> clues = PuzzleLibrary.create();
    Model model = new ModelImpl(clues);
    Controller controller = new ControllerImpl(model);

    // View
    PuzzleView view = new PuzzleView(controller);

    // Scene
    Scene scene = new Scene(view.render(), 420, 600);
    stage.setScene(scene);

    // Add Observer
    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });

    stage.setTitle("Nonograms");
    stage.setMinHeight(450);
    stage.setMinWidth(400);

    stage.show();
  }
}
