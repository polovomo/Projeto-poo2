/*
 * Controller responsável pelas operações relacionadas ao usuário, 
 * como autenticação.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
}

