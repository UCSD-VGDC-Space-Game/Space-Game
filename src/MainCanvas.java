import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class MainCanvas extends Canvas implements KeyListener, Runnable {

  private static final int FRAME_DELAY = 33;

  private String gameTitle;
  private Thread runThread;
  private ArrayList<Integer> pressedKeys;

  public MainCanvas(JFrame gameWindow) {
    super();
    gameTitle = "Space Game";
    pressedKeys = new ArrayList<Integer>();
    this.addKeyListener(this);
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

    g.drawRect(0, 0, 80, 80);
  }

  @Override
  public void run() {
    while (true) {
      try {
        // Loop through pressed keys to handle pressed
        Integer[] currentlyPressedKeys = pressedKeys.toArray(new Integer[pressedKeys.size()]); // Convert to array to avoid IndexOutOfBoundsException
        for (int key : currentlyPressedKeys) {
          System.out.println(key);
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
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
}
