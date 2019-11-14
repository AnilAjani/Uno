package org.improving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private Deck deck;
    LinkedList<Player> players;
    int currentPlayer = 0;
    int turnEngine = 0;
    int turnDirection = 1;
    int playerNumber;

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

    }

    public Game() {
        deck = new Deck();
        this.players = new LinkedList<>();
        this.players.addAll(Arrays.asList(
                new Player("Player one ", deck),
                new Player("Player two", deck),
                new Player("Player Three ", deck)

        ));
    }

    public void startGame(){
        System.out.println("New Game with " + players.size() + " players ");
        System.out.println("DRAW PILE SIZE = " + deck.getDrawPile().size());
        System.out.println("PLAYER ONE HAND SIZE= " + players.get(0).getHand().size());
        System.out.println("PLAYER TWO HAND SIZE= " + players.get(1).getHand().size());
        System.out.println("PLAYER THREE HAND SIZE= " + players.get(2).getHand().size());
        var topCard = deck.getDrawPile().remove(0);
        deck.getDiscardPile().add(topCard);
        System.out.println(topCard);
        int turns = 0;
        while(true) {
            for(var player: players) {
                player.takeTurn(deck, this);
                turns++;
                if (player.getHand().size() == 0) {
                    System.out.println("Player " + player.getName() + " Wins ");
                    return;
                }
            }
        }
    }

    public boolean isPlayable(Card card) {
        return deck.getDiscardTopCard().getFace() == card.getFace() ||
                deck.getDiscardTopCard().getColor() == card.getColor() ||
                card.getFace().getValue() == 50;
    }

    public void startTurns() {
        var player = this.players.get(0);
        player.takeTurn(deck, this);
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Player> getPlayer() {
        return players;
    }

    public void playCard(Card card) {
        System.out.println("Played card : " + card.toString());
        if()
        if(deck.getDiscardTopCard().getFace().getValue() == 50){
            card.setColor(Color.Red);
        }
        deck.getDiscardPile().add(card);
    }
    public void drawTwo(Player player, Card card){
        if(card.getFace() == Faces.DrawTwo){
            player.getHand().add(this.deck.draw());
            player.getHand().add(this.deck.draw());
        }
    }
}
