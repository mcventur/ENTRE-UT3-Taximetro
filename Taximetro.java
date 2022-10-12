
/**
 *  La clase modela un taximetro simplificado que recoge estadísticas
 *  de las carreras realizadas, tiempo total, distancia...
 * 
 * @author  Aritz Pérez de Ciriza
 */
public class Taximetro
{
    /** Constantes:
    */
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
    
    /** Atributos:
    */
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
        //Variables locales para guardar y modificar los parametros  int horaInicio y int horaFin
        int horaInicioCorregida = horaInicio;
        int horaFinCorregida = horaFin;
        /**
         * Habia un problema con los parametros horaInicio y horaFin que al introducirse 
         * como 0xx ocasionaba un error de cálculo, intenté quitar el 0 del principio, 
         * pero en realidad el problema es que el valor del parametro 
         * que llega No tiene un 0 delante, 015 en realidad entra como 13. 
         * 
         * Para solucionarlo hay es pasar el numero 13, a sus cifras en Octal.
         * 13%8 + 10*(13/8) = 15
         * 
         * Pero esto solo funciona por que los numero que empiezan por 0xx pueden ir de
         * 0:00 a 0:57 (058 y 059 dara error) y como 57 en octal es 
         * 47 decimal, y a partir de la 1:00 todas las hora son mayores que 100, 
         * si horaInicio o horaFin son menores que 100 (o que 48) sabemos que estamos hablando
         * de un imput tipo 0xx, que se ha traducido a cifras decimales. 
         * 
         * Sacamos las cifras que tendria en base octal, y recuperamos el imput original, 
         * pero sin el 0 delante.
         */
        if (horaInicio<100) horaInicioCorregida = horaInicio%8 +10*(horaInicio/8);
        if (horaFin<100) horaFinCorregida = horaFin%8 +10*(horaFin/8);
        
        //Variable formal que guarda el importe de una carrera concreta segun el valor que 
        //se le asigne en base a las tarifas, y luego se suma al atributo importe.
        double esteImporte;
        
        int minutos = (horaFinCorregida/100 - horaInicioCorregida/100) * 60 + horaFinCorregida % 100 - horaInicioCorregida % 100;
        
        switch (dia){
            case 1: case 2: case 3: case 4: case 5:
            //Si estamos en case 1-5, miramos si es una carrera antes de las 8 o despues, para aplicar la tarifa adecuada.
                if (horaInicio < 800){
                    // esteIporte guarda el valor del importe de esta carrera
                    esteImporte = Math.floor((BASE_AMPLIADA + KM_AMPLIADA * kilometros) * 100)/100;
                    // maxFacturaAmpliada guarda el importe mas alto hasta el momento,compara el importe de esta carrera, con su valor, y se actualiza con el valor mas grande.
                    maxFacturaAmpliada = Math.max(maxFacturaAmpliada,esteImporte);
                }
                else{
                    esteImporte = Math.floor((BASE_NORMAL + KM_NORMAL * kilometros) * 100)/100;
                    maxFacturaNormal = Math.max(maxFacturaNormal,esteImporte);
                }
                // Cuenta el numero de veces que usamos el metodo, osea cuantas carreras hace el taxi. 
                totalCarrerasLaborales++;
                // totalDistanciaLaborales guarda la distancia sumada de todas las carreras. Y se actualiza sumando a su valor los kilometros de esta carrera
                totalDistanciaLaborales+= kilometros;
                break;
                
                /**
                 * Como te parece mejor el case 6 y 7?
                 */
                
            case 6: case 7:
                if (dia == SABADO) totalCarrerasSabado++; else totalCarrerasDomingo++; 
                totalDistanciaFinde+= kilometros;
                esteImporte = Math.floor((BASE_AMPLIADA + KM_AMPLIADA * kilometros) * 100)/100;
                maxFacturaAmpliada = Math.max(maxFacturaAmpliada,esteImporte);
                break;
            // case 6: 
                // totalCarrerasSabado++; 
                // totalDistanciaFinde+= kilometros;
                // esteImporte = Math.floor((BASE_AMPLIADA + KM_AMPLIADA * kilometros) * 100)/100;
                // maxFacturaAmpliada = Math.max(maxFacturaAmpliada,esteImporte);
                // break;    
            // case 7:
                // totalCarrerasDomingo++;
                // totalDistanciaFinde+= kilometros;
                // esteImporte = Math.floor((BASE_AMPLIADA + KM_AMPLIADA * kilometros) * 100)/100;
                // maxFacturaAmpliada = Math.max(maxFacturaAmpliada,esteImporte);
                // break; 
            default:
                esteImporte = 0;
                System.out.println("Se ha introducido un dato erroneo");
            }
        // Importe total y tiempo total se actualizan sumando los de esta carrera
        importeFacturado+= esteImporte;
        tiempo+= minutos; 
    }
   
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
        // El valor maximo de entre totalCarrerasSabado,totalCarrerasDomingo,totalCarrerasLaborales
        double maximo = Math.max(totalCarrerasLaborales,Math.max(totalCarrerasSabado,totalCarrerasDomingo));
        // En esta variable guardaremos los String correspondientes a los maximos entre totalCarrerasSabado,totalCarrerasDomingo,totalCarrerasLaborales
        String diaGuardado = "";

        System.out.println("El dia que mas carreras se han realizado");
        if (totalCarrerasLaborales == maximo) diaGuardado+= " LABORABLES";

        if (totalCarrerasSabado == maximo) diaGuardado+= " SABADO";

        if (totalCarrerasDomingo == maximo) diaGuardado+= " DOMINGO";


        return diaGuardado; 
    }    
    /**
     * Cual te parece mejor?
     */
    public String diaMayorNumeroCarrerasV2() {
        String diaGuardado = "";

        System.out.println("El dia que mas carreras se han realizado");
        
        if (totalCarrerasLaborales >=totalCarrerasSabado && totalCarrerasLaborales >= totalCarrerasDomingo) 
        diaGuardado+= " LABORABLES";
        
        if (totalCarrerasSabado >=totalCarrerasLaborales && totalCarrerasSabado >= totalCarrerasDomingo) 
        diaGuardado+= " SABADO";
        
        if (totalCarrerasDomingo >=totalCarrerasLaborales && totalCarrerasDomingo >= totalCarrerasSabado) 
        diaGuardado+= " DOMINGO";

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
