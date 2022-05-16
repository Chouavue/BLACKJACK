import java.util.Scanner;



public class Game {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");
        System.out.println("What is your name?");
        String playerName = scan.nextLine();

        System.out.println("Hello " + playerName + "!" + " How much are you playing today?");
        double playerMoney = scan.nextDouble();

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        Scanner playerInput = new Scanner(System.in);

        while(playerMoney > 0){
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = playerInput.nextDouble();
            if (playerBet > playerMoney){
                System.out.println("You don't have enough money to bet. Good Bye!");
                break;
            }

            boolean endRound =false;

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);
        

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            if(playerDeck.cardsValue() == 21|| dealerDeck.cardsValue() == 21){
                System.out.println("BLACKJACK!");
            }

            while(true){
                System.out.println("-------Your Hand------ ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand value is : " + playerDeck.cardsValue());
                if(playerDeck.cardsValue() == 21){
                    System.out.println("You got BLACKJACK!");
                }

                System.out.println("------Dealer's hand-----");
                System.out.println(dealerDeck.getCard(0).toString());
                System.out.println("[****]");
                System.out.println("Do you want to (1) Hit or (2) Stand?");
                int response = playerInput.nextInt();

                if (response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
    
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Your hand value is : " + playerDeck.cardsValue());
                        System.out.println("BUST! Dealer Wins!");
                        playerMoney-=playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2){
                    break;
                }
            }
            System.out.println("------Dealer's hand-----");
            System.out.println(dealerDeck.toString());
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer Won!");
                playerMoney -= playerBet;
                endRound = true;
            }
            while((dealerDeck.cardsValue() < 21) && endRound == false){
                dealerDeck.draw(playerDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
                System.out.println("Dealer's hand value: " + dealerDeck.cardsValue());
                    if(dealerDeck.cardsValue() == 21){
                        System.out.println("Dealer got BLACKJACK!");
                    }
            } 
            if ((dealerDeck.cardsValue() > 21 ) && endRound == false){
                System.out.println("Dealer BUST. You Win!");
                playerMoney += playerBet;
                endRound = true;
            }
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
                System.out.println("Push! No one wins!");
                endRound = true;
            }
            if((dealerDeck.cardsValue() < playerDeck.cardsValue()) && endRound == false){
                System.out.println("You Won!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false){
            System.out.println("You lose!");
            playerMoney -= playerBet;
            endRound = true;
            }
            playerDeck.moveToDeck(playingDeck);
            dealerDeck.moveToDeck(playingDeck);
    }
        System.out.println("Game Over! You have no more money." );
        scan.close();
        playerInput.close();
    }

}
