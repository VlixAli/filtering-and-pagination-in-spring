package com.seinfeld.search.services;

import com.seinfeld.search.dtos.requests.GlobalOperator;
import com.seinfeld.search.dtos.requests.SearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequest searchRequest) {

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequest.getColumn()), searchRequest.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequest> searchRequests, GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequest searchRequest : searchRequests) {

                switch (searchRequest.getOperation()) {

                    case EQUAL:
                        Predicate equal = criteriaBuilder.equal(
                                root.get(searchRequest.getColumn()), searchRequest.getValue());
                        predicates.add(equal);
                        break;
                    case LIKE:
                        Predicate like = criteriaBuilder.like(
                                root.get(searchRequest.getColumn()), "%" + searchRequest.getValue() + "%");
                        predicates.add(like);
                        break;
                    case IN:
                        String[] split = searchRequest.getValue().split(",");
                        Predicate in = root.get(searchRequest.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                        break;
                    case GREATER_THAN:
                        Predicate greaterThan = criteriaBuilder.greaterThan(
                                root.get(searchRequest.getColumn()), searchRequest.getValue());
                        predicates.add(greaterThan);
                        break;
                    case LESS_THAN:
                        Predicate lessThan = criteriaBuilder.lessThan(
                                root.get(searchRequest.getColumn()), searchRequest.getValue());
                        predicates.add(lessThan);
                        break;
                    case BETWEEN:
                        String[] split2 = searchRequest.getValue().split(",");
                        Predicate between = criteriaBuilder.between(
                                root.get(searchRequest.getColumn()),
                                Long.parseLong(split2[0]), Long.parseLong(split2[1]));
                        predicates.add(between);
                        break;
                    case JOIN:
                        Predicate join = criteriaBuilder.equal(root.join(searchRequest.getJoinTable())
                                .get(searchRequest.getColumn()), searchRequest.getValue());
                        predicates.add(join);
                        break;
                    default:
                        throw new IllegalStateException("please enter the operation!");
                }
            }
            if (globalOperator.equals(GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
