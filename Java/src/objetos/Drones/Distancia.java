package objetos.Drones;

public class Distancia {
    static double latitud=Math.toRadians(-34.573195);
    static double longitud=Math.toRadians(-58.504111);




    public static double calcular(double longitudDestino, double latitudDestino) {
        double lat1Rad = latitud;
        double lon1Rad = longitud;
        double lat2Rad = Math.toRadians(latitudDestino);
        double lon2Rad = Math.toRadians(longitudDestino);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radioTierraKm = 6371;
        return radioTierraKm * c;
    }
}
