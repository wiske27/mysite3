package kr.co.dhflour.mysite.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler( Exception.class )
	public void handlerException( 
		HttpServletRequest request,
		HttpServletResponse response,
		Exception e ) throws Exception {
		//1. 로깅
		e.printStackTrace();
		
		//2. 사과
		request.
		getRequestDispatcher( "/WEB-INF/views/error/error.jsp" ).
		forward( request, response );
	}
}
