//-----------------------------------------------------------------------------
//
//QueueEmptyException.java
//Throws a QueueEmptyException when called
//Author: Dillon Ney
//ID: 1705097
//-----------------------------------------------------------------------------

public class QueueEmptyException extends RuntimeException {
	public QueueEmptyException(String s)
	{
		super(s);
	}
}
