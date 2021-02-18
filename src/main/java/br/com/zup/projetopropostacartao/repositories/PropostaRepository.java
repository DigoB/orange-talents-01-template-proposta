package br.com.zup.projetopropostacartao.repositories;

import br.com.zup.projetopropostacartao.propostas.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    boolean existsByDocumento(String documento);

    @Query("SELECT p FROM Proposta p WHERE p.cartaoCriado = false AND p.status = 0")
    Collection<Proposta> findByCartaoNaoCriado();

    Optional<Proposta> findByDocumento(String documento);

}