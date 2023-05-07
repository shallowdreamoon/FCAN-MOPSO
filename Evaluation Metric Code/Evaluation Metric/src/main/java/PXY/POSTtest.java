package PXY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class POSTtest {
	public static String post(String url1,String json){
		
		String str2 = "";
		System.out.println(url1);
		System.out.println(json);
		BufferedReader reader = null;
		try {
			URL url = new URL(url1);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			// connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			//一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(json);
			out.flush();
			out.close();
			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			String res = "";
			while ((line = reader.readLine()) != null) {
				res += line;
			}
			reader.close();

		   String str1 = URLDecoder.decode(res.toString(),"UTF-8");
		   str2 = URLDecoder.decode(str1,"UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("请求发生错误！");
			e.printStackTrace();
		}
		return str2;
	}
	
	public static String sendPost(String url1,String json){
		
		String res = "";
		System.out.println(url1);
		System.out.println(json);
		BufferedReader reader = null;
		try {
			URL url = new URL(url1);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			// connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			//一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(json);
			out.flush();
			out.close();
			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				res += line;
			}
			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("请求发生错误！");
			e.printStackTrace();
		}
		return res;
	}
}
