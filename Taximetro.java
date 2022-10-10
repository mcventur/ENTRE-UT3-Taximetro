
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Aritz Pérez de Ciriza
 */
public class Taximetro
{
    //Constantes:
    // Base dias laborales,empezando despues de las 8:00
    private final double BASE_NORMAL = 3.80;
    // Base fin de samana o cualquier dia antes de las 8:00
    private final double BASE_AMPLIADA = 4.00;
    // Precio por kilometro en laborables despues de las 8:00
    private final double KM_NORMAL = 0.75;
    // Precio por kilometro en findes o cualquier dia antes de las 8:00
    private final double KM_AMPLIADA = 1.10;
    
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    // Atributos:
    // Fijos de cada taxi:
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    // Variables y acumulativos:
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
        pesoVehiculo = pesoKg;
        coeficienteAerodinamico = coefAerodinamico;
        consumoMedio100Kms = pesoKg * coefAerodinamico * 0.01;
        
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
        double esteImporte;
        int minutos = (horaFin/100 - horaInicio/100) * 60 + horaFin % 100 - horaInicio % 100;
    
        switch (dia){
            case 1: case 2: case 3: case 4: case 5:
                if (horaInicio < 800){
                    esteImporte = Math.floor((BASE_AMPLIADA + KM_AMPLIADA * kilometros) * 100)/100;
                    maxFacturaAmpliada = Math.max(maxFacturaAmpliada,esteImporte);
                }
                else{
                    esteImporte = Math.floor((BASE_NORMAL + KM_NORMAL * kilometros) * 100)/100;
                    maxFacturaNormal = Math.max(maxFacturaNormal,esteImporte);
                }
                totalCarrerasLaborales++;
                totalDistanciaLaborales+= kilometros;
                break;
            case 6: case 7:
                if (dia == SABADO) totalCarrerasSabado++; else totalCarrerasDomingo++; 
                totalDistanciaFinde+= kilometros;
                esteImporte = Math.floor((BASE_AMPLIADA + KM_AMPLIADA * kilometros) * 100)/100;
                maxFacturaAmpliada = Math.max(maxFacturaAmpliada,esteImporte);
                break;
            default:
                esteImporte = 0;
                System.out.println("Se ha introducido un dato erroneo");
            }
        importeFacturado+= esteImporte;
        tiempo+= minutos; 
    }
        
         
    /**    
    public void registrarCarreraV2(int kilometros, int dia, int horaInicio, int horaFin) {
        double esteImporte;
        boolean noche = horaInicio < 800;
        boolean laborable = 7 - dia >= 2;
        int minutos = (horaFin/100 - horaInicio/100) * 60 + horaFin % 100 - horaInicio % 100;    
        If (laborable && noche){
            totalCarrerasLaborales++;
            totalDistanciaLaborales+= kilometros;
        }
        else{
            
        }
    }
    */
   
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del taxímetro" + "\n" + "*****************************");
        System.out.println("Matrícula del vehículo: " + matricula);
        System.out.println("Peso del vehículo en Kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: " + consumoMedio100Kms);
        
    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas" + '\n' + "*****************************");
        System.out.println("Distancia recorrida toda la semana: " + (totalDistanciaLaborales + totalDistanciaFinde));
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde);
        System.out.println("Nº carreras días laborables: " + totalCarrerasLaborales);
        System.out.println("Nº carreras sábados: " + totalCarrerasSabado);
        System.out.println("No carreras domingos: " + totalCarrerasDomingo);
        System.out.println("Estimación de litros consumidos: " + (totalDistanciaLaborales + totalDistanciaFinde) * (consumoMedio100Kms/100));
        System.out.println("Importe facturado: " + importeFacturado);
        System.out.println("Tiempo total en carreras: " + tiempo/60 + "h" + tiempo%60 + "min");
        System.out.println("Factura máxima tarifa normal: " + maxFacturaNormal);
        System.out.println("Factura máxima tarifa ampliada: " + maxFacturaAmpliada);
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        double maximo = Math.max(totalCarrerasLaborales,Math.max(totalCarrerasSabado,totalCarrerasDomingo));
        String diaGuardado = "";

        System.out.println("El dia que mas carreras se han realizado");
        if (totalCarrerasLaborales == maximo) diaGuardado+= " LABORABLES ";
        if (totalCarrerasSabado == maximo) diaGuardado+= " SABADO ";
        if (totalCarrerasLaborales == maximo) diaGuardado+= " DOMINGO ";
        return diaGuardado; 
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
