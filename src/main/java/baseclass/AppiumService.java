package baseclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utility.Helper;

public class AppiumService {

	static AppiumServiceBuilder builder;
	static AppiumDriverLocalService service;

	private static final String logPath = System.getProperty("user.dir") + "\\logs\\serverlogs\\appium.log";

	public static void startService() {
			Helper.createFileIfNotExists(System.getProperty("user.dir") + "/logs/serverlogs/appium.log");
			builder = new AppiumServiceBuilder();
			builder.withIPAddress("127.0.0.1");
			builder.usingPort(4723);
			File logfile = new File(logPath);
			if(!logfile.exists()){
				logfile.getParentFile().mkdirs();
                try {
                    logfile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
			builder.withLogFile(logfile);
			try {
				builder.withLogOutput(new FileOutputStream(logPath));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
//			builder.withArgument(GeneralServerFlag.USE_PLUGINS, "element-wait");
			builder.withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
			builder.withArgument(GeneralServerFlag.LOG_TIMESTAMP);
			service = builder.build();
			service.start();
			System.out.println("Appium Service Started...");
	}

	public static void stopService() {
		System.out.println("Stopping Appium Service");
        try {
            service.sendOutputTo(new FileOutputStream(logPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        service.stop();
		System.out.println("Appium Service Stopped...");
	}
}