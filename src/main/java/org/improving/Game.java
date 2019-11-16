package org.improving;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

    private Deck deck = new Deck();
    LinkedList<Player> players;
    int currentTurn = 0;
    int turnEngine = 0;
    int turnDirection = 1;


    public static void main(String[] args) {
        Game game = new Game();
        game.play();

    }

    public Game() {
        deck = new Deck();
        this.players = new LinkedList<>();
        this.players.addAll(Arrays.asList(
                new Player("Player 1 ", deck),
                new Player("Player 2 ", deck),
                new Player("Player 3 ", deck),
                new Player("Player 4 ", deck),
                new Player("Player 5 ", deck)

        ));
    }

    public void play(){
        System.out.println("New Game with " + players.size() + " players ");
        System.out.println("DRAW PILE SIZE = " + deck.getDrawPile().size());
        for(var player:players){
            System.out.println(player.getName() + " has " + player.getHand().size());
        }
        var topCard = deck.draw();
        playCard(topCard);
        System.out.println(topCard);
        int turns = 0;
        boolean gameProgress = true;
        int playerNumber;
        // start game
        while(gameProgress) {

            // get player index make it the "current player"
            playerNumber = getPlayerNumber();
            var actualPlayer = players.get(playerNumber);
            var newDrawPile = deck.getDrawPile();

            // "current player" takes turn if cards in hand isPlayable based on color, face, wild or else draws card
            System.out.println(actualPlayer.getName() + " Has " + actualPlayer.getHand());
            actualPlayer.takeTurn(this);
            turns++;

            // what if it is a draw multiple or skip or reverse?
            // evaluate if true and executeSpecial
                executeSpecial(this, topCard);
            if (actualPlayer.getHand().size() == 0) {
                System.out.println(actualPlayer.getName() + " Wins " + turns);
                return;
            }
            currentTurn = currentTurn + turnDirection;
        }
    }

    private int getPlayerNumber() {
        if(currentTurn < 0){
            currentTurn = currentTurn + players.size();
        }
        return currentTurn % players.size();
    }


    public boolean isPlayable(Card card) {
        return deck.getDiscardTopCard().getFace() == card.getFace() ||
                deck.getDiscardTopCard().getColor() == card.getColor() ||
                card.getFace().getValue() == 50;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return players;

    }

    public void playCard(Card card) {
        //System.out.println("Played card : " + card.toString());
        if(card.getColor() == null) card.setColor(Color.values()[new Random().nextInt(4)]);
        deck.getDiscardPile().add(card);
    }

    // evaluate if discard topcard is one of the following
    //if true proceed to executeSpecial

    // next player will have to deal with the special card
    public void executeSpecial(Game game, Card card){

        int nextPlayer = Math.abs((currentTurn + turnDirection) % players.size());
        int activePlayerNumber = currentTurn % players.size();

        if(deck.getDiscardPile().getLast().getFace() == Faces.DrawTwo) {
            System.out.println("---- " + players.get(nextPlayer).getName() + " DRAW TWO ----");
            players.get(nextPlayer).getHand().add(deck.draw());
            players.get(nextPlayer).getHand().add(deck.draw());
            currentTurn = currentTurn + turnDirection;
        }
        else if(deck.getDiscardPile().getLast().getFace() == Faces.WildDrawFour) {
            System.out.println("---- " + players.get(nextPlayer).getName() + " DRAW FOUR ----");
            players.get(nextPlayer).getHand().add(deck.draw());
            players.get(nextPlayer).getHand().add(deck.draw());
            players.get(nextPlayer).getHand().add(deck.draw());
            players.get(nextPlayer).getHand().add(deck.draw());


            currentTurn = currentTurn + turnDirection;
        }

        else if(deck.getDiscardPile().getLast().getFace() == Faces.Reverse) {
            System.out.println("----REVERSE----");
            turnDirection = turnDirection * -1;
        }

        else if(deck.getDiscardPile().getLast().getFace() == Faces.Skip) {
            System.out.println("----SKIP----" + players.get(nextPlayer).getName());
            currentTurn = currentTurn + turnDirection;
        }
    }
    public Card draw() {
        return deck.draw();
    }
}
