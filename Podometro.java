/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * Sergio Garrués Aizcorbe
 * 
 */
public class Podometro {

    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    private double altura;
    private double longitudZancada;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private double caminatasNoche;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int tiempo;
    private String marca;
    private char sexo;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura / 100;
        sexo = queSexo;

        if (sexo == HOMBRE)   {
            longitudZancada = Math.ceil(queAltura * ZANCADA_HOMBRE) / 100;
        }
        else {
            longitudZancada = Math.floor(queAltura * ZANCADA_MUJER) / 100;
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        switch  (dia)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
            break;
            case SABADO: totalPasosSabado += pasos;
            break;
            case DOMINGO: totalPasosDomingo += pasos;
            break;
        }
        if (horaInicio >= 2100) {
            caminatasNoche ++;
        }
        tiempo += (horaFin / 100 * 60 + horaFin % 100) - (horaInicio / 100 * 60 + horaInicio % 100);
        totalDistanciaSemana = (totalPasosLaborables + totalPasosSabado + totalPasosDomingo) * longitudZancada / 1000;
        totalDistanciaFinSemana = (totalPasosSabado + totalPasosDomingo) * longitudZancada / 1000;
    }   

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del podómetro");
        System.out.println("*********************************");
        System.out.println("Altura: " + altura + " mtos");
        if (sexo == MUJER)   {
            System.out.println("Sexo: " + " MUJER");
        }
        else    {
            System.out.println("Sexo: " + " HOMBRE");
        }
        System.out.println("Longitud zancada: " + longitudZancada + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas");
        System.out.println("*********************************");
        System.out.println("");
        System.out.println("Ditsancia recorrida toda la semana: " + totalDistanciaSemana + " Km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        System.out.println("");
        System.out.println("Nº pasos dias laborables: " + totalPasosLaborables);
        System.out.println("Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("Nº caminatas realizadas a partir de las 21h.: " + (int)caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminado en la semana: " + (tiempo / 60 ) + "h. " + "y " +(tiempo % 60) + "m." );

    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if (totalPasosSabado > totalPasosDomingo && totalPasosSabado > totalPasosLaborables)   {
            return "SABADO";
        }
        else if (totalPasosDomingo > totalPasosSabado && totalPasosDomingo > totalPasosLaborables)  {
            return "DOMINGO";
        }
        else {
            return "LABORABLES";
        }
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
