
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
        consumoMedio100Kms = (pesoKg * coefAerodinamico)/100;
        coeficienteAerodinamico = coefAerodinamico;
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
            tiempo += minutosInicio - minutosFin;
            double maximo;
            double minimo;
            double importe = Math.floor(importeFacturado * 100)/100;
            switch(dia){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    totalCarrerasLaborales ++;
                    totalDistanciaLaborales += kilometros;
                if(dia == SABADO && dia == DOMINGO || horaInicio < 800){
                    importeFacturado += BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                    importeFacturado = importe;
                    maximo = BASE_AMPLIADA + KM_AMPLIADA * kilometros;
                        if(maxFacturaAmpliada < maximo){
                            maxFacturaAmpliada = maximo;
                        }
                }
                else{
                    importeFacturado += BASE_NORMAL + KM_AMPLIADA * kilometros;
                    importeFacturado = importe;
                    maximo = BASE_NORMAL + KM_NORMAL * kilometros;
                        if(maxFacturaNormal < maximo){
                            maxFacturaNormal = maximo;
                        }
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
        int mins = tiempo % 60;
        int totalDistancia = totalDistanciaLaborales + totalDistanciaFinde;
        double consumo = ((totalDistanciaLaborales + totalDistanciaFinde) * consumoMedio100Kms)/100;
        System.out.println("\nEstadísticas");
        System.out.println("******************************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistancia );
        System.out.println("Distancia recorrida fin de semana:" + totalDistanciaFinde);
        System.out.println("\nNº carreras días laborables: " + totalCarrerasLaborales);
        System.out.println("Nº carreras sábados: " + totalCarrerasSabado);
        System.out.println("Nº carreras domingos: " + totalCarrerasDomingo);
        System.out.println("\nEstimación de litros consumidos: " + consumo);
        System.out.println("Importe facturado: "+ importeFacturado+" €");
        System.out.println("\nTiempo total en carreras:  "+ horas +" Horas" + mins +" minutos");
        System.out.println("Factura máxima tarifa normal: "+ BASE_NORMAL + " €");
        System.out.println("Factura máxima tarifa ampliada: "+ BASE_AMPLIADA + " €");
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        return "";
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
