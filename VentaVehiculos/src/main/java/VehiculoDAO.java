import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    private Connection connection;

    public VehiculoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<String> obtenerPlacas() throws SQLException {
        List<String> placas = new ArrayList<>();
        String query = "SELECT placa FROM Vehiculo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                placas.add(rs.getString("placa"));
            }
        }
        return placas;
    }

    public Vehiculo obtenerVehiculo(String placa) throws SQLException {
        String query = "SELECT * FROM Vehiculo WHERE placa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, placa);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Vehiculo(
                            rs.getString("placa"),
                            rs.getString("tipo"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("anio"),
                            rs.getInt("ejes"),
                            rs.getInt("cilindrada"),
                            rs.getDouble("valor")
                    );
                }
            }
        }
        return null;
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws SQLException {
        String query = "INSERT INTO Vehiculo (placa, tipo, marca, modelo, anio, ejes, cilindrada, valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, vehiculo.getPlaca());
            pstmt.setString(2, vehiculo.getTipo());
            pstmt.setString(3, vehiculo.getMarca());
            pstmt.setString(4, vehiculo.getModelo());
            pstmt.setInt(5, vehiculo.getAño());
            pstmt.setInt(6, vehiculo.getEjes());
            pstmt.setInt(7, vehiculo.getCilindrada());
            pstmt.setDouble(8, vehiculo.getValor());
            pstmt.executeUpdate();
        }
    }

    public void eliminarVehiculo(String placa) throws SQLException {
        String query = "DELETE FROM Vehiculo WHERE placa = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, placa);
            pstmt.executeUpdate();
        }
    }

    public List<Vehiculo> ordenarVehiculos(String criterio) throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String query = "SELECT * FROM Vehiculo ORDER BY " + criterio;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vehiculos.add(new Vehiculo(
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getInt("ejes"),
                        rs.getInt("cilindrada"),
                        rs.getDouble("valor")
                ));
            }
        }
        return vehiculos;
    }

    public List<String> buscarPorModeloYAnio(String modelo, int año) throws SQLException {
        List<String> placas = new ArrayList<>();
        String query = "SELECT placa FROM Vehiculo WHERE modelo = ? AND anio = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, modelo);
            pstmt.setInt(2, año);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    placas.add(rs.getString("placa"));
                }
            }
        }
        return placas;
    }

    public void disminuirPrecios(double cantidad) throws SQLException {
        String query = "UPDATE Vehiculo SET valor = valor * 0.9 WHERE valor > ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, cantidad);
            pstmt.executeUpdate();
        }
    }

    public Vehiculo vehiculoMasAntiguo() throws SQLException {
        String query = "SELECT * FROM Vehiculo ORDER BY anio ASC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Vehiculo(
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getInt("ejes"),
                        rs.getInt("cilindrada"),
                        rs.getDouble("valor")
                );
            }
        }
        return null;
    }

    public Vehiculo vehiculoMasPotente() throws SQLException {
        String query = "SELECT * FROM Vehiculo ORDER BY cilindrada DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Vehiculo(
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getInt("ejes"),
                        rs.getInt("cilindrada"),
                        rs.getDouble("valor")
                );
            }
        }
        return null;
    }

    public Vehiculo vehiculoMasBarato() throws SQLException {
        String query = "SELECT * FROM Vehiculo ORDER BY valor ASC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Vehiculo(
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getInt("ejes"),
                        rs.getInt("cilindrada"),
                        rs.getDouble("valor")
                );
            }
        }
        return null;
    }
}
