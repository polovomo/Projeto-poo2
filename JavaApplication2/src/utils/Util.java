/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrador
 */
public class Util {

    public static Image getIcone() {
        URL caminhoImagem = Util.class.getResource("/images/logo_mini.png");

        ImageIcon icon = new ImageIcon(caminhoImagem);

        return icon.getImage();

    }

    public static String converterDateToString(Date data) {

        //construo o formato que quero transformar o texto
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        String texto = "";

        try {
            //tenta converter a String em Date baseado no formato
            //construido anteriormente
            texto = formato.format(data);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter a data");
        }

        //retorna a data convertida
        return texto;
    }

    public static Date converterStringToDate(String texto) {
        //Construo o formato que quero transformar o texto
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        //crio minha variável data que será o retorno do método
        Date data = null;

        try {
            //tenta converter a String em Date baseado no formato 
            //contruido anteriomente
            data = formato.parse(texto);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao converter a data");
        }
        //retorna a data convertida
        return data;
    }

    public static String calcularHash(String senha) {
        String hashSHA1 = "";
        try {
            //crie uma instância do MessageDigest
            //com o algoritmo SHA1
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");

            //Atualize o digest com os bytes do teste
            sha1.update(senha.getBytes());

            //calcule o hash SHA1
            byte[] digest = sha1.digest();

            //converta o hash de bytes para uma representação hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            hashSHA1 = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo não encontrado !!!");
        }
        return hashSHA1;
    }

    public static File escolherImagem() {
        File arquivo = null;

        //cria um escolhedor de arquivo
        JFileChooser exploradorArquivo = new JFileChooser();

        exploradorArquivo.setDialogTitle("escolha um arquivo");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("imagens", "jpg", "jpeg", "png");

        exploradorArquivo.setFileFilter(filtro);

        exploradorArquivo.setMultiSelectionEnabled(false);

        int resultado = exploradorArquivo.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            arquivo = exploradorArquivo.getSelectedFile();
        }

        return arquivo;
    }

    public static Icon converterFileToIcon(File arquivo) {
        ImageIcon icon = new ImageIcon(arquivo.getAbsolutePath());
        return icon;
    }

    public static ImageIcon redimensionarImagem(Icon icone, int largura, int altura) {
        Image imagemOriginal = ((ImageIcon) icone).getImage();

        Image novaImagem = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

        return new ImageIcon(novaImagem);
    }

    public static byte[] converterIconToBytes(Icon icon) {
        BufferedImage image = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArray);

        } catch (IOException erro) {
            Logger.getLogger(Util.class.getName()).log(
                    Level.SEVERE, null, erro);
        }
        return byteArray.toByteArray();
    }
}
