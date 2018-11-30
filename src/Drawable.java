import java.awt.*;
import java.awt.image.ImageObserver;

abstract class Drawable {
  private int x;
  private int y;
  private Image asset;

  public Drawable(int x, int y, Image asset) {
    this.x = x;
    this.y = y;
    this.asset = asset;
  }

  public void setLocation(Point location) {
    this.x = location.x;
    this.y = location.y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth(ImageObserver observer) {
    return asset.getWidth(observer);
  }

  public int getHeight(ImageObserver observer) {
    return asset.getHeight(observer);
  }

  public void draw(Graphics g, ImageObserver observer) {
    g.drawImage(asset, x, y, observer);
  }
}