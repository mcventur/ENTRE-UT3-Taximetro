
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Iñaki Ojer
 */
public class Taximetro
{
    //Constantes y atributos
    private double Base_Normal = 3.80;
    private double Base_Ampliada = 4.00;
    private double Km_Normal = 0.75;
    private double Km_Ampliada = 1.10;
    private int Sabado = 6;
    private int Domingo = 7;
    
    private String Matricula;
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
        Matricula = queMatricula;
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Kms = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        tiempo = 0;
        maxFacturaNormal = 0;
        maxFacturaAmpliada = 0;
        
    }

    /**
     * Accesor para la matricula
     */
    public String getMatricula() {
        return Matricula;
    }

    /**
     * Permite configurar los parámetros de consumo del taximetro
     * (Leer enunciado)
     */
    public void configurar(double coefAerodinamico, int pesoKg) {
        coeficienteAerodinamico = coefAerodinamico;
        pesoVehiculo = pesoKg;
        consumoMedio100Kms = (pesoKg * coefAerodinamico) /100;
        
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
        double max;
        switch(dia){
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            totalCarrerasLaborales = totalCarrerasLaborales + 1;
            totalDistanciaLaborales = kilometros;
            if (horaInicio < 800){
                importeFacturado += Base_Ampliada + Km_Ampliada*kilometros;
                
                 importeFacturado = (Math.floor(importeFacturado*100));
                 importeFacturado = importeFacturado / 100;
                 max = importeFacturado;
                if(maxFacturaAmpliada < max){
                    maxFacturaAmpliada = max;
                }

            }
            else{
                importeFacturado += Base_Normal + Km_Normal*kilometros;
                
                 importeFacturado = (Math.floor(importeFacturado*100));
                 importeFacturado = importeFacturado / 100;
                 max = importeFacturado;
                if(maxFacturaNormal < max){
                    maxFacturaNormal = max;
                }
            }
            
        break;
            
        case 6:
            totalCarrerasSabado = totalCarrerasSabado + 1;
            totalDistanciaFinde += kilometros;
                importeFacturado += Base_Ampliada + Km_Ampliada*kilometros;
                
                 importeFacturado = (Math.floor(importeFacturado*100));
                 importeFacturado = importeFacturado / 100;
                 max = importeFacturado;
                if(maxFacturaAmpliada < max){
                    maxFacturaAmpliada = max;
                }
        break;
        
        case 7:
            totalCarrerasDomingo = totalCarrerasDomingo + 1;
            totalDistanciaFinde += kilometros;
                importeFacturado += Base_Ampliada + Km_Ampliada*kilometros;
                
                 importeFacturado = (Math.floor(importeFacturado*100));
                 importeFacturado = importeFacturado / 100;
                 max = importeFacturado;
                if(maxFacturaAmpliada < max){
                    maxFacturaAmpliada = max;
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
        System.out.println("Configuracion del taximetro");
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("Peso del vehiculo en Kg:" + pesoVehiculo);
        System.out.println("Coeficiente Aerodinamico:" + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100Kms:" + consumoMedio100Kms);
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas(){ 
        System.out.println("Distancia recorrida toda la semana:" + (totalDistanciaLaborales + totalDistanciaFinde) + "kms");
        System.out.println("Distancia recorrida el fin de semana" + totalDistanciaFinde);
        System.out.println("");
        System.out.println("");
        System.out.println("Numero de carreras dias laborables:" + totalCarrerasLaborales);
        System.out.println("Total carreras Sabado:" + totalCarrerasSabado);
        System.out.println("Total carreras Domingo:" + totalCarrerasDomingo);
        System.out.println("");
        System.out.println("");
        System.out.println("Estimado de litros consumidos:" + (((totalDistanciaLaborales + totalDistanciaFinde) * consumoMedio100Kms) / 100));
        System.out.println("Importe facturado:" + importeFacturado);
        System.out.println("");
        System.out.println("");
        System.out.println("Tiempo total en carrera:" + tiempo / 60 + "Horas" + tiempo % 60 + "Minutos");
        System.out.println("Factura maxima tarifa Normal:" + maxFacturaNormal);
        System.out.println("Factura maxima tarifa Ampliada:" + maxFacturaAmpliada);
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if(totalCarrerasLaborales > totalCarrerasSabado && totalCarrerasLaborales > totalCarrerasDomingo) {
            if(totalCarrerasLaborales == totalCarrerasSabado){
                return "LABORALES y SÁBADO";
            }
            else if(totalCarrerasLaborales == totalCarrerasDomingo){
                return "LABORALES y DOMINGO";
            }
            else{
                return "LABORALES";
            }
        }
        else if (totalCarrerasSabado > totalCarrerasLaborales && totalCarrerasSabado > totalCarrerasDomingo ){
            if(totalCarrerasSabado == totalCarrerasDomingo){
                return  "SABADO y DOMIGO";
            }
            else{
                return  "SABADO";
            }            
        }
        else {
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
    
        pesoVehiculo = 0;
        coeficienteAerodinamico = 0;
        consumoMedio100Kms = 0;
        totalCarrerasLaborales = 0;
        totalCarrerasSabado = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        tiempo = 0;
        maxFacturaNormal = 0;
        maxFacturaAmpliada = 0;
        

    }    

}
