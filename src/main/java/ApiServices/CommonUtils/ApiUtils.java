package ApiServices.CommonUtils;

import io.restassured.internal.assertion.Assertion;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.restassured.RestAssured.given;

public class ApiUtils {


    public static void fileReader(String filetext1, String filetext2) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        try {
            Response response = null;
            File text1 = new File(System.getProperty("user.dir") + "/src/test/resources/" + filetext1);
            File text2 = new File(System.getProperty("user.dir") + "/src/test/resources/" + filetext2);
            FileReader fr = new FileReader(text1);
            BufferedReader br = new BufferedReader(fr);
            String endpointsfromfile1;
            FileReader fr2 = new FileReader(text2);
            BufferedReader br2 = new BufferedReader(fr2);
            String endpointsfromfile2;
            while (true) {
                endpointsfromfile1 = br.readLine();
                endpointsfromfile2 = br2.readLine();

                if ((endpointsfromfile1 == null && endpointsfromfile2 == null))
                    break;
                Mythread t1 = new Mythread(endpointsfromfile1, endpointsfromfile2);
                executorService.execute(t1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public void compareJsonResponse(String endpointsfromfile1, String endpointsfromfile2) {
        Response response;
        Response response1;
        try {
            if (endpointsfromfile1 == null || endpointsfromfile2 == null){
                System.out.println(" Don't Give invalid Inputs ");
            }
            if (endpointsfromfile1 != null && endpointsfromfile2 != null) {
                response = given().when().get(endpointsfromfile1);
                response1 = given().when().get(endpointsfromfile2);

                String res1 = response.getBody().jsonPath().getString("data");
                String res2 = response1.getBody().jsonPath().getString("data");

                if (!res1.equals(res2)) {

                    System.out.println(endpointsfromfile1 + "-------= Not equals to =-------" + endpointsfromfile2);
                } else {
                    System.out.println(endpointsfromfile1 + "------= equals to =-------- " + endpointsfromfile2);

                }

            }
            else {
                System.out.println(endpointsfromfile1 + "-------= Not equals to =-------" + endpointsfromfile2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


class Mythread extends ApiUtils implements Runnable {
    String endpoint1file, endpoint2file;

    Mythread(String endpoint1file, String endpoint2file) {
        super();
        this.endpoint1file = endpoint1file;
        this.endpoint2file = endpoint2file;

    }

    @Override
    public void run() {
        compareJsonResponse(endpoint1file, endpoint2file);
    }
}



