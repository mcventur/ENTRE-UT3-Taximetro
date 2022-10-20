
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Marce
 * @version  
 */
public class Taximetro
{
    //Constantes
    //Precio base
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4;
    
    //Precios por kilómetro de cada tarifa
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    
    //Sabado y Domingo
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    //Atributos
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    
    private int totalCarrerasLaborales;
    private int totalCarrerasSabados;
    private int totalCarrerasDomingos;
    
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
        //Calculamos el consumo estimado a los 100
        consumoMedio100Kms = pesoVehiculo * coeficienteAerodinamico / 100;
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
        //Contabilizamos el tiempo
        int minutosInicio = (horaInicio / 100) * 60 + horaInicio % 100;
        int minutosFin = (horaFin / 100) * 60 + horaFin % 100;
        int tiempoCarrera=(minutosFin - minutosInicio); 
        tiempo += tiempoCarrera;
        //Variable para 
        boolean finde = false;
        
        //Contabilizamos distancias y carreras
        switch(dia){
            case 1:case 2:case 3:case 4:case 5:
                totalDistanciaLaborales += kilometros;
                totalCarrerasLaborales++;
                break;
            case SABADO:
                totalDistanciaFinde += kilometros;
                totalCarrerasSabados++;
                finde = true;
                break;
            case DOMINGO:
                totalDistanciaFinde += kilometros;
                totalCarrerasDomingos++;
                finde = true;
                break;
            default:
        }
        
        //Facturación
        double factura = 0;
        
        if((horaInicio<800) || (finde)){//Si la carrera empieza antes de las 8 o es fin de semana, se aplica la tarifa Ampliada
           //Calculamos la facturación de la carrera
            factura = BASE_AMPLIADA + kilometros * KM_AMPLIADA; 
            //Redondeo a dos decimales
            factura = Math.floor(factura*100)/100;
            //Comprobamos si hay que actualizar la factura máxima ampliada
            if(factura >= maxFacturaAmpliada)
                maxFacturaAmpliada = factura;
        }
        else{
           //Calculamos la facturación de la carrera
            factura = BASE_NORMAL + kilometros * KM_NORMAL;                        
            //Redondeo a dos decimales
            factura = Math.floor(factura*100)/100;            
            //Comprobamos si hay que actualizar la factura máxima Normal
            if(factura >= maxFacturaNormal)
                maxFacturaNormal = factura;            
        }
        
        //Acumulamos al importe facturado
        importeFacturado += factura;         
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println();
        System.out.println("Configuración del taxímetro");
        System.out.println("***************************");
        System.out.println("Peso del vehículo en Kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: " + consumoMedio100Kms);
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
        int totalDistancia = (totalDistanciaFinde + totalDistanciaLaborales);
        System.out.println();
        System.out.println("Estadísticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistancia + "kms");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde + "kms");
        System.out.println();
        System.out.println("Nº carreras días laborables: " + totalCarrerasLaborales);
        System.out.println("Nº carreras sábados: " + totalCarrerasSabados);
        System.out.println("Nº carreras domingos: " + totalCarrerasDomingos);
        System.out.println();
        System.out.println("Estimación de litros consumidos: " + (totalDistancia/100.0)*consumoMedio100Kms);
        System.out.println("Importe facturado: " + importeFacturado + " €");
        System.out.println();
        int tiempoHoras = tiempo/60;
        int tiempoMinutos = tiempo%60;
        System.out.println("Tiempo total en carreras: " + tiempoHoras + " horas y " + tiempoMinutos + " minutos");
        System.out.println("Factura máxima tarifa normal: " + maxFacturaNormal + " €");
        System.out.println("Factura máxima tarifa ampliada: " + maxFacturaAmpliada + " €");
        

    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        String diaMayor = "";//Guardaremos la cadena con el día o días en un String;
        int mayor=0;
        
        //Primero comparamos laborales y sábados
        if(totalCarrerasLaborales > totalCarrerasSabados){
            diaMayor="LABORALES";
            mayor = totalCarrerasLaborales;
        }
        else if(totalCarrerasLaborales < totalCarrerasSabados){
            diaMayor = " SABADOS";
            mayor = totalCarrerasSabados;
        }
        else{//Sólo queda que sean iguales
            diaMayor = "LABORALES SÁBADOS";
            mayor = totalCarrerasSabados;
        }
        
		//Ahora comparamos el mayor (entre laborales y sábados) con domingos
        if(totalCarrerasDomingos > mayor){
            diaMayor = "DOMINGOS";
        }
        else if(totalCarrerasDomingos == mayor){
            diaMayor += " DOMINGO"; //Si hay empate, añadimos DOMINGO a la cadena que teníamos con el diaMayor
        }
        
        return diaMayor; 

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
        totalCarrerasDomingos = 0;
        totalDistanciaLaborales = 0;
        totalDistanciaFinde = 0;
        tiempo = 0;
        importeFacturado = 0;
        maxFacturaNormal = 0;
        maxFacturaAmpliada = 0;
    }    

}
