/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author thiag
 */
public class LogCsvAdapter implements ILog {
    private final LogCsv logCsv;
    private final String caminhoFicheiro;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogCsvAdapter(String caminhoFicheiro) {
        this.logCsv = new LogCsv();
        this.caminhoFicheiro = caminhoFicheiro;
        inicializarFicheiro();
    }

    private void inicializarFicheiro() {
        try {
            File ficheiro = new File(caminhoFicheiro);
            
            if (!ficheiro.exists()) {
                if (ficheiro.getParentFile() != null) {
                    ficheiro.getParentFile().mkdirs();
                }
                try (FileWriter writer = new FileWriter(ficheiro)) {
                    writer.append("MENSAGEM_FORMATADA;USUARIO;DATA_HORA_ISO\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar ficheiro de log CSV: " + e.getMessage(), e);
        }
    }

    @Override
    public void registrar(String mensagem, String usuario, LocalDateTime dataHora, String id_c, String ciclo) {
        try {
            String[] dados = {
                id_c,
                ciclo,
                usuario,
                dataHora.toString(),
                mensagem   
            };
            logCsv.escrever(caminhoFicheiro, dados);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever log em CSV: " + e.getMessage(), e);
        }
    }
}
