package web.app.configuration;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implements an Application initialisation before anything else is
 * done.
 */
public class ApplicationInitialization extends HttpServlet {

	private static final long serialVersionUID = -4356462604773999266L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("WEB Application is starting to initialize log4j");

		String log4jlocation = config.getInitParameter("log4j-properties-location");

		ServletContext context = config.getServletContext();

		if (log4jlocation == null) {
			System.err.println("log4j properties not found. Start BasicConfigurator");
			BasicConfigurator.configure();
		} else {
			String webAppPath = context.getRealPath("/");
			String log4jpropPath = webAppPath + log4jlocation;
			File log4jFile = new File(log4jpropPath);

			if (log4jFile.exists()) {
				System.out.println("Configuring log4j from: " + log4jpropPath);
				PropertyConfigurator.configure(log4jpropPath);
			} else {
				System.err.println("Configuration file " + log4jpropPath + " not found. Initialize with basic configurator.");
				BasicConfigurator.configure();
			}
		}

		super.init(config);
	}

}
