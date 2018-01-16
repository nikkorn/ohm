package com.dumbpug.ohm.character.enemy;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.CharacterPhysicsBox;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * A generic physics box for enemies.
 */
public abstract class EnemyPhysicsBox<TEnemy extends Enemy> extends CharacterPhysicsBox {

    /** The enemy. */
    private TEnemy enemy;

    /**
     * Creates a new instance of the EnemyPhysicsBox class.
     * @param enemy
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public EnemyPhysicsBox(TEnemy enemy, float x, float y, float width, float height) {
        super(enemy, x, y, width, height);
        this.enemy = enemy;
        // Create the sensors for detecting contact with walls and players.
        createRightSensor();
        createLeftSensor();
        // Give the enemy physics box a name.
        this.setName("ENEMY");
    }

    /**
     * Create the right sensor used for detecting walls and players.
     */
    private void createRightSensor() {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = this.getWidth() / 2f;
        float sensorPosX   = this.getX() + this.getWidth();
        float sensorPosY   = this.getY() + (this.getWidth() / 4f);
        // Create the sensor.
        NBPSensor rightSensor = new NBPSensor(sensorPosX, sensorPosY, Constants.ENEMY_HORIZONTAL_SENSOR_WIDTH, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        rightSensor.setName("sensor_right");
        // Attach the sensor to the character box.
        attachSensor(rightSensor);
    }

    /**
     * Create the left sensor used for detecting walls and players.
     */
    private void createLeftSensor() {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = this.getWidth() / 2f;
        float sensorPosX   = this.getX() - Constants.ENEMY_HORIZONTAL_SENSOR_WIDTH;
        float sensorPosY   = this.getY() + (this.getWidth() / 4f);
        // Create the sensor.
        NBPSensor leftSensor = new NBPSensor(sensorPosX, sensorPosY, Constants.ENEMY_HORIZONTAL_SENSOR_WIDTH, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        leftSensor.setName("sensor_left");
        // Attach the sensor to the character box.
        attachSensor(leftSensor);
    }

    @Override
    public void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
        super.onSensorEntry(sensor, enteredBox);
    }

    @Override
    public void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {
        super.onSensorExit(sensor, exitedBox);
    }
}
