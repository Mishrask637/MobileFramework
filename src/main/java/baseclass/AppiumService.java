package baseclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utility.Helper;

public class AppiumService {

	static AppiumServiceBuilder builder;
	static AppiumDriverLocalService service;

	public static void startService() {

		Helper.createFileIfNotExists(System.getProperty("user.dir") + "/logs/serverlogs/appium.log");
        builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withLogFile(new File(System.getProperty("user.dir") + "\\logs\\serverlogs\\appium.log"));
        try {
            builder.withLogOutput(new FileOutputStream(System.getProperty("user.dir") + "\\logs\\serverlogs\\appium.log"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
		builder.withArgument(GeneralServerFlag.USE_PLUGINS, "element-wait");
		builder.withArgument(GeneralServerFlag.ALLOW_INSECURE,"chromedriver_autodownload");
		service = builder.build();
		service.start();
		System.out.println("Appium Service Started...");
	}

	public static void stopService() {
		System.out.println("Stopping Appium Service");
		service.stop();
		System.out.println("Appium Service Stopped...");
	}
}