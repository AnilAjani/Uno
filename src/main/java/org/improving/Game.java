package org.improving;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> player;
    private Deck deck;
    private Hand hand;

    public Game() {
        deck = new Deck();
        hand = new Hand(deck);
        this.player = new ArrayList<>();
        this.player.add(new Player(hand));
    }

    public static void main(String[] args) {
    var deck = new Deck();
    var hand = new Hand(deck);
    Game game = new Game();
    game.startGame(deck.draw());

    Player p;


       // System.out.println(deck.getDrawPile().size());
       // System.out.println(hand.firstHand().size());

    }

    public void startGame(Card card){
        var topCard = deck.getDrawPile().remove(0);
        deck.getDiscardPile().add(topCard);
    }

    public void startTurns() {
        var player = this.player.get(0);
        player.takeTurn(deck);
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getPlayer1() {
        return player.get(0);
    }
}
