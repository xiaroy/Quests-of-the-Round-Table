using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CourtCalledtoCamelot : EventCard {


    public CourtCalledtoCamelot() : base ("Court Called to Camelot")
    {

    }

    public class CardRecognition : CardCriteria
    {
        bool CardCriteria.DoesMeetCriteria(Card card)
        {
            if (card.GetCardType() == CardTypes.Ally)
            {
                return true;
            }
            return false;
        }
    }

    public override void doEffect(GameState gState)
    {
        Player[] players = gState.getPlayersInRankOrder();


        foreach (Player player in gState.getPlayers())
        {
            player.RemoveCardsFromBoardWithCriteria(new CardRecognition());
        }

    }

}
