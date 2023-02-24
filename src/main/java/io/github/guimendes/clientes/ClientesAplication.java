package io.github.guimendes.clientes;

import io.github.guimendes.clientes.model.entity.Cliente;
import io.github.guimendes.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ClientesAplication {

    public static void main(String[] args) {

        SpringApplication.run(
                ClientesAplication.class, args
        );
    }
}

