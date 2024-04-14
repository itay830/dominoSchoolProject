import classesPackage.*;

class mainProg {
  public static void main(String[] args) {
    Game game = new Game();
    while (!game.IsWinner()) {
      game.logic();
    }

    

  }
}
