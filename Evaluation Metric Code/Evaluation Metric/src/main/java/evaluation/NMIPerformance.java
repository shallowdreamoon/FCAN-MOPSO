package evaluation;

import  evaluation.SharedFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ejml.simple.SimpleMatrix;

public class NMIPerformance {
	public static Logger logger = Logger.getLogger(NMIPerformance.class);
	
	private String classificationFile;
	private List<String> oriVertexList, validVertexList;
	private SimpleMatrix membershipMatrix;
	private Map<String, Integer> classificationCountMap;
	List<Map<String, Integer>> classificationDistributionMap;
	private String report;
	private double nmi;
	
	public NMIPerformance(List<String> vertexList, SimpleMatrix membershipMatrix, String classificationFile) {
		this.classificationFile = classificationFile;
		this.oriVertexList = vertexList;
		this.validVertexList = vertexList;
		this.membershipMatrix = membershipMatrix;
	}
	
	public NMIPerformance(List<String> oriVertexList, List<String> validVertexList, SimpleMatrix membershipMatrix, String classificationFile) {
		this.classificationFile = classificationFile;
		this.oriVertexList = oriVertexList;
		this.validVertexList = validVertexList;
		this.membershipMatrix = membershipMatrix;
	}	
	
	public void run() {
		if (this.membershipMatrix == null) {
			this.nmi = 0;
			this.report = "Null Membership Matrix";
			return;
		}
		
		Map<String, String> classificationMap = SharedFunctions.getVertexClassificationMap(this.oriVertexList, this.classificationFile);
		this.setClassificationCountMap(classificationMap);
		
		int[] finalMemberShipArray = SharedFunctions.getDeterminateMembershipArray(membershipMatrix, this.validVertexList.size());
		
		this.classificationDistributionMap = SharedFunctions.getClassificationDistributionMap(this.membershipMatrix.numCols(), finalMemberShipArray, this.validVertexList, classificationMap);		
		
		this.setNMI();
		
		this.report = "NMI: " + this.getNMI();
	}
	
	private double getLogBase2(double val) {
		return Math.log(val) / Math.log(2);
	}
	
	private void setClassificationCountMap(Map<String, String> classificationMap) {
		// TODO Auto-generated method stub
		this.classificationCountMap = new HashMap<String, Integer>();
		
		for (String object : classificationMap.keySet()) {
			String classification = classificationMap.get(object);
			
			if (! this.classificationCountMap.containsKey(classification))
				this.classificationCountMap.put(classification, 0);
			
			this.classificationCountMap.put(classification, this.classificationCountMap.get(classification) + 1);
		}
	}

	private void setNMI() {
		// TODO Auto-generated method stub
		int n = this.oriVertexList.size();
		double numerator = 0, denominator = 0;
		
		// calculate numerator
		for (Map<String, Integer> map : this.classificationDistributionMap) {
			int nc = 0;
			for (String item : map.keySet()) {
				nc += map.get(item);
			}
			
			for (String item : map.keySet()) {
				if (! this.classificationCountMap.containsKey(item)) {
					logger.error("The count of " + item + " is not found");
					continue;
				} else {
					int nj = this.classificationCountMap.get(item);
					int ncj = map.get(item);
					
					if (ncj == 0)
						continue;
					
					numerator += ncj * this.getLogBase2((n * ncj) / (double)(nc * nj));
				}
			}
		}
		
		// calculate denominator
		double left = 0, right = 0;
		for (Map<String, Integer> map : this.classificationDistributionMap) {
			int nc = 0;
			for (String item : map.keySet()) {
				nc += map.get(item);
			}
			
			if (nc == 0)
				continue;
			
			left += nc * this.getLogBase2(nc / (double)n);
		}
		
		for (String item : this.classificationCountMap.keySet()) {
			if (! this.classificationCountMap.containsKey(item)) {
				logger.error("The count of " + item + " is not found");
				continue;
			} else {
				int nj = this.classificationCountMap.get(item);
				right += nj * this.getLogBase2(nj / (double)n);
			} 
		}
		
		denominator = Math.sqrt(left * right);
		
		if (denominator == 0)
			this.nmi = 0;
		else
			this.nmi = numerator / denominator;
	}

	public String getReport() {
		return this.report;
	}
	
	public double getNMI() {
		return this.nmi;
	}
}
