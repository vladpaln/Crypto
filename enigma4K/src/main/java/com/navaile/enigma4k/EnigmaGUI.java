/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import java.net.URL;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import org.apache.log4j.*;

/**
 * Enigma4K GUI
 *
 * @author navaile
 */
public class EnigmaGUI extends javax.swing.JFrame {
	
	private static final Logger LOG = Logger.getLogger(EnigmaGUI.class);

	/** Creates new form EnigmaGUI			*/
	private EnigmaGUI() {
		initComponents();

		if(roCountField != null)
			roCountField.setText(String.valueOf(Enigma4K.COUNT_MIN));
		
		if(pbCountField != null)
			pbCountField.setText(String.valueOf(Enigma4K.COUNT_MIN));
		
		if(jScrollPane1 != null)
			jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		if(textField != null) {
			textField.setWrapStyleWord(false);
			textField.setText("Your message goes here.");
		}

		URL iconURL = getClass().getResource("/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		super.setIconImage(icon.getImage());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        roLabel = new javax.swing.JLabel();
        pbLabel = new javax.swing.JLabel();
        handleLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        textLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textField = new javax.swing.JTextArea();
        encryptButton = new javax.swing.JButton();
        decryptButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        passwordField = new javax.swing.JTextField();
        handleField = new javax.swing.JTextField();
        pbCountField = new javax.swing.JTextField();
        roCountField = new javax.swing.JTextField();
        textDescLabel = new javax.swing.JLabel();
        dirSeedField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        aboutButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        dirSeedLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crypto Mk II");
        setLocation(new java.awt.Point(300, 50));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        title.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        title.setText("Crypto Mk II");

        roLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        roLabel.setText("Rotor #");

        pbLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        pbLabel.setText("Plugboard #");

        handleLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        handleLabel.setText("Recipient");

        passLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        passLabel.setText("Crypt Phrase");

        textLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        textLabel.setText("Text");

        textField.setColumns(20);
        textField.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        textField.setLineWrap(true);
        textField.setRows(5);
        textField.setToolTipText("Letters, numbers, and standard punctuation");
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFieldFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(textField);

        encryptButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        encryptButton.setText("Encrypt");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        decryptButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        decryptButton.setText("Decrypt");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        passwordField.setText("pass phrase/password");
        passwordField.setToolTipText("Password, pass phrase know by both parties");
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
        });

        handleField.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        handleField.setText("email/handle");
        handleField.setToolTipText("Recipient email, handle, or other identifier");
        handleField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                handleFieldFocusGained(evt);
            }
        });

        pbCountField.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        pbCountField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        pbCountField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pbCountFieldFocusGained(evt);
            }
        });

        roCountField.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        roCountField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        roCountField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                roCountFieldFocusGained(evt);
            }
        });

        textDescLabel.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        textDescLabel.setText("(allowed chars: A-Za-z, 0-9, standard punctuation)");

        dirSeedField.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        dirSeedField.setToolTipText("Directory randomization seed, default is blank");
        dirSeedField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dirSeedFieldFocusGained(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("(97 - 4K)");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("(97 - 500)");

        aboutButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        dirSeedLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        dirSeedLabel.setText("Directory Seed");

        jLabel3.setText("(0 - 9,223,372,036,854,775,807)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(dirSeedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(dirSeedLabel)
                .addComponent(jLabel3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(encryptButton)
                        .addGap(18, 18, 18)
                        .addComponent(decryptButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aboutButton))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passLabel)
                            .addComponent(handleLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(handleField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(passwordField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textLabel)
                        .addGap(18, 18, 18)
                        .addComponent(textDescLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pbLabel)
                                    .addComponent(roLabel))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(roCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pbCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2))))
                            .addComponent(title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dirSeedField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roLabel)
                        .addComponent(roCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pbLabel)
                    .addComponent(pbCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dirSeedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(handleLabel)
                    .addComponent(handleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textLabel)
                    .addComponent(textDescLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encryptButton)
                    .addComponent(decryptButton)
                    .addComponent(clearButton)
                    .addComponent(aboutButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed

		String passPhrase = passwordField.getText();
		String handle = handleField.getText();
		String roCountText = roCountField.getText();
		String pbCountText = pbCountField.getText();
		String dirSeedText = dirSeedField.getText();
		
		int roCount = 97, pbCount = 97;
		Long dirSeed = null;

		if(roCountText.length() != 0)
			try {	roCount = Integer.parseInt(roCountText);					}
			catch(Exception e) {	LOG.fatal("Parse Rotor Count", e);			}
		
		if(pbCountText.length() != 0)
			try {	pbCount = Integer.parseInt(pbCountText);					}
			catch(Exception e) {	LOG.fatal("Parse Plugboard Count", e);		}
		
		if(dirSeedText.length() != 0 && dirSeedText.matches("[0-9]+"))	
			try {	dirSeed = Long.parseLong(dirSeedText);						}
			catch(Exception e) {	LOG.fatal("Parse Directory Seed", e);		}
		
//		System.out.println(
//			"passPhrase: " + passPhrase +
//			", handle: " + handle +
//			", roCount: " + roCount +
//			", pbCount: " + pbCount +
//			", dirSeed: " + dirSeed +
//			", text: " + textField.getText()
//		);
		
		try {
			textField.setText(Enigma4K.encryptText(passPhrase, handle, roCount,
					pbCount, dirSeed, textField.getText()));
		}
		catch(Exception e) {	LOG.fatal("Enigma Encryption Exception", e);	}
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
		
		String passPhrase = passwordField.getText();
		String handle = handleField.getText();
		String roCountText = roCountField.getText();
		String pbCountText = pbCountField.getText();
		String dirSeedText = dirSeedField.getText();
		
		int roCount = 97, pbCount = 97;
		Long dirSeed = null;

		if(roCountText.length() != 0)
			try {	roCount = Integer.parseInt(roCountText);					}
			catch(Exception e) {	LOG.fatal("Parse Rotor Count", e);			}
		
		if(pbCountText.length() != 0)
			try {	pbCount = Integer.parseInt(pbCountText);					}
			catch(Exception e) {	LOG.fatal("Parse Plugboard Count", e);		}
		
		if(dirSeedText.length() != 0 && dirSeedText.matches("[0-9]+"))	
			try {	dirSeed = Long.parseLong(dirSeedText);						}
			catch(Exception e) {	LOG.fatal("Parse Directory Seed", e);		}

//		System.out.println(
//			"passPhrase: " + passPhrase +
//			", handle: " + handle +
//			", roCount: " + roCount +
//			", pbCount: " + pbCount +
//			", dirSeed: " + dirSeed +
//			", text: " + textField.getText()
//		);
		
		try {
			textField.setText(Enigma4K.decryptText(passPhrase, handle, roCount,
					pbCount, dirSeed, textField.getText()));
		}
		catch(Exception e) {	LOG.fatal("Enigma Decryption Exception", e);	}
    }//GEN-LAST:event_decryptButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

		roCountField.setText(String.valueOf(Enigma4K.COUNT_MIN));
		pbCountField.setText(String.valueOf(Enigma4K.COUNT_MIN));
		handleField.setText("");
		passwordField.setText("");
		dirSeedField.setText("");
		textField.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void roCountFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_roCountFieldFocusGained
		focusGained(evt);
    }//GEN-LAST:event_roCountFieldFocusGained

    private void pbCountFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pbCountFieldFocusGained
		focusGained(evt);
    }//GEN-LAST:event_pbCountFieldFocusGained

    private void handleFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_handleFieldFocusGained
		focusGained(evt);
    }//GEN-LAST:event_handleFieldFocusGained

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
		focusGained(evt);
    }//GEN-LAST:event_passwordFieldFocusGained

    private void dirSeedFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dirSeedFieldFocusGained
		focusGained(evt);
    }//GEN-LAST:event_dirSeedFieldFocusGained

    private void textFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldFocusGained
		focusGained(evt);
    }//GEN-LAST:event_textFieldFocusGained

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
		AboutDialog dialog = new AboutDialog(this, true);
			dialog.setVisible(true);
    }//GEN-LAST:event_aboutButtonActionPerformed

	private void focusGained(java.awt.event.FocusEvent evt) {
		
		if(evt.getSource() instanceof JTextComponent)
			SwingUtilities.invokeLater(() -> {
				((JTextComponent) evt.getSource()).selectAll();
			});
    }
	
	/** @param args the command line arguments		*/
	public static void main(String args[]) {
		
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);

		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {	LOG.fatal("Unable to set Look and Feel", e);	}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new EnigmaGUI().setVisible(true);
		});
	}
	
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton decryptButton;
    private javax.swing.JTextField dirSeedField;
    private javax.swing.JLabel dirSeedLabel;
    private javax.swing.JButton encryptButton;
    private javax.swing.JTextField handleField;
    private javax.swing.JLabel handleLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel passLabel;
    private javax.swing.JTextField passwordField;
    private javax.swing.JTextField pbCountField;
    private javax.swing.JLabel pbLabel;
    private javax.swing.JTextField roCountField;
    private javax.swing.JLabel roLabel;
    private javax.swing.JLabel textDescLabel;
    private javax.swing.JTextArea textField;
    private javax.swing.JLabel textLabel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

}
