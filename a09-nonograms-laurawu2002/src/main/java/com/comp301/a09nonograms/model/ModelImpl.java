package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final List<Clues> availablePuzzles;
  private int index = 0;
  private Board board;
  private final List<ModelObserver> modelObserverList = new ArrayList<>();

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new IllegalArgumentException();
    }
    this.availablePuzzles = new ArrayList<>(clues);
    board =
        new BoardImpl(
            availablePuzzles.get(index).getHeight(), availablePuzzles.get(index).getWidth());
  }

  @Override
  public boolean isShaded(int row, int col) {
    return board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    board.toggleCellShaded(row, col);
    update();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    board.toggleCellEliminated(row, col);
    update();
  }

  @Override
  public void clear() {
    board.clear();
    update();
  }

  @Override
  public int getWidth() {
    return availablePuzzles.get(index).getWidth();
  }

  @Override
  public int getHeight() {
    return availablePuzzles.get(index).getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return availablePuzzles.get(this.index).getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return availablePuzzles.get(this.index).getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return availablePuzzles.get(index).getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return availablePuzzles.get(index).getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return availablePuzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    return index;
  }

  @Override
  public void setPuzzleIndex(int index) {
    this.index = index;
    board =
        new BoardImpl(
            availablePuzzles.get(index).getHeight(), availablePuzzles.get(index).getWidth());
    update();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    modelObserverList.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    modelObserverList.remove(observer);
  }

  @Override
  public boolean isSolved() {
    int[] colClues;
    int[] rowClues;
    boolean flag = false;
    for (int i = 0; i < getWidth(); i++) {
      colClues = getColClues(i);
      if (isColSolved(i, colClues)) {
        flag = true;
      } else {
        return false;
      }
    }

    for (int i = 0; i < getHeight(); i++) {
      rowClues = getRowClues(i);
      if (isRowSolved(i, rowClues)) {
        flag = true;
      } else {
        return false;
      }
    }
    return flag;
  }

  public boolean isColSolved(int i, int[] clues) {
    boolean flag = false;
    int count = 0;
    int zero = 0;
    List<Integer> col = new ArrayList<>();

    for (int k = 0; k < clues.length; k++) {
      if (clues[k] == 0) {
        zero++;
      }
    }

    for (int j = 0; j < getHeight(); j++) {
      if (!isShaded(j, i)) {
        if (count > 0) {
          col.add(count);
          count = 0;
        }
      } else {
        count++;
      }
    }

    if (count > 0) {
      col.add(count);
    }

    flag = (clues.length == col.size() + zero);
    for (int m = 0; m < clues.length - zero; m++) {
      if (flag == true && (col.get(m) == clues[zero + m])) {
        flag = true;
      } else {
        flag = false;
      }
    }
    return flag;
  }

  public boolean isRowSolved(int i, int[] clues) {
    boolean flag = false;
    int count = 0;
    int zero = 0;
    List<Integer> row = new ArrayList<>();

    for (int k = 0; k < clues.length; k++) {
      if (clues[k] == 0) {
        zero++;
      }
    }

    for (int j = 0; j < getWidth(); j++) {
      if (!isShaded(i, j)) {
        if (count > 0) {
          row.add(count);
          count = 0;
        }
      } else {
        count++;
      }
    }

    if (count > 0) {
      row.add(count);
    }

    flag = (clues.length == row.size() + zero);
    for (int m = 0; m < clues.length - zero; m++) {
      if (flag == true && (row.get(m) == clues[zero + m])) {
        flag = true;
      } else {
        flag = false;
      }
    }
    return flag;
  }

  private void update() {
    for (ModelObserver m : modelObserverList) {
      m.update(this);
    }
  }
}
