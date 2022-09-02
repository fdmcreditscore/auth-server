package fdm.authsrv.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IntrospectResponseInfo {
	private String clientId;
	private boolean active;
	private String scope;
}
