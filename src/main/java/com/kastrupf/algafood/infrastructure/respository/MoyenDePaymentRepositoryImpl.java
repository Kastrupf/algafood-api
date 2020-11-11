package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.MoyenDePayment;
import com.kastrupf.algafood.domain.repository.MoyenDePaymentRepository;

@Repository
public class MoyenDePaymentRepositoryImpl implements MoyenDePaymentRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public MoyenDePayment parId(Long id) {
		return manager.find(MoyenDePayment.class, id);
	}
	
	@Override
	public List<MoyenDePayment> tous() {
		 return manager.createQuery("from MoyenDePayment", MoyenDePayment.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public MoyenDePayment ajouter(MoyenDePayment moyenDePayment) {
		return manager.merge(moyenDePayment);
	}
	
	@Override
	@Transactional
	public void supprimer(MoyenDePayment moyenDePayment) {
		moyenDePayment = parId(moyenDePayment.getId());
		manager.remove(moyenDePayment);
	}

}
