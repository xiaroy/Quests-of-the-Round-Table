package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import controller.AI.AIBidStrategy;
import controller.AI.AIQuestStrategy;
import controller.AI.AISponsorStrategy;
import controller.AI.AITournamentStrategy;
import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;

public class AIController extends Controller {

	private AISponsorStrategy sponsor;
	private AIQuestStrategy quest;
	private AITournamentStrategy tournament;
	private AIBidStrategy bid;
	
	public AIController(Player player, ControllerHub hub, AISponsorStrategy sponsorStrat, AIQuestStrategy questStrat, AITournamentStrategy tournamentStrat, AIBidStrategy bidStrat) {
		super(player, hub);
		this.sponsor = sponsorStrat;
        this.quest = questStrat;
        this.tournament = tournamentStrat;
        this.bid = bidStrat;
	}
	
	@Override
	public void updateDisplay() {}
	
	public void setSponsorStrategy(AISponsorStrategy sponsorStrat) { sponsor = sponsorStrat; }
    public void setQuestStrategy(AIQuestStrategy questStrat) { quest = questStrat; }
    public void setTournamentStrategy(AITournamentStrategy tournamentStrat) { tournament = tournamentStrat; }
    public void setBidStrategy(AIBidStrategy bidStrat) { bid = bidStrat; }

	@Override
	public ControllerResponse PromptForInput(GameState state, ControllerMessageType type) {
		boolean boolResp = false;
        Ability[] ablResp = null;

        switch (type)
        {
            case WillSponsor:
                boolResp = sponsor.DoISponorQuest(state, player);
                break;
            case CreateQuestStages:
                ablResp = sponsor.CardsToPlay(state, player);
                break;
            case ParticipateInQuest:
                boolResp = quest.DoIParticipateInQuest(state, player);
                break;
            case PlayForQuest:
                ablResp = quest.CardsToPlay(state, player);
                break;
            case ParticipateInTournament:
                boolResp = tournament.DoIParticipateInTournament(state, player);
                break;
            case PlayForTournament:
                ablResp = tournament.CardsToPlay(state, player);
                break;
        }

        if (ablResp != null)
        {
            for (Ability abl : ablResp)
                abl.UseAbility(state, player);
            return ControllerResponse.Continue;
        }
        if (boolResp)
            return ControllerResponse.Yes;
        return ControllerResponse.No;
	}

}
