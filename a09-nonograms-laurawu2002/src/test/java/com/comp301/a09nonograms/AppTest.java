package com.comp301.a09nonograms;

import static org.junit.Assert.assertTrue;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void testisSolved() {
    List<Clues> clueList = new ArrayList<>();
    Clues clues =
        new CluesImpl(
            new int[][] {
              new int[] {1, 1, 1},
              new int[] {0, 1, 3},
              new int[] {0, 0, 2},
              new int[] {0, 0, 2},
              new int[] {0, 0, 2},
            },
            new int[][] {
              new int[] {2, 2},
              new int[] {0, 2},
              new int[] {0, 2},
              new int[] {0, 2},
              new int[] {0, 3},
            });
    clueList.add(clues);
    ModelImpl model = new ModelImpl(clueList);
    model.toggleCellShaded(0, 0);
    model.toggleCellShaded(1, 0);
    // model.toggleCellShaded(2, 0);
    model.toggleCellShaded(3, 0);
    model.toggleCellShaded(4, 0);
    int[] check = new int[] {2, 2};

    assertTrue(model.isColSolved(0, check));
  }
}
