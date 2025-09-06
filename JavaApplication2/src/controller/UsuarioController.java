/*
 * Controller responsável pelas operações relacionadas ao usuário, 
 * como autenticação.
 */
package controller;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioController {

    /**
     * Método para autenticar um usuário com base no email e senha fornecidos.
     * 
     * @param usuario Email do usuário.
     * @param senha Senha do usuário.
     * @return true se o usuário for autenticado com sucesso, false caso contrário.
     */
    public boolean autenticardo(String usuario, String senha) {
        // Comando SQL para verificar se existe um usuário ativo com o email e senha informados
        String sql = "SELECT * from TBUSUARIO "
                   + "WHERE email = ? and senha = ? "
                   + "and ativo = true";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            // Prepara o comando SQL para execução
            comando = gerenciador.prepararComando(sql);

            // Define os parâmetros do comando SQL para evitar SQL Injection
            comando.setString(1, usuario);
            comando.setString(2, senha);

            // Executa a consulta no banco de dados
            resultado = comando.executeQuery();

            // Verifica se retornou algum resultado (usuário encontrado e ativo)
            if (resultado.next()) {
                return true; // Autenticação bem-sucedida
            }
        } catch (SQLException e) {
            // Em caso de erro, exibe uma mensagem para o usuário
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Fecha recursos de banco de dados para evitar vazamentos
            gerenciador.fecharConexao(comando, resultado);
        }

        // Se não encontrou o usuário ou ocorreu algum problema, retorna false
        return false;
    }
      public boolean inserir(Usuario usu) {
        // Comando SQL para verificar se existe um usuário ativo com o email e senha informados
        String sql =  "INSERT INTO TBUSUARIO (nome,email,senha,datanasc, ativo)VALUES(?,?,?,?,?)";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            // Prepara o comando SQL para execução
            comando = gerenciador.prepararComando(sql);

            // Define os parâmetros do comando SQL para evitar SQL Injection
            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setString(3, usu.getSenha());
            comando.setDate(4,new java.sql.Date(usu.getDataNascimento().getTime()));
            comando.setBoolean(5, usu.isAtivo());
            
          

            // Executa a consulta no banco de dados
            comando.executeQuery();
            return true;
           
        } catch (SQLException e) {
            // Em caso de erro, exibe uma mensagem para o usuário
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Fecha recursos de banco de dados para evitar vazamentos
            gerenciador.fecharConexao(comando);
        }

        // Se não encontrou o usuário ou ocorreu algum problema, retorna false
        return false;
    }



    public List<Usuario> consultar(int opcaoFiltro, String filtro) {
        // Comando SQL para verificar se existe um usuário ativo com o email e senha informados
        String sql = "SELECT * from TBUSUARIO WHERE ";
        
        
        if(opcaoFiltro == 0){
        
        sql += "pkUsuario = " + filtro;
        
        }else if (opcaoFiltro == 1 ){
        
        sql += "nome LIKE '%" + filtro + "%'";
        }
    
        else if (opcaoFiltro == 2 ){
        sql += "email LIKE '%" + filtro + "%'"; 
        }
        
        else if (opcaoFiltro == 3){
        sql += "ativo = " + filtro ;
        }
        
        GerenciadorConexao gerenciador = new GerenciadorConexao();

        //
        PreparedStatement comando = null;
        ResultSet resultado = null;

        //
        List<Usuario> lista = new ArrayList<>();

        try {
            // Prepara o comando SQL para execução
            comando = gerenciador.prepararComando(sql);
         
            // Executa a consulta no banco de dados
            resultado = comando.executeQuery();

            // Verifica se retornou algum resultado (usuário encontrado e ativo)
            while (resultado.next()) {
              Usuario usu = new Usuario ();
              
              usu.setPkUsuario(resultado.getInt("pkUsuario"));
              
              usu.setNome(resultado.getString("nome"));
              
              usu.setEmail(resultado.getString("email"));
              
              usu.setDataNascimento(resultado.getDate("dataNasc"));
              
              usu.setSenha(resultado.getString("senha"));// HAhSS
              
               usu.setAtivo(resultado.getBoolean("ativo"));
               
               
               lista.add(usu);
            }
        } catch (SQLException e) {
            // Em caso de erro, exibe uma mensagem para o usuário
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Fecha recursos de banco de dados para evitar vazamentos
            gerenciador.fecharConexao(comando, resultado);
        }

        return lista;
    }
  public boolean alterar(Usuario usu) {
        // Comando SQL para verificar se existe um usuário ativo com o email e senha informados
        String sql =  "UPDATE TBUSUARIO SET "
                +"nome = ?, email = ? ,datanasc = ?, ativo  = ? ";
        
               if(usu.getSenha() !=null){     
                sql     += ",senha = ?";
        }
               
               sql +="WHERE pkUsuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            // Prepara o comando SQL para execução
            comando = gerenciador.prepararComando(sql);

            // Define os parâmetros do comando SQL para evitar SQL Injection
            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
           
            comando.setDate(3,new java.sql.Date(usu.getDataNascimento().getTime()));
            comando.setBoolean(4, usu.isAtivo());
            
            if(usu.getSenha() != null){
                 comando.setString(5, usu.getSenha());
                  comando.setInt(6, usu.getPkUsuario());
            }else{
                 comando.setInt(5, usu.getPkUsuario());
            }
          

            // Executa a consulta no banco de dados
            comando.executeQuery();
            return true;
           
        } catch (SQLException e) {
            // Em caso de erro, exibe uma mensagem para o usuário
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Fecha recursos de banco de dados para evitar vazamentos
            gerenciador.fecharConexao(comando);
        }

        // Se não encontrou o usuário ou ocorreu algum problema, retorna false
        return false;
    }

 
}
    