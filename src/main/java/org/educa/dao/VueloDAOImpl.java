package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de {@link VueloDAOInt}.
 */
public class VueloDAOImpl implements VueloDAOInt {

    @Override
    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        String sql = "SELECT v.id_vuelo, v.num_vuelo, v.id_avion, v.fec_salida, v.fec_llegada, v.estado, " +
                "a.id_aeropuerto, a.cod_iata, a.nombre, a.ciudad, a.pais " +
                "FROM vuelos v " +
                "INNER JOIN aeropuertos a ON v.id_destino = a.id_aeropuerto " +
                "WHERE v.id_origen = ? AND v.estado <> 'CANCELADO' " +
                "ORDER BY v.fec_salida";

        List<VueloEntity> listaVuelos = new ArrayList<>();

        try (Connection conn = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, origen.getIdAeropuerto());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VueloEntity vuelo = new VueloEntity();
                    vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                    vuelo.setNumVuelo(rs.getString("num_vuelo"));
                    vuelo.setIdAvion(rs.getInt("id_avion"));
                    vuelo.setEstado(rs.getString("estado"));
                    vuelo.setOrigen(origen);

                    AeropuertoEntity destino = new AeropuertoEntity();
                    destino.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                    destino.setCodIata(rs.getString("cod_iata"));
                    destino.setNombre(rs.getString("nombre"));
                    destino.setCiudad(rs.getString("ciudad"));
                    destino.setPais(rs.getString("pais"));
                    vuelo.setDestino(destino);

                    vuelo.setFecSalida(rs.getTimestamp("fec_salida").toLocalDateTime());
                    vuelo.setFecLlegada(rs.getTimestamp("fec_llegada").toLocalDateTime());
                    listaVuelos.add(vuelo);
                }
            }
        }
        return listaVuelos;
    }
}
