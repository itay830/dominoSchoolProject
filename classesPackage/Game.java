package classesPackage;

import java.util.*;

public class Game {
  public static Scanner r = new Scanner(System.in);
  public Player p1;
  public Player p2;
  public DominoMap dominoMap;
  public boolean turn;
  public Vec2 edges;

  public Game() {
    clearConsole();
    edges = new Vec2(-1, -1);
    dominoMap = new DominoMap();
    turn = Math.round(Math.random() * 2) == 0;
    p1 = new Player(getUniqueName("Player 1"), getDeck());
    p2 = new Player(getUniqueName("Player 2"), getDeck());
    if (p1.name == p2.name) { // NO DUPLICATE NAMES :)
      p2.name = "theSecondOne'" + p2.name + "'";
    }
  }

  public void logic() {
    Player p;
    int[] potentialDominoIndexes;
    int indexChoice;
    clearConsole();
    if (turn) {
      p = p1;
    } else {
      p = p2;
    }
    if (edges.x == -1 || edges.y == -1) {
      p.deck.printColumns(new int[] { 0, 1, 2, 3, 4, 5, 6 });
      System.out.print(p.name + "'s first turn!\nEnter the INDEX of your starting DOMINO: ");
      indexChoice = r.nextInt();
      // ADD TO MAP
      p.deck.popAt(indexChoice);
      turn = !turn;
      return;
    }
    potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
    p.deck.printColumns(potentialDominoIndexes);
    System.out.print(p.name + "'s turn. Enter the INDEX of the DOMINO you want to put: ");
    indexChoice = r.nextInt();
    // ADD TO MAP
    p.deck.popAt(indexChoice);

    turn = !turn;
  }

  public void clearConsole() {
    System.out.print("\033[H\033[2J"); // ANSI to clear the console
    System.out.flush(); // Empting the buffer
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

  public boolean addToDeckUnique() {

    return true;
  }

  public int[] getPotentialDominoIndexes(DominoList deck) {
    List indexList = new List(7);
    for (int i = 0; i < deck.index; i++) {
      if (deck.findLight(edges.x) != -1 || deck.findLight(edges.y) != -1) {
        indexList.add(i);
      }
    }
    return indexList.toArray();
  }

  public boolean IsWinner() {
    if (p1.deck.index == 0) {
      System.out.println(p1.name + " HAS WON!!!");
      return true;
    }
    if (p2.deck.index == 0) {
      System.out.println(p2.name + " HAS WON!!!");
      return true;
    }
    return false;
  }
}
