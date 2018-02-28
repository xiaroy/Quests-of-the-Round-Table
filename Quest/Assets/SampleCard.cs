using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SampleCard : MonoBehaviour {

    public Button button;
    public Image image;

    private CardModel item;
    private ScrollList scrollList;

    // Use this for initialization
    void Start () {
        button.onClick.AddListener(HandleClick);
        
	}

    public void Setup(CardModel currItem, ScrollList currScrollList)
    {
        item = currItem;
        
        image.sprite = currItem.image;

        scrollList = currScrollList;
    }
    public void HandleClick()
    {
        scrollList.TransferCard(item);
    }

}
