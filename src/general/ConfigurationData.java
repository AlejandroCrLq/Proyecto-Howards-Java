package general;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationData {
	public enum Property{
		ftpServer,
		ftpPort
	}

	private InputStream inputStream;

	public String ReadProperty(String propertyToRead) throws IOException {
		String result = "";
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.resources";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
			
			result = prop.getProperty(propertyToRead);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}

}
