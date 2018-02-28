using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SampleCard : MonoBehaviour {

    public Button button;
    public Image image;

    private Card item;
    private ScrollList scrollList;

    // Use this for initialization
    void Start () {
		
	}

    public void Setup(Card currItem, ScrollList currScrollList)
    {
        item = currItem;
        
        image.sprite = currItem.artwork;

        scrollList = currScrollList;
    }

}
