package dev.maria;

public class CuentaAhorros extends Cuenta {
    private boolean activa;

    public CuentaAhorros(Long id, double saldo, int transacciones, boolean activa){
        super(id, saldo);
        this.activa = activa;
    }
    public CuentaAhorros(){
        super();
    }
    public boolean isActiva() {
        return activa;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public double  retirar(double cantidad) {
        if(isActiva() && cantidad <= getSaldo()){
            double nuevoSaldo = getSaldo() - cantidad;
            setSaldo(nuevoSaldo);
            setTransacciones(getTransacciones()+1);
            return getSaldo();
        }
            return 0;
    }

    @Override
    public double consignar(double cantidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double extractoMensual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}