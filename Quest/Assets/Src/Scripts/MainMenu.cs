using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System;

public class MainMenu : MonoBehaviour {

	// Use this for initialization
	public void PlayGame()
    {
        SceneManager.LoadScene("board",LoadSceneMode.Single);
    }
    public void Back()
    {
        SceneManager.LoadScene("beginning", LoadSceneMode.Single);
    }
    public void Quit()
    {
        Debug.Log("Thanks for Playing Byeeeeeeeee!!!!!!");
        //Console.WriteLine("Byeeeeeeeeeeeeeee!!!1");
        Application.Quit();
    }
}
