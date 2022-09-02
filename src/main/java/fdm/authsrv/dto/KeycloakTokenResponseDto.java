package fdm.authsrv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeycloakTokenResponseDto {
	@JsonProperty(value="access_token")
	private String accessToken;
	
	@JsonProperty(value="expires_in")
	private int expiresIn;
	
	@JsonProperty(value="refresh_expires_in")
	private int refreshExpiresIn;
	
	@JsonProperty(value="refresh_token")
	private String refreshToken;
	
	@JsonProperty(value="token_type")
	private String tokenType;
	
//	@JsonProperty(value="not-before-policy")
//	private int notBeforePolicy;
//	
//	@JsonProperty(value="session_state")
//	private String sessionState;

//	private String scope;

}
