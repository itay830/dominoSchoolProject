package classesPackage;

import java.util.*;

public class Game {
  public static Scanner r = new Scanner(System.in);
  public Player p1;
  public Player p2;
  public DominoList dominoRecord;

  public Game() {
    p1 = new Player(getUniqueName("Player 1"), getDeck());
    p2 = new Player(getUniqueName("Player 2"), getDeck());
    if (p1.name == p2.name) { // NO DUPLICATE NAMES :)
      p2.name = "theSecondOne'" + p2.name + "'";
    }
  }

  public DominoList getDeck() {
    DominoList dList = new DominoList(7);
    for (int i = 0; i < 7; i++) {
      boolean legit = false;
      while (!legit) {
        Domino newD = new Domino();
        if (dList.findLight(newD) == -1) {
          dList.add(newD);
          legit = true;
        }
      }
    }
    return dList;
  }

  public String getUniqueName(String greetingName) {
    String name = "";
    while (name.length() < 2) {
      System.out.print("Enter your name " + greetingName + ": ");
      name = r.next();
    }
    return name;
  }

}
