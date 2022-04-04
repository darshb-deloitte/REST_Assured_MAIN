import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

public class Pagination_helper {

    public boolean pagination(String tokenG,int limit){

        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + tokenG)
                .header("Content-Type", "application/json");

        Response ResponsegetTask = null;

        ResponsegetTask = request.get("/task?limit="+limit);
        ResponsegetTask.prettyPrint();

        String jsonString=ResponsegetTask.getBody().asPrettyString();

        int count = JsonPath.from(jsonString).get("count");
        ArrayList<String> data = JsonPath.from(jsonString).get("data");

        if(limit == data.size()){
            return true;
        }

        else {
            return false;
        }
    }
}