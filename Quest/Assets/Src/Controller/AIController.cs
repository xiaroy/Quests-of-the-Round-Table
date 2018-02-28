using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AIController : Controller {

    AIQuestSponorStrategy sponsor;
    AIQuestStrategy quest;
    AITournamentStrategy tournament;
    AIBidStrategy bid;

    public AIController(Player player, ControllerHub hub, AIQuestSponorStrategy sponsorStrat, AIQuestStrategy questStrat, AITournamentStrategy tournamentStrat, AIBidStrategy bidStrat) : base(player, hub)
    {
        this.sponsor = sponsorStrat;
        this.quest = questStrat;
        this.tournament = tournamentStrat;
        this.bid = bidStrat;
    }

    public void setSponsorStrategy(AIQuestSponorStrategy sponsorStrat) { sponsor = sponsorStrat; }
    public void setQuestStrategy(AIQuestStrategy questStrat) { quest = questStrat; }
    public void setTournamentStrategy(AITournamentStrategy tournamentStrat) { tournament = tournamentStrat; }
    public void setBidStrategy(AIBidStrategy bidStrat) { bid = bidStrat; }

    public override ControllerResponse PromptForInput(GameState state, ControllerMessageType type)
    {
        bool boolResp = false;
        Ability[] ablResp = null;

        switch (type)
        {
            case ControllerMessageType.WillSponsor:
                boolResp = sponsor.DoISponorQuest(state, player);
                break;
            case ControllerMessageType.CreateQuestStages:
                ablResp = sponsor.CardsToPlay(state, player);
                break;
            case ControllerMessageType.ParticipateInQuest:
                boolResp = quest.DoIParticipateInQuest(state, player);
                break;
            case ControllerMessageType.PlayForQuest:
                ablResp = quest.CardsToPlay(state, player);
                break;
            case ControllerMessageType.ParticipateInTournament:
                boolResp = tournament.DoIParticipateInTournament(state, player);
                break;
            case ControllerMessageType.PlayForTournament:
                ablResp = tournament.CardsToPlay(state, player);
                break;
        }

        if (ablResp != null)
        {
            foreach (Ability abl in ablResp)
                abl.UseAbility(state, player);
            return ControllerResponse.Continue;
        }
        if (boolResp)
            return ControllerResponse.Yes;
        return ControllerResponse.No;
    }
}

public interface AIQuestSponorStrategy
{
    bool DoISponorQuest(GameState state, Player player);
    Ability[] CardsToPlay(GameState state, Player player);
}

public interface AIQuestStrategy
{
    bool DoIParticipateInQuest(GameState state, Player player);
    Ability[] CardsToPlay(GameState state, Player player);
}

public interface AITournamentStrategy
{
    bool DoIParticipateInTournament(GameState state, Player player);
    Ability[] CardsToPlay(GameState state, Player player);
}

public interface AIBidStrategy
{
    int NumberOfCardsToBid(GameState state, Player player);
    Card[] CardsToDiscard(GameState state, Player player);
}