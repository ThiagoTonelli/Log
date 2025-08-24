/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.log;

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
    }

    @Override
    public void registrar(String operacao, String usuario, LocalDateTime dataHora) {
        try {
            String[] dados = {
                dataHora.format(formatter),
                usuario,
                operacao
            };
            logCsv.escrever(caminhoFicheiro, dados);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever log em CSV: " + e.getMessage(), e);
        }
    }
}
