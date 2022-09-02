package fdm.authsrv;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest 
@AutoConfigureMockMvc
public class AuthTest {
    @Autowired
    private MockMvc mvc;

	@Test
	public void request1() throws Exception {

		String strBody = "{\"clientId\":\"login-app\", \"clientSecret\":\"ticn5VMJ6PC7468Q1ECm15Sgzui9bHF3\"}";
		mvc
		.perform(
				post("http://localhost:8084/auth/request-token")
				.contentType(MediaType.APPLICATION_JSON)
				.content(strBody)
				)
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.access_token").exists())
		.andExpect(jsonPath("$.access_token").isNotEmpty())
//		.andExpect(jsonPath("$.access_token", is(notNullValue())))
				;
		
	}
}
