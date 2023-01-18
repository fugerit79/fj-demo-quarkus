package org.fugerit.java.demo.quarkus.jvsf;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.jvfs.JFile;
import org.fugerit.java.core.jvfs.JVFS;
import org.fugerit.java.core.jvfs.file.RealJMount;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/jvfs")
public class JVFSRest {

	private static final Logger logger = LoggerFactory.getLogger( JVFSRest.class );
	
	private static JVFS jvfs;
	static {
		try {
			File root = new File( "." );
			logger.info( "root -> {}", root.getCanonicalPath() );
			jvfs = RealJMount.createJVFS( root );	
		} catch (Exception e) {
			throw new ConfigRuntimeException( e );
		}
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "/list" )
    public Response list( @QueryParam("path") String path ) {
    	Response res = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    	try {
    		path = StringUtils.valueWithDefault( path , JFile.SEPARATOR );
    		logger.info( "path -> {}", path );    		
    		JFile jFile = jvfs.getJFile( path );
    		res = Response.ok().entity( 
    			Arrays.asList( jFile.listFiles() ).stream().map(  
    					f -> new JFileDTO( f )
    			).collect( Collectors.toList() )
    		).build();
    	} catch (Exception e) {
    		logger.error( "Error : "+e, e );
		}
        return res;
    }
	
}
