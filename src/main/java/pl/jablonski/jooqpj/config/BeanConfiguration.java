package pl.jablonski.jooqpj.config;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Configuration
public class BeanConfiguration {

    @Bean
    public Faker getFaker() {
        return new Faker();
    }

    @Bean
    public Random getRandom() throws NoSuchAlgorithmException {
        return SecureRandom.getInstanceStrong();
    }
}
