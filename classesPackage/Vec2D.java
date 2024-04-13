package classesPackage;

public class Vec2D {
  public Domino x;
  public Domino y;

  public Vec2D() {
    x = new Domino();
    y = new Domino();
  }

  public Vec2D(Domino dominoX, Domino dominoY) {
    x = dominoX;
    y = dominoY;
  }

  public boolean compareHeavy(Vec2D v2) {
    return this.x == v2.x && this.y == v2.y;
  }

  public boolean compareLight(Vec2D v2) {
    return (this.x == v2.x && this.y == v2.y) || (this.x == v2.y && this.y == v2.x);
  }

  public void flip() {
    Domino sideXTemp = x;
    x = y;
    y = sideXTemp;
  }
  
}
