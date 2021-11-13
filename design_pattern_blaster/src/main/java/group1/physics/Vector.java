package group1.physics;

public class Vector 
{
	private double magnitude;
	private double angle;
	
	public Vector(double mag, double ang)
	{
		magnitude = mag;
		angle = ang;
	}
	
	public double getMagnitude()
	{
		return magnitude;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public void setMagnitude(double mag)
	{
		magnitude = mag;
	}
	
	public void setAngle(double ang)
	{
		angle = ang;
	}
	
	//x component of a vector is equal to its magnitude * cos(angle)
	public double getXComponent()
	{
		return Math.cos(Math.toRadians(angle)) * magnitude;
	}
	
	//y component of a vector is equal to its magnitude * cos(angle)
	public double getYComponent()
	{
		return Math.sin(Math.toRadians(angle)) * magnitude;
	}
}
