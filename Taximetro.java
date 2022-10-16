
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Imanol Perez
 */
public class Taximetro
{
    //Constantes para indicar el precio base de cada tarifa
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    //Constantes para indicar el precio por kilometro en cada tarifa
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADO = 1.10;
    //Constantes para indicar el nº de día de la semana
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //Atributos
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    //Para recuento de carreras:
    private int totalCarrerasLaborales;
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo;
    //Para recuento de distancia en laborales/fin de semana
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    //Totales de tiempo y estadísticas de facturación:
    private int tiempo;
    private double importeFacturado;
    private double maxFacturaNormal;
    private double maxFacturaAmpliada;   

    
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String queMatricula)    
    {
        matricula = queMatricula;
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Kms = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
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
    consumoMedio100Kms = (pesoKg * coefAerodinamico) / 100;
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
        double importeCarrera;
        int tiempoCarrera;
        int minutosInicio;
        int minutosFinal;
        double distanciaTotal;
        switch (dia) {
        case 1: case 2: case 3: case 4: case 5:
            totalCarrerasLaborales ++;
            totalDistanciaLaborales += kilometros;
            importeCarrera = BASE_NORMAL + (kilometros * KM_NORMAL);
            importeCarrera = (Math.floor(importeCarrera * 100) / 100);
            //importeFacturado = (totalDistanciaLaborales * KM_NORMAL) + (totalDistanciaFinde * KM_AMPLIADO);
            importeFacturado += importeCarrera;
            minutosInicio = (horaInicio / 100) * 60 + (horaInicio % 100);
            minutosFinal = (horaFin / 100) * 60 + (horaFin % 100);
            tiempoCarrera = minutosFinal - minutosInicio;
            tiempo += tiempoCarrera;
            if (importeCarrera > maxFacturaNormal){
                maxFacturaNormal = importeCarrera;
            }
            break;
            
        case 6:
            totalCarrerasSabado ++;
            totalDistanciaFinde = totalDistanciaFinde + kilometros;
            importeCarrera = BASE_AMPLIADA + (kilometros * KM_AMPLIADO);
            importeCarrera = (Math.floor(importeCarrera * 100) / 100);
            //importeFacturado = (totalDistanciaLaborales * KM_NORMAL) + (totalDistanciaFinde * KM_AMPLIADO);
            importeFacturado += importeCarrera;
            minutosInicio = (horaInicio / 100) * 60 + (horaInicio % 100);
            minutosFinal = (horaFin / 100) * 60 + (horaFin % 100);
            tiempoCarrera = minutosFinal - minutosInicio;
            tiempo += tiempoCarrera;
            if (importeCarrera > maxFacturaAmpliada){
                maxFacturaAmpliada = importeCarrera;
            }
            break;
            
        case 7:
            totalCarrerasDomingo++;
            totalDistanciaFinde = totalDistanciaFinde + kilometros;
            importeCarrera = BASE_AMPLIADA + (kilometros * KM_AMPLIADO);
            importeCarrera = (Math.floor(importeCarrera * 100) / 100);
            //importeFacturado = (totalDistanciaLaborales * KM_NORMAL) + (totalDistanciaFinde * KM_AMPLIADO);
            importeFacturado += importeCarrera;
            minutosInicio = (horaInicio / 100) * 60 + (horaInicio % 100);
            minutosFinal = (horaFin / 100) * 60 + (horaFin % 100);
            tiempoCarrera = minutosFinal - minutosInicio;
            tiempo += tiempoCarrera;

            if (importeCarrera > maxFacturaAmpliada){
                maxFacturaAmpliada = importeCarrera;
            }
            break;
        
        }         
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    
    public void printConfiguracion() {
    System.out.println ("Configuración del taxímetro");
    System.out.println ("*****************************");
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
    System.out.println (""); 
    System.out.println ("Estadísticas");
    System.out.println ("*****************************");
    System.out.println ("Distancia recorrida toda la semana: " + (totalDistanciaLaborales + totalDistanciaFinde) + "Kms");
    System.out.println ("Distancia recorrida fin de semana: " + totalDistanciaFinde + "Kms");
    System.out.println (""); 
    System.out.println ("Nº carreras dias laborables: " + totalCarrerasLaborales);
    System.out.println ("Nº carreras sábados: " + totalCarrerasSabado); 
    System.out.println ("Nº carreras domingos: " + totalCarrerasDomingo);
    System.out.println ("");
    System.out.println ("Estimación de litros consumidos: " + (consumoMedio100Kms / 100) * (totalDistanciaLaborales + totalDistanciaFinde)); 
    System.out.println ("Importe facturado: " + importeFacturado + "€");
    System.out.println ("");
    System.out.println ("Tiempo total en carreras: " + tiempo);
    System.out.println ("Factura máxima tarifa normal: " + maxFacturaNormal + "€");
    System.out.println ("Factura máxima tarifa ampliada: " + maxFacturaAmpliada + "€");
    
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if (totalCarrerasLaborales > totalCarrerasSabado & totalCarrerasLaborales > totalCarrerasDomingo) {
            return "LABORALES";
        }
        else if (totalCarrerasSabado > totalCarrerasLaborales & totalCarrerasSabado > totalCarrerasDomingo) {
            return  "SÁBADO";
        }
        else {
            return "DOMINGO";
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
    }    

}
