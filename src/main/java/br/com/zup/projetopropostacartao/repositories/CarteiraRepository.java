package br.com.zup.projetopropostacartao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.projetopropostacartao.carteiras.Carteira;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira,Long> {
    
}
