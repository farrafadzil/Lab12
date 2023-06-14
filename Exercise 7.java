import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataConsumption {
    public static void main(String[] args) {
        String fileName = "rainfall_data.txt";

        try (FileReader fileReader = new FileReader(fileName);
             FileWriter fileWriter = new FileWriter("output.txt")) {

            int numStations = 0;
            int numDistricts = 0;

            fileWriter.write("Station Rainfall Data:\n");

            StringBuilder outputBuilder = new StringBuilder();

            int character;
            boolean skipHeader = true;
            boolean newLine = true;

            while ((character = fileReader.read()) != -1) {
                if (newLine) {
                    outputBuilder.append("Station ID: ");
                }

                if (character == '\n') {
                    newLine = true;
                    continue;
                } else if (character == '\t') {
                    if (skipHeader) {
                        skipHeader = false;
                        outputBuilder.setLength(0);
                        continue;
                    }

                    String data = outputBuilder.toString().trim();
                    outputBuilder.setLength(0);

                    if (data.isEmpty()) {
                        continue;
                    }

                    if (numStations % 2 == 0) {
                        numDistricts++;
                    }

                    if (outputBuilder.length() > 0) {
                        fileWriter.write(outputBuilder.toString() + "\n");
                    }

                    if (data.startsWith("Station ID")) {
                        outputBuilder.append("Station Name: ");
                        continue;
                    }

                    if (numStations % 2 == 0) {
                        fileWriter.write("\nDistrict Name: " + data + "\n");
                    } else {
                        fileWriter.write("Station ID: " + data + "\n");
                    }

                    outputBuilder.append("Rainfall Data:\n");
                    numStations++;
                } else {
                    outputBuilder.append((char) character);
                }

                newLine = false;
            }

            if (outputBuilder.length() > 0) {
                fileWriter.write(outputBuilder.toString() + "\n");
            }

            fileWriter.write("Number of Stations: " + numStations + "\n");
            fileWriter.write("Number of Districts: " + numDistricts + "\n");

            System.out.println("Data has been consumed and written to the output file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

