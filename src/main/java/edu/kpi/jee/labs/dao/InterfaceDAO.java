package edu.kpi.jee.labs.dao;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public interface InterfaceDAO<E, K> {

    public abstract List <E> getAll();

    public abstract boolean update(E entity);

    public abstract E getByKey(K key);

    public abstract boolean delete(E entity);

    public abstract boolean create(E entity);

}