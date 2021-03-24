package cn.ityihang.zblog.exception;

public class ZblogException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ZblogException(String message){
		super(message);
	}
	
	public ZblogException(Throwable cause)
	{
		super(cause);
	}
	
	public ZblogException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
