import classesPackage.*;

class mainProg {
  public static void main(String[] args) {
    // Main Program
    Game game = new Game(); // Game manager
    while (game.running) { // Main loop;
      game.logic(); // All the game logic...
    }
  }
}
