public class Vehiculo {
    private String placa;
    private String tipo;
    private String marca;
    private String modelo;
    private int año;
    private int ejes;
    private int cilindrada;
    private double valor;

    public Vehiculo(String placa, String tipo, String marca, String modelo, int año, int ejes, int cilindrada, double valor) {
        this.placa = placa;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.ejes = ejes;
        this.cilindrada = cilindrada;
        this.valor = valor;
    }

    // Getters y toString() para mostrar información
    public String getPlaca() { return placa; }
    public String getTipo() { return tipo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAño() { return año; }
    public int getEjes() { return ejes; }
    public int getCilindrada() { return cilindrada; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", ejes=" + ejes +
                ", cilindrada=" + cilindrada +
                ", valor=" + valor +
                '}';
    }
}
