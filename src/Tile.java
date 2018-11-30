import java.awt.*;
import java.awt.image.ImageObserver;

class Tile extends Drawable {
  
  boolean passThrough;

  Tile(int _xPos, int _yPos, Image _asset, boolean _passThrough) {
    super(_xPos, _yPos, _asset);

    passThrough = _passThrough;
  }
}
