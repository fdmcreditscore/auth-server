package fdm.authsrv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponseInfo {

	@JsonProperty(value="access_token")
	private String accessToken;
	
	@JsonProperty(value="expires_in")
	private int expiresIn;
	
//	@JsonProperty(value="token_type")
//	private String tokenType;

}
