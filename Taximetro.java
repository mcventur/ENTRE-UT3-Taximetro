
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  
 */
public class Taximetro
{
    //Constantes y atributos
    private final double baseNormal = 3.80;
    private final double baseAmpliada = 4.00;
    private final double kmNormal = 0.75;
    private final double kmAmpliada = 1.10;
    private final int sabado = 6;
    private final int domingo = 7;
    
    private String matricula;
    private int pesoVehiculo;
    private double coeficienteAerodinamico;
    private double consumoMedio100Km; 
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
    public Taximetro(String matricula)    
    {
        this.matricula = matricula;
    }

    /**
     * Accesor para la matricula
     */
    public String getMatricula() 
    {
        return matricula;
    }

    /**
     * Permite configurar los parámetros de consumo del taximetro
     * (Leer enunciado)
     */
    public void configurar(double coefAerodinamico, int pesoKg) 
    {
        coeficienteAerodinamico = coefAerodinamico;
        pesoVehiculo = pesoKg;
        consumoMedio100Km = ((pesoKg * coefAerodinamico)/100);
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
        tiempo += ((horaFin - horaInicio)/100) ;
        if (dia < 6 && dia > 0 )
        {
            totalCarrerasLaborales ++;
            totalDistanciaLaborales += kilometros;
            if (horaInicio >= 800 && horaInicio <= 2359)
            {
                importeFacturado += ((baseNormal) + (kilometros *
                kmNormal));
            }
            else if (horaInicio < 800 && horaInicio > 0)
            {
                importeFacturado += ((baseAmpliada) + (kilometros *
                kmAmpliada));
            }
        }
        else if (dia == 6)
        {
            totalCarrerasSabado ++;
            totalDistanciaFinde += kilometros;
            importeFacturado += ((baseAmpliada) + (kilometros *
            kmAmpliada));
        }
        else if (dia == 7)
        {
            totalCarrerasDomingo ++;
            totalDistanciaFinde += kilometros;
            importeFacturado += ((baseAmpliada) + (kilometros *
            kmAmpliada));
        }
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
        System.out.println("Configuración del taxímetro " + "\n********************"
        + "\n" + "\nPeso del vehículo en Kg: " + pesoVehiculo + "\nCoeficiente aerodinamico: " + coeficienteAerodinamico
        + "\nConsumo medio estimado por cada 100kms: " + consumoMedio100Km);
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
        tiempo = (tiempo * 60);
        System.out.println("Estadísticas " + "\n*************************** "
        + "\nDistancia recorrida toda la semana: " + totalDistanciaLaborales
        + "\nDistancia recorrida fin de semana: " + totalDistanciaFinde
        + "\nNº carreras días laborables: " + totalCarrerasLaborales
        + "\nNº carreras sábados: " + totalCarrerasSabado
        + "\nNº carreras domingos: " + totalCarrerasDomingo
        + "\n" +"\nImporte facturado: " + importeFacturado 
        + "\n" + "\nTiempo total en carreras: " + tiempo + " minutos"
        + "\nFactura máxima tarifa normal: " + maxFacturaNormal + "€"
        + "\nFactura máxima tarifa ampliada: " + maxFacturaAmpliada + "€");
    }    
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se han realizado más carreras - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroCarreras() 
    {
     String empate ="";
     if (totalCarrerasLaborales > totalCarrerasSabado 
     && totalCarrerasLaborales > totalCarrerasDomingo)
     {
         return "LABORABLE";
     }
     else if (totalCarrerasSabado > totalCarrerasLaborales 
     && totalCarrerasSabado > totalCarrerasDomingo)
     {
         return "SÁBADO";
     }
     else if(totalCarrerasDomingo > totalCarrerasLaborales
     && totalCarrerasDomingo > totalCarrerasSabado)
     {
         return "DOMINGO";
     }
     else 
     {
         if (totalCarrerasLaborales == totalCarrerasSabado || 
             totalCarrerasLaborales == totalCarrerasDomingo ||
             totalCarrerasSabado == totalCarrerasDomingo)
             {
                 empate = "DOBLE EMPATE";
             }
         else if (totalCarrerasLaborales == totalCarrerasSabado &&
                 totalCarrerasLaborales == totalCarrerasDomingo)
             {
                 empate = "TRIPLE EMPATE";
             }
         return empate;    
     }
    }    
    
    /**
     * Restablecer los valores iniciales del taximetro
     * Todos los atributos se ponen a cero
     * La matrícula no varía
     *  
     */    
    public void reset() 
    {
        String matricula;
         int pesoVehiculo = 0;
         double coeficienteAerodinamico = 0;
         double consumoMedio100Km = 0;
         int totalCarrerasLaborales = 0;
         int totalCarrerasSabado = 0;
         int totalCarrerasDomingo = 0;
         int totalDistanciaLaborales = 0;
         int totalDistanciaFinde = 0;
         int tiempo = 0;
         double importeFacturado = 0;
         double maxFacturaNormal = 0;
         double maxFacturaAmpliada = 0;
    }    

}
