public class Alquiler {
    private Vehiculo vehiculo;
    private int dias;
    private double costoTotal;
    private boolean activo;

    public Alquiler(Vehiculo vehiculo, int dias) {
        if (!vehiculo.isDisponible()) {
            throw new IllegalArgumentException(
                "El vehículo ya está alquilado."
            );
        }

        this.costoTotal = vehiculo.calcularCosto(dias);
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.activo = true;

        vehiculo.setDisponible(false);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void registrarDevolucion() {
        if (!activo) {
            throw new IllegalArgumentException(
                "Este alquiler ya fue devuelto."
            );
        }

        activo = false;
        vehiculo.setDisponible(true);
    }

    @Override
    public String toString() {
        return "Placa: " + vehiculo.getPlaca()
            + " | Días: " + dias
            + " | Total: Q" + costoTotal
            + " | Estado: " + (activo ? "Activo" : "Devuelto");
    }
}