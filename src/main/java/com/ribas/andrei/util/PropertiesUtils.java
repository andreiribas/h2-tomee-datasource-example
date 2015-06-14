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
package com.ribas.andrei.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ribas.andrei.db.DataSourceUtils;



/**
 * @author Andrei Gonçalves Ribas <andrei.g.ribas@gmail.com>
 *
 */
public class PropertiesUtils {

	private static final Logger LOGGER = Logger.getLogger(DataSourceUtils.class);

	private static final String DATASOURCE_JNDI_NAME_PROPERTY_KEY = "ds.jndiName";
	
	private static final Properties applicationProperties = new Properties();

	static {

		InputStream stream = PropertiesUtils.class.getClassLoader().getResourceAsStream("datasource.properties");

		try {
			applicationProperties.load(stream);
		} catch (IOException e) {
			LOGGER.error("Error while loading the properties file.", e);
		}

	}

	public static String getDataSourceJndiName() {
		return applicationProperties.getProperty(DATASOURCE_JNDI_NAME_PROPERTY_KEY);
	}
	
}
