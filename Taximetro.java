
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  David Andueza Ferro
 */
public class Taximetro
{
    //Constantes y atributos
    //Constantes
    private double BASE_NORMAL = 3.80;
    private double BASE_AMPLIADA = 4.00;
    private double KM_NORMAL = 0.75;
    private double KM_AMPLIADA = 1.10;
    private int SABADO = 6;
    private int DOMINGO = 7;
    //atributos
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
    public Taximetro(String nuevaMatricula)    {
        matricula=nuevaMatricula;
        pesoVehiculo=0;
        coeficienteAerodinamico=0;
        consumoMedio100Kms=0;
        totalCarrerasLaborales=0;
        totalCarrerasSabado=0;
        totalCarrerasDomingo=0;
        totalDistanciaLaborales=0;
        totalDistanciaFinde=0;
        tiempo=0;
        importeFacturado=0;
        maxFacturaNormal=0;
        maxFacturaAmpliada=0;
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
    coeficienteAerodinamico=coefAerodinamico;
    pesoVehiculo=pesoKg;
    consumoMedio100Kms=(pesoKg*coefAerodinamico)/100;
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
        double resultado;
        int minutosI= (horaInicio / 100)*60 + (horaInicio % 100);
        int minutosF = (horaFin / 100)*60 + (horaFin % 100);
        tiempo+= minutosF - minutosI; 
        switch(dia){
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
                totalCarrerasSabado++;
                totalDistanciaFinde+=kilometros;
            break;
            case 7:
                totalCarrerasDomingo++;
                totalDistanciaFinde+=kilometros;
            } 
        if (dia==6||dia==7||horaInicio<800){
            importeFacturado+= BASE_AMPLIADA+KM_AMPLIADA*kilometros;
            importeFacturado=Math.floor(importeFacturado*100);
            importeFacturado = importeFacturado/100;
            if(BASE_AMPLIADA+(KM_AMPLIADA*kilometros)>maxFacturaAmpliada){
                resultado = BASE_AMPLIADA+(KM_AMPLIADA*kilometros);
                resultado=Math.floor(resultado*100);
                maxFacturaAmpliada= resultado/100;
            }
        }
        else{
            importeFacturado+= BASE_NORMAL+KM_NORMAL*kilometros;
            importeFacturado=Math.floor(importeFacturado*100);
            importeFacturado = importeFacturado/100;  
            if(BASE_NORMAL+(KM_NORMAL*kilometros)>maxFacturaNormal){
                resultado= BASE_NORMAL+(KM_NORMAL*kilometros);
                resultado=Math.floor(resultado*100);
                maxFacturaNormal= resultado/100;
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
        System.out.println("Configuracion del taximetro");
        System.out.println("********************************");
        System.out.println();
        System.out.println("Peso del vehiculo en Kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinamico: " + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100Kms: " + consumoMedio100Kms);
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadisticas");
        System.out.println("************************");
        System.out.println();
        System.out.println("Distancia recorrida toda la semana: " +  (totalDistanciaLaborales + totalDistanciaFinde)+"kms");
        System.out.println("Distancia recorrida fin la semana: " +  totalDistanciaFinde+"kms");
        System.out.println();
        System.out.println("Nº carreras dias laborables: " + totalCarrerasLaborales);
        System.out.println("Nº carreras Sabados: " + totalCarrerasSabado);
        System.out.println("Nº carreras Domingos: " + totalCarrerasDomingo);
        System.out.println();
        System.out.println("Estimacion de litros consumidos: " + (((totalDistanciaLaborales + totalDistanciaFinde)*100)*consumoMedio100Kms)/10000);
        System.out.println("Importe facturado: " + importeFacturado+ " €");
        System.out.println();
        System.out.println("Tiempo total en carreras: "+tiempo/60+ "horas y "+ tiempo % 60 + "minutos");
        System.out.println("Factura maxima tarifa normal: " + maxFacturaNormal+ " €");
        System.out.println("Factura maxima tarifa ampliada: " + maxFacturaAmpliada+ " €");
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        if(totalCarrerasSabado==totalCarrerasDomingo && totalCarrerasSabado==totalCarrerasLaborales){
            return "SÁBADO,DOMINGO y LABORABLES";
        }
        else if(totalCarrerasDomingo==totalCarrerasSabado && totalCarrerasDomingo==totalCarrerasLaborales){
            return "SÁBADO,DOMINGO y LABORABLES";
        } 
        else if(totalCarrerasLaborales==totalCarrerasSabado && totalCarrerasLaborales==totalCarrerasDomingo){
            return "SÁBADO,DOMINGO y LABORABLES";
        }
        else if(totalCarrerasSabado==totalCarrerasDomingo || totalCarrerasDomingo==totalCarrerasSabado){
            return "SÁBADO y DOMINGO";
        }
        else if(totalCarrerasLaborales==totalCarrerasDomingo || totalCarrerasDomingo==totalCarrerasLaborales){
            return "DOMINGO y LABORABLES";
        }
        else if(totalCarrerasLaborales==totalCarrerasSabado || totalCarrerasSabado==totalCarrerasLaborales){
            return "SÁBADO y LABORABLES";
        }
        else if(totalCarrerasLaborales>totalCarrerasSabado || totalCarrerasLaborales>totalCarrerasDomingo){
            return "LABORABLES";
        }
        else if(totalCarrerasDomingo>totalCarrerasLaborales || totalCarrerasDomingo>totalCarrerasSabado){
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
        pesoVehiculo=0;
        coeficienteAerodinamico=0;
        consumoMedio100Kms=0;
        totalCarrerasLaborales=0;
        totalCarrerasSabado=0;
        totalCarrerasDomingo=0;
        totalDistanciaLaborales=0;
        totalDistanciaFinde=0;
        tiempo=0;
        importeFacturado=0;
        maxFacturaNormal=0;
        maxFacturaAmpliada=0;
    }    

}
