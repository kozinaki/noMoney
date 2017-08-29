package auction.exception;

public class SqlResultException extends Exception {

	public SqlResultException() {}
	
	public SqlResultException(String message) {
		super(message);
	}
	
	public SqlResultException(Throwable cause) {
		super(cause);
	}
	
	public SqlResultException(String message, Throwable cause) {
		super(message, cause);
	}
}
