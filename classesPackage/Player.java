package classesPackage;

// A class for just clarity ; holding the data of the player.

public class Player {
  public String name;
  public DominoList deck;
  String color;

  public Player(String _name, DominoList _deck, String _color) {
    name = _name;
    deck = _deck;
    color = _color;
  }
  
}
