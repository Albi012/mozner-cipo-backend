package com.codecool.moznercipo;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class MoznerCipoApplication {

    @Autowired
    private ShoeRepository shoeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoznerCipoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Shoe shoe = Shoe.builder()
                    .price("10000")
                    .brand("Bugatti")
                    .size(Map.of("32", 1))
                    .url("213")
                    .category("men")
                    .build();
            shoeRepository.save(shoe);
        };
    }
}
