package br.embrapa.produtor.auxiliar;


import br.embrapa.produtor.interfaces.ExportToFile;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExportCSV implements ExportToFile {

    //Delimiters which has to be in the CSV file
    private static final String DELIMITADOR = ",";
    private static final String DELIMITADOR_LINHA = "\n";

    //File header
    private static final String HEADER = "PRODUTOR_ID,PRODUTOR,DATA,FOTO,CULTURA,DOENÇA,PESQUISADOR_ID,PESQUISADOR";


    public File exportCsv(List<Object[]> list) {

        try {
            File file = File.createTempFile("temp", ".csv");
            FileWriter fileWriter = new FileWriter(file);

            //Adding the header
            fileWriter.append(HEADER);
            //New Line after the header
            fileWriter.append(DELIMITADOR_LINHA);

            for (Object[] row : list) {

                for (int i = 0; i < row.length; i++) {

                    try {
                        fileWriter.append(row[i].toString());
                        fileWriter.append(DELIMITADOR);
                    } catch (IOException e) {
                        System.out.println(e + " ERRO! Não foi possível inserir uma nova linha.");
                    }
                }

                try {
                    fileWriter.append(DELIMITADOR_LINHA);
                } catch (IOException e) {

                }
            }

            fileWriter.flush();
            fileWriter.close();

            return file;

        } catch (IOException e) {
            System.out.println("ERRO! Não foi possível gravar no arquivo...");
        }

        return null;
    }






        @Override
    public BufferedReader file() {

        String toWrite = "Hello";
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("test", ".tmp");
            FileWriter writer = new FileWriter(tmpFile);
            writer.write(toWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(tmpFile));
            assertEquals(toWrite, reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return reader;
    }
}
