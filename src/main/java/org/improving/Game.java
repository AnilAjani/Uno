package org.improving;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

    private Deck deck = new Deck();
    LinkedList<Player> players;
    int currentTurn = 0;
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

    public void play() {
        printGameStart();

        // Draw the first card.
        playCard(draw());
        System.out.println(deck.getDiscardPile().getLast());

        int turns = 0;
        int playerNumber;
        // start game
        while (true) {

            // get player index make it the "current player"
            playerNumber = getPlayerNumber();
            var actualPlayer = players.get(playerNumber);

            // "current player" takes turn if cards in hand isPlayable based on color, face, wild or else draws card
            System.out.println(actualPlayer.getName() + " Has " + actualPlayer.getHand());
            var playedCard = actualPlayer.takeTurn(this);
            // what if it is a draw multiple or skip or reverse?
            if (playedCard != null) executeSpecial();
            turns++;

            if (actualPlayer.handSize() == 0) {
                System.out.println(actualPlayer.getName() + " Wins " + turns);
                return;
            }
            nextPlayer();
        }
    }

    private void printGameStart() {
        System.out.println("New Game with " + players.size() + " players ");
        System.out.println("DRAW PILE SIZE = " + deck.getDrawPile().size());
        for (var player : players) {
            System.out.println(player.getName() + " has " + player.handSize());
        }
    }

    private void nextPlayer() {
        currentTurn = currentTurn + turnDirection;
    }

    private int getPlayerNumber() {
        return Math.abs(currentTurn % players.size());
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
        // If color is null, set the color.
        if (card.getColor() == null) card.setColor(Color.values()[new Random().nextInt(4)]);
        deck.getDiscardPile().add(card);
    }

    // evaluate if discard topcard is one of the following
    //if true proceed to executeSpecial

    // next player will have to deal with the special card
    public void executeSpecial() {

        int nextPlayer = Math.abs((currentTurn + turnDirection) % players.size());

        if (deck.getDiscardPile().getLast().getFace() == Faces.DrawTwo) {
            System.out.println("---- " + players.get(nextPlayer).getName() + " DRAW TWO ----");
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            nextPlayer();
        } else if (deck.getDiscardPile().getLast().getFace() == Faces.WildDrawFour) {
            System.out.println("---- " + players.get(nextPlayer).getName() + " DRAW FOUR ----");
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            nextPlayer();
        } else if (deck.getDiscardPile().getLast().getFace() == Faces.Reverse) {
            System.out.println("----REVERSE----");
            turnDirection = turnDirection * -1;
        } else if (deck.getDiscardPile().getLast().getFace() == Faces.Skip) {
            System.out.println("----SKIP----" + players.get(nextPlayer).getName());
            nextPlayer();
        }
    }

    public Card draw() {
        return deck.draw();
    }
}
