import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final SistemaAlquiler sistema = new SistemaAlquiler();
    private static final FinalJornada jornada = new FinalJornada();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nALQUILER DE VEHÍCULOS");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Cotizar");
            System.out.println("3. Confirmar alquiler");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Consultar resumen");
            System.out.println("0. Salir");

            try {
                int opcion = leerEntero("Opción: ");

                switch (opcion) {
                    case 1:
                        registrarVehiculo();
                        break;

                    case 2:
                        cotizar();
                        break;

                    case 3:
                        confirmarAlquiler();
                        break;

                    case 4:
                        devolverVehiculo();
                        break;

                    case 5:
                        System.out.println(jornada.generarResumen(sistema));
                        break;

                    case 0:
                        System.out.println(jornada.generarResumen(sistema));
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException error) {
                System.out.println("Error: " + error.getMessage());
            }
        }

        entrada.close();
    }

    private static void registrarVehiculo() {
        System.out.println("1. Automóvil");
        System.out.println("2. Motocicleta");
        System.out.println("3. Camioneta");

        int tipo = leerEntero("Tipo: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido.");
            return;
        }

        String placa = leerTexto("Placa: ");

        if (sistema.buscarPorPlaca(placa) != null) {
            System.out.println("Ya existe un vehículo con esa placa.");
            return;
        }

        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        double tarifa = leerDecimal("Tarifa diaria en Q: ");

        Vehiculo vehiculo;

        switch (tipo) {
            case 1:
                int pasajeros = leerEntero("Cantidad de pasajeros: ");
                int transmision = leerEntero(
                    "Transmisión (1 = automática, 2 = manual): "
                );

                if (transmision != 1 && transmision != 2) {
                    throw new IllegalArgumentException(
                        "La transmisión debe ser 1 o 2."
                    );
                }

                vehiculo = new Automovil(
                    placa, marca, modelo, tarifa,
                    pasajeros, transmision == 1
                );
                break;

            case 2:
                int cilindraje = leerEntero("Cilindraje en cc: ");

                vehiculo = new Motocicleta(
                    placa, marca, modelo, tarifa, cilindraje
                );
                break;

            default:
                double capacidad = leerDecimal(
                    "Capacidad máxima en toneladas: "
                );

                vehiculo = new Camioneta(
                    placa, marca, modelo, tarifa, capacidad
                );
        }

        if (sistema.registrarVehiculo(vehiculo)) {
            System.out.println("Vehículo registrado.");
        } else {
            System.out.println("La placa ya está registrada.");
        }
    }

    private static void cotizar() {
        String placa = leerTexto("Placa: ");
        int dias = leerEntero("Cantidad de días: ");

        double total = sistema.cotizar(placa, dias);
        Vehiculo vehiculo = sistema.buscarPorPlaca(placa);

        System.out.println(vehiculo);
        System.out.println("Días solicitados: " + dias);
        System.out.printf("Costo total: Q%.2f%n", total);
    }

    private static void confirmarAlquiler() {
        String placa = leerTexto("Placa: ");
        int dias = leerEntero("Cantidad de días: ");

        // Consultar el costo no modifica los ingresos.
        double total = sistema.cotizar(placa, dias);
        Vehiculo vehiculo = sistema.buscarPorPlaca(placa);

        if (!vehiculo.isDisponible()) {
            System.out.println("El vehículo está ocupado.");
            return;
        }

        System.out.printf("Costo total: Q%.2f%n", total);
        String respuesta = leerTexto("¿Confirmar alquiler? (s/n): ");

        if (!respuesta.equalsIgnoreCase("s")) {
            System.out.println("No se confirmó el alquiler.");
            return;
        }

        if (sistema.confirmarAlquiler(placa, dias)) {
            System.out.println("Alquiler confirmado.");
        } else {
            System.out.println("No se pudo confirmar el alquiler.");
        }
    }

    private static void devolverVehiculo() {
        String placa = leerTexto("Placa: ");

        if (sistema.devolverVehiculo(placa)) {
            System.out.println("Devolución registrada.");
        } else {
            System.out.println(
                "No hay un alquiler activo para esa placa."
            );
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException error) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                String texto = leerTexto(mensaje).replace(',', '.');
                double numero = Double.parseDouble(texto);

                if (Double.isFinite(numero)) {
                    return numero;
                }
            } catch (NumberFormatException error) {
                
            }

            System.out.println("Ingresa un número decimal válido.");
        }
    }
}