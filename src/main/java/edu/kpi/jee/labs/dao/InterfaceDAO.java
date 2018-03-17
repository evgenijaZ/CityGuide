package edu.kpi.jee.labs.dao;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public interface InterfaceDAO<E, K> {

    List <E> getAll();

    boolean update(E entity);

    E getByKey(K key);

    boolean deleteByKey(K key);

    boolean create(E entity);

}