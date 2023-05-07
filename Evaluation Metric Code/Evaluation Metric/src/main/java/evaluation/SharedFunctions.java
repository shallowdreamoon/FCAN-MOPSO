package evaluation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ejml.simple.SimpleMatrix;

public class SharedFunctions {
	public static Logger logger = Logger.getLogger(SharedFunctions.class);
	
	public static void saveFile(String filename, String content) {
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
		}		
	}
	
	public static Map<String, String> getVertexClassificationMap(List<String> vertexList, String vertexClassificationFile) {
		// TODO Auto-generated method stub
		Map<String, String> vertexClassificationMap = new HashMap<String, String>();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(vertexClassificationFile));
			String line;
			
			while ((line = br.readLine()) != null) {
				String[] items = line.split(",");
				
				if (vertexList.contains(items[0]))
					vertexClassificationMap.put(items[0], items[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return vertexClassificationMap;
	}
	
	public static List<Map<String, Integer>> getClassificationDistributionMap(
			int numClusters, int[] membershipList, List<String> objectList, Map<String, String> classificationMap) {
		List<Map<String, Integer>> classificationOverviewMap = new ArrayList<Map<String,Integer>>();
		
		for (int i = 0; i < numClusters; i++)
			classificationOverviewMap.add(new HashMap<String, Integer>());
		
		for (int i = 0; i < membershipList.length; i++) {
			int clusterIndex = membershipList[i];
			String vertex = objectList.get(i);
			String type = classificationMap.get(vertex);
			
			if (type == null) {
				logger.error("The clustering result of vertex " + vertex + " is not found");
				continue;
			}
			
			if (clusterIndex<0) {
				continue;
			}
			
			if (! classificationOverviewMap.get(clusterIndex).containsKey(type))
				classificationOverviewMap.get(clusterIndex).put(type, 0);
			
			int currValue = classificationOverviewMap.get(clusterIndex).get(type);
			classificationOverviewMap.get(clusterIndex).put(type, currValue + 1);
		}
		
		return classificationOverviewMap;
	}
	
	public static List<String> getCoraVertexList(String dataFile) {
		// TODO Auto-generated method stub
		List<String> vertexList = new ArrayList<String>();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(dataFile));
			String line;
			
			while ((line = br.readLine()) != null) {
				String[] items = line.split("\t");
				vertexList.add(items[0]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return vertexList;
	}
	
	/**
	 * (N+1) x M where N is the number of objects and M is the number of attributes
	 * @param folder
	 * @param attributeDataList
	 */
	public static void saveAsObject2Attribute(String folder, String filePrefix, List<List<String>> attributeDataList) {
		boolean ignoreHeader = true;
		String[] content = ignoreHeader ? new String[attributeDataList.size()] : new String[attributeDataList.size() + 1];
		
		// add header
		if (! ignoreHeader) {
			content[0] = "";
			for (int i = 0; i < attributeDataList.get(0).size(); i++) {
				content[0] += (i == 0 ? "" : "\t") + (i + 1);
			}
		}
		
		// add content
		int startIndex = ignoreHeader ? 0 : 1;
		for (int i = 0; i < attributeDataList.size(); i++) {
			List<String> list = attributeDataList.get(i);
			content[i + startIndex] = "";
			
			for (int j = 0; j < list.size(); j++) {
				content[i + startIndex] += (j == 0 ? "" : "\t") + list.get(j);
			}
		}
		
//		saveFileBySequence(folder, filePrefix, content);
		saveFileFromContentArray(folder + File.separator + "attribute.data", content);
	}
	
	public static void saveAsObjectLink(String folder, String filePrefix, List<String> vertexList, Set<Set<String>> edgeSet) {
		int[][] linkData = new int[vertexList.size()][vertexList.size()];
		boolean ignoreHeader = true;
		
		for (Set<String> edge : edgeSet) {
			int[] indices = new int[2];
			
			if (edge.size() == 2) {
				int index = 0;
				
				for (String item : edge)
					indices[index++] = vertexList.indexOf(item);
						
				if (indices[0] == -1 || indices[1] == -1) {
					logger.error("Found null vertex for edge " + edge.toString());
				}else {
					linkData[indices[0]][indices[1]] = 1;
					linkData[indices[1]][indices[0]] = 1;
				}
 			} else {
				logger.error("Failed to find the link information for  " + edge);
			}
		}
		
		String[] content = ignoreHeader ? new String[vertexList.size()] : new String[vertexList.size() + 1];
		
		// add header
		if (! ignoreHeader) {
			content[0] = "";
			for (int i = 0; i < linkData.length; i++) {
				content[0] += (i == 0 ? "" : "\t") + (i + 1);
			}
		}
		
		// add content
		int startIndex = ignoreHeader ? 0 : 1;
		for (int i = 0; i < linkData.length; i++) {
			content[i + startIndex] = "";
			
			for (int j = 0; j < linkData[i].length; j++) {
				content[i + startIndex] += (j == 0 ? "" : "\t") + linkData[i][j];
			}			
		}
		
//		saveFileBySequence(folder, filePrefix, content);	
		saveFileFromContentArray(folder + File.separator + "link.data", content);
	}
	
	private static void saveFileFromContentArray(String file, String[] contentArray) {
		// TODO Auto-generated method stub
		String content = "";
		
		for (String item : contentArray) 
			content += (content.length() == 0 ? "" : "\n") + item;
		
		saveFile(file, content);
	}

	public static boolean createFolder(String folder) {
		File newFolder = new File(folder);
		
		if (! newFolder.isDirectory())
			if (! newFolder.mkdir())
				return false;
		
		return true;
	}
	
	private static void saveFileBySequence(String folder, String filePrefix, String[] content) {
		// TODO Auto-generated method stub
		// create folder to save sequence files
		String folderPath = folder + "\\" + filePrefix;
		
		if (createFolder(folderPath)) {
			for (int i = 0; i < content.length; i++) {
				String line = content[i];
				String filename = filePrefix + "-" + i;
				
				SharedFunctions.saveFile(folderPath + "\\" + filename, line);
			}
		}
	}
	
	public static SimpleMatrix getMembershipMatrixByDeterminateClassification(
			String classificationResultFile, int numObjects, int numClusters, int startClusterIndex) {
		// TODO Auto-generated method stub
		double[][] data = new double[numObjects][];
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(classificationResultFile));
			String line;
			int lineIndex = 0;
			
			while ((line = br.readLine()) != null) {
				if (lineIndex < numObjects) {
					int clusterIndex = (int) Math.round(Double.valueOf(line.trim()) - startClusterIndex);
					data[lineIndex] = new double[numClusters];
					data[lineIndex++][clusterIndex] = 1;
				}
			}
			
			if (lineIndex != numObjects)
				logger.error("Results are not matched!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		
		return new SimpleMatrix(data);		
	}
	
	public static SimpleMatrix getMembershipMatrixByFuzzyResult(
			String fuzzyResultFile, int numObjects, int numClusters, String demiliter) {
		// TODO Auto-generated method stub
		double[][] data = new double[numObjects][];
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(fuzzyResultFile));
			String line;
			int lineIndex = 0;
			
			while ((line = br.readLine()) != null) {
				String[] items = line.split(demiliter);
				data[lineIndex] = new double[numClusters];
				
				if (items.length != numClusters) {
					logger.error("The fuzzy result for the line #" + lineIndex + " is not correct");
				} else {
					for (int i = 0; i < items.length; i++) 
						data[lineIndex][i] = Double.valueOf(items[i]);
					
					lineIndex++;
				}
			}
			
			if (lineIndex != numObjects)
				logger.error("Results are not matched!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		
		return new SimpleMatrix(data);		
	}
	
	public static int[] getDeterminateMembershipArray(SimpleMatrix membershipMatrix, int numObjects) {
		// TODO Auto-generated method stub
		int[] finalMembershipArray = new int[numObjects];
		
		for (int i = 0; i < numObjects; i++) {
			int membershipIndex = 0;
			double membership = membershipMatrix.get(i, 0);
			
			for (int j = 1; j < membershipMatrix.numCols(); j++) {
				if (membershipMatrix.get(i, j) > membership) {
					membershipIndex = j;
					membership = membershipMatrix.get(i, j);
				}
			}
			
			if (membershipIndex == 0 && membership == 0)
				finalMembershipArray[i] = -1;
			else
				finalMembershipArray[i] = membershipIndex;
		}
		
		return finalMembershipArray;
	}
	
	public static void saveMembershipMatrix(SimpleMatrix membershipMatrix, String filename) {
		// TODO Auto-generated method stub
		String content = "";
		
		for (int i = 0; i < membershipMatrix.numRows(); i++) {
			for (int j = 0; j < membershipMatrix.numCols(); j++) {
				content += (j == 0 ? "" : "\t") + membershipMatrix.get(i, j);
			}
			
			content += "\n";
		}
		
		saveFile(filename, content);
	}
	
	public static void saveMembershipMatrixInSeq(List<String> labelList, SimpleMatrix membershipMatrix, String folder, int numItemsEachFile) {
		// TODO Auto-generated method stub
		createFolder(folder);
		
		String content = "";
		int numRecords = 0;
		
		for (int i = 0; i < membershipMatrix.numRows(); i++) {
			content += (content.length() == 0 ? "" : "\n") + labelList.get(i);
			
			for (int j = 0; j < membershipMatrix.numCols(); j++) {
				content += "\t" + membershipMatrix.get(i, j);
			}
			
			numRecords++;
			
			if (numRecords % numItemsEachFile == 0) {
				saveFile(folder + File.separator + (numRecords / numItemsEachFile), content);
				content = "";
			}
		}
		
		if (content.length() != 0) {
			saveFile(folder + File.separator + (numRecords / numItemsEachFile + 1), content);
			content = "";
		}
	}	
}
