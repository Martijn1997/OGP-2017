package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Martijn & Flor
 * 
 * @Invar The ship cannot go faster than the speed of light
 * 			|isValidVelocity(TotalVelocity(getXVelocity(), getYVelocity))
 *
 * @Invar The radius of the ship is always lager than 10km
 * 			|isValidRadius(radius)
 */
public class Ship {
	
	
	/**
	 * Initializes an object of the Ship class
	 * @param xPos, the x position of the spaceShip
	 * @param yPos, the y position of the spaceShip
	 * @param orientation, the orientation of the spaceShip
	 * @param radius, the Radius of the spaceShip
	 * 
	 * @effect xPosition is set to the provided xPos
	 * 			| setXPosition(xPos)
	 * @effect yPosition is set to the provided yPos
	 * 			| setYPosition(yPos)
	 * @effect the orientation of the ship is set to the given orientation
	 * 			| setOrientation(orientation)
	 * @effect the radius of the ship is set to the given radius
	 * 			| setRadius(radius)
	 * @effect the velocity is set to the given velocity components xVel and yVel
	 * 			| setVelocity(xVel,yVel)
	 */
	public Ship(double xPos, double yPos, double orientation, double radius, double xVel, double yVel){
		this.setXPosition(xPos);
		this.setYposition(yPos);
		this.setOrientation(orientation);
		this.setRadius(radius);
		this.setVelocity(xVel, yVel);
	}
	
	/**
	 * default constructor for a Ship object
	 * @effect Ship(0,0,0,10)
	 */
	public Ship(){
		this(0d,0d,0d,10d,0,0);
	}
	
	/**
	 * Returns the current x position of the ship
	 */
	@Basic @Raw
	public double getXPosition(){	
		return this.xPosition;
	}
	
	/**
	 * Sets the x position of the ship
	 * 
	 * @post	...
	 * 			|new.getXPosition() = xPos
	 * 
	 * @throws 	IllegalArgumentexception
	 * 			| !isValidposition
	 */
	@Basic @Raw
	public void setXPosition(double xPos) throws IllegalArgumentException{
		if(!isValidPosition(xPos))
			throw new IllegalArgumentException();
		this.xPosition = xPos;
	}
	
	/**
	 * Checks if the Position is a Valid position
	 */
	public static boolean isValidPosition(double Pos){
		return !((Pos == Double.NaN)||(Pos == Double.POSITIVE_INFINITY)
				||(Pos == Double.NEGATIVE_INFINITY));
	}
	
	private double xPosition;
	
	/**
	 * returns the y position of the ship
	 */
	@Basic @Raw
	public double getYPosition(){	
		return this.yPosition;
	}
	
	
	/**
	 * Sets the y position of the ship
	 * 
	 * @post	...
	 * 			|new.getYPosition() = yPos
	 * @throws 	IllegalArgumentException
	 * 			| !isValidPosition
	 */
	@Basic @Raw
	public void setYposition(double yPos) throws IllegalArgumentException{
		if(!isValidPosition(yPos))
			throw new IllegalArgumentException();
		this.yPosition = yPos;
	}
	
	private double yPosition;
	
	
	/**
	 * Returns the x velocity
	 * @return this.xvelocity
	 */
	public double getXVelocity(){
		
		return this.xVelocity;
	}
	
	/**
	 * 
	 *@post if the supplied value for yVel is valid, the velocity it set to yVel
	 * 			| if isValidVelocity(totalVelocity(yVel, this.getXVelocity))
	 * 			| then new.getXVelocity() = xVel
	 */
	@Basic @Raw
	public void setXVelocity(double xVel){
		
		if(isValidVelocity(totalVelocity(xVel, this.yVelocity)))
			this.xVelocity = xVel;
		
	}
	
	private double xVelocity;
	
	/**
	 * Returns the y velocity of the ship
	 */
	public double getYVelocity(){
		return this.yVelocity;
	}
	
	/** 
	 * @post 	if the supplied value for yVel is valid, the velocity it set to yVel
	 * 			| if isValidVelocity(totalVelocity(this.xVelocity, yVel))
	 * 			| then new.getYVelocity() = yVel
	 */
	@Basic @Raw
	public void setYVelocity(double yVel){
		if(isValidVelocity(totalVelocity(this.xVelocity,yVel)))
			this.xVelocity = yVel;
		
	}
	private double yVelocity;
	
	
	
	/**

	 * @post 	if the supplied velocity is valid, the velocity is set to the supplied values
	 * 			| if isValidVelocity(totalVelocity(xVel, yVel))
	 * 			| then new.getXVelocity() = xVel&&
	 * 			| 	   new.getXVelocity() = yVel
	 */
	@Basic @Raw
	public void setVelocity(double xVel, double yVel){
		if (isValidVelocity(totalVelocity(xVel,yVel)))
			this.xVelocity = xVel;
			this.yVelocity = yVel;
	}
	
	/**
	 * @return The total velocity of the ship
	 * 			|result == Math.sqrt(Math.pow(xVel,2) +Math.pow(yVel,2))
	 * 
	 * @return if the sum causes overflow, return positive infinity
	 * 			|result == Double.POSITIVE_INFINITY
	 */
	public double totalVelocity(double xVel, double yVel){
		if (Double.MAX_VALUE - Math.pow(xVel,2) < Math.pow(yVel,2))
			return Double.POSITIVE_INFINITY;
		else
			return Math.sqrt(Math.pow(xVel,2) +Math.pow(yVel,2));
	}
	
	/**
	 * Returns if the given velocity is below the light speed
	 * @return totalVel <= LIGHT_SPEED && totalVel >= 0
	 */
	public static boolean isValidVelocity(double totalVel){
		return (totalVel <= LIGHT_SPEED&& totalVel >= 0);
	}

	public static final double LIGHT_SPEED = 300000;
	
	
	/**
	 * Returns the orientation of the Ship
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;
	}
	
	/**
	 * @pre the angle is between 0 and Math.PI * 2
	 * 			| isValidOrientation(angle)
	 * 
	 * @post the orientation is equal to the given angle
	 * 			| new.getOrientation() = angle
	 */
	@Basic
	public void setOrientation(double angle){
		
		this.orientation = angle;
	}
	
	/**
	 * returns if the given angle is between 0 and 2PI
	 * @return
	 */
	public static boolean isValidOrientation(double angle){
		return (angle <= 2*Math.PI)&&(angle >= 0);
	}
	private double orientation;
	
	/**
	 * Returns the radius of the ship
	 * @return |this.radius
	 */
	@Basic @Raw
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Sets the radius of a ship
	 * @throws  IllegalArgumentException
	 * 			| !isValidRadius(rad)
	 * 		
	 */
	@Basic @Immutable
	public void setRadius(double rad) throws IllegalArgumentException{
		try {
			if(! isValidRadius(rad))
				throw new IllegalArgumentException();
			setRadius(rad);
		} catch (IllegalArgumentException exc){
			this.setRadius(MIN_RADIUS);
		}
		
	}
	
	public static boolean isValidRadius(double rad){
		return rad >= MIN_RADIUS;
	}
	
	// the radius will not change once the radius has been set
	private static double radius;
	
	public final static double MIN_RADIUS = 10;
	
	
	/**
	 * Sets the new position of the ship according to the current speed and given time interval
	 * @param   time
	 * 			the passed time used to move the ship
	 * 
	 * @post 	if the given time is zero, the position of the ship will not change
	 * 			| if(time == 0)
	 * 			| then (new.getXPosition() == getXPosition) &&
	 * 			|	   (new.getYPosition() == getYPosition) 
	 * 
	 * @post	if the given type is nonnegative and not zero the ship is moved
	 * 			| if(time > 0)
	 * 			| then (new.getXPosition() == getXPosition() + getXVelocity()*time)&&
	 * 			|	   (new.getYPosition() == getYPosition() + getYVelocity()*time)
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given value for the time is illegal
	 * 			| ! isValidTime(time)
	 */
	public void move(double time) throws IllegalArgumentException{
		if(!isValidTime(time))
			throw new IllegalArgumentException();
		
		double xPos = this.getXPosition() + time*this.getXVelocity();
		double yPos = this.getYPosition() + time*this.getYVelocity();
		
		this.setXPosition(xPos);
		this.setYposition(yPos);
		
	}
	
	/**
	 * Check whether the given time is a valid time
	 * @param   time
	 * 			the time to check
	 * @return 	True if and only if the time is nonnegative
	 * 			| result == time >= 0
	 */
	public static boolean isValidTime(double time){
		return time>=0;
	}
	
	/**
	 * Turns the ship for a given angle
	 * @param 	angle
	 * 			The angle the ship has to turn
	 * 
	 * @Pre 	The sum of the angle and the old angle of the ship is nonnegative and smaller than 2PI
	 * 			| (angle + getOrientation()) <= 2*Math.PI && (angle + getOrientation() >= 0)
	 * 
	 * @post 	The new orientation is the sum of the angle and the old orientation
	 * 			| new.getOrientation() == angle + getOrientation()
	 */
	public void turn(double angle){
		double newAngle = angle + this.getOrientation();
		assert( (newAngle <= 2*Math.PI)&&(newAngle >= 0) );
		this.setOrientation(newAngle);		
	}
}
