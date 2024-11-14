package ChangeRequestserviceNow;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


public class BaseClassChangeRequest {
	
	protected String sysId;
	
	@BeforeMethod
	
	public void uriAuthSetUp() {
		baseURI = "serviveNowUrl";
		basePath = "api/now/table";
		authentication = basic("admin",	"password");

	}
	public ResponseSpecification CreateRecord() {
		ResponseSpecification expectResult = RestAssured.expect();
		expectResult.statusCode(201);
		expectResult.statusLine(containsString("Created"));
		expectResult.contentType(ContentType.JSON);
		expectResult.time(lessThan(5000L));
		return expectResult;
		
	}
	

}
