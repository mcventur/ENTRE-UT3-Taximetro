
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Khrystyna Polishchuk
 */
public class Taximetro
{
    //Constantes para el precio base de cada tarifa
    final double BASE_NORMAL = 3.80;
    final double BASE_AMPLIADA = 4.00;

    //Constantes para el precio por km en cada tarifa
    final double KM_NORMAL = 0.75;
    final double KM_AMPLIADA = 1.10;

    //Constantes dia de la semana
    final int SABADO = 6;
    final int DOMINGO = 7;

    //Atributos
    private String matricula; 
    private double pesoVehiculo; 
    private double coeficienteAerodinamico; 
    private double consumoMedio100Km; 

    //Atributos para el recuento de carreras
    private int totalCarrerasLaborales; 
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo; 

    //Atributos para recuento de distancia en laborales/fin de semana
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde; 

    //Atributos para totales de tiempo y estadísticas de facturación
    private double tiempo; 
    private double importeFacturado;
    private double maxFacturaNormal; 
    private double maxFacturaAmpliada;

    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String queMatricula)    {
        matricula = queMatricula;
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Km = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
    }

    /**
     * Accesor para la matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Permite configurar los parámetros de consumo del taximetro
     * (Leer enunciado)
     */
    public void configurar(double coefAerodinamico, int pesoKg) {
        coeficienteAerodinamico = coefAerodinamico;
        pesoVehiculo = pesoKg;
        consumoMedio100Km = (pesoVehiculo * coeficienteAerodinamico)/100; 
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    kilometros - el nº de kilometros de la carrera
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el taximetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCarrera(int kilometros, int dia, int horaInicio, int horaFin) {
        
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {

        

    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {

        

    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {

         

    }    
    
    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
     *  
     */    
    public void reset() {

        

    }    

}
