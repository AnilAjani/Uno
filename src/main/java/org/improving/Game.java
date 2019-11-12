package org.improving;

import java.util.List;

public class Game {

    private List<Player> player;

    public static void main(String[] args) {
    var deck = new Deck();
    var hand = new Hand(deck);
    Game game = new Game();
    game.startGame(deck, hand, deck.draw());

    Player p;


       // System.out.println(deck.getDrawPile().size());
       // System.out.println(hand.firstHand().size());

    }

    public void startGame (Deck deck, Hand hand, Card card){
        var topCard = deck.getDrawPile().remove(0);
        deck.getDiscardPile().add(0, topCard);
        if(topCard.getFace() == Faces.Wild);
    }
}
