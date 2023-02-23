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

    public synchronized void rSendAttempt(String procesoId, String prodId) {
        report("El proceso " + procesoId + " intentó enviar el Producto " + prodId);
    };

    public synchronized void rProductoAdded(String buzonId, String productoId) {
        report("El buzón " + buzonId + " recibió el Producto " + productoId);
    };

    public synchronized void rRetriveAttempt(String procesoId) {
        report("El proceso " + procesoId + " intentó recuperar un Producto");
    };

    public synchronized void rProductoRemoved(String buzonId, String productoId) {
        report("El buzón " + buzonId + " entregó el Producto " + productoId);
    };

    public synchronized void rBuzonLleno(String buzonId, String productoId) {
        report("El buzón " + buzonId + " está lleno. Inicia espera para añadir Producto" + productoId);
    }

    public synchronized void rBuzonVacio(String buzonId) {
        report("El buzón " + buzonId + " está vacio. Inicia espera para sacar el siguiente Producto");
    }

    public synchronized void rEndOfExecution(String procesoId) {
        report("El proceso " + procesoId + " imprimio el ultimo Producto. Finaliza su ejecución");
    }

    public synchronized void report(String msg) {
        try {
            bw.newLine();
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
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