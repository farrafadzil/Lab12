import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataCreation {
    public static void main(String[] args) {
        int[] rainfallData = {10, 5, 8, 12, 7, 15}; // Example data

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("rainfall_data.dat"))) {
            for (int rainfall : rainfallData) {
                dataOutputStream.writeInt(rainfall);
            }
            System.out.println("Data creation completed.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the data: " + e.getMessage());
        }
    }
}

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
