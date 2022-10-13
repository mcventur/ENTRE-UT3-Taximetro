
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author Nidae Asrih
 */
public class Taximetro
{
    //Constantes y atributos
    private final double BASE_NORMAL = 3.80; // De lunes a viernes 
    private final double BASE_AMPLIADA = 4.00; // Finde
    
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    // ATRIBUTOS
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100kms;
    //recuento de carreras:
    private int totalCarrerasLaborales;
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo;
    // recuento distancia en laborables/findes:
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    //totales de tiempo y estadisticas:
    private int tiempo;
    private double importeFacturado;
    private double maxFacturaNormal;
    private double maxFacturaAmpliada;
    
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String matri)    {
        matricula = matri;
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100kms = 0;
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
        consumoMedio100kms = (pesoVehiculo * coeficienteAerodinamico) / 100;

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
        switch(dia){
            case 1: 
            case 2:
            case 3:
            case 4:
            case 5:
                totalDistanciaLaborales += kilometros;
                importeFacturado += (KM_NORMAL * totalDistanciaLaborales ) + BASE_NORMAL;
                break;
            case 6:
            case 7:
                totalDistanciaFinde += kilometros;
                importeFacturado += (KM_AMPLIADA * totalDistanciaFinde ) + BASE_AMPLIADA;
                break;
        }
        
        if(dia >= 1 && dia <= 5){
            totalCarrerasLaborales++;
        }
        if(dia == 6){
            totalCarrerasSabado++;
        }
        if(dia == 7){
            totalCarrerasDomingo++;
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
        System.out.println("Configuración del taxímetro");
        System.out.println("***************************");
        System.out.println("Peso del vehículo en Kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: " + consumoMedio100kms);
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        double litros = (((totalDistanciaLaborales + totalDistanciaFinde)/100) * consumoMedio100kms);
        
        System.out.println("Estadísticas");
        System.out.println("***************************");
        
        System.out.println("Distancia recorrida toda la semana: " + (totalDistanciaFinde+totalDistanciaLaborales));
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde);
        
        System.out.println("Nº carreras días laborales: " + totalCarrerasLaborales);
        System.out.println("Nº carreras sabados: " + totalCarrerasSabado);
        System.out.println("Nº carreras domingos: " + totalCarrerasDomingo);
        
        System.out.println("Estimación de litros consumidos: " + litros);
        System.out.println("Importe facturado: " + importeFacturado);
        
        System.out.println("Tiempo total en carreras: " + pesoVehiculo);
        System.out.println("Factura máxima tarifa normal: " + pesoVehiculo);
        System.out.println("Factura máxima tarifa ampliada: " + pesoVehiculo);

    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        int temp;
        String dia = "";
        
        if(totalCarrerasLaborales > totalCarrerasSabado){
            temp = totalCarrerasLaborales;
            dia = "LABORALES";
         } else {
             temp = totalCarrerasSabado;
             dia = "SABADO";
         }
        
        if(totalCarrerasDomingo > temp){
            temp = totalCarrerasDomingo; 
            dia = "DOMINGO";
        }
        
        return dia;
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
        consumoMedio100kms = 0;
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
