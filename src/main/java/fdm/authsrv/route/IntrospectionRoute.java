package fdm.authsrv.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;

import fdm.authsrv.dto.IntrospectResponseInfo;
import fdm.authsrv.dto.KeycloakIntrospectResponseDto;

@Component
public class IntrospectionRoute extends RouteBuilder {

	JacksonDataFormat introspectionResponseJdf = new JacksonDataFormat(KeycloakIntrospectResponseDto.class);
	
	@Override
	public void configure() throws Exception {
		
		introspectionResponseJdf.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		from("direct:introspect").routeId("introspect")
			.log("${body}")
			.setHeader("clientId", simple("${body.clientId}"))
			.transform().simple("client_id=${body.clientId}&client_secret=${body.clientSecret}&token=${body.token}")
			.setHeader("CamelHttpMethod", constant("POST"))
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED))
			
			.to("{{authsrv.url.introspect}}?bridgeEndpoint=true")
			.convertBodyTo(String.class)
			.unmarshal(introspectionResponseJdf)

			.process(exchange -> {
				String clientId = exchange.getMessage().getHeader("clientId", String.class);
				KeycloakIntrospectResponseDto keycloakResponse = exchange.getMessage().getBody(KeycloakIntrospectResponseDto.class);
				IntrospectResponseInfo resp = new IntrospectResponseInfo();
				resp.setActive(keycloakResponse.isActive());
				resp.setClientId(clientId);
				resp.setScope(keycloakResponse.getScope());
				exchange.getMessage().setBody(resp);
			})
			.removeHeaders("*")
		;
	}

}
