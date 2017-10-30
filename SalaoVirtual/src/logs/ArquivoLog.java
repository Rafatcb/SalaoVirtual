/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author faad2
 */
public class ArquivoLog {
    File arquivo;
    FileReader fileReader;
    BufferedReader buffRead;
    FileWriter fileWrite;
    BufferedWriter buffWrite;
    public ArquivoLog(String erros){
        EscreverLog(erros);
    }
    private void EscreverLog(String erros){
        try {
            arquivo = new File("Exceptions.log");
            fileReader = new FileReader(arquivo);
            buffRead = new BufferedReader(fileReader);
            Vector texto = new Vector();
            while(buffRead.ready()){
                texto.add(buffRead.readLine());
            }
            fileWrite = new FileWriter(arquivo);
            buffWrite = new BufferedWriter(fileWrite);
            for (Object textoA : texto) {
                buffWrite.write(textoA.toString());
                buffWrite.newLine();
            }
            buffWrite.write(erros);
            buffRead.close();
            buffWrite.close();
        } catch (FileNotFoundException ex) {
            try {
                arquivo.createNewFile();
                EscreverLog(erros);
            } catch (IOException ex1) {
                System.exit(0);
            }
        } catch (IOException er){
            System.exit(0);
        }
        
    }
}