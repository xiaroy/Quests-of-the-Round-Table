using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SampleText : MonoBehaviour {

    public Text name;
    public Text rank;
    public Text noShields;
    public Player player;

    private Rank rankss;

    public void Start()
    {
        
    }
    public void Setup(Player player)
    {
        name.text = player.GetName();
        rankss = player.GetRank();
        rank.text = rankss.GetRankString(rankss.getCurrentRank());

        noShields.text = rankss.getCurrentShields().ToString();

    }

}
