import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();
        BufferedReader in = new BufferedReader(new FileReader("data/SP22EngSimple.tsv"));
        String str;
        while ((str = in.readLine()) != null) {
            String[] row = str.split("\t");
            //System.out.println(Arrays.toString(row));
            data.add(row);
        }
        //System.out.println(row);
        //System.out.println(data);

        Course[] SP21Eng = new Course[data.size()];
        for (int i = 0; i < SP21Eng.length; i++) {
            //System.out.println(Arrays.toString(data.get(i)));
            String department = data.get(i)[0].split(" ")[1];
            String number = data.get(i)[0].split(" ")[2];
            String description = data.get(i)[3];
            String title = data.get(i)[1];
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
        //System.out.println(SP21Eng[0]);


        for (int i = 0; i < SP21Eng.length; i++) {
            //System.out.println(data.get(i)[5] + data.get(i)[6]);
            if (!data.get(i)[5].equals("null")) {
                //System.out.println("SSSS");
                String[] preReqStr = data.get(i)[5].split(",");
                ArrayList<Course> preReq = new ArrayList<Course>();
                //System.out.println(Arrays.toString(preReqStr));
                for (String s : preReqStr) {
                    //System.out.println(s.split(" ")[0]);
                    preReq.add(searchCourse(SP21Eng, s.split(" ")[0], s.split(" ")[1]));
                }
                //System.out.println(preReq);
                SP21Eng[i].setPrerequisite(preReq);
            }

            if (!data.get(i)[6].equals("null")) {
                String[] coReqStr = data.get(i)[6].split(",");
                ArrayList<Course> coReq = new ArrayList<Course>();
                for (String s : coReqStr) {
                    System.out.println(s.split(" ")[0] + s.split(" ")[1]);
                    coReq.add(searchCourse(SP21Eng, s.split(" ")[0], s.split(" ")[1]));
                }
                SP21Eng[i].setCorequisite(coReq);
            }
        }

        //System.out.println(searchCourse(SP21Eng, "BME", "228"));
        for (Course c :
                SP21Eng) {
            System.out.println(c.getDepartment() + c.getNumber());
            System.out.println(c.getPrerequisite());
        }
        SP21Eng[1].getPrerequisite().get(1).setCompleted(true);
        System.out.println();
        System.out.println(SP21Eng[1].getPrerequisite().get(1).isCompleted());
    }


    static Course searchCourse(Course[] from, String dept, String num){
        for (Course c : from) {
            if (c.getDepartment().equals(dept) && c.getNumber().equals(num)) {
                return c;
                //System.out.println("FOUND");
            }
        }
        return null;
    }

}
