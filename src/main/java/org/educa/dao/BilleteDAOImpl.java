package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Implementación JDBC de {@link BilleteDAOInt}.
 */
public class BilleteDAOImpl implements BilleteDAOInt {

    @Override
    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        String sql = "INSERT INTO billetes (id_pasajero, id_vuelo, id_asiento, precio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, billete.getPasajero().getIdPasajero());
            ps.setInt(2, billete.getVuelo().getIdVuelo());
            ps.setInt(3, billete.getAsiento().getIdAsiento());
            ps.setBigDecimal(4, billete.getPrecio());
            ps.executeUpdate();
        }
    }
}
