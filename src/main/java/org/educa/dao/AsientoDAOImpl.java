package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de {@link AsientoDAOInt}.
 */
public class AsientoDAOImpl implements AsientoDAOInt {

    @Override
    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        String sql = "SELECT a.id_asiento, a.id_avion, a.cod_asiento, a.clase, " +
                "CASE a.clase " +
                "WHEN 'TURISTA' THEN 0.00 " +
                "WHEN 'PREMIUM_ECONOMY' THEN 35.00 " +
                "WHEN 'BUSINESS' THEN 120.00 " +
                "WHEN 'FIRST' THEN 250.00 " +
                "ELSE 0.00 " +
                "END AS suplemento " +
                "FROM vuelos v " +
                "INNER JOIN asientos a ON a.id_avion = v.id_avion " +
                "WHERE v.id_vuelo = ? " +
                "AND NOT EXISTS (SELECT 1 FROM billetes b WHERE b.id_asiento = a.id_asiento AND b.id_vuelo = v.id_vuelo) " +
                "ORDER BY a.id_asiento";

        List<AsientoEntity> listaAsientos = new ArrayList<>();

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vuelo.getIdVuelo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AsientoEntity asiento = new AsientoEntity();
                    asiento.setIdAsiento(rs.getInt("id_asiento"));
                    asiento.setIdAvion(rs.getInt("id_avion"));
                    asiento.setCodAsiento(rs.getString("cod_asiento"));
                    asiento.setClase(rs.getString("clase"));
                    asiento.setSuplemento(rs.getBigDecimal("suplemento"));
                    listaAsientos.add(asiento);
                }
            }
        }

        return listaAsientos;
    }
}
