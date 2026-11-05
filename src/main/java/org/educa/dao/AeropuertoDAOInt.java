package org.educa.dao;

import org.educa.entity.AeropuertoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Acceso a datos de los aeropuertos.
 */
public interface AeropuertoDAOInt {

    /**
     * Recupera todos los aeropuertos registrados en el sistema.
     *
     * @return lista de aeropuertos ordenada por su identificador
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    List<AeropuertoEntity> findAll() throws SQLException;
}
