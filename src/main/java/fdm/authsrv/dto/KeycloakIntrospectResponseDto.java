package fdm.authsrv.dto;

import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeycloakIntrospectResponseDto {
	private String scope;
	private boolean active;
	private int exp;

	public String getScope() {
		return Optional.ofNullable(scope).orElse("");
	}
}
