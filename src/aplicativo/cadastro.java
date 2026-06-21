package aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.Statement;
import java.security.Permission;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class cadastro extends JFrame implements ActionListener, FocusListener, KeyListener {

    //Variavel Cadastro
    private JPanel painelCadastro;
    private JLabel lnome, lequipe, lnum_carro; // cria as label
    private TextField tnome, tequipe, tnum_carro; // cria as caixa de texto
    private Button bNovo, bSalva,bcontinuar; // cria os botoes
    private Vector vCadastro;
    private float minhaStringVazia = 00;
    public String piloto;

    //Variavel Cronometro
    
    
    //tela cadastro
    public cadastro() {
        
        setTitle("Running Pace");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        ImageIcon imageIcon = new ImageIcon("img\\running.jpg"); // Colocar o caminho relativo da imagem
        Image image = imageIcon.getImage(); // transforma o ícone em uma imagem
        Image newimg = image.getScaledInstance(1366, 768,  java.awt.Image.SCALE_SMOOTH); // redimensiona a imagem para o tamanho da janela
        ImageIcon imageIconNew = new ImageIcon(newimg);  // transforma a imagem redimensionada em um ícone

        setContentPane(new JLabel(imageIconNew));
        setLayout(null);

        // Criação dos painéis
        painelCadastro = new JPanel();
        painelCadastro.setBounds(363, 143, 620, 450); // Define a posição e o tamanho do painel
        painelCadastro.setBackground(new Color(0, 128, 128));
        painelCadastro.setLayout(null);
        add(painelCadastro);

        Font fcadastro = new Font("Arial", Font.BOLD, 20); // crias fontes
        Font fpreencher = new Font("Arial", Font.PLAIN, 20);

        vCadastro = new Vector();

        lnome = new JLabel("NOME DO PILOTO:");
        lnome.setFont(fcadastro);
        lnome.setBounds(10, 10, 610, 30); // Ajuste esses valores conforme necessário
        painelCadastro.add(lnome);
        
        lequipe = new JLabel("NOME DA EQUIPE:");
        lequipe.setFont(fcadastro);
        lequipe.setBounds(10, 90, 610, 30); // Ajuste esses valores conforme necessário
        painelCadastro.add(lequipe);
        
        lnum_carro = new JLabel("NUMERO DO CARRO:");
        lnum_carro.setFont(fcadastro);
        lnum_carro.setBounds(10, 170, 610, 30); // Ajuste esses valores conforme necessário
        painelCadastro.add(lnum_carro);

        tnome = new TextField(10); 
        tnome.setBounds(10,40,590,30);
        tnome.setFont(fpreencher);
        painelCadastro.add(tnome);

        tequipe = new TextField(10);
        tequipe.setBounds(10,120,590,30);
        tequipe.setFont(fpreencher);
        painelCadastro.add(tequipe);

        tnum_carro = new TextField(10);
        tnum_carro.setBounds(10, 200, 590, 30);
        tnum_carro.setFont(fpreencher);
        painelCadastro.add(tnum_carro);

        bNovo = new Button("NOVO/LIMPAR");
		bNovo.addActionListener(this);
        bNovo.setBounds(107,280,150,30);
        bNovo.setFont(fcadastro);
        painelCadastro.add(bNovo);
		
        
        bSalva = new Button("SALVAR");
		bSalva.addActionListener(this);
        bSalva.setBounds(364,280,150,30);
        bSalva.setFont(fcadastro);
        painelCadastro.add(bSalva);
		
        
        bcontinuar = new Button("CONTINUAR");
        bcontinuar.addActionListener(this);
        bcontinuar.setBounds(233,340,150,30);
        bcontinuar.setEnabled(false);
        bcontinuar.setFont(fcadastro);
        painelCadastro.add(bcontinuar);

        

    }
    
    
    
//_______________________________________________________________________________________________________________
    //METODOS ACESSORES (Permitem adicionar valores para os campos)
    public void setNome_piloto(String nome_piloto){
		tnome.setText(nome_piloto);
	}

    public void setNome_equipe(String nome_equipe){
        tequipe.setText(nome_equipe);
    }

    public void setNumero_carro(String numero_carro){
        tnum_carro.setText(numero_carro);
    }

    //METODOS MUTADORES (Permitem receber o conteúdo dos campos)
    public String getNome_piloto(){
		return tnome.getText();
	}

    public String getNome_equipe(){
        return tequipe.getText();
    }

    public String getNumero_carro(){
        return tnum_carro.getText();
    }

    

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button b=(Button)e.getSource();
		if (b==bNovo)           this.botaoNovo();
		else if (b==bSalva)	    this.botaoSalva();
		else if (b==bcontinuar) this.botaoContinuar();
        
    }


    //--------------------------------------------------------------------------------------------------


    Connection conecta()
{
    String url="jdbc:mysql://localhost:3306/runningpace";
    Connection con;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection(url,"root","");
        return con;
    }
    catch(ClassNotFoundException cnf){
        System.out.println("Houve um erro no DRIVER: classnotfoundexcepition-"+cnf);
        return null;
    }
    catch(SQLException sql){
        System.out.println("Houve um erro no SQL:sqlexception sql-"+sql);
        return null;
    }
}

    //--------------------------------------------------------------------------------------------------

    public void botaoSalva() {
        piloto = getNome_piloto();
        bcontinuar.setEnabled(true);
        Connection con = conecta();
        try {
            // Cria um Statement para acesso ao banco
            java.sql.Statement st = con.createStatement();
    
            // Executa um comando SQL para inserir os dados na tabela
            int resultado = st.executeUpdate("INSERT INTO placar (nome_piloto, nome_equipe, num_carro, id_temp) VALUES ('" + getNome_piloto() + "', '" + getNome_equipe() + "', '" + getNumero_carro() + "', '" + minhaStringVazia +"')");
            st.close();
            con.close();
    
            System.out.println("Registro salvo.");
            this.limpaDados();
            // ...
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bSalva.setEnabled(false);
    }
        
    public void limpaDados() {
        this.setNome_piloto("");   // move vazio para os campos de texto
        this.setNome_equipe("");
        this.setNumero_carro("");
    }
    
    public void botaoNovo() {
        this.limpaDados();

    }
    

    public void botaoContinuar() {
        cronometro outraTela1 = new cronometro();
                // Torna a outra tela visível
                outraTela1.setVisible(true);
                // Fecha a janela atual
                dispose();
    }

    public void obterDadosContatos(int pos) {
		//cria um objeto para receber o conteudo na posicao do vetor
		cadastro cadastroAtual=(cadastro)vCadastro.elementAt(pos);
		//Utiliza o metodo getCodigo do objeto e devolve para o método setCodigo do componente
		this.setNome_piloto(cadastroAtual.getNome_piloto());
		this.setNome_equipe(cadastroAtual.getNome_equipe());
		this.setNumero_carro(cadastroAtual.getNumero_carro());

	}

//______________________________________________________________________________________________________________________________________________________________________

    //tela cronometro
    public class cronometro extends JFrame implements ActionListener {
        
        private Timer timer;
        private int segundos1 = 0;
        private int minutos1 = 0;
        private int milisegundos1 = 0;
        private JLabel segundos;
        private JLabel minutos;
        private JLabel milisegundos; 
        private JLabel volta1,volta2;
        private JPanel painelCronometro;
        private Button bComecar, bTerminar, bFechar,bterminarcorrida,bjogadores;
        private int csegundos,cminutos,ctotal,cmilisegundos;
        float ctotalAcumulado = 0;
        DecimalFormat dftempo = new DecimalFormat("0.00");
        int contador =0;

        public cronometro() {
            setTitle("Running Pace");
            setSize(1366, 768);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); 
            setVisible(false);
    
            ImageIcon imageIcon = new ImageIcon("img\\fundocronometro.png"); 
            Image image = imageIcon.getImage(); 
            Image newimg = image.getScaledInstance(1366, 768,  java.awt.Image.SCALE_SMOOTH); 
            ImageIcon imageIconNew = new ImageIcon(newimg);  
    
            setContentPane(new JLabel(imageIconNew));
            setLayout(null);
    
            JPanel painelCronometro = new JPanel();
            painelCronometro.setBounds(363, 143, 620, 450); 
            painelCronometro.setBackground(new Color(0, 0, 0));
            painelCronometro.setLayout(null);
            add(painelCronometro);
    
            Font font = new Font("Arial", Font.BOLD, 20);
            Font cronometro = new Font("Arial", Font.BOLD, 60 );
            Font cronometro2 = new Font("Arial", Font.BOLD, 40 );
    
            

            minutos = new JLabel("00");
            minutos.setBounds(210, 100, 75, 75); 
            minutos.setFont(cronometro);
            minutos.setForeground(Color.white);
            painelCronometro.add(minutos);
    
            segundos = new JLabel("00");
            segundos.setBounds(290, 100, 75, 75); 
            segundos.setFont(cronometro);
            segundos.setForeground(Color.white);
            painelCronometro.add(segundos);
    
            milisegundos = new JLabel("00"); 
            milisegundos.setBounds(370, 108, 70, 70); 
            milisegundos.setFont(cronometro2);
            milisegundos.setForeground(Color.white);
            painelCronometro.add(milisegundos);

            volta1 = new JLabel(); 
            volta1.setBounds(230, 180, 700, 50); 
            volta1.setFont(font);
            painelCronometro.add(volta1);  
            
            volta2 = new JLabel(); 
            volta2.setBounds(230, 200, 700, 50); 
            volta2.setFont(font);;
            painelCronometro.add(volta2); 
    
            bComecar = new Button("COMEÇAR");
            bComecar.addActionListener(this);
            bComecar.setBounds(107, 280, 150, 30);
            bComecar.setEnabled(true);
            bComecar.setFont(font);
            painelCronometro.add(bComecar);
    
            bTerminar = new Button("VOLTA");
            bTerminar.addActionListener(this);
            bTerminar.setBounds(107, 280, 150, 30);
            bTerminar.setFont(font);
            bTerminar.setForeground(Color.RED);
            bTerminar.setEnabled(false);
            bTerminar.setVisible(false);
            painelCronometro.add(bTerminar);
            
            bFechar = new Button("CADASTRO");
            bFechar.addActionListener(this);
            bFechar.setBounds(107, 340, 150, 30);
            bFechar.setFont(font);
            bFechar.setEnabled(false);
            bFechar.setVisible(false);
            painelCronometro.add(bFechar);
    
            bterminarcorrida = new Button("TERMINAR");
            bterminarcorrida.addActionListener(this);
            bterminarcorrida.setBounds(364, 280, 150, 30);
            bterminarcorrida.setFont(font);
            bterminarcorrida.setEnabled(false);
            painelCronometro.add(bterminarcorrida);

            bjogadores = new Button("JOGADORES");
            bjogadores.addActionListener(this);
            bjogadores.setBounds(364, 340, 150, 30);
            bjogadores.setFont(font);
            bjogadores.setEnabled(false);
            bjogadores.setVisible(false);
            painelCronometro.add(bjogadores);
        
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            Button b = (Button)e.getSource();
            if (b == bComecar)
                this.botaoComecar();
            else if (b == bTerminar)
                this.botaoTerminar();
            else if (b == bFechar)
                this.botaoFechar();
            else if (b == bterminarcorrida)
                this.botaoterminarcorrida();
            else if (b == bjogadores)
                this.botaojogadores();
        }
    

        

        Connection conecta()
        {
            String url="jdbc:mysql://localhost:3306/runningpace";
            Connection con;

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection(url,"root","");
                return con;
            }
            catch(ClassNotFoundException cnf){
                System.out.println("Houve um erro no DRIVER: classnotfoundexcepition-"+cnf);
                return null;
            }
            catch(SQLException sql){
                System.out.println("Houve um erro no SQL:sqlexception sql-"+sql);
                return null;
            }
        }



        private void botaoComecar() {
            System.out.println(piloto);
            if (timer == null) {
                timer = new Timer(1, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        milisegundos1++;
                        if (milisegundos1 >= 68) {
                            milisegundos1 = 0;
                            segundos1++;
                            if (segundos1 >= 60) {
                                segundos1 = 0;
                                minutos1++;
                            }
                            minutos.setText(String.format("%02d", minutos1));
                        }
                        segundos.setText(String.format("%02d", segundos1));
                        milisegundos.setText(String.format("%02d", milisegundos1));
                    }
                });
            
                timer.start();
                bComecar.setEnabled(false); 
                bComecar.setVisible(false);
                bTerminar.setEnabled(true);
                bTerminar.setVisible(true);
                bterminarcorrida.setEnabled(false);
            }
        }

        private void botaoTerminar() {
            contador += 1;
            cminutos = minutos1*60000;
            csegundos = segundos1*1000;
            cmilisegundos = milisegundos1;
            ctotal = cminutos+csegundos+milisegundos1;
            ctotalAcumulado += ctotal;
            long millis = ctotal;
            

            if (contador == 2) {
                bTerminar.setEnabled(false);
                bterminarcorrida.setEnabled(true);
                System.out.println(contador);

                timer.stop();
                timer = null; 

                minutos1 = 0;
                segundos1 = 0;
                milisegundos1 = 0;

                milisegundos.setText(String.format("%02d", milisegundos1));
                segundos.setText(String.format("%02d", segundos1));
                minutos.setText(String.format("%02d", minutos1));

            }

            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes);

            minutos1 = 0;
            segundos1 = 0;
            milisegundos1 = 0;


            System.out.println("teste"+ctotal);

            System.out.println("teste acumulado"+ctotalAcumulado);
            
            if (volta1.getText().isEmpty()){
                volta1.setText("Volta 01: 0"+minutes + ":" +String.format("%02d" ,seconds)+"."+String.format("%02d", cmilisegundos));
            } else {
                volta2.setText("Volta 02: 0"+minutes + ":" +String.format("%02d" ,seconds)+"."+String.format("%02d", cmilisegundos));
            }
            
        }

        private void botaoterminarcorrida() {
            long millis = (long) ctotalAcumulado;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes);
            long milliseconds = millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis));


            milisegundos.setText(String.format("%02d", milliseconds));
            segundos.setText(String.format("%02d", seconds));
            minutos.setText(String.format("%02d", minutes));
            
            String Totalformatado = String.format("%02d", minutes) + ":" +String.format("%02d" ,seconds)+"."+ cmilisegundos;

            bComecar.setEnabled(false);
            bTerminar.setEnabled(false);
            bFechar.setEnabled(true);
            bFechar.setVisible(true);
            bjogadores.setEnabled(true);
            bjogadores.setVisible(true);
            bTerminar.setEnabled(false);
            bTerminar.setVisible(false);
            bterminarcorrida.setEnabled(false);
            bterminarcorrida.setVisible(false);

            Connection con = conecta();
        try {
            // Cria um Statement para acesso ao banco
            java.sql.Statement st = con.createStatement();
    
            // Executa um comando SQL para inserir os dados na tabela

            int resultado2 = st.executeUpdate("UPDATE placar SET id_temp = '" + Totalformatado + "' WHERE nome_piloto = '" + piloto + "'");

            st.close();
            con.close();

            System.out.println("salvo com sucesso");
            // ...
        } catch (SQLException e) {
            e.printStackTrace();
        }

        

        }
    
        private void botaoFechar() {
            
            cadastro outraTela = new cadastro();
                // Torna a outra tela visível
                outraTela.setVisible(true);
                // Fecha a janela atual
                dispose();
        }
    
        private void botaojogadores() {
            Connection con = conecta();
            try {
                // Cria um Statement para acesso ao banco
                java.sql.Statement st = con.createStatement();
                
                // Executando a consulta SQL
                ResultSet rs = st.executeQuery("SELECT * FROM placar ORDER BY id_temp ASC");
                
                // Criando um ArrayList para armazenar os dados
                ArrayList<Object[]> dados = new ArrayList<>();
                
                // Adicionando cada dado do ResultSet ao ArrayList
                while (rs.next()) {
                    Object[] linha = {
                        rs.getString("nome_piloto"),
                        rs.getString("nome_equipe"),
                        rs.getString("num_carro"),
                        rs.getString("id_temp")
                    };
                    dados.add(linha);
                }
                
                // Fechando a conexão
                con.close();
                
                // Convertendo o ArrayList em uma matriz de objetos
                Object[][] matrizDados = new Object[dados.size()][];
                for (int i = 0; i < dados.size(); i++) {
                    matrizDados[i] = dados.get(i);
                }
                
                // Criando os cabeçalhos da tabela
                String[] cabecalhos = {"Piloto", "Equipe", "Número do Carro", "Tempo"};
                
                // Criando a tabela e adicionando-a a um JScrollPane
                JTable tabela = new JTable(matrizDados, cabecalhos);
                JScrollPane scrollPane = new JScrollPane(tabela);
                
                // Exibindo a tabela em um JOptionPane
                JOptionPane.showMessageDialog(null, scrollPane, "Dados", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }    
        }
        
    }
}


    
    
        
        

    

