package br.com.zup.projetopropostacartao.repositories;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao,Long> {

}
