package org.educa.service;

import org.educa.dao.AsientoDAOImpl;
import org.educa.dao.AsientoDAOInt;
import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Lógica de negocio relacionada con los asientos.
 */
public class AsientoService {

    AsientoDAOInt asientoDAO = new AsientoDAOImpl();

    /**
     * Obtiene los asientos libres del avión que realiza un vuelo, es decir, los que todavía
     * no tienen un billete vendido para ese vuelo.
     *
     * @param vuelo vuelo del que se quieren saber los asientos libres
     * @return lista de asientos libres con su suplemento
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        return asientoDAO.findDisponibles(vuelo);
    }
}
