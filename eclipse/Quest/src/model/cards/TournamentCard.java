package model.cards;

import model.game.GameState;

public class TournamentCard extends StoryCard {

	public int reward;

    public TournamentCard(String name, int reward)
    {
    	super(name, CardTypes.Tournament);
        this.reward = reward;
    }

    public int getReward(int participants) { return reward + participants; }

    public void doEffect(GameState gState)
    {
        gState.startTournament(this);
    }
}
