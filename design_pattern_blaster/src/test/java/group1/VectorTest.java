package group1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import group1.physics.Vector;

class VectorTest 
{

	@Test
	void getMagnitudeTest() 
	{
		double magnitude = 69.0;
		double angle = 180;
		Vector v = new Vector(magnitude,angle);
		assertEquals(magnitude, v.getMagnitude());
	}

	@Test
	void setMagnitudeTest() 
	{
		double magnitude = 69.0;
		double angle = 180;
		Vector v = new Vector(magnitude,angle);
		assertEquals(magnitude, v.getMagnitude());
		magnitude = 420.0;
		v.setMagnitude(magnitude);
		assertEquals(magnitude,v.getMagnitude());
	}
	
	@Test
	void getAngleTest()
	{
		double magnitude = 69.0;
		double angle = 180;
		Vector v = new Vector(magnitude,angle);
		assertEquals(angle, v.getAngle());
	}
	
	@Test
	void setAngleTest()
	{
		double magnitude = 69.0;
		double angle = 180;
		Vector v = new Vector(magnitude,angle);
		assertEquals(angle, v.getAngle());
		angle = 100;
		v.setAngle(angle);
		assertEquals(angle, v.getAngle());
	}

    @Test
    void getXComponentTest()
    {
        double magnitude = 100.0;
        double angle = Math.toRadians(30.0);
        Vector forceVector = new Vector(magnitude, angle); 

        //x component of a vector is equal to magnitude * cos(angle)
        double expected = Math.cos((angle)) * forceVector.getMagnitude();
        assertEquals(expected, forceVector.getCosComponent());
        System.out.println("dx: " + expected);
    }

    @Test
    void getYComponentTest()
    {
        double magnitude = 100.0;
        double angle = Math.toRadians(30.0);
        Vector forceVector = new Vector(magnitude, angle); 

        //y component of a vector is equal to magnitude * sin(angle)
        double expected = Math.sin((angle)) * forceVector.getMagnitude();
        assertEquals(expected, forceVector.getSinComponent());
        System.out.println("dy: " + expected);
    }

}
