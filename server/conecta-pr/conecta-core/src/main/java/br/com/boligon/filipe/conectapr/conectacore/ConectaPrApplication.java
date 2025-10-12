package br.com.boligon.filipe.conectapr.conectacore;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = {
        "br.com.boligon.filipe.conectapr.conectautils",
        "br.com.boligon.filipe.conectapr.conectacore"
})
@EntityScan(basePackages = "br.com.boligon.filipe.conectapr.conectacore")
@EnableJpaRepositories(basePackages = "br.com.boligon.filipe.conectapr.conectacore")
public class ConectaPrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConectaPrApplication.class, args);
    }

    @Bean
    public CommandLineRunner migrateDatabase(Flyway flyway) {
        return args -> {
            flyway.migrate();
        };
    }

    @Component
    public static class StartupListener {

        private static final Logger log = LoggerFactory.getLogger(StartupListener.class);
        private final Environment environment;

        public StartupListener(Environment environment) {
            this.environment = environment;
        }

        @EventListener(ApplicationReadyEvent.class)
        public void onApplicationReady() {
            try {
                String host = InetAddress.getLocalHost().getHostAddress();
                String port = environment.getProperty("server.port", "8080");
                String contextPath = environment.getProperty("server.servlet.context-path", "");

                String localUrl = "http://localhost:" + port + contextPath;
                String networkUrl = "http://" + host + ":" + port + contextPath;

                System.out.println();
                System.out.println("================================================================================");
                System.out.println("                          🔗 CONECTA PR 🔗");
                System.out.println("================================================================================");
                System.out.println("  🚀 Service started successfully!");
                System.out.println("  🌐 Local:   " + localUrl);
                System.out.println("  🌐 Network: " + networkUrl);
                System.out.println("================================================================================");
                System.out.println();
            } catch (UnknownHostException e) {
                log.error("Failed to determine host IP address: {}", e.getMessage());
            }
        }
    }
}