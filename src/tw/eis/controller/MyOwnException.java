package tw.eis.controller;

public class MyOwnException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public MyOwnException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}    
     
}
