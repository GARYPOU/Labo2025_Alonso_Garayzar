package P7.Mensajeria;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
    private Properties propiedades;
    private String archivo;

    public Configuracion(String archivoPropiedades) {
        this.archivo = archivoPropiedades;
        this.propiedades = new Properties();
        cargarConfiguracion();
    }

    private void cargarConfiguracion() {
        try {

            InputStream input = getClass().getClassLoader().getResourceAsStream(archivo);

            if (input == null) {
                input = new FileInputStream(archivo);
            }

            propiedades.load(input);
            input.close();

        } catch (IOException ex) {
            System.err.println("No se pudo cargar el archivo de configuración: " + archivo);
            System.err.println("Asegúrate de que el archivo '" + archivo + "' exista en el directorio del proyecto.");
            System.err.println("Directorio actual: " + new File(".").getAbsolutePath());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public String getProperty(String key) {
        String value = propiedades.getProperty(key);
        if (value == null) {
            System.err.println("ERROR: Propiedad '" + key + "' no encontrada en " + archivo);
            System.exit(1);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        return propiedades.getProperty(key, defaultValue);
    }

    public int getIntProperty(String key) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException e) {
            System.err.println("ERROR: La propiedad '" + key + "' debe ser un número entero en " + archivo);
            System.exit(1);
            return -1;
        }
    }

    public int getIntProperty(String key, int defaultValue) {
        try {
            String value = propiedades.getProperty(key);
            if (value == null) return defaultValue;
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: La propiedad '" + key + "' debe ser un número entero en " + archivo);
            System.exit(1);
            return -1;
        }
    }
}