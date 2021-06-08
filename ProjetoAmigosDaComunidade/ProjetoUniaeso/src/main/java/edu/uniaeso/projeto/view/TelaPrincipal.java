/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniaeso.projeto.view;

import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import edu.uniaeso.projeto.Vo.OcorrenciaDoUsuarioVo;
import edu.uniaeso.projeto.Vo.OcorrenciaTabelaVo;
import edu.uniaeso.projeto.Vo.TodasEmpresasVo;
import edu.uniaeso.projeto.dao.LoginDAO;
import edu.uniaeso.projeto.fachada.Fachada;
import edu.uniaeso.projeto.embutiveis.Contato;
import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.embutiveis.Endereco;
import edu.uniaeso.projeto.modelo.Login;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.embutiveis.Sexo;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.util.JPAutil;
import edu.uniaeso.projeto.util.ViaCEPutil;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TelaPrincipal extends javax.swing.JFrame {

    
    
    private static ViaCEPutil viaCep = new ViaCEPutil();
    private static EntityManager em = JPAutil.getEntityManager();
    private static LoginDAO loginDAO = loginDAO = new LoginDAO(em);;
    private static List<Empresa> empresas;
    private static Usuario usuarioLogado;
    private static Fachada fachada = Fachada.getFachadaInstance(em);
    TelaLogin telaLogin = null;
    
    public TelaPrincipal() {
        
        initComponents();   
        this.setResizable(false);
        this.addMetodoAoFechar();
        
        
        this.setSize(1350, 900);
        
        centreWindow(this);
        
        
        telaLogin = new TelaLogin(this, true);
        boolean autorizado = telaLogin.getAutorizado();
        
        
        if(autorizado == true){
            this.setVisible(true);
            this.preencheCombosBoxEmpresa();
            this.preencheCamposDoUsuario();
            carregaTabela();
            carregaPerfil();
            carregaTabelaEmpresas();
        }
        
        if(autorizado == false){
            System.exit(0);
        }
        
    }
    
    private void carregaPerfil(){
        txtNomePerfil.setText(usuarioLogado.getNome());
        txtTelefonePerfil.setText(usuarioLogado.getContato().getDdd()+" "+usuarioLogado.getContato().getNumero());
        comboSexoPerfil.setSelectedItem(String.valueOf(usuarioLogado.getSexo()));
        txtSenhaPerfil.setText(usuarioLogado.getLogin().getSenha());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = usuarioLogado.getDataDeNascimento();
        txtCpfPerfil.setText(String.valueOf(usuarioLogado.getCpf()));
        
        String dataFormatada = data.format(formato);
        txtDataNascimentoPerfil.setText(dataFormatada);
        txtEmailPerfil.setText(usuarioLogado.getLogin().getEmail());
        enabledElementosPerfil(false);
        botaoAtualizarPerfil.setVisible(false);
        botaoCancelarAtPerfil.setVisible(false);
    }
    
    private void enabledElementosPerfil(boolean b){
        txtNomePerfil.setEnabled(b);
        txtTelefonePerfil.setEnabled(b);
        comboSexoPerfil.setEnabled(b);
        txtDataNascimentoPerfil.setEnabled(b);
        txtEmailPerfil.setEnabled(b);
        txtSenhaPerfil.setEnabled(b);
        txtCpfPerfil.setEnabled(b);
    }
    
    
    private static void cadastraEmpresa(){
        
        
        
        Contato contatoEmpresa = new Contato(81,34314535l);
        Empresa CELPE = new Empresa("CELPE", 12345678912341l, contatoEmpresa, "celpe@NEOENERGIA.org");
        
        Contato contatoEmpresa2 = new Contato(81,34478444l);
        Empresa EMLURBE = new Empresa("EMLURBE", 12345678912343l, contatoEmpresa2, "emlurbe@INFRA.org");
        
        Contato contatoEmpresa3 = new Contato(81,34314444l);
        Empresa COMPESA = new Empresa("COMPESA", 12345678912342l, contatoEmpresa3, "compresa@NEOAGUA.org");	
        
        
        em.getTransaction().begin();
        
        fachada.cadastrarEmpresa(CELPE);
        fachada.cadastrarEmpresa(COMPESA);
        fachada.cadastrarEmpresa(EMLURBE);
        
        em.getTransaction().commit();
        
    }
    
    private void preencheCombosBoxEmpresa(){
        
        Long quantidade = fachada.quantidadeDeEmpresas();
        
        
        if(quantidade == 0){
            cadastraEmpresa();
        } 
        
        empresas = fachada.todasEmpresas();
        
        empresas.forEach(empresa -> { 
            comboEmpresaCO.addItem(empresa.getCnpj()+ "-"+ empresa.getNome());
            ComboEmpresaOcorrenciaAO.addItem(empresa.getCnpj()+ "-"+ empresa.getNome());
                    });
        
        
    }
    
    private void resetaCombosBoxEmpresa(){
        
        empresas = fachada.todasEmpresas();
        
        comboEmpresaCO.removeAllItems();
        comboEmpresaCO.addItem("Selecione");
        comboEmpresaCO.setSelectedIndex(0);
                
        ComboEmpresaOcorrenciaAO.removeAllItems();
        ComboEmpresaOcorrenciaAO.addItem("Selecione");
        ComboEmpresaOcorrenciaAO.setSelectedIndex(0);
        
        empresas.forEach(empresa -> { 
            comboEmpresaCO.addItem(empresa.getCnpj()+ "-"+ empresa.getNome());
            ComboEmpresaOcorrenciaAO.addItem(empresa.getCnpj()+ "-"+ empresa.getNome());
                    });
        
        
    }
    
    private void preencheCamposDoUsuario(){
         labelNomeUsuario.setText(usuarioLogado.getNome());
         labelCPFUsuarioLogado.setText(usuarioLogado.getCpf().toString());
    }
   
    public static EntityManager getEm() {
        return em;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        TelaPrincipal.usuarioLogado = usuarioLogado;
    }
    
    private void addMetodoAoFechar(){
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e) {
                
                
                super.windowOpened(e); //To change body of generated methods, choose Tools | Templates.
            }
            
            
            
            @Override
            public void windowClosing(WindowEvent e) {
                em.close();
                System.out.println(em);
                System.out.println("tela principal close");
                System.exit(0);
            }
   
            @Override
            public void windowClosed(WindowEvent e) {
                em.close();
                System.out.println(em);
                System.out.println("tela principal closed");
                System.exit(0);
            }
            
    });
        
        
    }
    
    private static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        CardLayoutTelaPrincipal = new javax.swing.JPanel();
        telaPrincipal = new javax.swing.JPanel();
        infoTelaPrincipal = new javax.swing.JPanel();
        labelBemVindo = new javax.swing.JLabel();
        labelNomeUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelCPFUsuarioLogado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        conteudoTelaPrincipal = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaTodasOcorrencias = new javax.swing.JTable();
        TodasOcorrencias = new javax.swing.JPanel();
        MinhasOcorrencias = new javax.swing.JPanel();
        infoTelaOcorrencias = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        conteudoOcorrencias = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMinhasOcorrencias = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoOcorrenciaAO = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCepOcorrenciaAO = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtLogradouroOcorrenciaAO = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPontoDeRefAO = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        ComboEmpresaOcorrenciaAO = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescricaoAO = new javax.swing.JTextArea();
        botaoAtualizarAO = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        botaoAtualizarOcorrencia = new javax.swing.JButton();
        botaoRemoverOcorrencia = new javax.swing.JButton();
        cadastrarOcorrencia = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        comboProblemaCO = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textDescricaoCO = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        textPontoDeRefCO = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboEmpresaCO = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        textRuaCO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        botaoBuscarCep = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textCepCO = new javax.swing.JFormattedTextField();
        Perfil = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtNomePerfil = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTelefonePerfil = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDataNascimentoPerfil = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        comboSexoPerfil = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtEmailPerfil = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtSenhaPerfil = new javax.swing.JPasswordField();
        botaoEditarPerfil = new javax.swing.JButton();
        botaoTrocarSenha = new javax.swing.JButton();
        botaoAtualizarPerfil = new javax.swing.JButton();
        botaoCancelarAtPerfil = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtCpfPerfil = new javax.swing.JTextField();
        TodasEmpresas = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaTodasEmpresas = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        telaPrincipalMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        sairDoPrograma = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CardLayoutTelaPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        CardLayoutTelaPrincipal.setPreferredSize(new java.awt.Dimension(1300, 900));
        CardLayoutTelaPrincipal.setLayout(new java.awt.CardLayout());

        infoTelaPrincipal.setBackground(new java.awt.Color(102, 204, 255));
        infoTelaPrincipal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelBemVindo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelBemVindo.setText("Seja bem-vindo(a):");

        labelNomeUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("CPF:");

        labelCPFUsuarioLogado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout infoTelaPrincipalLayout = new javax.swing.GroupLayout(infoTelaPrincipal);
        infoTelaPrincipal.setLayout(infoTelaPrincipalLayout);
        infoTelaPrincipalLayout.setHorizontalGroup(
            infoTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoTelaPrincipalLayout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(labelBemVindo)
                .addGap(18, 18, 18)
                .addComponent(labelNomeUsuario)
                .addGap(155, 155, 155)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCPFUsuarioLogado)
                .addContainerGap(348, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoTelaPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
        );
        infoTelaPrincipalLayout.setVerticalGroup(
            infoTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoTelaPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(infoTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBemVindo)
                    .addComponent(labelNomeUsuario)
                    .addComponent(jLabel1)
                    .addComponent(labelCPFUsuarioLogado))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        conteudoTelaPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        conteudoTelaPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabelaTodasOcorrencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "data_abertura", "status", "descricao", "rua_ou_avenida", "nome_empresa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaTodasOcorrencias.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelaTodasOcorrencias);
        if (tabelaTodasOcorrencias.getColumnModel().getColumnCount() > 0) {
            tabelaTodasOcorrencias.getColumnModel().getColumn(0).setResizable(false);
            tabelaTodasOcorrencias.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabelaTodasOcorrencias.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabelaTodasOcorrencias.getColumnModel().getColumn(2).setPreferredWidth(25);
            tabelaTodasOcorrencias.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabelaTodasOcorrencias.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        javax.swing.GroupLayout conteudoTelaPrincipalLayout = new javax.swing.GroupLayout(conteudoTelaPrincipal);
        conteudoTelaPrincipal.setLayout(conteudoTelaPrincipalLayout);
        conteudoTelaPrincipalLayout.setHorizontalGroup(
            conteudoTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        conteudoTelaPrincipalLayout.setVerticalGroup(
            conteudoTelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conteudoTelaPrincipalLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout telaPrincipalLayout = new javax.swing.GroupLayout(telaPrincipal);
        telaPrincipal.setLayout(telaPrincipalLayout);
        telaPrincipalLayout.setHorizontalGroup(
            telaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(telaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoTelaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conteudoTelaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        telaPrincipalLayout.setVerticalGroup(
            telaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoTelaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(conteudoTelaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        CardLayoutTelaPrincipal.add(telaPrincipal, "telaPrincipal");

        javax.swing.GroupLayout TodasOcorrenciasLayout = new javax.swing.GroupLayout(TodasOcorrencias);
        TodasOcorrencias.setLayout(TodasOcorrenciasLayout);
        TodasOcorrenciasLayout.setHorizontalGroup(
            TodasOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        TodasOcorrenciasLayout.setVerticalGroup(
            TodasOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );

        CardLayoutTelaPrincipal.add(TodasOcorrencias, "todasOcorrencias");

        infoTelaOcorrencias.setBackground(new java.awt.Color(102, 204, 255));
        infoTelaOcorrencias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setText("Minhas Ocorrencias");

        javax.swing.GroupLayout infoTelaOcorrenciasLayout = new javax.swing.GroupLayout(infoTelaOcorrencias);
        infoTelaOcorrencias.setLayout(infoTelaOcorrenciasLayout);
        infoTelaOcorrenciasLayout.setHorizontalGroup(
            infoTelaOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoTelaOcorrenciasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infoTelaOcorrenciasLayout.setVerticalGroup(
            infoTelaOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoTelaOcorrenciasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        conteudoOcorrencias.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabelaMinhasOcorrencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "codigo", "data_abertura", "status", "descricao", "cep", "ruaOuAvenida", "ponto_referencia", "empresa"
            }
        ));
        jScrollPane1.setViewportView(tabelaMinhasOcorrencias);
        if (tabelaMinhasOcorrencias.getColumnModel().getColumnCount() > 0) {
            tabelaMinhasOcorrencias.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(1).setMinWidth(100);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(1).setMaxWidth(100);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(2).setMinWidth(110);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(2).setMaxWidth(110);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(4).setMaxWidth(80);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(7).setMinWidth(85);
            tabelaMinhasOcorrencias.getColumnModel().getColumn(7).setMaxWidth(85);
        }

        javax.swing.GroupLayout conteudoOcorrenciasLayout = new javax.swing.GroupLayout(conteudoOcorrencias);
        conteudoOcorrencias.setLayout(conteudoOcorrenciasLayout);
        conteudoOcorrenciasLayout.setHorizontalGroup(
            conteudoOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
        );
        conteudoOcorrenciasLayout.setVerticalGroup(
            conteudoOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conteudoOcorrenciasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("CODIGO");

        jLabel14.setText("CEP");

        jLabel15.setText("LOGRADOURO");

        jLabel13.setText("Ponto de referencia");

        jLabel16.setText("Empresa");

        ComboEmpresaOcorrenciaAO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel17.setText("Descricao");

        txtDescricaoAO.setColumns(20);
        txtDescricaoAO.setRows(5);
        jScrollPane4.setViewportView(txtDescricaoAO);

        botaoAtualizarAO.setText("Atualizar");
        botaoAtualizarAO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarAOActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Limpar Formulario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(348, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtCodigoOcorrenciaAO, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtCepOcorrenciaAO, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtLogradouroOcorrenciaAO)
                    .addComponent(txtPontoDeRefAO)
                    .addComponent(ComboEmpresaOcorrenciaAO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(botaoAtualizarAO, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoOcorrenciaAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCepOcorrenciaAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLogradouroOcorrenciaAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPontoDeRefAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboEmpresaOcorrenciaAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoAtualizarAO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        botaoAtualizarOcorrencia.setText("Atualizar Ocorrencia");
        botaoAtualizarOcorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarOcorrenciaActionPerformed(evt);
            }
        });

        botaoRemoverOcorrencia.setText("Remover Ocorrencia");
        botaoRemoverOcorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverOcorrenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MinhasOcorrenciasLayout = new javax.swing.GroupLayout(MinhasOcorrencias);
        MinhasOcorrencias.setLayout(MinhasOcorrenciasLayout);
        MinhasOcorrenciasLayout.setHorizontalGroup(
            MinhasOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MinhasOcorrenciasLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(MinhasOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conteudoOcorrencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoTelaOcorrencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MinhasOcorrenciasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoAtualizarOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(botaoRemoverOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        MinhasOcorrenciasLayout.setVerticalGroup(
            MinhasOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MinhasOcorrenciasLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(infoTelaOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(MinhasOcorrenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAtualizarOcorrencia)
                    .addComponent(botaoRemoverOcorrencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(conteudoOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        CardLayoutTelaPrincipal.add(MinhasOcorrencias, "minhasOcorrencias");

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("Cadastrar ocorrência");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(jLabel4)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel4)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jButton2.setText("Cadastrar Ocorrência");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        comboProblemaCO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Cano estourado", "Falta de energia", "Buraco na via" }));

        jLabel10.setText("Problema");

        jLabel11.setText("Anexar arquivos");

        jToggleButton1.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add.png"))); // NOI18N

        textDescricaoCO.setColumns(20);
        textDescricaoCO.setRows(5);
        jScrollPane3.setViewportView(textDescricaoCO);

        jLabel9.setText("Descrição");

        jLabel7.setText("Ponto de referência");

        comboEmpresaCO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jLabel8.setText("Empresa");

        jLabel6.setText("Rua/Avenida");

        botaoBuscarCep.setText("BUSCAR CEP");
        botaoBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarCepActionPerformed(evt);
            }
        });

        jLabel5.setText("CEP");

        try {
            textCepCO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(botaoBuscarCep))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textPontoDeRefCO, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(comboEmpresaCO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(138, 138, 138)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(comboProblemaCO, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jToggleButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(textCepCO, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textRuaCO, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textRuaCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCepCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoBuscarCep)
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPontoDeRefCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEmpresaCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboProblemaCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel11))
                        .addComponent(jToggleButton1)))
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cadastrarOcorrenciaLayout = new javax.swing.GroupLayout(cadastrarOcorrencia);
        cadastrarOcorrencia.setLayout(cadastrarOcorrenciaLayout);
        cadastrarOcorrenciaLayout.setHorizontalGroup(
            cadastrarOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastrarOcorrenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadastrarOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        cadastrarOcorrenciaLayout.setVerticalGroup(
            cadastrarOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastrarOcorrenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        CardLayoutTelaPrincipal.add(cadastrarOcorrencia, "cadastroOcorrencia");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Perfil"));

        jLabel18.setText("Nome");

        jLabel19.setText("Telefone");

        jLabel20.setText("Data de nascimento");

        jLabel22.setText("Sexo");

        comboSexoPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino", "Indefinido" }));

        jLabel24.setText("Email");

        jLabel25.setText("Senha");

        botaoEditarPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/user_edit.png"))); // NOI18N
        botaoEditarPerfil.setText("Editar Perfil");
        botaoEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarPerfilActionPerformed(evt);
            }
        });

        botaoTrocarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/user_edit.png"))); // NOI18N
        botaoTrocarSenha.setText("Trocar Senha");
        botaoTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTrocarSenhaActionPerformed(evt);
            }
        });

        botaoAtualizarPerfil.setText("Atualizar Perfil");
        botaoAtualizarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarPerfilActionPerformed(evt);
            }
        });

        botaoCancelarAtPerfil.setText("Cancelar");
        botaoCancelarAtPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarAtPerfilActionPerformed(evt);
            }
        });

        jLabel26.setText("CPF:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel23))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmailPerfil)
                            .addComponent(txtNomePerfil)
                            .addComponent(txtTelefonePerfil)
                            .addComponent(txtDataNascimentoPerfil)
                            .addComponent(comboSexoPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCpfPerfil)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel18)
                                    .addComponent(txtSenhaPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addGap(4, 4, 4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoEditarPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoTrocarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addGap(24, 24, 24))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(botaoCancelarAtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(botaoAtualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(0, 0, 0)
                .addComponent(txtNomePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(botaoEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(botaoTrocarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, 0)
                        .addComponent(comboSexoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpfPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(1, 1, 1)
                        .addComponent(txtTelefonePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addGap(0, 0, 0)
                        .addComponent(txtDataNascimentoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel24)
                        .addGap(0, 0, 0)
                        .addComponent(txtEmailPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(0, 0, 0)
                        .addComponent(txtSenhaPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(5, 5, 5)
                .addComponent(botaoAtualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCancelarAtPerfil)
                .addGap(200, 200, 200))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(457, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(450, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PerfilLayout = new javax.swing.GroupLayout(Perfil);
        Perfil.setLayout(PerfilLayout);
        PerfilLayout.setHorizontalGroup(
            PerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PerfilLayout.setVerticalGroup(
            PerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        CardLayoutTelaPrincipal.add(Perfil, "perfil");

        jPanel6.setBackground(new java.awt.Color(102, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel27.setText("Empresas");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(551, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(549, 549, 549))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel27)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        tabelaTodasEmpresas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CNPJ", "NOME", "EMAIL", "QTD_OCORRENCIAS"
            }
        ));
        jScrollPane5.setViewportView(tabelaTodasEmpresas);
        if (tabelaTodasEmpresas.getColumnModel().getColumnCount() > 0) {
            tabelaTodasEmpresas.getColumnModel().getColumn(0).setResizable(false);
            tabelaTodasEmpresas.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TodasEmpresasLayout = new javax.swing.GroupLayout(TodasEmpresas);
        TodasEmpresas.setLayout(TodasEmpresasLayout);
        TodasEmpresasLayout.setHorizontalGroup(
            TodasEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TodasEmpresasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TodasEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        TodasEmpresasLayout.setVerticalGroup(
            TodasEmpresasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TodasEmpresasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        CardLayoutTelaPrincipal.add(TodasEmpresas, "todasEmpresas");

        jMenu1.setText("Principal");

        telaPrincipalMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/table.png"))); // NOI18N
        telaPrincipalMenu.setText("Tela principal");
        telaPrincipalMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telaPrincipalMenuActionPerformed(evt);
            }
        });
        jMenu1.add(telaPrincipalMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ocorrencias");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add.png"))); // NOI18N
        jMenuItem2.setText("Cadastrar ocorrencia");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/table_multiple.png"))); // NOI18N
        jMenuItem4.setText("Minhas ocorrencias");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Empresas");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/group.png"))); // NOI18N
        jMenuItem5.setText("Empresas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Meu perfil");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/user.png"))); // NOI18N
        jMenuItem6.setText("Ver Perfil");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        sairDoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancel.png"))); // NOI18N
        sairDoPrograma.setText("Sair");
        sairDoPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairDoProgramaActionPerformed(evt);
            }
        });
        jMenu4.add(sairDoPrograma);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CardLayoutTelaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CardLayoutTelaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sairDoProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairDoProgramaActionPerformed
        
//        telaLogin.setUsuarioLogado(null);
//        telaLogin.setAutorizado(false);
        this.setVisible(false);
        telaLogin = new TelaLogin(this, true);
//        telaLogin.setVisible(true);
        
        if(telaLogin.getAutorizado()){
            CardLayout cl = (CardLayout) CardLayoutTelaPrincipal.getLayout();
            cl.show(CardLayoutTelaPrincipal, "telaPrincipal");
            this.setVisible(true); 
            this.preencheCamposDoUsuario();
        } else{
            System.exit(0);
        }
    }//GEN-LAST:event_sairDoProgramaActionPerformed

    private void telaPrincipalMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telaPrincipalMenuActionPerformed
        
        //carregaTabela();
        
        CardLayout cl = (CardLayout) CardLayoutTelaPrincipal.getLayout();
        cl.show(CardLayoutTelaPrincipal, "telaPrincipal");
        
    }//GEN-LAST:event_telaPrincipalMenuActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        
        txtCodigoOcorrenciaAO.setEnabled(false);
        carregaTabelaMinhasOcorrencias();
        
        CardLayout cl = (CardLayout) CardLayoutTelaPrincipal.getLayout();
        cl.show(CardLayoutTelaPrincipal, "minhasOcorrencias");
        
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        
        
        String cep = textCepCO.getText();
        cep = cep.replace("-", "");
        String rua = textRuaCO.getText();
        String pontoDeReferencia = textPontoDeRefCO.getText();
        Object empresa = comboEmpresaCO.getSelectedItem();
        String descricao = textDescricaoCO.getText();
        Object problema = comboProblemaCO.getSelectedItem();
        
        String empresaString = String.valueOf(empresa);
        String[] splitEmpresa = empresaString.split("-");
        String empresaCNPJ = splitEmpresa[0];
        
        Integer cepFormatado = Integer.valueOf(cep);
        
        
        Endereco endereco = new Endereco( rua, pontoDeReferencia, cepFormatado);
        
        
        System.out.println(cep);
        System.out.println(rua);
        System.out.println(pontoDeReferencia);
        System.out.println(empresaString);
        System.out.println(descricao);
        System.out.println(problema);
        
        Empresa empresaOBJ = retornaEmpresa(Long.valueOf(empresaCNPJ));
        
        Ocorrencia ocorrencia = new Ocorrencia(endereco, descricao, "doc_anexos", usuarioLogado, empresaOBJ);
        
        
        
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar realizar o cadastro?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
        
        
        if(confirmar == 0){
            em.getTransaction().begin();
            fachada.cadastrarOcorrencia(ocorrencia);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "OCORRENCIA CADASTRADA COM SUCESSO!");
            limparFormCadastroOcorrencia();
            carregaTabela();
            carregaTabelaEmpresas();
            
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    public void limparFormCadastroOcorrencia(){
        textCepCO.setText("");
        textRuaCO.setText("");
        textPontoDeRefCO.setText("");
        comboEmpresaCO.setSelectedIndex(0);
        textDescricaoCO.setText("");
        comboProblemaCO.setSelectedIndex(0);
        
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       
        CardLayout cl = (CardLayout) CardLayoutTelaPrincipal.getLayout();
        cl.show(CardLayoutTelaPrincipal, "cadastroOcorrencia");
        
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botaoBuscarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarCepActionPerformed
        String cep = textCepCO.getText();
        cep = cep.replace("-", "");
        
        if(!cep.isEmpty()){
            
            try {
                ViaCEPEndereco endereco = viaCep.getEndereco(cep);
                
                textRuaCO.setText(endereco.getLogradouro());
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ERRO AO BUSCAR CEP", "ERROR", 1);
            } catch(NullPointerException | IllegalArgumentException n){
                JOptionPane.showMessageDialog(this, "DIGITE UM CEP VALIDO!");
            }
                    } else{
                  JOptionPane.showMessageDialog(this, "INFORME O CEP");
        }
        
    }//GEN-LAST:event_botaoBuscarCepActionPerformed

    private void botaoRemoverOcorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverOcorrenciaActionPerformed
        
        
        int linha = tabelaMinhasOcorrencias.getSelectedRow();
        
        if(linha != -1){
            
            Object CodigoOcorrencia = tabelaMinhasOcorrencias.getValueAt(linha, 0);
            String codigoString = String.valueOf(CodigoOcorrencia);
            Long codigo = Long.valueOf(codigoString);
            
            int confirmar = JOptionPane.showConfirmDialog(this, "Deseja remover a ocorrencia?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
            
            if(confirmar == 0){
                em.getTransaction().begin();
                fachada.removerOcorrenciaPorId(codigo);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "OCORRENCIA REMOVIDA COM SUCESSO!");
                carregaTabelaMinhasOcorrencias();
                carregaTabelaEmpresas();
                carregaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "SELECIONE UMA LINHA NA TABELA!");
        }
        
        
        
    }//GEN-LAST:event_botaoRemoverOcorrenciaActionPerformed

    private void botaoAtualizarOcorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarOcorrenciaActionPerformed
        
        int linha = tabelaMinhasOcorrencias.getSelectedRow();
        
        if(linha != -1){
            
            Object CodigoOcorrencia = tabelaMinhasOcorrencias.getValueAt(linha, 0);
            String codigoString = String.valueOf(CodigoOcorrencia);
            Long codigo = Long.valueOf(codigoString);
           
            Ocorrencia ocorrencia = fachada.buscarOcorrenciaPorCodigo(codigo);
            preencheFormAtualizarOcorrencia(ocorrencia);
                
                
            
        } else {
            JOptionPane.showMessageDialog(this, "SELECIONE UMA LINHA NA TABELA!");
        }
        
    }//GEN-LAST:event_botaoAtualizarOcorrenciaActionPerformed

    private void botaoAtualizarAOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarAOActionPerformed
        boolean autorizado = verificaCamposFormAtualiza();
        if(autorizado == false){
            return;
        }
        
        String codigo = txtCodigoOcorrenciaAO.getText();
        String cep = txtCepOcorrenciaAO.getText();
        String logradouro = txtLogradouroOcorrenciaAO.getText();
        String pontoDeReferencia = txtPontoDeRefAO.getText();
        Object empresaCOMBO = ComboEmpresaOcorrenciaAO.getSelectedItem();
        String descricao = txtDescricaoAO.getText();
        
        
        String empresaCombo = String.valueOf(empresaCOMBO);
        String[] splitEmpresaCombo = empresaCombo.split("-");
        String cnpjEmpresa = splitEmpresaCombo[0];
        
        Empresa empresa = retornaEmpresa(Long.valueOf(cnpjEmpresa));
        Endereco endereco = new Endereco(logradouro, pontoDeReferencia, Integer.valueOf(cep));
        
        Ocorrencia ocorrencia = new Ocorrencia(endereco, descricao, "documentos-anexos", usuarioLogado, empresa);
        ocorrencia.setCodigoOcorrencia(Long.valueOf(codigo));
        
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar realizar o cadastro?" , "Confirmar cadastro",JOptionPane.YES_NO_OPTION);
        if(autorizado){
            if(confirmar == 0){
                System.out.println("erro de cadastro");
                em.getTransaction().begin();
                fachada.atualizarOcorrencia(ocorrencia);
                em.getTransaction().commit();
                carregaTabelaMinhasOcorrencias();
                JOptionPane.showMessageDialog(this, "ATUALIZADO COM SUCESSO!");
                resetaFormAtualizarOcorrencia();
                carregaTabela();
                carregaTabelaEmpresas();
            }
        }
        
    }//GEN-LAST:event_botaoAtualizarAOActionPerformed
    
    
    private boolean verificaCamposFormAtualiza(){
        String codigo = txtCodigoOcorrenciaAO.getText();
        String cep = txtCepOcorrenciaAO.getText();
        String logradouro = txtLogradouroOcorrenciaAO.getText();
        String pontoDeReferencia = txtPontoDeRefAO.getText();
        Object empresaCOMBO = ComboEmpresaOcorrenciaAO.getSelectedItem();
        String descricao = txtDescricaoAO.getText();
        
        String combo = String.valueOf(empresaCOMBO);
        
        
        if(codigo.isEmpty() ||
                cep.isEmpty() ||
                logradouro.isEmpty() ||
                pontoDeReferencia.isEmpty() ||
                combo.equals("Selecione") ||
                descricao.isEmpty()){
            JOptionPane.showMessageDialog(this, "SELECIONE UMA OCORRENCIA!");
            return false;
        } else{
            return true;
        }
    
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       resetaFormAtualizarOcorrencia();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        
        txtNomePerfil.setEnabled(false);
        CardLayout cl = (CardLayout) CardLayoutTelaPrincipal.getLayout();
        cl.show(CardLayoutTelaPrincipal, "perfil");
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void botaoEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarPerfilActionPerformed
       enabledElementosPerfil(true);
       botaoAtualizarPerfil.setVisible(true);
       txtSenhaPerfil.setEchoChar((char)0);
       botaoCancelarAtPerfil.setVisible(true);
       txtCpfPerfil.setEditable(false);
    }//GEN-LAST:event_botaoEditarPerfilActionPerformed

    private void botaoTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTrocarSenhaActionPerformed
     botaoAtualizarPerfil.setVisible(true);
     txtSenhaPerfil.setEnabled(true);
     botaoCancelarAtPerfil.setVisible(true);
     txtSenhaPerfil.setEchoChar((char)0);
     
     
     
    }//GEN-LAST:event_botaoTrocarSenhaActionPerformed

    private void botaoAtualizarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarPerfilActionPerformed
        String nome = txtNomePerfil.getText();
        String telefone = txtTelefonePerfil.getText();
        Object sexo = comboSexoPerfil.getSelectedItem();
        char[] senha = txtSenhaPerfil.getPassword();
        String data = txtDataNascimentoPerfil.getText();
        String email = txtEmailPerfil.getText();
        String cpf = txtCpfPerfil.getText();
        telefone = telefone.trim();
        telefone = telefone.replace(" ", "");
        
        Sexo sexoEnum = Sexo.valueOf(String.valueOf(sexo));
        
        
        String ddd = telefone.substring(0, 2);
        String numero = telefone.substring(2);
        Contato contato = new Contato(Integer.valueOf(ddd), Long.valueOf(numero));
        Login loginAtual = usuarioLogado.getLogin();
        
        Login login = new Login(email, new String(senha));
        login.setId(loginAtual.getId());
        
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate dataFormatada = null;
        try{
            dataFormatada = LocalDate.parse(String.valueOf(data), formatoData);
        } catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(this, "DIGITE UMA DATA VALIDA!");
            return;
        }
        
        Usuario usuario = new Usuario(nome, usuarioLogado.getCpf(), dataFormatada, sexoEnum, contato, login);
        
        int confirmar = JOptionPane.showConfirmDialog(this, "Desejar atualizar?" , "Confirmar atualizacao",JOptionPane.YES_NO_OPTION);
        
        
        if(confirmar == 0){
            em.getTransaction().begin();
            fachada.atualizarUsuario(usuario);
            em.getTransaction().commit();
            
            JOptionPane.showMessageDialog(this, "ATUALIZADO COM SUCESSO!");
            txtSenhaPerfil.setEchoChar('*');
            carregaPerfil();
        }
    }//GEN-LAST:event_botaoAtualizarPerfilActionPerformed

    private void botaoCancelarAtPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarAtPerfilActionPerformed
          
//        enabledElementosPerfil(false);
//        botaoAtualizarPerfil.setVisible(false);
//        botaoCancelarAtPerfil.setVisible(false);
          txtSenhaPerfil.setEchoChar('*');
          carregaPerfil();
    }//GEN-LAST:event_botaoCancelarAtPerfilActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        CardLayout cl = (CardLayout) CardLayoutTelaPrincipal.getLayout();
        cl.show(CardLayoutTelaPrincipal, "todasEmpresas");
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    
    private void carregaTabelaEmpresas(){
        
        List<TodasEmpresasVo> empresas = fachada.TodasEmpresasComQuantidadeDeOcorrencias();
        tabelaTodasEmpresas.setEnabled(false);
        DefaultTableModel modelo = (DefaultTableModel) tabelaTodasEmpresas.getModel();
        modelo.setRowCount(0);
        
        
        empresas.forEach(empresa -> {
            modelo.addRow(new Object[]{
                empresa.getCnpj(),
                empresa.getNome(),
                empresa.getEmail(),
                empresa.getQtdOcorrencias()
            });
        
        });
    }
    
    private void preencheFormAtualizarOcorrencia(Ocorrencia ocorrencia){
        
        txtCodigoOcorrenciaAO.setText(String.valueOf(ocorrencia.getId()));
        txtCepOcorrenciaAO.setText(String.valueOf(ocorrencia.getEndereco().getCEP()));
        txtLogradouroOcorrenciaAO.setText(String.valueOf(ocorrencia.getEndereco().getRuaOuAvenida()));
        txtPontoDeRefAO.setText(ocorrencia.getEndereco().getPontoDeReferencia());
        ComboEmpresaOcorrenciaAO.setSelectedItem(ocorrencia.getEmpresa().getCnpj()+"-"+ocorrencia.getEmpresa().getNome());
        txtDescricaoAO.setText(ocorrencia.getDescricao());
        
    }
    
     private void resetaFormAtualizarOcorrencia(){
        
        txtCodigoOcorrenciaAO.setText("");
        txtCepOcorrenciaAO.setText("");
        txtLogradouroOcorrenciaAO.setText("");
        txtPontoDeRefAO.setText("");
        ComboEmpresaOcorrenciaAO.setSelectedIndex(0);
        txtDescricaoAO.setText("");
        
    }
    
    
    private Empresa retornaEmpresa(Long empresaCNPJ){
       
        for(int c = 0; c < empresas.size(); c++){
            Empresa empresa = empresas.get(c);
            
            
            if(empresa.getCnpj().equals(empresaCNPJ)){
                return empresa;
            }
        }

        return null;
    }
    
    
    private void carregaTabelaMinhasOcorrencias(){
        
        List<OcorrenciaDoUsuarioVo> lista = fachada.todasOcorrenciasDoUsuarioVo(usuarioLogado.getCpf());
        DefaultTableModel model = (DefaultTableModel) tabelaMinhasOcorrencias.getModel();
        model.setRowCount(0);
        
        for(int c = 0; c < lista.size(); c++){
            
            OcorrenciaDoUsuarioVo ocorrencia = lista.get(c);
            model.addRow(
                    
                    new Object[] {
                        ocorrencia.getCodOcorrencia(),
                        ocorrencia.getDataAbertura(),
                        ocorrencia.getStatus(), 
                        ocorrencia.getDescricao(),
                        ocorrencia.getCEP(),
                        ocorrencia.getRuaOuAvenida(),
                        ocorrencia.getPontoDeReferencia(),
                        ocorrencia.getNomeEmpresa()
                    }
                    
            );
        }
        
        
        lista.forEach(ocorrencia -> {
            
        });
        
    }
    
    private void carregaTabela(){
        List<OcorrenciaTabelaVo> lista = fachada.todasOcorrenciasVo();
        tabelaTodasOcorrencias.setEnabled(false);
        DefaultTableModel modelo = (DefaultTableModel) tabelaTodasOcorrencias.getModel();
        
        modelo.setRowCount(0);
        
        
        lista.forEach(
                ocorrencia -> {
                    modelo.addRow( 
                            
                            new Object[] {
                                ocorrencia.getCodigoOcorrencia(),
                                ocorrencia.getDataAbertura(),
                                ocorrencia.getStatus(), 
                                ocorrencia.getDescricao(),
                                ocorrencia.getRuaOuAvenida(),
                                ocorrencia.getNomeDaEmpresa()
                            }
                    );
                }
        );
        
    }
    
    public static LoginDAO getLoginDAO(){
        return loginDAO;
    }


    public static Fachada getFachada(){
        return fachada;
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardLayoutTelaPrincipal;
    private javax.swing.JComboBox<String> ComboEmpresaOcorrenciaAO;
    private javax.swing.JPanel MinhasOcorrencias;
    private javax.swing.JPanel Perfil;
    private javax.swing.JPanel TodasEmpresas;
    private javax.swing.JPanel TodasOcorrencias;
    private javax.swing.JButton botaoAtualizarAO;
    private javax.swing.JButton botaoAtualizarOcorrencia;
    private javax.swing.JButton botaoAtualizarPerfil;
    private javax.swing.JButton botaoBuscarCep;
    private javax.swing.JButton botaoCancelarAtPerfil;
    private javax.swing.JButton botaoEditarPerfil;
    private javax.swing.JButton botaoRemoverOcorrencia;
    private javax.swing.JButton botaoTrocarSenha;
    private javax.swing.JPanel cadastrarOcorrencia;
    private javax.swing.JComboBox<String> comboEmpresaCO;
    private javax.swing.JComboBox<String> comboProblemaCO;
    private javax.swing.JComboBox<String> comboSexoPerfil;
    private javax.swing.JPanel conteudoOcorrencias;
    private javax.swing.JPanel conteudoTelaPrincipal;
    private javax.swing.JPanel infoTelaOcorrencias;
    private javax.swing.JPanel infoTelaPrincipal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel labelBemVindo;
    private javax.swing.JLabel labelCPFUsuarioLogado;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JMenuItem sairDoPrograma;
    private javax.swing.JTable tabelaMinhasOcorrencias;
    private javax.swing.JTable tabelaTodasEmpresas;
    private javax.swing.JTable tabelaTodasOcorrencias;
    private javax.swing.JPanel telaPrincipal;
    private javax.swing.JMenuItem telaPrincipalMenu;
    private javax.swing.JFormattedTextField textCepCO;
    private javax.swing.JTextArea textDescricaoCO;
    private javax.swing.JTextField textPontoDeRefCO;
    private javax.swing.JTextField textRuaCO;
    private javax.swing.JTextField txtCepOcorrenciaAO;
    private javax.swing.JTextField txtCodigoOcorrenciaAO;
    private javax.swing.JTextField txtCpfPerfil;
    private javax.swing.JTextField txtDataNascimentoPerfil;
    private javax.swing.JTextArea txtDescricaoAO;
    private javax.swing.JTextField txtEmailPerfil;
    private javax.swing.JTextField txtLogradouroOcorrenciaAO;
    private javax.swing.JTextField txtNomePerfil;
    private javax.swing.JTextField txtPontoDeRefAO;
    private javax.swing.JPasswordField txtSenhaPerfil;
    private javax.swing.JTextField txtTelefonePerfil;
    // End of variables declaration//GEN-END:variables
}
