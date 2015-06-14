/* 
The MIT License (MIT)
Copyright (c) 2015 Andrei Gonçalves Ribas <andrei.g.ribas@gmail.com>
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.ribas.andrei.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ribas.andrei.util.PropertiesUtils;

/**
 * @author Andrei Gonçalves Ribas <andrei.g.ribas@gmail.com>
 *
 */
public class DataSourceUtils {

	private static final Logger LOGGER = Logger
			.getLogger(DataSourceUtils.class);

	public static DataSource getDataSourceByJNDI() throws NamingException {

		Context context = new InitialContext();

		Context envContext = (Context) context.lookup("java:comp/env/");
		
		return getDataSourceByContext(envContext,
				PropertiesUtils.getDataSourceJndiName());

	}

	private static DataSource getDataSourceByContext(Context context,
			String dataSourceJNDI) throws NamingException {

		DataSource dataSource = (DataSource) context.lookup(dataSourceJNDI);

		LOGGER.debug(String.format(
				"Got the data source with with JNDI %s using context %s.",
				dataSourceJNDI, context));

		return dataSource;

	}
	
}
