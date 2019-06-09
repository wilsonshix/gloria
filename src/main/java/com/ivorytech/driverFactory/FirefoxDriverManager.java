package com.ivorytech.driverFactory;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager{
	
	private GeckoDriverService fxService;
	
	@Override
	public void startService(){
		if(null==fxService){
			try{
				fxService = new GeckoDriverService.Builder()
						 .usingDriverExecutable(new File("C:\\SeleniumGecko\\geckodriver.exe"))
						 .usingAnyFreePort()
						 .build();
				fxService.start();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopService() {
		if(null!=fxService && fxService.isRunning())
			fxService.stop();
	}

	@Override
	public void createDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("test-type");	    
		capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
		driver = new FirefoxDriver(fxService, capabilities);
	}

	
}
