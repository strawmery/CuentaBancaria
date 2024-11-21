package dev.maria;

abstract  class Cuenta {
    private long id;
    private double saldo;
    private int transacciones;

    public Cuenta(long id, double saldo) {
        this.id = id;
        this.saldo = saldo;
        this.transacciones = 0;
    }
    public Cuenta (){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(int transacciones) {
        this.transacciones = transacciones;
    }

    public abstract double retirar(double cantidad);
    public abstract double consignar(double cantidad);
    public abstract double extractoMensual();
    public abstract void imprimir();

}
