package dev.maria;

public class CuentaCorriente extends Cuenta {
    private double sobregiro;

    public CuentaCorriente() {
        this.sobregiro = 0;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(double sobregiro) {
        this.sobregiro = sobregiro;
    }

    @Override
    public double retirar(double cantidad) throws Exception {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que cero");
        }
        
        double saldoDisponible = getSaldo() + sobregiro;
        if (cantidad > saldoDisponible) {
            throw new Exception("La cantidad excede el saldo disponible y el sobregiro permitido");
        }
        
        if (cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
        } else {
            double cantidadSobregiro = cantidad - getSaldo();
            setSaldo(0);
            sobregiro -= cantidadSobregiro;
        }
        
        setRetiros(getRetiros() + 1);
        return getSaldo();
    }
    @Override
    public double consignar(double cantidad) throws Exception {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a consignar debe ser mayor que cero");
        }
        double nuevoSaldo = getSaldo() + cantidad;
        setSaldo(nuevoSaldo);

        if (sobregiro > 0) {
            double cantidadReduccionSobregiro = Math.min(sobregiro, nuevoSaldo - getSaldo());
            sobregiro -= cantidadReduccionSobregiro;
            setSaldo(getSaldo() - cantidadReduccionSobregiro);
        }

        return getSaldo();
    }

    @Override
    public double extractoMensual() throws Exception {
        double comisionMensual = getSaldo() * 0.01; // Assuming a simple commission calculation
        setComisionMensual(comisionMensual);
        return getComisionMensual();
    }

    @Override
    public void imprimir() {
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Comisión mensual: " + getComisionMensual());
        System.out.println("Número de transacciones: " + (getConsignaciones() + getRetiros()));
        System.out.println("Valor de sobregiro: " + sobregiro);
    }
}
