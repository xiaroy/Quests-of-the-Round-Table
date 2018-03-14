package model.cards.abilities;

import model.cards.AdventureCard;
import model.game.GameState;
import model.player.Player;

public abstract class Ability {
	private String name;
    private AdventureCard sourceCard;

    public Ability(String name, AdventureCard sourceCard)
    {
        this.name = name;
        this.sourceCard = sourceCard;
    }

    public abstract boolean CanUseAbility(GameState gState, Player sourcePlayer);
    public abstract void UseAbility(GameState gState, Player sourcePlayer);

    public boolean DoesTarget(GameState gState) { return false; }

    public boolean CanTarget(GameState state, Object obj) { return false; }
    public void SetTarget(GameState state, Object obj) { }

    public String GetName() { return name; }
    public AdventureCard getSourceCard() { return sourceCard; }
}
