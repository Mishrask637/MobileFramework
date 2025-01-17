package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

public class GenerateCucumberReports {

	public void createDir() {
		File dir = new File("target/reports/cucumberhtmlreports/");

		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public void removeDir() {
		File dir = new File("target/reports/cucumberhtmlreports/");

		if (dir.exists()) {
			dir.delete();
		}
	}

	public void generateCucumberReports() {

		removeDir();
		createDir();
		File reportOutputDirectory = new File("target/reports/cucumberhtmlreports/");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add(
				System.getProperty("user.dir")+"\\target\\reports\\json\\cucumberReports.json");

		String buildNumber = "1";
		String projectName = "MobileAutomationCucumberProject";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc for details
		configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
		// do not make scenario failed when step has status SKIPPED
		configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
		configuration.setBuildNumber(buildNumber);
		// addidtional metadata presented on main page
		configuration.addClassifications("Platform", "Android");
		configuration.addClassifications("Device", "One Plus 12");
		configuration.addClassifications("Branch", "release/1.0");

		// optionally add metadata presented on main page via properties file
//		List<String> classificationFiles = new ArrayList<>();
//		classificationFiles.add("properties-1.properties");
//		classificationFiles.add("properties-2.properties");
//		configuration.addClassificationFiles(classificationFiles);

		// optionally specify qualifiers for each of the report json files
//		        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
//		        configuration.setQualifier("cucumber-report-1","First report");
//		        configuration.setQualifier("cucumber-report-2","Second report");

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
		// and here validate 'result' to decide what to do if report has failed
	}

	public static void main(String[] args) {
		GenerateCucumberReports reports = new GenerateCucumberReports();
		reports.generateCucumberReports();
	}

}
