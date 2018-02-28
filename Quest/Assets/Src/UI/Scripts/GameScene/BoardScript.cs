using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BoardScript : MonoBehaviour {

    public Transform thisPanel;
    public Transform cardPanel;
    public Transform rankPanel;
    public Text nameText;
    public Text rankText;
    public Text shieldText;

    public ObjectPool cardPool;

    // Use this for initialization
    void Start () {
		
	}

    public void Resize(float width, float height)
    {
        Debug.Log(width);
        thisPanel.GetComponent<RectTransform>().sizeDelta.Set(width, height);
        cardPanel.GetComponent<RectTransform>().sizeDelta.Set(width, height - 50);
        rankPanel.GetComponent<RectTransform>().sizeDelta.Set(width, 50);
    }
	
	public void SetupBoard(string name, Rank rank, Card[] cards)
    {
        nameText.text = "Name: " + name;
        rankText.text = "Rank: " + Rank.GetRankString(rank.getCurrentRank());
        shieldText.text = "Shield: " + rank.getCurrentShields();
    }

    public void UpdateBoard(Rank rank, Card[] cards)
    {
        rankText.text = "Rank: " + Rank.GetRankString(rank.getCurrentRank());
        shieldText.text = "Shield: " + rank.getCurrentShields();
    }

    private void AddCards(Card[] cards)
    {
        foreach (Card card in cards)
        {
            GameObject newButton = cardPool.GetObject();
            newButton.transform.SetParent(cardPanel);

            CardButtonScript cardButton = newButton.GetComponent<CardButtonScript>();
            cardButton.Setup(card);
        }
    }

    private void RemoveCards()
    {
        while (cardPanel.childCount > 0)
        {
            GameObject toRemove = transform.GetChild(0).gameObject;
            cardPool.ReturnObject(toRemove);
        }
    }
}
