package com.maia.apiveiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maia.apiveiculos.entity.Veiculo;

@Repository
public interface IVeiculoRepository extends JpaRepository<Veiculo, Long> {
	

}
