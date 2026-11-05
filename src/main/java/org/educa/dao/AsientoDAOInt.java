package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Acceso a datos de los asientos de los aviones.
 */
public interface AsientoDAOInt {

    /**
     * Recupera los asientos del avión de un vuelo que todavía no tienen un billete vendido para ese vuelo,
     * calculando el suplemento de cada uno según su clase.
     *
     * @param vuelo vuelo del que se quieren conocer los asientos libres
     * @return lista de asientos libres con su suplemento
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException;
}
