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
			 .preemptive().basic("bhooms.balaji@gmail.com","ATATT3xFfGF0-Z_QVRLUZfT4TisMiJTdrSLBChnSbQu6NbVyugCyOU91gf9O03--0DOr85nhaYMBGFu0q2lyIlDNsE6O1mT1yD5VzPm-f_6P3UEZtZ6iohGHD2WUVUrKgk-I2dl-Z8jmvMY2vpqsXVCKjZLd-M4j2--KqEFKbkBjZF7DGVjjsWA=E0CBEAB3")
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
		 .preemptive().basic("bhooms.balaji@gmail.com","ATATT3xFfGF0-Z_QVRLUZfT4TisMiJTdrSLBChnSbQu6NbVyugCyOU91gf9O03--0DOr85nhaYMBGFu0q2lyIlDNsE6O1mT1yD5VzPm-f_6P3UEZtZ6iohGHD2WUVUrKgk-I2dl-Z8jmvMY2vpqsXVCKjZLd-M4j2--KqEFKbkBjZF7DGVjjsWA=E0CBEAB3")
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
		 .preemptive().basic("bhooms.balaji@gmail.com","ATATT3xFfGF0-Z_QVRLUZfT4TisMiJTdrSLBChnSbQu6NbVyugCyOU91gf9O03--0DOr85nhaYMBGFu0q2lyIlDNsE6O1mT1yD5VzPm-f_6P3UEZtZ6iohGHD2WUVUrKgk-I2dl-Z8jmvMY2vpqsXVCKjZLd-M4j2--KqEFKbkBjZF7DGVjjsWA=E0CBEAB3")
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
		 	.preemptive().basic("bhooms.balaji@gmail.com","ATATT3xFfGF0-Z_QVRLUZfT4TisMiJTdrSLBChnSbQu6NbVyugCyOU91gf9O03--0DOr85nhaYMBGFu0q2lyIlDNsE6O1mT1yD5VzPm-f_6P3UEZtZ6iohGHD2WUVUrKgk-I2dl-Z8jmvMY2vpqsXVCKjZLd-M4j2--KqEFKbkBjZF7DGVjjsWA=E0CBEAB3")
		 	.pathParam("issueKey", issueKey)
		 	.log().all()
		 .when()
		 	.delete("/{issueKey}")
		 .then().assertThat().spec(deleteIssue());
		
		 
		
	}
	
}
