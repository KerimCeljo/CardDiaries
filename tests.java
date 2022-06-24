package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class cardairiesTest {


	private static WebDriver webDriver;
	private static String baseURL;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\imtec\\Downloads\\chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		baseURL = "https://swe-cardairies.herokuapp.com/login.html";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		webDriver.close();
	}

	// ***Log in test***

	@Test
	void loginTest() throws InterruptedException {
		webDriver.get(baseURL);
		String title = webDriver.getTitle();
		System.out.println("Title:" + title);

		assertEquals("CarDiaries Login", title);
		Thread.sleep(4000);


		//Log in
		WebElement email = webDriver.findElement(By.name("email"));
        email.sendKeys("kerim.celjo20@gmail.com");

        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("kerim");
        Thread.sleep(2000);

        WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[1]/div/div[2]/form/div[3]/button"));
    	login.click();
    	Thread.sleep(2000);


	}



	// ***Adding new car test***

	@Test
	void addCarTest() throws InterruptedException {
		webDriver.get(baseURL);
		String title = webDriver.getTitle();
		System.out.println("Title:" + title);
		assertEquals("CarDiaries Login", title);
		Thread.sleep(3000);


		//Log in
				WebElement email = webDriver.findElement(By.name("email"));
		        email.sendKeys("kerim.celjo20@gmail.com");

		        WebElement password = webDriver.findElement(By.name("password"));
		        password.sendKeys("kerim");
		        Thread.sleep(2000);

		        WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[1]/div/div[2]/form/div[3]/button"));
		    	login.click();
		    	Thread.sleep(2000);


		//Home Page -> Cars

		    	WebElement carsButton = webDriver.findElement(By.xpath("/html/body/nav/div/li[2]/a"));
		    	carsButton.click();
		    	Thread.sleep(3000);

		    	WebElement car = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/a"));
		    	car.click();
		    	Thread.sleep(3000);

		    	WebElement model_name = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[1]/div[1]/input"));
		        model_name.sendKeys("Integra");
		        Thread.sleep(1000);

		        WebElement vin = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[2]/div[1]/input"));
		        vin.sendKeys("JH4DA9460MS032070");

		        WebElement num_of_doors = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[3]/div[1]/input"));
		        num_of_doors.sendKeys("3");

		        WebElement manufacturer = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[4]/div[1]/input"));
		        manufacturer.sendKeys("Acura");

		        WebElement year_of_production = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[1]/div[2]/input"));
		        year_of_production.sendKeys("1991");

		        WebElement mileage = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[2]/div[2]/input"));
		        mileage.sendKeys("245.730");

		        WebElement engine_power_kw = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[3]/div[2]/input"));
		        engine_power_kw.sendKeys("92");

		        WebElement fuel = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[4]/div[2]/input"));
		        fuel.sendKeys("Gasoline");
		        Thread.sleep(2000);

		        WebElement clearCar = webDriver.findElement(By.xpath("/html/body/div/div/main/section[3]/div[3]/form/div[5]/button[1]"));
		    	clearCar.click();
		    	Thread.sleep(2000);

		    	WebElement backHome = webDriver.findElement(By.xpath("/html/body/nav/div/li[1]/a"));
		    	backHome.click();
		    	Thread.sleep(1000);
	}



	// **Posting new car test***

	@Test
	void addPostTest() throws InterruptedException {
		webDriver.get(baseURL);
		String title = webDriver.getTitle();
		System.out.println("Title:" + title);
		assertEquals("CarDiaries Login", title);
		Thread.sleep(3000);


		//Log in

		WebElement email = webDriver.findElement(By.name("email"));
		email.sendKeys("kerim.celjo20@gmail.com");

		WebElement password = webDriver.findElement(By.name("password"));
		password.sendKeys("kerim");
		        Thread.sleep(2000);

		WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[1]/div/div[2]/form/div[3]/button"));
		login.click();
		Thread.sleep(2000);



		//Home page -> Add Post

		WebElement addPost = webDriver.findElement(By.xpath("/html/body/div/div/main/section[1]/button"));
		addPost.click();
		Thread.sleep(1000);

	    Select cardropDown = new Select (webDriver.findElement(By.id("home-cars-insert")));
		cardropDown.selectByVisibleText("C class");
		Thread.sleep(2000);

		WebElement postDesc = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section[1]/div[1]/div/div/form/div[2]/div[2]/textarea"));
		postDesc.sendKeys("Best car ever!");

		WebElement chooseFile = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section[1]/div[1]/div/div/form/div[2]/div[4]/div/input"));
		chooseFile.sendKeys("C:\\Users\\imtec\\Downloads\\Mercedes.jpg");
		Thread.sleep(3000);
	}


	// ***Log in and Log out testing***

	@Test
	void LogOutTest() throws InterruptedException {
		webDriver.get(baseURL);

		//Log in
		WebElement email = webDriver.findElement(By.name("email"));
        email.sendKeys("kerim.celjo20@gmail.com");

        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("kerim");
        Thread.sleep(2000);

        WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[1]/div/div[2]/form/div[3]/button"));
    	login.click();
    	Thread.sleep(2000);

    	WebElement profileButton = webDriver.findElement(By.xpath("/html/body/nav/div/li[5]/a"));
     	profileButton.click();
     	Thread.sleep(1000);


     	WebElement emailStatus = webDriver.findElement(By.xpath("/html/body/div/div/main/section[5]/div[2]/div/div[1]/p/b[3]"));
     	assertTrue(emailStatus.getText().contains("kerim.celjo20@gmail.com"));
     	Thread.sleep(1000);


     	WebElement logoutButton = webDriver.findElement(By.xpath("/html/body/div/div/main/section[5]/div[3]/button"));
     	logoutButton.click();
     	Thread.sleep(2000);

     	String title = webDriver.getTitle();
		System.out.println("Title:" + title);

		String url = webDriver.getCurrentUrl();
		assertEquals("https://swe-cardairies.herokuapp.com/login.html", url);
		Thread.sleep(2000);

	}

	// ***Registration Testing***

	@Test
	void registrationTest() throws InterruptedException {
		webDriver.get(baseURL);
		String title = webDriver.getTitle();
		System.out.println("Title:" + title);

		assertEquals("CarDiaries Login", title);
		Thread.sleep(4000);

		WebElement registrationButton = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[1]/div/div[3]/div/button"));
		registrationButton.click();
     	Thread.sleep(2000);

     	WebElement firstName = webDriver.findElement(By.name("first_name"));
     	firstName.sendKeys("kerim");

        WebElement lastName = webDriver.findElement(By.name("last_name"));
        lastName.sendKeys("celjo");

        WebElement email = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[2]/div/div[2]/form/div[2]/input"));
        email.sendKeys("kerim.celjo20@gmail.com");

        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("kerim");

        WebElement password = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[2]/div/div[2]/form/div[4]/div/div/input"));
        password.sendKeys("kerim");

        Thread.sleep(2000);

        WebElement goToLoginButton = webDriver.findElement(By.xpath("/html/body/div/div[1]/main/div/div[3]/div[2]/div/div[3]/div/button"));
        goToLoginButton.click();
     	Thread.sleep(2000);

     	String titleback = webDriver.getTitle();
		System.out.println("Title:" + titleback);

		assertEquals("CarDiaries Login", titleback);
		Thread.sleep(1000);

	}
		
}
