import java.util.ArrayList;
import java.util.Random;

public class Deck{

    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }
    public void createFullDeck(){
        for (Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()){
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
        }

        public void shuffle(){
            ArrayList<Card> tmpDeck = new ArrayList<Card>();
            Random random = new Random();
            int randomCardIndex = 0;
            int originalSize = this.cards.size();
            for(int i=0; i <originalSize; i++){
                randomCardIndex = random.nextInt((this.cards.size()-1) +1) ;
                tmpDeck.add(this.cards.get(randomCardIndex));
                this.cards.remove(randomCardIndex);
            }
            this.cards = tmpDeck;
        }

        public String toString(){
            String cardListOutput = " ";

            for(Card aCard : this.cards){
                cardListOutput += "\n" + aCard.toString();
            }
            return cardListOutput;
        }
        public void removeCard(int i){
            this.cards.remove(i);
        }
        public Card getCard(int i){
            return this.cards.get(i);
        }
        public void addCard(Card addCard){
            this.cards.add(addCard);
        }
        public void draw(Deck comingFrom){
            this.cards.add(comingFrom.getCard(0));
            comingFrom.removeCard(0);
        }

        public int cardsValue(){
            int value =0;
            int aces = 0;

            for (Card aCard : this.cards){
                switch(aCard.getValue()){
                    case TWO : value += 2; break;
                    case THREE : value += 3; break;
                    case FOUR : value += 4;break;
                    case FIVE : value += 5; break;
                    case SIX : value += 6; break;
                    case SEVEN : value += 7; break;
                    case EIGHT : value += 8; break;
                    case NINE : value += 9; break;
                    case TEN : value += 10; break;
                    case JACK : value +=10; break;
                    case QUEEN : value += 10; break;
                    case KING : value += 10; break;
                    case ACE : aces += 1; break;
                }
            }
            for (int i = 0; i < aces; i ++){
                if(value > 10){
                value += 1;
                }
                else{
                value +=11;
                }
            }
            return value;
        }
        public int deckSize(){
            return this.cards.size();
        }
        public void moveToDeck(Deck moveTo){
            int thisDeckSize = this.cards.size();

            for (int i =0 ; i < thisDeckSize; i ++){
                moveTo.addCard(this.getCard(i));
            }
            for(int i = 0; i < thisDeckSize; i ++ ){
                this.removeCard(0);
            }
        }
    }

