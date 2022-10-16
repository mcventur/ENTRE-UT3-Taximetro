
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @Antonio Aguilera  
 */
public class Taximetro
{
    //CONSTANTES
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    //ATRIBUTOS
    private String matricula;
    private int pesoVehiculo;
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
    public Taximetro(String queMatricula)    {
        matricula = queMatricula;
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Kms = 0;
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
        consumoMedio100Kms = (pesoKg * coefAerodinamico) / 100;
        pesoVehiculo = pesoKg;
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
        int minutosInicio = (horaInicio / 100)* 60 + (horaInicio % 100);
        int minutosFin = (horaFin / 100)* 60 + (horaFin % 100);
        tiempo += minutosFin - minutosInicio;
        double maximo;
        double importe;
        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                totalCarrerasLaborales ++;
                totalDistanciaLaborales += kilometros;
                if(horaInicio < 800){
                    importeFacturado += BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                    importe = Math.floor(importeFacturado * 100)/100;
                    maximo = BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                    if(maxFacturaAmpliada < maximo){
                        importe = BASE_AMPLIADA + (KM_AMPLIADA * kilometros);
                        maxFacturaAmpliada = importe;
                    }
                }
                else{
                    importeFacturado += BASE_NORMAL + KM_NORMAL * kilometros;
                    importe = Math.floor(importeFacturado * 100)/100;
                    maximo = BASE_NORMAL + KM_NORMAL * kilometros;
                    if(maxFacturaNormal < maximo){
                        importe = BASE_NORMAL + (KM_NORMAL * kilometros);
                        maxFacturaNormal = importe;
                    }
                }
            break;
            case SABADO:
                totalCarrerasSabado++;
                totalDistanciaFinde += kilometros;
                importeFacturado += BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                importe = Math.floor(importeFacturado * 100)/100;
                maximo = BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                    if(maxFacturaAmpliada < maximo){
                        importe = BASE_AMPLIADA + (KM_AMPLIADA * kilometros);
                        maxFacturaAmpliada = importe;
                    }
            break;
            case DOMINGO:
                totalCarrerasDomingo++;
                totalDistanciaFinde += kilometros;
                importeFacturado += BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                importe = Math.floor(importeFacturado * 100)/100;
                maximo = BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                    if(maxFacturaAmpliada < maximo){
                        importe = BASE_AMPLIADA + (KM_AMPLIADA * kilometros);
                        maxFacturaAmpliada = importe;
                    }
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
        System.out.println("Configuracion del taximtro");
        System.out.println("\n*******************************************");
        System.out.println("\nPeso del vehículo en Kg: " + pesoVehiculo);
        System.out.println("\nCoeficiente aerodinamico:" + coeficienteAerodinamico);
        System.out.println("\nConsumo medio estimado por cada 100kms: " + consumoMedio100Kms);
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        int horas = tiempo / 60;
        int minu = tiempo % 60;
        int totalDistancia = totalDistanciaLaborales + totalDistanciaFinde;
        double consumoAproximado = (totalDistancia * consumoMedio100Kms) / 100;
        System.out.println("\nEstadisticas");
        System.out.println("**************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistancia + " kms");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde + " kms");
        System.out.println("\nNº carreras dias laborales: " + totalCarrerasLaborales);
        System.out.println("Nº carreras dias sábados: " + totalCarrerasSabado);
        System.out.println("Nº carreras dias domingos: " + totalCarrerasDomingo);
        System.out.println("\nEstimacion de litros consumidos: " + consumoAproximado);
        System.out.println("importe facturado: " + importeFacturado + " €");
        System.out.println("\nTiempo total de carreras: " + horas + " horas" + " y " + minu + " minutos");
        System.out.println("Factura máxima tarifa normal: " + maxFacturaNormal + " €");
        System.out.println("Factura máxima tarifa ampliada: " + maxFacturaAmpliada + " €");
    }    

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if(totalCarrerasLaborales > totalCarrerasSabado && totalCarrerasLaborales > totalCarrerasDomingo || totalCarrerasLaborales == totalCarrerasDomingo) {
            if(totalCarrerasLaborales == totalCarrerasSabado){
                return "LABORALES  SÁBADO";
            }else if(totalCarrerasLaborales == totalCarrerasDomingo){
                return "LABORALES  DOMINGO";
            }else{
                return "LABORALES";
            }
        }else if (totalCarrerasSabado > totalCarrerasLaborales && totalCarrerasSabado>totalCarrerasDomingo || totalCarrerasSabado == totalCarrerasDomingo ){
            if(totalCarrerasSabado == totalCarrerasDomingo){
                return  "SABADO  DOMIGO";
            }            
        }
        return "DOMINGO";
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
