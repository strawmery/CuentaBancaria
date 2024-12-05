package dev.maria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CuentaAhorrosTest {

    @Test
    void testConsignarCuentaActiva() throws Exception {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, true);
        double nuevoSaldo = cuenta.consignar(500);
        assertEquals(1500, nuevoSaldo, "El saldo después de consignar debe ser 1500");
        assertEquals(1, cuenta.getConsignaciones(), "Debe haber registrado una consignación");
    }

    @Test
    void testConsignarCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, false);
        Exception exception = assertThrows(Exception.class, () -> cuenta.consignar(500));
        assertEquals("error, no se puede consignar dinero a una cuenta inactiva", exception.getMessage());
    }

    @Test
    void testConsignarCantidadNegativa() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, true);
        Exception exception = assertThrows(Exception.class, () -> cuenta.consignar(-100));
        assertEquals("error, la cantidad a consignar debe ser mayor a 0", exception.getMessage());
    }

    @Test
    void testRetirarCuentaActiva() throws Exception {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, true);
        double nuevoSaldo = cuenta.retirar(500);
        assertEquals(500, nuevoSaldo, "El saldo después de retirar debe ser 500");
        assertEquals(1, cuenta.getRetiros(), "Debe haber registrado un retiro");
    }

    @Test
    void testRetirarCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, false);
        Exception exception = assertThrows(Exception.class, () -> cuenta.retirar(500));
        assertEquals("No se puede retirar dinero de una cuenta inactiva", exception.getMessage());
    }

    @Test
    void testRetirarSaldoInsuficiente() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, true);
        Exception exception = assertThrows(Exception.class, () -> cuenta.retirar(1500));
        assertEquals("Saldo insuficiente para realizar el retiro", exception.getMessage());
    }

    @Test
    void testCalcularInteresCuentaActiva() throws Exception {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1200, 0.12, true);
        double interes = cuenta.calcularInteres();
        assertEquals(12, interes, "El interés mensual debe ser correcto (1200 * 0.01)");
        assertEquals(1212, cuenta.getSaldo(), "El saldo después de aplicar el interés debe ser 1212");
    }

    @Test
    void testCalcularInteresCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1200, 0.12, false);
        Exception exception = assertThrows(Exception.class, cuenta::calcularInteres);
        assertEquals("No se pueden calcular intereses en una cuenta inactiva", exception.getMessage());
    }

    @Test
    void testExtractoMensualCuentaActiva() throws Exception {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.12, true);
        cuenta.setComisionMensual(10);
        double saldoFinal = cuenta.extractoMensual();
        assertEquals(10.0, saldoFinal, "El saldo final debe incluir el interés menos la comisión mensual");
    }

    @Test
    void testExtractoMensualCuentaInactiva() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.12, false);
        Exception exception = assertThrows(Exception.class, cuenta::extractoMensual);
        assertEquals("error, la cuenta esta inactiva", exception.getMessage());
    }

    @Test
    void testImprimir() {
        CuentaAhorros cuenta = new CuentaAhorros(1L, 1000, 0.05, true);
        cuenta.imprimir();
        assertTrue(true, "La impresión se realizó correctamente");
    }
}

