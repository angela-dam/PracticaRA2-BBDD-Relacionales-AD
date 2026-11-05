package org.educa.service;

import org.educa.dao.PasajeroDAO;
import org.educa.dao.PasajeroDAOImpl;
import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Lógica de negocio relacionada con los pasajeros.
 */
public class PasajeroService {

    private PasajeroDAO pasajeroDAO = new PasajeroDAOImpl();

    /**
     * Guarda un pasajero dentro de una transacción ya abierta.
     *
     * @param connection conexión de la transacción en curso
     * @param pasajero   pasajero a guardar
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        pasajeroDAO.save(connection, pasajero);
    }
}
