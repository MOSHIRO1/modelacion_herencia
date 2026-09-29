import java.util.ArrayList;

public class SistemaAlquiler {
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Alquiler> alquileres;

    public SistemaAlquiler() {
        vehiculos = new ArrayList<>();
        alquileres = new ArrayList<>();
    }

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        if (buscarPorPlaca(vehiculo.getPlaca()) != null) {
            return false;
        }

        vehiculos.add(vehiculo);
        return true;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa.trim())) {
                return vehiculo;
            }
        }

        return null;
    }

    public double cotizar(String placa, int dias) {
        Vehiculo vehiculo = buscarPorPlaca(placa);

        if (vehiculo == null) {
            throw new IllegalArgumentException(
                "No existe un vehículo con esa placa."
            );
        }

        
        return vehiculo.calcularCosto(dias);
    }

    public boolean confirmarAlquiler(String placa, int dias) {
        Vehiculo vehiculo = buscarPorPlaca(placa);

        if (vehiculo == null || !vehiculo.isDisponible()) {
            return false;
        }


        Alquiler alquiler = new Alquiler(vehiculo, dias);
        alquileres.add(alquiler);

        return true;
    }

    public boolean devolverVehiculo(String placa) {
        for (Alquiler alquiler : alquileres) {
            if (alquiler.isActivo()
                    && alquiler.getVehiculo().getPlaca()
                        .equalsIgnoreCase(placa.trim())) {
                alquiler.registrarDevolucion();
                return true;
            }
        }

        return false;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos);
    }

    public ArrayList<Alquiler> getAlquileres() {
        return new ArrayList<>(alquileres);
    }
}