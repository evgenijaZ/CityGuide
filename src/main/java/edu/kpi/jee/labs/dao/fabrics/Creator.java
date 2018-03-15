package edu.kpi.jee.labs.dao.fabrics;

import java.sql.ResultSet;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public interface Creator<E> {
    public E create(ResultSet resultSet);
}
