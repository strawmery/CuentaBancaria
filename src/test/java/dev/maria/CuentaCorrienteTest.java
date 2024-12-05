package dev.maria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CuentaCorrienteTest {

    @Test
    void testRetirarConSaldoSuficiente() throws Exception {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(1000);
        double saldoRestante = cuenta.retirar(500);
        assertEquals(500, saldoRestante);
    }

    @Test
    void testRetirarConSobregiro() throws Exception {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(500);
        cuenta.setSobregiro(200);
        double saldoRestante = cuenta.retirar(600); // Usa 100 de sobregiro
        assertEquals(0, cuenta.getSaldo());
        assertEquals(100, cuenta.getSobregiro());
    }

    @Test
    void testRetirarCantidadMayorQueDisponible() {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(500);
        cuenta.setSobregiro(200);
        assertThrows(Exception.class, () -> cuenta.retirar(800)); // Excede el saldo + sobregiro
    }

    @Test
    void testRetirarCantidadNegativa() {
        CuentaCorriente cuenta = new CuentaCorriente();
        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(-100));
    }

    @Test
    void testConsignarCantidadValida() throws Exception {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(500);
        double saldoNuevo = cuenta.consignar(200);
        assertEquals(700, saldoNuevo);
    }

    @Test
    void testConsignarReduceSobregiro() throws Exception {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(0);
        cuenta.setSobregiro(100);
        double saldoNuevo = cuenta.consignar(150); // Reduce sobregiro en 100
        assertEquals(50, saldoNuevo);
        assertEquals(0, cuenta.getSobregiro());
    }

    @Test
    void testConsignarCantidadNegativa() {
        CuentaCorriente cuenta = new CuentaCorriente();
        assertThrows(IllegalArgumentException.class, () -> cuenta.consignar(-200));
    }

    @Test
    void testExtractoMensual() throws Exception {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(1000);
        double comision = cuenta.extractoMensual();
        assertEquals(10, comision); // 1% de 1000
    }

    @Test
    void testImprimir() {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldo(500);
        cuenta.setSobregiro(200);
        cuenta.setConsignaciones(2);
        cuenta.setRetiros(1);
        cuenta.setComisionMensual(10);

        cuenta.imprimir(); // Verifica manualmente que la salida sea la esperada.
    }
}

