public class Camioneta extends Vehiculo {
    private double capacidadToneladas;

    public Camioneta(String placa, String marca, String modelo,
                    double tarifaDiaria, double capacidadToneladas) {
        super(placa, marca, modelo, tarifaDiaria);

        if (!Double.isFinite(capacidadToneladas)
                || capacidadToneladas <= 0) {
            throw new IllegalArgumentException(
                "La capacidad debe ser mayor que cero."
            );
        }

        this.capacidadToneladas = capacidadToneladas;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        return (getTarifaDiaria() + 100 * capacidadToneladas) * dias;
    }

    @Override
    public String toString() {
        return super.toString()
            + " | Capacidad: " + capacidadToneladas + " toneladas";
    }
}
