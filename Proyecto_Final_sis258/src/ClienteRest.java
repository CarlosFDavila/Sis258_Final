/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * @author jose
 */
public class ClienteRest {

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        boolean respuesta = userValidation("armando","armando");
//        System.out.println("respuesta : "+ respuesta);
//
//    }

    public static String peticionHttpGet(String urlParaVisitar) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL(urlParaVisitar);

        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        // Búferes para leer
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        // Cerrar el BufferedReader
        rd.close();
        // Regresar resultado, pero como cadena, no como StringBuilder
        return resultado.toString();
    }

    /**
     * Metodo para Validar los usuarios
     *
     * @param userName
     * @param password
     * @return
     */
    public static boolean userValidation(String userName, String password) {

        String url = "http://localhost:3000/usuarios";
        String respuesta = "";
        boolean validation = false;
        try {
            respuesta = peticionHttpGet(url);
            //System.out.println("La respuesta es:\n" + respuesta);
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(respuesta).getAsJsonArray();
            System.out.println(gsonArr);
            String user;
            String pass;
            for (JsonElement obj : gsonArr) {
                JsonObject gsonObj = obj.getAsJsonObject();
                user = gsonObj.get("fullname").toString();
                user = user.replace("\"", "");
                pass = gsonObj.get("pass").toString();
                pass = pass.replace("\"", "");
                if(user.equals(userName) && pass.equals(password)){
                    System.out.println("Ingrese!!!");
                    validation = true;
                    return validation;
                }
                System.out.println(user);
            }

        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
        return validation;
    }
}
