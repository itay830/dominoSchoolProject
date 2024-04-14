package classesPackage;

import java.util.*;

public class Game {
  public static Scanner r = new Scanner(System.in);
  public Player p1;
  public Player p2;
  public DominoMap dominoMap;
  public boolean turn;

  public Game() {
    clearConsole();
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
    Domino chosenDomino;
    clearConsole();
    if (turn) {
      p = p1;
    } else {
      p = p2;
    }
    if (dominoMap.edges.getSidesX() == -1 || dominoMap.edges.getSidesY() == -1) {
      sleep(500);
      p.deck.printColumns(new int[] { 0, 1, 2, 3, 4, 5, 6 });
      sleep(500);
      System.out.print(p.name + "'s first turn!\nEnter the INDEX of your starting DOMINO: ");
      indexChoice = r.nextInt();
      dominoMap.add(p.deck.popAt(indexChoice));
      turn = !turn;
      return;
    }
    potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
    if (potentialDominoIndexes.length != 0) {
      p.deck.printColumns(potentialDominoIndexes);
    } else {
      // while (potentialDominoIndexes.length == 0 && p.deck.index < 7) {
      //   addToDeckUnique(p.deck);
      //   potentialDominoIndexes = getPotentialDominoIndexes(p.deck);
      // }
      // if (p.deck.index == 7) {
      //   turn = !turn;
      //   return;
      // }
      addToDeckUnique(p.deck);
      sleep(2000);
      System.out.println(Colors.RED + "You have no possible connection. +1 DOMINO" + Colors.RESET);
      turn = !turn;
      return;
    }
    sleep(500);
    dominoMap.print();
    sleep(300);
    do {
      System.out.print(p.name + "'s turn. Enter the INDEX of the DOMINO you want to put: ");
      indexChoice = r.nextInt();
      chosenDomino = p.deck.getAt(indexChoice);
    } while(!dominoMap.add(chosenDomino));
    p.deck.popAt(indexChoice);
    turn = !turn;
  }

  public void sleep(int mls) {
    try {
      Thread.sleep(mls);
    } catch(InterruptedException e) {
      System.out.println("Interupted!");
    }
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

  public void addToDeckUnique(DominoList deck) {
    int randCount = 0;
    Domino newDomino = new Domino();
    while (randCount < 50) {
      if (p1.deck.findLight(newDomino) == -1 && p2.deck.findLight(newDomino) == -1
          && dominoMap.rowMap.findLight(newDomino) == -1) {
            deck.add(newDomino);
      }
      newDomino = new Domino();
      randCount++;
    }
    deck.add(newDomino);
  }

  public int[] getPotentialDominoIndexes(DominoList deck) {
    List indexList = new List(7);
    for (int i = 0; i < deck.index; i++) {
      if (deck.getAt(i).getSides().compareLight(dominoMap.edges.getSidesX()) || deck.getAt(i).getSides().compareLight(dominoMap.edges.getSidesY())) {
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
