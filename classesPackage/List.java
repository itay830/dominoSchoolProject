package classesPackage;

public class List {
  int[] arr;
  int index;

  public List(int size) {
    if (size <= 0) {
      index = 1;
    } else {
      index = 0;
    }
    arr = new int[size];
  }

  public void add(int d) {
    arr[index] = d;
    index++;
    if (index >= arr.length) {
      expand();
    }
  }

  public void expand() {
    int[] arrCopy = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      arrCopy[i] = arr[i];
    }
    arr = new int[arrCopy.length + 5];
    for (int i = 0; i < arrCopy.length; i++) {
      arr[i] = arrCopy[i];
    }
  }

  public int popAt(int popIndex) {
    if (index == 0) {
      return 0;
    } else if (popIndex >= index) {
      popIndex = index - 1;
    } else if (popIndex < 0) {
      popIndex = 0;
    }
    int popped = arr[popIndex];
    index--;
    for (int i = popIndex; i < index; i++) {
      arr[i] = arr[i + 1];
    }
    return popped;
  }

  public int[] toArray() {
    int[] newArr = new int[index];
    for (int i = 0; i < index; i++) {
      newArr[i] = arr[i];
    }
    return newArr;
  }
}
