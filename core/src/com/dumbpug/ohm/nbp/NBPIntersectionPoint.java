package com.dumbpug.ohm.nbp;

/**
 * 
 * @author nikolas.howard
 *
 */
public class NBPIntersectionPoint extends NBPPoint {
	// Direction of intersection.
	private NBPMath.NBPIntersectionDirection dir;
	
	public NBPIntersectionPoint(float x, float y, NBPMath.NBPIntersectionDirection direction) {
		super(x, y);	    	
		this.dir = direction;
	}
	
	public NBPIntersectionPoint(NBPPoint p, NBPMath.NBPIntersectionDirection direction) {
		super(p.getX(), p.getY());	    	
		this.dir = direction;
	}
	
	public NBPMath.NBPIntersectionDirection getIntersectionDir() {
		return this.dir;
	}
}
