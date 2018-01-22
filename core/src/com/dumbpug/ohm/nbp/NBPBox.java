package com.dumbpug.ohm.nbp;

import java.util.ArrayList;

/**
 * Represents a box in our physics world.
 */
public abstract class NBPBox {
	// Position
	private float x;
	private float y;
	// Previous Position
	private float lastPosX;
	private float lastPosY;
	// Velocity
	private float velx;
	private float vely;
	// The max velocity for this box
	private float maxVelX = 50f;
	private float maxVelY = 50f;
	// Size
	private float width;
	private float height;
	// Box Type
	private NBPBoxType type;
	// Original Box Type
	private NBPBoxType originalType;
	// Entity name
	private String name = "undefined";
	// Friction
	private float friction = 0f;
	// Restitution
	private float restitution = 0f;
	// List of sensors that are attached to this box.
	private ArrayList<NBPSensor> attachedSensors;
	// Point of origin
	private NBPPoint originPoint;
	// Last point of origin
	private NBPPoint lastOriginPoint;
	// Defines whether this box should be removed from the world at the end of the next physics step.
	private boolean isMarkedForDeletion = false;
	// Defines whether this box has been deleted.
	private boolean isDeleted = false;
	// Is this box affected by gravity?
	private boolean isAffectedByGravity = true;

	/**
	 * Creates a new instance of the NBPBox class.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param type
	 */
	public NBPBox(float x, float y, float width, float height, NBPBoxType type) {
		this.x               = x;
		this.y               = y;
		this.lastPosX        = x;
		this.lastPosY        = y;
		this.width           = width;
		this.height          = height;
		this.type            = type;
		this.originalType    = type;
		this.originPoint     = new NBPPoint(x + (width / 2), y + (height / 2));
		this.lastOriginPoint = new NBPPoint(lastPosX + (width / 2), y + (height / 2));
		attachedSensors      = new ArrayList<NBPSensor>();
	}

	/**
	 * Apply an impulse to this box.
	 * @param x
	 * @param y
	 */
	public void applyImpulse(float x, float y) {
		this.velx += x;
		this.vely += y;
		// Clamp the velocity
		clampVelocity();
	}

	/**
	 * Do our physics update along the X axis.
	 * @param gravity The gravity to apply.
	 */
	public void updateAxisX(NBPGravity gravity) {
		// If this box is static then do nothing!
		if (this.type == NBPBoxType.KINETIC) {
			// Add our gravity to X velocity only if this box is affected by gravity.
			if(this.isAffectedByGravity && gravity != null) {
				switch(gravity.getDirection()) {
					case RIGHT:
						this.velx += gravity.getForce();
						break;
					case LEFT:
						this.velx -= gravity.getForce();
						break;
				}
			}
			// If the velocity is a super small number (between -0.0005 and 0.0005)
			// just set it to zero to stop our boxes infinitely floating around.
			if (velx > -0.0005 && velx < 0.0005) {
				velx = 0f;
			}
			// Clamp our velocity to worlds max.
			clampVelocity();
			// Alter Position
			this.setX(this.x + velx);
		}
	}

	/**
	 * Do our physics update along the Y axis.
	 * @param gravity The gravity to apply.
	 */
	public void updateAxisY(NBPGravity gravity) {
		// If this box is static then do nothing!
		if (this.type == NBPBoxType.KINETIC) {
			// Add our gravity to Y velocity only if this box is affected by gravity.
			if(this.isAffectedByGravity && gravity != null) {
				switch(gravity.getDirection()) {
					case UP:
						this.vely += gravity.getForce();
						break;
					case DOWN:
						this.vely -= gravity.getForce();
						break;
				}
			}
			// If the velocity is a super small number (between -0.0005 and 0.0005)
			// just set it to zero to stop our boxes infinitely floating around.
			if (vely > -0.0005 && vely < 0.0005) {
				vely = 0f;
			}
			// Clamp our velocity to worlds max.
			clampVelocity();
			// Alter Position
			this.setY(this.y + vely);
		}
	}
	
	/**
     * Apply change in velocity to this NBPBox instance using an angle of direction and a force of velocity.
     * @param angle
     * @param force
     */
    public void applyVelocityInDirection(double angle, float force) {
        float impulseX = -(float) (Math.cos(angle) * force);
        float impulseY = -(float) (Math.sin(angle) * force);
        this.applyImpulse(impulseX, impulseY);
    }
    
    /**
     * Apply a bloom to this box.
     * @param bloom
     */
    public void applyBloom(NBPBloom bloom) {
    	// Get the point of the bloom as a NBPPoint object.
		NBPPoint bloomPoint = new NBPPoint(bloom.getX(), bloom.getY());
		// Get the distance 
		float distance = NBPMath.getDistanceBetweenPoints(bloomPoint, getCurrentOriginPoint());
		// Check to see if the box is even in the range of the bloom.
		if(distance <= bloom.getRadius()) {
			// Our box was in the bloom, get angle difference between our bloom and the current box.
			float angleBetweenBloomAndBox = NBPMath.getAngleBetweenPoints(getCurrentOriginPoint(), bloomPoint);
			// Recalculate force based on the distance between our box and bloom. Avoid a divide by zero.
			float force;
			if (distance == 0) {
				force = bloom.getForce() * 1;
			} else {
				force = bloom.getForce() * (1 - (distance/bloom.getRadius()));
			}
			// Apply the force of the bloom to this box.
			if(onBloomPush(bloom, angleBetweenBloomAndBox, force, distance)) {
				applyVelocityInDirection(angleBetweenBloomAndBox, force);
			}
		}
    }

	/**
	 * Clamp the velocity of this box so that it does not exceed its max.
	 */
	private void clampVelocity() {
		if (velx < -this.getMaxVelocityX()) {
			velx = -this.getMaxVelocityX();
		} else if (velx > this.getMaxVelocityX()) {
			velx = this.getMaxVelocityX();
		}
		if (vely < -this.getMaxVelocityY()) {
			vely = -this.getMaxVelocityY();
		} else if (vely > this.getMaxVelocityY()) {
			vely = this.getMaxVelocityY();
		}
	}
	
	/**
	 * Make this a ghost box.
	 */
	public void kill() {
		this.type = NBPBoxType.GHOST;
	}   
	
	/**
	 * Revert this box to its original type.
	 */
	public void revive() {
		this.type = this.originalType;
	}

	/**
	 * Get all attached sensors.
	 * @return sensors
	 */
	public ArrayList<NBPSensor> getAttachedSensors() {
		return this.attachedSensors;
	}

	/**
	 * Attach a sensor.
	 * @param sensor
	 */
	public void attachSensor(NBPSensor sensor) {
		// Don't bother if this sensor is already attached.
		if (!attachedSensors.contains(sensor)) {
			attachedSensors.add(sensor);
			// Set the parent of the sensor to be this box.
			sensor.setParent(this);
		}
	}

	/**
	 * Remove a sensor.
	 * @param sensor
	 */
	public void removeSensor(NBPSensor sensor) {
		// Make sure that this sensor is attached.
		if (attachedSensors.contains(sensor)) {
			attachedSensors.remove(sensor);
		}
	}

	/**
	 * Get the X position of this box.
	 * @return x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Set the X position of this box.
	 * @param newX position
	 */
	public void setX(float newX) {
		// Move attached sensors along with this box.
		if (newX > this.x) {
			for (NBPSensor sensor : this.attachedSensors) {
				sensor.setX(sensor.getX() + (newX - this.x));
			}
		} else if (newX < this.x) {
			for (NBPSensor sensor : this.attachedSensors) {
				sensor.setX(sensor.getX() - (this.x - newX));
			}
		}
		this.lastPosX = x;
		this.x        = newX;
	}

	/**
	 * Get the Y position of this box.
	 * @return y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Set the Y position of this box.
	 * @param newY position
	 */
	public void setY(float newY) {
		// Move attached sensors along with this box.
		if (newY > this.y) {
			for (NBPSensor sensor : this.attachedSensors) {
				sensor.setY(sensor.getY() + (newY - this.y));
			}
		} else if (newY < this.y) {
			for (NBPSensor sensor : this.attachedSensors) {
				sensor.setY(sensor.getY() - (this.y - newY));
			}
		}
		this.lastPosY = y;
		this.y        = newY;
	}

	/**
	 * Gets whether this box is affected by gravity.
	 * @return isAffectedByGravity
	 */
	public boolean isAffectedByGravity() {
		return isAffectedByGravity;
	}

	/**
	 * Sets whether this box is affected by gravity.
	 * @param isAffectedByGravity
	 */
	public void setAffectedByGravity(boolean isAffectedByGravity) { this.isAffectedByGravity = isAffectedByGravity; }

	public float getVelx() {
		return this.velx;
	}

	public void setVelx(float velx) {
		this.velx = velx;
	}

	public void setVely(float vely) {
		this.vely = vely;
	}

	public float getVely() {
		return this.vely;
	}

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}

	public NBPBoxType getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLastPosX() {
		return lastPosX;
	}

	public float getLastPosY() {
		return lastPosY;
	}

	public boolean isMarkedForDeletion() {
		return isMarkedForDeletion;
	}

	public void markForDeletion() {
		this.isMarkedForDeletion = true;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted() {
		this.isDeleted = true;
	}

	public float getMaxVelocityX() {
		return maxVelX;
	}

	public void setMaxVelocityX(float maxVelX) {
		this.maxVelX = maxVelX;
	}

	public float getMaxVelocityY() {
		return maxVelY;
	}

	public void setMaxVelocityY(float maxVelY) {
		this.maxVelY = maxVelY;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		// Friction must be a float value between 0 and 1.
		if (friction < 0) {
			this.friction = 0f;
		} else if (friction > 1f) {
			this.friction = 1f;
		} else {
			this.friction = friction;
		}
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(float restitution) {
		// Restitution must be a float value between 0 and 1.
		if (restitution < 0) {
			this.restitution = 0f;
		} else if (restitution > 1f) {
			this.restitution = 1f;
		} else {
			this.restitution = restitution;
		}
	}

	/**
	 * Get the current origin point of this box.
	 * @return The current origin point of this box.
	 */
	public NBPPoint getCurrentOriginPoint() {
		originPoint.setX(this.x + (width / 2));
		originPoint.setY(this.y + (height / 2));
		return originPoint;
	}

	/**
	 * Get the last origin point of this box.
	 * @return The last origin point of this box.
	 */
	public NBPPoint getLastOriginPoint() {
		lastOriginPoint.setX(this.lastPosX + (width / 2));
		lastOriginPoint.setY(this.lastPosY + (height / 2));
		return lastOriginPoint;
	}

	protected abstract void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision);

	protected abstract void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision);

	protected abstract void onSensorEntry(NBPSensor sensor, NBPBox enteredBox);

	protected abstract void onSensorExit(NBPSensor sensor, NBPBox exitedBox);

	/**
	 * @param bloom
	 * @param angleOfForce
	 * @param force
	 * @param distance
	 * @return whether our box should be affected by this bloom.
	 */
	protected abstract boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance);

	protected abstract void onBeforeUpdate();

	protected abstract void onAfterUpdate();

	protected abstract void onDeletion();
}
