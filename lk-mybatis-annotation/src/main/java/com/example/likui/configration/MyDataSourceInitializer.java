package com.example.likui.configration;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
* 类说明
* @author likui 
* @since  2020年5月12日 上午12:04:17
* @version 1.0
*
*/
//@Configuration
public class MyDataSourceInitializer implements InitializingBean{
	@Value("classpath:dev/sql/schema1.sql")
	private Resource schemaScript;
	@Value("classpath:dev/sql/data1.sql")
    private Resource dataScript1;
	@Value("classpath:dev/sql/data2.sql")
	private Resource dataScript2;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(schemaScript);
        populator.addScripts(dataScript1);
        populator.addScripts(dataScript2);
        populator.setSeparator(";");
        return populator;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
}
