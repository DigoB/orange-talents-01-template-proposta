package br.com.zup.projetopropostacartao.repositories;

import br.com.zup.projetopropostacartao.cartao.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira,Long> {
}
