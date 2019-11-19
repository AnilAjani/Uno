package com.improving;

import java.util.List;
import java.util.Optional;

public interface IGame {
    void playCard(Card card, Optional<Colors> chosenColor);
    boolean isPlayable(Card card);
    Card draw();
    List<IPlayerInfo> getPlayerInfo();

}
