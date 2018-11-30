import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class MainCanvas extends Canvas implements KeyListener, Runnable {

  private static final int FRAME_DELAY = 33;
  private static final int SPEED = 5;

  private String gameTitle;
  private Thread runThread;
  private ArrayList<Integer> pressedKeys;
  Player player;
  Grid grid;

  public MainCanvas(JFrame gameWindow) {
    super();
    gameTitle = "Space Game";
    pressedKeys = new ArrayList<Integer>();
    this.addKeyListener(this);
    player = new Player(0, 0, null);
    grid = new Grid(this);
  }

  public String getGameTitle() {
    return gameTitle;
  }

  @Override
  public void update(Graphics g) {
    // Set up double buffering
    Graphics doubleBufferGraphics;
    BufferedImage doubleBuffer;
    Dimension d = this.getSize();
    doubleBuffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
    doubleBufferGraphics = doubleBuffer.getGraphics();
    doubleBufferGraphics.setColor(this.getBackground());
    doubleBufferGraphics.fillRect(0, 0, d.width, d.height);
    doubleBufferGraphics.setColor(this.getForeground());
    paint(doubleBufferGraphics);

    // Flip
    g.drawImage(doubleBuffer, 0, 0, this);
  }


  public void paint(Graphics g) {
    if (runThread == null) {
      // Start the main thread if it hasn't already and add a KeyListener
      this.addKeyListener(this);
      runThread = new Thread(this);
      runThread.start();
    }
    player.draw(g, null);
  }

  @Override
  public void run() {
    while (true) {
      try {
        // Loop through pressed keys to handle pressed
        Integer[] currentlyPressedKeys = pressedKeys.toArray(new Integer[pressedKeys.size()]); // Convert to array to avoid IndexOutOfBoundsException
        for (int key : currentlyPressedKeys) {
          Point future = null;
          switch (key){
            case KeyEvent.VK_W:
              future = new Point(player.getX(), player.getY() - SPEED);
              break;
            case KeyEvent.VK_A:
              future = new Point(player.getX() - SPEED, player.getY());
              break;
            case KeyEvent.VK_S:
              future = new Point(player.getX(), player.getY() + SPEED);
              break;
            case KeyEvent.VK_D:
              future = new Point(player.getX() + SPEED, player.getY());
              break;
          }
          player.move(future, this.getWidth(), this.getHeight());
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      repaint();
      // Attempt to sleep the thread
      try {
        Thread.sleep(FRAME_DELAY);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (!pressedKeys.contains(e.getKeyCode())) {
      pressedKeys.add(e.getKeyCode());
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (pressedKeys.contains(e.getKeyCode())) {
      pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode()));
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  public int getCanvasHeight() {
    return this.getHeight();
  }

  public int getCanvasWidth() {
    return this.getWidth();
  }
}
