import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    int cash = 1000;
    try (var scanner = new Scanner(System.in)) {
      while (cash > 0) {
        System.out.println("You have $%d...".formatted(cash));
        System.out.println("How much would you like to bet? (enter 0 to quit)");
        int bet = inputBet(scanner, cash);
        System.out.println();
        if (bet != 0) {
          int winnings = Blackjack.play(scanner, bet) - bet;
          printWinnings(winnings);
          cash += winnings;
          System.out.println();
        } else {
          break;
        }
      }
    }
    printConclusion(cash);
  }

  private static int inputBet(Scanner scanner, int cash) {
    while (true) {
      System.out.print("$");
      try {
        int bet = Integer.parseInt(scanner.nextLine());
        if (bet < 0) {
          System.out.print("You can't bet negative money!");
        } else if (bet % 5 != 0) {
          System.out.print("Bet must be a multiple of 5.");
        } else {
          return bet;
        }
      } catch (NumberFormatException e) {
        System.out.print("That doesn't seem to be a number.");
      }
      System.out.println(" Please try again.");
    }
  }

  private static void printWinnings(int winnings) {
    if (winnings > 0) {
      System.out.println("You make $%d!".formatted(winnings));
    } else if (winnings < 0) {
      System.out.println("You lose $%d.".formatted(Math.abs(winnings)));
    } else {
      System.out.println("You break even.");
    }
  }

  private static void printConclusion(int cash) {
    if (cash > 0) {
      System.out.println("You escape with your life and $%d in cash. Congratulations!".formatted(cash));
    } else if (cash == 0) {
      System.out.println("You are flat broke. Goodbye!");
    } else {
      System.out.println("You are now in $%d of crippling debt. Good luck!".formatted(Math.abs(cash)));
    }
  }
}
