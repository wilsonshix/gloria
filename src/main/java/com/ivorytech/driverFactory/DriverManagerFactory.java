package com.ivorytech.driverFactory;

import com.ivorytech.driverFactory.ChromeDriverManager;
import com.ivorytech.driverFactory.DriverManager;
import com.ivorytech.driverFactory.FirefoxDriverManager;
import com.ivorytech.driverFactory.SafariDriverManager;

public class DriverManagerFactory {
	
	public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new SafariDriverManager();
                break;
        }
        return driverManager;

    }
	
	public enum DriverType {
	    CHROME,
	    FIREFOX,
	    IE,
	    SAFARI;
	}

}
