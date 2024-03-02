package com.laguna.project18.WallapopGratuito.model;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.laguna.project18.WallapopGratuito.repository.CategoryRepository;
import com.laguna.project18.WallapopGratuito.repository.ProductRepository;
import com.laguna.project18.WallapopGratuito.repository.TagRepository;
import com.laguna.project18.WallapopGratuito.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class SampleDataPostConstruct {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final Faker faker = new Faker(new Locale("es"));


    @PostConstruct
    public void init(){
        try {
            Category category = new Category(
                    null,
                    faker.commerce().department(),
                    faker.lordOfTheRings().character(),
                    null
            );
            categoryRepository.save(category);
            //Creación de 5 usuarios
            for (int i = 0; i < 5; i++) {
                Integer in = Integer.parseInt(faker.bothify("####"));
    //            log.info(in.toString());
                User user = new User(
                        Long.getLong(faker.numerify("####")),
                        faker.name().username(),
                        faker.bothify("????##@gmail.com"),
                        null,
                        null,
                        BCrypt.hashpw(faker.bothify("????##????##????##"), BCrypt.gensalt()),
                        in,
                        null,
                        null
                );
                userRepository.save(user);
                Tag tag = new Tag(
                        null,
                        faker.howIMetYourMother().character(),
                        null,
                        null
                );
                tagRepository.save(tag);
                Product product = new Product(
                        null,
                        faker.leagueOfLegends().champion(),
                        faker.leagueOfLegends().quote(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        (long)1,
                        1,
                        user,
                        category,
                        Set.of(tag)
                );
                productRepository.save(product);
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }

    }
}