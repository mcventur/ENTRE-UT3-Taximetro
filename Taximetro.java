
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Kaiet Jiemenez Aldasoro
 */
public class Taximetro
{
    //Constantes y atributos
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    //valores que representa lo que marca el taximetro cuando el cliente se sube al taxi
    
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    //precio por km de cada tarifa
    
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //numero de dia de la semana 
    
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamica;
    //que será un valor entre 0 y 1 (ambos excluidos).
    private double consumoMedio100Kms;
    //almacena el consumo medio estimado en litros de combustible por cada 100kms (permitirá hacer un cálculo de litros consumidos).
    
    
    //recuento de carreras
    private int totalCarrerasLaborales;
    //guarda el no de carreras empezadas los sábados o domingos, o cualquier día antes de las 08:00.
    private int totalCarrerasSabados;
    private int totalCarrearasDomingos;
    
    
    //para recuento de distancia en laborales/fin de semana
    private int totalDistanciaLaborales;
    //distancia recorrida de lunes a viernes,da igual la hora que sea.
    private int totalDistanciaFinde;
    
    
    //Totales de tiempo y estadísticas de facturación
    private int tiempo;
    //en minutos, tiempo total de todas las carrearas en toda la semana 
    private double importeFacturado;
    //almacena el importe total facturado por el taxímetro aplicando las tarifas correspondientes.
    private double maxFacturadoNormal;
    private double maxFacturadoAmpliada;
    
    
    
    
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String queMatricula)    {
        pesoVehiculo = 0;
        coeficienteAerodinamica = 0;
        consumoMedio100Kms = 0;
        matricula = queMatricula;
        
        totalCarrerasLaborales = 0;
        totalCarrerasSabados = 0;
        totalCarrearasDomingos = 0;
        
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        
        tiempo = 0;
        importeFacturado = 0;
        maxFacturadoNormal = 0;
        maxFacturadoAmpliada = 0;
        
        
        
        

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
        coeficienteAerodinamica = coefAerodinamico;
        
        consumoMedio100Kms = (pesoVehiculo * coeficienteAerodinamica) / 100;

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
        if(dia >= 1 && dia <= 5){
            totalDistanciaLaborales += kilometros;
            
            totalCarrerasLaborales ++;
            
        
            }
        else if(dia == SABADO ){
            totalDistanciaFinde += kilometros;
                         
            totalCarrerasSabados ++;
            
            
        }
        else if(dia == DOMINGO){ 
            totalDistanciaFinde += kilometros;
            
            totalCarrearasDomingos ++;
            
            }
        
        tiempo = tiempo + (((horaFin / 100) * 60) + (horaFin % 100)) - (((horaInicio / 100) * 60) + (horaInicio % 100));
        
        
        String nombreDia; 
        nombreDia = "";
    
        switch (dia){
                case 1:             
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                     if(horaInicio > 800){   
                         nombreDia = "Laboral";
                        importeFacturado = importeFacturado + BASE_NORMAL + (kilometros * KM_NORMAL);
                        
                        importeFacturado = importeFacturado * 100;
                        Math.floor(importeFacturado);
                        importeFacturado = importeFacturado / 100;
                        
                            if(maxFacturadoNormal <  BASE_NORMAL + (kilometros * KM_NORMAL)){
                                
                                maxFacturadoNormal = (Math.floor((BASE_NORMAL+(KM_NORMAL*kilometros))*100))/100; 
                                //maxFacturadoNormal = (BASE_NORMAL+(KM_NORMAL*kilometros));
                               //maxFacturadoNormal = maxFacturadoNormal * 100;
                               //maxFacturadoNormal = Math.floor(maxFacturadoNormal);
                               //maxFacturadoNormal = maxFacturadoNormal / 100;
                            }
                        break;
                        
                    }  
                    
                    else if(horaInicio < 800){
                        
                        importeFacturado = importeFacturado + BASE_AMPLIADA + (kilometros * KM_AMPLIADA);
                        
                        importeFacturado = importeFacturado * 100; 
                        Math.floor(importeFacturado);
                        importeFacturado = importeFacturado / 100;
                            if(maxFacturadoAmpliada <  BASE_AMPLIADA + (kilometros * KM_AMPLIADA)){
                                
                                maxFacturadoAmpliada = (Math.floor((BASE_AMPLIADA+(KM_AMPLIADA*kilometros))*100))/100; 
                                //maxFacturadoAmpliada = (BASE_AMPLIADA+(KM_AMPLIADA*kilometros));
                               //maxFacturadoAmpliada = maxFacturadoAmpliada * 100;
                               //maxFacturadoAmpliada = Math.floor(maxFacturadoAmpliada);
                               //maxFacturadoAmpliada = maxFacturadoAmpliada / 100;
                        
                                }
                        
                        break;
                    
                        }                    
                case 6:
                case 7:
                    importeFacturado = importeFacturado + BASE_AMPLIADA + kilometros * KM_AMPLIADA;
                        
                    importeFacturado = importeFacturado * 100; 
                    Math.floor(importeFacturado);
                    importeFacturado = importeFacturado / 100;
                    if(maxFacturadoAmpliada <  BASE_AMPLIADA + (kilometros * KM_AMPLIADA)){
                                
                           maxFacturadoAmpliada = (Math.floor((BASE_AMPLIADA+(KM_AMPLIADA*kilometros))*100))/100; 
                           //maxFacturadoAmpliada = (BASE_AMPLIADA+(KM_AMPLIADA*kilometros));
                           //maxFacturadoAmpliada = maxFacturadoAmpliada * 100;
                           //maxFacturadoAmpliada = Math.floor(maxFacturadoAmpliada);
                           //maxFacturadoAmpliada = maxFacturadoAmpliada / 100;
                           
                           
                        
                        
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

        System.out.println(" Configuracion del taximetro ");
        System.out.println("***************************");
        
        System.out.println(" Peso del vehiculo en Kg: " + pesoVehiculo);
        System.out.println(" Coeficiente aerodinámico: " + coeficienteAerodinamica);
        System.out.println(" Consumo medio estimado por cada 100kms: " + consumoMedio100Kms);
         
        
        System.out.println();
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
        System.out.println(" Estadisticas ");
        System.out.println("***************************");
       
        System.out.println(" Distancia recorrida toda la semana: " + (totalDistanciaLaborales + totalDistanciaFinde) +"Kms");
        System.out.println(" Distancia recorrida fin de semana: " + totalDistanciaFinde+ "Kms");
        System.out.println();
        System.out.println();
        System.out.println( "Nº carreras días laborables: " + totalCarrerasLaborales);
        System.out.println( "Nº carreras Sabados: " + totalCarrerasSabados);
        System.out.println( "Nº carreras domingos: " + totalCarrearasDomingos);
        System.out.println();
        System.out.println();
        System.out.println( "Estimación de litos consumidos: " + (totalDistanciaLaborales + totalDistanciaFinde) * (consumoMedio100Kms / 100 )) ;
        System.out.println( "Importe facturado: " + importeFacturado + "€");
         System.out.println();
        System.out.println();
        System.out.println( "Tiempo total en carreras: " + tiempo/60 + " horas " + " y " + tiempo%60 + " minutos " );
        System.out.println( "Factura maxima tarifa normal: " + maxFacturadoNormal + "€");
        System.out.println( "Factura maxima tarifa ampliada " + maxFacturadoAmpliada + "€");

        

        }    
    
    String diaMayorNumeroCarreras(){
        String laborales;
            laborales = "LABORALES";
        String Sabado;
            Sabado = "SABADO";
        String Domingo;
            Domingo = "DOMINGO";
        
        
        if(totalCarrerasLaborales > totalCarrerasSabados && totalCarrerasLaborales > totalCarrearasDomingos){
            return laborales;
            }
        else if(totalCarrearasDomingos == totalCarrerasLaborales && totalCarrerasSabados==totalCarrerasLaborales){
            return Sabado + "  " + laborales + "  " + Domingo;
            }
     
        else if(totalCarrerasSabados > totalCarrearasDomingos && totalCarrerasSabados > totalCarrerasLaborales ){     
            return Sabado;
        }
        else if(totalCarrerasSabados == totalCarrearasDomingos){
                return Sabado + "  " + Domingo;
                
            }
            
        else if(totalCarrearasDomingos > totalCarrerasSabados && totalCarrearasDomingos > totalCarrerasLaborales){
            return Domingo;
        }
        else if(totalCarrearasDomingos == totalCarrerasLaborales){
                return laborales + "  " + Domingo;
            }
        else if(totalCarrerasSabados == totalCarrerasLaborales){
                return laborales + "  " + Sabado;
            
            }
        return diaMayorNumeroCarreras();
        }

    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
     *  
     */    
    public void reset() {
        pesoVehiculo = 0;
        coeficienteAerodinamica = 0;
        consumoMedio100Kms = 0;
        
        
        totalCarrerasLaborales = 0;
        totalCarrerasSabados = 0;
        totalCarrearasDomingos = 0;
        
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        
        tiempo = 0;
        importeFacturado = 0;
        maxFacturadoNormal = 0;
        maxFacturadoAmpliada = 0;

        

    }    

}
