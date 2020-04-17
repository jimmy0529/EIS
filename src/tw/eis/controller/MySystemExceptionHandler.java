package tw.eis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MySystemExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public Object processExceptHandle(Exception e) {
    	String msg1 = "Error:Exception";
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg1);
    }
	
	@ExceptionHandler(MyOwnException.class)
    public Object processMyExceptHandle(MyOwnException e) {
    	String msg2 = "Error:MyOwnException-" + e.getMsg();
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg2);
    }
}
