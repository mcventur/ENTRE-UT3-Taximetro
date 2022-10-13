
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @aitor
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
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    private int totalCarrerasLaborales;
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo;
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    private int tiempo; // minutos
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
        consumoMedio100Kms = (pesoKg*coefAerodinamico)/100;
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
        tiempo +=  minutosFin-minutosInicio;

        switch(dia){
            
            case 1:
            case 2:
            case 3: 
            case 4: 
            case 5:
                totalCarrerasLaborales ++;
                totalDistanciaLaborales += kilometros;
            if(horaInicio<800){
                importeFacturado += BASE_AMPLIADA+ KM_AMPLIADA*kilometros;
                testMax((BASE_AMPLIADA + KM_AMPLIADA*kilometros),1);
                testMin((BASE_AMPLIADA + KM_AMPLIADA*kilometros),1);
            }
            else{
                importeFacturado += BASE_NORMAL + KM_NORMAL* (double)kilometros;
                testMax((BASE_NORMAL + KM_NORMAL * (double)kilometros),2);
                testMin((BASE_NORMAL + KM_NORMAL * (double)kilometros),2);
            }
                break;
            case 6:
                
                totalDistanciaFinde += kilometros;
                totalCarrerasSabado ++;
                importeFacturado += BASE_AMPLIADA + KM_AMPLIADA* (double)kilometros;
                testMax((BASE_AMPLIADA + KM_AMPLIADA*kilometros),1);
                testMin((BASE_AMPLIADA + KM_AMPLIADA*kilometros),1);
                
                break;
            case 7:
                totalDistanciaFinde += kilometros;
                totalCarrerasDomingo ++;
                importeFacturado += BASE_AMPLIADA + KM_AMPLIADA*kilometros;
                
                Math.floor(importeFacturado); //preguntar
                testMax((BASE_AMPLIADA + KM_AMPLIADA*kilometros),1);
                testMin((BASE_AMPLIADA + KM_AMPLIADA*kilometros),1);
                
                break;
            default:
                System.out.println("dia incorrecto");
                break;
        }
        //totalCarrerasLaborales = 0;
        //totalCarrerasSabado = 0;
        //totalCarrerasDomingo = 0;
        //totalDistanciaLaborales = 0;
        //totalDistanciaFinde = 0;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void testMax(double posibleMaximo,int indice)
    {
        
        switch(indice){
            case 1:
                if(maxFacturaAmpliada<posibleMaximo){
                    maxFacturaAmpliada=posibleMaximo;
                    
                }
                
            break;
            case 2:
                if(maxFacturaNormal<posibleMaximo){
                    maxFacturaNormal=posibleMaximo;                    
                }
                
            break;
        
        }
        
        /**if(maxFacturaNormal<maxFacturaAmpliada){
            diaMaxCarrera = dia;
        }else if(maxFacturaAmpliada<maxFacturaNormal){
            diaMaxCarrera = dia;
        }
        */
    }
    
    public void testMin(double posibleMinimo,int indice)
    {
        switch(indice){
            case 1:
                if(maxFacturaAmpliada<posibleMinimo){
                    maxFacturaAmpliada=posibleMinimo;                    
                }
            break;
            case 2:
                if(maxFacturaNormal<posibleMinimo){
                    maxFacturaNormal=posibleMinimo;                    
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
        System.out.println("\n Configuracion del taximtro");
        System.out.println("*******************************************");
        System.out.println("Peso del vehículo en Kg: "+pesoVehiculo);
        System.out.println("Coeficiente aerodinamico:"+coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: "+ consumoMedio100Kms);
        System.out.println();
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        int tiempoHoras= tiempo/60;
        int timepoMins=tiempo%60;
        int totalDist=totalDistanciaLaborales+totalDistanciaFinde;
        System.out.println("\nEstadísticas");
        System.out.println("******************************************");
        System.out.println("Distancia recorrida toda la semana: " +totalDist );
        System.out.println("Distancia recorrida fin de semana:"+totalDistanciaFinde);
        System.out.println("\nNº carreras días laborables: "+totalCarrerasLaborales);
        System.out.println("Nº carreras sábados: "+totalCarrerasSabado);
        System.out.println("Nº carreras domingos: "+totalCarrerasDomingo);
        System.out.println("\nEstimación de litros consumidos: "+consumoMedio100Kms);
        System.out.println("Importe facturado: "+ importeFacturado+" €");
        System.out.println("\nTiempo total en carreras:  "+tiempoHoras+"h"+timepoMins+"mins");
        System.out.println("Factura máxima tarifa normal: "+ BASE_NORMAL+" €");
        System.out.println("Factura máxima tarifa ampliada: "+ BASE_AMPLIADA+" €");
    }    

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if(totalCarrerasLaborales>totalCarrerasSabado && totalCarrerasLaborales>totalCarrerasDomingo) {
            if(totalCarrerasLaborales==totalCarrerasSabado){
                return "LABORALES y SÁBADO";
            }else if(totalCarrerasLaborales==totalCarrerasDomingo){
                return "LABORALES y DOMINGO";
            }else{
                return "LABORALES";
            }
        }else if (totalCarrerasSabado>totalCarrerasLaborales && totalCarrerasSabado>totalCarrerasDomingo ){
            if(totalCarrerasSabado==totalCarrerasDomingo){
                return  "SABADO y DOMIGO";
            }else{
                return  "SABADO";
            }            
        }else {
            return  "DOMINGO";
        }
    }    
    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
     *  
     */    
    public void reset() {
        matricula = "";
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
