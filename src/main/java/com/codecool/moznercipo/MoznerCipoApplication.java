package com.codecool.moznercipo;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoznerCipoApplication {

    @Autowired
    private ShoeRepository shoeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoznerCipoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(){
        return args -> {
            Shoe shoe1 = Shoe.builder()
                    .name("Fekete")
                    .brand("Bugatti")
                    .price("10 000 HUF")
                    .quantitiy(2)
                    .build();
            shoeRepository.save(shoe1);
        };
    }
}
