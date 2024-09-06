package config;

import lombok.Getter;

public enum TestEnvironment {
    TEST_1("test1.json"),
    TEST_2("test2.json"),
    LIVE("live.json");

    @Getter
    private String envFileName;

    TestEnvironment(String envFileName) {
        this.envFileName = envFileName;
    }
}
