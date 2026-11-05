package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Acceso a datos de los billetes.
 */
public interface BilleteDAOInt {

    /**
     * Inserta un billete utilizando una conexión externa, de forma que pueda formar parte de una transacción.
     *
     * @param connection conexión sobre la que se ejecuta la inserción
     * @param billete    billete a guardar, con pasajero, vuelo, asiento y precio informados
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    void save(Connection connection, BilleteEntity billete) throws SQLException;
}
