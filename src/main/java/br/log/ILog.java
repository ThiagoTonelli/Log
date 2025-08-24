/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.log;

import java.time.LocalDateTime;

/**
 *
 * @author thiag
 */
public interface ILog {
    void registrar(String operacao, String usuario, LocalDateTime dataHora);
}
