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
package com.ribas.andrei.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.ribas.andrei.db.DataSourceUtils;
import com.ribas.andrei.model.DBSchema;


/**
 * @author Andrei Gonçalves Ribas <andrei.g.ribas@gmail.com>
 *
 */
@WebServlet(name = "dbData", urlPatterns = { "/" })
public class DBDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088162498970797795L;

	private static final Logger LOGGER = Logger.getLogger(DBDataServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

		try {

			Class.forName("org.h2.Driver");

			Collection<DBSchema> dbSchemasRetrievedWithDefaultInitialContext = this.getDataFromDataSource(DataSourceUtils.getDataSourceByJNDI());

			req.setAttribute("dbSchemasRetrievedWithDefaultInitialContext", dbSchemasRetrievedWithDefaultInitialContext);

		} catch (ClassNotFoundException | SQLException | NamingException e) {
			logAndCreateException(req, e);
		} finally {

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/dbSchemas.jsp");

			try {
				dispatcher.forward(req, resp);
			} catch (IOException e) {
				logAndCreateException(req, e);
			}

		}

	}

	private void logAndCreateException(HttpServletRequest req, Exception e) {

		LOGGER.error("An exception occurred.", e);

		String exceptionStackTrace = ExceptionUtils.getStackTrace(e);
		
		req.setAttribute("exceptionStackTrace", exceptionStackTrace);

	}

	private Collection<DBSchema> getDataFromDataSource(DataSource dataSource) throws SQLException {

		Collection<DBSchema> dbSchemas = null;

		try (Connection connection = dataSource.getConnection()) {

			String sql = "select schema_name, is_default from information_schema.schemata order by schema_name";

			try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {

					if (dbSchemas == null) {
						dbSchemas = new ArrayList<>();
					}

					dbSchemas.add(new DBSchema(rs.getString("SCHEMA_NAME"), rs.getBoolean("IS_DEFAULT")));

				}

			}

		}

		return dbSchemas;

	}

}
