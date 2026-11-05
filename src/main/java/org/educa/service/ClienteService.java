package org.educa.service;

import org.educa.dao.ClienteDAOImpl;
import org.educa.dao.ClienteDAOInt;
import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

/**
 * Lógica de negocio relacionada con los clientes.
 */
public class ClienteService {

    ClienteDAOInt clienteDAO = new ClienteDAOImpl();

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni DNI del cliente
     * @return el cliente encontrado o {@code null} si no está registrado
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public ClienteEntity findByDNI(String dni) throws SQLException {
        return clienteDAO.findByDni(dni);
    }

    /**
     * Da de alta un cliente. Tras la operación la entidad contiene su identificador.
     *
     * @param cliente cliente a registrar
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public void saveCliente(ClienteEntity cliente) throws SQLException {
        clienteDAO.saveCliente(cliente);
    }
}
