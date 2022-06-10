package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {
  private boolean[][] shade;
  private boolean[][] elim;

  public BoardImpl(int row, int column) {
    this.shade = new boolean[row][column];
    this.elim = new boolean[row][column];
  }

  @Override
  public boolean isShaded(int row, int col) {
    return shade[row][col] && !elim[row][col];
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return elim[row][col] && !shade[row][col];
  }

  @Override
  public boolean isSpace(int row, int col) {
    return !isShaded(row, col) && !isEliminated(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (isShaded(row, col)) {
      shade[row][col] = false;
    } else if (!isShaded(row, col)) {
      shade[row][col] = true;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (isEliminated(row, col)) {
      elim[row][col] = false;
    } else if (!isEliminated(row, col)) {
      elim[row][col] = true;
    }
  }

  @Override
  public void clear() {
    this.shade = new boolean[shade.length][shade[0].length];
    this.elim = new boolean[shade.length][shade[0].length];
  }
}
