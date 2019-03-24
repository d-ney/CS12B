//-----------------------------------------------------------------------------
//KeyNotFoud.java
//Throws a KeyNotFoundException when called
//Author: Dillon Ney
//ID: 1705097
//-----------------------------------------------------------------------------

public class KeyNotFoundException extends RuntimeException {
	public KeyNotFoundException(String s)
	{
		super(s);
	}
}