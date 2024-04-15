package classesPackage;

import java.util.Scanner;

// A class for holding and printing the game board.

public class DominoMap {
  public LinkedList rowMap;
  public Domino edges;
  Scanner r = new Scanner(System.in);

  public DominoMap() {
    rowMap = new LinkedList();
    edges = new Domino(-1, -1);
  }

  // Add the domino the right way to the map.
  public boolean add(Domino domino) {
    if (rowMap.head == null) {
      rowMap.addStart(domino);
      edges.setSidesX(rowMap.getLeft().getSidesX());
      edges.setSidesY(rowMap.getRight().getSidesY());
      return true;
    }
    String path = Domino.IsCompatibleString(domino, edges, true, true);
    char choice = '-';
    if (path == "-") {
      System.out.println(Colors.RED + "NO POSSIBLE CONNECTIONS!!!" + Colors.RESET);
      return false;
    } else if (path == "*") {
      System.out.print("Choose if to go LEFT or RIGHT (l/r, default is 'l'): ");
      choice = r.next().charAt(0);
      if (choice != 'l' && choice != 'r') {
        choice = 'l';
      }
      if (choice == 'l') {
        path = Domino.IsCompatibleString(domino, edges, true, false);
      } else if (choice == 'r') {
        path = Domino.IsCompatibleString(domino, edges, false, true);
      }
      addToMapWithPath(path, domino);
      edges.setSidesX(rowMap.getLeft().getSidesX());
      edges.setSidesY(rowMap.getRight().getSidesY());
    } else {
      addToMapWithPath(path, domino);
    }
    return true;
  }

  // Uses the 'path' string that is given by 'Domino.IsCompatibleString' to place
  // the domino in the map the right way.
  public void addToMapWithPath(String path, Domino domino) {
    switch (path) {
      case "XX":
        rowMap.addStart(new Domino(domino.getSidesY(), domino.getSidesX()));
        break;
      case "YY":
        rowMap.addEnd(new Domino(domino.getSidesY(), domino.getSidesX()));
        break;
      case "XY":
        rowMap.addEnd(new Domino(domino.getSidesX(), domino.getSidesY()));
        break;
      case "YX":
        rowMap.addStart(new Domino(domino.getSidesX(), domino.getSidesY()));
        break;
    }
    edges.setSidesX(rowMap.getLeft().getSidesX());
    edges.setSidesY(rowMap.getRight().getSidesY());
  }

  // Prints the board (prints a 'LinkedList').
  public void print() {
    rowMap.print();
  }
}
