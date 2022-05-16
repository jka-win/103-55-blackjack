import java.util.Scanner;

public class Blackjack {
  public static int play(Scanner scanner, int bet) {
    return new Blackjack(scanner, bet).startRound();
  }

  private final Deck deck;
  private final Dealer dealer;
  private final Player player;
  private int bet;
  private final Scanner scanner;

  private Blackjack(Scanner scanner, int bet) {
    deck = new Deck();
    deck.shuffle();
    dealer = new Dealer();
    player = new Player();
    this.bet = bet;
    this.scanner = scanner;
  }

  private int startRound() {
    drawCards();

    boolean dealerIsNatural = dealer.getHand().isNatural();
    boolean playerIsNatural = player.getMainHand().isNatural();
    if (dealerIsNatural || playerIsNatural) {
      printHands(false);
      return resolveNaturals(dealerIsNatural, playerIsNatural);
    } else {
      printHands(true);
      System.out.println();
      int playerValue = player.getMainHand().getMinimumValue();
      boolean doubleDown = false;
      if (playerValue >= 9 && playerValue <= 11) {
        System.out.println("Would you like to double down? (yes/no)");
        doubleDown = inputDoubleDown();
        if (doubleDown) {
          bet *= 2;
          Card card = deck.draw();
          System.out.println("You draw: %s".formatted(card.toLongName()));
          player.getMainHand().add(card);
        }
      }
      return playRound(doubleDown);
    }
  }

  private void drawCards() {
    player.getMainHand().add(deck.draw());
    dealer.getHand().add(deck.draw());
    player.getMainHand().add(deck.draw());
    dealer.getHand().add(deck.draw());
  }

  private int resolveNaturals(boolean dealerIsNatural, boolean playerIsNatural) {
    if (dealerIsNatural && playerIsNatural) {
      System.out.println("Two naturals; stand-off!");
      return bet;
    } else if (dealerIsNatural) {
      System.out.println("Dealer's natural; dealer wins!");
      return 0;
    } else if (playerIsNatural) {
      System.out.println("Player's natural; player wins!");
      return (int) Math.floor(bet * 2.5);
    }
    throw new IllegalStateException("resolveNaturals may only be called when one or more naturals occurred.");
  }

  private boolean inputDoubleDown() {
    while (true) {
      String choice = scanner.nextLine().trim().toLowerCase();
      if (choice.equals("yes") || choice.equals("no")) {
        return choice.equals("yes");
      }
      System.out.println("Please enter \"yes\" or \"no\".");
    }
  }

  private int playRound(boolean doubleDown) {
    if (!doubleDown) {
      System.out.println("Player's turn...");
      while (true) {
        String choice = inputChoice();
        if (choice.equals("stand")) {
          break;
        }

        Card card = deck.draw();
        System.out.println("You draw: %s".formatted(card.toLongName()));
        player.getMainHand().add(card);
        if (player.getMainHand().isBust()) {
          printHands(false);
          System.out.println("Player busts!");
          return 0;
        } else {
          printHands(true);
          System.out.println();
        }
      }
    }

    System.out.println();
    System.out.println("Dealer's turn...");
    printHands(false);
    System.out.println();
    while (dealer.getHand().getBestValue() < 17) {
      Card card = deck.draw();
      System.out.println("Dealer draws: %s".formatted(card.toLongName()));
      dealer.getHand().add(card);
      printHands(false);
      if (dealer.getHand().isBust()) {
        System.out.println("Dealer busts!");
        return bet * 2;
      }
      System.out.println();
    }

    int playerValue = player.getMainHand().getBestValue();
    int dealerValue = dealer.getHand().getBestValue();
    if (playerValue > dealerValue) {
      System.out.println("Player wins!");
      return bet * 2;
    } else if (playerValue < dealerValue) {
      System.out.println("Player loses!");
      return 0;
    } else {
      System.out.println("Stand-off!");
      return bet;
    }
  }

  private String inputChoice() {
    System.out.println("Would you like to hit, or stand?");
    while (true) {
      String choice = scanner.nextLine().trim().toLowerCase();
      if (choice.equals("hit") || choice.equals("stand")) {
        return choice;
      }
      System.out.println("Please enter either \"hit\", or \"stand\".");
    }
  }

  private void printHands(boolean dealerHidden) {
    System.out.print("Dealer's hand: ");
    if (dealerHidden) {
      System.out.print(dealer.getHand().get(0).toLongName());
      System.out.println(", [Hidden]");
    } else {
      printHand(dealer.getHand());
    }

    if (player.getOffHand().size() == 0) {
      System.out.print("Player's hand: ");
      printHand(player.getMainHand());
    } else {
      System.out.print("Player's left hand:  ");
      printHand(player.getMainHand());
      System.out.print("Player's right hand: ");
      printHand(player.getOffHand());
    }
  }

  private static void printHand(Hand hand) {
    System.out.println(String.join(", ", hand.stream().map(card -> card.toLongName()).toList()));
  }
}
