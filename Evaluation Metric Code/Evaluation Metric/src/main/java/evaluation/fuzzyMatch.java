package evaluation;

import java.io.*;
import java.sql.SQLException;
import evaluation.readtxt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionException;

import org.ejml.simple.SimpleMatrix;
public class fuzzyMatch {
	public static void main (String[] args) throws ClassNotFoundException, SQLException, IOException {
		//（原）
//		String suffix="FCAN5E-4stepE-1Co1000max0.7yita4PER"; //求解出的最优隶属度矩阵保存的目录的上层目录
////		List<String> resultFilePath=readResult("D:\\DATA\\Datasets\\cora\\threads",suffix);
//		List<String> resultFilePath=readResult("D:\\DATA\\Datasets\\cora\\",suffix); //最优隶属度矩阵保存的目录的上层的上层目录 //（原）


		String suffix="Desktop"; //求解出的最优隶属度矩阵保存的目录的上层目录
		//List<String> resultFilePath=readResult("D:\\DATA\\Datasets\\cora\\threads",suffix);

		List<String> resultFilePath=readResult("C:\\Users\\dell\\",suffix);

//		String suffix="698"; //求解出的最优隶属度矩阵保存的目录的上层目录
//		List<String> resultFilePath=readResult("E:datasets\\facebook_All\\result\\facebook\\",suffix);
//		System.out.println(resultFilePath);

		//自己加的批处理
//		String DataSetName="facebook";
//		String yueDirsname= String.format("D:\\DATA\\Datasets\\%s", DataSetName);
//		File f=new File(yueDirsname);
//		String[] dirsArray=f.list();
//		for(String yueStr:dirsArray) {
//			String stemp=yueStr;
//			//String stemp = "107172099";
//			String suffix = String.format("%s", stemp);
//			//List<String> resultFilePath = readResult(String.format("C:\\Users\\dell\\Desktop\\experiment\\methodOfEveryrow\\objFunOfSimplify\\%s\\", DataSetName), suffix);
//			List<String> resultFilePath = readResult(String.format("E:\\datasets\\facebook_All\\result\\%s\\", DataSetName), suffix);
			double NMISUM = 0;
			double ACCSUM = 0;
			for (String fileTemp : resultFilePath) {
				String vertexFile="D:\\DATA\\Datasets\\cora\\graph"; //原图数据集 //（原）
				//String vertexFile="D:\\DATA\\Datasets\\citeseer\\graph";
				//String vertexFile="D:\\DATA\\Datasets\\facebook\\698\\graph";
				//String vertexFile="D:\\DATA\\Datasets\\twitter\\123684709\\graph";
				//			String vertexFile="D:\\DATA\\Datasets\\cora\\graph";

				//String vertexFile = String.format("D:\\DATA\\Datasets\\%s\\%s\\graph",DataSetName, stemp);

				//			String membershipFile="F:\\DATA\\toydatasets\\1000\\graphG0.1-1@22\\";
				String membershipFile = fileTemp + "\\";
				String truth="D:\\DATA\\Datasets\\cora\\ground_truth"; //原图数据集类别的真实情况 //（原）
				//String truth="D:\\DATA\\Datasets\\citeseer\\ground_truth"; //原图数据集类别的真实情况 //（原）
				//String truth="D:\\DATA\\Datasets\\facebook\\698\\ground_truth";
				//String truth="D:\\DATA\\Datasets\\twitter\\123684709\\ground_truth";

				//String truth = String.format("D:\\DATA\\Datasets\\%s\\%s\\ground_truth",DataSetName, stemp);

				//
				//			String truth="D:\\DATA\\Datasets\\cora\\ground_truth";
				//		String vertexFile="F:\\DATA\\graph";
				//		String membershipFile="F:\\DATA\\graphG\\";
				//		String truth="F:\\DATA\\ground_truth";

				List<String> vertexList = readtxt.readVertexList(vertexFile);
				double[][] membership = readtxt.readMembershipMatrix(membershipFile, 1);
				SimpleMatrix membershipMatrex = new SimpleMatrix(membership);
				NMIPerformance nmi = new NMIPerformance(vertexList, membershipMatrex, truth);
				try {
					nmi.run();
				} catch (Exception e) {
					System.out.print(fileTemp);
				}
				NMISUM += nmi.getNMI();
				ClassificationOverviewPerformance acc = new ClassificationOverviewPerformance(vertexList, membershipMatrex, truth);
				acc.run();
				ACCSUM += acc.getAccuracy();


			}
			double NMI = NMISUM / (double) resultFilePath.size(); //评估指标NMI
			double ACC = ACCSUM / (double) resultFilePath.size(); //评估指标ACC
			//		List<String> interation=readIteration("D:\\DATA\\Datasets\\cora\\threads",suffix);
			//		List<String> timePerIter=readTimePerIter("D:\\DATA\\Datasets\\cora\\threads",suffix);
			//
			//		double interator = average(interation);
			//		double timePer = average(timePerIter);
			//
			System.out.println("NMI");
			System.out.println(NMI);
			System.out.println("=========================");

			System.out.println("ACC");
			System.out.println(ACC);
			System.out.println("=========================");

			//将其存储到文件中
//			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(String.format("E:\\datasets\\%s_All\\result\\NMI_ACC\\result24.txt", DataSetName),true));
//			osw.write(String.format("%.10f %.10f\n", NMI, ACC));
//
//			osw.close();


			//
			//		System.out.println(interator);
			//
			//		System.out.println(timePer);

			//		String[][] cora1500 = readtxt.createArray("F:\\DATA\\graphFCANa.txt");
			//		String[][] coraExpect = readtxt.createArray4truth("F:\\DATA\\ground_truth");
			////		NMIPerformance nmi=new NMIPerformance(vertexList,membershipMatrex,truth);
			////		nmi.run();
			////		System.out.println(nmi.getNMI());
			//////		System.out.println(nmi.getReport());
			////		System.out.println(nmi.getReport());
			//
			//		ClassificationOverviewPerformance acc=new ClassificationOverviewPerformance(vertexList,membershipMatrex,truth);
			//		acc.run();
			//		System.out.println("=========================");
			//
			//		System.out.println(acc.getAccuracy());
			//		System.out.println(acc.getReport());
		}

	//}
	public static List<String> readResult(String filePlace,String communitName) {
		List<String> resultFilePath =  new ArrayList<String>();
		File file = new File(filePlace); // 创建File对象
		if (file.isDirectory()) { // 判断File对象对应的目录是否存在
			String[] names = file.list(); // 获得目录下的所有文件的文件名
				for (String name : names) {
					if(name.indexOf(communitName)!=-1)
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
						String interator = name.split("@")[1].split("#")[0];
						resultIteration.add(interator); 
					}
				}
		}
		return resultIteration;
	}
	public static List<String> readTimePerIter (String filePlace,String communitName) {
		List<String> resultIteration =  new ArrayList<String>();
		File file = new File(filePlace); // 创建File对象
		if (file.isDirectory()) { // 判断File对象对应的目录是否存在
			String[] names = file.list(); // 获得目录下的所有文件的文件名
			for (String name : names) {
				if(name.indexOf(communitName)!=-1) {
					String timePerIter = name.split("@")[1].split("#")[1];
					resultIteration.add(timePerIter);
				}
			}
		}
		return resultIteration;
	}
	public static double average(List<String> list) {
		double sum = 0;
		for(String element : list) {
			double temp = Double.parseDouble(element);
			sum+=temp;
		}
		return sum/(double)list.size();
	}
}


