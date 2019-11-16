package org.improving;

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
        Card card = new Card(Color.Red, Faces.Five);
        game.getDeck().getDiscardPile().add(card);
    }

    @Test
    void isPlayable_Should_Return_True_When_Faces_Match() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(Color.Red, Faces.Two);
        //Act
        var result = game.isPlayable(card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_Color_Match() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(Color.Blue, Faces.Four);
        //Act
        var result = game.isPlayable(card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_Wild() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(null, Faces.Wild);
        //Act
        var result = game.isPlayable(card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_True_When_DrawFour() {
        //Arrange

        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(null, Faces.WildDrawFour);
        //Act
        var result = game.isPlayable(card);
        //Assert
        assertTrue(result);
    }
    @Test
    void isPlayable_Should_Return_False_When_False() {
        //Arrange
        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        Card card = new Card(Color.Red, Faces.Six);
        //Act
        var result = game.isPlayable(card);
        //Assert
        assertFalse(result);
    }

    @Test
    void takeTurn_Should_Remove_One_Card_From_Hand() {
        //Arrange
        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        //Act
        player.takeTurn(game.getDeck(), game);
        var result = player.getHand().size();

        //Assert
        assertEquals(6, result);
    }
    @Test
    void takeTurn_Should_Add_One_Card_To_DiscardPile() {
        //Arrange

        //Act
        player.takeTurn(game.getDeck(), game);
        var result = game.getDeck().getDiscardPile().size();

        //Assert
        assertEquals(2, result);
    }
    @Test
    void takeTurn_Should_Play_First_Playable_Card() {
        //Arrange

        player.getHand().clear();
        player.getHand().addAll(Arrays.asList(
                new Card(Color.Red, Faces.Seven),
                new Card(Color.Red, Faces.Five),
                new Card(Color.Green, Faces.Six),
                new Card(Color.Yellow, Faces.Zero),
                new Card(Color.Blue, Faces.Two),
                new Card(Color.Red, Faces.Three),
                new Card(Color.Green, Faces.One)
        ));
        //Act
        player.takeTurn(game.getDeck(),game);
        var result = game.getDeck().getDiscardPile().size();

        //Assert
        assertEquals(2, result);

    }
    @Test
    void takeTurn_Should_Draw_Card_If_Not_Playable() {
        //Arrange
        game.getDeck().getDiscardPile().add(new Card(Color.Blue, Faces.Two));
        player.getHand().clear();
        player.getHand().addAll(Arrays.asList(
                new Card(Color.Red, Faces.Seven),
                new Card(Color.Red, Faces.Five)

        ));
        //Act
        player.takeTurn(game.getDeck(),game);
        var result = player.getHand().size();

        //Assert
        assertEquals(3, result);
    }

}