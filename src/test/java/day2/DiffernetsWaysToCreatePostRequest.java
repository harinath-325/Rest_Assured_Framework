package day2;

//static imports to identify given(),when(),then() keywords.
import  static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;






//using HashMap as data source for post request

public class DiffernetsWaysToCreatePostRequest {
	
	@Test
	void postUsingHashMap() {
		
		HashMap data = new HashMap();
		data.put("name","scott");
		data.put("location","France");
		data.put("phone","802346");
		
		String courseArr[] = {"C","C++"};
		data.put("course", courseArr);

		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
		  .post("url")
		  
		.then()
		  .statusCode(201)
		  .body("name",equalTo("Scott"))
		  .body("location",equalTo("France"))
		  .body("phone",equalTo("802346"))
		  .body("course[0]",equalTo("C"))
		  .body("course[1]",equalTo("C++"))
		  .header("Content-type","application/json; charset = utf 8")
		  .log().all();		
	}
	
	//using ORG.JSON as data source for post request
	
	@Test
	void postusingOrgJson() {
		
		JSONObject jb = new JSONObject();
		jb.put("name","scott");
		jb.put("location","France");
		jb.put("phone","802346");
		
		String courseArr[] = {"C","C++"};
		jb.put("course", courseArr);
		
		given()
		  .contentType("application/json")
		  .body(jb.toString())  // use toStrin() method to convert jsonObject to string
		
		.when()
		  .post("url")
		  
		.then()
		  .statusCode(201)
		  .body("name",equalTo("Scott"))
		  .body("location",equalTo("France"))
		  .body("phone",equalTo("802346"))
		  .body("course[0]",equalTo("C"))
		  .body("course[1]",equalTo("C++"))
		  .header("Content-type","application/json; charset = utf 8")
		  .log().all();		

	}
	
	//using plain old java object as data source for post request
	
	@Test
	void postUsingPOJO() {
		
		PlainOldJavaObject pojo = new PlainOldJavaObject();
		pojo.setName("Scott");
		pojo.setLocator("France");
		pojo.setPhone("80345");
		 
		String course[] = {"C","C++"};
		pojo.setCourse(course);
		
		
		given()
		  .contentType("application/json")
		  .body(pojo)  
		
		.when()
		  .post("url")
		  
		.then()
		  .statusCode(201)
		  .body("name",equalTo("Scott"))
		  .body("location",equalTo("France"))
		  .body("phone",equalTo("802346"))
		  .body("course[0]",equalTo("C"))
		  .body("course[1]",equalTo("C++"))
		  .header("Content-type","application/json; charset = utf 8")
		  .log().all();		
		
	}
	
	
	//using External JSON file as data source for post request
	@Test
	void postUsingExtJSONFile() throws FileNotFoundException {
		
		File f = new File(".\\body.json"); // .\\ Represent current directory
		
		FileReader fr = new FileReader(f); // read the file
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject jb = new JSONObject(jt);
		
		given()
		  .contentType("application/json")
		  .body(jb.toString())  
		
		.when()
		  .post("url")
		  
		.then()
		  .statusCode(201)
		  .body("name",equalTo("Scott"))
		  .body("location",equalTo("France"))
		  .body("phone",equalTo("802346"))
		  .body("course[0]",equalTo("C"))
		  .body("course[1]",equalTo("C++"))
		  .header("Content-type","application/json; charset = utf 8")
		  .log().all();		
		
	}

}
