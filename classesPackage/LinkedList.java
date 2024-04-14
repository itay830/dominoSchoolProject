package classesPackage;

class Node {
  Domino value;
  Node next;

  public Node(Domino value) {
    this.value = value;
    this.next = null;
  }
}

public class LinkedList {
  public Node head;
  public int length;

  public LinkedList() {
    head = null;
    length = 0;
  }

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

  // public int[] toArray() {
  //   int[] arr = new int[length];
  //   Node headCopy = head;
  //   int i = 0;
  //   while (headCopy != null) {
  //     arr[i] = headCopy.value;
  //     i++;
  //     headCopy = headCopy.next;
  //   }
  //   return arr;
  // }

  // public void print() {
  //   Node headCopy = head;
  //   while (headCopy != null) {
  //     System.out.println(headCopy.value);
  //     headCopy = headCopy.next;
  //   }
  // }
}
