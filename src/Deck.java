import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private final List<Card> cards;

  public Deck() {
    cards = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        cards.add(new Card(suit, rank));
      }
    }
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public Card draw() {
    return cards.remove(cards.size() - 1);
  }

  public void undraw(Card card) {
    cards.add(0, card);
  }

  @Override
  public String toString() {
    return cards.toString();
  }
}
