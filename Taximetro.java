
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author Aimar Caballero
 */
public class Taximetro
{
    //Constantes y atributos
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String matricula;
    private double pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    private int totalCarrerasLaborales;
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo;
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    private int tiempo;
    private double importeFacturado;
    private double maxFacturaNormal;
    private double maxFacturaAmpliada;
    
    
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String cualMatricula)    {
        matricula = cualMatricula;
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Kms = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        tiempo = 0;
        importeFacturado = 0;
        maxFacturaNormal = 0;
        maxFacturaAmpliada = 0;
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
        pesoVehiculo = pesoKg;
        coeficienteAerodinamico = coefAerodinamico;
        consumoMedio100Kms = (pesoKg*coefAerodinamico)/100;
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
        double importeFacturaNormal = Math.floor(BASE_NORMAL + (kilometros * KM_NORMAL));
        double importeFacturaAmpliada = Math.floor(BASE_AMPLIADA + (kilometros * KM_AMPLIADA));
        importeFacturado = importeFacturaNormal + importeFacturaAmpliada;
        if (importeFacturaNormal > maxFacturaNormal) {
            maxFacturaNormal = importeFacturaNormal;
        }
        if (importeFacturaAmpliada > maxFacturaAmpliada) {
            maxFacturaAmpliada = importeFacturaAmpliada;
        }
        
        switch (dia) {
        case 1: case 2: case 3: case 4: case 5:
            totalCarrerasLaborales++;
            totalDistanciaLaborales = totalDistanciaLaborales + kilometros;
            break;
            
        case 6:
            totalCarrerasSabado++;
             totalDistanciaFinde = totalDistanciaFinde + kilometros;
            break;
        
        case 7:
            totalCarrerasDomingo++;
             totalDistanciaFinde = totalDistanciaFinde + kilometros;
            break;
        }
        
        int tiempoCarrera = 0;
        tiempoCarrera = horaFin - horaInicio;
        tiempo = tiempoCarrera + tiempo;
        horaInicio = (horaInicio/100)*60 + (horaInicio%100); 
        horaFin = (horaFin/100)*60 + (horaFin%100); 
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println ("         ");
        System.out.println ("Configuración del taxímetro");
        System.out.println ("---------------------------");
        System.out.println ("Matricula: " + matricula);
        System.out.println ("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println ("Peso del vehículo en Kg: " + pesoVehiculo);
        System.out.println ("Consumo medio estimado por cada 100kms: " + consumoMedio100Kms);
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println ("         ");
        System.out.println ("Estadísticas");
        System.out.println ("------------");
        System.out.println ("Distancia recorrida toda la semana: " + totalDistanciaLaborales + "Kms");
        System.out.println ("Distancia recorrida fin de semana: " + totalDistanciaFinde + "Kms");
        System.out.println ("         ");
        System.out.println ("Nº carreras dias laborables: " + totalCarrerasLaborales);
        System.out.println ("Nº carreras sábados: " + totalCarrerasSabado);
        System.out.println ("Nº carreras domingos: " + totalCarrerasDomingo);
        System.out.println ("         ");
        System.out.println ("Estimación de litros consumidos: " + consumoMedio100Kms);
        System.out.println ("Importe facturado: " + importeFacturado + "€");
        System.out.println ("         ");
        System.out.println ("Tiempo total en carreras: " + tiempo + " minutos");
        System.out.println ("Factura máxima tarifa normal: " + maxFacturaNormal + "€");
        System.out.println ("Factura máxima tarifa ampliada: " + maxFacturaAmpliada + "€");
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if (totalCarrerasSabado > totalCarrerasDomingo && totalCarrerasSabado > totalCarrerasLaborales) {
            return "SÁBADO";
        }
        else if (totalCarrerasDomingo > totalCarrerasSabado && totalCarrerasDomingo > totalCarrerasLaborales) {
            return "DOMINGO";
        }
        else if (totalCarrerasLaborales > totalCarrerasSabado && totalCarrerasLaborales > totalCarrerasDomingo) {
            return "LABORALES";
        }
        else if (totalCarrerasSabado == totalCarrerasDomingo) {
            return "SABADO Y DOMINGO"; 
        }
        else if (totalCarrerasSabado == totalCarrerasLaborales) {
            return "SABADO Y LABORALES";
        }
        else {
            return "LABORALES Y DOMINGO";
        }
    }
      
    
    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
     *  
     */    
    public void reset() {
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Kms = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        tiempo = 0;
        importeFacturado = 0;
        maxFacturaNormal = 0;
        maxFacturaAmpliada = 0;
    }    

}
