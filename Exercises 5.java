import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataConsumption {
    public static void main(String[] args) {
        int[] rainfallData = new int[6];

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("rainfall_data.dat"))) {
            for (int i = 0; i < rainfallData.length; i++) {
                rainfallData[i] = dataInputStream.readInt();
            }

            int totalRainfall = 0;
            for (int rainfall : rainfallData) {
                totalRainfall += rainfall;
            }
            double averageRainfall = (double) totalRainfall / rainfallData.length;

            System.out.println("Rainfall data:");
            for (int day = 1; day <= rainfallData.length; day++) {
                System.out.println("Day " + day + ": " + rainfallData[day - 1]);
            }
            System.out.println("Average rainfall: " + averageRainfall);
        } catch (IOException e) {
            System.out.println("An error occurred while consuming the data: " + e.getMessage());
        }
    }
}
