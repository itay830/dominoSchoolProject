package classesPackage;

public class DominoList {
  public Domino[] dominoArr;
  public int index;

  public DominoList(int size) {
    if (size <= 0) {
      index = 1;
    } else {
      index = 0;
    }
    dominoArr = new Domino[size];
  }

  public void add(Domino d) {
    dominoArr[index] = d;
    index++;
    if (index >= dominoArr.length) {
      expand();
    }
  }

  public Domino popAt(int popIndex) {
    if (index == 0) {
      return new Domino(-1, -1);
    } else if (popIndex >= index) {
      popIndex = index - 1;
    } else if (popIndex < 0) {
      popIndex = 0;
    }
    Domino poppedD = dominoArr[popIndex];
    index--;
    for (int i = popIndex; i < index; i++) {
      dominoArr[i] = dominoArr[i + 1];
    }
    return poppedD;
  }

  public Domino getAt(int findIndex) {
    if (index == 0) {
      return new Domino(-1, -1);
    } else if (findIndex >= index) {
      findIndex = index - 1;
    } else if (findIndex < 0) {
      findIndex = 0;
    }
    return dominoArr[findIndex];
  }

  private void expand() {
    Domino[] dominoArrCopy = new Domino[dominoArr.length];
    for (int i = 0; i < dominoArr.length; i++) {
      dominoArrCopy[i] = dominoArr[i];
    }
    dominoArr = new Domino[dominoArrCopy.length + 5];
    for (int i = 0; i < dominoArrCopy.length; i++) {
      dominoArr[i] = dominoArrCopy[i];
    }
  }

  public void printColumns(int[] rDominoIndexes) {
    if (index == 0) {
      return;
    }
    int xSize = 5;
    String color;

    for (int k = 0; k < 2; k++) {
      System.out.print("\n");
      for (int i = 0; i < index && k == 0; i++) {
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }
        System.out.print(color + "   " + i + "    " + Colors.RESET);
      }
      if (k==0) {System.out.print("\n");}

      for (int i = 0; i < index; i++) {
        String str;
        if (k == 1) {
          str =  "╠" + "╩".repeat(xSize) + "╣ " ;
        } else {
          
          str =  "╔" + "═".repeat(xSize) + "╗ " ;
        }
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }
        System.out.print(color + str + Colors.RESET);
      }

      System.out.print("\n");
      for (int i = 0; i < index; i++) {
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }
        System.out.print(color + "║     ║ " + Colors.RESET);
      }
      System.out.print("\n");

      for (int i = 0; i < index; i++) {
        int value;
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }

        if (k == 0) {
          value = dominoArr[i].getSidesX();
        } else {
          value = dominoArr[i].getSidesY();
        }
        System.out.print(color + "║  " + value + "  ║ " + Colors.RESET);
      }

      System.out.print("\n");
      for (int i = 0; i < index; i++) {
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }
        System.out.print(color + "║     ║ " + Colors.RESET);
      }
      System.out.print("\n");

      for (int i = 0; i < index; i++) {
        String str;
        if (k == 0) {
          str = "╠" + "╦".repeat(xSize) + "╣ ";
        } else {
          str = "╚" + "═".repeat(xSize) + "╝ ";
        }
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }

        System.out.print(color + str + Colors.RESET);
      }

      System.out.print("\n");
      for (int i = 0; i < index && k == 0; i++) {
        if (DominoList.findLight(rDominoIndexes, i) != -1) {
          color = Colors.GREEN;
        } else {
          color = Colors.RESET;
        }

        System.out.print(color + "╠" + "╬".repeat(xSize) + "╣ " + Colors.RESET);
      }
    }

    System.out.println("\n");
  }

  public static int findLight(int[] numArr, int num) {
    for (int i = 0; i < numArr.length; i++) {
      if (numArr[i] == num) {
        return i;
      }
    }
    return -1;
  }

  public int findLight(Domino d) {
    for (int i = 0; i < index; i++) {
      if (Domino.IsCompatibleBoolean(dominoArr[i], d)) {
        return i;
      }
    }
    return -1;
  }

  public int findLight(int num) {
    for (int i = 0; i < index; i++) {
      if (dominoArr[i].getSidesX() == num || dominoArr[i].getSidesY() == num) {
        return i;
      }
    }
    return -1;
  }

}
