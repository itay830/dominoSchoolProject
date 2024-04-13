package classesPackage;

public class Vec2 {
  public int x;
  public int y;

  public Vec2() {
    x = 0;
    y = 0;
  }

  public Vec2(int _x, int _y) {
    x = _x;
    y = _y;
  }

  public boolean compareHeavy(Vec2 v2) {
    return this.x == v2.x && this.y == v2.y;
  }

  public boolean compareLight(Vec2 v2) {
    return (this.x == v2.x && this.y == v2.y) || (this.x == v2.y && this.y == v2.x);
  }

  public void flip() {
    int sideXTemp = x;
    x = y;
    y = sideXTemp;
  }
}
