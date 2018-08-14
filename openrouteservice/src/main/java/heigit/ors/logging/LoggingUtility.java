/*
 *  Licensed to GIScience Research Group, Heidelberg University (GIScience)
 *
 *   http://www.giscience.uni-hd.de
 *   http://www.heigit.org
 *
 *  under one or more contributor license agreements. See the NOTICE file 
 *  distributed with this work for additional information regarding copyright 
 *  ownership. The GIScience licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except in compliance 
 *  with the License. You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package heigit.ors.logging;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;


public class LoggingUtility {

	public static void init() throws Exception
	{
		if (LoggingSettings.getEnabled())
		{
			String settingsFileName = LoggingSettings.getLevelFile();

			if (settingsFileName != null)
			{
				ClassPathResource rs = new ClassPathResource("logs/" + settingsFileName);
				ConfigurationSource source = new ConfigurationSource(rs.getInputStream());

				File outputPath = Paths.get(LoggingSettings.getLocation()).toFile();

				ConfigurationBuilder<BuiltConfiguration> conf = ConfigurationBuilderFactory.newConfigurationBuilder();
				conf.setConfigurationSource(source);
				conf.addProperty("filename", LoggingSettings.getLocation() + "/ors-logs.log");

				conf.writeXmlConfiguration(System.out);
				Configurator.initialize(conf.build());

				//Configurator.initialize(null, source);

				/*File configFile = Paths.get(logsPath, location).toFile();
				if (configFile.exists())
				{
					File outputPath  = Paths.get(LoggingSettings.getLocation()).toFile();
					
					if (!outputPath.isAbsolute())
					   outputPath = Paths.get(logsPath, LoggingSettings.getLocation()).toFile();
					
					initInternal(configFile, outputPath.toString(), LoggingSettings.getStdOut());
				}
				else
					throw new Exception("Logging config file does not exist.");*/
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static void initInternal(File configFile, String outputPath, boolean stdOut) throws IOException
	{

		/*List<Appender> appenders = new ArrayList<Appender>();

		// Retrieve all existing appenders
		Enumeration apps = LogManager.getRootLogger().getAllAppenders();
		while(apps.hasMoreElements()) {
			Appender appender = (Appender)apps.nextElement();
			if (!(appender instanceof ConsoleAppender || appender instanceof FileAppender))
				appenders.add( appender );
		}

		Properties lprops = new Properties();
		lprops.load(new FileInputStream(configFile));
		LogManager.resetConfiguration();
		
		if (lprops.getProperty("log4j.appender.orslogfile.File") == null)
			lprops.put("log4j.appender.orslogfile.File", outputPath);
		
		PropertyConfigurator.configure(lprops);

		if (!stdOut) {
            apps = org.apache.log4j.Logger.getRootLogger().getAllAppenders();
            while (apps.hasMoreElements()) {
            	Appender appender = (Appender)apps.nextElement();
                if (appender instanceof ConsoleAppender) {
                    org.apache.log4j.Logger.getRootLogger().removeAppender(appender);
                }
            }
		} 

		for (Appender appender : appenders )
			LogManager.getRootLogger().addAppender( appender );*/
	}
}
