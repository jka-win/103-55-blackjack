public class Player {
  private final Hand mainHand;
  private final Hand offHand;

  public Player() {
    mainHand = new Hand();
    offHand = new Hand();
  }

  public Hand getMainHand() {
    return mainHand;
  }

  public Hand getOffHand() {
    return offHand;
  }
}
