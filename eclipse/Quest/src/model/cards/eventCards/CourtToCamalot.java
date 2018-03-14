package model.cards.eventCards;

import model.cards.Card;
import model.cards.EventCard;
import model.game.CardCriteria;
import model.game.GameState;
import model.player.Player;

public class CourtToCamalot extends EventCard {

	public CourtToCamalot() {
		super("Court Called to Camelot");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doEffect(GameState gState) {
		Player[] players = gState.getPlayersInRankOrder();
        for (Player player : gState.getPlayers())
        {
            player.RemoveCardsFromBoardWithCriteria(new CardCriteria() {
				@Override
				public boolean DoesMeetCriteria(Card card) {
					return card.GetCardType() == CardTypes.Ally;
				}
			});
        }
	}

}
