import java.util.Scanner;
/**
 *  
 * Clase para testear Taximetro - NO MODIFICAR
 */
public class DemoTaximetro
{

    private Taximetro taximetro;
    private Scanner teclado;

    /**
     * Constructor de la clase DemoPodometro
     */
    public DemoTaximetro() {

        teclado = new Scanner(System.in);
        //taximetro = new Taximetro("2976 CLK");

    }

    /**
     *  Configurar el podómetro y registrar todas las caminatas
     *  
     */
    public void iniciar() {
        taximetro = new Taximetro("2976 CLK");
        
        //test1
        printMatricula();
        configurar(0.4, 1600);
        int[] distancias = {9, 12, 6, 14, 13, 8, 10, 4, 8, 13 };
        int[] dias = {1, 2, 4, 5, 5, 6, 6, 7, 7, 7 };
        int[] horasInicio = {1850, 2210, 300, 1730, 2217, 2025, 2230, 615, 1345, 1835};
        int[] horasFin = {1950, 2345, 335, 1845, 2310, 2100, 2310, 650, 1405, 2000};
        registrarCarreras(distancias, dias, horasInicio, horasFin);
        mostrarEstadisticas();

        pausa();

        taximetro.reset();
        
        //test2
        printMatricula();
        configurar(0.6, 1850);
        distancias = new int[]{11, 15, 2, 17, 9, 8, 6, 16, 10, 14 };
        dias = new int[]{1, 2, 7, 5, 5, 6, 6, 7, 7, 7 };
        horasInicio = new int[]{1450, 2210, 1600, 730, 2117, 2045, 2210, 15, 1145, 635};
        horasFin = new int[]{1525, 2320, 1610, 910, 2150, 2110, 2310, 150, 1225, 900};
        registrarCarreras(distancias, dias, horasInicio, horasFin);
        mostrarEstadisticas();

        pausa();

        taximetro.reset();    
        
        //test3
        printMatricula();
        configurar(0.3, 1250);
        distancias = new int[]{30, 19, 21, 4, 45, 6, 6, 25, 18, 4 };
        dias = new int[]{6, 2, 7, 5, 6, 6, 6, 7, 7, 7 };
        horasInicio = new int[]{450, 2200, 1600, 1730, 2117, 1925, 2210, 115, 1125, 635};
        horasFin = new int[]{635, 2255, 1710, 1745, 2350, 2000, 2250, 250, 1230, 655};
        registrarCarreras(distancias, dias, horasInicio, horasFin);
        mostrarEstadisticas();

        pausa();

        taximetro.reset();              
        
        //test4
        printMatricula();
        configurar(0.8, 2150);
        distancias = new int[]{30, 19, 21, 44, 45 };
        dias = new int[]{6, 7, 7, 7, 6 };
        horasInicio = new int[]{250, 2000, 1600, 1230, 2117};
        horasFin = new int[]{325, 2105, 1720, 1410, 2350};
        registrarCarreras(distancias, dias, horasInicio, horasFin);
        mostrarEstadisticas();  
        pausa();

    }

    private void printMatricula() {
        System.out.println("********************************************");
        System.out.println("*******    Taximetro " + taximetro.getMatricula().toUpperCase()
            + "       *******");
        System.out.println("********************************************\n");
    }

    private void configurar(double coeficiente, int peso) {
        taximetro.configurar(coeficiente, peso);
        taximetro.printConfiguracion();

    }

    /**
     * registrar los recorridos realizados en el podómetro
     */
    private void registrarCarreras(int[] distancias, int[] dias, int[] horasInicio,
    int[] horasFin) {

        for (int i = 0; i < distancias.length; i++) {
            taximetro.registrarCarrera(distancias[i], dias[i], horasInicio[i], horasFin[i]);
        }

    }

    private void mostrarEstadisticas() {

        taximetro.printEstadísticas();
        System.out.print("\nDía/s con más carreras: " + taximetro.diaMayorNumeroCarreras());

    }

    /**
     *  hacer una pausa
     */
    private void pausa() {

        System.out.println("\n\nPulse <Intro> para continuar");
        teclado.nextLine();
        System.out.println("\u000C");
    }
}
