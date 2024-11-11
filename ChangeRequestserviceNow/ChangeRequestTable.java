package ChangeRequestserviceNow;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;


public class ChangeRequestTable extends BaseClassChangeRequest{
	
	private String tableName = "change_request";
	
	@Test
	public void runCreateRecord() {
		PojoBodyRequest request = new PojoBodyRequest();
		request.setDescription("New record to be created");
		request.setShort_description("Create record");
		sysId = given()
			.header("Content-Type","application/json")
			.pathParam("tableName",tableName)
			.log().all()
		.when()
			.body(request)
			.post("/{tableName}")
		.then()
			.log().all()
			.spec(CreateRecord())
			.extract()
			.jsonPath()
			.getString("result.sys_id");
		
	}

}
