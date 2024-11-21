package dev.maria;

abstract  class Cuenta {
    private long id;
    private double saldo;
    private int consignaciones;
    private int retiros;
    private double tasaAnual;
    private double comisionMensual;

    public Cuenta(long id, double saldo, double tasaAnual) {
        this.id = id;
        this.saldo = saldo;
        this.consignaciones = 0;
        this.retiros = 0;
        this.tasaAnual = tasaAnual;
        this.comisionMensual = 0;
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

    public int getConsignaciones() {
        return consignaciones;
    }

    public void setConsignaciones(int consignaciones) {
        this.consignaciones = consignaciones;
    }

    public int getRetiros() {
        return retiros;
    }

    public void setRetiros(int retiros) {
        this.retiros = retiros;
    }

    public double getTasaAnual() {
        return tasaAnual;
    }

    public void setTasaAnual(double tasaAnual) {
        this.tasaAnual = tasaAnual;
    }

    public double getComisionMensual() {
        return comisionMensual;
    }

    public void setComisionMensual(double comisionMensual) {
        this.comisionMensual = comisionMensual;
    }

    public abstract double retirar(double cantidad) throws Exception;
    public abstract double consignar(double cantidad) throws Exception;
    public abstract double extractoMensual() throws Exception;
    public abstract void imprimir();

   

}
