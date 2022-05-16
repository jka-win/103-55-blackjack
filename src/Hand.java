import java.util.ArrayList;

public class Hand extends ArrayList<Card> {
  public boolean isNatural() {
    if (size() == 2) {
      Rank r1 = get(0).getRank();
      Rank r2 = get(1).getRank();
      return (r1 == Rank.ACE || r2 == Rank.ACE) && (r1.getValue() == 10 || r2.getValue() == 10);
    }
    return false;
  }

  public boolean isBust() {
    return getMinimumValue() > 21;
  }

  public int getMinimumValue() {
    int total = 0;
    for (Card card : this) {
      total += card.getRank().getValue();
    }
    return total;
  }

  public int getBestValue() {
    int total = getMinimumValue();
    for (Card card : this) {
      if (card.getRank() == Rank.ACE) {
        if (total + 10 > 21) {
          return total;
        }
        total += 10;
      }
    }
    return total;
  }
}
