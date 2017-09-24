package com.dumbpug.ohm.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.Character;
import com.dumbpug.ohm.character.CharacterPhysicsBox;

/**
 * The physics box for the player.
 */
public class PlayerPhysicsBox extends CharacterPhysicsBox {

    /**
     * Creates a new instance of the PlayerPhysicsBox class.
     * @param character
     * @param x
     * @param y
     */
    public PlayerPhysicsBox(Character character, float x, float y) {
        super(character, x, y, Constants.PLAYER_PHYSICS_SIZE, Constants.PLAYER_PHYSICS_SIZE);
        this.setName("PLAYER");
    }
}
