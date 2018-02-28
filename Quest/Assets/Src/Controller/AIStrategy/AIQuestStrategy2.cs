using UnityEngine;
using System.Collections;

public class AIQuestStrategy2 : AIQuestStrategy
{
    public Ability[] CardsToPlay(GameState state, Player player)
    {
        //Creating fields to store found cards to play
        WeaponCard wCard = null;
        AllyCard alCard = null;
        AmourCard amCard = null;

        //Searching players list for valid cards to play
        foreach (AdventureCard card in player.getPlayersCards())
        {
            if (card.getBattlePoints(state) >= 10 && player.CanPlayCardToBoard(card))
            {
                if (card.GetCardType() == CardTypes.Weapon)
                    wCard = (WeaponCard)card;
                else if (card.GetCardType() == CardTypes.Amour)
                    amCard = (AmourCard)card;
                else if (card.GetCardType() == CardTypes.Ally)
                    alCard = (AllyCard)card;
            }
        }

        //Getting the ability for playing the appropriate card
        Ability ability = null;
        if (amCard != null)
            ability = amCard.GetAbilities()[0];
        else if (alCard != null)
            ability = alCard.GetAbilities()[0];
        else if (wCard != null)
            ability = wCard.GetAbilities()[0];

        //Returning the ability to play the appropriate card
        if (ability != null)
            return new Ability[] { ability };
        return null;
    }

    public bool DoIParticipateInQuest(GameState state, Player player)
    {
        //Creating counters for the amount of foes and valid cards to play
        int counter = 0, foes = 0;
        //Looping through all the cards in the players list
        foreach (AdventureCard card in player.getPlayersCards())
        {
            //Checking if this card is a foe card
            if (card.GetCardType() == CardTypes.Foe)
                foes++;
            //Checking if this is a valid card to play(needs to check for recurring cards in list that aren't currently in play)
            else if (card.getBattlePoints(state) >= 10 && player.CanPlayCardToBoard(card))
                counter++;

            //Checking if the conditions to participate have been met
            if (counter >= state.getCurrentQuest().GetNumberOfStages() && foes >= 2)
                return true;
        }
        return false;
    }
}
