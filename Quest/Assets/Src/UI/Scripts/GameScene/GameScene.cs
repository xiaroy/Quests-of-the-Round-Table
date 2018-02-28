using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameScene : MonoBehaviour {

    Transform oppArea;

    InfoPanel infoPanel;
    PlayerPanel playerPanel;
    PlayerPanel[] oppPanels;
    StoryPanel storyPanel;

	// Use this for initialization
	void Start () {
		
	}

    public void Setup(GameView view)
    {
        infoPanel.Setup(view.GetPerspectiveHand(), view.GetPerspectiveName(), view.GetPerspectiveRank());
        playerPanel.Setup(view.GetPerspectiveBoard(), view.GetPerspectiveName(), view.GetPerspectiveRank(), view.GetPerspectiveHand().Length, false);

        for (int i = 0; i < oppPanels.Length && i < view.NumberOfOtherPlayers(); i++)
            oppPanels[i].Setup(view.GetOtherPlayerBoard(i), view.GetOtherPlayerName(i), view.GetOtherPlayerRank(i), view.GetOtherPlayerHandCount(i), true);

        //storyPanel.updateFields();
    }

    public void UpdateView(GameView view)
    {
        infoPanel.UpdateFields(view.GetPerspectiveHand(), view.GetPerspectiveRank());
        playerPanel.UpdateFields(view.GetPerspectiveBoard(), view.GetPerspectiveRank());

        for (int i = 0; i < oppPanels.Length && i < view.NumberOfOtherPlayers(); i++)
            oppPanels[i].UpdateFields(view.GetOtherPlayerBoard(i), view.GetOtherPlayerRank(i));

        //storyPanel.updateFields();
    }
}
