package accrdyn.web;

import accrdyn.concordance.CodeMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/query" )
public class QueryController {

    @Autowired
    private CodeMatcher codeMatcher;

    @PostMapping( "/codes" )
    @ResponseBody
    public ResponseEntity<CodeInfoResponse> codeInfo( @RequestBody CodeInfoRequest request ) {

        CodeInfoResponse response = new CodeInfoResponse();
        response.setSystem( request.getSystem() );
        response.setCode( request.getCode() );


        return ResponseEntity.ok( response );
    }

    @RequestMapping( "/concord" )
    public ResponseEntity<String> concord( String fromSystem, String toSystem ) {
        return ResponseEntity.ok( "" );
    }

}
