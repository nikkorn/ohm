package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Ohm;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.Player;
import com.dumbpug.ohm.player.PlayerColour;
import com.dumbpug.ohm.player.PlayerPhysicsBox;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;
import java.util.ArrayList;

/**
 * Represents the player selection screen state.
 */
public class PlayerSelection implements State {

    @Override
    public void tick(StateManager manager) {

        // Pretend we have picked some players!
        Player player1 = new Player(new PlayerPhysicsBox(0, 0));
        final IngamePlayer ingamePlayer1 = new IngamePlayer(player1, Ohm.getInputProvider(), PlayerColour.RED);

        Player player2 = new Player(new PlayerPhysicsBox(0, 0));
        final IngamePlayer ingamePlayer2 = new IngamePlayer(player2, null, PlayerColour.GREEN);

        // Go straight to a new game.
        manager.setState(new Game(new ArrayList<IngamePlayer>() {{
            add(ingamePlayer1);
            add(ingamePlayer2);
        }}));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(AreaResources.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public StateType getStateType() { return StateType.PLAYER_SELECTION; }
}