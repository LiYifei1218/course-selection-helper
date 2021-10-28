import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();
        BufferedReader in = new BufferedReader(new FileReader("data/SP22Eng.tsv"));
        String str;
        while ((str = in.readLine()) != null) {
            String[] row = str.split("\t");
            //System.out.println(Arrays.toString(row));
            data.add(row);
        }
        //System.out.println(row);
        //System.out.println(data);

        Course[] SP21Eng = new Course[data.size()];
        for (int i = 0; i < data.size(); i++) {
            //System.out.println(Arrays.toString(data.get(i)));
            String department = data.get(i)[0].split(" ")[1];
            String number = data.get(i)[0].split(" ")[2].substring(0, data.get(i)[0].split(" ")[2].length() - 1);
            String description = data.get(i)[3].substring(1, data.get(i)[3].length() - 1);
            String title = data.get(i)[1].substring(1, data.get(i)[1].length() - 1);
//            String[] preReqStr = data.get(i)[5].substring(1, data.get(i)[5].length() - 1).split(",");
//            String[] coReqStr = data.get(i)[6].substring(1, data.get(i)[6].length() - 1).split(",");
//            Course[] preReq = new Course[preReqStr.length];
//            Course[] coReq = new Course[coReqStr.length];
//            for (int j = 0; j < preReq.length; j++) {
//                preReq[j] = searchCourse(preReqStr[j][0], preReqStr[j][1]);
//            }

            SP21Eng[i] = new Course(department, number ,description, title);
            //System.out.println(department + number + title +description );
        }
        //System.out.println(Arrays.toString(SP21Eng));
        System.out.println(SP21Eng[0]);
    }


    Course searchCourse(Course[] from, String dept, String num){
        for (Course c : from) {
            if (c.getDepartment().equals(dept) && c.getNumber().equals(num))
                return c;
        }
        return null;
    }

}
