package dev.maria;

public class CuentaAhorros extends Cuenta {
    private boolean activa;

    public CuentaAhorros(Long id, double saldo,double tasaAnual, boolean activa){
        super(id, saldo, tasaAnual);
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
    public double retirar(double cantidad) throws Exception {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que cero");
        }
        
        if (!isActiva()) {
            throw new Exception("No se puede retirar dinero de una cuenta inactiva");
        }
        
        if (cantidad > getSaldo()) {
            throw new Exception("Saldo insuficiente para realizar el retiro");
        }
        
        double nuevoSaldo = getSaldo() - cantidad;
        setSaldo(nuevoSaldo);
        setRetiros(getRetiros() + 1);
        return nuevoSaldo;
    }

    @Override
    public double consignar(double cantidad) throws Exception {
        if(cantidad <= 0){
            throw new Exception("error, la cantidad a consignar debe ser mayor a 0");
        }
        if(!isActiva()){
            throw new Exception("error, no se puede consignar dinero a una cuenta inactiva");
        }
        double nuevoSaldo = getSaldo() + cantidad;
        setSaldo(nuevoSaldo);
        setConsignaciones(getConsignaciones()+1);
        return nuevoSaldo;
    }

    public double calcularInteres() throws Exception {
        if (isActiva()) {
            double interes = getSaldo() * (getTasaAnual() / 12); // Interés mensual
            double nuevoSaldo = getSaldo() + interes;
            setSaldo(nuevoSaldo);
            return interes;
        } else {
            throw new Exception("No se pueden calcular intereses en una cuenta inactiva");
        }
    }

    @Override
    public double extractoMensual() throws Exception {
        if(isActiva()){
            double saldoComisionMensual = getSaldo() - getComisionMensual();
            double nuevoSaldo = calcularInteres();
            return nuevoSaldo;
        }else{
            throw new Exception("error, la cuenta esta inactiva");
        }
        
    }

    @Override
    public void imprimir() {
        System.out.println("id: "+getId() +
                           "\nactiva: "+isActiva()+
                           "\nsaldo: "+getSaldo()+
                           "\nconsignaciones: "+getConsignaciones()+
                           "\nretiros: "+getRetiros()+
                           "\ntasa anual: "+getTasaAnual()+
                           "\ncomision mensual: "+getComisionMensual());
    }


}