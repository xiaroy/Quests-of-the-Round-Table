using UnityEngine;
using System.Collections;

[CreateAssetMenu(fileName = "New Tournament Card", menuName = "Tournament Card")]

public class TournamentCard : StoryCard
{
    public int reward;

    public TournamentCard(string name, int reward) : base(name, CardTypes.Tournament)
    {
        this.reward = reward;
    }

    public int getReward(int participants) { return reward + participants; }

    public override void doEffect(GameState gState)
    {
        gState.startTournament(this);
    }
}
