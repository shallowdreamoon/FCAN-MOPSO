package evaluation;

import evaluation.SharedFunctions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ejml.simple.SimpleMatrix;

public class ClassificationOverviewPerformance {
	public static Logger logger = Logger.getLogger(ClassificationOverviewPerformance.class);
	public static String GroundTruthDelimiter = ":";
	
	private String classificationFile;
	private List<String> vertexList;
	private SimpleMatrix membershipMatrix;
	private Map<String, String> classificationMap;
	private List<Map<String, Integer>> classificationOverviewMap;
	private String report;
	private float accuracy;
	
	public ClassificationOverviewPerformance(List<String> vertexList, SimpleMatrix membershipMatrix, String classificationFile) {
		this.classificationFile = classificationFile;
		this.vertexList = vertexList;
		this.membershipMatrix = membershipMatrix;
	}
	
	public void run() {
		if (this.membershipMatrix == null) {
			this.accuracy = 0;
			this.report = "Null Membership Matrix";
			return;
		}
		
		this.classificationMap = SharedFunctions.getVertexClassificationMap(this.vertexList, this.classificationFile);
		
		int[] finalMemberShipArray = SharedFunctions.getDeterminateMembershipArray(membershipMatrix, this.vertexList.size());
		
		this.classificationOverviewMap = SharedFunctions.getClassificationDistributionMap(this.membershipMatrix.numCols(), finalMemberShipArray, this.vertexList, this.classificationMap);
		
		save(finalMemberShipArray);
		
		this.report = this.getClassificationOverview();
		
		this.report += "\n\n\n\n" + this.calculateAccuracy();
	}
	
	private void save(int[] finalMemberShipArray) {
		// TODO Auto-generated method stub
		String content = "";
		for (int i = 0; i < this.vertexList.size(); i++) {
			content += (content.length() == 0 ? "" : "\n") + this.vertexList.get(i) + "," + finalMemberShipArray[i] + "," + this.classificationMap.get(this.vertexList.get(i));
		}
		
		SharedFunctions.saveFile(this.classificationFile + "-result", content);
	}

	public String getReport() {
		return this.report;
	}
	
	public float getAccuracy() {
		return this.accuracy;
	}
	
	private String calculateAccuracy() {
		// TODO Auto-generated method stub
		List<String> classificationList = new ArrayList<String>();
		List<Integer> countList = new ArrayList<Integer>();
		
		for (int i = 0; i < this.classificationOverviewMap.size(); i++) {
			for (String classfication : this.classificationOverviewMap.get(i).keySet()) {
				classificationList.add(i + GroundTruthDelimiter + classfication);
				countList.add(this.classificationOverviewMap.get(i).get(classfication));
			}
		}
		
		// sort classification list by count list
		this.sort(classificationList, countList);
		
		// select max count and the matched classification for each cluster
		Map<String, Integer> classificationCountMap = this.selectClassificationForCluster(classificationList, countList);
		
		int sum = 0;
		for (String classification : classificationCountMap.keySet()) {
			sum += classificationCountMap.get(classification);
		}
		
		this.accuracy = sum / (float) this.vertexList.size();
		
		return "Accuracy: " + accuracy + " (" + sum + "/" + this.vertexList.size() + ")";
	}

	private Map<String, Integer> selectClassificationForCluster(
			List<String> classificationList, List<Integer> countList) {
		// TODO Auto-generated method stub
		Set<String> selectedClusters = new HashSet<String>();
		Set<String> selectedClassifications = new HashSet<String>();
		Map<String, Integer> classificationCountMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < classificationList.size(); i++) {
			String item = classificationList.get(i);
			String[] parts = item.split(GroundTruthDelimiter);
			
			if (selectedClusters.contains(parts[0]) 
					|| selectedClassifications.contains(parts[1]))
				continue;
			
			selectedClusters.add(parts[0]);
			selectedClassifications.add(parts[1]);
			
			if (classificationCountMap.containsKey(parts[1])) {
				logger.error("Found duplicate entry for the classification " + parts[1]);
			} else {
				classificationCountMap.put(parts[1], countList.get(i));
			}
		}
		
		return classificationCountMap;
	}

	private void sort(List<String> classificationList, List<Integer> countList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < countList.size() - 1; i++) {
			for (int j = i + 1; j < countList.size(); j++) {
				if (countList.get(i) < countList.get(j)) {
					Collections.swap(countList, i, j);
					Collections.swap(classificationList, i, j);
				}
			}
		}
	}

	private String getClassificationOverview() {
		// TODO Auto-generated method stub
		String overviewReport = "";
		
		for (int i = 0; i < this.classificationOverviewMap.size(); i++) {
			String lineResult = i + ":\t";
			
			for (String key : this.classificationOverviewMap.get(i).keySet()) {
				lineResult += key + "(" + this.classificationOverviewMap.get(i).get(key) + ")\t";
			}
			
			overviewReport += lineResult + "\n";
		}
		
		return overviewReport;
	}
}
