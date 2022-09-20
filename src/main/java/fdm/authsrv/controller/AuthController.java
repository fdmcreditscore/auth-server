package fdm.authsrv.controller;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fdm.authsrv.dto.IntrospectRequestInfo;
import fdm.authsrv.dto.IntrospectResponseInfo;
import fdm.authsrv.dto.TokenRequestInfo;
import fdm.authsrv.dto.TokenResponseInfo;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private ProducerTemplate template;

    @PostMapping("/token")
    public TokenResponseInfo getToken(@RequestBody TokenRequestInfo token) {
    	Object o = template.sendBody("direct:auth", ExchangePattern.InOut, token);
    
    	TokenResponseInfo resp = (TokenResponseInfo) o;
    	return resp;
    	
    }

    @PostMapping("/introspect")
    public IntrospectResponseInfo introspect(@RequestBody IntrospectRequestInfo req) {
    	Object o = template.sendBody("direct:introspect", ExchangePattern.InOut, req);
    
    	IntrospectResponseInfo resp = (IntrospectResponseInfo) o;
    	return resp;
    	
    }

}
