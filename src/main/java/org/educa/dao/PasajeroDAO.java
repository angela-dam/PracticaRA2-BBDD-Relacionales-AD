package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Acceso a datos de los pasajeros.
 */
public interface PasajeroDAO {

    /**
     * Inserta un pasajero utilizando una conexión externa, de forma que pueda formar parte de una transacción,
     * y actualiza la entidad con el identificador generado.
     *
     * @param connection conexión sobre la que se ejecuta la inserción
     * @param pasajero   pasajero a guardar, con el identificador de su reserva informado
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    void save(Connection connection, PasajeroEntity pasajero) throws SQLException;
}
