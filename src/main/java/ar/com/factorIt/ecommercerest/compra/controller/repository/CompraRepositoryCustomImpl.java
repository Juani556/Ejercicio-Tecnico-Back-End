package ar.com.factorIt.ecommercerest.compra.controller.repository;

import ar.com.factorIt.ecommercerest.compra.entity.Compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompraRepositoryCustomImpl implements CompraRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Compra> getCompras(Map<String, Object> params) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Compra> query = cb.createQuery(Compra.class);
        Root<Compra> compra = query.from(Compra.class);






        List<Predicate> predicates = armarPredicates(params, cb, compra);

        Order order = null;

        if (params.containsKey("orden")) {
            order = cb.asc(compra.get((String) params.get("orden")));
        }

        query.select(compra)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()]))).orderBy(order);

        return entityManager.createQuery(query)
                .getResultList();

    }

    private List<Predicate> armarPredicates(Map<String, Object> params, CriteriaBuilder cb, Root compra) {
        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("from")) {
            predicates.add(cb.greaterThanOrEqualTo(compra.get("fecha"), (LocalDate) params.get("from")));
        }

        if (params.containsKey("to")) {
            predicates.add(cb.lessThanOrEqualTo(compra.get("fecha"), (LocalDate) params.get("to")));
        }

        predicates.add(cb.equal(compra.get("usuario"), params.get("usuario")));

        return predicates;
    }
}
