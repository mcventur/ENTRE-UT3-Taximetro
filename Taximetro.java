
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author Álvaro Mateos
 */
public class Taximetro
{
    //Constantes 
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    private final double KM_NORMAL= 0.75;
    private final double KM_AMPLIADA = 1.10;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //----------------------------------------------
    //Atributos
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    private int totalCarrerasLaborales;
    private int totalCarrerasSabados;
    private int totalCarrerasDomingo;
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    private int tiempo;
    private double importeFacturado;
    private double maxFacturaNormal;
    private double maxFacturaAmpliada;
    //----------------------------------------------
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String nuevaMatricula)    {
    matricula = nuevaMatricula;
    pesoVehiculo = 0;
    coeficienteAerodinamico = 0;
    consumoMedio100Kms = 0;
    totalCarrerasLaborales = 0;
    totalCarrerasSabados = 0;
    totalCarrerasDomingo = 0;
    totalDistanciaLaborales = 0;
    totalDistanciaFinde = 0;
    tiempo = 0;
    importeFacturado = 0;
    maxFacturaNormal = 0;
    maxFacturaAmpliada= 0;
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
        consumoMedio100Kms = (pesoKg * coeficienteAerodinamico)/100;
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
        //atributos locales
        int minutosInicio= (horaInicio/100) * 60 + (horaInicio%100);
        int minutosFin = (horaFin/100) * 60 + (horaFin%100);
        double max;
        double resultado;
        
        tiempo += minutosFin - minutosInicio;
        switch (dia)
            {
                case 1:
                totalCarrerasLaborales++;
                totalDistanciaLaborales+=kilometros;
                break;
                case 2:
                totalCarrerasLaborales++;
                totalDistanciaLaborales+=kilometros;
                break;
                case 3:
                totalCarrerasLaborales++;
                totalDistanciaLaborales+=kilometros;
                break;
                case 4:
                totalCarrerasLaborales++;
                totalDistanciaLaborales+=kilometros;
                break;
                case 5:
                totalCarrerasLaborales++;
                totalDistanciaLaborales+=kilometros;
                break;
                case 6:
                totalCarrerasSabados++;
                totalDistanciaFinde+=kilometros;
                break;
                case 7:
                totalCarrerasDomingo++;
                totalDistanciaFinde+=kilometros;
                break;
            }
        
        if (dia==6||dia==7||horaInicio<800) { 
            importeFacturado += BASE_AMPLIADA+KM_AMPLIADA*kilometros;   
            importeFacturado = Math.floor(importeFacturado*100)/100;
            max = BASE_AMPLIADA+KM_AMPLIADA*kilometros;
                if(maxFacturaAmpliada < max){
                    resultado=BASE_AMPLIADA+(KM_AMPLIADA*kilometros);
                    maxFacturaAmpliada=resultado;
                }
            }
        else { 
            importeFacturado += BASE_NORMAL+KM_NORMAL*kilometros;   
            importeFacturado = Math.floor(importeFacturado*100)/100;
            max = BASE_NORMAL+KM_NORMAL*kilometros;
                if(maxFacturaNormal < max){
                    resultado=BASE_NORMAL+(KM_NORMAL*kilometros);
                    maxFacturaNormal=resultado;
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
        System.out.println("Configuracion del taxímetro");
        System.out.println("***************************");
        System.out.println();
        System.out.println("Peso del vehiculo en Kg:"+ pesoVehiculo);
        System.out.println("Coeficiente aerodinamico:" + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100Kms:" + consumoMedio100Kms);
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
    System.out.println();
    System.out.println();
    System.out.println("Estadisticas");
    System.out.println("***************************");
    System.out.println();
    System.out.println("Distancia recorrida toda la semana: "+ (totalDistanciaLaborales + totalDistanciaFinde)+"km");
    System.out.println("Distancia recorrida el fin de semana: "+ (totalDistanciaFinde)+"km");
    System.out.println();
    System.out.println("N° carreras en dias laborables: "+ totalCarrerasLaborales);
    System.out.println("N° carreras en Sabados: "+ totalCarrerasSabados);
    System.out.println("N° carreras en Domingos: "+ totalCarrerasDomingo);
    System.out.println();
    System.out.println("Estimacion de litros consumido: "+ ((totalDistanciaLaborales+totalDistanciaFinde)*consumoMedio100Kms)/100);
    System.out.println("Importe facturado: "+ importeFacturado+"€");
    System.out.println();
    System.out.println("Tiempo total en carreras: "+ tiempo/60 + " horas y " + (tiempo%60)+ " minutos");
    System.out.println("Factura maxima de tarifa normal: "+ maxFacturaNormal+"€");
    System.out.println("Factura maxima de tarifa ampliada: "+ maxFacturaAmpliada+"€");
    
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if(totalCarrerasSabados==totalCarrerasDomingo && totalCarrerasSabados == totalCarrerasLaborales){
            return "SABADO,DOMINGO y LABORALES";
        }
        else if(totalCarrerasDomingo==totalCarrerasSabados && totalCarrerasDomingo == totalCarrerasLaborales){
            return "SABADO,DOMINGO y LABORALES";
        }
        else if(totalCarrerasLaborales==totalCarrerasSabados && totalCarrerasLaborales == totalCarrerasDomingo){
            return "SABADO,DOMINGOS y LABORALES";
        }
        else if(totalCarrerasSabados==totalCarrerasDomingo){
            return "SABADO y DOMINGO";
        }
        else if(totalCarrerasDomingo==totalCarrerasLaborales){
            return "DOMINGO y LABORALES";
        } 
        else if(totalCarrerasLaborales>totalCarrerasSabados){
            return "LABORALES";
        }
        else if(totalCarrerasDomingo>totalCarrerasLaborales){
            return "DOMINGO";
        }
        else 
            return "SABADO";
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
        totalCarrerasSabados = 0;
        totalCarrerasDomingo = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        tiempo = 0;
        importeFacturado = 0;
        maxFacturaNormal = 0;
        maxFacturaAmpliada= 0;
    }    

}
