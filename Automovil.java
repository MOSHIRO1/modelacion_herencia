public class Automovil extends Vehiculo {
    private int cantPasajeros;
    private boolean esAutomatico;

    public Automovil(String placa, String marca, String modelo,
                     double tarifaDiaria, int cantPasajeros,
                     boolean esAutomatico) {
        super(placa, marca, modelo, tarifaDiaria);

        if (cantPasajeros <= 0) {
            throw new IllegalArgumentException(
                "La cantidad de pasajeros debe ser mayor que cero."
            );
        }

        this.cantPasajeros = cantPasajeros;
        this.esAutomatico = esAutomatico;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double recargo = esAutomatico ? 50 : 0;
        return (getTarifaDiaria() + recargo) * dias;
    }

    @Override
    public String toString() {
        return super.toString()
            + " | Pasajeros: " + cantPasajeros
            + " | Transmisión: "
            + (esAutomatico ? "Automática" : "Manual");
    }
}