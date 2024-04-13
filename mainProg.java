import classesPackage.*;

class mainProg {
  public static void main(String[] args) {
    Game game = new Game();
    game.p1.deck.printColumns();
    game.p1.deck.popAt(1);
    System.out.println("NEW: ");
    game.p1.deck.printColumns();
  }
}
