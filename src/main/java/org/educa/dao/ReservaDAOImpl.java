package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;
import org.educa.util.ReservaUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de {@link ReservaDAOInt}.
 */
public class ReservaDAOImpl implements ReservaDAOInt {

    @Override
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        String sql = "SELECT id_reserva, cod_reserva, fec_reserva, estado FROM reservas WHERE id_cliente = ? ORDER BY fec_reserva";
        List<ReservaEntity> reservas = new ArrayList<>();

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cliente.getIdCliente());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReservaEntity reserva = new ReservaEntity();
                    reserva.setIdReserva(rs.getInt("id_reserva"));
                    reserva.setCodReserva(rs.getString("cod_reserva"));
                    reserva.setCliente(cliente);
                    reserva.setFecReserva(rs.getTimestamp("fec_reserva").toLocalDateTime());
                    reserva.setEstado(rs.getString("estado"));
                    reservas.add(reserva);
                }
            }
        }
        return reservas;
    }

    @Override
    public void save(ReservaEntity reserva, Connection conn) throws SQLException {
        String sql = "INSERT INTO reservas (cod_reserva, id_cliente) " +
                "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM reservas WHERE cod_reserva = ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_reserva"})) {

            String codReserva;
            boolean insertada = false;
            do {
                codReserva = ReservaUtils.generarCodigoReserva();
                ps.setString(1, codReserva);
                ps.setInt(2, reserva.getCliente().getIdCliente());
                ps.setString(3, codReserva);
                insertada = ps.executeUpdate() > 0;
            } while (!insertada);

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    reserva.setIdReserva(rs.getInt(1));
                }
            }

            reserva.setCodReserva(codReserva);
        }
    }
}
