package br.com.acme.provider.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.acme.provider.model.Provider;

@Transactional
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	
	public List<Provider> findAll();
	
	public Optional<Provider> findById(Integer id);
	
}
