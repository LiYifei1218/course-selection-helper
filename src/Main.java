import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
            data.add(row);
        }

        ArrayList<Course> SP21Eng = new ArrayList<Course>();
        for (String[] datum : data) {
            String department = datum[0].split(" ")[1];
            String number = datum[0].split(" ")[2];
            String description = datum[3];
            String title = datum[1];
            SP21Eng.add(new Course(
                    department, number, description,
                    new ArrayList<Course>(),
                    new ArrayList<Course>(),
                    false, title
            ));
        }

        for (int i = 0; i < SP21Eng.size(); i++) {
            if (!data.get(i)[5].equals("null")) {
                String[] preReqStr = data.get(i)[5].split(",");
                ArrayList<Course> preReq = new ArrayList<Course>();
                for (String s : preReqStr) {
                    preReq.add(searchCourse(SP21Eng, s.split(" ")[0], s.split(" ")[1]));
                }
                SP21Eng.get(i).getPrerequisite().addAll(preReq);
            }

            if (!data.get(i)[6].equals("null")) {
                String[] coReqStr = data.get(i)[6].split(",");
                ArrayList<Course> coReq = new ArrayList<Course>();
                for (String s : coReqStr) {
                    coReq.add(searchCourse(SP21Eng, s.split(" ")[0], s.split(" ")[1]));
                }
                SP21Eng.get(i).getCorequisite().addAll(coReq);
            }
        }

        System.out.println(SP21Eng.get(1).select());
    }


    static @Nullable Course searchCourse(ArrayList<Course> from, String dept, String num){
        for (Course c : from)
            if (c.getDepartment().equals(dept) && c.getNumber().equals(num))
                return c;
        return null;
    }
}