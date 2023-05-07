package evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class readtxt {
	public static List readTxtFile (String filePath) {
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "UTF-8";
			File file = new File (filePath);
			if(file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine())!=null) {
					list.add(lineTxt);
				}
				read.close();
			}else {
				System.out.println("找不到文件");
			}
		}catch (Exception e) {
			System.out.println("出错了");
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public static String[][] createArrayMCODE (String filePath){
		List<String> list = readTxtFile(filePath);
		String array[][] = new String[list.size()-10][];
		for(int i=10;i<list.size();i++) {
			
			String linetxt=list.get(i);
			String[] myArray = linetxt.replaceAll("\t","@").split("@")[4].replaceAll(", ", "#").split("#");
			array[i-10]=new String[myArray.length];
			for(int j=0;j<myArray.length;j++) {
				array[i-10][j]=myArray[j];
			}
		}
		return array;
	}
	
	public static String[][] createArrayMCODE2 (String filePath) throws ClassNotFoundException, SQLException{
		List<String> list = readTxtFile(filePath);
		String array[][] = new String[list.size()-10][];
		for(int i=10;i<list.size();i++) {
			
			String linetxt=list.get(i);
			String[] myArray = linetxt.replaceAll("\t","@").split("@")[4].replaceAll(", ", "#").split("#");
			array[i-10]=new String[myArray.length];
			for(int j=0;j<myArray.length;j++) {
				array[i-10][j]=myArray[j];
			}
		}
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url1="jdbc:mysql://localhost:3306/biodata";
		String url2="?user=root&password=admin";
		String url3="&useUnicode=true&characterEncoding=UTF-8";
		String url=url1+url2+url3+"&serverTimezone=UTC";
		
		Class.forName(driverName);
		
		Connection conn=DriverManager.getConnection(url);
		
		String sql="SELECT gene_id FROM biodata.id_mapping where uniprotkb_ac =? ;";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		for(int x=0; x<array.length; x++) {
			 for(int y=0; y<array[x].length; y++) {
				pstmt.setString(1,array[x][y]);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.beforeFirst();
				
				while(rs.next()) {
					if(rs.getString(1).equals("")) {
	    	  			
	    	  		}else {
	    	  			//System.out.println(rs.getString(1));
	    	  			array[x][y]=rs.getString(1).split(";")[0];
	    	  		}
	    	  	}
	    	  	rs.beforeFirst();
	    	  	rs.close();
			 }
		}

		return array;
	}
	
	public static String[][] createArray (String filePath){
		List<String> list = readTxtFile(filePath);
		int size = 0;
		String array[][] = new String[list.size()][];
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			
			String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
			if(myArray.length>1) {
				size ++;
			}
			array[i]=new String[myArray.length];
			for(int j=0;j<myArray.length;j++) {
				array[i][j]=myArray[j];
			}
		}
		String array2[][] = new String[size][];
		int temp = 0;
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			
			String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
			if(!(myArray.length<=1)) {
				array2[temp]=new String[myArray.length];
				for(int j=0;j<myArray.length;j++) {
					array2[temp][j]=myArray[j];
				}
				temp++;
			}
		}
		return array2;
	}
	public static double[][] readMembershipMatrix (String filePath,int index){
		//String filePath = "F:\\FCAN\\cluster4SIMGavin1000-"+c+"\\";
				//String filePath = "F:\\DATA\\graphFCANa\\";
				//String filePath = "F:\\FCM\\FCMDIPhsapi1500\\";
				int fileindex=1;
				List<String> list = new ArrayList<String>();
				while(fileindex <= index)
				{
					list.addAll(readTxtFile(filePath+fileindex)); 
					fileindex++;
				}
				
				double array[][] = new double[list.size()][];
				for(int i=0;i<list.size();i++) {
					
					String linetxt=list.get(i);
					String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
					array[i]=(new double[myArray.length]);
					for(int j=0;j<myArray.length;j++) {
						array[i][j]=Double.parseDouble(myArray[j]);
					}
				}
				return array;
	}
	public static List<String> readVertexList (String filePath){
		List<String> list = readTxtFile(filePath);
		List<String> list2 = new ArrayList<>();

		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			
			String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
//			if(i==100) {
//				System.out.print("asdfsadf");
//			}
			if(myArray[0].equals("v")) {
				list2.add(myArray[1]);
			}
		}
		return list2;
	}

	
	public static String[][] createArray4truth (String filePath){
		List<String> list = readTxtFile(filePath);
		
		String array[][] = new String[list.size()][];
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			
			String[] myArray = linetxt.replaceAll(",","@").split("@");
			array[i]=new String[myArray.length];
			for(int j=0;j<myArray.length;j++) {
				array[i][j]=myArray[j];
			}
		}
		Map<Integer, List<String>> map = new HashMap<>();
		
		for(int i=0;i<array.length;i++) {
			Integer temt=Integer.valueOf(array[i][1].substring(array[i][1].length()-1))-1;
			if(map.containsKey(temt)) {
				map.get(temt).add(array[i][0]);
			}else {
				List<String> listtemp = new ArrayList<>();
				listtemp.add(array[i][0]);
				map.put(temt, listtemp);
			}
		}
		String array2[][] = new String[map.size()][];
		int index=0;
		 for (Integer key : map.keySet()) {
	         array2[index]=new String[map.get(key).size()];
	         array2[index]=map.get(key).toArray(new String[map.get(key).size()]);
	         index++;
	    }
		return array2;
	}
	
	public static String getPvalue (String filePath){
		List<String> list = readTxtFile(filePath);
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			String[] myArray = linetxt.replaceAll("\\t","@").split("@");
			if(myArray[0].equals("Min p-value")) {
				return myArray[1];
			}
		}
		return "";
	}
	
	public static List<String> getV (String filePath){
		List<String> list = readTxtFile(filePath);
		
		return list;
	}
	
		public static Map<String, List<String>> getAttribueArray (String filePath){
			List<String> list = readTxtFile(filePath);
			Map<String, List<String>> map = new HashMap<>();
			for(int i=0;i<list.size();i++) {
				String linetxt=list.get(i);
				String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
				List<String> list2=new ArrayList<>();
				
				for(int j=1;j<myArray.length;j++) {
					if(myArray[j].equals("1"))
						list2.add(String.valueOf(j));
				}
				map.put(myArray[0], list2);
			}
			return map;
		}
	
	public static String[][] createArray4EGCPI (String filePath){
		List<String> list = readTxtFile(filePath);
		String array[][] = new String[list.size()/2][];
		int index=0;
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			if(!linetxt.startsWith("Cluster")) {
					String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
					array[index]=new String[myArray.length];
					for(int j=0;j<myArray.length;j++) {
						array[index][j]=myArray[j];
					}
					index++;
			}
		}
		return array;
	}
	
	public static String[][] createArray2 (String filePath) throws ClassNotFoundException, SQLException{
		List<String> list = readTxtFile(filePath);
		int size = 0;
		String array[][] = new String[list.size()][];
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			
			String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
			if(myArray.length>1) {
				size ++;
			}
			array[i]=new String[myArray.length];
			for(int j=0;j<myArray.length;j++) {
				array[i][j]=myArray[j];
			}
		}
		String array2[][] = new String[size][];
		int temp = 0;
		for(int i=0;i<list.size();i++) {
			
			String linetxt=list.get(i);
			
			String[] myArray = linetxt.replaceAll("\\s+","@").split("@");
			if(!(myArray.length<=1)) {
				array2[temp]=new String[myArray.length];
				for(int j=0;j<myArray.length;j++) {
					array2[temp][j]=myArray[j];
				}
				temp++;
			}
		}
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url1="jdbc:mysql://localhost:3306/biodata";
		String url2="?user=root&password=admin";
		String url3="&useUnicode=true&characterEncoding=UTF-8";
		String url=url1+url2+url3+"&serverTimezone=UTC";
		
		Class.forName(driverName);
		
		Connection conn=DriverManager.getConnection(url);
		
		String sql="SELECT gene_id FROM biodata.id_mapping where uniprotkb_ac =? ;";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		for(int x=0; x<array2.length; x++) {
			 for(int y=0; y<array2[x].length; y++) {
				pstmt.setString(1,array2[x][y]);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs.beforeFirst();
				
				while(rs.next()) {
					if(rs.getString(1).equals("")) {
	    	  			
	    	  		}else {
	    	  			//System.out.println(rs.getString(1));
	    	  			array2[x][y]=rs.getString(1).split(";")[0];
	    	  		}
	    	  	}
	    	  	rs.beforeFirst();
	    	  	rs.close();
			 }
		}

		return array2;
	}
}
