package fdm.authsrv.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;

import fdm.authsrv.dto.KeycloakTokenResponseDto;
import fdm.authsrv.dto.TokenResponseInfo;

@Component
public class TokenRoute extends RouteBuilder {

	JacksonDataFormat tokenResponseJdf = new JacksonDataFormat(KeycloakTokenResponseDto.class);
	
	@Override
	public void configure() throws Exception {
		
		tokenResponseJdf.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		from("direct:auth").routeId("auth")
			.log("${body}")
			.transform().simple("client_id=${body.clientId}&client_secret=${body.clientSecret}&grant_type=client_credentials")

			.setHeader("CamelHttpMethod", constant("POST"))
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED))
			
			.to("{{authsrv.url.token-request}}?bridgeEndpoint=true")
			.convertBodyTo(String.class)
			
			.unmarshal(tokenResponseJdf)
			
			.process(exchange -> {
				KeycloakTokenResponseDto key = exchange.getMessage().getBody(KeycloakTokenResponseDto.class);
				TokenResponseInfo resp = new TokenResponseInfo();
				resp.setAccessToken(key.getAccessToken());
				resp.setExpiresIn(key.getExpiresIn());
//				resp.setTokenType(key.getTokenType());
				exchange.getMessage().setBody(resp);
			})
			.removeHeaders("*")
			;
		
	}

}
