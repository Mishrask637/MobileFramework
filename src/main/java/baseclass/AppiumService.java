package baseclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumService {

	static AppiumServiceBuilder builder;
	static AppiumDriverLocalService service;

	public static void startService() {

		builder = new AppiumServiceBuilder();

		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		try {
			builder.withLogOutput(
					new FileOutputStream(new File(System.getProperty("user.dir") + "/Logs/ServerLogs/logs.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		builder.withArgument(GeneralServerFlag.USE_PLUGINS, "element-wait");
		service = builder.build();
		service.start();
	}

	public static void stopService() {

		service.stop();

	}

}
