package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

/**
 * Acceso a datos de los clientes.
 */
public interface ClienteDAOInt {

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni DNI del cliente
     * @return el cliente encontrado o {@code null} si no existe
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    ClienteEntity findByDni(String dni) throws SQLException;

    /**
     * Da de alta un cliente y actualiza la entidad con el identificador generado.
     *
     * @param cliente cliente a guardar
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    void saveCliente(ClienteEntity cliente) throws SQLException;
}
