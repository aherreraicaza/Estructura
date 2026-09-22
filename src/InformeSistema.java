// InformeSistema.java
// Antuan Herrera Icaza - DAM2
// PSP - Tarea 1

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class InformeSistema {

    public static void main(String[] args) {
        System.out.println("PROCESADORES");
        System.out.println("Disponibles JVM: " + Runtime.getRuntime().availableProcessors());
        System.out.println("(son hilos logicos: con SMT no coinciden con los nucleos fisicos)");

        Runtime r = Runtime.getRuntime();
        long usadoAntes = r.totalMemory() - r.freeMemory();

        titulo("MEMORIA - ANTES");
        long total = r.totalMemory();
        long libre = r.freeMemory();
        long usado = total - libre;
        System.out.println("Total reservada: " + total / 1024 / 1024 + " MiB");
        System.out.println("Libre: " + libre / 1024 / 1024 + " MiB");
        System.out.println("En uso: " + usado / 1024 / 1024 + " MiB (" + Math.round(usado * 100.0 / total) + " % de la total)");
        System.out.println("Maxima (-Xmx): " + r.maxMemory() / 1024 / 1024 + " MiB");

        long[] reservado = new long[8 * 1024 * 1024];
        long usadoDespues = r.totalMemory() - r.freeMemory();

        titulo("MEMORIA - DESPUES DE RESERVAR 64 MIB");
        total = r.totalMemory();
        libre = r.freeMemory();
        usado = total - libre;
        System.out.println("Total reservada: " + total / 1024 / 1024 + " MiB");
        System.out.println("Libre: " + libre / 1024 / 1024 + " MiB");
        System.out.println("En uso: " + usado / 1024 / 1024 + " MiB (" + Math.round(usado * 100.0 / total) + " % de la total)");
        System.out.println("Maxima (-Xmx): " + r.maxMemory() / 1024 / 1024 + " MiB");
        System.out.println("Incremento en uso: " + (usadoDespues - usadoAntes) / 1024 / 1024 + " MiB");
        System.out.println("(el array sigue en memoria: reservado[0] = " + reservado[0] + ")");

        titulo("SISTEMA");
        System.out.println("os.name: " + System.getProperty("os.name"));
        String separador = System.getProperty("file.separator");
        System.out.println("file.separator: \"" + separador + "\"");
        System.out.println("Ruta construida con las propiedades:");
        System.out.println(System.getProperty("user.home") + separador + "psp" + separador + "informe.txt");

        String[] prefijos;
        if (args.length > 0) {
            prefijos = args;
        } else {
            prefijos = new String[] { "os.", "user.", "java.version" };
        }

        String texto = "";
        for (String prefijo : prefijos) {
            texto = texto + prefijo + " ";
        }
        titulo("PROPIEDADES QUE EMPIEZAN POR " + texto);

        Properties props = System.getProperties();
        List<String> claves = new ArrayList<>();
        for (Object k : props.keySet()) {
            String clave = k.toString();
            for (String prefijo : prefijos) {
                if (clave.startsWith(prefijo)) {
                    claves.add(clave);
                    break;
                }
            }
        }
        Collections.sort(claves);
        for (String clave : claves) {
            System.out.println(clave + " = " + props.getProperty(clave));
        }

        titulo("PROCESO EN ESPERA");
        System.out.println("Buscame desde otra terminal con:");
        System.out.println("ps -ef | grep InformeSistema");
        System.out.print("Pulsa INTRO para terminar... ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("Fin del programa.");
    }

    private static void titulo(String t) {
        System.out.println();
        System.out.println(t);
        System.out.println("==================================================");
    }
}