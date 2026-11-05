package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de {@link AeropuertoDAOInt}.
 */
public class AeropuertoDAOImpl implements AeropuertoDAOInt {

    @Override
    public List<AeropuertoEntity> findAll() throws SQLException {
        String sql = "SELECT id_aeropuerto, cod_iata, nombre, ciudad, pais FROM aeropuertos ORDER BY id_aeropuerto";
        List<AeropuertoEntity> listAero = new ArrayList<>();

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AeropuertoEntity aeropuerto = new AeropuertoEntity();
                aeropuerto.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                aeropuerto.setCodIata(rs.getString("cod_iata"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCiudad(rs.getString("ciudad"));
                aeropuerto.setPais(rs.getString("pais"));
                listAero.add(aeropuerto);
            }
        }

        return listAero;
    }
}
