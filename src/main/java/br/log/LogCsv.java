/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.log;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author thiag
 */
public class LogCsv {
    public void escrever(String caminhoFicheiro, String[] dados) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoFicheiro, true)) { // 'true' para append
            writer.append(String.join(";", dados));
            writer.append("\n");
        }
    }
}
