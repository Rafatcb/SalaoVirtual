/*
 * Classe referente à alteração de objetos em arquivo
 */
package salaovirtual.access;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import salaovirtual.Produto;
import salaovirtual.Servico;

/**
 * Classe referente à alteração de objetos em arquivo
 * 
 * @author Rafael Tavares
 */
public class Alteracao {
    /**
     * Alterar um serviço existente
     * @param novo - o serviço novo
     */
    public void alterarServico (Servico novo) {
        try {
            Path caminho;
            caminho = Paths.get("","Servico.csv");
            List<String> conteudoArquivo = new ArrayList<>(Files.readAllLines(caminho, StandardCharsets.UTF_8));
            String[] linha;
            for (int i = 0; i < conteudoArquivo.size(); i++) {
                linha = conteudoArquivo.get(i).split(";");
                if (Integer.parseInt(linha[0]) == novo.getCodigo()) {
                    conteudoArquivo.set(i, novo.toString());
                    break;
                }
            }
            Files.write(caminho, conteudoArquivo, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            //Logger.getLogger(Alteracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Alterar um produto existente
     * @param novo - o produto novo
     */
    public void alterarProduto (Produto novo) {
        try {
            Path caminho;
            caminho = Paths.get("","Produto.csv");
            List<String> conteudoArquivo = new ArrayList<>(Files.readAllLines(caminho, StandardCharsets.UTF_8));
            String[] linha;
            for (int i = 0; i < conteudoArquivo.size(); i++) {
                linha = conteudoArquivo.get(i).split(";");
                if (Integer.parseInt(linha[0]) == novo.getCodigo()) {
                    conteudoArquivo.set(i, novo.toString());
                    break;
                }
            }
            Files.write(caminho, conteudoArquivo, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            //Logger.getLogger(Alteracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
