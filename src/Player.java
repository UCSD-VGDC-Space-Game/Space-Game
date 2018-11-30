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
}
