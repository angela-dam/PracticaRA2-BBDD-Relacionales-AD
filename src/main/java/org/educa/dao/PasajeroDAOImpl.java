package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación JDBC de {@link PasajeroDAO}.
 */
public class PasajeroDAOImpl implements PasajeroDAO {

    @Override
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        String sql = "INSERT INTO pasajeros (dni, nombre, p_apellido, s_apellido, id_reserva, email, telefono, fec_nac) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_pasajero"})) {
            ps.setString(1, pasajero.getDni());
            ps.setString(2, pasajero.getNombre());
            ps.setString(3, pasajero.getPrimerApellido());
            ps.setString(4, pasajero.getSegundoApellido());
            ps.setInt(5, pasajero.getIdReserva());
            ps.setString(6, pasajero.getEmail());
            ps.setString(7, pasajero.getTelefono());
            ps.setDate(8, Date.valueOf(pasajero.getFecNac()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pasajero.setIdPasajero(rs.getInt(1));
                }
            }
        }
    }
}
