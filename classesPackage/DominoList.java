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

  public void printColumns() {
    if (index == 0) {return;}
    for (int k = 0; k < 2; k++) {
      System.out.print("\n");

      for (int i = 0; i < index; i++) {
        System.out.print("╔═════╗ ");
      }
      
      System.out.print("\n");
      for (int i = 0; i < index; i++) {System.out.print("║     ║ ");}
      System.out.print("\n");

      for (int i = 0; i < index; i++) {
        int value;
        if (k == 0) {
          value = dominoArr[i].getSidesX();
        } else {
          value = dominoArr[i].getSidesY();
        }
        System.out.print("║  " + value + "  ║ ");
      }

      System.out.print("\n");
      for (int i = 0; i < index; i++) {System.out.print("║     ║ ");}
      System.out.print("\n");
      
      for (int i = 0; i < index; i++) {
        System.out.print("╚═════╝ ");
      }
    }
  }

  public int findLight(Domino d) {
    for (int i = 0; i < index; i++) {
      if (Domino.IsCompatibleBoolean(dominoArr[i], d)) {
        return i;
      }
    }
    return -1;
  }
}
