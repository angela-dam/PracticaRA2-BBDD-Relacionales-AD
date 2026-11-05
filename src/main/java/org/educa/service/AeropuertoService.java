package org.educa.service;

import org.educa.dao.AeropuertoDAOImpl;
import org.educa.dao.AeropuertoDAOInt;
import org.educa.entity.AeropuertoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Lógica de negocio relacionada con los aeropuertos.
 */
public class AeropuertoService {

    AeropuertoDAOInt aeropuertoDAO = new AeropuertoDAOImpl();

    /**
     * Obtiene todos los aeropuertos del sistema.
     *
     * @return lista de aeropuertos
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public List<AeropuertoEntity> findAll() throws SQLException {
        return aeropuertoDAO.findAll();
    }
}
