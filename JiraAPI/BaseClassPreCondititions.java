package JiraAPI;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class BaseClassPreCondititions {
	
	protected String issueKey;
	protected String id;
	@BeforeMethod
	public void initialSetUp() {
		
		baseURI = "https://bhoomsbalaji.atlassian.net/";
		basePath="rest/api/3/issue";
				
	}

public ResponseSpecification createIssue() {
	ResponseSpecification expect = RestAssured.expect();
	expect.statusCode(201);
	expect.statusLine(Matchers.containsString("Created"));
	expect.contentType(ContentType.JSON);
	expect.time(Matchers.lessThan(5000L));
	
	return expect;
	}
public ResponseSpecification updateIssue() {
	ResponseSpecification expect = RestAssured.expect();
	expect.statusCode(204);
	expect.statusLine(Matchers.containsString("No Content"));
	expect.contentType(ContentType.JSON);
	expect.time(Matchers.lessThan(5000L));
	return expect;

}
public ResponseSpecification deleteIssue() {
	ResponseSpecification expect = RestAssured.expect();
	expect.statusCode(204);
	expect.statusLine(Matchers.containsString("No Content"));
	expect.time(Matchers.lessThan(5000L));
	return expect;

}
 public ResponseSpecification getIssue() {
	 ResponseSpecification expect = RestAssured.expect();
		expect.statusCode(200);
		expect.statusLine(Matchers.containsString("OK"));
		expect.contentType(ContentType.JSON);
		expect.time(Matchers.lessThan(5000L));
		return expect;
	

}
}

