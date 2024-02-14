package com.laguna.project18.WallapopGratuito.model;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.laguna.project18.WallapopGratuito.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class SampleDataPostConstruct {
    private final UserRepository userRepository;
    private final Faker faker = new Faker(new Locale("es"));
    @Autowired
    public SampleDataPostConstruct(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        try {
        for (int i = 0; i < 100; i++) {
            Integer in = Integer.parseInt(faker.bothify("####"));
            log.info(in.toString());
            userRepository.save(new User(
                    Long.getLong(faker.numerify("####")),
                    faker.name().username(),
                    faker.bothify("????##@gmail.com"),
                    BCrypt.hashpw(faker.bothify("????##????##????##"), BCrypt.gensalt()),
                    in,
                    null,
                    null
                    ));
        }} catch (Exception e){
            log.error(e.getMessage());
        }

    }
}
