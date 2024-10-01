import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Vehiculos", "postgres", "")) {
            VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("Opciones:");
                System.out.println("1. Listar placas");
                System.out.println("2. Detalle vehículo por placa");
                System.out.println("3. Agregar vehículo");
                System.out.println("4. Ordenar vehículos");
                System.out.println("5. Buscar por modelo y año");
                System.out.println("6. Comprar vehículo");
                System.out.println("7. Disminuir precios");
                System.out.println("8. Vehículo más antiguo");
                System.out.println("9. Vehículo más potente");
                System.out.println("10. Vehículo más barato");
                System.out.println("0. Salir");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        List<String> placas = vehiculoDAO.obtenerPlacas();
                        System.out.println("Placas: " + placas);
                        break;
                    case 2:
                        System.out.print("Ingrese placa: ");
                        String placa = scanner.next();
                        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculo(placa);
                        System.out.println(vehiculo != null ? vehiculo : "Vehículo no encontrado.");
                        break;
                    case 3:
                        System.out.print("Ingrese placa: ");
                        String nuevaPlaca = scanner.next();
                        System.out.print("Ingrese tipo: ");
                        String tipo = scanner.next();
                        System.out.print("Ingrese marca: ");
                        String marca = scanner.next();
                        System.out.print("Ingrese modelo: ");
                        String modelo = scanner.next();
                        System.out.print("Ingrese año: ");
                        int año = scanner.nextInt();
                        System.out.print("Ingrese número de ejes: ");
                        int ejes = scanner.nextInt();
                        System.out.print("Ingrese cilindrada: ");
                        int cilindrada = scanner.nextInt();
                        System.out.print("Ingrese valor: ");
                        double valor = scanner.nextDouble();

                        vehiculoDAO.agregarVehiculo(new Vehiculo(nuevaPlaca, tipo, marca, modelo, año, ejes, cilindrada, valor));
                        System.out.println("Vehículo agregado.");
                        break;
                    case 4:
                        System.out.print("Ingrese criterio de ordenación (modelo, marca, año): ");
                        String criterio = scanner.next();
                        List<Vehiculo> vehiculosOrdenados = vehiculoDAO.ordenarVehiculos(criterio);
                        System.out.println(vehiculosOrdenados);
                        break;
                    case 5:
                        System.out.print("Ingrese modelo: ");
                        String modeloBuscado = scanner.next();
                        System.out.print("Ingrese año: ");
                        int añoBuscado = scanner.nextInt();
                        List<String> placasEncontradas = vehiculoDAO.buscarPorModeloYAnio(modeloBuscado, añoBuscado);
                        System.out.println("Placas encontradas: " + placasEncontradas);
                        break;
                    case 6:
                        System.out.print("Ingrese placa del vehículo a comprar: ");
                        String placaCompra = scanner.next();
                        vehiculoDAO.eliminarVehiculo(placaCompra);
                        System.out.println("Vehículo comprado.");
                        break;
                    case 7:
                        System.out.print("Ingrese cantidad para disminuir precios: ");
                        double cantidad = scanner.nextDouble();
                        vehiculoDAO.disminuirPrecios(cantidad);
                        System.out.println("Precios actualizados.");
                        break;
                    case 8:
                        Vehiculo masAntiguo = vehiculoDAO.vehiculoMasAntiguo();
                        System.out.println("Vehículo más antiguo: " + (masAntiguo != null ? masAntiguo : "No se encontró ningún vehículo."));
                        break;
                    case 9:
                        Vehiculo masPotente = vehiculoDAO.vehiculoMasPotente();
                        System.out.println("Vehículo más potente: " + (masPotente != null ? masPotente : "No se encontró ningún vehículo."));
                        break;
                    case 10:
                        Vehiculo masBarato = vehiculoDAO.vehiculoMasBarato();
                        System.out.println("Vehículo más barato: " + (masBarato != null ? masBarato : "No se encontró ningún vehículo."));
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
