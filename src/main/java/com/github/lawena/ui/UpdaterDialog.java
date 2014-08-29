package com.github.lawena.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;

import com.github.lawena.update.BuildInfo;
import com.github.lawena.update.Channel;

public class UpdaterDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  private final JPanel contentPanel = new JPanel();
  private JComboBox<Channel> channelsComboBox;
  private JComboBox<BuildInfo> buildsComboBox;
  private JTextPane channelDataPane;
  private JButton okButton;
  private JButton cancelButton;
  private JTextField channelTextField;
  private JTextField buildTextField;
  private JButton refreshButton;
  private JLabel lblLastCheck;
  private JLabel lastCheckLabel;
  private JLabel switchStatusLabel;

  public UpdaterDialog() {
    setIconImage(Toolkit.getDefaultToolkit().getImage(
        UpdaterDialog.class.getResource("/com/github/lawena/ui/fugue/globe-green.png")));
    setTitle("Change Update Branch");
    setBounds(100, 100, 600, 400);
    BorderLayout borderLayout = new BorderLayout();
    getContentPane().setLayout(borderLayout);
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.NORTH);
    GridBagLayout gbl_contentPanel = new GridBagLayout();
    gbl_contentPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
    gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0};
    gbl_contentPanel.columnWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
    gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    contentPanel.setLayout(gbl_contentPanel);
    {
      JLabel lblNewLabel =
          new JLabel(
              "<html>Configure the updater to retrieve the latest version from various Update Branches. Press Check Now to get the most recent information and then Switch Update Branch to perform the upgrade.");
      GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
      gbc_lblNewLabel.gridwidth = 5;
      gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
      gbc_lblNewLabel.gridx = 0;
      gbc_lblNewLabel.gridy = 0;
      contentPanel.add(lblNewLabel, gbc_lblNewLabel);
    }
    {
      JLabel lblCurrentChannel = new JLabel("Current Branch:");
      GridBagConstraints gbc_lblCurrentChannel = new GridBagConstraints();
      gbc_lblCurrentChannel.anchor = GridBagConstraints.EAST;
      gbc_lblCurrentChannel.insets = new Insets(0, 0, 5, 5);
      gbc_lblCurrentChannel.gridx = 0;
      gbc_lblCurrentChannel.gridy = 1;
      contentPanel.add(lblCurrentChannel, gbc_lblCurrentChannel);
    }
    {
      channelTextField = new JTextField();
      channelTextField.setEditable(false);
      GridBagConstraints gbc_channelTextField = new GridBagConstraints();
      gbc_channelTextField.insets = new Insets(0, 0, 5, 5);
      gbc_channelTextField.fill = GridBagConstraints.HORIZONTAL;
      gbc_channelTextField.gridx = 1;
      gbc_channelTextField.gridy = 1;
      contentPanel.add(channelTextField, gbc_channelTextField);
      channelTextField.setColumns(10);
    }
    {
      JLabel lblVersion_1 = new JLabel("Version:");
      GridBagConstraints gbc_lblVersion_1 = new GridBagConstraints();
      gbc_lblVersion_1.anchor = GridBagConstraints.EAST;
      gbc_lblVersion_1.insets = new Insets(0, 0, 5, 5);
      gbc_lblVersion_1.gridx = 2;
      gbc_lblVersion_1.gridy = 1;
      contentPanel.add(lblVersion_1, gbc_lblVersion_1);
    }
    {
      buildTextField = new JTextField();
      buildTextField.setEditable(false);
      GridBagConstraints gbc_buildTextField = new GridBagConstraints();
      gbc_buildTextField.gridwidth = 2;
      gbc_buildTextField.insets = new Insets(0, 0, 5, 0);
      gbc_buildTextField.fill = GridBagConstraints.HORIZONTAL;
      gbc_buildTextField.gridx = 3;
      gbc_buildTextField.gridy = 1;
      contentPanel.add(buildTextField, gbc_buildTextField);
      buildTextField.setColumns(10);
    }
    {
      JLabel lblUpdateChannel = new JLabel("New Branch:");
      GridBagConstraints gbc_lblUpdateChannel = new GridBagConstraints();
      gbc_lblUpdateChannel.insets = new Insets(0, 0, 5, 5);
      gbc_lblUpdateChannel.anchor = GridBagConstraints.EAST;
      gbc_lblUpdateChannel.gridx = 0;
      gbc_lblUpdateChannel.gridy = 2;
      contentPanel.add(lblUpdateChannel, gbc_lblUpdateChannel);
    }
    {
      channelsComboBox = new JComboBox<>();
      GridBagConstraints gbc_channelsComboBox = new GridBagConstraints();
      gbc_channelsComboBox.insets = new Insets(0, 0, 5, 5);
      gbc_channelsComboBox.fill = GridBagConstraints.HORIZONTAL;
      gbc_channelsComboBox.gridx = 1;
      gbc_channelsComboBox.gridy = 2;
      contentPanel.add(channelsComboBox, gbc_channelsComboBox);
    }
    {
      JLabel lblVersion = new JLabel("Version:");
      GridBagConstraints gbc_lblVersion = new GridBagConstraints();
      gbc_lblVersion.anchor = GridBagConstraints.EAST;
      gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
      gbc_lblVersion.gridx = 2;
      gbc_lblVersion.gridy = 2;
      contentPanel.add(lblVersion, gbc_lblVersion);
    }
    {
      buildsComboBox = new JComboBox<>();
      GridBagConstraints gbc_buildsComboBox = new GridBagConstraints();
      gbc_buildsComboBox.gridwidth = 2;
      gbc_buildsComboBox.insets = new Insets(0, 0, 5, 0);
      gbc_buildsComboBox.fill = GridBagConstraints.HORIZONTAL;
      gbc_buildsComboBox.gridx = 3;
      gbc_buildsComboBox.gridy = 2;
      contentPanel.add(buildsComboBox, gbc_buildsComboBox);
    }
    {
      lblLastCheck = new JLabel("Last Check:");
      GridBagConstraints gbc_lblLastCheck = new GridBagConstraints();
      gbc_lblLastCheck.anchor = GridBagConstraints.EAST;
      gbc_lblLastCheck.insets = new Insets(0, 0, 0, 5);
      gbc_lblLastCheck.gridx = 0;
      gbc_lblLastCheck.gridy = 3;
      contentPanel.add(lblLastCheck, gbc_lblLastCheck);
    }
    {
      lastCheckLabel = new JLabel("Never");
      GridBagConstraints gbc_lastCheckLabel = new GridBagConstraints();
      gbc_lastCheckLabel.anchor = GridBagConstraints.WEST;
      gbc_lastCheckLabel.insets = new Insets(0, 0, 0, 5);
      gbc_lastCheckLabel.gridx = 1;
      gbc_lastCheckLabel.gridy = 3;
      contentPanel.add(lastCheckLabel, gbc_lastCheckLabel);
    }
    {
      JPanel panel = new JPanel();
      FlowLayout flowLayout = (FlowLayout) panel.getLayout();
      flowLayout.setAlignment(FlowLayout.RIGHT);
      flowLayout.setVgap(0);
      flowLayout.setHgap(0);
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.gridwidth = 3;
      gbc_panel.fill = GridBagConstraints.BOTH;
      gbc_panel.gridx = 2;
      gbc_panel.gridy = 3;
      contentPanel.add(panel, gbc_panel);
      {
        refreshButton = new JButton("Check Now");
        panel.add(refreshButton);
      }
    }
    {
      JPanel buttonPane = new JPanel();
      FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
      buttonPane.setLayout(fl_buttonPane);
      getContentPane().add(buttonPane, BorderLayout.SOUTH);
      {
        switchStatusLabel = new JLabel(" ");
        switchStatusLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        buttonPane.add(switchStatusLabel);
      }
      {
        okButton = new JButton("Switch Update Branch");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
      }
      {
        cancelButton = new JButton("Close");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
      }
    }
    {
      JPanel centerPanel = new JPanel();
      getContentPane().add(centerPanel, BorderLayout.CENTER);
      centerPanel.setLayout(new BorderLayout(0, 0));
      {
        JScrollPane scrollPane = new JScrollPane();
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        {
          channelDataPane = new JTextPane();
          channelDataPane.setContentType("text/html");
          channelDataPane.setEditable(false);
          String style =
              new StringBuilder().append("body { font-family: ")
                  .append(UIManager.getDefaults().getFont("TextPane.font").getFamily())
                  .append("; ").append("font-size: 10pt; text-align: left}").toString();

          ((HTMLDocument) channelDataPane.getDocument()).getStyleSheet().addRule(style);
          scrollPane.setViewportView(channelDataPane);
        }
      }
    }
  }

  public JComboBox<Channel> getChannelsComboBox() {
    return channelsComboBox;
  }

  public JComboBox<BuildInfo> getBuildsComboBox() {
    return buildsComboBox;
  }

  public JTextPane getChannelDataPane() {
    return channelDataPane;
  }

  public JButton getOkButton() {
    return okButton;
  }

  public JButton getCancelButton() {
    return cancelButton;
  }

  public JButton getRefreshButton() {
    return refreshButton;
  }

  public JTextField getChannelTextField() {
    return channelTextField;
  }

  public JTextField getBuildTextField() {
    return buildTextField;
  }

  public JLabel getLastCheckLabel() {
    return lastCheckLabel;
  }

  public JLabel getSwitchStatusLabel() {
    return switchStatusLabel;
  }
}
