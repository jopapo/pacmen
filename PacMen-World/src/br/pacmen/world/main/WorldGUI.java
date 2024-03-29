package br.pacmen.world.main;

import java.awt.Color;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import netbula.ORPC.Pmapsvc;
import netbula.ORPC.rpc_err;
import br.pacmen.world.ctl.WorldServerThread;


@SuppressWarnings("serial")
public class WorldGUI extends javax.swing.JFrame implements ListSelectionListener {
	
	WorldServerThread server = null;

    /** Creates new form NewJFrame */
    public WorldGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jtpPrincipal = new javax.swing.JTabbedPane();
        jpWorld = new javax.swing.JPanel();
        jbWorldServerToggle = new javax.swing.JButton();
        jlWorldServerStatus = new javax.swing.JLabel();
        jpGhost = new javax.swing.JPanel();
        jpGhostServer = new javax.swing.JPanel();
        jspGhostServer = new javax.swing.JScrollPane();
        jlGhostServer = new javax.swing.JList();
        jbGhostServerAdd = new javax.swing.JButton();
        jbGhostServerRemove = new javax.swing.JButton();
        jpRestaurant = new javax.swing.JPanel();
        jpRestaurantServer = new javax.swing.JPanel();
        jspRestaurantServer = new javax.swing.JScrollPane();
        jlRestaurantServer = new javax.swing.JList();
        jbRestaurantServerAdd = new javax.swing.JButton();
        jbRestaurantServerRemove = new javax.swing.JButton();
        jpTransport = new javax.swing.JPanel();
        jpTransportServer = new javax.swing.JPanel();
        jspTransportServer = new javax.swing.JScrollPane();
        jlTransportServer = new javax.swing.JList();
        jbTransportServerAdd = new javax.swing.JButton();
        jbTransportServerRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PacMen - World");
        setName("fPrincipal"); // NOI18N
        setResizable(false);
        setLocationRelativeTo(null); // Centraliza
        
        jtpPrincipal.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jbWorldServerToggle.setText("Ativar servidor");
        jbWorldServerToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbWorldServerToggleActionPerformed(evt);
            }
        });

        jlWorldServerStatus.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlWorldServerStatus.setForeground(java.awt.Color.red);
        jlWorldServerStatus.setText("Servidor inativo");

        javax.swing.GroupLayout jpWorldLayout = new javax.swing.GroupLayout(jpWorld);
        jpWorld.setLayout(jpWorldLayout);
        jpWorldLayout.setHorizontalGroup(
            jpWorldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpWorldLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbWorldServerToggle)
                .addContainerGap(144, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpWorldLayout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(jlWorldServerStatus)
                .addContainerGap())
        );
        jpWorldLayout.setVerticalGroup(
            jpWorldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpWorldLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbWorldServerToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(jlWorldServerStatus)
                .addContainerGap())
        );

        jtpPrincipal.addTab("Principal", jpWorld);

        jpGhostServer.setBorder(javax.swing.BorderFactory.createTitledBorder("Servidores"));

        jlGhostServer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlGhostServer.addListSelectionListener(this);
        jlGhostServer.setModel(new DefaultListModel());
        
        jspGhostServer.setViewportView(jlGhostServer);

        javax.swing.GroupLayout jpGhostServerLayout = new javax.swing.GroupLayout(jpGhostServer);
        jpGhostServer.setLayout(jpGhostServerLayout);
        jpGhostServerLayout.setHorizontalGroup(
            jpGhostServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspGhostServer, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );
        jpGhostServerLayout.setVerticalGroup(
            jpGhostServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspGhostServer, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        jbGhostServerAdd.setText("Adicionar");
        jbGhostServerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGhostServerAddActionPerformed(evt);
            }
        });

        jbGhostServerRemove.setText("Remover");
        jbGhostServerRemove.setEnabled(false);
        jbGhostServerRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGhostServerRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGhostLayout = new javax.swing.GroupLayout(jpGhost);
        jpGhost.setLayout(jpGhostLayout);
        jpGhostLayout.setHorizontalGroup(
            jpGhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGhostLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpGhostServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpGhostLayout.createSequentialGroup()
                        .addComponent(jbGhostServerAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbGhostServerRemove)))
                .addContainerGap())
        );
        jpGhostLayout.setVerticalGroup(
            jpGhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGhostLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpGhostServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGhostServerAdd)
                    .addComponent(jbGhostServerRemove))
                .addContainerGap())
        );

        jtpPrincipal.addTab("Fantasmas", jpGhost);

        jpRestaurantServer.setBorder(javax.swing.BorderFactory.createTitledBorder("Servidores"));

        jlRestaurantServer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlRestaurantServer.addListSelectionListener(this);
        jlRestaurantServer.setModel(new DefaultListModel());

        jspRestaurantServer.setViewportView(jlRestaurantServer);

        javax.swing.GroupLayout jpRestaurantServerLayout = new javax.swing.GroupLayout(jpRestaurantServer);
        jpRestaurantServer.setLayout(jpRestaurantServerLayout);
        jpRestaurantServerLayout.setHorizontalGroup(
            jpRestaurantServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspRestaurantServer, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );
        jpRestaurantServerLayout.setVerticalGroup(
            jpRestaurantServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspRestaurantServer, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        jbRestaurantServerAdd.setText("Adicionar");
        jbRestaurantServerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRestaurantServerAddActionPerformed(evt);
            }
        });

        jbRestaurantServerRemove.setText("Remover");
        jbRestaurantServerRemove.setEnabled(false);
        jbRestaurantServerRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRestaurantServerRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpRestaurantLayout = new javax.swing.GroupLayout(jpRestaurant);
        jpRestaurant.setLayout(jpRestaurantLayout);
        jpRestaurantLayout.setHorizontalGroup(
            jpRestaurantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
            .addGroup(jpRestaurantLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRestaurantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpRestaurantServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpRestaurantLayout.createSequentialGroup()
                        .addComponent(jbRestaurantServerAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRestaurantServerRemove)))
                .addContainerGap())
        );
        jpRestaurantLayout.setVerticalGroup(
            jpRestaurantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRestaurantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpRestaurantServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRestaurantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbRestaurantServerAdd)
                    .addComponent(jbRestaurantServerRemove))
                .addContainerGap())
        );

        jtpPrincipal.addTab("Restaurantes", jpRestaurant);

        jpTransportServer.setBorder(javax.swing.BorderFactory.createTitledBorder("Servidores"));

        jlTransportServer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlTransportServer.addListSelectionListener(this);
        jlTransportServer.setModel(new DefaultListModel());

        jspTransportServer.setViewportView(jlTransportServer);

        javax.swing.GroupLayout jpTransportServerLayout = new javax.swing.GroupLayout(jpTransportServer);
        jpTransportServer.setLayout(jpTransportServerLayout);
        jpTransportServerLayout.setHorizontalGroup(
            jpTransportServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspTransportServer, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );
        jpTransportServerLayout.setVerticalGroup(
            jpTransportServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspTransportServer, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        jbTransportServerAdd.setText("Adicionar");
        jbTransportServerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTransportServerAddActionPerformed(evt);
            }
        });

        jbTransportServerRemove.setText("Remover");
        jbTransportServerRemove.setEnabled(false);
        jbTransportServerRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTransportServerRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTransportLayout = new javax.swing.GroupLayout(jpTransport);
        jpTransport.setLayout(jpTransportLayout);
        jpTransportLayout.setHorizontalGroup(
            jpTransportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
            .addGap(0, 259, Short.MAX_VALUE)
            .addGroup(jpTransportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTransportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTransportServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpTransportLayout.createSequentialGroup()
                        .addComponent(jbTransportServerAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbTransportServerRemove)))
                .addContainerGap())
        );
        jpTransportLayout.setVerticalGroup(
            jpTransportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTransportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTransportServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpTransportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbTransportServerAdd)
                    .addComponent(jbTransportServerRemove))
                .addContainerGap())
        );

        jtpPrincipal.addTab("Teletransporte", jpTransport);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        setEnableTabs(false);
        
        pack();
    }// </editor-fold>

	private void jbWorldServerToggleActionPerformed(java.awt.event.ActionEvent evt) {
		if (server == null)
			try {
				server = new WorldServerThread() {
					public void on() {
						setEnableTabs(true);
						jbWorldServerToggle.setEnabled(false);
						//jbWorldServerToggle.setText("Desativar servidor");
						jlWorldServerStatus.setText("Servidor ativo");
						jlWorldServerStatus.setForeground(Color.GREEN);
					}
					public void off(String msg) {
				        setEnableTabs(false);
						jbWorldServerToggle.setEnabled(true);
						jlWorldServerStatus.setText("Servidor inativo!");
						if (msg != null)
							jlWorldServerStatus.setText(jlWorldServerStatus.getText().concat("\n").concat(msg));
					}
				};
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Imposs�vel inicializar o mundo.\n\nErro: " + e.getMessage());
				return;
			}

		// Inicializa o servidor
		server.start();
		
	}
	
	private void setEnableTabs(boolean enabled) {
		jtpPrincipal.setEnabledAt(1, enabled);
        jtpPrincipal.setEnabledAt(2, false);
        jtpPrincipal.setEnabledAt(3, false);		
	}
	
	private void jbGhostServerAddActionPerformed(java.awt.event.ActionEvent evt) {
	    String rsp = JOptionPane.showInputDialog(this, "Informe o ip ou nome do servidor:", "localhost");
	    if (rsp != null)
	    	try {
				server.getServerImpl().getGhostControl().addGhostServer(rsp);
		    	((DefaultListModel) jlGhostServer.getModel()).addElement(rsp);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao conectar com o servidor!\n\n".concat(e.getMessage()));
			}
	}
	
	private void jbGhostServerRemoveActionPerformed(java.awt.event.ActionEvent evt) {
		String host = (String) jlGhostServer.getSelectedValue();
		server.getServerImpl().getGhostControl().removeGhostServer(host);
    	((DefaultListModel) jlGhostServer.getModel()).removeElement(host);
	}
	
	private void jbRestaurantServerAddActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}
	
	private void jbRestaurantServerRemoveActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}
	
	private void jbTransportServerAddActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}
	
	private void jbTransportServerRemoveActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {

    	// Inicializa o PortMapper por padr�o
    	new Thread() {
    		public void run() {
            	System.out.println("Inicializando o PortMapper...");
            	try {
        			new Pmapsvc().run();
        		} catch (rpc_err e) {
        			e.printStackTrace();
        		}
    		}
    	}.start();

    	// Abre a janela de controle
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorldGUI().setVisible(true);
            }
        });
    	
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jbGhostServerAdd;
    private javax.swing.JButton jbGhostServerRemove;
    private javax.swing.JButton jbRestaurantServerAdd;
    private javax.swing.JButton jbRestaurantServerRemove;
    private javax.swing.JButton jbTransportServerAdd;
    private javax.swing.JButton jbTransportServerRemove;
    private javax.swing.JButton jbWorldServerToggle;
    private javax.swing.JList jlGhostServer;
    private javax.swing.JList jlRestaurantServer;
    private javax.swing.JList jlTransportServer;
    private javax.swing.JLabel jlWorldServerStatus;
    private javax.swing.JPanel jpGhost;
    private javax.swing.JPanel jpGhostServer;
    private javax.swing.JPanel jpRestaurant;
    private javax.swing.JPanel jpRestaurantServer;
    private javax.swing.JPanel jpTransport;
    private javax.swing.JPanel jpTransportServer;
    private javax.swing.JPanel jpWorld;
    private javax.swing.JScrollPane jspGhostServer;
    private javax.swing.JScrollPane jspRestaurantServer;
    private javax.swing.JScrollPane jspTransportServer;
    private javax.swing.JTabbedPane jtpPrincipal;
    // End of variables declaration

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JButton btRemove = null;
		if (e.getSource().equals(jlGhostServer))
			btRemove = jbGhostServerRemove;
		else
		if (e.getSource().equals(jlRestaurantServer))
			btRemove = jbRestaurantServerRemove;
		else
		if (e.getSource().equals(jlTransportServer))
			btRemove = jbTransportServerRemove;
		
		if (btRemove != null)
			btRemove.setEnabled(((JList) e.getSource()).getSelectedIndex() >= 0);		
	}

}
