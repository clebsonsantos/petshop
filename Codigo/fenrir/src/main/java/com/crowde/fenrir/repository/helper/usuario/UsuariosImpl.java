package com.crowde.fenrir.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {

	@Autowired
	private EntityManager manager;
	
	@Override
	@Transactional
	public Optional<Usuario> porLoginEAtivo(String login) {	
		
		Optional<Usuario> usuario =  manager.createQuery("from Usuario where lower(login) = lower(:login) and ativo = true", Usuario.class)
					.setParameter("login", login)
						.getResultList().stream().findFirst();
		
		manager.createQuery("update Usuario u set u.dataAcesso = now() where id = :id ").setParameter("id", usuario.get().getId()).executeUpdate();
		
		return usuario;
	}

	@Override
	public List<String> permissoes(Usuario usuario) {
		
		return manager.createQuery("select distinct p.descricao from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
					.setParameter("usuario",usuario).getResultList();
	}

}
