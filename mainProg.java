import classesPackage.*;

class mainProg {
  public static void main(String[] args) {
    Game game = new Game();
    game.p1.deck.printColumns(new int[]{1, 2, 3});
  }
}
