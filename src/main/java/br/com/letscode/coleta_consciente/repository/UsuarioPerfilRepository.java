package br.com.letscode.coleta_consciente.repository;

import br.com.letscode.coleta_consciente.entity.Perfil;
import br.com.letscode.coleta_consciente.entity.UsuarioPerfis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfis, Integer> {
}
