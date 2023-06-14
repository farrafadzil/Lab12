import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class RainfallDataReader {
    public static void main(String[] args) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream("rainfall_data.bin"))) {
            // Read the number of stations and districts
            int numStations = inputStream.readInt();
            int numDistricts = inputStream.readInt();

            System.out.println("Number of stations: " + numStations);
            System.out.println("Number of districts: " + numDistricts);

            // Read station data and compute average rainfall
            for (int i = 0; i < numStations; i++) {
                String stationId = inputStream.readUTF();
                String stationName = inputStream.readUTF();
                String districtName = inputStream.readUTF();

                System.out.println("Station ID: " + stationId);
                System.out.println("Station Name: " + stationName);
                System.out.println("District Name: " + districtName);

                int totalRainfall = 0;
                for (int j = 0; j < 6; j++) {
                    int rainfall = inputStream.readInt();
                    totalRainfall += rainfall;
                    System.out.println("Day " + (j + 1) + " Rainfall: " + rainfall);
                }

                double averageRainfall = totalRainfall / 6.0;
                System.out.println("Average Rainfall: " + averageRainfall);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
