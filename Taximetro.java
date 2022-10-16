/**
*  La clase modela un taximetro simplificado que recoge estadísticas
*  de las carreras realizadas, tiempo total, distancia...
* 
* @author  Ander Vegas
*/

    public class Taximetro {
    // Constantes y atributos
    private final double BASE_NORMAL = 3.80;
    private final double BASE_AMPLIADA = 4.00;
    private final double KM_NORMAL = 0.75;
    private final double KM_AMPLIADA = 1.10;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
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
    private int maxFacturaNormal;
    private int maxFacturaAmpliada;

    /**
     * Constructor Inicializa el taximetro con la matricula del vehículo. El resto
     * de atributos se ponen a 0
     */
    public Taximetro(String matriculaTaxi) {
        matricula = matriculaTaxi;
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
     * Permite configurar los parámetros de consumo del taximetro (Leer enunciado)
     */
    public void configurar(double coefAerodinamico, int pesoKg) {
        coeficienteAerodinamico = coefAerodinamico;
        pesoVehiculo = pesoKg;
        
        consumoMedio100Kms = (pesoKg * coefAerodinamico) / 100;

    }

    /**
     * Recibe cuatro parámetros que supondremos correctos: kilometros - el nº de
     * kilometros de la carrera dia - nº de día de la semana en que se ha hecho la
     * caminata (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo) horaInicio
     * – hora de inicio de la caminata horaFin – hora de fin de la caminata
     * 
     * A partir de estos parámetros el método debe realizar ciertos cálculos y
     * actualizará el taximetro adecuadamente
     * 
     * (leer enunciado del ejercicio)
     */

    public void registrarCarrera(int kilometros, int dia, int horaInicio, int horaFin) {

        int Duracion = horaFin - horaInicio;

        switch (dia) {
                
            case 1:
                Duracion += tiempo;
                kilometros += totalDistanciaLaborales;
                totalCarrerasLaborales += 1;
               break;
               
            case 2:
                Duracion += tiempo;
                kilometros += totalDistanciaLaborales;
                totalCarrerasLaborales += 1;
               break;
               
            case 3:
                Duracion += tiempo;
                kilometros += totalDistanciaLaborales;
                totalCarrerasLaborales += 1;
               break;  
               
            case 4:
                Duracion += tiempo;
                kilometros += totalDistanciaLaborales;
                totalCarrerasLaborales += 1;
               break;  
               
            case 5:
                Duracion += tiempo;
                kilometros += totalDistanciaLaborales;
                totalCarrerasLaborales += 1;
               break;
            
            case 6:
                Duracion += tiempo;
                kilometros += totalDistanciaFinde;
                totalCarrerasSabado += 1;
              break;
              
            case 7:
                Duracion += tiempo;
                kilometros += totalDistanciaFinde;
                totalCarrerasDomingo += 1;
              break;
               
        }
        
        double maxFacturaNormal = 3.80 + (kilometros * KM_NORMAL);
        double maxFacturaAmpliado = 4.00 + (kilometros * 1.10);
        
        
        
        double importeFacturado = maxFacturaNormal + maxFacturaAmpliado;
        importeFacturado = Math.round(this.importeFacturado) / 100.0;
        
    }

    /**
     * Muestra en pantalla la configuración del taxímetro (matricula, coeficiente
     * aerodinámico, peso y consumo a los 100)
     * 
     * (ver enunciado)
     * 
     */
    public void printConfiguracion() {
        
        System.out.println("Configuración del taxímetro");
        System.out.println("***************************");
        System.out.println("Peso del vehículo en Kg: " + pesoVehiculo);
        System.out.println("Coeficiente aerodinámico: " + coeficienteAerodinamico);
        System.out.println("Consumo medio estimado por cada 100kms: " + consumoMedio100Kms);

    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida, carreras,
     * tiempo total, ....
     * 
     * (leer enunciado)
     * 
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas");
        System.out.println("************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaLaborales + "kms");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinde + "kms");
        System.out.println();
        System.out.println("Nº carreras días laborables: " + totalCarrerasLaborales);
        System.out.println("Nº carreras sábados: " + totalCarrerasSabado);
        System.out.println("Nº carreras domingos: " + totalCarrerasDomingo);
        System.out.println();
        System.out.println("Estimación de litros consumidos: " + ((consumoMedio100Kms / 100) * ( totalDistanciaLaborales + totalDistanciaFinde )));
        System.out.println("Importe facturado: " + importeFacturado + "€");
        System.out.println();

        int tiempo = this.tiempo;
        int horas = tiempo / 100;
        int minutos = (tiempo - horas * 100) % 60;

        System.out.println("Tiempo total en carreras: " + horas + " horas y " + minutos + " minutos");
        System.out.println("Factura máxima tarifa normal: " + maxFacturaNormal);
        System.out.println("Factura máxima tarifa ampliada: " + maxFacturaAmpliada);

    }

    /**
     * Calcula y devuelve un String que representa el nombre del día en el que se
     * han realizado más carreras - "SÁBADO" "DOMINGO" o "LABORABLES"
     */
        public String diaMayorNumeroCarreras() {

        if (totalCarrerasSabado > totalCarrerasDomingo || totalCarrerasSabado > totalCarrerasLaborales) {
            return "SABADO";
        }

        if (totalCarrerasDomingo > totalCarrerasSabado || totalCarrerasDomingo > totalCarrerasLaborales) {
            return "DOMINGO";
        }

        if (totalCarrerasLaborales > totalCarrerasDomingo || totalCarrerasLaborales > totalCarrerasSabado) {
            return "LABORABLES";
        }
         return "";
    }
    

    /**
     * Restablecer los valores iniciales del taximetro Todos los atributos se ponen
     * a cero La matrícula no varía
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
