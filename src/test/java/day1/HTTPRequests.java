package day1;

//static imports to identify given(),when(),then() keywords.
import  static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;


public class HTTPRequests {
	
	String id;
	//@Test(priority = 1)
	void getUser() {		
		
		when()
		   .get("http://localhost:3000/students/101")
		
		.then()
		   .statusCode(200)
		   .body("id", equalTo("101"))
		   .log().all();
		
	}
	
	@Test(priority =2)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name", "ramu");
		data.put("location","germany");
				
		given()
		  .contentType("application/json")
		  .body(data)
		  
		  .when()
		    .post("http://localhost:3000/students")
		    .jsonPath().getString("id");
		    
		  
//		  .then()
//		     .statusCode(201)
//		     
//		     .log().all()
	}
	
	@Test(priority =3,dependsOnMethods = {"createUser"})
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "ravi");
		data.put("location","london");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("http://localhost:3000/students/"+id)
		
		.then()
		.statusCode(200)
		.log().all();							
				
	}
	
	@Test
	void deleteUser() {
		when()
		.delete("http://localhost:3000/students/"+id)
		.then()
		.statusCode(204);
	}

}
