package br.com.zup.projetopropostacartao.repositories;

import br.com.zup.projetopropostacartao.cartao.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio,Long> {
}