package org.fugerit.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fugerit.java.core.db.dao.DAOUtilsNG;
import org.fugerit.java.core.db.dao.rse.StringRSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.agroal.api.AgroalDataSource;

@Path("/dao_utils_ng")
public class TestDaoUtilsNGRest {

	@Inject
	AgroalDataSource defaultDataSource;
	
	private static final Logger logger = LoggerFactory.getLogger( TestDaoUtilsNGRest.class );
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "/db_info" )
    public Response dbInfo() {
    	LinkedHashMap<String, String> props = new LinkedHashMap<>();
    	Response res = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
    	try ( Connection conn = this.defaultDataSource.getConnection() ) {
    		DatabaseMetaData dbmd = conn.getMetaData();
    		props.put( "database_name" , dbmd.getDatabaseProductName() );
    		props.put( "database_version" , dbmd.getDatabaseProductVersion() );
    		props.put( "driver_name" , dbmd.getDriverName() );
    		props.put( "driver_version" , dbmd.getDriverVersion() );
    		String catalogName = DAOUtilsNG.extraOne( conn, "SELECT * FROM INFORMATION_SCHEMA.INFORMATION_SCHEMA_CATALOG_NAME", StringRSE.DEFAULT );
    		props.put( "catalog_name" , catalogName );
    		res = Response.ok().entity( props ).build();
    	} catch (Exception e) {
    		logger.error( "Error : "+e, e );
		}
        return res;
    }
 
    
}