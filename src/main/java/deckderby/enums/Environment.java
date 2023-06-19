package deckderby.enums;

public enum Environment {
    LOCAL("local"),
    DEVELOPMENT("dev");

    private final String value;

    Environment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Environment fromValue(String value) {
        for (Environment environment : values()) {
            if (environment.getValue().equalsIgnoreCase(value)) {
                return environment;
            }
        }
        throw new IllegalArgumentException("Invalid environment value: " + value);
    }
}