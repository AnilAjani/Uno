package com.improving;

import java.util.LinkedList;

public interface IDeck {
    int getDrawPileSize();
    LinkedList<Card> getDiscardPile();
}
