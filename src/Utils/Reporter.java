package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reporter {

    private BufferedWriter bw;
    private boolean lrBuzonFinalVacio;

    public Reporter(String fileName) {
        lrBuzonFinalVacio = false;
        fileName = fileName.replace(":", "-");
        fileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
        Path crrPath = Paths.get(System.getProperty("user.dir"));
        Path rPath = Paths.get(crrPath.toString(), "Reports", fileName + ".log");
        File rFile = new File(rPath.toString());
        File rDir = new File(rPath.getParent().toString());
        rDir.mkdirs();
        try {
            rFile.createNewFile();
            FileWriter fw = new FileWriter(rFile, StandardCharsets.UTF_8);
            bw = new BufferedWriter(fw);

        } catch (IOException e) {
            System.out.println("No fue posible crear el archivo de reporte de ejecución -" + e.getMessage());
        }
    }

    public synchronized void rSendAttempt(String id, String msg) {
        report("El proceso " + id + " intentó enviar el mensaje " + msg);
    };

    public synchronized void rMessageAdded(String id, String msg) {
        report("El buzón " + id + " recibió el mensaje " + msg);
    };

    public synchronized void rRetriveAttempt(String id) {
        report("El proceso " + id + " intentó recuperar un mensaje");
    };

    public synchronized void rMessageRemoved(String id, String msg) {
        report("El buzón " + id + " entregó el mensaje " + msg);
    };

    public synchronized void rBuzonLleno(String id, String msg) {
        report("El buzón " + id + " está lleno. Inicia espera para añadir " + msg);
    }

    public synchronized void rBuzonVacio(String id) {
        report("El buzón " + id + " está vacio. Inicia espera para sacar el siguiente mensaje");
    }

    public synchronized void rEndOfExecution(String id) {
        report("El proceso " + id + " recibió el mensaje 'FIN'. Finaliza su ejecución");
    }

    public synchronized void rBuzonFinalVacio() {
        if(!lrBuzonFinalVacio) {
            report("El buzón final está vacio. Inicia espera para sacar el siguiente mensaje");
            lrBuzonFinalVacio = true;
        }
    }

    public synchronized void report(String msg) {
        lrBuzonFinalVacio = false;
        try {
            bw.newLine();
            bw.write(msg);
        } catch (IOException e) {
            System.out.println("No fue posible escribir en el archivo de reporte de ejecución");
        }
    }

    public synchronized void close() {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}