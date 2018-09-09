package br.embrapa.produtor.auxiliar;


import br.embrapa.produtor.interfaces.ExportToFile;
import br.embrapa.produtor.models.Solicitacao;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ExportCSV implements ExportToFile {

    //Delimiters which has to be in the CSV file
    private static final String DELIMITADOR = ",";
    private static final String DELIMITADOR_LINHA = "\n";

    //File header
    private static final String HEADER = "PRODUTOR,DATA,FOTO,CULTURA,DOENÇA,PESQUISADOR";


    public File exportCsv(Iterable<Solicitacao> list){

        try
        {
            File file = File.createTempFile("temp",".csv");
            FileWriter fileWriter = new FileWriter(file);

            //Adding the header
            fileWriter.append(HEADER);
            //New Line after the header
            fileWriter.append(DELIMITADOR_LINHA);


            for (Solicitacao solicitacao : list) {
                try {
                    fileWriter.append(solicitacao.getId().toString());
                    fileWriter.append(DELIMITADOR);
                    fileWriter.append(solicitacao.getCidade());
                    fileWriter.append(DELIMITADOR);
                    fileWriter.append(solicitacao.getCultura().getNome());
                    fileWriter.append(DELIMITADOR);
                    fileWriter.append(String.valueOf(solicitacao.getProdutor().getNome()));
                    fileWriter.append(DELIMITADOR_LINHA);
                } catch (IOException e) {
                    System.out.println(e + " ERRO!");
                }

            }

            fileWriter.flush();
            fileWriter.close();

            return file;
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            return null;
        }

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
