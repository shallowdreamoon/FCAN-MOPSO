package PXY;


import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;






/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
//		 String sign = Base64.encodeBase64String(RSAToolkit.signSHA256WithRSA(Base64.decodeBase64(Constants.privatekey90), "123".getBytes("UTF-8")));
//			if(RSAToolkit.verifySignSHA256WithRSA(Base64.decodeBase64(Constants.publickey90), "123".getBytes(), Base64.decodeBase64(sign))) {
//				System.out.print("ads");
//			}

    	JSONObject dataObject = new JSONObject();
    	dataObject.put("requestId", "2019110115014684005300");
    	dataObject.put("orgId", "8523873");
    	dataObject.put("orgName", "中外运久凌储运有限公司武汉分公司");
    	dataObject.put("taxWaybillId", "12965576");
    	dataObject.put("xid", "70952257");
    	dataObject.put("taxWaybillNo", "ZF-JDFDCY1910062");
    	dataObject.put("taxPosInfoId", "12799713");
    	dataObject.put("serialNumber", "9652154293");
    	dataObject.put("payState", "2");
    	dataObject.put("waybillPayState", "5");
    	dataObject.put("payActualMoney", "500.00");
    	dataObject.put("payableFreight", "500.00");
    	dataObject.put("payAppTime", "2019-11-01 10:22:07");
    	Date now = new Date();
    	dataObject.put("payTime", "2019-11-01 15:01:45");
		 String sign = Base64.encodeBase64String(RSAToolkit.signSHA256WithRSA(Base64.decodeBase64(Constants.privatekey90), dataObject.toString().getBytes("UTF-8")));
		JSONObject object = new JSONObject();
		object.put("sign", sign);
		object.put("notifyType", "ADD.POS");
		object.put("data", dataObject.toString());
		String url ="http://localhost:8080/ssm/pushPayment/info";
		String json = object.toString();
		//String urlStr = java.net.URLEncoder.encode(json, "utf-8");

//		String urlStr = java.net.URLEncoder.encode(json, "utf-8");
//		String urlStr2 = java.net.URLEncoder.encode(urlStr, "utf-8");
		String result = POSTtest.post(url, json);
		//String result = POSTtest.post(url, json);

		JSONObject object2 = new JSONObject(result);
		Integer reCode=object2.getInt("reCode");
	    String reInfo=object2.getString("reInfo");
	    System.out.println(reCode);
	    System.out.println(reInfo);

    }
}
