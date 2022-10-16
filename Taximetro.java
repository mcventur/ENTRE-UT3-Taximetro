
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Pablo Sanz
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
    private double consumoMedio;
    
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
    public Taximetro(String matriculaTaxi)    {
        matricula = matriculaTaxi;
        reset(); 
        /*
        DemoTaximetro demoTaximetro = new DemoTaximetro();
        demoTaximetro.iniciar();
        */
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
        consumoMedio = (pesoVehiculo * coeficienteAerodinamico)/100;

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
        int min_total;
        double min_inicio;
        double min_fin;
        double importe_carrera;
        
        importe_carrera = 0;
        
        min_inicio = (double)((horaInicio/100)*60)+(horaInicio%100);
        min_fin = (double)((horaFin/100)*60)+(horaFin%100);
        min_total = (int)(min_fin - min_inicio);
              
        
        switch(dia){
            case 1:
            case 2: 
            case 3: 
            case 4: 
            case 5: 
                importe_carrera = Math.round((BASE_NORMAL + (KM_NORMAL * (double)kilometros)) * (double)100) / (double)100; 
                totalCarrerasLaborales ++;
                totalDistanciaLaborales = totalDistanciaLaborales + kilometros;
                if(maxFacturaNormal < importe_carrera){
                  maxFacturaNormal = importe_carrera;  
                }
                break;
            case 6:     
            case 7:
                importe_carrera = Math.round((BASE_AMPLIADA + (KM_AMPLIADA * (double)kilometros)) * (double)100) / (double)100; 
                totalDistanciaFinde = totalDistanciaFinde + kilometros;
                if(maxFacturaAmpliada < importe_carrera){
                  maxFacturaAmpliada = importe_carrera;  
                }
                if(dia == 6){
                  totalCarrerasSabado ++;  
                }else{
                  totalCarrerasDomingo ++;  
                }
                break;
        
        
        
        }
        tiempo = tiempo + min_total;
        importeFacturado = importeFacturado + importe_carrera;
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println ("configuración del taxímetro");
        System.out.println ("***************************");
        System.out.println ("Peso del vehículo en Kg: " + pesoVehiculo);
        System.out.println ("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println ("Consumo medio estimado por cada 100 Kms: " 
        + consumoMedio);

    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        int total_km, horas, minutos; 
        double litrosConsumidos; 
        total_km = totalDistanciaLaborales+totalDistanciaFinde;
        litrosConsumidos = ((double)total_km / 100) * consumoMedio; 
        horas = tiempo/60;
        minutos = tiempo%60;

        System.out.println ("");
        System.out.println ("Estadísticas");
        System.out.println ("***************************");
        System.out.println ("Distacia recorrida toda la semana: " + total_km + "Kms");
        System.out.println ("Distancia recorrida fin de semana: " + totalDistanciaFinde + "Kms");
        System.out.println ("");
        System.out.println ("Nº carreras dias laborales: " + totalCarrerasLaborales);
        System.out.println ("Nº carreras sábados: " + totalCarrerasSabado);
        System.out.println ("Nº carreras domingos: " + totalCarrerasDomingo);
        System.out.println ("");
        System.out.println ("Estimación de litros consumidos: "+ litrosConsumidos);
        System.out.println ("Importe facturado: "+ importeFacturado + " €");
        System.out.println ("");
        System.out.println ("Tiempo total en la carrera: " + horas + " horas y " + minutos + " minutos");
        System.out.println ("Factura maxima tarifa normal: "+ maxFacturaNormal+ " €");
        System.out.println ("Factura maxima tarifa ampliada: "+ maxFacturaAmpliada+ " €");

        

    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() {
        String dia;
        dia = "";
        int max;
        max = Math.max(totalCarrerasLaborales,totalCarrerasSabado);
        max = Math.max(max,totalCarrerasDomingo);
        
        
        if (totalCarrerasLaborales == max){
           dia = "LABORALES";
        }
        if (totalCarrerasSabado == max){
           dia = dia + " SÁBADO";
        }
        if (totalCarrerasDomingo == max){
           dia = dia + " DOMINGO";        
        }         
        return dia.trim();
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
      consumoMedio = 0;
    
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
