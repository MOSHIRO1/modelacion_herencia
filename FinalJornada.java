public class FinalJornada {

    public int contarRegistrados(SistemaAlquiler sistema) {
        return sistema.getVehiculos().size();
    }

    public int contarDisponiblesPorCategoria(
            SistemaAlquiler sistema, String categoria) {
        int cantidad = 0;

        for (Vehiculo vehiculo : sistema.getVehiculos()) {
            if (esCategoria(vehiculo, categoria)
                    && vehiculo.isDisponible()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int contarAlquiladosPorCategoria(
            SistemaAlquiler sistema, String categoria) {
        int cantidad = 0;

        for (Vehiculo vehiculo : sistema.getVehiculos()) {
            if (esCategoria(vehiculo, categoria)
                    && !vehiculo.isDisponible()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    private boolean esCategoria(Vehiculo vehiculo, String categoria) {
        return vehiculo.getClass().getSimpleName()
            .equalsIgnoreCase(categoria);
    }

    public double calcularIngresosConfirmados(SistemaAlquiler sistema) {
        double total = 0;

        for (Alquiler alquiler : sistema.getAlquileres()) {

            total += alquiler.getCostoTotal();
        }

        return total;
    }

    public String generarResumen(SistemaAlquiler sistema) {
        StringBuilder resumen = new StringBuilder();

        resumen.append("\nRESUMEN DE JORNADA\n");
        resumen.append("Vehículos registrados: ")
            .append(contarRegistrados(sistema))
            .append("\n");

        String[] categorias = {
            "Automovil", "Motocicleta", "Camioneta"
        };

        int totalDisponibles = 0;
        int totalAlquilados = 0;

        for (String categoria : categorias) {
            int disponibles =
                contarDisponiblesPorCategoria(sistema, categoria);
            int alquilados =
                contarAlquiladosPorCategoria(sistema, categoria);

            totalDisponibles += disponibles;
            totalAlquilados += alquilados;

            resumen.append(categoria)
                .append(": registrados = ")
                .append(disponibles + alquilados)
                .append(", disponibles = ")
                .append(disponibles)
                .append(", alquilados = ")
                .append(alquilados)
                .append("\n");
        }

        resumen.append("Total disponibles: ")
            .append(totalDisponibles).append("\n");
        resumen.append("Total alquilados: ")
            .append(totalAlquilados).append("\n");
        resumen.append(String.format(
            "Ingresos por alquileres confirmados: Q%.2f%n",
            calcularIngresosConfirmados(sistema)
        ));

        return resumen.toString();
    }
}