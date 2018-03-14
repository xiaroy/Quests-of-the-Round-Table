package model.game;

import model.cards.Card;

public interface CardSpace <T extends Card> {

	boolean AddCard(T card);
    boolean RemoveCard(T card);
    boolean ContainsCard(T card);

    int TotalCards();
    T[] GetCards();
    
}
