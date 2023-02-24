package io.github.guimendes.clientes.model.repository;

import io.github.guimendes.clientes.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ServicoPrestadoReposity extends JpaRepository<ServicoPrestado, Integer> {
    @Query(" select s from ServicoPrestado s join s.cliente c where upper( c.nome ) like upper( :nome )  and MONTH(s.data) = :mes")

    List<ServicoPrestado> findByNomeClienteAndMes(
            @Param("nome")String nome,  @Param("mes") Integer mes);
}
