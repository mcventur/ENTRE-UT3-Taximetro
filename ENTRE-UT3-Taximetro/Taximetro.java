
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Juan Pablo Marin
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
    private  String matricula;
    private  int pesoVehiculo;
    private  double coeficienteAerodinamico;
    private  double consumoMedio100Kms;
    private  int totalCarrerasLaborales;
    private  int totalCarrerasSabado;
    private  int totalCarrerasDomingo;
    private  int totalDistanciaLaborales;
    private  int totalDistanciaFinde;
    private  int tiempo;
    private  double importeFacturado;
    private  double maxFacturaNormal;
    private  double maxFacturaAmpliada;
    
    
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
    public   String    getMatricula() {
        return matricula;

    }

    /**
     * Permite configurar los parámetros de consumo del taximetro
     * (Leer enunciado)
     */
    public void configurar(double coefAerodinamico, int pesoKg) {
        pesoVehiculo=pesoKg;
        coeficienteAerodinamico = coefAerodinamico;
        consumoMedio100Kms = ((pesoVehiculo*coeficienteAerodinamico)/((double)100));
        
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
      switch (dia){
          case 1: case 2: case 3: case 4: case 5: totalCarrerasLaborales++;
                  break;
          case 6: totalCarrerasSabado ++;
                  break;
          case 7: totalCarrerasDomingo++;
                  break;
      }
      
      switch (dia){
          case 1: case 2: case 3: case 4: case 5: totalDistanciaLaborales += kilometros;
                  break;
          case 6: case 7: totalDistanciaFinde += kilometros;
                  break;
      }
       int mins1= 0;
      int mins = 0;
      mins = ((horaInicio/100)*60)+(horaInicio%100);
      mins1 = ((horaFin/100)*60)+(horaFin%100);
      tiempo +=(mins1 - mins);
      
      double importeCarrera = 0;
      double importeCarreraDecimales = 0;

      if(horaInicio<800 || dia>5){
          importeCarreraDecimales = BASE_AMPLIADA +((double)kilometros*KM_AMPLIADA);
          importeCarrera = (Math.floor(importeCarreraDecimales*100))/100.0;
          importeFacturado += importeCarrera;
          
          if(maxFacturaAmpliada<=importeCarrera){
              maxFacturaAmpliada = importeCarrera;
          }
          importeCarrera = 0;
          importeCarreraDecimales = 0;
      }else if(horaInicio>=800 || dia>=5){
          importeCarreraDecimales = BASE_NORMAL +((double)kilometros*KM_NORMAL);
          importeCarrera = (Math.floor(importeCarreraDecimales*100))/100.0;
          importeFacturado += importeCarrera;
          
          if(maxFacturaNormal<=importeCarrera){
              maxFacturaNormal = importeCarrera;
          }
          importeCarrera = 0;
          importeCarreraDecimales = 0;
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
   
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Configuración del taxímetro   "+"\n"+"***************************************");
        System.out.println("Peso del vehiculo en kg:  "+pesoVehiculo);
        System.out.println("Coeficiente aerodinámico:  "+coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms:  "+consumoMedio100Kms+"\n");
        System.out.println("\n");
        System.out.println("\n");

    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        
        double hora=0;
        double minutos=0;
        hora = (tiempo/60);
        hora= Math.floor(hora);
        int hora1 = (int)hora;
        minutos= (tiempo-(hora*60));
        minutos = Math.floor(minutos);
        int minutos1 = (int)minutos;
        
        double litrosCosumidos= (((totalDistanciaLaborales+totalDistanciaFinde)/100.0)*consumoMedio100Kms);
        System.out.println("Estadísticas");
        System.out.println("*********************************************************");
        System.out.println("Distancia recorrida toda la semana: "+(totalDistanciaLaborales+totalDistanciaFinde)+" kms");
        System.out.println("Distancia recorrida fin de semana: "+totalDistanciaFinde+"kms");
        System.out.println("\n");
        System.out.println("Nº carreras dias laborales: "+totalCarrerasLaborales);
        System.out.println("Nº carreras sábados: "+totalCarrerasSabado);
        System.out.println("Nº carreras domingos: "+totalCarrerasDomingo);
        System.out.println("\n");
        System.out.println("Estimación de litros consumidos: "+litrosCosumidos);
        System.out.println("Importe facturado: "+ importeFacturado+" €");
        System.out.println("Tiempo total en carreras: "+hora1+" horas y "+minutos1+" minutos");
        System.out.println("Factura máxima tarifa normal: "+ maxFacturaNormal+" € ");
        System.out.println("Factura máxima tarifa ampliada: "+ maxFacturaAmpliada+" € ");
        
       
        

        

    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        String diaMayorNumeroCarreras="";
        if(totalCarrerasLaborales>totalCarrerasSabado){
            if(totalCarrerasLaborales>totalCarrerasDomingo){
                diaMayorNumeroCarreras="LABORALES";
            }else if(totalCarrerasLaborales==totalCarrerasDomingo){
                diaMayorNumeroCarreras="LABORALES DOMINGO";
            }else if(totalCarrerasLaborales<totalCarrerasDomingo){
                diaMayorNumeroCarreras="DOMINGO";
            }
        }else if(totalCarrerasSabado>totalCarrerasLaborales){
            if(totalCarrerasSabado>totalCarrerasDomingo){
                diaMayorNumeroCarreras="SÁBADO";
            }else if(totalCarrerasSabado==totalCarrerasDomingo){
                diaMayorNumeroCarreras="SÁBADO DOMINGO";
            }else if(totalCarrerasSabado<totalCarrerasDomingo){
                diaMayorNumeroCarreras="DOMINGO";
            }
            
        }else if(totalCarrerasLaborales==totalCarrerasSabado){
            
            if(totalCarrerasLaborales>totalCarrerasDomingo){
                diaMayorNumeroCarreras="LABORALES SÁBADO";
            }else if(totalCarrerasLaborales==totalCarrerasDomingo){
                diaMayorNumeroCarreras= " LABORALES SÁBADO DOMINGO";
            }else if(totalCarrerasLaborales<totalCarrerasDomingo){
                diaMayorNumeroCarreras="DOMINGO";
            }
        }
            return diaMayorNumeroCarreras;
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
