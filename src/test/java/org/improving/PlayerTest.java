package org.improving;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void isPlayable_Should_Return_True_When_Faces_Match() {
        //Arrange
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Player player = new Player(hand);
        deck.getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(Color.Red, Faces.Two);
        //Act
        var result = player.isPlayable(deck, card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_Color_Match() {
        //Arrange
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Player player = new Player(hand);
        deck.getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(Color.Blue, Faces.Four);
        //Act
        var result = player.isPlayable(deck, card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_Wild() {
        //Arrange
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Player player = new Player(hand);
        deck.getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(null, Faces.Wild);
        //Act
        var result = player.isPlayable(deck, card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_DrawFour() {
        //Arrange
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Player player = new Player(hand);
        deck.getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(null, Faces.WildDrawFour);
        //Act
        var result = player.isPlayable(deck, card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_False_When_False() {
        //Arrange
        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Player player = new Player(hand);
        deck.getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(Color.Red, Faces.Six);
        //Act
        var result = player.isPlayable(deck, card);
        //Assert
        assertFalse(result);
    }

    @Test
    void takeTurn() {
    }
}