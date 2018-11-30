import java.awt.*;
import java.awt.image.ImageObserver;

public class Player extends Drawable {

  public Player(int x, int y, Image asset){
    super(x, y, asset);
  }

  @Override
  public void draw(Graphics g, ImageObserver observer) {
    g.drawRect(super.getX(), super.getY(), 50, 50);
  }

  @Override
  public int getWidth (ImageObserver observer) {
    return 50;
  }

  @Override
  public int getHeight(ImageObserver observer) {
    return 50;
  }

  public void move(Point point, int width, int height) {
    // Ensure the player stays within the window
    if (point != null) {
      if (point.getX() < 0) {
        point.setLocation(0, point.getY());
      }
      if (point.getY() < 0) {
        point.setLocation(point.getX(), 0);
      }
      if (point.getX() >= 0 && point.getY() >= 0) {
        if (point.getX() + getWidth(null) < width && point.getY() + getHeight(null) < height) {
          setLocation(point);
        } else if (point.getX() + getWidth(null) < width) {
          setLocation(new Point((int) point.getX(), height - getHeight(null)));
        } else if (point.getY() + getHeight(null) < height) {
          setLocation(new Point(width - getWidth(null), (int) point.getY()));
        } else {
          setLocation(new Point(width - getWidth(null), height - getHeight(null)));
        }
      }
    }
  }
}
