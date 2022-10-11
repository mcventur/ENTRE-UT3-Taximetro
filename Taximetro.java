
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
    private String diaMaxCarrera;
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
        tiempo = minutosInicio - minutosFin;
        switch(dia){
            
            case 1:
            case 2:
            case 3: 
            case 4: 
            case 5:
                totalCarrerasLaborales ++;
                totalDistanciaLaborales += kilometros;
            if(horaInicio<800){
                importeFacturado = BASE_AMPLIADA+ KM_AMPLIADA*kilometros;
                testMax(importeFacturado,1);
                testMin(importeFacturado,1);
            }
            else{
                importeFacturado = BASE_NORMAL + KM_NORMAL* (double)kilometros;
                testMax(importeFacturado,2);
                testMin(importeFacturado,2);
            }
                break;
            case 6:
                
                totalDistanciaFinde = kilometros;
                totalDistanciaLaborales += kilometros;
                totalCarrerasSabado ++;
                importeFacturado = BASE_AMPLIADA + KM_AMPLIADA* (double)kilometros;
                testMax(importeFacturado,1);
                testMin(importeFacturado,1);
                
                break;
            case 7:
                totalDistanciaFinde = kilometros;
                totalDistanciaLaborales += kilometros;
                totalCarrerasDomingo ++;
                importeFacturado = BASE_AMPLIADA + KM_AMPLIADA*kilometros;
                Math.floor(importeFacturado); //preguntar
                testMax(importeFacturado,1);
                testMin(importeFacturado,1);
                
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
    
    public String testMin(double posibleMinimo,int indice)
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
        System.out.println("\nEstadísticas");
        System.out.println("********************************");
        System.out.println("Distancia recorrida toda la semana: " +totalDistanciaLaborales );
        System.out.println("Distancia recorrida fin de semana:");
        System.out.println("\nNº carreras días laborables: 5");
        System.out.println("Nº carreras sábados: 2");
        System.out.println("Nº carreras domingos: 3");
        System.out.println("\nEstimación de litros consumidos: 6.208");
        System.out.println("Importe facturado: 129.1 €");
        System.out.println("\nTiempo total en carreras: 8 horas y 53 minutos");
        System.out.println("Factura máxima tarifa normal: 14.3 €");
        System.out.println("Factura máxima tarifa ampliada: 18.3 €");
    }    

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        return "String algo" ;

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
