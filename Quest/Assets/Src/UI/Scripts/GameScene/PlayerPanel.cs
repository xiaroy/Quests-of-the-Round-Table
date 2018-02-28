using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerPanel : MonoBehaviour {

    Transform cardPanel;
    Transform rankPanel;
    Text nameText;
    Text rankText;
    Text shieldText;

	// Use this for initialization
	void Start () {
		
	}
	
    public void Setup(Card[] cards, string name, Rank rank, int handCardCount, bool displayRank)
    {
        nameText.text = name;
        rankText.text = "Rank: " + Rank.GetRankString(rank.getCurrentRank());
        shieldText.text = "Sheilds: " + rank.getCurrentShields();
    }

    public void UpdateFields(Card[] cards, Rank rank)
    {
        rankText.text = "Rank: " + Rank.GetRankString(rank.getCurrentRank());
        shieldText.text = "Sheilds: " + rank.getCurrentShields();
    }
}
