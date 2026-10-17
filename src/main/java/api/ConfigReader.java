package api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.io.InputStream;

public class ConfigReader {

        private static final Properties properties = new Properties();

        static {
            try (InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties")) {

                if (input == null) {
                    throw new IllegalStateException(
                            "config.properties was not found"
                    );
                }

                properties.load(input);

            } catch (IOException e) {
                throw new RuntimeException(
                        "Could not load configuration", e
                );
            }
        }

        public static String getBaseUrl() {
            return properties.getProperty("base.url");
        }

        public static String getToken() {
            Path tokenPath = Path.of(
                    System.getProperty("user.home"),
                    "Desktop",
                    "NewProjectMVN",
                    "src",
                    "test",
                    "resources",
                    "secrets",
                    "token.txt"
            );

            try {
                return Files.readString(tokenPath).trim();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Could not read token file: " + tokenPath, e
                );
            }
        }
    }

