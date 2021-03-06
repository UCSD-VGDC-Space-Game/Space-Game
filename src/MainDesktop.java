import javax.swing.*;
import java.awt.*;

public class MainDesktop {

  final static Dimension WINDOW_DIMENSION = new Dimension(1280, 720);

  public static void main (String[] args) {
    JFrame mainFrame = new JFrame();
    MainCanvas mainCanvas = new MainCanvas(mainFrame);
    mainCanvas.setSize(WINDOW_DIMENSION);
    mainCanvas.setPreferredSize(WINDOW_DIMENSION);
    mainFrame.add(mainCanvas);
    mainFrame.getContentPane().setSize(WINDOW_DIMENSION);
    mainFrame.getContentPane().setPreferredSize(WINDOW_DIMENSION);
    mainFrame.pack();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setTitle(mainCanvas.getGameTitle());
    mainFrame.setVisible(true);
  }

}
