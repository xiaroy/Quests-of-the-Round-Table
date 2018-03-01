using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class CardModel
{
    public string name;
    public Sprite image;
   // private Card reference;
}
public class ScrollList : MonoBehaviour {

    //public Deck deck;
    public Transform contentPanel;
    public List<CardModel> list;
    public SimpleObjectPool buttonObjectPool;
   // public Transform otherPanel;
    public ScrollList otherPanel;


	// Use this for initialization
	void Start () {
        RefreshDisplay();
	}
    private void RefreshDisplay()
    {
        RemoveButtons();
        AddButtons();
    }

    private void AddButtons()
    {
        for (int i = 0; i < list.Count; i++)
        {
            CardModel item = list[i];
            GameObject newButton = buttonObjectPool.GetObject();
            newButton.transform.SetParent(contentPanel);

            SampleCard sampleButton = newButton.GetComponent<SampleCard>();
            sampleButton.Setup(item, this);
        }
    }

    private void RemoveButtons()
    {
        while (contentPanel.childCount > 0)
        {
            GameObject toRemove = transform.GetChild(0).gameObject;
            buttonObjectPool.ReturnObject(toRemove);
        }
    }
    public void AddCard(CardModel cardToAdd, ScrollList otherList)
    {
    
            otherList.list.Add(cardToAdd);


    }
    public void RemoveCard(CardModel cardToRemove, ScrollList scrollList)
    {
        for (int i = scrollList.list.Count - 1; i >= 0; i--)
        {
            if (scrollList.list[i] == cardToRemove)
            {
                scrollList.list.RemoveAt(i);
            }
        }
    }

    public void TransferCard(CardModel card)
    {
        AddCard(card, otherPanel);
        RemoveCard(card, this);

        RefreshDisplay();
        otherPanel.RefreshDisplay();


    }



}
