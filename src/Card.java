public class Card {
  private final Suit suit;
  private final Rank rank;

  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public Rank getRank() {
    return rank;
  }

  public String toShortName() {
    return "%s%s".formatted(
      rank.getShortName(), suit.getShortName()
    );
  }

  public String toLongName() {
    return "%s of %s".formatted(
      rank.getLongName(), suit.getLongName()
    );
  }

  @Override
  public String toString() {
    return toShortName();
  }
}
