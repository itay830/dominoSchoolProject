import classesPackage.*;

// Made by Itay S' for a school project!
// START : 12/04/2024
// END : 15/04/2024 (Also knows as the "Dead Line :L")

class mainProg {
  public static void main(String[] args) {
    // Main Program
    Game game = new Game(); // Game manager
    while (game.running) { // Main loop;
      game.logic(); // All the game logic...
    }
  }
}
