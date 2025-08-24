/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.log;

/**
 *
 * @author thiag
 */
public class LogFactory {
    public static ILog criarLog(String tipo, String caminhoFicheiro) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("O tipo de log não pode ser nulo ou vazio.");
        }

        switch (tipo.toLowerCase()) {
            case "csv":
                return new LogCsvAdapter(caminhoFicheiro);
            case "json":
                return new LogJsonAdapter(caminhoFicheiro);
            default:
                throw new IllegalArgumentException("Tipo de log desconhecido: " + tipo);
        }
    }
}
