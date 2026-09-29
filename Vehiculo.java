public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private boolean disponible;

    public Vehiculo(String placa, String marca,
                    String modelo, double tarifaDiaria) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa es obligatoria.");
        }

        if (!Double.isFinite(tarifaDiaria) || tarifaDiaria <= 0) {
            throw new IllegalArgumentException(
                "La tarifa diaria debe ser mayor que cero."
            );
        }

        this.placa = placa.trim().toUpperCase();
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public String getPlaca() {
        return placa;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    protected void validarDias(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException(
                "La cantidad de días debe ser mayor que cero."
            );
        }
    }

    public abstract double calcularCosto(int dias);

    @Override
    public String toString() {
        return "Placa: " + placa
            + " | Marca: " + marca
            + " | Modelo: " + modelo
            + " | Tarifa diaria: Q" + tarifaDiaria
            + " | Disponible: " + (disponible ? "Sí" : "No");
    }
}