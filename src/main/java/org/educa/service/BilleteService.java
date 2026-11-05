package org.educa.service;

import org.educa.dao.BilleteDAOImpl;
import org.educa.dao.BilleteDAOInt;
import org.educa.entity.BilleteEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Lógica de negocio relacionada con los billetes.
 */
public class BilleteService {

    private static final long EDAD_INFANTIL = 12;
    private static final int RESERVAS_PARA_DESCUENTO = 3;
    private static final BigDecimal FACTOR_DESCUENTO_INFANTIL = new BigDecimal("0.80");
    private static final BigDecimal FACTOR_DESCUENTO_RESERVAS = new BigDecimal("0.90");

    private BilleteDAOInt billeteDAO = new BilleteDAOImpl();
    private VueloService vueloService = new VueloService();

    /**
     * Calcula el precio final de un billete y lo asigna a la entidad. Suma el precio base del vuelo y el
     * suplemento del asiento, aplica un 20% de descuento si el pasajero tiene menos de 12 años en la fecha
     * de salida del vuelo y, después, un 10% si el cliente tiene más de 3 reservas previas.
     * El resultado se redondea a 2 decimales.
     *
     * @param billete     billete con pasajero, vuelo y asiento informados
     * @param numReservas número de reservas previas del cliente
     */
    public void calculatePrecio(BilleteEntity billete, int numReservas) {
        BigDecimal precioBase = vueloService.getPrecioBase(billete.getVuelo());
        BigDecimal suplementoAsiento = billete.getAsiento().getSuplemento();

        BigDecimal precioFinal = precioBase.add(suplementoAsiento);

        LocalDate fechaNacimiento = billete.getPasajero().getFecNac();
        LocalDate fechaSalidaVuelo = billete.getVuelo().getFecSalida().toLocalDate();
        long edadEnVuelo = ChronoUnit.YEARS.between(fechaNacimiento, fechaSalidaVuelo);

        if (edadEnVuelo < EDAD_INFANTIL) {
            precioFinal = precioFinal.multiply(FACTOR_DESCUENTO_INFANTIL);
        }

        if (numReservas > RESERVAS_PARA_DESCUENTO) {
            precioFinal = precioFinal.multiply(FACTOR_DESCUENTO_RESERVAS);
        }

        billete.setPrecio(precioFinal.setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * Guarda un billete dentro de una transacción ya abierta.
     *
     * @param connection conexión de la transacción en curso
     * @param billete    billete a guardar
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        billeteDAO.save(connection, billete);
    }
}
