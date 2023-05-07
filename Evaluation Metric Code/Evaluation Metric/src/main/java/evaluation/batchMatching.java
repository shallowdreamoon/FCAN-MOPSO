package evaluation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

public class batchMatching {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		String[] community =  new String[123];
		int index=0;
		File file = new File("D:\\DATA\\Datasets\\gplus"); // 创建File对象 (原)
//		File file = new File("D:\\DATA\\Datasets\\cora");
		if (file.isDirectory()) { // 判断File对象对应的目录是否存在
			String[] names = file.list(); // 获得目录下的所有文件的文件名
				for (String name : names) {
					if(name.equals("summary") || name.equals("ground_truth") || name.equals("graph") || name.equals("fuzzy-properties") || name.equals("result")) {
						continue;
					}else {
						community[index]=name; // 输出文件名
						index++;
					}
				}
		}
		
		StringBuilder content = new StringBuilder();
		content.append("communit");
		content.append("\t");
		content.append("NMI");
		content.append("\t");
		content.append("ACC");
		content.append("\t");
		content.append("迭代次数");
		content.append("\n");
		double NMISUMSUM= 0;
		double ACCSUMSUM= 0;
		double interatorSum= 0;
		for(int i = 0 ;i <community.length;i++) {
			content.append(community[i]);
			content.append("\t");
			List<String> resultFilePath=readResult("D:\\DATA\\Datasets\\gplus\\result\\gaijinE-3STEPE-11000MAX1.0yitaOPT",community[i]);
			String vertexFile="D:\\DATA\\Datasets\\gplus\\"+community[i]+"\\graph";
			String truth="D:\\DATA\\Datasets\\gplus\\"+community[i]+"\\ground_truth";
			double NMISUM= 0;
			double ACCSUM= 0;
			
			for(String fileTemp:resultFilePath) {
				String membershipFile=fileTemp+"\\";
				List<String> vertexList= readtxt.readVertexList(vertexFile);
				double[][] membership=readtxt.readMembershipMatrix(membershipFile,1);
				SimpleMatrix membershipMatrex = new SimpleMatrix(membership);
				NMIPerformance nmi=new NMIPerformance(vertexList,membershipMatrex,truth);
				try {
					nmi.run();
				} catch (Exception e) {
					System.out.print(fileTemp);
				} 
				
				NMISUM+=nmi.getNMI();
				ClassificationOverviewPerformance acc=new ClassificationOverviewPerformance(vertexList,membershipMatrex,truth);
				acc.run();
				ACCSUM+=acc.getAccuracy();
			}
			List<String> interation=readIteration("D:\\DATA\\Datasets\\gplus\\result\\gaijinE-3STEPE-11000MAX1.0yitaOPT",community[i]);
			double interator = average(interation);
			double NMI=NMISUM/(double)resultFilePath.size();
			double ACC=ACCSUM/(double)resultFilePath.size();
			
			NMISUMSUM+=NMI;
			ACCSUMSUM+=ACC;
			interatorSum+=interator;
			
			content.append(NMI);
			content.append("\t");
			content.append(ACC);
			content.append("\t");
			content.append(interator);
			content.append("\n");
		}
		content.append("average");
		content.append("\t");
		content.append(NMISUMSUM/(double)community.length);
		content.append("\t");
		content.append(ACCSUMSUM/(double)community.length);
		content.append("\t");
		content.append(interatorSum/(double)community.length);
		content.append("\n");
		saveContentToFile(content.toString(),"D:\\DATA\\Datasets\\gplus\\result\\gaijinE-3STEPE-11000MAX1.0yitaOPTi");
//		List<String> resultFilePath=readAllResult("F:\\DATA\\Datasets\\twitter\\result\\E4");
	}
	private static void saveContentToFile(String content, String file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			logger.error(e.getMessage());
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					logger.error(e.getMessage());
				}
		}
	}
	public static double average(List<String> list) {
		double sum = 0;
		for(String element : list) {
			double temp = Double.parseDouble(element);
			sum+=temp;
		}
		return sum/(double)list.size();
	}
	public static List<String> readAllResult(String filePlace) {
		List<String> resultFilePath =  new ArrayList<String>();
		File file = new File(filePlace); // 创建File对象
		if (file.isDirectory()) { // 判断File对象对应的目录是否存在
			String[] names = file.list(); // 获得目录下的所有文件的文件名
				for (String name : names) {
					resultFilePath.add(filePlace+"\\"+name); // 输出文件名
				}
		}
		return resultFilePath;
	}
	public static List<String> readResult(String filePlace,String communitName) {
		List<String> resultFilePath =  new ArrayList<String>();
		File file = new File(filePlace); // 创建File对象
		if (file.isDirectory()) { // 判断File对象对应的目录是否存在
			String[] names = file.list(); // 获得目录下的所有文件的文件名
				for (String name : names) {
//					if(name.indexOf(communitName)!=-1)
					if(name.indexOf(communitName)==0)

						resultFilePath.add(filePlace+"\\"+name); // 输出文件名
				}
		}
		return resultFilePath;
	}
	public static List<String> readIteration (String filePlace,String communitName) {
		List<String> resultIteration =  new ArrayList<String>();
		File file = new File(filePlace); // 创建File对象
		if (file.isDirectory()) { // 判断File对象对应的目录是否存在
			String[] names = file.list(); // 获得目录下的所有文件的文件名
				for (String name : names) {
					if(name.indexOf(communitName)!=-1) {
						String temp = name.split("@")[1];

						String interator = temp.split("#")[0];
						resultIteration.add(interator); 
					}
				}
		}
		return resultIteration;
	}
}
