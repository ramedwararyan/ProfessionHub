package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.service.implement.SearchSpecification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class GenericSearchService {
	@Autowired
	private EntityManager entityManager;

	public <T> List<T> searchByJobField(Class<T> entityType, String jobField) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(entityType);
		Root<T> root = query.from(entityType);

		query.select(root).where(new SearchSpecification<>("jobField", jobField).toPredicate((Root<Object>) root, query,
				criteriaBuilder));

		return entityManager.createQuery(query).getResultList();
	}
}
