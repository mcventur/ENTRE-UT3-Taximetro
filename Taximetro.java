
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
        if(coefAerodinamico > 0 && coefAerodinamico <1){
           coeficienteAerodinamico = coefAerodinamico; 
        }
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
        double impFact; //variable local para facilitar el calculo del importe facturado
        switch (dia) {
        case 1: 
        case 2:
        case 3: 
        case 4: 
        case 5: 
            totalDistanciaLaborales += kilometros; //Actualizamos la distancia total 
            totalCarrerasLaborales++; //Actualizamos el numero de carreras
            
            //Calculamos el importe facturado
            if(horaInicio < 800){
            impFact = (BASE_AMPLIADA + (KM_AMPLIADA * kilometros));
            importeFacturado = (Math.floor(impFact * 100)) / 100;
                if(importeFacturado > maxFacturaAmpliada){
                maxFacturaAmpliada = importeFacturado;
                }
            }
            else {
            impFact = (BASE_NORMAL + (KM_NORMAL * kilometros));
            importeFacturado = (Math.floor(impFact * 100)) / 100;
                if(importeFacturado > maxFacturaNormal){
                maxFacturaNormal = importeFacturado;
                }
            }
            break; 
        case 6:
            totalCarrerasSabado++; //Actualizamos el numero de carreras
            totalDistanciaFinde += kilometros; //Actualizamos la distancia total
            
            //Calculamos el importe facturado
            impFact = (BASE_AMPLIADA + (KM_AMPLIADA * kilometros));
            importeFacturado = (Math.floor(impFact * 100)) / 100;
            if(importeFacturado > maxFacturaAmpliada){
                maxFacturaAmpliada = importeFacturado;
            }
            break; 
        case 7: 
            totalCarrerasDomingo++; //Actualizamos el numero de carreras
            totalDistanciaFinde += kilometros; //Actualizamos la distancia total
            
            //Calculamos el importe facturado
            impFact = (BASE_AMPLIADA + (KM_AMPLIADA * kilometros));
            importeFacturado = (Math.floor(impFact * 100)) / 100;
            if(importeFacturado > maxFacturaAmpliada){
                maxFacturaAmpliada = importeFacturado;
            }
            break;
        }
        
        //Actualizamos el tiempo
        int minsInicio = (horaInicio / 100) * 60 + (horaInicio % 100); 
        int minsFinal = (horaFin / 100) * 60 + (horaFin % 100);
        tiempo += (minsFinal - minsInicio); 
    }
       
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("");
        System.out.println("Configuración del taximetro");
        System.out.println("***************************");
        System.out.println("Matricula: " + matricula); 
        System.out.println("Peso del vehiculo en Kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println("Consumo estimado por cada 100km: " + consumoMedio100Km);
    }
    
        /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        int distanciaTotal = totalDistanciaLaborales + totalDistanciaFinde;
        System.out.println("");
        System.out.println("Estadísticas");
        System.out.println("*****************");
        System.out.println("Distancia recorrida toda la semana: " + distanciaTotal);
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde);
        System.out.println("");
        System.out.println("N. carreras días laborales: " + totalCarrerasLaborales);
        System.out.println("N. carreras sábados: " + totalCarrerasSabado);
        System.out.println("N. carreras domingos: " + totalCarrerasDomingo);
        System.out.println("");
        System.out.println("Estimación de litros consumidos: " + (consumoMedio100Km * distanciaTotal));
        System.out.println("Importe facturado: " + importeFacturado);
        System.out.println("");
        System.out.println("Tiempo total en carreras: " + tiempo);
        System.out.println("Factura máxima tarifa normal: " + maxFacturaNormal);
        System.out.println("Factura máxima tarifa ampliada: " + maxFacturaAmpliada);
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        String diaMayorNumCarreras; 
        if(totalCarrerasLaborales > totalCarrerasSabado && totalCarrerasLaborales > totalCarrerasDomingo){
             return diaMayorNumCarreras = "LABORALES";
         }
        else if(totalCarrerasSabado > totalCarrerasLaborales && totalCarrerasSabado > totalCarrerasDomingo){
            return diaMayorNumCarreras = "SÁBADO"; 
        }
        else if(totalCarrerasDomingo > totalCarrerasLaborales && totalCarrerasDomingo > totalCarrerasSabado){
            return diaMayorNumCarreras = "DOMINGO";
        }
        else if(totalCarrerasDomingo == totalCarrerasLaborales && totalCarrerasDomingo > totalCarrerasSabado){
            return diaMayorNumCarreras = "LABORALES " + "DOMINGO";
        }
        else if(totalCarrerasSabado == totalCarrerasLaborales && totalCarrerasSabado > totalCarrerasDomingo){
            return diaMayorNumCarreras = "LABORALES " + "SÁBADO";
        }
        else if(totalCarrerasDomingo == totalCarrerasSabado && totalCarrerasDomingo > totalCarrerasLaborales){
            return diaMayorNumCarreras = "SÁBADO " + "DOMINGO";
        }
        else{
            return "false";
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
        consumoMedio100Km = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        importeFacturado = 0; 
        tiempo = 0; 
        maxFacturaNormal = 0; 
        maxFacturaAmpliada = 0;
    }    
}
