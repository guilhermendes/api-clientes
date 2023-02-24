package io.github.guimendes.clientes.rest;

import io.github.guimendes.clientes.model.entity.Cliente;
import io.github.guimendes.clientes.model.entity.ServicoPrestado;
import io.github.guimendes.clientes.model.repository.ClienteRepository;
import io.github.guimendes.clientes.model.repository.ServicoPrestadoReposity;
import io.github.guimendes.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.guimendes.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoControler {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoReposity repository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto){

        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Optional<Cliente> clienteOption = clienteRepository.findById(idCliente);
        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow( () ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST, "Cliente Inexistente.") );

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData( data );
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor( BigDecimalConverter.converter(dto.getPreco()) );
        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
