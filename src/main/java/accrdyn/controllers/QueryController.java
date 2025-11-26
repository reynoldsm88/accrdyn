package accrdyn.controllers;

import accrdyn.api.CodeInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/query" )
public class QueryController {

    @RequestMapping( "/codes" )
    public ResponseEntity<CodeInfo> codeInfo( String system, String code ) {
        CodeInfo info = new CodeInfo( "FAKE_0", "00000", "2025", "cassette tapes" );
        return ResponseEntity.ok( new CodeInfo() );
    }

    @RequestMapping( "/concord" )
    public ResponseEntity<String> concord( String fromSystem, String toSystem ) {
        return ResponseEntity.ok( "" );
    }

}
