package com.kastrupf.algafood.domain.repository;

import java.util.List;

import com.kastrupf.algafood.domain.model.MoyenDePayment;

public interface MoyenDePaymentRepository {
	
	List<MoyenDePayment> tous();
	MoyenDePayment parId(Long id);
	MoyenDePayment ajouter(MoyenDePayment moyenDePayment);
	void supprimer(MoyenDePayment moyenDePayment);

}
