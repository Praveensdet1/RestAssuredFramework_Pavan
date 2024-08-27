import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class demo {
	
	int id;
	
	@Test(priority=1)
	public void getUsers() {
		
		
		
		when()
		    .get("https://reqres.in/api/users?page=2")
		    
		.then()
		    .statusCode(200)
		    .body("total", equalTo(12))
		    .log().all();
		
		
	}
@Test(priority=2)	
public void createUser() {
	
	HashMap data = new HashMap();
	data.put("name", "praveen");
	data.put("job", "doctor");
	
		
		id=given()
		     .body(data)
		     .contentType("application/json")
		
		
		.when()
		    .post("https://reqres.in/api/users")
		    .jsonPath().getInt("id");
		    
		/*.then()
		    .statusCode(201)
		    .body("name", equalTo("morpheus"))
		    .log().all();*/
		
		
	}

@Test(priority=3,dependsOnMethods={"createUser"})	
public void updateUser() {
	
	HashMap data = new HashMap();
	data.put("name", "kumar");
	data.put("job", "doctor");
	
		
		given()
		     .body(data)
		     .contentType("application/json")
		
		
		.when()
		    .put("https://reqres.in/api/users/"+id)
		    
		    
		.then()
		    .statusCode(200)
		    .body("name", equalTo("kumar"))
		    .log().all();
		
		
	}

@Test(priority=4)	
public void deleteUser() {


		when()
		    .delete("https://reqres.in/api/users/"+id)
		    
		    
		.then()
		    .statusCode(204)
		    .log().all();
		
		
	}

}



