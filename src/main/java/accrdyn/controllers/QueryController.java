package accrdyn.controllers;

import accrdyn.api.CodeInfoRequest;
import accrdyn.api.CodeInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/query" )
public class QueryController {

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
