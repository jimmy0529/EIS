package tw.eis.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import tw.eis.model.Users;

public class GenerateLinkUtil {
	
	//private final String CHECK_CODE = "checkCode";
	
	public String generateResetPwdLink(Users uBean) {
		return "http://localhost:8080/EIS/login/resetPassword?userName=" 
				+ uBean.getUserName();
				//+ "&" + CHECK_CODE + "=" + generateCheckcode(uBean);
		}
	
//	public String generateCheckcode(Users uBean) {
//		String userName = uBean.getUserName();
//		String randomCode = uBean.getuUID();
//		return md5(userName + ":" + randomCode);
//	}
	
	private String md5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String bytes2Hex(byte[] byteArray)
	{
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
		{
			if(byteArray[i] >= 0 && byteArray[i] < 16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}


}
