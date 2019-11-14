package org.improving;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private Deck deck = new Deck();
    LinkedList<Player> players;
    int currentTurn = 0;
    int turnEngine = 0;
    int turnDirection = 1;


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
        boolean gameProgress = true;
        int playerNumber;

        while(gameProgress) {
            playerNumber = getPlayerNumber();
            var actualPlayer = players.get(playerNumber);
            actualPlayer.takeTurn(deck, this);
            System.out.println(actualPlayer.getName() + " Played " + actualPlayer.getHand());
            turns++;
            if(evaluateSpecialCard(topCard)) {
                executeSpecial(this, topCard);
            }
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
        if(deck.getDiscardTopCard().getFace().getValue() == 50){
            card.setColor(Color.Red);
        }
        deck.getDiscardPile().add(card);
    }

    public boolean evaluateSpecialCard(Card card) {
        if (card.getFace().equals(Faces.DrawTwo)
                || card.getFace().equals(Faces.WildDrawFour)
                || card.getFace().equals(Faces.Skip)
                || card.getFace().equals(Faces.Reverse)) {
            return true;
        } else return false;
    }

    public void executeSpecial(Game game, Card card){

        int nextPlayer = (currentTurn + turnDirection) % players.size();
        int activePlayerNumber = currentTurn % players.size();

        if(card.getFace() == Faces.DrawTwo && card.isChecked) {
            players.get(nextPlayer).draw(game);
            players.get(nextPlayer).draw(game);
            card.isChecked = false;
            currentTurn = currentTurn + turnDirection;
        }
        if(card.getFace() == Faces.WildDrawFour && card.isChecked) {
            players.get(nextPlayer).draw(game);
            players.get(nextPlayer).draw(game);
            players.get(nextPlayer).draw(game);
            players.get(nextPlayer).draw(game);
            card.isChecked = false;
            currentTurn = currentTurn + turnDirection;
        }

        if(card.getFace() == Faces.Reverse && card.isChecked) {
            turnDirection = turnDirection * -1;
            card.isChecked = false;
        }

        if(card.getFace() == Faces.Skip && card.isChecked) {
            turnDirection = turnDirection + 1;
            card.isChecked = false;
        }
    }
}
