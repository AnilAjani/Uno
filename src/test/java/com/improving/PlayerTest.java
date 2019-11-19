package com.improving;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Deck deck;
    Game game;
    Player player;

    @BeforeEach
    void arrange() {
        deck = new Deck();
        game = new Game();
        player = new Player("This", game.getDeck());
        Card card = new Card(Colors.Red, Faces.Five);
        game.getDeck().getDiscardPile().add(card);
    }

    @Test
    void isPlayable_Should_Return_True_When_Faces_Match() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        Card card = new Card(Colors.Red, Faces.Two);
        //Act
        var result = game.isPlayable(card);
        //Assert
        Assertions.assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_Color_Match() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        Card card = new Card(Colors.Blue, Faces.Four);
        //Act
        var result = game.isPlayable(card);
        //Assert
        Assertions.assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_Wild() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        Card card = new Card(null, Faces.Wild);
        //Act
        var result = game.isPlayable(card);
        //Assert
        Assertions.assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_DrawFour() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        Card card = new Card(null, Faces.WildDrawFour);
        //Act
        var result = game.isPlayable(card);
        //Assert
        Assertions.assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_False_When_False() {
        //Arrange
        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        Card card = new Card(Colors.Red, Faces.Six);
        //Act
        var result = game.isPlayable(card);
        //Assert
        Assertions.assertFalse(result);
    }

    @Test
    void takeTurn_Should_Remove_One_Card_From_Hand() {
        //Arrange
        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        //Act
        player.takeTurn(game);
        var result = player.getHand().size();

        //Assert
        assertEquals(6, result);
    }
    @Test
    void takeTurn_Should_Add_One_Card_To_DiscardPile() {
        //Arrange

        //Act
        player.takeTurn(game);
        var result = game.getDeck().getDiscardPile().size();

        //Assert
        assertEquals(2, result);
    }
    @Test
    void takeTurn_Should_Play_First_Playable_Card() {
        //Arrange

        player.getHand().clear();
        player.getHand().addAll(Arrays.asList(
                new Card(Colors.Red, Faces.Seven),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Green, Faces.Six),
                new Card(Colors.Yellow, Faces.Zero),
                new Card(Colors.Blue, Faces.Two),
                new Card(Colors.Red, Faces.Three),
                new Card(Colors.Green, Faces.One)
        ));
        //Act
        player.takeTurn(game);
        var result = game.getDeck().getDiscardPile().size();

        //Assert
        assertEquals(2, result);

    }
    @Test
    void takeTurn_Should_Draw_Card_If_Not_Playable() {
        //Arrange
        game.getDeck().getDiscardPile().add(new Card(Colors.Blue, Faces.Two));
        player.getHand().clear();
        player.getHand().addAll(Arrays.asList(
                new Card(Colors.Red, Faces.Seven),
                new Card(Colors.Red, Faces.Five)

        ));
        //Act
        player.takeTurn(game);
        var result = player.getHand().size();

        //Assert
        assertEquals(3, result);
    }
    @Test
    void takeTurn_Should_Let_DrawnCard_Play_If_isPlayable(){

    }
    @Test
    void player_Should_Play_Most_Common_Color_In_Hand(){
        // Arrange

        // Act

        //Assert
    }
//    @Test
//    void player_Should_Play_Highest_Value_Card_First(){
//        // Arrange
//        player.getHand().add(new Card(Colors.Blue, Faces.Wild));
//        // Act
//        player.takeTurn(game);
//        deck.getDiscardPile().getLast();
//        //Assert
//    }

}