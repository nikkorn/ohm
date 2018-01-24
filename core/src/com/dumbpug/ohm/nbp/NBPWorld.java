package com.dumbpug.ohm.nbp;

import com.dumbpug.ohm.nbp.zone.Zone;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents our physical world.
 */
public class NBPWorld {
	// The world gravity.
	private Gravity gravity;
	// The box entities that are in our physics world.
	private ArrayList<Box> boxEntities;
	// The box entities waiting to be added to our world (added during physics update)
	private ArrayList<Box> pendingBoxEntities;
	// List holding any pending Bloom instances to be processed.
	private ArrayList<Bloom> bloomList;
	// List holding any zones of force in the physics world.
	private ArrayList<Zone> zoneList;
	// Are we currently processing a physics step.
	private boolean inPhysicsStep = false;

	/**
	 * Create a new instance of the NBPWorld class with gravity.
	 * @param gravity The world gravity.
	 */
	public NBPWorld(Gravity gravity) {
		boxEntities        = new ArrayList<Box>();
		pendingBoxEntities = new ArrayList<Box>();
		bloomList          = new ArrayList<Bloom>();
		zoneList           = new ArrayList<Zone>();
		this.gravity       = gravity;
	}

	/**
	 * Create a new instance of the NBPWorld class.
	 */
	public NBPWorld() { this(null); }

	/**
	 * Update the box entities in our world.
	 */
	public void update() {
		this.onBeforeWorldUpdate();
		// Mark the start of the physics step.
		inPhysicsStep = true;
		// Remove any boxes which were marked for deletion.
		Iterator<Box> boxIterator = boxEntities.iterator();
		while (boxIterator.hasNext()) {
			Box cbox = boxIterator.next();
			if (cbox.isMarkedForDeletion()) {
				cbox.setDeleted();
				boxIterator.remove();
				// Call user specified behaviour on deletion.
				cbox.onDeletion();
			}
		}
		// Apply any world blooms.
		for (Bloom bloom : bloomList) {
			// Go over all boxes.
			for (Box box : boxEntities) {
				// Make sure this is a kinematic box.
				if(box.getType() == BoxType.KINETIC) {
					// Apply the bloom to this box
					box.applyBloom(bloom);
				}
			}
		}
		// Remove processed world blooms.
		bloomList.clear();
		// Apply zone forces to any intersecting boxes.
		for (Zone zone : zoneList) {
			// Go over all boxes.
			for (Box box : boxEntities) {
				// Make sure this is a kinematic box and that it actually intersects the zone.
				if((box.getType() == BoxType.KINETIC) && zone.intersects(box)) {
					// Allow the zone of force to influence the intersecting box.
					zone.influence(box);
				}
			}
		}
		// Do collision detection and try to handle it.
		for (Box currentBox : boxEntities) {
            currentBox.onBeforeUpdate();
			// Update this box on the X axis.
            currentBox.updateAxisX(this.gravity);
            // Resolve collisions on the X axis.
			if (currentBox.getType() == BoxType.KINETIC) {
				// Get colliding boxes
				for (Box targetBox : boxEntities) {
					// Are these boxes different and do they collide?
					if ((currentBox != targetBox) && NBPMath.doBoxesCollide(currentBox, targetBox)) {
						NBPMath.handleCollision(targetBox, currentBox, CollisionAxis.X);
					}
				}
			}
			// Update this box on the Y axis.
            currentBox.updateAxisY(this.gravity);
            // Resolve collisions on the X axis.
			if (currentBox.getType() == BoxType.KINETIC) {
				// Get colliding boxes
				for (Box targetBox : boxEntities) {
					// Are these boxes different and do they collide?
					if ((currentBox != targetBox) && NBPMath.doBoxesCollide(currentBox, targetBox)) {
						NBPMath.handleCollision(targetBox, currentBox, CollisionAxis.Y);
					}
				}
			}
			// Process the sensors attached to the current box.
			for (NBPSensor sensor : currentBox.getAttachedSensors()) {
				sensor.reviewIntersections(boxEntities);
			}
            currentBox.onAfterUpdate();
		}
		// Mark the end of the physics step.
		inPhysicsStep = false;
		// Any boxes that were added as part of this physics step should be added to our actual entity list now.
		if(pendingBoxEntities.size() > 0) {
			for(Box pendingBox : pendingBoxEntities) {
				this.addBox(pendingBox);
			}
			// Clear the pending list.
			pendingBoxEntities.clear();
		}
		this.onAfterWorldUpdate();
	}

	/**
	 * Add a Static/Kinematic box to the world.
	 * @param box The box to add.
	 */
	public void addBox(Box box) {
		// If this addition is taking place during a physics update, then it should be queued for later addition.
		if(inPhysicsStep) {
			pendingBoxEntities.add(box);
		} else {
			if (!boxEntities.contains(box)) {
				boxEntities.add(box);
			}
		}
	}

	/**
	 * Remove a Static/Kinematic box from the world.
	 * @param box The box to remove.
	 */
	public void removeBox(Box box) {
		if (boxEntities.contains(box)) {
			boxEntities.remove(box);
		}
	}

	/**
	 * Get all boxes in the world.
	 * @return world boxes
	 */
	public ArrayList<Box> getWorldBoxes() {
		return boxEntities;
	}

	/**
	 * Get the world gravity.
	 * @return gravity The world gravity.
	 */
	public Gravity getGravity() {
		return this.gravity;
	}

	/**
	 * Set the world gravity.
	 * @param gravity The world gravity.
	 */
	public void setGravity(Gravity gravity) {
		this.gravity = gravity;
	}
	
	/**
	 * Add a bloom to this world.
	 * @param bloom The bloom to add.
	 */
	public void addBloom(Bloom bloom) {
		this.bloomList.add(bloom);
	}

	/**
	 * Add a zone of force to this physics world.
	 * @param zone The zone of force to add.
	 */
	public void addZone(Zone zone) {
		// Don't add a zone that already exists in this physics world.
		if (!this.zoneList.contains(zone)){
			this.zoneList.add(zone);
		}
	}

	/**
	 * Remove a zone of force from this physics world.
	 * @param zone The zone of force to remove.
	 */
	public void removeZone(Zone zone) {
		// Don't try to remove a zone that doesn't already exist in this physics world.
		if (this.zoneList.contains(zone)){
			this.zoneList.remove(zone);
		}
	}
	
	/**
	 * Called before an update.
	 */
	protected void onBeforeWorldUpdate() {}
	
	/**
	 * Called after an update.
	 */
	protected void onAfterWorldUpdate() {}
}
