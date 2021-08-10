package com.crowde.fenrir.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crowde.fenrir.model.Cliente;
import com.crowde.fenrir.repository.filter.ClienteFilter;

public interface ClientesQueries {
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
}
