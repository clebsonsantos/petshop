package com.crowde.fenrir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Cliente;
import com.crowde.fenrir.repository.helper.cliente.ClientesQueries;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries{

}
