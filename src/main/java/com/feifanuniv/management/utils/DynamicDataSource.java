package com.feifanuniv.management.utils;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp.AbandonedConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.SQLNestedException;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPoolFactory;

import org.slf4j.LoggerFactory;

public class DynamicDataSource extends BasicDataSource {
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DynamicDataSource.class); 
	private static boolean isChanged = false;
	private static Map<String,String> map;
	private AbandonedConfig abandonedConfig;

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}
	
	 protected synchronized DataSource createDataSource()
		        throws SQLException {
		        if (closed) {
		            throw new SQLException("Data source is closed");
		        }
		        
		        if (isChanged) {
		        	super.dataSource = null;
		        	
		        	super.driverClassName = map.get("jdbc1.driver");
		        	super.url = map.get("jdbc1.url");
		        	super.username =map.get("jdbc1.username");
		        	super.password =map.get("jdbc1.password");
		        	
		        	isChanged = false;
		        	log.debug("\n");
		        	log.debug("\n");
		        	log.debug("--------------------------------------------------------------------------------------------------------");
		        	log.debug("                                 学校"+super.url.substring(super.url.indexOf("qingshu_")+8,super.url.indexOf("?"))+"                                                              ");
		        	log.debug("--------------------------------------------------------------------------------------------------------");
		        	log.debug("\n");
		        	log.debug("\n");
		        	log.debug("切换数据库连接到ConnectionUrl:--------"+super.url);
		        	return super.createDataSource();
		        }
		        
		        // Return the pool if we have already created it
		        if (dataSource != null) {
		            return (dataSource);
		        }
				 
		        // create factory which returns raw physical connections
		        ConnectionFactory driverConnectionFactory = createConnectionFactory();

		        // create a pool for our connections
		        createConnectionPool();

		        // Set up statement pool, if desired
		        GenericKeyedObjectPoolFactory statementPoolFactory = null;
		        if (isPoolPreparedStatements()) {
		            statementPoolFactory = new GenericKeyedObjectPoolFactory(null,
		                        -1, // unlimited maxActive (per key)
		                        GenericKeyedObjectPool.WHEN_EXHAUSTED_FAIL,
		                        0, // maxWait
		                        1, // maxIdle (per key)
		                        maxOpenPreparedStatements);
		        }

		        // Set up the poolable connection factory
		        createPoolableConnectionFactory(driverConnectionFactory, statementPoolFactory, abandonedConfig);

		        // Create and return the pooling data source to manage the connections
		        createDataSourceInstance();
		        
		        try {
		            for (int i = 0 ; i < initialSize ; i++) {
		                connectionPool.addObject();
		            }
		        } catch (Exception e) {
		            throw new SQLNestedException("Error preloading the connection pool", e);
		        }
		        
		        return dataSource;
		    }
	
	
	public static synchronized void setChanged(boolean fileChanged,Map<String,String> datamap) {
		map=datamap;
		isChanged = fileChanged;
		}
		 
	public static synchronized boolean isChanged() {
		return isChanged;
		}

}
