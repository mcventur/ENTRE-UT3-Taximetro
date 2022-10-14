
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  
 */
public class Taximetro
{
    //Constantes para indicar precio base.
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    //Constante para representar lo que indica el taxímetro.
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;    
    //Constatnes para indicar número del día de la semana.
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //Atributos.
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    //Para recuento de carreras.
    private int totalCarrerasLaborales;
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo;
    //Para recuento de distancia.
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    //Para totales de tiempo y estadísticas de facturación.
    private int tiempo;
    private double importeFacturado;
    private double maxFacturaNormal;
    private double maxFacturaAmpliada;
    
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String newMatricula)    {
        matricula = newMatricula;
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
        coeficienteAerodinamico = coefAerodinamico;
        pesoVehiculo = pesoKg;
        consumoMedio100Kms = (pesoVehiculo*coeficienteAerodinamico)/100.00;
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
        int horasAminsInicio;
        int horasAminsFin;
        double importeNormal;
        double importeAmpliado;
        importeNormal = Math.floor((BASE_NORMAL + kilometros * KM_NORMAL)*100)/100;
        importeAmpliado = Math.floor((BASE_AMPLIADA + kilometros * KM_AMPLIADA)*100)/100;
        
        horasAminsInicio = (horaInicio / 100) * 60 + horaInicio%100;
        horasAminsFin = (horaFin / 100) * 60 + horaFin%100;
        switch (dia) {
            case 1: case 2: case 3: case 4: case 5:
                totalDistanciaLaborales += kilometros;
                totalCarrerasLaborales++;
                if (horaInicio<800) {
                    importeFacturado+=importeAmpliado;
                    if (importeAmpliado>maxFacturaAmpliada) {
                    maxFacturaAmpliada = importeAmpliado;
                    }
                }
                else {
                    importeFacturado+=importeNormal;
                    if (importeNormal>maxFacturaNormal) {
                    maxFacturaNormal = importeNormal;
                    }
                }
                break;
            case 6:
                totalDistanciaFinde += kilometros;
                totalCarrerasSabado++;
                importeFacturado+=importeAmpliado;
                if (importeAmpliado>maxFacturaAmpliada) {
                    maxFacturaAmpliada = importeAmpliado;
                }
                break;
            case 7:
                totalDistanciaFinde += kilometros;
                totalCarrerasDomingo++;
                importeFacturado+=importeAmpliado;
                if (importeAmpliado>maxFacturaAmpliada) {
                    maxFacturaAmpliada = importeAmpliado;
                }
                break;
        }
        tiempo += horasAminsFin - horasAminsInicio;
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del taxímetro");
        System.out.println("***************************");
        System.out.println("Peso vehículo en kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: " + consumoMedio100Kms);
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + (totalDistanciaLaborales + totalDistanciaFinde) + "kms");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde + "kms");
        System.out.println("Nº carreras días laborales: " + totalCarrerasLaborales);
        System.out.println("Nº carreras sabados: " + totalCarrerasSabado);
        System.out.println("Nº carreras domingos: " + totalCarrerasDomingo);
        System.out.println("");
        System.out.println("Estimación de litros consumidos: " + (((totalDistanciaLaborales + totalDistanciaFinde)/100.00))*consumoMedio100Kms);
        System.out.println("Importe facturado: " + importeFacturado);
        System.out.println("");
        System.out.println("Tiempo total en carreras: " + tiempo/60 + " horas y " + tiempo%60 + " minutos");
        System.out.println("Factura máxima tarifa normal: " + maxFacturaNormal);
        System.out.println("Factura máxima tarifa ampliada: " + maxFacturaAmpliada);
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if (totalCarrerasLaborales>totalCarrerasSabado && totalCarrerasLaborales>totalCarrerasDomingo) {
             return "LABORABLES";
        }
        else if (totalCarrerasSabado>totalCarrerasDomingo && totalCarrerasSabado>totalCarrerasLaborales) {
             return "SÁBADO";
        }
        else if (totalCarrerasSabado<totalCarrerasDomingo && totalCarrerasSabado>totalCarrerasLaborales) {
             return "DOMINGO";
        }
        else if (totalCarrerasSabado==totalCarrerasDomingo && totalCarrerasSabado !=totalCarrerasLaborales) {
            return "SÁBADO DOMINGO";
        }
        else if (totalCarrerasSabado==totalCarrerasLaborales && totalCarrerasSabado !=totalCarrerasLaborales) {
            return "SÁBADO LABORALES";
        }
        else if (totalCarrerasLaborales==totalCarrerasDomingo && totalCarrerasLaborales !=totalCarrerasSabado) {
            return "DOMINGO LABORALES";
        }
        else 
            return "IGUALES";
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
