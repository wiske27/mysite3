package kr.co.dhflour.mysite.exception;

public class UserDaoException extends RuntimeException {
	
	public UserDaoException() {
		super( "UserDaoException Occurs" );
	}
	
	public UserDaoException( String message ) {
		super( message );
	}
}
