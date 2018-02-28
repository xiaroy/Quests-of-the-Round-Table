using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class InfoPanel : MonoBehaviour {

    HandPanel handPanel;
    ControlPanel controlPanel;

    Text nameText;
    Text rankText;
    Text shieldText;

    public void Setup(Card[] cards, string name, Rank rank)
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
