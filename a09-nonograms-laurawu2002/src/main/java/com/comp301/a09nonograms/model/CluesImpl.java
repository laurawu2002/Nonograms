package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {
  private final int height;
  private final int width;
  private final int[][] rowClues;
  private final int[][] columnClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues == null || colClues == null) {
      throw new IllegalArgumentException();
    }
    this.height = rowClues.length;
    this.width = colClues.length;
    this.rowClues = rowClues;
    this.columnClues = colClues;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int[] getRowClues(int index) {
    return rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    return columnClues[index];
  }

  @Override
  public int getRowCluesLength() {
    return rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return columnClues[0].length;
  }
}
