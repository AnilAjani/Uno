package org.improving;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnoTest {

    Deck deck = new Deck();
    Hand hand = new Hand(deck);
    Faces face;
    Color color;


    @Test
    public void Hand_Should_Recognize_Starting_Hand() {
        //Arrange
        this.deck = deck;
        this.hand = hand;

//        var cards = new Card[]{
//                new Card(Color.Red, Faces.Six),
//                new Card(Color.Blue, Faces.Three),
//                new Card(Color.Blue, Faces.Four),
//                new Card(Color.Yellow, Faces.Two),
//                new Card(Color.Yellow, Faces.Two),
//                new Card(Color.Green, Faces.Five),
//                new Card(Color.Green, Faces.Zero)
//  };
        //Act
        List<Card> firstHand = hand.firstHand();

        //Assert
        assertTrue(firstHand.size() == 7);
    }

    @Test
    public void Hand_Should_Recognize_Face(){
        //Arrange
        Card card = new Card(Color.Blue, Faces.DrawTwo);

        //Act
        boolean correct = false;
        correct = card.getFace().toString().equalsIgnoreCase("DrawTwo");

        //Assert
        assertTrue(correct);

    }
    @Test
    public void Hand_Should_Recognize_Color(){
        //Arrange
        Card card = new Card(Color.Blue, Faces.DrawTwo);

        //Act
        boolean correct = false;
        correct = card.getFace().toString().equalsIgnoreCase("DrawTwo");

        //Assert
        assertTrue(correct);
    }
    @Test
    public void Deck_Should_Return_112_Cards(){
        //Arrange

        //Act
        System.out.println(deck.getDrawPile().size());

        //Assert
        assertTrue(deck.getDrawPile().size() == 112);

    }

    @Test
    public void Draw_Card_Should_Remove_From_drawPile(){
        //Arrange
        var drawPile = deck.getDrawPile().size();

        //Act
        hand.drawCard();
        var afterDraw = deck.getDrawPile().size();

        //Assert
        assertTrue(drawPile - 1 == afterDraw);
    }
    @Test
    public void Draw_Card_Should_Add_Card_To_Hand(){
        //Arrange
        var playerHand = hand.getHand().size();

        //Act
        hand.drawCard();
        var newPlayerHand = hand.getHand().size();

        //Assert
        assertTrue(playerHand + 1 == newPlayerHand);
    }
}



