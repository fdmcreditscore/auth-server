package fdm.authsrv.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IntrospectRequestInfo {
	private String clientId;
	private String clientSecret;
	private String token;
	
}
