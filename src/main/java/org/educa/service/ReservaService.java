package org.educa.service;

import org.educa.dao.ReservaDAOImpl;
import org.educa.dao.ReservaDAOInt;
import org.educa.entity.BilleteEntity;
import org.educa.entity.ClienteEntity;
import org.educa.entity.PasajeroEntity;
import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Lógica de negocio relacionada con las reservas.
 */
public class ReservaService {

    ReservaDAOInt reservaDAO = new ReservaDAOImpl();

    /**
     * Obtiene todas las reservas de un cliente.
     *
     * @param cliente cliente del que se quieren conocer las reservas
     * @return lista de reservas del cliente
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        return reservaDAO.findByCliente(cliente);
    }

    /**
     * Guarda una reserva en una única transacción: la reserva, el pasajero de cada billete y el propio billete.
     * Si falla cualquiera de las inserciones se deshace la transacción y no queda nada guardado.
     *
     * @param reserva reserva a guardar con sus billetes
     * @throws SQLException si se produce un error al acceder a la base de datos
     */
    public void save(ReservaEntity reserva) throws SQLException {
        BilleteService billeteService = new BilleteService();
        PasajeroService pasajeroService = new PasajeroService();

        try (Connection conn = ConnectionPool.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            try {
                reservaDAO.save(reserva, conn);

                for (BilleteEntity billete : reserva.getBilletes()) {
                    PasajeroEntity pasajero = billete.getPasajero();
                    pasajero.setIdReserva(reserva.getIdReserva());
                    pasajeroService.save(conn, pasajero);
                    billeteService.save(conn, billete);
                }

                conn.commit();
            } catch (SQLException | RuntimeException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}
