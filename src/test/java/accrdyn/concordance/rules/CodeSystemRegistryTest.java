package accrdyn.concordance.rules;

import accrdyn.concordance.CodeDefinition;
import accrdyn.concordance.CodeSystem;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class CodeSystemRegistryTest {

    @Test
    public void testTablesawPoc() throws Exception {
        ClassLoader classLoader = MethodHandles.lookup().getClass().getClassLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver( classLoader );

        Resource[] codeSystemFiles = resolver.getResources( "classpath:code_systems/*.csv" );


        for ( Resource codeSystemCsv : codeSystemFiles ) {
            if ( codeSystemCsv.isFile() ) {
                CsvReadOptions options =
                        CsvReadOptions
                                .builder( codeSystemCsv.getFile() )
                                .columnTypes(
                                        new ColumnType[]{
                                                ColumnType.STRING,
                                                ColumnType.STRING,
                                                ColumnType.STRING,
                                                ColumnType.STRING,
                                        }
                                )
                                .build();

                Table codeTable = Table.read().csv( codeSystemCsv.getFile() );

                Column<String> systemCol = codeTable.column( "system" ).unique().asStringColumn();
                Column<String> versionCol = codeTable.column( "version" ).unique().asStringColumn();

                String name = null;
                if ( systemCol.size() == 1 ) {
                    name = systemCol.get( 0 );
                } else {
                    throw new RuntimeException( "fix this!!!" );
                }

                String version = null;
                if ( versionCol.size() == 1 ) {
                    version = versionCol.get( 0 );
                } else {
                    throw new RuntimeException( "fix this!!!" );
                }

                List<CodeDefinition> codes = codeTable
                        .stream()
                        .map( row -> {
                            String code = row.getString( "code" );
                            String description = row.getString( "description" );
                            return new CodeDefinition( code, description );
                        } )
                        .toList();

                CodeSystem system = new CodeSystem( name, version, codes );
            }
        }
    }
}
