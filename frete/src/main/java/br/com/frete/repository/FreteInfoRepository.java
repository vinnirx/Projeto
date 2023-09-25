package br.com.frete.repository;

import br.com.frete.entity.FreteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreteInfoRepository extends JpaRepository<FreteInfo, Long> {

    Optional<FreteInfo> findByCep(String cep);
}