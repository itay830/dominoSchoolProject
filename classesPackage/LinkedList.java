package classesPackage;

// Classes for building something similar to a "Linked List".

// A class that acts like a basic one 'Pointer' 'Node'. 'value' is a 'Domino'...

class Node {
  public Domino value;
  public Node next;

  public Node(Domino value) {
    this.value = value;
    this.next = null;
  }
}

// A class for holding a sequence of 'Dominos' like in a "Linked List".

public class LinkedList {
  public Node head;
  public int length;

  // String.repeat() is only supported in Java 11.
  public static String repeat(String str, int times) {
    String newStr = "";
    for (int i = 0; i < times; i++) {
      newStr += str;
    }
    return newStr;
  }

  public LinkedList() {
    head = null;
    length = 0;
  }

  // Add a 'Domino' 'Node' to the end of the 'Linked List'.
  public void addEnd(Domino value) {
    length++;
    if (head == null) {
      head = new Node(value);
      return;
    }
    Node headCopy = head;
    while (headCopy.next != null) {
      headCopy = headCopy.next;
    }
    headCopy.next = new Node(value);
  }

  // Add a 'Domino' 'Node' to the start of the 'Linked List'.
  public void addStart(Domino value) {
    length++;
    if (head == null) {
      head = new Node(value);
      return;
    }
    Node newNode = new Node(value);
    newNode.next = head;
    head = newNode;
  }

  // Get the 'domino' that is in the leftest side of the 'Linked List'.
  public Domino getLeft() {
    return head.value;
  }

  // Get the 'domino' that is in the rightest side of the 'Linked List'.
  public Domino getRight() {
    Node headCopy = head;
    while (headCopy.next != null) {
      headCopy = headCopy.next;
    }
    return headCopy.value;
  }

  // Returns the "index" (Not really an index but you got it...) of the lighlty
  // compared 'Domino' to some 'int' value. If not found return '-1'
  public int findLight(Domino domino) {
    Node headCopy = head;
    int index = 0;
    while (headCopy != null) {
      if (Domino.IsCompatibleBoolean(domino, headCopy.value)) {
        return index;
      }
      index++;
      headCopy = headCopy.next;
    }
    return -1;
  }

  // Prints the linked list regulary *HAS NO USE!*
  public void rPrint() {
    Node headCopy = head;
    while (headCopy != null) {
      System.out.print("(" + headCopy.value.getSidesX() + ", " + headCopy.value.getSidesY() + ")\n");
      headCopy = headCopy.next;
    }
  }

  // Special way of printing the game map.
  public void print() {
    System.out.println("MAP: ");

    Node headCopy = head;
    System.out.print(repeat("╔═╤═╗", length) + "\n");
    while (headCopy != null) {
      System.out.print("║" + headCopy.value.getSidesX() + "│" + headCopy.value.getSidesY() + "║");
      headCopy = headCopy.next;
    }
    System.out.print("\n" + repeat("╚═╧═╝", length) + "\n");
  }
}
