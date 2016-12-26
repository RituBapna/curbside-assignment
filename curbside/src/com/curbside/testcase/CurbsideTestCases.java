package com.curbside.testcase;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurbsideTestCases {
	private Map<String, Integer> mapWithFullStateName = new HashMap<String, Integer>();
	private Map<String, Integer> jobMap = new HashMap<String, Integer>();

	public Map<String, Integer> getJobMap() {
		return jobMap;
	}

	public void setJobMap(Map<String, Integer> jobMap) {
		this.jobMap = jobMap;
	}

	public Map<String, Integer> getMapWithFullStateName() {
		return mapWithFullStateName;
	}

	public void setMapWithFullStateName(
			Map<String, Integer> mapWithFullStateName) {
		this.mapWithFullStateName = mapWithFullStateName;
	}

	@Test
	public void firstTestCase() {

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\ritujai.ORADEV\\Downloads\\chromedriver_win32\\chromedriver.exe"
		 * );
		 */
		ChromeDriver driver = getChromeDriver();
		driver.get("https://curbside.com/jobs/");
		WebElement table = driver.findElement(By.id("jobsTable"));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		int i = 0;
		String str[] = new String[3];

		Boolean flag = false;
		Integer value = 0;
		/* Map<String, Integer> jobMap = new HashMap<String, Integer>(); */
		Map<String, String> states = new HashMap<String, String>();

		states.put("AL", "Alabama");
		states.put("AK", "Alaska");
		states.put("AZ", "Arizona");
		states.put("AR", "Arkansas");
		states.put("CA", "California");
		states.put("CO", "Colorado");
		states.put("CT", "Connecticut");
		states.put("DE", "Delaware");
		states.put("DC", "District of Columbia");
		states.put("FL", "Florida");
		states.put("GA", "Georgia");
		states.put("HI", "Hawaii");
		states.put("ID", "Idaho");
		states.put("IL", "Illinois");
		states.put("IN", "Indiana");
		states.put("IA", "Iowa");
		states.put("KS", "Kansas");
		states.put("KY", "Kentucky");
		states.put("LA", "Louisiana");
		states.put("ME", "Maine");
		states.put("MD", "Maryland");
		states.put("MA", "Massachusetts");
		states.put("MI", "Michigan");
		states.put("MN", "Minnesota");
		states.put("MS", "Mississippi");
		states.put("MO", "Missouri");
		states.put("MT", "Montana");
		states.put("NE", "Nebraska");
		states.put("NV", "Nevada");
		states.put("NH", "New Hampshire");
		states.put("NJ", "New Jersey");
		states.put("NM", "New Mexico");
		states.put("NY", "New York");
		states.put("NC", "North Carolina");
		states.put("ND", "North Dakota");
		states.put("OH", "Ohio");
		states.put("OK", "Oklahoma");
		states.put("OR", "Oregon");
		states.put("PA", "Pennsylvania");
		states.put("PR", "Puerto Rico");
		states.put("RI", "Rhode Island");
		states.put("SC", "South Carolina");
		states.put("SD", "South Dakota");
		states.put("TN", "Tennessee");
		states.put("TX", "Texas");
		states.put("VI", "U.S. Virgin Islands");
		states.put("UT", "Utah");
		states.put("VT", "Vermont");
		states.put("VA", "Virginia");
		states.put("WA", "Washington");
		states.put("WV", "West Virginia");
		states.put("WI", "Wisconsin");
		states.put("WY", "Wyoming");
		states.put("QC", "Quebec");

		for (WebElement row : allRows) {
			System.out.println();
			List<WebElement> cells = row.findElements(By.tagName("td"));
			List<String> list = new ArrayList<String>();

			i = 0;
			value = null;
			for (WebElement cell : cells) {
				if (cells.size() == 0)
					flag = false;
				else
					flag = true;
				str[i++] = cell.getText();
				// i = i + 1;
				list.add(cell.getText() + " ");
			}
			/**
			 * When the size of cell is not 0, the flag value is set to be true
			 * And thus values in the cells should be put in the map
			 * 
			 * **/
			if (flag) {
				/**
				 * Check if the map already contains a key If so fetch the value
				 * of the key, store in a variable i.e.'value' Now put back the
				 * key value pair in the map with new updated value
				 * 
				 * **/

				if (jobMap.containsKey(str[1])) {
					value = jobMap.get(str[1]);
					value = value + 1;
					jobMap.remove(str[1]);
					jobMap.put(str[1], value);
					setJobMap(getJobMap());
				}

				else {
					jobMap.put(str[1], 1);
					setJobMap(getJobMap());
				}

			}
		}
		/**
		 * Logic to create a new map which has full state name
		 * mapWithFullStateName has key as city name, full state name and value
		 * as integer
		 * 
		 * 
		 * **/

		for (String key : jobMap.keySet()) {

			String[] splitByComma = key.split(",");
			String[] splitBySpace = splitByComma[1].split(" ");

			Integer jobMapValue = jobMap.get(key);

			StringBuffer bf = new StringBuffer(splitByComma[0]);
			bf.append(", ");
			bf.append(states.get(splitBySpace[1]));

			mapWithFullStateName.put(bf.toString(), jobMapValue);
			setMapWithFullStateName(getMapWithFullStateName());

		}
		for (String key : getMapWithFullStateName().keySet()) {
			System.out.println(key + ": " + getMapWithFullStateName().get(key));
		}

	}

	@Test
	public void secondTestCase() {

		ChromeDriver driver = getChromeDriver();
		driver.get("https://careers.smartrecruiters.com/Curbside1/");

		/**
		 * 
		 * Comparing the result of testcase #1 with testcase #2 Variable int i
		 * is used to keep track of no of sections created for job opening based
		 * on the city name For example, if size of map which is created in
		 * testcase #1 is 2, then two sections for the cities will be created on
		 * the page of testcase #2 if size of map is 3 then three sections on
		 * page 2 will be created, so on and so forth
		 * 
		 * **/
		int i = 1;
		for (String key : getMapWithFullStateName().keySet()) {
			System.out
					.println("------------------------------------------------");
			WebElement cityName = driver
					.findElementByXPath("//*[@id='st-openings']/div[2]/div/div/section["
							+ i + "]/header/ul/li[1]");
			WebElement noOfJobs = driver
					.findElementByXPath("//*[@id='st-openings']/div[2]/div/div/section["
							+ i + "]/header/ul/li[2]/span");
			/*
			 * System.out.println("city:: "+cityName.getText());
			 * System.out.println("no of jobs:: "+noOfJobs.getText());
			 */
			i++;

			String[] splitByComma = key.split(",");

			Assert.assertTrue(cityName.getText().contains(splitByComma[0]));
			Assert.assertTrue(noOfJobs.getText().contains(
					Integer.toString(getMapWithFullStateName().get(key))));
			/*
			 * System.out.println("key: " + key + " value: " +
			 * getMapWithFullStateName().get(key));
			 */

			/*
			 * city:: Palo Alto, CA no of jobs:: 12 jobs key: Palo Alto,
			 * California value: 12
			 */
		}

	}

	public ChromeDriver getChromeDriver() {
		/**
		 * 
		 * this is the path set to the location where chrome.exe is stored on
		 * your local machine This method returns instance of chrome webdriver
		 * 
		 * **/
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ritujai.ORADEV\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		return driver;
	}

}
