package com.project.service.implement;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SearchSpecification<T> implements Specification<T> {
    private String fieldName;
    private String value;

    public SearchSpecification(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (value == null || value.isEmpty()) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // no filtering
        }

        Path<String> fieldPath = root.get(fieldName);
        return criteriaBuilder.like(criteriaBuilder.lower(fieldPath), "%" + value.toLowerCase() + "%");
    }
}
