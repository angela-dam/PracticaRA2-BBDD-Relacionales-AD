package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Acceso a datos de las reservas.
 */
public interface ReservaDAOInt {

    /**
     * Recupera todas las reservas de un cliente.
     *
     * @param cliente cliente del que se quieren conocer las reservas
     * @return lista de reservas del cliente ordenada por fecha de reserva
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException;

    /**
     * Inserta una reserva utilizando una conexión externa, de forma que pueda formar parte de una transacción.
     * Genera un código de reserva que no coincide con el de ninguna reserva existente y actualiza la entidad
     * con el identificador y el código de la reserva.
     *
     * @param reserva reserva a guardar
     * @param conn    conexión sobre la que se ejecuta la inserción
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    void save(ReservaEntity reserva, Connection conn) throws SQLException;
}
