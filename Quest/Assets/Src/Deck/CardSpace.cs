using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public interface CardSpace <T> {

    bool AddCard(T card);
    bool RemoveCard(T card);
    bool ContainsCard(T card);

    int TotalCards();
    T[] GetCards();
}
