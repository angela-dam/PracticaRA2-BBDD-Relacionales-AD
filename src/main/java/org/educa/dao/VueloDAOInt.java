package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Acceso a datos de los vuelos.
 */
public interface VueloDAOInt {

    /**
     * Recupera los vuelos no cancelados que salen de un aeropuerto, junto con su aeropuerto de destino.
     *
     * @param origen aeropuerto de origen
     * @return lista de vuelos ordenada por fecha de salida
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException;
}
