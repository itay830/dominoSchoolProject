package classesPackage;

public class Domino {
  private Vec2 sides;

  public Domino() {
    sides = new Vec2((int) (Math.random() * 7), (int) (Math.random() * 7));
  }

  public Domino(int x, int y) {
    sides = new Vec2(x, y);
  }

  public void setSidesX(int num) {
    sides.x = num;
  }

  public void setSidesY(int num) {
    sides.y = num;
  }

  public int getSidesX() {
    return sides.x;
  }
  
  public int getSidesY() {
    return sides.y;
  }

  public Vec2 getSides() {
    return sides;
  }

  public void print() {
    System.out.println("SIDE X: " + sides.x + ", SIDE Y: " + sides.y);
  }
  
  public static boolean IsCompatibleBoolean(Domino d1, Domino d2) {
    return d1.sides.compareLight(d2.sides);
  }

  public static String IsCompatibleString(Domino d1, Domino d2, boolean left, boolean right) {
    /*
      Takes : Domino for comparision
      Outputs : TODO: Add explanation
    */
    if (d1.sides.compareLight(d2.sides) && right && left) {
      // Two Possible connections
      return "*";
    }

    if (d1.sides.x == d2.sides.x && left) {
      return "XX";
    }

    if (d1.sides.y == d2.sides.y && right) {
      return "YY";
    }

    if (d1.sides.x == d2.sides.y && right) {
      return "XY";
    }

    if (d1.sides.y == d2.sides.x && left) {
      return "YX";
    }

    return "-";

  }
}
