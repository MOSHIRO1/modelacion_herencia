public class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo,
                       double tarifaDiaria, int cilindraje) {
        super(placa, marca, modelo, tarifaDiaria);

        if (cilindraje <= 0) {
            throw new IllegalArgumentException(
                "El cilindraje debe ser mayor que cero."
            );
        }

        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(int dias) {
        validarDias(dias);

        double recargo = cilindraje == 250 ? 75 : 0;
        return getTarifaDiaria() * dias + recargo;
    }

    @Override
    public String toString() {
        return super.toString()
            + " | Cilindraje: " + cilindraje + " cc";
    }
}