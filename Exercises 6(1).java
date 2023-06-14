import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RainfallDataWriter {
    public static void main(String[] args) {
        // Sample data for two stations in each district in Melaka
        String[][] stationData = {
                {"S1", "Station 1", "District A", "5", "10", "15", "5", "2", "8"},
                {"S2", "Station 2", "District A", "8", "12", "6", "9", "5", "3"},
                {"S3", "Station 3", "District B", "3", "7", "5", "10", "6", "4"},
                {"S4", "Station 4", "District B", "6", "8", "11", "3", "7", "9"}
        };

        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("rainfall_data.bin"))) {
            // Write the number of stations and districts
            outputStream.writeInt(stationData.length);
            outputStream.writeInt(2); // Assuming 2 districts

            // Write station data
            for (String[] station : stationData) {
                outputStream.writeUTF(station[0]); // Station ID
                outputStream.writeUTF(station[1]); // Station name
                outputStream.writeUTF(station[2]); // District name

                // Write 6-days reading of daily rainfall
                for (int i = 3; i < station.length; i++) {
                    outputStream.writeInt(Integer.parseInt(station[i]));
                }
            }

            System.out.println("Rainfall data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
