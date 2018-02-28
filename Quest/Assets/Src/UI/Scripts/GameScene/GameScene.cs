using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameScene : MonoBehaviour {

    public Transform oppArea;
    public Transform playerBoard;

    BoardScript[] oppBoardScript;
    BoardScript playerBoardScript;

    public Transform playerHand;
    public Transform currentStoryCard;
    public Text gameMsgText;
    public Transform gameMsgButtonBox;
    
    public ObjectPool boardPool;


    // Use this for initialization
    void Start () {
        CreateScreen(3);
    }

    public void CreateScreen(int numberOfOpp)
    {
        oppBoardScript = new BoardScript[numberOfOpp];
        playerBoardScript = playerBoard.GetComponent<BoardScript>();

        for (int i = 0; i < numberOfOpp; i++)
        {
            GameObject newBoard = boardPool.GetObject();
            newBoard.transform.SetParent(oppArea);
            
            oppBoardScript[i] = newBoard.GetComponent<BoardScript>();
            oppBoardScript[i].Resize(oppArea.GetComponent<RectTransform>().sizeDelta.x / numberOfOpp, oppArea.GetComponent<RectTransform>().sizeDelta.y);
            //oppBoardScript[i].Setup(item, this);
        }
    }

    public void Setup(GameView view)
    {
        
    }

    public void UpdateView(GameView view)
    {
        
    }
}
