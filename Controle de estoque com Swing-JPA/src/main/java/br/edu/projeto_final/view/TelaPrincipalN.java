/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.projeto_final.view;

import br.edu.projeto_final.dao.FuncionarioDAO;
import br.edu.projeto_final.dao.PontoDeVendaDAO;
import br.edu.projeto_final.dao.ProdutoDAO;
import br.edu.projeto_final.embutiveis.Endereco;
import br.edu.projeto_final.embutiveis.Telefone;
import br.edu.projeto_final.enums.Categoria;
import br.edu.projeto_final.enums.Cor;
import br.edu.projeto_final.enums.Modelo;
import br.edu.projeto_final.enums.Sexo;
import br.edu.projeto_final.enums.Tamanho;
import br.edu.projeto_final.enums.Tipo;
import br.edu.projeto_final.exceptions.EstoqueInsuficienteException;
import br.edu.projeto_final.exceptions.FuncionarioJaCadastradoException;
import br.edu.projeto_final.exceptions.NaoEncontradoException;
import br.edu.projeto_final.exceptions.ProdutoJaCadastradoException;
import br.edu.projeto_final.modelo.Funcionario;
import br.edu.projeto_final.modelo.PontoDeVenda;
import br.edu.projeto_final.modelo.Produto;
import br.edu.projeto_final.util.JPAutil;
import br.edu.projeto_final.util.ViaCepUtil;
import br.edu.projeto_final.vo.PontoDeVendaVo;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TelaPrincipalN extends javax.swing.JFrame {

    private static DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private EntityManager em = JPAutil.getEntityManager();
    ViaCepUtil viaCepUtil = new ViaCepUtil();
    PontoDeVendaDAO pontoDeVendaDAO = new PontoDeVendaDAO(em);
    ProdutoDAO produtoDAO = new ProdutoDAO(em);
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO(em);
    
    
    private static List<PontoDeVendaVo> bairrosPontosDeVenda;
    private static List<Produto>  produtosTabelaTodosProdutos;
    
    
    public TelaPrincipalN() {
        initComponents();
        addEscutadorNaJanela();
        this.setSize(900, 800);
        this.setResizable(false);
        centreWindow(this);
        
        this.setVisible(true);
        
        
        
    }
    
    public void carregaBairroDosPontosDeVenda(){
        bairrosPontosDeVenda = pontoDeVendaDAO.BairroDosPontosDeVenda();
    }
    
    public void addEscutadorNaJanela(){
        this.addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                
                em.close();
                System.out.println(em);
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowOpened(WindowEvent e) {
                carregaBairroDosPontosDeVenda();
//                carregaPontosDeVendaCardProduto();
//                carregaComboPontoDeVendaNoCadastroFuncionario();
                carregaComboPontosDeVenda();
                
                super.windowOpened(e); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        
    }
    
    public void carregaComboPontosDeVenda(){
        for(PontoDeVendaVo pv : bairrosPontosDeVenda){
            comboPontoDeVendaCP.addItem(pv.getCEP() + "-" + pv.getBairro()); 
            comboPontoDeVendaCF.addItem(pv.getCEP() + "-" + pv.getBairro());
            comboPontosDeVendaAtualizarPV.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaAP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaTP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontosDeVendaCFP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaRPV.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaTF.addItem(pv.getCEP()+"-"+pv.getBairro());
        } 
    }
    
   
    
     
    
      private void resetaCombosBox(){
        
        comboPontoDeVendaCF.removeAllItems();
        comboPontoDeVendaCP.removeAllItems();
        comboPontosDeVendaAtualizarPV.removeAllItems();
        comboPontoDeVendaAP.removeAllItems();
        comboPontoDeVendaTP.removeAllItems();;
        comboPontosDeVendaCFP.removeAllItems();
        comboPontoDeVendaRPV.removeAllItems();
        comboPontoDeVendaTF.removeAllItems();
                
                
        comboPontoDeVendaCF.addItem("Selecione");
        comboPontoDeVendaCF.setSelectedIndex(0);
        
        comboPontoDeVendaCP.addItem("Selecione");
        comboPontoDeVendaCP.setSelectedIndex(0);
        
        comboPontosDeVendaAtualizarPV.addItem("Selecione");
        comboPontosDeVendaAtualizarPV.setSelectedIndex(0);
        
        comboPontoDeVendaAP.addItem("Selecione");
        comboPontoDeVendaAP.setSelectedIndex(0);
        
        comboPontoDeVendaTP.addItem("Selecione");
        comboPontoDeVendaTP.setSelectedIndex(0);
        
        comboPontosDeVendaCFP.addItem("Selecione");
        comboPontosDeVendaCFP.setSelectedIndex(0);
        
        comboPontoDeVendaRPV.addItem("Selecione");
        comboPontoDeVendaRPV.setSelectedIndex(0);
        
        comboPontoDeVendaTF.addItem("Selecione");
        comboPontoDeVendaTF.setSelectedIndex(0);
        
        bairrosPontosDeVenda = pontoDeVendaDAO.BairroDosPontosDeVenda();
        
        for(PontoDeVendaVo pv : bairrosPontosDeVenda){
            comboPontoDeVendaCP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaCF.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontosDeVendaAtualizarPV.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaAP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaTP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontosDeVendaCFP.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaRPV.addItem(pv.getCEP()+"-"+pv.getBairro());
            comboPontoDeVendaTF.addItem(pv.getCEP()+"-"+pv.getBairro());
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CardTela = new javax.swing.JPanel();
        CardTelaPrincipal = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        CARDCadastrarPontoDeVenda = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtLogradouroCPV = new javax.swing.JTextField();
        txtCepCPV = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBairroCPV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboEstadoCPV = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        comboCidadeCPV = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroCPV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtComplementoCPV = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        botaoCadastrarPontoDeVenda = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTelefoneCPV = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEmailCPV = new javax.swing.JTextField();
        CARDCadastrarProduto = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        comboCategoriaCP = new javax.swing.JComboBox<>();
        txtDescricaoProdutoCP = new javax.swing.JTextField();
        comboTipoCP = new javax.swing.JComboBox<>();
        comboTamanhoCP = new javax.swing.JComboBox<>();
        comboModeloCP = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtQuantidadeCP = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        comboCorCP = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtCodigoBarrasCP = new javax.swing.JTextField();
        botaoCadastrarProduto = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtPrecoCP = new javax.swing.JFormattedTextField();
        comboPontoDeVendaCP = new javax.swing.JComboBox<>();
        jLabel84 = new javax.swing.JLabel();
        CARDCadastroFuncionario = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNomeCF = new javax.swing.JTextField();
        txtDataNascimentoCF = new javax.swing.JFormattedTextField();
        txtCpfCF = new javax.swing.JFormattedTextField();
        jLabel63 = new javax.swing.JLabel();
        comboPontoDeVendaCF = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        comboSexoCF = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        txtLogradouroCF = new javax.swing.JTextField();
        txtCepCF = new javax.swing.JFormattedTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtBairroCF = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        comboEstadoCF = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        comboCidadeCF = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txtNumeroCF = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txtComplementoCF = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        botaoCadastrarFuncionario = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtEmailCF = new javax.swing.JTextField();
        txtTelefoneCF = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        txtRedeSocialCF = new javax.swing.JTextField();
        botaoLimparCF = new javax.swing.JButton();
        CARDAtualizaPontoDeVenda = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtLogradouroAPV = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtBairroAPV = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        comboEstadoAPV = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        comboCidadeAPV = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtNumeroAPV = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtComplementoAPV = new javax.swing.JTextField();
        txtCepAPV = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtIDPontoDeVendaAPV = new javax.swing.JTextField();
        botaoCadastrarPontoDeVenda1 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtEmailAPV = new javax.swing.JTextField();
        txtTelefoneAPV = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        comboPontosDeVendaAtualizarPV = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        CARDAtualizaProduto = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        comboCategoriaAP = new javax.swing.JComboBox<>();
        txtDescricaoProdutoAP = new javax.swing.JTextField();
        comboTipoAP = new javax.swing.JComboBox<>();
        comboTamanhoAP = new javax.swing.JComboBox<>();
        comboModeloAP = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtQuantidadeAP = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        comboCorAP = new javax.swing.JComboBox<>();
        botaoAtualizarProduto = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        txtPrecoAP = new javax.swing.JFormattedTextField();
        jPanel22 = new javax.swing.JPanel();
        txtCodigoBarrasBuscaAP = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        comboPontoDeVendaAP = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        botaoBuscaProdutoAP = new javax.swing.JButton();
        TodosProdutos = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTodosProdutos = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        txtDescricaoTP = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtCodigoBarrasTP = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        comboCategoriaTP = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        comboTamanhoTP = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        comboCorTP = new javax.swing.JComboBox<>();
        jLabel75 = new javax.swing.JLabel();
        txtQuantidadeTP = new javax.swing.JTextField();
        txtPrecoTP = new javax.swing.JFormattedTextField();
        jLabel76 = new javax.swing.JLabel();
        comboModeloTP = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        comboTipoTP = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        comboPontoDeVendaTP = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtIdPVTP = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        txtCepTP = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        txtIdTP = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        CadastrarFuncionarioNoPonto = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        txtCpfCFP = new javax.swing.JFormattedTextField();
        jLabel80 = new javax.swing.JLabel();
        txtNomeCFP = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        txtSexoCFP = new javax.swing.JTextField();
        botaoBuscarFuncCFP = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        comboPontosDeVendaCFP = new javax.swing.JComboBox<>();
        jLabel82 = new javax.swing.JLabel();
        CARDRemoverPontoDeVenda = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        comboPontoDeVendaRPV = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        CARDAtualizaFuncionario = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtNomeAF = new javax.swing.JTextField();
        txtDataNascimentoAF = new javax.swing.JFormattedTextField();
        jLabel93 = new javax.swing.JLabel();
        comboSexoAF = new javax.swing.JComboBox<>();
        txtCpfAF = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        txtLogradouroAF = new javax.swing.JTextField();
        txtCepAF = new javax.swing.JFormattedTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        txtBairroAF = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        comboEstadoAF = new javax.swing.JComboBox<>();
        jLabel98 = new javax.swing.JLabel();
        comboCidadeAF = new javax.swing.JComboBox<>();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        txtNumeroAF = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        txtComplementoAF = new javax.swing.JTextField();
        botaoBuscarAF = new javax.swing.JButton();
        AtualizarFuncionario = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        txtEmailAF = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        txtRedeSocialAF = new javax.swing.JTextField();
        txtTelefoneAF = new javax.swing.JTextField();
        botaoLimparCF1 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        txtBuscaFuncionarioAF = new javax.swing.JFormattedTextField();
        jButton14 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        CARDRemoverFuncionario = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jLabel113 = new javax.swing.JLabel();
        txtCpfRF = new javax.swing.JFormattedTextField();
        CardTodosFuncionarios = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        comboPontoDeVendaTF = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaTodosFuncionarios = new javax.swing.JTable();
        CARDTodosPontoDeVenda = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaTodosPontosDeVenda = new javax.swing.JTable();
        CARDTodosProdutosGeral = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaTodosProdutosGeral = new javax.swing.JTable();
        Menu_Bar_PontoDeVenda = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CardTela.setLayout(new java.awt.CardLayout());

        CardTelaPrincipal.setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel8.setBackground(new java.awt.Color(254, 254, 254));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("Principal");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel4)
                .addContainerGap(442, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel4)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(254, 254, 254));

        jLabel106.setText("Tem um Menu Bar na parte superior com as seguintes funcionalidades");

        jLabel107.setText("Ponto De Venda");

        jLabel108.setText("Cadastrar");

        jLabel109.setText("Atualizar");

        jLabel110.setText("Remover");

        jLabel111.setText("Cadastrar Funcionario no ponto - Coloca cpf do funcionario, busca ele e escolhe o ponto");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106)
                    .addComponent(jLabel107)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel109)
                                .addComponent(jLabel108)
                                .addComponent(jLabel110))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel111)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel106)
                .addGap(18, 18, 18)
                .addComponent(jLabel107)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel111)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CardTelaPrincipalLayout = new javax.swing.GroupLayout(CardTelaPrincipal);
        CardTelaPrincipal.setLayout(CardTelaPrincipalLayout);
        CardTelaPrincipalLayout.setHorizontalGroup(
            CardTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardTelaPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        CardTelaPrincipalLayout.setVerticalGroup(
            CardTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardTelaPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        CardTela.add(CardTelaPrincipal, "cardTelaPrincipal");

        CARDCadastrarPontoDeVenda.setMaximumSize(new java.awt.Dimension(800, 700));
        CARDCadastrarPontoDeVenda.setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Cadastrar Ponto De Venda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel1)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Endereco"));

        jLabel5.setText("CEP");

        try {
            txtCepCPV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Logradouro");

        jLabel8.setText("Estado");

        comboEstadoCPV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PE", "BH", "RJ", "RN", "PB" }));

        jLabel9.setText("Cidade");

        comboCidadeCPV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Olinda", "Abreu e Lima", "Recife", "Paulista", "Igarrasu", "Jaboatão Dos Guararapes", "Candeias " }));

        jLabel10.setText("Bairro");

        jLabel11.setText("Numero");

        jLabel12.setText("Complemento");

        jButton2.setText("BUSCAR");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(txtLogradouroCPV, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(txtBairroCPV)
                            .addComponent(jLabel8)
                            .addComponent(comboEstadoCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(comboCidadeCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtComplementoCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtCepCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCepCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboEstadoCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCidadeCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBairroCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogradouroCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplementoCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        botaoCadastrarPontoDeVenda.setText("Cadastrar Ponto De Venda");
        botaoCadastrarPontoDeVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarPontoDeVendaActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contato"));

        jLabel13.setText("Telefone");

        try {
            txtTelefoneCPV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setText("Email");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(txtEmailCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefoneCPV, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailCPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoCadastrarPontoDeVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoCadastrarPontoDeVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CARDCadastrarPontoDeVendaLayout = new javax.swing.GroupLayout(CARDCadastrarPontoDeVenda);
        CARDCadastrarPontoDeVenda.setLayout(CARDCadastrarPontoDeVendaLayout);
        CARDCadastrarPontoDeVendaLayout.setHorizontalGroup(
            CARDCadastrarPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDCadastrarPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(CARDCadastrarPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        CARDCadastrarPontoDeVendaLayout.setVerticalGroup(
            CARDCadastrarPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDCadastrarPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        CardTela.add(CARDCadastrarPontoDeVenda, "cadastroPontoDeVenda");

        CARDCadastrarProduto.setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel3.setBackground(new java.awt.Color(254, 254, 254));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Cadastrar Produto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel2)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(254, 254, 254));
        jPanel4.setPreferredSize(new java.awt.Dimension(780, 500));

        comboCategoriaCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Vestuario", "Acessorios" }));
        comboCategoriaCP.setMinimumSize(new java.awt.Dimension(100, 20));
        comboCategoriaCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaCPActionPerformed(evt);
            }
        });

        txtDescricaoProdutoCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoProdutoCPActionPerformed(evt);
            }
        });

        comboTipoCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Camisa", "Bermuda", "Vestido", "Meia", "Bone", "Pochete", "Brinco", "Oculos" }));
        comboTipoCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoCPActionPerformed(evt);
            }
        });

        comboTamanhoCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PP", "P", "M", "G", "GG", "XG" }));
        comboTamanhoCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTamanhoCPActionPerformed(evt);
            }
        });

        comboModeloCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino", "Infantil", "Unissex" }));
        comboModeloCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModeloCPActionPerformed(evt);
            }
        });

        jLabel15.setText("Descrição");

        jLabel17.setText("Tipo");

        jLabel18.setText("Tamanho");

        jLabel19.setText("Modelo");

        jLabel20.setText("Categoria");

        jLabel21.setText("Quantidade");

        jLabel22.setText("Cor");

        comboCorCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Preto", "Colorido", "Azul", "Vermelho", "Rosa", "Amarelo", "Branco", "Cinza", "Verde" }));
        comboCorCP.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel23.setText("Codigo de Barras");

        txtCodigoBarrasCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarrasCPActionPerformed(evt);
            }
        });

        botaoCadastrarProduto.setText("Cadastrar Produto");
        botaoCadastrarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarProdutoActionPerformed(evt);
            }
        });

        jLabel61.setText("Ponto De Venda");

        jLabel62.setText("Preco");

        txtPrecoCP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        comboPontoDeVendaCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 102, 102));
        jLabel84.setText("Utilize o mesmo codigo de barras para o mesmo produto, so mudar as caracteristicas abaixo!");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(botaoCadastrarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel23)
                            .addComponent(txtCodigoBarrasCP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel17))
                            .addComponent(jLabel18)
                            .addComponent(jLabel84)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboCategoriaCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboTamanhoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addComponent(txtQuantidadeCP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboCorCP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel62)
                                    .addComponent(comboTipoCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19)
                                    .addComponent(comboModeloCP, 0, 109, Short.MAX_VALUE)
                                    .addComponent(jLabel61)
                                    .addComponent(txtPrecoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboPontoDeVendaCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtDescricaoProdutoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricaoProdutoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addGap(1, 1, 1)
                .addComponent(txtCodigoBarrasCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCategoriaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTamanhoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboModeloCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel61))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCorCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPontoDeVendaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel62))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantidadeCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(botaoCadastrarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CARDCadastrarProdutoLayout = new javax.swing.GroupLayout(CARDCadastrarProduto);
        CARDCadastrarProduto.setLayout(CARDCadastrarProdutoLayout);
        CARDCadastrarProdutoLayout.setHorizontalGroup(
            CARDCadastrarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDCadastrarProdutoLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(CARDCadastrarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        CARDCadastrarProdutoLayout.setVerticalGroup(
            CARDCadastrarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDCadastrarProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CardTela.add(CARDCadastrarProduto, "CadastroProduto");

        CARDCadastroFuncionario.setMaximumSize(new java.awt.Dimension(800, 700));
        CARDCadastroFuncionario.setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel5.setBackground(new java.awt.Color(254, 254, 254));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Cadastrar Funcionário");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(364, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(260, 260, 260))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel3)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(254, 254, 254));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Informacoes"));

        jLabel24.setText("Nome");

        jLabel25.setText("CPF");

        jLabel26.setText("Data de nascimento");

        try {
            txtDataNascimentoCF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCpfCF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel63.setText("Ponto De Venda");

        comboPontoDeVendaCF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel27.setText("Sexo");

        comboSexoCF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "MASCULINO", "FEMININO", "INDEFINIDO" }));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(txtDataNascimentoCF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpfCF, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(txtNomeCF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(comboSexoCF, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPontoDeVendaCF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataNascimentoCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPontoDeVendaCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboSexoCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Endereco"));

        jLabel67.setText("CEP");

        try {
            txtCepCF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel68.setText("Logradouro");

        jLabel70.setText("Estado");

        comboEstadoCF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PE", "BH", "RJ", "RN", "PB" }));

        jLabel71.setText("Cidade");

        comboCidadeCF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Olinda", "Abreu e Lima", "Recife", "Paulista", "Igarrasu", "Jaboatão Dos Guararapes", "Candeias " }));

        jLabel72.setText("Bairro");

        jLabel73.setText("Numero");

        jLabel74.setText("Complemento");

        jButton9.setText("BUSCAR");
        jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txtCepCF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel67)
                                .addComponent(txtLogradouroCF)
                                .addComponent(jLabel68)
                                .addComponent(txtBairroCF, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel70)
                                .addComponent(comboEstadoCF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel71)
                                .addComponent(comboCidadeCF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel72))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroCF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel74)
                                    .addComponent(txtComplementoCF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel69))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCepCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstadoCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCidadeCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLogradouroCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComplementoCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        botaoCadastrarFuncionario.setText("Cadastrar Funcionario");
        botaoCadastrarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarFuncionarioActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));

        jLabel59.setText("Telefone");

        jLabel60.setText("Email");

        try {
            txtTelefoneCF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel28.setText("Rede social");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(txtEmailCF)
                    .addComponent(txtTelefoneCF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addComponent(txtRedeSocialCF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtRedeSocialCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        botaoLimparCF.setText("Limpar Formulario");
        botaoLimparCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparCFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoCadastrarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163)
                .addComponent(botaoLimparCF)
                .addGap(76, 76, 76))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(111, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(botaoLimparCF)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout CARDCadastroFuncionarioLayout = new javax.swing.GroupLayout(CARDCadastroFuncionario);
        CARDCadastroFuncionario.setLayout(CARDCadastroFuncionarioLayout);
        CARDCadastroFuncionarioLayout.setHorizontalGroup(
            CARDCadastroFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDCadastroFuncionarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CARDCadastroFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CARDCadastroFuncionarioLayout.setVerticalGroup(
            CARDCadastroFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDCadastroFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CardTela.add(CARDCadastroFuncionario, "CadastroFuncionario");

        jPanel11.setBackground(new java.awt.Color(254, 254, 254));

        jLabel29.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel29.setText("Atualizar Ponto De Venda");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel29)
                .addContainerGap(348, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel29)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(254, 254, 254));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Endereco"));

        jLabel30.setText("CEP");

        jLabel31.setText("Logradouro");

        jLabel33.setText("Estado");

        comboEstadoAPV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PE", "BH", "RJ", "RN", "PB" }));

        jLabel34.setText("Cidade");

        comboCidadeAPV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Olinda", "Abreu e Lima", "Recife", "Paulista", "Igarrasu", "Jaboatão Dos Guararapes", "Candeias " }));

        jLabel35.setText("Bairro");

        jLabel36.setText("Numero");

        jLabel37.setText("Complemento");

        jLabel41.setText("ID");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLogradouroAPV)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(txtBairroAPV)
                    .addComponent(jLabel33)
                    .addComponent(comboEstadoAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(comboCidadeAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(txtComplementoAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(txtCepAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(txtIDPontoDeVendaAPV)
                                .addGap(9, 9, 9))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCepAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDPontoDeVendaAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboEstadoAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCidadeAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBairroAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogradouroAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplementoAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        botaoCadastrarPontoDeVenda1.setText("Atualizar Ponto De Venda");
        botaoCadastrarPontoDeVenda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarPontoDeVenda1ActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contato"));

        jLabel38.setText("Telefone");

        jLabel39.setText("Email");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(txtTelefoneAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailAPV, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailAPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        comboPontosDeVendaAtualizarPV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel40.setText("Pontos De Venda");

        jButton1.setText("Buscar Ponto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setText("BUSCAR PONTO PARA ATUALIZAR");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel86))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboPontosDeVendaAtualizarPV, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel86)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPontosDeVendaAtualizarPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );

        jButton11.setText("Limpar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(170, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(botaoCadastrarPontoDeVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrarPontoDeVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CARDAtualizaPontoDeVendaLayout = new javax.swing.GroupLayout(CARDAtualizaPontoDeVenda);
        CARDAtualizaPontoDeVenda.setLayout(CARDAtualizaPontoDeVendaLayout);
        CARDAtualizaPontoDeVendaLayout.setHorizontalGroup(
            CARDAtualizaPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDAtualizaPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CARDAtualizaPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CARDAtualizaPontoDeVendaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        CARDAtualizaPontoDeVendaLayout.setVerticalGroup(
            CARDAtualizaPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDAtualizaPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        CardTela.add(CARDAtualizaPontoDeVenda, "atualizarPontoDeVenda");

        jPanel19.setBackground(new java.awt.Color(254, 254, 254));

        jLabel42.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel42.setText("Atualizar Produto");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel42)
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel42)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(254, 254, 254));
        jPanel20.setPreferredSize(new java.awt.Dimension(780, 500));

        comboCategoriaAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Vestuario", "Acessorios" }));
        comboCategoriaAP.setMinimumSize(new java.awt.Dimension(100, 20));
        comboCategoriaAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaAPActionPerformed(evt);
            }
        });

        txtDescricaoProdutoAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoProdutoAPActionPerformed(evt);
            }
        });

        comboTipoAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Camisa", "Bermuda", "Vestido", "Meia", "Bone", "Pochete", "Brinco", "Oculos" }));
        comboTipoAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoAPActionPerformed(evt);
            }
        });

        comboTamanhoAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PP", "P", "M", "G", "GG", "XG" }));
        comboTamanhoAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTamanhoAPActionPerformed(evt);
            }
        });

        comboModeloAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino", "Infantil", "Unissex" }));
        comboModeloAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModeloAPActionPerformed(evt);
            }
        });

        jLabel43.setText("Descrição");

        jLabel45.setText("Tipo");

        jLabel46.setText("Tamanho");

        jLabel47.setText("Modelo");

        jLabel48.setText("Categoria");

        jLabel49.setText("Quantidade");

        jLabel50.setText("Cor");

        comboCorAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Preto", "Colorido", "Azul", "Vermelho", "Rosa", "Amarelo", "Branco", "Cinza", "Verde" }));
        comboCorAP.setPreferredSize(new java.awt.Dimension(100, 20));

        botaoAtualizarProduto.setText("Atualizar Produto");
        botaoAtualizarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarProdutoActionPerformed(evt);
            }
        });

        jLabel65.setText("Preco");

        txtPrecoAP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel46))
                                .addGap(101, 101, 101)
                                .addComponent(jLabel45))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboCategoriaAP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboTamanhoAP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel49)
                                    .addComponent(txtQuantidadeAP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel65)
                                    .addComponent(comboTipoAP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel47)
                                    .addComponent(comboModeloAP, 0, 109, Short.MAX_VALUE)
                                    .addComponent(txtPrecoAP, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(comboCorAP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(82, 82, 82)))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(txtDescricaoProdutoAP, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(25, Short.MAX_VALUE))))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(botaoAtualizarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(txtDescricaoProdutoAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(jLabel48))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTipoAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCategoriaAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTamanhoAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboModeloAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel50)
                        .addGap(3, 3, 3)
                        .addComponent(comboCorAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel65))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantidadeAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecoAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51)
                .addComponent(botaoAtualizarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        txtCodigoBarrasBuscaAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarrasBuscaAPActionPerformed(evt);
            }
        });

        jLabel51.setText("Codigo de Barras");

        comboPontoDeVendaAP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel52.setText("Ponto De Venda");

        botaoBuscaProdutoAP.setText("Buscar Produto");
        botaoBuscaProdutoAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscaProdutoAPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPontoDeVendaAP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(botaoBuscaProdutoAP, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 28, Short.MAX_VALUE)))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51)
                            .addComponent(txtCodigoBarrasBuscaAP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel51)
                .addGap(1, 1, 1)
                .addComponent(txtCodigoBarrasBuscaAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPontoDeVendaAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botaoBuscaProdutoAP)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout CARDAtualizaProdutoLayout = new javax.swing.GroupLayout(CARDAtualizaProduto);
        CARDAtualizaProduto.setLayout(CARDAtualizaProdutoLayout);
        CARDAtualizaProdutoLayout.setHorizontalGroup(
            CARDAtualizaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDAtualizaProdutoLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(CARDAtualizaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        CARDAtualizaProdutoLayout.setVerticalGroup(
            CARDAtualizaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDAtualizaProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CardTela.add(CARDAtualizaProduto, "atualizarProduto");

        jPanel24.setBackground(new java.awt.Color(254, 254, 254));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel53.setText("Produtos");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel53)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tabelaTodosProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categoria", "CodigoBarras", "Cor", "Descricao", "Modelo", "Preço", "qtd", "Tamanho", "Tipo", "pvId"
            }
        ));
        jScrollPane1.setViewportView(tabelaTodosProdutos);
        if (tabelaTodosProdutos.getColumnModel().getColumnCount() > 0) {
            tabelaTodosProdutos.getColumnModel().getColumn(0).setMinWidth(30);
            tabelaTodosProdutos.getColumnModel().getColumn(0).setMaxWidth(30);
            tabelaTodosProdutos.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(2).setMinWidth(110);
            tabelaTodosProdutos.getColumnModel().getColumn(2).setMaxWidth(110);
            tabelaTodosProdutos.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(4).setMinWidth(200);
            tabelaTodosProdutos.getColumnModel().getColumn(4).setMaxWidth(200);
            tabelaTodosProdutos.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(6).setMinWidth(60);
            tabelaTodosProdutos.getColumnModel().getColumn(6).setMaxWidth(60);
            tabelaTodosProdutos.getColumnModel().getColumn(7).setMinWidth(50);
            tabelaTodosProdutos.getColumnModel().getColumn(7).setMaxWidth(50);
            tabelaTodosProdutos.getColumnModel().getColumn(8).setMinWidth(65);
            tabelaTodosProdutos.getColumnModel().getColumn(8).setMaxWidth(65);
            tabelaTodosProdutos.getColumnModel().getColumn(9).setMinWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(9).setMaxWidth(80);
            tabelaTodosProdutos.getColumnModel().getColumn(10).setMinWidth(35);
            tabelaTodosProdutos.getColumnModel().getColumn(10).setMaxWidth(35);
        }

        jPanel25.setBackground(new java.awt.Color(254, 254, 254));

        txtDescricaoTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoTPActionPerformed(evt);
            }
        });

        jLabel56.setText("Descrição");

        jLabel57.setText("Codigo de Barras");

        txtCodigoBarrasTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarrasTPActionPerformed(evt);
            }
        });

        jLabel58.setText("Categoria");

        comboCategoriaTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Vestuario", "Acessorios" }));
        comboCategoriaTP.setMinimumSize(new java.awt.Dimension(100, 20));
        comboCategoriaTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaTPActionPerformed(evt);
            }
        });

        jLabel64.setText("Tamanho");

        comboTamanhoTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PP", "P", "M", "G", "GG", "XG" }));
        comboTamanhoTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTamanhoTPActionPerformed(evt);
            }
        });

        jLabel66.setText("Cor");

        comboCorTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Preto", "Colorido", "Azul", "Vermelho", "Rosa", "Amarelo", "Branco", "Cinza", "Verde" }));
        comboCorTP.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel75.setText("Quantidade");

        txtPrecoTP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel76.setText("Preco");

        comboModeloTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino", "Infantil", "Unissex" }));
        comboModeloTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModeloTPActionPerformed(evt);
            }
        });

        jLabel78.setText("Modelo");

        comboTipoTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Camisa", "Bermuda", "Vestido", "Meia", "Bone", "Pochete", "Brinco", "Oculos" }));
        comboTipoTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoTPActionPerformed(evt);
            }
        });

        jLabel79.setText("Tipo");

        comboPontoDeVendaTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel77.setText("Ponto De Venda");

        jButton3.setText("Buscar produtos ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel54.setText("ID PV");

        jLabel85.setText("CEP");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(comboPontoDeVendaTP, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdPVTP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCepTP, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtIdPVTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85)
                    .addComponent(txtCepTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel77)
                .addGap(3, 3, 3)
                .addComponent(comboPontoDeVendaTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addGap(44, 44, 44))
        );

        jButton5.setText("Atualizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel83.setText("ID");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83)
                            .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoBarrasTP, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))
                        .addGap(41, 41, 41))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(txtDescricaoTP, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuantidadeTP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel75))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(txtPrecoTP, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(comboCorTP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(comboCategoriaTP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addComponent(comboTipoTP, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel64)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(comboTamanhoTP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel78)
                            .addComponent(comboModeloTP, 0, 109, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel58))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboTipoTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboCategoriaTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel78)
                                    .addComponent(jLabel64))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboTamanhoTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboModeloTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodigoBarrasTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel83)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescricaoTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel75)
                                    .addComponent(jLabel76))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtQuantidadeTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecoTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel66)
                                .addGap(3, 3, 3)
                                .addComponent(comboCorTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton4.setText("Atualizar Produto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Remover Produto");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Limpar Formulario");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 153, 153));
        jButton10.setText("Dar baixa no produto");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TodosProdutosLayout = new javax.swing.GroupLayout(TodosProdutos);
        TodosProdutos.setLayout(TodosProdutosLayout);
        TodosProdutosLayout.setHorizontalGroup(
            TodosProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TodosProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TodosProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TodosProdutosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        TodosProdutosLayout.setVerticalGroup(
            TodosProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TodosProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TodosProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CardTela.add(TodosProdutos, "cardProdutos");

        jPanel23.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(254, 254, 254));

        jLabel55.setText("CPF do funcionario");

        try {
            txtCpfCFP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel80.setText("Nome");

        jLabel81.setText("Sexo");

        botaoBuscarFuncCFP.setText("Buscar Funcionario");
        botaoBuscarFuncCFP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarFuncCFPActionPerformed(evt);
            }
        });

        jButton8.setText("Cadastrar No Ponto");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        comboPontosDeVendaCFP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel82.setText("Ponto De Venda");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel81)
                    .addComponent(jLabel80)
                    .addComponent(jLabel55)
                    .addComponent(txtCpfCFP, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txtNomeCFP)
                    .addComponent(txtSexoCFP))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(comboPontosDeVendaCFP, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoBuscarFuncCFP, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPontosDeVendaCFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpfCFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoBuscarFuncCFP))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeCFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSexoCFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout CadastrarFuncionarioNoPontoLayout = new javax.swing.GroupLayout(CadastrarFuncionarioNoPonto);
        CadastrarFuncionarioNoPonto.setLayout(CadastrarFuncionarioNoPontoLayout);
        CadastrarFuncionarioNoPontoLayout.setHorizontalGroup(
            CadastrarFuncionarioNoPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CadastrarFuncionarioNoPontoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CadastrarFuncionarioNoPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CadastrarFuncionarioNoPontoLayout.createSequentialGroup()
                        .addGap(0, 49, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CadastrarFuncionarioNoPontoLayout.setVerticalGroup(
            CadastrarFuncionarioNoPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CadastrarFuncionarioNoPontoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        CardTela.add(CadastrarFuncionarioNoPonto, "cadastrarNoPonto");

        jPanel28.setBackground(new java.awt.Color(254, 254, 254));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel87.setText("Remover Ponto De Venda");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel87)
                .addGap(285, 285, 285))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel87)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(254, 254, 254));

        jLabel88.setText("Ponto De Venda");

        comboPontoDeVendaRPV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jButton12.setText("Remover Ponto De Venda");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel88)
                    .addComponent(comboPontoDeVendaRPV, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPontoDeVendaRPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );

        javax.swing.GroupLayout CARDRemoverPontoDeVendaLayout = new javax.swing.GroupLayout(CARDRemoverPontoDeVenda);
        CARDRemoverPontoDeVenda.setLayout(CARDRemoverPontoDeVendaLayout);
        CARDRemoverPontoDeVendaLayout.setHorizontalGroup(
            CARDRemoverPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDRemoverPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CARDRemoverPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CARDRemoverPontoDeVendaLayout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 125, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CARDRemoverPontoDeVendaLayout.setVerticalGroup(
            CARDRemoverPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDRemoverPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        CardTela.add(CARDRemoverPontoDeVenda, "removerPontoDeVenda");

        CARDAtualizaFuncionario.setPreferredSize(new java.awt.Dimension(900, 800));

        jPanel32.setBackground(new java.awt.Color(254, 254, 254));

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Informacoes"));

        jLabel89.setText("Nome");

        jLabel90.setText("CPF");

        jLabel91.setText("Data de nascimento");

        try {
            txtDataNascimentoAF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNascimentoAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoAFActionPerformed(evt);
            }
        });

        jLabel93.setText("Sexo");

        comboSexoAF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "MASCULINO", "FEMININO", "INDEFINIDO" }));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(jLabel90)
                    .addComponent(jLabel91)
                    .addComponent(txtDataNascimentoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeAF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93)
                    .addComponent(comboSexoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpfAF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataNascimentoAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboSexoAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Endereco"));

        jLabel94.setText("CEP");

        try {
            txtCepAF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel95.setText("Logradouro");

        jLabel97.setText("Estado");

        comboEstadoAF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "PE", "BH", "RJ", "RN", "PB" }));

        jLabel98.setText("Cidade");

        comboCidadeAF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Olinda", "Abreu e Lima", "Recife", "Paulista", "Igarrasu", "Jaboatão Dos Guararapes", "Candeias " }));

        jLabel99.setText("Bairro");

        jLabel100.setText("Numero");

        jLabel101.setText("Complemento");

        botaoBuscarAF.setText("BUSCAR");
        botaoBuscarAF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoBuscarAF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoBuscarAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarAFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(txtCepAF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoBuscarAF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel94)
                                .addComponent(txtLogradouroAF)
                                .addComponent(jLabel95)
                                .addComponent(txtBairroAF, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel97)
                                .addComponent(comboEstadoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel98)
                                .addComponent(comboCidadeAF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel99))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroAF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel100))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel101)
                                    .addComponent(txtComplementoAF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel96))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCepAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoBuscarAF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstadoAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCidadeAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel99)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLogradouroAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100)
                            .addComponent(jLabel101))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtComplementoAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        AtualizarFuncionario.setText("Atualizar Funcionario");
        AtualizarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarFuncionarioActionPerformed(evt);
            }
        });

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));

        jLabel102.setText("Telefone");

        jLabel103.setText("Email");

        jLabel104.setText("Rede social");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel102)
                    .addComponent(jLabel103)
                    .addComponent(txtEmailAF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel104)
                    .addComponent(txtRedeSocialAF)
                    .addComponent(txtTelefoneAF))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102)
                .addGap(1, 1, 1)
                .addComponent(txtTelefoneAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel103)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel104)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtRedeSocialAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        botaoLimparCF1.setText("Limpar Formulario");
        botaoLimparCF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparCF1ActionPerformed(evt);
            }
        });

        jLabel105.setText("CPF");

        try {
            txtBuscaFuncionarioAF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton14.setText("Buscar Funcionario");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel105)
                        .addComponent(txtBuscaFuncionarioAF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel105)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaFuncionarioAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AtualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoLimparCF1)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AtualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimparCF1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel31.setBackground(new java.awt.Color(254, 254, 254));

        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel120.setText("Atualizar Funcionario");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120)
                    .addComponent(jLabel92))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout CARDAtualizaFuncionarioLayout = new javax.swing.GroupLayout(CARDAtualizaFuncionario);
        CARDAtualizaFuncionario.setLayout(CARDAtualizaFuncionarioLayout);
        CARDAtualizaFuncionarioLayout.setHorizontalGroup(
            CARDAtualizaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CARDAtualizaFuncionarioLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(CARDAtualizaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        CARDAtualizaFuncionarioLayout.setVerticalGroup(
            CARDAtualizaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDAtualizaFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        CardTela.add(CARDAtualizaFuncionario, "AtualizarFuncionario");

        jPanel37.setBackground(new java.awt.Color(254, 254, 254));

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel112.setText("Remover Funcionario");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel112)
                .addGap(285, 285, 285))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel112)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel38.setBackground(new java.awt.Color(254, 254, 254));

        jButton13.setText("Remover Funcionario");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel113.setText("CPF");

        try {
            txtCpfRF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel113)
                            .addComponent(txtCpfRF, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jLabel113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout CARDRemoverFuncionarioLayout = new javax.swing.GroupLayout(CARDRemoverFuncionario);
        CARDRemoverFuncionario.setLayout(CARDRemoverFuncionarioLayout);
        CARDRemoverFuncionarioLayout.setHorizontalGroup(
            CARDRemoverFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDRemoverFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CARDRemoverFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CARDRemoverFuncionarioLayout.createSequentialGroup()
                        .addGap(0, 226, Short.MAX_VALUE)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 225, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CARDRemoverFuncionarioLayout.setVerticalGroup(
            CARDRemoverFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDRemoverFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        CardTela.add(CARDRemoverFuncionario, "removerFuncionario");

        jPanel39.setBackground(new java.awt.Color(254, 254, 254));

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel114.setText("Todos Funcionarios");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabel114)
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel114)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel40.setBackground(new java.awt.Color(254, 254, 254));

        comboPontoDeVendaTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel115.setText("Ponto De Venda");

        jLabel116.setText("Por Ponto De Venda");

        jButton16.setText("Buscar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel116)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addGap(0, 84, Short.MAX_VALUE)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel115)
                            .addComponent(comboPontoDeVendaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addGap(127, 127, 127))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel116)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel115)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPontoDeVendaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton16)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel41.setBackground(new java.awt.Color(254, 254, 254));

        jLabel117.setText("Todos os funcionarios");

        jButton15.setText("Buscar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel117))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel117)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabelaTodosFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "NOME", "Sexo", "data_nascimento", "Email", "Cep", "Estado", "Cidade", "Bairro", "Logradouro", "Numero", "Complemento", "Telefone", "Rede Social"
            }
        ));
        tabelaTodosFuncionarios.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tabelaTodosFuncionarios);
        tabelaTodosFuncionarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabelaTodosFuncionarios.getColumnModel().getColumnCount() > 0) {
            tabelaTodosFuncionarios.getColumnModel().getColumn(0).setMinWidth(90);
            tabelaTodosFuncionarios.getColumnModel().getColumn(0).setMaxWidth(90);
            tabelaTodosFuncionarios.getColumnModel().getColumn(2).setMinWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(2).setMaxWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(3).setMinWidth(85);
            tabelaTodosFuncionarios.getColumnModel().getColumn(3).setMaxWidth(85);
            tabelaTodosFuncionarios.getColumnModel().getColumn(6).setMinWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(6).setMaxWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(7).setMinWidth(50);
            tabelaTodosFuncionarios.getColumnModel().getColumn(7).setMaxWidth(50);
            tabelaTodosFuncionarios.getColumnModel().getColumn(8).setMinWidth(70);
            tabelaTodosFuncionarios.getColumnModel().getColumn(8).setMaxWidth(70);
            tabelaTodosFuncionarios.getColumnModel().getColumn(10).setMinWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(10).setMaxWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(11).setMinWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(11).setMaxWidth(30);
            tabelaTodosFuncionarios.getColumnModel().getColumn(13).setMinWidth(60);
            tabelaTodosFuncionarios.getColumnModel().getColumn(13).setMaxWidth(60);
        }

        javax.swing.GroupLayout CardTodosFuncionariosLayout = new javax.swing.GroupLayout(CardTodosFuncionarios);
        CardTodosFuncionarios.setLayout(CardTodosFuncionariosLayout);
        CardTodosFuncionariosLayout.setHorizontalGroup(
            CardTodosFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardTodosFuncionariosLayout.createSequentialGroup()
                .addGroup(CardTodosFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CardTodosFuncionariosLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(CardTodosFuncionariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CardTodosFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        CardTodosFuncionariosLayout.setVerticalGroup(
            CardTodosFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardTodosFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CardTodosFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CardTela.add(CardTodosFuncionarios, "todosFuncionarios");

        jPanel42.setBackground(new java.awt.Color(254, 254, 254));

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel118.setText("Todos Pontos De Venda");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(361, Short.MAX_VALUE)
                .addComponent(jLabel118)
                .addGap(353, 353, 353))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel118)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tabelaTodosPontosDeVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Email", "Estado", "Cidade", "Cep", "Bairro", "Logradouro", "Numero", "Complemento", "Telefone"
            }
        ));
        jScrollPane3.setViewportView(tabelaTodosPontosDeVenda);
        if (tabelaTodosPontosDeVenda.getColumnModel().getColumnCount() > 0) {
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(1).setMinWidth(150);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(1).setMaxWidth(150);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(2).setMinWidth(40);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(2).setMaxWidth(40);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(4).setMinWidth(75);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(4).setMaxWidth(75);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(5).setMinWidth(90);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(5).setMaxWidth(90);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(6).setMinWidth(150);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(6).setMaxWidth(150);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(7).setMinWidth(50);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(7).setMaxWidth(50);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(8).setMinWidth(70);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(8).setMaxWidth(70);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(9).setMinWidth(120);
            tabelaTodosPontosDeVenda.getColumnModel().getColumn(9).setMaxWidth(120);
        }

        javax.swing.GroupLayout CARDTodosPontoDeVendaLayout = new javax.swing.GroupLayout(CARDTodosPontoDeVenda);
        CARDTodosPontoDeVenda.setLayout(CARDTodosPontoDeVendaLayout);
        CARDTodosPontoDeVendaLayout.setHorizontalGroup(
            CARDTodosPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDTodosPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CARDTodosPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        CARDTodosPontoDeVendaLayout.setVerticalGroup(
            CARDTodosPontoDeVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDTodosPontoDeVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );

        CardTela.add(CARDTodosPontoDeVenda, "todosPontoDeVenda");

        jPanel43.setBackground(new java.awt.Color(254, 254, 254));

        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel119.setText("Todos Produtos Geral");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap(377, Short.MAX_VALUE)
                .addComponent(jLabel119)
                .addGap(353, 353, 353))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel119)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tabelaTodosProdutosGeral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categoria", "CodigoBarras", "Cor", "Descricao", "Modelo", "Preço", "qtd", "Tamanho", "Tipo", "pvId"
            }
        ));
        jScrollPane4.setViewportView(tabelaTodosProdutosGeral);
        if (tabelaTodosProdutosGeral.getColumnModel().getColumnCount() > 0) {
            tabelaTodosProdutosGeral.getColumnModel().getColumn(0).setMinWidth(30);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(0).setMaxWidth(30);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(1).setMinWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(1).setMaxWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(2).setMinWidth(110);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(2).setMaxWidth(110);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(3).setMinWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(3).setMaxWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(4).setMinWidth(200);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(4).setMaxWidth(200);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(5).setMinWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(5).setMaxWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(6).setMinWidth(60);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(6).setMaxWidth(60);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(7).setMinWidth(50);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(7).setMaxWidth(50);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(8).setMinWidth(65);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(8).setMaxWidth(65);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(9).setMinWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(9).setMaxWidth(80);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(10).setMinWidth(35);
            tabelaTodosProdutosGeral.getColumnModel().getColumn(10).setMaxWidth(35);
        }

        javax.swing.GroupLayout CARDTodosProdutosGeralLayout = new javax.swing.GroupLayout(CARDTodosProdutosGeral);
        CARDTodosProdutosGeral.setLayout(CARDTodosProdutosGeralLayout);
        CARDTodosProdutosGeralLayout.setHorizontalGroup(
            CARDTodosProdutosGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDTodosProdutosGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CARDTodosProdutosGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        CARDTodosProdutosGeralLayout.setVerticalGroup(
            CARDTodosProdutosGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CARDTodosProdutosGeralLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );

        CardTela.add(CARDTodosProdutosGeral, "todosProdutosGeral");

        jMenu1.setText("MENU");

        jMenuItem10.setText("Tela Principal");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        Menu_Bar_PontoDeVenda.add(jMenu1);

        jMenu2.setText("Ponto De venda");

        jMenuItem7.setText("Cadastrar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Atualizar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Remover");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem11.setText("Cadastrar Funcionario no Ponto");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        Menu_Bar_PontoDeVenda.add(jMenu2);

        jMenu3.setText("Produto");

        jMenuItem4.setText("Cadastrar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem13.setText("Todos Produtos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        Menu_Bar_PontoDeVenda.add(jMenu3);

        jMenu4.setText("Funcionario");

        jMenuItem1.setText("Cadastrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Atualizar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem3.setText("Remover");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        Menu_Bar_PontoDeVenda.add(jMenu4);

        jMenu5.setText("Listagem");

        jMenuItem5.setText("Todos Funcionarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem6.setText("Todos Pontos De Venda");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem12.setText("Todos Produtos GERAL");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        Menu_Bar_PontoDeVenda.add(jMenu5);

        setJMenuBar(Menu_Bar_PontoDeVenda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CardTela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CardTela, javax.swing.GroupLayout.PREFERRED_SIZE, 779, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       
        
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "cadastroPontoDeVenda");
        
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       
        
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "CadastroProduto");
        
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "CadastroFuncionario");
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void botaoCadastrarPontoDeVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarPontoDeVendaActionPerformed
         boolean autorizado = verificaCamposPontoDeVenda();
         
         if(autorizado == false){
             return;
         }
         
         
         
         String cep = txtCepCPV.getText();
         cep = cep.replace("-", "");
         
         boolean isCadastrado = pontoDeVendaDAO.isCadastrado(cep);
         
         
        if(autorizado && !isCadastrado){
            String bairro = txtBairroCPV.getText();
            String complemento = txtComplementoCPV.getText();
            String logradouro = txtLogradouroCPV.getText();
            String numero = txtNumeroCPV.getText();
            String telefone = txtTelefoneCPV.getText();
            String email = txtEmailCPV.getText();
            Object estado = comboEstadoCPV.getSelectedItem();
            Object cidade = comboCidadeCPV.getSelectedItem();
            
            String telefoneFormatado = telefone.replace("(", "");
            telefoneFormatado = telefoneFormatado.replace(")", "");
            telefoneFormatado = telefoneFormatado.replace(" ", ";");
            String[] telefoneComDDD = telefoneFormatado.split(";");
            
            String ddd = telefoneComDDD[0];
            String numeroTel = telefoneComDDD[1];
            
            
            Telefone telefonePV = new Telefone(ddd, numeroTel);
            Endereco endereco = new Endereco(cep, logradouro, bairro, String.valueOf(cidade), String.valueOf(estado), numero, complemento);
            PontoDeVenda pontoDeVenda = new PontoDeVenda(endereco, telefonePV, email);
            
            
            int confirmar = JOptionPane.showConfirmDialog(this, "Desejar realizar o cadastro?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
            
            if(confirmar == 0){
                em.getTransaction().begin();
                pontoDeVendaDAO.cadastrar(pontoDeVenda);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "CADASTRADO COM SUCESSO!");
                limpaFormCadastroPontoDeVenda();
                resetaCombosBox();
            }
            
        } else{
            
            if(isCadastrado && autorizado){
               JOptionPane.showMessageDialog(this, "Ponto de Venda cadastrado!", "JA CADASTRADO", 2);
            }
            
        }
        
    }//GEN-LAST:event_botaoCadastrarPontoDeVendaActionPerformed

     private void limpaFormCadastroPontoDeVenda(){
        txtBairroCPV.setText("");
        txtComplementoCPV.setText("");
        txtLogradouroCPV.setText("");
        txtNumeroCPV.setText("");
        txtTelefoneCPV.setValue(null);
        txtEmailCPV.setText("");
        comboCidadeCPV.setSelectedIndex(0);
        comboEstadoCPV.setSelectedIndex(0);
        comboCidadeCPV.setSelectedItem(0);
        txtCepCPV.setValue(null);
    }
    
     public boolean verificaCamposPontoDeVenda(){
        
        String bairro = txtBairroCPV.getText();
        String cep = txtCepCPV.getText();
        String complemento = txtComplementoCPV.getText();
        String logradouro = txtLogradouroCPV.getText();
        String numero = txtNumeroCPV.getText();
        String telefone = txtTelefoneCPV.getText();
        String email = txtEmailCPV.getText();
        Object comboCidade = comboCidadeCPV.getSelectedItem();
        Object estado = comboEstadoCPV.getSelectedItem();
        
        
        if(bairro.trim().isEmpty() ||
           cep.trim().isEmpty() ||
           complemento.trim().isEmpty() ||
           logradouro.trim().isEmpty() ||
           numero.trim().isEmpty() ||
           telefone.trim().isEmpty() ||
           email.trim().isEmpty() ||
           comboCidade.equals("Selecione") ||
           estado.equals("Selecione")
                ) {
            
            JOptionPane.showMessageDialog(this, "PREENCHA TODOS OS CAMPOS!", "DADOS INCOMPLETOS", 2);
            return false;
            
        } else{
            return true;
        }
        
        
    }
    
    private void txtDescricaoProdutoCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoProdutoCPActionPerformed
       
    }//GEN-LAST:event_txtDescricaoProdutoCPActionPerformed

    private void comboCategoriaCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaCPActionPerformed

    private void comboTipoCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoCPActionPerformed

    private void comboTamanhoCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTamanhoCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTamanhoCPActionPerformed

    private void comboModeloCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModeloCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboModeloCPActionPerformed

    private void txtCodigoBarrasCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarrasCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarrasCPActionPerformed

    private void botaoCadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarProdutoActionPerformed
        boolean autorizado = verificaCamposCadastroProduto();
        
        if(autorizado == false){
            return;
        }
        
        
        
        String descricao = txtDescricaoProdutoCP.getText();
        String codigoBarras = txtCodigoBarrasCP.getText();
        String categoria = String.valueOf(comboCategoriaCP.getSelectedItem());
        String tipo = String.valueOf(comboTipoCP.getSelectedItem());
        String tamanho = String.valueOf(comboTamanhoCP.getSelectedItem());
        String modelo = String.valueOf(comboModeloCP.getSelectedItem());
        String cor = String.valueOf(comboCorCP.getSelectedItem());
        String pontoDeVenda = String.valueOf(comboPontoDeVendaCP.getSelectedItem());
        String quantidade = txtQuantidadeCP.getText();
        String preco = txtPrecoCP.getText();
        preco = preco.replace(",", ".");
        
        String[] pv = pontoDeVenda.split("-");
        String cepPontoDeVenda = pv[0];
        
        PontoDeVenda pontoDeVendaOBJ = buscarPontoDeVenda(cepPontoDeVenda);
        
        Modelo modeloEnum = Modelo.valueOf(modelo);
        Cor corEnum = Cor.valueOf(cor);
        Tamanho tamanhoEnum = Tamanho.valueOf(tamanho);
        Tipo tipoEnum = Tipo.valueOf(tipo);
        Categoria categoriaEnum = Categoria.valueOf(categoria);
        Integer quantidadeInteger = Integer.valueOf(quantidade);
        BigDecimal precoBD = new BigDecimal(preco);
        
        
        Produto produto = new Produto();
        
        produto.setCodigoDeBarras(Long.valueOf(codigoBarras));
        produto.setPontoDeVenda(pontoDeVendaOBJ);
        produto.setModelo(modeloEnum);
        produto.setCor(corEnum);
        produto.setTamanho(tamanhoEnum);
        produto.setTipo(tipoEnum);
        
        
        
        
        //boolean isCadastrado = produtoDAO.isCadastrado(produto);
        
        if(autorizado){ // && !isCadastrado
            produto.setDescricao(descricao);
            produto.setCategoria(categoriaEnum);
            produto.setQuantidade(quantidadeInteger);
            produto.setPreco(precoBD);
                
            
            
            int confirmar = JOptionPane.showConfirmDialog(this, "Desejar realizar o cadastro?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
            
            try{
                if(confirmar == 0){
                       
                    em.getTransaction().begin();
                    produtoDAO.cadastrar(produto);
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(this, "PRODUTO CADASTRADO COM SUCESSO!");
                    limpaFormCadastroProduto();
                    
                }
            } catch(ProdutoJaCadastradoException e){
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(this, "PRODUTO JA CADASTRADO!");
                return;
            }
            
        }

        
    }//GEN-LAST:event_botaoCadastrarProdutoActionPerformed
    
    private void limpaFormCadastroProduto(){
        txtDescricaoProdutoCP.setText("");
        txtCodigoBarrasCP.setText("");
        comboCategoriaCP.setSelectedIndex(0);
        comboTipoCP.setSelectedIndex(0);
        comboTamanhoCP.setSelectedIndex(0);
        comboModeloCP.setSelectedIndex(0);
        comboCorCP.setSelectedIndex(0);
        comboPontoDeVendaCP.setSelectedIndex(0);
        txtQuantidadeCP.setText("");
        txtPrecoCP.setText("");
    }
    
    
    public PontoDeVenda buscarPontoDeVenda(String cep){
        PontoDeVenda pontoDeVendaOBJ;
        try{
            pontoDeVendaOBJ = pontoDeVendaDAO.buscarPontoDeVendaPorCEP(cep.trim());
            return pontoDeVendaOBJ;
        } catch(NaoEncontradoException e){
            pontoDeVendaOBJ = null;
            System.out.println("Nao encontrado");
            return pontoDeVendaOBJ;
        }
    }
    
    public boolean verificaCamposCadastroProduto(){
        
        String descricao = txtDescricaoProdutoCP.getText();
        String codigoBarras = txtCodigoBarrasCP.getText();
        Object categoria = comboCategoriaCP.getSelectedItem();
        Object tipo = comboTipoCP.getSelectedItem();
        Object tamanho = comboTamanhoCP.getSelectedItem();
        Object modelo = comboModeloCP.getSelectedItem();
        Object cor = comboCorCP.getSelectedItem();
        Object pontoDeVenda = comboPontoDeVendaCP.getSelectedItem();
        String quantidade = txtQuantidadeCP.getText();
        String preco = txtPrecoCP.getText();
        
        
        
        if( descricao.trim().isEmpty() ||
            codigoBarras.trim().isEmpty() ||
            categoria.equals("Selecione") ||
            tipo.equals("Selecione") ||
            tamanho.equals("Selecione") ||
            modelo.equals("Selecione") ||
            cor.equals("Selecione") ||
            pontoDeVenda.equals("Selecione") ||
            quantidade.trim().isEmpty() ||
            preco.trim().isEmpty() ||
            preco.equals("0")
                ) {
            
            JOptionPane.showMessageDialog(this, "PREENCHA TODOS OS CAMPOS!", "DADOS INCOMPLETOS", 2);
            return false;
            
        } else{
            return true;
        }
        
        
    }
    
    
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      
        
        String cep = txtCepCF.getText();
        cep = cep.replace("-", "");
        cep = cep.trim();
        
        if(!cep.isEmpty()){
            try {
                ViaCEPEndereco endereco = viaCepUtil.getEndereco(cep);
                
                System.out.println(endereco.getLocalidade());
                txtBairroCF.setText(endereco.getBairro());
                txtLogradouroCF.setText(endereco.getLogradouro());
                comboCidadeCF.setSelectedItem(endereco.getLocalidade());
                comboEstadoCF.setSelectedItem(endereco.getUf());
                
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "INFORME UM CEP VALIDO");
            }
               
        } else {
            JOptionPane.showMessageDialog(this, "INFORME O CEP!");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String cep = txtCepCPV.getText();
        cep = cep.trim();
        cep = cep.replace("-", "");
        
        
        if(!cep.isEmpty()){
            try {
                ViaCEPEndereco endereco = viaCepUtil.getEndereco(cep);
                
                System.out.println(endereco.getLocalidade());
                txtBairroCPV.setText(endereco.getBairro());
                txtLogradouroCPV.setText(endereco.getLogradouro());
                comboCidadeCPV.setSelectedItem(endereco.getLocalidade());
                comboEstadoCPV.setSelectedItem(endereco.getUf());
                
                
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "INFORME UM CEP VALIDO");
            }
               
        } else {
            JOptionPane.showMessageDialog(this, "INFORME O CEP!");
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botaoCadastrarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarFuncionarioActionPerformed
        boolean autorizado = verificaCamposCadastroFuncionario();
        
        if(autorizado == false){
            return;
        }
        
        String nome = txtNomeCF.getText();
        String cep = txtCepCF.getText();
        cep = cep.replace("-", "");
        String bairro = txtBairroCF.getText();
        String complemento = txtComplementoCF.getText();
        String logradouro = txtLogradouroCF.getText();
        String numero = txtNumeroCF.getText();
        String telefone = txtTelefoneCF.getText();
        String email = txtEmailCF.getText();
        Object estado = comboEstadoCF.getSelectedItem();
        Object cidade = comboCidadeCF.getSelectedItem();
        String sexo = String.valueOf(comboSexoCF.getSelectedItem());
        Sexo sexoEnum = Sexo.valueOf(sexo);
        String redeSocial = txtRedeSocialCF.getText();
        String dataNascimento = txtDataNascimentoCF.getText();
        Object comboPontoDeVenda = comboPontoDeVendaCF.getSelectedItem();
        
        
        String telefoneFormatado = telefone.replace("(", "");
        telefoneFormatado = telefoneFormatado.replace(")", "");
        telefoneFormatado = telefoneFormatado.replace(" ", ";");
        String[] telefoneComDDD = telefoneFormatado.split(";");
            
        String ddd = telefoneComDDD[0];
        String numeroTel = telefoneComDDD[1];
        
            
        Telefone telefoneFC = new Telefone(ddd, numeroTel);
        Endereco endereco = new Endereco(cep, logradouro, bairro, String.valueOf(cidade), String.valueOf(estado), numero, complemento);
        
        String cpf = txtCpfCF.getText().trim();
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        
        String pontoDeVendaString = String.valueOf(comboPontoDeVenda);
        String[] splitPonto = pontoDeVendaString.split("-");
        String pontoDeVendaCEP= splitPonto[0];
        
        
        PontoDeVenda pontoDeVenda = buscarPontoDeVenda(pontoDeVendaCEP);
        
        
        LocalDate dataNascimentoFormatada = retornaDataNascimentoDate(dataNascimento);
        
        if(dataNascimentoFormatada == null && !dataIsValida(dataNascimentoFormatada)){
            autorizado = false;
            JOptionPane.showMessageDialog(this, "DATA DE NASCIMENTO INCORRETA!", "DATA DE NASCIMENTO INCORRETA", 2);
            return;
        }
        
        
        
//      (Long cpf, String nome, Sexo sexo, Endereco endereco, LocalDate dataNascimento, Telefone telefone,
//			String email, String redeSocial)  
        
        
        if(autorizado){
            
            int confirmar = JOptionPane.showConfirmDialog(this, "Desejar realizar o cadastro?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
            Funcionario funcionario = new Funcionario(Long.valueOf(cpf), nome, sexoEnum, endereco, dataNascimentoFormatada, telefoneFC, email, redeSocial);
            try{
            if(confirmar == 0){
                
                
                em.getTransaction().begin();
                funcionarioDAO.cadastrarFuncionarioAoPontoDeVenda(funcionario, pontoDeVenda);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "CADASTRADO COM SUCESSO!");
                limpaFormCadastroFuncionario();
               
            } 
            } catch (FuncionarioJaCadastradoException e){
                JOptionPane.showMessageDialog(this, "FUNCIONARIO COM O CPF " + funcionario.getCpf() + " JA CADASTRADO");
            }
        }
        
        
        
    }//GEN-LAST:event_botaoCadastrarFuncionarioActionPerformed
    
    
    
    private void botaoLimparCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparCFActionPerformed
        
        limpaFormCadastroFuncionario();
        
    }//GEN-LAST:event_botaoLimparCFActionPerformed

    private void botaoCadastrarPontoDeVenda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarPontoDeVenda1ActionPerformed
        boolean autorizado = verificaCamposAtualizarPV();
        
        String bairro = txtBairroAPV.getText();
        String cep = txtCepAPV.getText();
        String complemento = txtComplementoAPV.getText();
        String logradouro = txtLogradouroAPV.getText();
        String numero = txtNumeroAPV.getText();
        String telefone = txtTelefoneAPV.getText().trim();
        String email = txtEmailAPV.getText();
        Object comboCidade = comboCidadeAPV.getSelectedItem();
        Object estado = comboEstadoAPV.getSelectedItem();
        String id = txtIDPontoDeVendaAPV.getText();
        
        //(Endereco endereco, Telefone telefone, String email)
        //(String CEP, String logradouro, String Bairro, String cidade, String estado, String num, String complemento)
       
        
        telefone = telefone.replace("(", "");
        telefone = telefone.replace(")", "");
        
        
        String ddd = telefone.substring(0, 2).trim();
        String telefoneNum = telefone.substring(2).trim();
        
        
        Endereco endereco = new Endereco(cep, logradouro, bairro, String.valueOf(comboCidade), String.valueOf(estado), numero, complemento); 
        Telefone telefoneOBJ = new Telefone(ddd, telefoneNum);
        
        
        PontoDeVenda pontoDeVenda = new PontoDeVenda(endereco, telefoneOBJ, email);
        pontoDeVenda.setId(Long.valueOf(id));
        
        if(autorizado == true){
            int confirmar = JOptionPane.showConfirmDialog(this, "Desejar atualizar?" , "Confirmar atualizacao",JOptionPane.YES_NO_OPTION);

            if(confirmar == 0){
                em.getTransaction().begin();
                pontoDeVendaDAO.atualizarPontoDeVenda(pontoDeVenda);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "ATUALIZADO COM SUCESSO!");
                limpaCamposAtualizaPv();
                enabledCamposAtualizaPontoDeVenda(false);
            }
        }
        
        
    }//GEN-LAST:event_botaoCadastrarPontoDeVenda1ActionPerformed
    
    public void limpaCamposAtualizaPv(){
        comboPontosDeVendaAtualizarPV.setSelectedIndex(0);
        txtBairroAPV.setText("");
        txtComplementoAPV.setText("");
        txtLogradouroAPV.setText("");
        txtNumeroAPV.setText("");
        txtTelefoneAPV.setText("");
        txtEmailAPV.setText("");
        comboEstadoAPV.setSelectedIndex(0);
        comboCidadeAPV.setSelectedIndex(0);
        txtCepAPV.setText("");
        txtIDPontoDeVendaAPV.setText("");
    }
    
    
    public void enabledCamposAtualizaPontoDeVenda(boolean bol){
        txtBairroAPV.setEnabled(bol);
        txtComplementoAPV.setEnabled(bol);
        txtLogradouroAPV.setEnabled(bol);
        txtNumeroAPV.setEnabled(bol);
        txtTelefoneAPV.setEnabled(bol);
        txtEmailAPV.setEnabled(bol);
        comboEstadoAPV.setEnabled(bol);
        comboCidadeAPV.setEnabled(bol);
        txtCepAPV.setEnabled(bol);
        txtIDPontoDeVendaAPV.setEnabled(bol);
    }
    
    public boolean verificaCamposAtualizarPV(){
        String bairro = txtBairroAPV.getText();
        String cep = txtCepAPV.getText();
        String complemento = txtComplementoAPV.getText();
        String logradouro = txtLogradouroAPV.getText();
        String numero = txtNumeroAPV.getText();
        String telefone = txtTelefoneAPV.getText();
        String email = txtEmailAPV.getText();
        Object comboCidade = comboCidadeAPV.getSelectedItem();
        Object estado = comboEstadoAPV.getSelectedItem();
        
         if(bairro.trim().isEmpty() ||
           cep.trim().isEmpty() ||
           complemento.trim().isEmpty() ||
           logradouro.trim().isEmpty() ||
           numero.trim().isEmpty() ||
           telefone.trim().isEmpty() ||
           email.trim().isEmpty() ||
           comboCidade.equals("Selecione") ||
           estado.equals("Selecione")
                ) {
            
            JOptionPane.showMessageDialog(this, "PREENCHA TODOS OS CAMPOS!", "DADOS INCOMPLETOS", 2);
            return false;
            
        } else{
            return true;
        }
        
        
        
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
        Object pontoDeVenda = comboPontosDeVendaAtualizarPV.getSelectedItem();
        String pontoDeVendaString = String.valueOf(pontoDeVenda);
        
        String[] splitPontoDeVenda = pontoDeVendaString.split("-");
        
        String cepPontoDeVenda = splitPontoDeVenda[0];
        
        
        try{
            PontoDeVenda pontoDeVendaOBJ = buscarPontoDeVenda(cepPontoDeVenda);
            enabledCamposAtualizaPontoDeVenda(true);
            sobrecarregaFormAtualizaPontoDeVenda(pontoDeVendaOBJ);
        } catch(NaoEncontradoException e){
            JOptionPane.showMessageDialog(this, "PONTO DE VENDA NAO ENCONTRADO!");
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    
    
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
       enabledCamposAtualizaPontoDeVenda(false);
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "atualizarPontoDeVenda");
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void comboCategoriaAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaAPActionPerformed

    private void txtDescricaoProdutoAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoProdutoAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoProdutoAPActionPerformed

    private void comboTipoAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoAPActionPerformed

    private void comboTamanhoAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTamanhoAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTamanhoAPActionPerformed

    private void comboModeloAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModeloAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboModeloAPActionPerformed

    private void txtCodigoBarrasBuscaAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarrasBuscaAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarrasBuscaAPActionPerformed

    private void botaoAtualizarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAtualizarProdutoActionPerformed

    private void botaoBuscaProdutoAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscaProdutoAPActionPerformed
      
        
//      txtCodigoBarrasBuscaAP;
//      comboPontoDeVendaAP;
//      
      
    }//GEN-LAST:event_botaoBuscaProdutoAPActionPerformed

    private void txtDescricaoTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoTPActionPerformed

    private void txtCodigoBarrasTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarrasTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarrasTPActionPerformed

    private void comboCategoriaTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaTPActionPerformed

    private void comboTamanhoTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTamanhoTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTamanhoTPActionPerformed

    private void comboModeloTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModeloTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboModeloTPActionPerformed

    private void comboTipoTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTipoTPActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    
      DefaultTableModel model = (DefaultTableModel) tabelaTodosProdutos.getModel();
      model.setRowCount(0);
      
      if(comboPontoDeVendaTP.getSelectedIndex() == 0){
          JOptionPane.showMessageDialog(this, "Escolha um ponto de venda!");
          return;
      }
      
      Object comboPontoDeVenda = comboPontoDeVendaTP.getSelectedItem();
      String pontoDeVendaString = String.valueOf(comboPontoDeVenda);
      String[] splitPonto = pontoDeVendaString.split("-");
      String pontoDeVendaCEP= splitPonto[0];
      
      
      carregaTabelaTodosProdutos(pontoDeVendaCEP);
      
        
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void carregaTabelaTodosProdutos(String cep){
        DefaultTableModel model = (DefaultTableModel) tabelaTodosProdutos.getModel();
        model.setRowCount(0);
        
        produtosTabelaTodosProdutos = produtoDAO.todosProdutosDeUmPontoDeVenda(cep);
        
        
        
        if(produtosTabelaTodosProdutos.isEmpty()){
          JOptionPane.showMessageDialog(this, "Nenhum produto no ponto de venda!");
          return;
      } else{
          txtIdPVTP.setText(String.valueOf(produtosTabelaTodosProdutos.get(0).getPontoDeVenda().getId()));
          txtCepTP.setText(cep);
        }
      
      
      produtosTabelaTodosProdutos.forEach(produto -> 
      
        model.addRow(new Object[]{
            produto.getId(),
            produto.getCategoria(),
            produto.getCodigoDeBarras(),
            produto.getCor(),
            produto.getDescricao(),
            produto.getModelo(),
            produto.getPreco(),
            produto.getQuantidade(),
            produto.getTamanho(),
            produto.getTipo(),
            produto.getPontoDeVenda().getId()

        })
      
      
      );
    }
    
    private void atualizaTabelaTodosProdutosPosAlteracao(){
        
        
        
    }
    
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        txtIdPVTP.setEnabled(false);
        enabledCamposTP(false);
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "cardProdutos");
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void botaoBuscarFuncCFPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarFuncCFPActionPerformed
        
        
        
        
        String cpf = txtCpfCFP.getText();
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        
        if(cpf.isEmpty()){
            JOptionPane.showMessageDialog(this, "INSIRA UM CPF VALIDO!");
            return;
        }
        
        Funcionario funcionario = null;
        try{
            funcionario = funcionarioDAO.buscaFuncionarioPeloCpf(Long.valueOf(cpf));
        } catch (NaoEncontradoException e){
            JOptionPane.showMessageDialog(this, "Funcionario nao cadastrado!");
            return;
        }
        
        if(funcionario != null){
            txtNomeCFP.setEditable(false);
            txtNomeCFP.setText(funcionario.getNome());
                
            txtSexoCFP.setEditable(false);
            txtSexoCFP.setText(String.valueOf(funcionario.getSexo()));
        }
        
        
    }//GEN-LAST:event_botaoBuscarFuncCFPActionPerformed
    
    
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        txtNomeCFP.setEnabled(false);
        txtSexoCFP.setEnabled(false);
        
        limparFormCadastrarFuncNoPonto();
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "cadastrarNoPonto");

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      
        
        Object comboPv = comboPontosDeVendaCFP.getSelectedItem();
        String combo = String.valueOf(comboPv);
        String[] splitCombo = combo.split("-");
        String cep = splitCombo[0];
        
        String cpf = txtCpfCFP.getText();
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        
        PontoDeVenda pv = pontoDeVendaDAO.buscarPontoDeVendaPorCEP(cep);
        Funcionario funcionario = funcionarioDAO.buscaFuncionarioPeloCpf(Long.valueOf(cpf));
        
        System.out.println(funcionario);
        
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar realizar o cadastro?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
        
        
        if(confirmar == 0){
            em.getTransaction().begin();
            pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(funcionario, pv);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "FUNCIONARIO CADASTRADO NO PONTO COM SUCESSO!");
            limparFormCadastrarFuncNoPonto();
        }
        
        
        
    }//GEN-LAST:event_jButton8ActionPerformed
    
    public void limparFormCadastrarFuncNoPonto(){
        txtCpfCFP.setValue(null);
        txtNomeCFP.setText("");
        txtSexoCFP.setText("");
        comboPontosDeVendaCFP.setSelectedIndex(0);
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int linha = tabelaTodosProdutos.getSelectedRow();
        
        DefaultTableModel model = (DefaultTableModel) tabelaTodosProdutos.getModel();
        
        if(linha == -1){
            JOptionPane.showMessageDialog(this, "Selecione um produto abaixo para editar");
            return;
        }
        
        Object id = model.getValueAt(linha, 0);
        String idProduto = String.valueOf(id);
        
        Produto produto = produtoDAO.buscarProdutoPorId(Long.valueOf(idProduto));
        
        if(produto != null){
            enabledCamposTP(true);
            txtIdTP.setText(String.valueOf(produto.getId()));
            txtCodigoBarrasTP.setText(String.valueOf(produto.getCodigoDeBarras()));
            txtDescricaoTP.setText(produto.getDescricao());
            txtQuantidadeTP.setText(String.valueOf(produto.getQuantidade()));
            txtPrecoTP.setText(String.valueOf(produto.getPreco()));
            comboCategoriaTP.setSelectedItem(String.valueOf(produto.getCategoria()));
            comboTamanhoTP.setSelectedItem(String.valueOf(produto.getTamanho()));
            comboCorTP.setSelectedItem(String.valueOf(produto.getCor()));
            comboTipoTP.setSelectedItem(String.valueOf(produto.getTipo()));
            comboModeloTP.setSelectedItem(String.valueOf(produto.getModelo()));
            
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        limparFormTP();
        tabelaTodosProdutos.clearSelection();
    }//GEN-LAST:event_jButton7ActionPerformed
    
    private void limparFormTP(){
        txtIdTP.setText("");
        txtCodigoBarrasTP.setText("");
        txtDescricaoTP.setText("");
        txtQuantidadeTP.setText("");
        txtPrecoTP.setText("");
        comboCategoriaTP.setSelectedIndex(0);
        comboTamanhoTP.setSelectedIndex(0);
        comboCorTP.setSelectedIndex(0);
        comboTipoTP.setSelectedIndex(0);
        comboModeloTP.setSelectedIndex(0);
        enabledCamposTP(false);
    }
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       int linha = tabelaTodosProdutos.getSelectedRow();
        
        DefaultTableModel model = (DefaultTableModel) tabelaTodosProdutos.getModel();
        
        if(linha == -1){
            JOptionPane.showMessageDialog(this, "Selecione um produto abaixo para remover");
            return;
        }
        
        Object id = model.getValueAt(linha, 0);
        String idProduto = String.valueOf(id);
        
        
        
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar remover o produto?" , "Remover produto",JOptionPane.YES_NO_OPTION);
        
        if(confirmar == 0){
            em.getTransaction().begin();
            produtoDAO.removerProdutoPorId(Long.valueOf(idProduto));
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
            model.removeRow(linha);
        }   
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
     
        int linha = tabelaTodosProdutos.getSelectedRow();
        
        DefaultTableModel model = (DefaultTableModel) tabelaTodosProdutos.getModel();
        
        if(linha == -1){;
            JOptionPane.showMessageDialog(this, "Selecione um produto abaixo para dar baixa");
            return;
        }
        
        Object id = model.getValueAt(linha, 0);
        String idProduto = String.valueOf(id);
        
        String qtd = JOptionPane.showInputDialog(this, "Quantidade de produtos para baixa?", "BAIXA DE PRODUTO", 3);
        
        Integer qtdInt = Integer.valueOf(qtd);
        
        
        if(qtd == null || qtdInt <= 0){
            JOptionPane.showMessageDialog(this, "Insira uma quantidade para baixa valida!");
            return;
        } else{
            try{
                em.getTransaction().begin();
                produtoDAO.darBaixa(Long.valueOf(idProduto), qtdInt);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "BAIXA COM SUCESSO!");
                carregaTabelaTodosProdutos(txtCepTP.getText());
            }
                catch(EstoqueInsuficienteException e){
                    em.getTransaction().rollback();
                    JOptionPane.showMessageDialog(this, "ESTOQUE INSUFICIENTE!");
                    return;
                }
            }
        
        
        
        
        
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String idPv = txtIdPVTP.getText();
        String id = txtIdTP.getText();
        String codigoBarras = txtCodigoBarrasTP.getText();
        String descricao = txtDescricaoTP.getText();
        String quantidade = txtQuantidadeTP.getText();
        String preco = txtPrecoTP.getText();
        
        preco = preco.replace(",", ".");
        
        Object comboCategoria = comboCategoriaTP.getSelectedItem();
        Object comboTamanho = comboTamanhoTP.getSelectedItem();
        Object comboCor = comboCorTP.getSelectedItem();
        Object comboTipo = comboTipoTP.getSelectedItem();
        Object comboModelo = comboModeloTP.getSelectedItem();
        
        Categoria categoriaEnum = Categoria.valueOf(String.valueOf(comboCategoria));
        Tamanho tamanhoEnum = Tamanho.valueOf(String.valueOf(comboTamanho));
        Cor corEnum = Cor.valueOf(String.valueOf(comboCor));
        Tipo tipoEnum = Tipo.valueOf(String.valueOf(comboTipo));
        Modelo modeloEnum = Modelo.valueOf(String.valueOf(comboModelo));
        
        PontoDeVenda pv = new PontoDeVenda();
        pv.setId(Long.valueOf(idPv));
        
        Produto produto = new Produto(descricao, Long.valueOf(codigoBarras), categoriaEnum, tipoEnum, tamanhoEnum, modeloEnum, corEnum, Integer.valueOf(quantidade), new BigDecimal(preco), pv);
        produto.setId(Long.valueOf(id));
         
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar atualizar o produto?" , "Atualizar produto",JOptionPane.YES_NO_OPTION);
        
        
        if(confirmar == 0){
            em.getTransaction().begin();
            produtoDAO.atualizar(produto);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "PRODUTO ATULIZADO COM SUCESSO!");
            limparFormTP();
            carregaTabelaTodosProdutos(txtCepTP.getText());
        }
        
        enabledCamposTP(false);
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      limpaCamposAtualizaPv();
      enabledCamposAtualizaPontoDeVenda(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
       
       
       Object pvCombo = comboPontoDeVendaRPV.getSelectedItem();
       
       if(pvCombo.equals("Selecione")){
           JOptionPane.showMessageDialog(this, "ESCOLHA UM PONTO DE VENDA!");
           return;
       }
       
       String pvString = String.valueOf(pvCombo);
       
       
       String[] splitPV = pvString.split("-");
        
       String cepPv = splitPV[0];
       
       
       Long id = null;
       int index = -1;
       for(int c = 0; c < bairrosPontosDeVenda.size(); c++){
          PontoDeVendaVo pv =  bairrosPontosDeVenda.get(c);
          if(pv.getCEP().equals(cepPv)){
              
              id = pv.getId();
              index = c;
          }
       }
       System.out.println(id);
       
       int confirmar = JOptionPane.showConfirmDialog(this, "Desejar remover o ponto de venda?" , "Confirmar remocao",JOptionPane.YES_NO_OPTION);
       
       
       if(confirmar == 0){
           em.getTransaction().begin();
           pontoDeVendaDAO.removerPorId(id);
           em.getTransaction().commit();
           JOptionPane.showMessageDialog(this, "PONTO DE VENDA REMOVIDO COM SUCESSO!");
           carregaComboPontosDeVenda();
           bairrosPontosDeVenda.remove(index);
           resetaCombosBox();
           comboPontoDeVendaRPV.setSelectedIndex(0);
       }
       
       
       
       
       
       
       
       
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
      
        
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "removerPontoDeVenda");
        
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void botaoBuscarAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarAFActionPerformed
      String cep = txtCepAF.getText();
        cep = cep.trim();
        cep = cep.replace("-", "");
        
        
        if(!cep.isEmpty()){
            try {
                ViaCEPEndereco endereco = viaCepUtil.getEndereco(cep);
                
                System.out.println(endereco.getLocalidade());
                txtBairroAF.setText(endereco.getBairro());
                txtLogradouroAF.setText(endereco.getLogradouro());
                comboCidadeAF.setSelectedItem(endereco.getLocalidade());
                comboEstadoAF.setSelectedItem(endereco.getUf());
                
                
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "INFORME UM CEP VALIDO");
            }
               
        } else {
            JOptionPane.showMessageDialog(this, "INFORME O CEP!");
        }
    }//GEN-LAST:event_botaoBuscarAFActionPerformed

    private void AtualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarFuncionarioActionPerformed
    
        String cep = txtCepAF.getText();
        cep = cep.replace("-", "");
        String nome = txtNomeAF.getText();
        String cpf = txtCpfAF.getText();
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-","");
        
        String bairro = txtBairroAF.getText();
        String complemento = txtComplementoAF.getText();
        String logradouro = txtLogradouroAF.getText();
        String numero = txtNumeroAF.getText();
        String telefone = txtTelefoneAF.getText();
        String email = txtEmailAF.getText();
        Object estadoCombo = comboEstadoAF.getSelectedItem();
        Object CidadeCombo = comboCidadeAF.getSelectedItem();
        Object sexoCombo = comboSexoAF.getSelectedItem();
        Object redeSocial = txtRedeSocialAF.getText();
        Object dataNascimento = txtDataNascimentoAF.getText();
        
        Sexo sexoEnum = Sexo.valueOf(String.valueOf(sexoCombo));
       
        String ddd = telefone.substring(0, 2);
        String numeroTel = telefone.substring(2);
        
        LocalDate data = LocalDate.parse(String.valueOf(dataNascimento));
        
        System.out.println(data);
        Telefone telefoneOBJ = new Telefone(ddd, numeroTel);
        Endereco endereco = new Endereco(cep, logradouro, bairro, String.valueOf(CidadeCombo), String.valueOf(estadoCombo),numero, complemento);
       
        Funcionario funcionario = new Funcionario(Long.valueOf(cpf), nome, sexoEnum, endereco, data, telefoneOBJ, email, String.valueOf(redeSocial));
        
        
        
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar atualizar o funcionario?" , "Confirmar atualizacao",JOptionPane.YES_NO_OPTION);
        
        if(confirmar == 0){
            
            em.getTransaction().begin();
            funcionarioDAO.atualizar(funcionario);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "ATUALIZADO COM SUCESSO!");
            limpaFormAtualizarFuncionario();
            enabledCamposAtualizarFuncionario(false);
        }
        
        
    }//GEN-LAST:event_AtualizarFuncionarioActionPerformed
    
    
    private void limpaFormAtualizarFuncionario(){
        txtCepAF.setValue(null);
        txtNomeAF.setText("");
        txtCpfAF.setText("");
        txtBairroAF.setText("");
        txtComplementoAF.setText("");
        txtLogradouroAF.setText("");
        txtNumeroAF.setText("");
        txtTelefoneAF.setText("");
        txtEmailAF.setText("");
        comboEstadoAF.setSelectedIndex(0);
        comboCidadeAF.setSelectedIndex(0);
        comboSexoAF.setSelectedIndex(0);
        txtRedeSocialAF.setText("");
        txtDataNascimentoAF.setText("");
        txtBuscaFuncionarioAF.setValue(null);
    }
    
    public void enabledCamposAtualizarFuncionario(boolean bol){
        txtCepAF.setEnabled(bol);
        txtNomeAF.setEnabled(bol);
        txtCpfAF.setEnabled(false);
        txtBairroAF.setEnabled(bol);
        txtComplementoAF.setEnabled(bol);
        txtLogradouroAF.setEnabled(bol);
        txtNumeroAF.setEnabled(bol);
        txtTelefoneAF.setEnabled(bol);
        txtEmailAF.setEnabled(bol);
        comboEstadoAF.setEnabled(bol);
        comboCidadeAF.setEnabled(bol);
        comboSexoAF.setEnabled(bol);
        txtRedeSocialAF.setEnabled(bol);
        txtDataNascimentoAF.setEnabled(bol);
        
    }
    
    
    private void botaoLimparCF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparCF1ActionPerformed
       limpaFormAtualizarFuncionario();
       enabledCamposAtualizarFuncionario(false);
    }//GEN-LAST:event_botaoLimparCF1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
      
        
        String cpfBuscado = txtBuscaFuncionarioAF.getText();
        cpfBuscado = cpfBuscado.replace(".", "");
        cpfBuscado = cpfBuscado.replace("-", "");
        
        try{
        Funcionario funcionario = funcionarioDAO.buscaFuncionarioPeloCpf(Long.valueOf(cpfBuscado));
        
        preencheCamposAtualizaFuncionario(funcionario);
        enabledCamposAtualizarFuncionario(true);

        } catch(NaoEncontradoException e){
            JOptionPane.showMessageDialog(this, "FUNCIONARIO NAO CADASTRADO!");
        }
        
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        enabledCamposAtualizarFuncionario(false);
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "AtualizarFuncionario");
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtDataNascimentoAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoAFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoAFActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
     
        
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "cardTelaPrincipal");
        
        
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
      String cpf = txtCpfRF.getText();
      cpf = cpf.replace(".", "");
      cpf = cpf.replace("-", "");
      
      Funcionario funcionario = null;
      
      try{
      funcionario = funcionarioDAO.buscaFuncionarioPeloCpf(Long.valueOf(cpf));
      } catch(NaoEncontradoException e){
          JOptionPane.showMessageDialog(this, "FUNCIONARIO NAO CADASTRADO!");
          return;
        }
      
      
      int confirmar = JOptionPane.showConfirmDialog(this, "Desejar remover o funcionario?" , "Remover Funcionario",JOptionPane.YES_NO_OPTION);
      
      if(confirmar == 0){
          em.getTransaction().begin();
          funcionarioDAO.removePeloCpf(Long.valueOf(cpf));
          em.getTransaction().commit();
          JOptionPane.showMessageDialog(this, "O(A) FUNCIONARIO(A) " + funcionario.getNome() + " FOI REMOVIDO(A) COM SUCESSO!");
          txtCpfRF.setValue(null);
      }
      
      
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "removerFuncionario");
        
        
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        tabelaTodosFuncionarios.setEnabled(false);
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "todosFuncionarios");
        
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        tabelaTodosPontosDeVenda.setEnabled(false);
        preencheTabelaTodosPontosDeVenda();
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "todosPontoDeVenda");
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    
    
    
    private void preencheTabelaTodosPontosDeVenda(){
        
        List<PontoDeVenda> todos = pontoDeVendaDAO.listarTodos();
        
        DefaultTableModel model = (DefaultTableModel) tabelaTodosPontosDeVenda.getModel();
        
        model.setRowCount(0);
        
        todos.forEach(pv -> {
        
            model.addRow( new Object[]{
                
                pv.getId(),
                pv.getEmail(),
                pv.getEndereco().getEstado(),
                pv.getEndereco().getCidade(),
                pv.getEndereco().getCEP(),
                pv.getEndereco().getBairro(),
                pv.getEndereco().getLogradouro(),
                pv.getEndereco().getNumero(),
                pv.getEndereco().getComplemento(),
                '(' + pv.getTelefone().getDdd() + ')' + " " + pv.getTelefone().getNumero()
            
            });
        
        });

        
        
    }
    
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        
        
        tabelaTodosProdutosGeral.setEnabled(false);
        preencheTabelaTodosProdutosGeral();
        CardLayout cl = (CardLayout) CardTela.getLayout();
        cl.show(CardTela, "todosProdutosGeral");        
           
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
      
    
        List<Funcionario> todos = funcionarioDAO.todosFuncionarios();
        
        
        DefaultTableModel model = (DefaultTableModel)  tabelaTodosFuncionarios.getModel();
        model.setRowCount(0);

        if(todos.isEmpty()){
            JOptionPane.showMessageDialog(this, "NENHUM FUNCIONARIO CADASTRADO!");
            return;
        }
        
        todos.forEach(funcionario -> {
            
            model.addRow(
                    new Object[]{
                        funcionario.getCpf(),
                        funcionario.getNome(),
                        String.valueOf(funcionario.getSexo()).substring(0, 4),
                        funcionario.getDataNascimento(),
                        funcionario.getEmail(),
                        funcionario.getEndereco().getCEP(),
                        funcionario.getEndereco().getEstado(),
                        funcionario.getEndereco().getCidade(),
                        funcionario.getEndereco().getBairro(),
                        funcionario.getEndereco().getLogradouro(),
                        funcionario.getEndereco().getNumero(),
                        funcionario.getEndereco().getComplemento(),
                        '(' + funcionario.getTelefone().getDdd() + ')' + " " + funcionario.getTelefone().getNumero(),
                        funcionario.getRedeSocial()
                    }
            );
        });
        
        
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       
        Object comboPv = comboPontoDeVendaTF.getSelectedItem();
        String[] splitPv = String.valueOf(comboPv).split("-");
        String cep = splitPv[0];
        List<Funcionario> todos = funcionarioDAO.buscarTodosFuncionariosDeUmPontoPorCpf(cep);
        
        
        
        DefaultTableModel model = (DefaultTableModel)  tabelaTodosFuncionarios.getModel();
        model.setRowCount(0);
        
        if(todos.isEmpty()){
            JOptionPane.showMessageDialog(this, "NENHUM FUNCIONARIO NO PONTO!");
            return;
        }
        
        todos.forEach(funcionario -> {
            
            model.addRow(
                    new Object[]{
                        funcionario.getCpf(),
                        funcionario.getNome(),
                        String.valueOf(funcionario.getSexo()).substring(0, 4),
                        funcionario.getDataNascimento(),
                        funcionario.getEmail(),
                        funcionario.getEndereco().getCEP(),
                        funcionario.getEndereco().getEstado(),
                        funcionario.getEndereco().getCidade(),
                        funcionario.getEndereco().getBairro(),
                        funcionario.getEndereco().getLogradouro(),
                        funcionario.getEndereco().getNumero(),
                        funcionario.getEndereco().getComplemento(),
                        '(' + funcionario.getTelefone().getDdd() + ')' + " " + funcionario.getTelefone().getNumero(),
                        funcionario.getRedeSocial()
                    }
            );
        });
        
        
        
                
                
    }//GEN-LAST:event_jButton16ActionPerformed
    
    private void preencheTabelaTodosProdutosGeral(){
        
        
        List<Produto> produtos = produtoDAO.listarTodos();
        
        DefaultTableModel model = (DefaultTableModel) tabelaTodosProdutosGeral.getModel();
        model.setRowCount(0);
        
        
        produtos.forEach(produto -> {
            
            model.addRow(
                    new Object[]{
                        produto.getId(),
                        produto.getCategoria(),
                        produto.getCodigoDeBarras(),
                        produto.getCor(),
                        produto.getDescricao(),
                        produto.getModelo(),
                        produto.getPreco(),
                        produto.getQuantidade(),
                        produto.getTamanho(),
                        produto.getTipo(),
                        produto.getPontoDeVenda().getId()
                    }
                    
            );
            
        
        
        });
        
        
        
        
    }
    
    private void preencheCamposAtualizaFuncionario(Funcionario funcionario){
        txtCepAF.setText(funcionario.getEndereco().getCEP());
        txtNomeAF.setText(funcionario.getNome());
        txtCpfAF.setText(String.valueOf(funcionario.getCpf()));
        txtBairroAF.setText(funcionario.getEndereco().getBairro());
        txtComplementoAF.setText(funcionario.getEndereco().getComplemento());
        txtLogradouroAF.setText(funcionario.getEndereco().getLogradouro());
        txtNumeroAF.setText(funcionario.getEndereco().getNumero());
        txtTelefoneAF.setText(String.valueOf(funcionario.getTelefone().getDdd()) + " " + String.valueOf(funcionario.getTelefone().getNumero()));
        txtEmailAF.setText(funcionario.getEmail());
        comboEstadoAF.setSelectedItem(funcionario.getEndereco().getEstado());
        comboCidadeAF.setSelectedItem(funcionario.getEndereco().getCidade());
        comboSexoAF.setSelectedItem(String.valueOf(funcionario.getSexo()));
        txtRedeSocialAF.setText(funcionario.getRedeSocial());
        String dataNasc = String.valueOf(funcionario.getDataNascimento());
        
//        LocalDate data = LocalDate.parse(dataNasc, formatoData);
//        System.out.println(data);
//        
        txtDataNascimentoAF.setText(dataNasc);
        
        
        
    }
    
    public void enabledCamposTP(boolean bol){
            txtCepTP.setEnabled(false);
            txtIdTP.setEnabled(false);
            txtCodigoBarrasTP.setEnabled(bol);
            txtDescricaoTP.setEnabled(bol);
            txtQuantidadeTP.setEnabled(bol);
            txtPrecoTP.setEnabled(bol);
            comboCategoriaTP.setEnabled(bol);
            comboTamanhoTP.setEnabled(bol);
            comboCorTP.setEnabled(bol);
            comboTipoTP.setEnabled(bol);
            comboModeloTP.setEnabled(bol);
    }
    
    public void sobrecarregaFormAtualizaPontoDeVenda(PontoDeVenda pv){
        txtBairroAPV.setText(pv.getEndereco().getBairro());
        txtComplementoAPV.setText(pv.getEndereco().getComplemento());
        txtLogradouroAPV.setText(pv.getEndereco().getLogradouro());
        txtNumeroAPV.setText(pv.getEndereco().getNumero());
        txtTelefoneAPV.setText(pv.getTelefone().getDdd()+ " " + pv.getTelefone().getNumero());
        txtEmailAPV.setText(pv.getEmail());
        comboEstadoAPV.setSelectedItem(pv.getEndereco().getEstado());
        comboCidadeAPV.setSelectedItem(pv.getEndereco().getCidade());
        txtCepAPV.setText(pv.getEndereco().getCEP());
        txtIDPontoDeVendaAPV.setText(String.valueOf(pv.getId()));
        txtIDPontoDeVendaAPV.setEditable(false);
    }
    
    public void limpaFormCadastroFuncionario(){
        txtNomeCF.setText("");
        txtCepCF.setValue(null);
        txtBairroCF.setText("");
        txtComplementoCF.setText("");
        txtLogradouroCF.setText("");
        txtNumeroCF.setText("");
        txtTelefoneCF.setValue(null);
        txtEmailCF.setText("");
        comboEstadoCF.setSelectedIndex(0);
        comboCidadeCF.setSelectedIndex(0);
        comboSexoCF.setSelectedIndex(0);
        txtRedeSocialCF.setText("");
        txtDataNascimentoCF.setValue(null);
        comboPontoDeVendaCF.setSelectedIndex(0);
        txtCpfCF.setValue(null);
        
    }
    
    public boolean verificaCamposCadastroFuncionario(){
        
        String nome = txtNomeCF.getText();
        String cpf = txtCpfCF.getText();
        String dataNascimento = txtDataNascimentoCF.getText();
        Object comboPontoDeVenda = comboPontoDeVendaCF.getSelectedItem();
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        
        dataNascimento = dataNascimento.replace("/", "");
        
        
        
        String cep = txtCepCF.getText();
        cep = cep.replace("-", "");
        String bairro = txtBairroCF.getText();
        String complemento = txtComplementoCF.getText();
        String logradouro = txtLogradouroCF.getText();
        String numero = txtNumeroCF.getText();
        String telefone = txtTelefoneCF.getText();
        String email = txtEmailCF.getText();
        Object estado = comboEstadoCF.getSelectedItem();
        Object cidade = comboCidadeCF.getSelectedItem();
        Object sexo = comboSexoCF.getSelectedItem();
        String redeSocial = txtRedeSocialCF.getText();
        
        if( nome.trim().isEmpty() ||
            cpf.trim().isEmpty() ||
            dataNascimento.trim().isEmpty() ||
            cep.isEmpty() ||
            bairro.isEmpty() ||
            complemento.isEmpty() ||
            logradouro.isEmpty() ||
            numero.isEmpty() ||
            telefone.length() != 15 ||
            email.isEmpty() ||
            estado.equals("Selecione") ||
            cidade.equals("Selecione") ||
            comboPontoDeVenda.equals("Selecione") ||
            sexo.equals("Selecione") ||
            redeSocial.isEmpty()
                ){
            JOptionPane.showMessageDialog(this, "DADOS INCOMPLETOS", "DADOS INCOMPLETOS", 2);
            return false;
        } else {
            return true;
        }
        
        
        
    }
    
    public LocalDate retornaDataNascimentoDate(String dataNascimento){
        LocalDate dataDeNascimentoFormatada = null;
        try{
               dataDeNascimentoFormatada = LocalDate.parse(dataNascimento, formatoData);
            } catch(DateTimeParseException | NullPointerException e){
               JOptionPane.showMessageDialog(this, "DATA DE NASCIMENTO INCORRETA!", "DATA DE NASCIMENTO INCORRETA", 2);
            }
            
            
        return dataDeNascimentoFormatada;
    }
    
    
    private boolean dataIsValida(LocalDate data){
        
        if(data == null){
            return false;
        }
        
        int ano = data.getYear();
        
        if(ano >= 1950){
            return true;
        } else{
        return false;
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtualizarFuncionario;
    private javax.swing.JPanel CARDAtualizaFuncionario;
    private javax.swing.JPanel CARDAtualizaPontoDeVenda;
    private javax.swing.JPanel CARDAtualizaProduto;
    private javax.swing.JPanel CARDCadastrarPontoDeVenda;
    private javax.swing.JPanel CARDCadastrarProduto;
    private javax.swing.JPanel CARDCadastroFuncionario;
    private javax.swing.JPanel CARDRemoverFuncionario;
    private javax.swing.JPanel CARDRemoverPontoDeVenda;
    private javax.swing.JPanel CARDTodosPontoDeVenda;
    private javax.swing.JPanel CARDTodosProdutosGeral;
    private javax.swing.JPanel CadastrarFuncionarioNoPonto;
    private javax.swing.JPanel CardTela;
    private javax.swing.JPanel CardTelaPrincipal;
    private javax.swing.JPanel CardTodosFuncionarios;
    private javax.swing.JMenuBar Menu_Bar_PontoDeVenda;
    private javax.swing.JPanel TodosProdutos;
    private javax.swing.JButton botaoAtualizarProduto;
    private javax.swing.JButton botaoBuscaProdutoAP;
    private javax.swing.JButton botaoBuscarAF;
    private javax.swing.JButton botaoBuscarFuncCFP;
    private javax.swing.JButton botaoCadastrarFuncionario;
    private javax.swing.JButton botaoCadastrarPontoDeVenda;
    private javax.swing.JButton botaoCadastrarPontoDeVenda1;
    private javax.swing.JButton botaoCadastrarProduto;
    private javax.swing.JButton botaoLimparCF;
    private javax.swing.JButton botaoLimparCF1;
    private javax.swing.JComboBox<String> comboCategoriaAP;
    private javax.swing.JComboBox<String> comboCategoriaCP;
    private javax.swing.JComboBox<String> comboCategoriaTP;
    private javax.swing.JComboBox<String> comboCidadeAF;
    private javax.swing.JComboBox<String> comboCidadeAPV;
    private javax.swing.JComboBox<String> comboCidadeCF;
    private javax.swing.JComboBox<String> comboCidadeCPV;
    private javax.swing.JComboBox<String> comboCorAP;
    private javax.swing.JComboBox<String> comboCorCP;
    private javax.swing.JComboBox<String> comboCorTP;
    private javax.swing.JComboBox<String> comboEstadoAF;
    private javax.swing.JComboBox<String> comboEstadoAPV;
    private javax.swing.JComboBox<String> comboEstadoCF;
    private javax.swing.JComboBox<String> comboEstadoCPV;
    private javax.swing.JComboBox<String> comboModeloAP;
    private javax.swing.JComboBox<String> comboModeloCP;
    private javax.swing.JComboBox<String> comboModeloTP;
    private javax.swing.JComboBox<String> comboPontoDeVendaAP;
    private javax.swing.JComboBox<String> comboPontoDeVendaCF;
    private javax.swing.JComboBox<String> comboPontoDeVendaCP;
    private javax.swing.JComboBox<String> comboPontoDeVendaRPV;
    private javax.swing.JComboBox<String> comboPontoDeVendaTF;
    private javax.swing.JComboBox<String> comboPontoDeVendaTP;
    private javax.swing.JComboBox<String> comboPontosDeVendaAtualizarPV;
    private javax.swing.JComboBox<String> comboPontosDeVendaCFP;
    private javax.swing.JComboBox<String> comboSexoAF;
    private javax.swing.JComboBox<String> comboSexoCF;
    private javax.swing.JComboBox<String> comboTamanhoAP;
    private javax.swing.JComboBox<String> comboTamanhoCP;
    private javax.swing.JComboBox<String> comboTamanhoTP;
    private javax.swing.JComboBox<String> comboTipoAP;
    private javax.swing.JComboBox<String> comboTipoCP;
    private javax.swing.JComboBox<String> comboTipoTP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabelaTodosFuncionarios;
    private javax.swing.JTable tabelaTodosPontosDeVenda;
    private javax.swing.JTable tabelaTodosProdutos;
    private javax.swing.JTable tabelaTodosProdutosGeral;
    private javax.swing.JTextField txtBairroAF;
    private javax.swing.JTextField txtBairroAPV;
    private javax.swing.JTextField txtBairroCF;
    private javax.swing.JTextField txtBairroCPV;
    private javax.swing.JFormattedTextField txtBuscaFuncionarioAF;
    private javax.swing.JFormattedTextField txtCepAF;
    private javax.swing.JTextField txtCepAPV;
    private javax.swing.JFormattedTextField txtCepCF;
    private javax.swing.JFormattedTextField txtCepCPV;
    private javax.swing.JTextField txtCepTP;
    private javax.swing.JTextField txtCodigoBarrasBuscaAP;
    private javax.swing.JTextField txtCodigoBarrasCP;
    private javax.swing.JTextField txtCodigoBarrasTP;
    private javax.swing.JTextField txtComplementoAF;
    private javax.swing.JTextField txtComplementoAPV;
    private javax.swing.JTextField txtComplementoCF;
    private javax.swing.JTextField txtComplementoCPV;
    private javax.swing.JTextField txtCpfAF;
    private javax.swing.JFormattedTextField txtCpfCF;
    private javax.swing.JFormattedTextField txtCpfCFP;
    private javax.swing.JFormattedTextField txtCpfRF;
    private javax.swing.JFormattedTextField txtDataNascimentoAF;
    private javax.swing.JFormattedTextField txtDataNascimentoCF;
    private javax.swing.JTextField txtDescricaoProdutoAP;
    private javax.swing.JTextField txtDescricaoProdutoCP;
    private javax.swing.JTextField txtDescricaoTP;
    private javax.swing.JTextField txtEmailAF;
    private javax.swing.JTextField txtEmailAPV;
    private javax.swing.JTextField txtEmailCF;
    private javax.swing.JTextField txtEmailCPV;
    private javax.swing.JTextField txtIDPontoDeVendaAPV;
    private javax.swing.JTextField txtIdPVTP;
    private javax.swing.JTextField txtIdTP;
    private javax.swing.JTextField txtLogradouroAF;
    private javax.swing.JTextField txtLogradouroAPV;
    private javax.swing.JTextField txtLogradouroCF;
    private javax.swing.JTextField txtLogradouroCPV;
    private javax.swing.JTextField txtNomeAF;
    private javax.swing.JTextField txtNomeCF;
    private javax.swing.JTextField txtNomeCFP;
    private javax.swing.JTextField txtNumeroAF;
    private javax.swing.JTextField txtNumeroAPV;
    private javax.swing.JTextField txtNumeroCF;
    private javax.swing.JTextField txtNumeroCPV;
    private javax.swing.JFormattedTextField txtPrecoAP;
    private javax.swing.JFormattedTextField txtPrecoCP;
    private javax.swing.JFormattedTextField txtPrecoTP;
    private javax.swing.JTextField txtQuantidadeAP;
    private javax.swing.JTextField txtQuantidadeCP;
    private javax.swing.JTextField txtQuantidadeTP;
    private javax.swing.JTextField txtRedeSocialAF;
    private javax.swing.JTextField txtRedeSocialCF;
    private javax.swing.JTextField txtSexoCFP;
    private javax.swing.JTextField txtTelefoneAF;
    private javax.swing.JTextField txtTelefoneAPV;
    private javax.swing.JFormattedTextField txtTelefoneCF;
    private javax.swing.JFormattedTextField txtTelefoneCPV;
    // End of variables declaration//GEN-END:variables
}
