/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.log;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thiag
 */
public class LogJsonAdapter implements ILog {
    private final LogJson logJson;
    private final String caminhoFicheiro;
    private final Gson gson; 

    public LogJsonAdapter(String caminhoFicheiro) {
        this.logJson = new LogJson();
        this.caminhoFicheiro = caminhoFicheiro;
        this.gson = new Gson(); 
        inicializarFicheiro();
    }
    
    private void inicializarFicheiro() {
        try {
            File ficheiro = new File(caminhoFicheiro);
            if (!ficheiro.exists()) {
                if (ficheiro.getParentFile() != null) {
                    ficheiro.getParentFile().mkdirs();
                }
                ficheiro.createNewFile(); 
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar ficheiro de log JSON: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void registrar(String operacao, String usuario, LocalDateTime dataHora) {
        try {
            Map<String, String> logEntry = new HashMap<>();
            logEntry.put("dataHora", dataHora.toString());
            logEntry.put("usuario", usuario);
            logEntry.put("operacao", operacao);

            String jsonString = gson.toJson(logEntry);

            logJson.escrever(caminhoFicheiro, jsonString);
            
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever log em JSON com Gson: " + e.getMessage(), e);
        }
    }
}
