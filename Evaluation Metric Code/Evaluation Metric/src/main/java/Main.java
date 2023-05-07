import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main( String[] args ){
        List<String> list = readTxtFile("D:/DATA/Datasets/twitter/summary"); //原本
//        List<String> list = readTxtFile("D:/DATA/Datasets/cora/summary");
        Integer sum = 0;
        for(int i=0;i<list.size();i++) {

            String linetxt=list.get(i);

            String[] myArray = linetxt.replaceAll(",","@").split("@");
            sum+=Integer.parseInt(myArray[1]);
        }
        System.out.println("sum:"+sum);

    }
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
}
