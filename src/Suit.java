public enum Suit {
  CLUBS("C", "Clubs"),
  DIAMONDS("D", "Diamonds"),
  HEARTS("H", "Hearts"),
  SPADES("S", "Spades");

  private final String shortName, longName;

  Suit(String shortName, String longName) {
    this.shortName = shortName;
    this.longName = longName;
  }

  public String getShortName() {
    return shortName;
  }

  public String getLongName() {
    return longName;
  }

  @Override
  public String toString() {
    return longName;
  }
}
