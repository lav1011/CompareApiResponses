import ApiServices.CommonUtils.ApiUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApiUtilsTest {


   @Test
    public void  test1 () throws IOException {
       ApiUtils util= new ApiUtils();
      util.compareJsonResponse("Test","Test");

   }


}
