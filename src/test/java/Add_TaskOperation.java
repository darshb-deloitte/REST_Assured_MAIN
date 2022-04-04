import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.util.ArrayList;

public class Add_TaskOperation extends UserOperation {

    String task_path = "C:\\Users\\darshb\\Desktop\\MAIN_ASSIGNMENT_REST\\src\\test\\resources\\task.xlsx";
    ArrayList<JSONObject> task;
    Pagination_helper page = new Pagination_helper();

    @Test(priority = 3)
    public void addTask() throws IOException {

        task = base.add_task(task_path);

        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + tokenG)
                .header("Content-Type", "application/json");

        Response ResponseaddTask = null;

        for (JSONObject jsonData : arr) {
            String addTask = jsonData.toString();
            ResponseaddTask = request.body(addTask).post("/task");
            ResponseaddTask.prettyPrint();
        }
    }

    @Test(priority = 4)
    public void validate_pagination_limit_2(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";

        System.out.println(page.pagination(tokenG,2));
    }

    @Test(priority = 5)
    public void validate_pagination_limit_5(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";

        System.out.println(page.pagination(tokenG,5));
    }

    @Test(priority = 6)
    public void validate_pagination_limit_10(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";

        System.out.println(page.pagination(tokenG,10));
    }
}