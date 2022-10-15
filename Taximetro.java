
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Ivan Barguilla Pascual
 */
public class Taximetro
{
    private final double BASE_NORMAL= 3.80;
    private final double BASE_AMPLIADA= 4.00;
    private final double KM_NORMAL= 0.75;
    private final double KM_AMPLIADA= 1.10;
    private final int SABADO= 6;
    private final int DOMINGO= 7;
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
    public Taximetro(String queMatricula)    {
        matricula = queMatricula;
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
        consumoMedio100Kms = (pesoVehiculo * coeficienteAerodinamico)/100;
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
        switch (dia) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalCarrerasLaborales++;
                    totalDistanciaLaborales = totalDistanciaLaborales + kilometros;
                    if (horaInicio>800){
                     importeFacturado = (Math.floor(((importeFacturado + BASE_NORMAL)+(KM_NORMAL*kilometros))*100))/100;
                       if(maxFacturaNormal < BASE_NORMAL+(KM_NORMAL*kilometros)){
                        maxFacturaNormal = (Math.floor((BASE_NORMAL+(KM_NORMAL*kilometros))*100))/100;
                        } 
                    } 
                    else {importeFacturado = (Math.floor(((importeFacturado + BASE_AMPLIADA)+(KM_AMPLIADA*kilometros))*100))/100;
                          if (maxFacturaAmpliada < BASE_AMPLIADA+(KM_AMPLIADA*kilometros)){
                            maxFacturaAmpliada = (Math.floor((BASE_AMPLIADA+(KM_AMPLIADA*kilometros))*100))/100;
                            }
                    }
                break;
            case 6: totalCarrerasSabado++;
                    totalDistanciaFinde = totalDistanciaFinde + kilometros;
                    importeFacturado = (Math.floor(((importeFacturado + BASE_AMPLIADA)+(KM_AMPLIADA*kilometros))*100))/100;
                    if (maxFacturaAmpliada < BASE_AMPLIADA+(KM_AMPLIADA*kilometros)){
                        maxFacturaAmpliada = (Math.floor((BASE_AMPLIADA+(KM_AMPLIADA*kilometros))*100))/100;
                        }
                break;
            case 7: totalCarrerasDomingo++;
                    totalDistanciaFinde = totalDistanciaFinde + kilometros;
                    importeFacturado = (Math.floor(((importeFacturado + BASE_AMPLIADA)+(KM_AMPLIADA*kilometros))*100))/100;
                    if (maxFacturaAmpliada < BASE_AMPLIADA+(KM_AMPLIADA*kilometros)){
                        maxFacturaAmpliada = (Math.floor((BASE_AMPLIADA+(KM_AMPLIADA*kilometros))*100))/100;
                        }
                break;
        }

            
        tiempo = tiempo + ((horaFin/100*60+horaFin%100) - (horaInicio/100*60+horaInicio%100));
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuracion del taximetro" +
        "\n"+"**********************************************" +
        "\n"+"Peso del vehiculo en Kg: "+pesoVehiculo+ "."+
        "\n"+"Coeficiente aerodinámico: "+coeficienteAerodinamico+ "."+
        "\n"+"Consumo medio estimado por cada 100Kms: "+consumoMedio100Kms+ ".");
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadisticas" +
        "\n"+"**********************************************" +
        "\n"+"Distancia recorrida toda la semana: "+ (totalDistanciaLaborales + totalDistanciaFinde) + "."+
        "\n"+"Distancia recorrida fin de semana: "+totalDistanciaFinde+ "."+
        "\n" +
        "\n" +"Nº carreras días laborables: " + totalCarrerasLaborales +"."+
        "\n" +"Nº carreras sábados: " + totalCarrerasSabado +"." +
        "\n" +"Nº carreras domingos: " + totalCarrerasDomingo +"."+
        "\n" +
        "\n" +"Estimación de litros consumidos: " + (consumoMedio100Kms/100)*(totalDistanciaLaborales+totalDistanciaFinde)+"." +
        "\n" +"Importe facturado: " +importeFacturado +"." +
        "\n" +
        "\n" +"Tiempo total en carreras: " + tiempo/60 +" horas y "+ tiempo%60+ " minutos."+
        "\n" +"Factura máxima tarifa normal: " + maxFacturaNormal +"."+
        "\n" +"Factura máxima tarifa ampliada: " + maxFacturaAmpliada +"." );
        

    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        String queDia = "";
        
        if(totalCarrerasSabado > totalCarrerasLaborales && totalCarrerasSabado > totalCarrerasDomingo){
        queDia = "Sabado";
        }
     
        if(totalCarrerasDomingo > totalCarrerasLaborales && totalCarrerasDomingo > totalCarrerasSabado){
        queDia ="Domingo";
        }
       
        if(totalCarrerasLaborales > totalCarrerasSabado && totalCarrerasLaborales > totalCarrerasDomingo){
        queDia =  "Laborales";
        }
        
        if(totalCarrerasSabado == totalCarrerasLaborales){
        queDia += "Laborabes";
        }
        if(totalCarrerasSabado == totalCarrerasDomingo){
        queDia += "Domingo";
        }
        if(totalCarrerasLaborales == totalCarrerasDomingo){
        queDia += "Domingo";
        }
        
                
        return queDia;
        
    }    
    
    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
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
