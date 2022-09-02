package fdm.authsrv.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequestDto {
	private String organizationName;
	private String applicationCode;
	private String password;
	private String adminName;
	private String adminEmailAddress;
	private String adminPhoneNumber;
	private String status;
}
