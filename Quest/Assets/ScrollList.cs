using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class ScrollList : MonoBehaviour {

    //public Deck deck;
    public Transform contentPanel;
    public List<Card> list;
    public SimpleObjectPool buttonObjectPool;


	// Use this for initialization
	void Start () {
        RefreshDisplay();
	}
    private void RefreshDisplay()
    {
        AddButtons();
    }

    private void AddButtons()
    {
        for (int i = 0; i < list.Count; i++)
        {
            Card item = list[i];
            GameObject newButton = buttonObjectPool.GetObject();
            newButton.transform.SetParent(contentPanel);

            SampleCard sampleButton = newButton.GetComponent<SampleCard>();
            sampleButton.Setup(item, this);
        }
    }



}
