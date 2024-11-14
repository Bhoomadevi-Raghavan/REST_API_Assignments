package JiraAPI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class EndToEndIssueJira extends BaseClassPreCondititions {
	public String id,issueKey;
	@Test(priority=0)
	public void runCreateIssue() {
		
		File file = new File("src/test/resources/JiraInput/issueCreation.json");
		
	 Response post = given()
			 .auth()
			 .preemptive().basic("username","apiToken")
			.header("Content-Type","application/json")
			.log().all()
		.when()	
			.body(file)
			.post();
	 			
	    issueKey = post.jsonPath().getString("key");	
	 	id = post.jsonPath().getString("id");
	 	post.then().assertThat().spec(createIssue());
	 	System.out.println(issueKey);
	 	System.out.println(id);
	 	System.out.println(post.asPrettyString());
		}
	@Test(priority = 1)
	public void runGetCreatedIssue() {
		
		given()
		 .auth()
		 .preemptive().basic("userName","apiToken")
		 .pathParam("issueKey",issueKey)
			.log().all()
		.when()
			.get("/{issueKey}")
		.then().spec(getIssue());
			
		
		}
	
	@Test(priority=2)
	public void runUpdateIssue() {
				
		File file = new File("src/test/resources/JiraInput/UpdateIssue.json");
		given()
		 .auth()
		 .preemptive().basic("userName","apiToken")
		.header("Content-Type","application/json")
		.pathParam("issueKey", issueKey)
		.log().all()
	.when()	
		.body(file)
		.put("/{issueKey}")
	.then().assertThat().spec(updateIssue());
	

	}
	@Test(priority=3)
	public void runDelete() {
		given()
		 	.auth()
		 	.preemptive().basic("userName","apiToken")
		 	.pathParam("issueKey", issueKey)
		 	.log().all()
		 .when()
		 	.delete("/{issueKey}")
		 .then().assertThat().spec(deleteIssue());
		
		 
		
	}
	
}
