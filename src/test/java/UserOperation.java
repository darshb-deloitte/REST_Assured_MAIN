import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;



public class UserOperation {
    BaseClass base = new BaseClass();
    ArrayList<JSONObject> arr;
    String tokenG;
    String[] array = new String[2];
    String login_path = "C:\\Users\\darshb\\Desktop\\MAIN_ASSIGNMENT_REST\\src\\test\\resources\\log.xlsx";

    @BeforeClass
    public void post_data() throws IOException {

        String register_path = "C:\\Users\\darshb\\Desktop\\MAIN_ASSIGNMENT_REST\\src\\test\\resources\\login.xlsx";
        arr = base.user_details(register_path);

        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/user";

    }


    @Test(priority = 1)
    public void testPost()
    {
        for(JSONObject jsonData: arr) {

            System.out.println(jsonData);
            given()
                    .contentType("application/json")
                    .body(jsonData.toString())
                    .when()
                    .post("/register")
                    .then()
                    .statusCode(201);
        }
    }

    @Test(priority = 2)
    public void Authenticate() throws IOException {
        baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request = RestAssured.given();
        array = base.login_details(login_path);
        String loginDetails="{\n" +
                "  \n" +
                "  \"email\": \""+array[0]+"\",\n" +
                "  \"password\": \""+array[1]+"\"\n" +
                "  \n" +
                "}";

        System.out.println(loginDetails);

//        JSONObject loginDetails = base.login_details(login_path);
        request.header("Content-Type", "application/json");
        Response responseG = request.body(loginDetails.toString()).post("/user/login");
        responseG.prettyPrint();
        String jsonString=responseG.getBody().asString();
        tokenG= JsonPath.from(jsonString).get("token");
        System.out.println(tokenG);

    }

}
