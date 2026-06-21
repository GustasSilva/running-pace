
package aplicativo;

import aplicativo.cadastro;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.security.Permission;

import javax.sound.sampled.*;

public class launcher extends JFrame implements ActionListener, FocusListener, KeyListener {


    JTextField textField; // Adiciona um JTextField
    Clip clip; // Adiciona um objeto Clip

    public launcher() {
        setTitle("Running Pace");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("img\\logo.png"); // Colocar o caminho relativo da imagem
        Image image = imageIcon.getImage(); // transforma o ícone em uma imagem
        Image newimg = image.getScaledInstance(1366, 768,  java.awt.Image.SCALE_SMOOTH); // redimensiona a imagem para o tamanho da janela
        ImageIcon imageIconNew = new ImageIcon(newimg);  // transforma a imagem redimensionada em um ícone

        setContentPane(new JLabel(imageIconNew));
        setLayout(null); // Define o layout como null para permitir o posicionamento absoluto

       

        JLabel label = new JLabel("APERTE QUALQUER TECLA PARA COMEÇAR!!!");
        label.setBounds(425, 320, 700, 50); // Define a posição e o tamanho do JLabel
        Font font = new Font("Impact", Font.PLAIN, 30);// Define a fonte e o tamanho da fonte
        label.setFont(font);
        label.setForeground(Color.RED);// Define a cor do texto
        add(label);

        Timer timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {// A cada chamada, alterna a visibilidade do texto
            label.setVisible(!label.isVisible());
            }
        });

        timer.start();

        playSound("audio\\04.-Speed-Car.wav"); // Colocar o caminho relativo do audio

        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Cria uma nova instância da OutraJanela
                cadastro outraTela = new cadastro();
                // Torna a outra tela visível
                outraTela.setVisible(true);
                // Fecha a janela atual
                dispose();
                // parar a musica
                clip.stop();
            }
        });
        setFocusable(true);
        setVisible(true);
        
        addWindowFocusListener(new WindowAdapter() {
            public void windowLostFocus(WindowEvent e) {
                setState(JFrame.ICONIFIED); // Minimiza a janela quando ela perde o foco
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        // Código a ser executado quando uma ação ocorrer
    }

    public void focusGained(FocusEvent e) {
        // Código a ser executado quando o foco é ganho
    }

    public void focusLost(FocusEvent e) {
        // Código a ser executado quando o foco é perdido
    }

    public static void main(String[] args) {
    launcher launcher = new launcher();
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
                try {
                    Toolkit.getDefaultToolkit().getSystemClipboard()
                          .setContents(new StringSelection("Não foi Possivel Printar"), null);
              
                    System.out.println(((String) Toolkit.getDefaultToolkit()
                          .getSystemClipboard().getData(DataFlavor.stringFlavor)));
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }

                // Cria um JDialog personalizado
                JDialog dialog = new JDialog();
                dialog.setModal(true);

                // Define o tamanho do diálogo para o tamanho da tela
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                dialog.setSize(screenSize.width, screenSize.height);

                // Cria o JOptionPane
                String message = "<html><body style='width: 100%; text-align: center; font-size: 20px;'>"
                                            + "Olá! 👋<br>"
                                            + "Percebemos que você tentou tirar um print screen no nosso aplicativo. Infelizmente, essa funcionalidade não está disponível. Isso ocorre porque valorizamos a privacidade e a segurança dos dados dos nossos usuários. A restrição de screenshots ajuda a proteger as informações sensíveis exibidas no aplicativo contra acesso não autorizado."
                                +"</body></html>";
                JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
                optionPane.setPreferredSize(new Dimension(screenSize.width - 100, screenSize.height - 100));

                // Adiciona o JOptionPane ao JDialog
                dialog.setContentPane(optionPane);
                dialog.pack();

                // Mostra o diálogo
                dialog.setVisible(true);

                return true; // Isso bloqueia o evento de ser passado adiante
            }
            return false;
        }
    });
}


    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Erro ao reproduzir o som.");
            ex.printStackTrace();
        }
    }
    
    
    
    // Métodos do KeyListener
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        // Código a ser executado quando uma tecla é pressionada
    }

    public void keyReleased(KeyEvent e) {
        // Código a ser executado quando uma tecla é liberada
    }

    
}
