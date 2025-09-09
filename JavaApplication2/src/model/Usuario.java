/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.swing.Icon;

/**
 *
 * @author Administrador
 */
public class Usuario {

    private int pkUsuario;
    private String nome;
    private String email;
    private String senha;
    private Date dataNascimento;
    private boolean ativo;
    private Icon imagem;

    public void setImagem(Icon imagem) {
        this.imagem = imagem;
    }

    public Icon getImagem() {
        return imagem;
    }

    public void setPkUsuario(int pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getPkUsuario() {
        return pkUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getAtivoString() {
        if (ativo) {
            return "Ativo";
        } else {
            return "Inativo";
        }
    }

}
