package ar.com.factorIt.ecommercerest.compra.repository;

import ar.com.factorIt.ecommercerest.compra.entity.Compra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CompraRepositoryCustom {

    public List<Compra> getCompras(Map<String, Object> params);

}
