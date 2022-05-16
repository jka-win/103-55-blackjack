public enum Rank {
  ACE("A", "Ace", 1),
  TWO("2", "Two", 2),
  THREE("3", "Three", 3),
  FOUR("4", "Four", 4),
  FIVE("5", "Five", 5),
  SIX("6", "Six", 6),
  SEVEN("7", "Seven", 7),
  EIGHT("8", "Eight", 8),
  NINE("9", "Nine", 9),
  TEN("T", "Ten", 10),
  JACK("J", "Jack", 10),
  QUEEN("Q", "Queen", 10),
  KING("K", "King", 10);

  private final String shortName;
  private final String longName;
  private final int value;

  Rank(String shortName, String longName, int value) {
    this.shortName = shortName;
    this.longName = longName;
    this.value = value;
  }

  public String getShortName() {
    return shortName;
  }

  public String getLongName() {
    return longName;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return longName;
  }
}
