import java.awt.*;

class Grid {
  
  private final static int NUM_ROWS = 36;
  private final static int NUM_COLUMNS = 48;
  private static Tile grid[][] = new Tile[NUM_ROWS][NUM_COLUMNS];
  public static int Y_DELTA;
  public static int X_DELTA;
  public static int seed = (int)(Math.random()*(0x7fffffff));
  private static MainCanvas mainCanvas;

  public Grid(MainCanvas _mainCanvas) {
    mainCanvas = _mainCanvas;
    Y_DELTA = mainCanvas.getCanvasHeight()/NUM_ROWS;
    X_DELTA = mainCanvas.getCanvasWidth()/NUM_COLUMNS;

    GridInit();
  }

  public static void GridInit() {
    Reset();
    for (int row = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLUMNS && col*X_DELTA <= mainCanvas.getCanvasWidth(); col++) {
        grid[row][col] = new Tile(mainCanvas.getCanvasWidth(),
          mainCanvas.getCanvasHeight(), null, true);
        System.out.println(row + " " + col);
      }
    }
  }

  public static void Reset() {
    for (int row = 0; row<NUM_ROWS; row++) {
      for (int col = 0; col<NUM_COLUMNS; col++) {
        grid[row][col] = null;
      }
    }
  }
}
