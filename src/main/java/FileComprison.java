import ApiServices.CommonUtils.ApiUtils;

import java.io.IOException;

public class FileComprison extends ApiUtils {

    public static void main(String[] args) throws IOException {

        /* First Test Case With Correct Files */

        fileReader("endpoints1.txt", "endpoints2.txt");

        /* First Test Case Completed with Correct Files */

        /*===============================================/*

        /* Second Test Case With Valid and Invalid Files */

//        fileReader("endpoints1.txt", "invalidpoints.txt");

        /* Second Test Case Completed with one Invalid File */

        /*===============================================/*

        /* Third Test Case With Invalid Files */

//        fileReader("invalidpoints.txt", "endpoints2.txt");

        /* Third Test Case Completed with Invalid File */

    }

}
