
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Adrian Vera
 */
public class Taximetro
{
    //CONSTANTES
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4;
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    // ATRIBUTOS
    // GENERALES
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Kms;
    
    // CARRERAS
    private int totalCarrerasLaborales;
    private int totalCarrerasSabado;
    private int totalCarrerasDomingo;
    
    // DISTANCIA
    private int totalDistanciaLaborales;
    private int totalDistanciaFinde;
    
    // TIEMPO Y FACTURACIÓN
    private int tiempo;
    private double importeFacturado;
    private double maxFacturaNormal;
    private double maxFacturaAmpliada;
    
    
    /**
     * Constructor 
     * Inicializa el taximetro con la matricula del vehículo. 
     * El resto de atributos se ponen a 0
     */
    public Taximetro(String matricula)
    {
        this.matricula = matricula;
        this.pesoVehiculo = 0;
        this.coeficienteAerodinamico = 0.0;
        this.consumoMedio100Kms = 0.0;
        this.totalCarrerasLaborales = 0;
        this.totalCarrerasSabado = 0;
        this.totalCarrerasDomingo = 0;
        this.totalDistanciaLaborales = 0;
        this.totalDistanciaFinde = 0;
        this.tiempo = 0;
        this.importeFacturado = 0.0;
        this.maxFacturaNormal = 0.0;
        this.maxFacturaAmpliada = 0.0;
    }

    /**
     * Accesor para la matricula
     */
    public String getMatricula()
    {
        return this.matricula;
    }

    /**
     * Permite configurar los parámetros de consumo del taximetro
     * (Leer enunciado)
     */
    public void configurar(double coefAerodinamico, int pesoKg)
    {
        this.coeficienteAerodinamico = coefAerodinamico;
        this.pesoVehiculo = pesoKg;
        this.consumoMedio100Kms = (pesoKg * coefAerodinamico) / 100;
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
    public void registrarCarrera(int kilometros, int dia, int horaInicio, int horaFin)
    {
        int horaAMinutos = 0;
        int horasTranscurridas = (horaFin / 100) - (horaInicio / 100);
        int minutos = (horaFin % 100) - (horaInicio % 100);
        double[] tarifasBase = {this.BASE_NORMAL, this.BASE_AMPLIADA};
        double[] kmBase = {this.KM_NORMAL, this.KM_AMPLIADA};
        int status = 0;
        
        // Verificar si el dia es sábado, domingo o si la hora es anterior a las 8:00 (para aplicar tarifa básica o ampliada)
        if((horaInicio / 100) <  8 || dia == 6 || dia == 7) status = 1;
        
        // Pasar de horas a minutos
        for(int i = 0; horasTranscurridas != 0; i++)
        {
            horaAMinutos += 60;
            horasTranscurridas--;
        }
        
        double importe = Math.floor((tarifasBase[status] + (kmBase[status] * kilometros)) * 100.0) / 100.0;
        
        // Registro de valores dependiendo del día
        switch(dia)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                this.totalCarrerasLaborales++;
                this.totalDistanciaLaborales += kilometros;
                break;
            case 6:
                this.totalCarrerasSabado++;
                this.totalDistanciaFinde += kilometros;
                break;
            case 7:
                this.totalCarrerasDomingo++;
                this.totalDistanciaFinde += kilometros;
                break;
        }
        
        // Verificar y aplicar cambios si el importe es mayor al ya guardado o no
        if(this.maxFacturaAmpliada < importe && status == 1) this.maxFacturaAmpliada = importe;
        if(this.maxFacturaNormal < importe && status == 0) this.maxFacturaNormal = importe;
        
        this.tiempo += horaAMinutos + minutos;
        this.importeFacturado += importe;
    }
    
    /**
     * Muestra en pantalla la configuración del taxímetro
     * (matricula, coeficiente aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion()
    {
        System.out.println("Configuración del taxímetro");
        System.out.println("***************************");
        System.out.println("Peso del vehículo en Kg: " + this.pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + this.coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: " + this.consumoMedio100Kms);

    }
    
    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * carreras, tiempo total, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas()
    {
        int numHorasTotales = 0;
        int tiempo = this.tiempo;
        
        // Pasar de minutos a horas
        for(int i = 0; tiempo >= 60; i++)
        {
            numHorasTotales = tiempo / 60;
            tiempo %= 60;
        }
        
        System.out.println("\n\nEstadísticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + (this.totalDistanciaLaborales + this.totalDistanciaFinde) + " kms");
        System.out.println("Distancia recorrida fin de semana: " + this.totalDistanciaFinde + " kms\n");
        System.out.println("Nº carreras días laborales: " + this.totalCarrerasLaborales);
        System.out.println("Nº carreras sábado: " + this.totalCarrerasSabado);
        System.out.println("Nº carreras domingo: " + this.totalCarrerasDomingo + "\n");
        System.out.println("Estimación de litros consumidos: " + Math.floor((((this.totalDistanciaLaborales + this.totalDistanciaFinde) * this.consumoMedio100Kms) / 100) * 1000.0) / 1000.0);
        System.out.println("Importe facturado: " + this.importeFacturado + " €\n");
        System.out.println("Tiempo total en carreras: " + numHorasTotales + " horas y " + tiempo + " minutos");
        System.out.println("Factura máxima tarifa normal: " + this.maxFacturaNormal + " €");
        System.out.println("Factura máxima tarifa ampliada: " + this.maxFacturaAmpliada + " €");
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras()
    {
        if(this.totalCarrerasSabado == this.totalCarrerasDomingo) return "SABADO DOMINGO";
        if(this.totalCarrerasSabado == this.totalCarrerasLaborales) return "SABADO LABORABLES";
        if(this.totalCarrerasDomingo == this.totalCarrerasLaborales) return "LABORABLES DOMINGO";
        
        if(this.totalCarrerasLaborales > this.totalCarrerasDomingo || this.totalCarrerasLaborales > this.totalCarrerasSabado) return "LABORABLES";
        if(this.totalCarrerasDomingo > this.totalCarrerasSabado || this.totalCarrerasDomingo > this.totalCarrerasLaborales) return "DOMINGO";
        
        return "";
    }    
    
    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
     *  
     */    
    public void reset()
    {
        this.pesoVehiculo = 0;
        this.coeficienteAerodinamico = 0.0;
        this.consumoMedio100Kms = 0.0;
        this.totalCarrerasLaborales = 0;
        this.totalCarrerasSabado = 0;
        this.totalCarrerasDomingo = 0;
        this.totalDistanciaLaborales = 0;
        this.totalDistanciaFinde = 0;
        this.tiempo = 0;
        this.importeFacturado = 0.0;
        this.maxFacturaNormal = 0.0;
        this.maxFacturaAmpliada = 0.0;
    }    

}
