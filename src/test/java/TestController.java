import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbt.springBoot.SpringBootRestApiApp;
import com.sbt.springBoot.persistence.model.internal.request.task.TaskPage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApiApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testListAllUsers() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/SpringBootRestApi/api/allUser"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"userId\":1,\"userName\":\"admin\",\"taskList\":[{\"taskId\":1,\"taskTitle\":\"new01\"},{\"taskId\":2,\"taskTitle\":\"new02\"}]},{\"userId\":2,\"userName\":\"mahsa\",\"taskList\":[{\"taskId\":3,\"taskTitle\":\"new03\"},{\"taskId\":5,\"taskTitle\":\"new05\"}]},{\"userId\":3,\"userName\":\"sbt\",\"taskList\":[{\"taskId\":4,\"taskTitle\":\"new04\"}]}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testGetUserByID() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/SpringBootRestApi/api/userId/1"),
				HttpMethod.GET, entity, String.class);
		String expected = "{\"userId\":1,\"userName\":\"admin\",\"taskList\":[{\"taskId\":1,\"taskTitle\":\"new01\"},{\"taskId\":2,\"taskTitle\":\"new02\"}]}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testGetUserByName() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/SpringBootRestApi/api/userName/admin"),
				HttpMethod.GET, entity, String.class);
		String expected = "\r\n" + 
				"{\"userId\":1,\"userName\":\"admin\",\"taskList\":[{\"taskId\":1,\"taskTitle\":\"new01\"},{\"taskId\":2,\"taskTitle\":\"new02\"}]}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testGetLikeUserNameAndTaskTitle() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/SpringBootRestApi/api/users/admin/new01"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"userName\":\"admin\",\"taskTitle\":\"new01\"}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testGetTaskPage() {
		TaskPage taskPage = new TaskPage();
		taskPage.setPageNumber(1);
		taskPage.setSize(3);
		HttpEntity<TaskPage> entity = new HttpEntity<TaskPage>(taskPage, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/SpringBootRestApi/api/tasks"),
				HttpMethod.POST, entity, String.class);
        String expected = "[{\"taskId\":4,\"taskTitle\":\"new04\"},{\"taskId\":5,\"taskTitle\":\"new05\"}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testGetTaskSortable() {
		TaskPage taskPage = new TaskPage();
		taskPage.setSort("asc");
		taskPage.setSortColumn("taskId");
		taskPage.setPageNumber(1);
		taskPage.setSize(3);
		taskPage.setSortDirection(Direction.ASC);
		HttpEntity<TaskPage> entity = new HttpEntity<TaskPage>(taskPage, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/SpringBootRestApi/api/tasks/sort"),
				HttpMethod.POST, entity, String.class);
        String expected = "[{\"taskId\":4,\"taskTitle\":\"new04\"},{\"taskId\":5,\"taskTitle\":\"new05\"}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
