package xyz.zzzxb.serve.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;

/**
 * zzzxb
 * 2024/3/13
 */
@Slf4j
@Configuration
public class AutoOpenBrowser {

    @Value("${server.port}")
    private String appPort;

    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() throws IOException {
        String url = "http://localhost:" + appPort;
        log.info("server: {}", url);

        String command = "open ";
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            command = "cmd /c start ";
        }
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command + url);
    }
}
