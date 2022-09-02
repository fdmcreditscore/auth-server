package fdm.authsrv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestInfo {
	private String clientId;
	private String clientSecret;
	
}
