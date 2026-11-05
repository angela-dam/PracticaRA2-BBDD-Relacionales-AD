package org.educa.service;

import org.educa.dao.VueloDAOImpl;
import org.educa.dao.VueloDAOInt;
import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Lógica de negocio relacionada con los vuelos.
 */
public class VueloService {

    private static final BigDecimal PRECIOMINUTO = new BigDecimal("0.75");
    VueloDAOInt vueloDAO = new VueloDAOImpl();

    /**
     * Obtiene los vuelos no cancelados que salen de un aeropuerto.
     *
     * @param origen aeropuerto de origen
     * @return lista de vuelos con su aeropuerto de destino
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        return vueloDAO.findByOrigen(origen);
    }

    /**
     * Calcula el precio base de un vuelo a razón de 0,75€ por cada minuto transcurrido
     * entre la fecha de salida y la de llegada.
     *
     * @param vuelo vuelo con fechas de salida y llegada informadas
     * @return precio base del vuelo con 2 decimales
     */
    public BigDecimal getPrecioBase(VueloEntity vuelo) {
        BigDecimal minutos = BigDecimal.valueOf(ChronoUnit.MINUTES.between(vuelo.getFecSalida(), vuelo.getFecLlegada()));
        return minutos.multiply(PRECIOMINUTO);
    }
}
