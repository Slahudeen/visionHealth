package com.visionHealth.Web.utils;

public class PathFile {

    private static final String PROD = "prod";
    private static final String SANDBOX = "sandbox";
    private static final String DEV = "dev";

    String env = System.getProperty("env");
    String environment = env;

    public String webPropertiesFilePath() {
        switch (environment) {
            case PROD:
                return "configs/prod_environment/Web.properties";
            case SANDBOX:
                return "configs/sandbox_environment/Web.properties";
            case DEV:
                return "configs/dev_environment/Web.properties";
        }
        return null;

    }

}
