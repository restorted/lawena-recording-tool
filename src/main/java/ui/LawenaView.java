
package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class LawenaView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JComboBox<String> cmbSkybox;
    private JButton btnRestart;
    private JComboBox<String> cmbResolution;
    private JComboBox<String> cmbHud;
    private JComboBox<String> cmbFramerate;
    private JComboBox<String> cmbQuality;
    private JSpinner spinnerViewmodelFov;
    private JCheckBox enableMotionBlur;
    private JCheckBox enableParticles;
    private JCheckBox disableCrosshair;
    private JCheckBox disableCrosshairSwitch;
    private JCheckBox disableCombatText;
    private JCheckBox disableHitSounds;
    private JCheckBox disableVoiceChat;
    private JCheckBox disableAnnouncer;
    private JCheckBox disableDominationSounds;
    private JCheckBox disableSteamCloud;
    private JButton btnStartTf;
    private JLabel lblResolution;
    private JLabel lblFrameRate;
    private JLabel lblPreview;
    private JPanel panelMovieOptions;
    private JButton btnSaveSettings;
    private JButton btnChangeMovieFolder;
    private JButton btnClearMovieFolder;
    private JButton btnOpenMovieFolder;
    private JPanel panelBottom;
    private JPanel panelSettingsOptions;
    private JButton btnDefaultSettings;
    private JLabel lblSkyboxPreview;
    private JButton btnLoadSettings;
    private JPanel panelViewmodelOptions;
    private JRadioButton rdbtnViewmodelsOn;
    private JRadioButton rdbtnViewmodelsOff;
    private JRadioButton rdbtnViewmodelsDefault;
    private final ButtonGroup buttonGroupViewmodels = new ButtonGroup();

    /**
     * Create the frame.
     */
    public LawenaView() {
        setTitle("lawena Recording Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 791, 450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width / 2, dim.height / 2);
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        setLocation(x, y);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel panelSettings = new JPanel();
        tabbedPane.addTab("Settings", null, panelSettings, null);
        GridBagLayout gbl_panelSettings = new GridBagLayout();
        gbl_panelSettings.columnWidths = new int[] {
                0, 0, 0, 0
        };
        gbl_panelSettings.rowHeights = new int[] {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        gbl_panelSettings.columnWeights = new double[] {
                0.0, 1.0, 0.0, 0.0
        };
        gbl_panelSettings.rowWeights = new double[] {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE
        };
        panelSettings.setLayout(gbl_panelSettings);

        lblResolution = new JLabel("Resolution:");
        GridBagConstraints gbc_lblResolution = new GridBagConstraints();
        gbc_lblResolution.insets = new Insets(0, 0, 5, 5);
        gbc_lblResolution.anchor = GridBagConstraints.EAST;
        gbc_lblResolution.gridx = 0;
        gbc_lblResolution.gridy = 0;
        panelSettings.add(lblResolution, gbc_lblResolution);

        cmbResolution = new JComboBox<>();
        cmbResolution.setModel(new DefaultComboBoxModel(new String[] {
                "640x360", "854x480", "1280x720", "1920x1080"
        }));
        cmbResolution.setEditable(true);
        GridBagConstraints gbc_cmbResolution = new GridBagConstraints();
        gbc_cmbResolution.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbResolution.insets = new Insets(0, 0, 5, 5);
        gbc_cmbResolution.gridx = 1;
        gbc_cmbResolution.gridy = 0;
        panelSettings.add(cmbResolution, gbc_cmbResolution);

        lblFrameRate = new JLabel("Frame Rate:");
        GridBagConstraints gbc_lblFrameRate = new GridBagConstraints();
        gbc_lblFrameRate.anchor = GridBagConstraints.EAST;
        gbc_lblFrameRate.insets = new Insets(0, 0, 5, 5);
        gbc_lblFrameRate.gridx = 2;
        gbc_lblFrameRate.gridy = 0;
        panelSettings.add(lblFrameRate, gbc_lblFrameRate);

        cmbFramerate = new JComboBox<>();
        cmbFramerate.setModel(new DefaultComboBoxModel(new String[] {
                "60", "120", "240", "480", "960", "1920", "3840"
        }));
        cmbFramerate.setEditable(true);
        GridBagConstraints gbc_cmbFramerate = new GridBagConstraints();
        gbc_cmbFramerate.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbFramerate.insets = new Insets(0, 0, 5, 5);
        gbc_cmbFramerate.gridx = 3;
        gbc_cmbFramerate.gridy = 0;
        panelSettings.add(cmbFramerate, gbc_cmbFramerate);

        enableMotionBlur = new JCheckBox("Enable Motion Blur");
        GridBagConstraints gbc_enableMotionBlur = new GridBagConstraints();
        gbc_enableMotionBlur.anchor = GridBagConstraints.WEST;
        gbc_enableMotionBlur.insets = new Insets(0, 0, 5, 0);
        gbc_enableMotionBlur.gridx = 4;
        gbc_enableMotionBlur.gridy = 0;
        panelSettings.add(enableMotionBlur, gbc_enableMotionBlur);
                
                        JLabel lblHud = new JLabel("HUD:");
                        GridBagConstraints gbc_lblHud = new GridBagConstraints();
                        gbc_lblHud.anchor = GridBagConstraints.EAST;
                        gbc_lblHud.insets = new Insets(0, 0, 5, 5);
                        gbc_lblHud.gridx = 0;
                        gbc_lblHud.gridy = 1;
                        panelSettings.add(lblHud, gbc_lblHud);
                
                        cmbHud = new JComboBox<>();
                        cmbHud.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Minimal (kill notices)", "Medic (hp, ubercharge, cp)", "Full", "Custom"
                        }));
                        GridBagConstraints gbc_cmbHud = new GridBagConstraints();
                        gbc_cmbHud.fill = GridBagConstraints.HORIZONTAL;
                        gbc_cmbHud.insets = new Insets(0, 0, 5, 5);
                        gbc_cmbHud.gridx = 1;
                        gbc_cmbHud.gridy = 1;
                        panelSettings.add(cmbHud, gbc_cmbHud);
                
                        lblSkyboxPreview = new JLabel("");
                        GridBagConstraints gbc_lblSkyboxPreview = new GridBagConstraints();
                        gbc_lblSkyboxPreview.anchor = GridBagConstraints.SOUTHWEST;
                        gbc_lblSkyboxPreview.gridheight = 3;
                        gbc_lblSkyboxPreview.insets = new Insets(0, 0, 5, 5);
                        gbc_lblSkyboxPreview.gridx = 3;
                        gbc_lblSkyboxPreview.gridy = 1;
                        panelSettings.add(lblSkyboxPreview, gbc_lblSkyboxPreview);
        
                JLabel lblDxLevel = new JLabel("Quality:");
                GridBagConstraints gbc_lblDxLevel = new GridBagConstraints();
                gbc_lblDxLevel.anchor = GridBagConstraints.EAST;
                gbc_lblDxLevel.insets = new Insets(0, 0, 5, 5);
                gbc_lblDxLevel.gridx = 0;
                gbc_lblDxLevel.gridy = 2;
                panelSettings.add(lblDxLevel, gbc_lblDxLevel);
        
                cmbQuality = new JComboBox<>();
                cmbQuality.setModel(new DefaultComboBoxModel(new String[] {"DirectX 8.0 (Lowest)", "DirectX 8.1 (Low)", "DirectX 9.0 (Medium)", "DirectX 9.5 (High)", "DirectX 9.8 (Highest)"}));
                GridBagConstraints gbc_cmbQuality = new GridBagConstraints();
                gbc_cmbQuality.fill = GridBagConstraints.HORIZONTAL;
                gbc_cmbQuality.insets = new Insets(0, 0, 5, 5);
                gbc_cmbQuality.gridx = 1;
                gbc_cmbQuality.gridy = 2;
                panelSettings.add(cmbQuality, gbc_cmbQuality);

        disableCrosshair = new JCheckBox("Disable Crosshair");
        GridBagConstraints gbc_disableCrosshair = new GridBagConstraints();
        gbc_disableCrosshair.anchor = GridBagConstraints.WEST;
        gbc_disableCrosshair.insets = new Insets(0, 0, 5, 0);
        gbc_disableCrosshair.gridx = 4;
        gbc_disableCrosshair.gridy = 2;
        panelSettings.add(disableCrosshair, gbc_disableCrosshair);
        
                lblPreview = new JLabel("");
                GridBagConstraints gbc_lblPreview = new GridBagConstraints();
                gbc_lblPreview.anchor = GridBagConstraints.EAST;
                gbc_lblPreview.insets = new Insets(0, 0, 5, 5);
                gbc_lblPreview.gridx = 2;
                gbc_lblPreview.gridy = 3;
                panelSettings.add(lblPreview, gbc_lblPreview);

        disableCrosshairSwitch = new JCheckBox("Disable Crosshair Switching in demos");
        GridBagConstraints gbc_disableCrosshairSwitch = new GridBagConstraints();
        gbc_disableCrosshairSwitch.anchor = GridBagConstraints.WEST;
        gbc_disableCrosshairSwitch.insets = new Insets(0, 0, 5, 0);
        gbc_disableCrosshairSwitch.gridx = 4;
        gbc_disableCrosshairSwitch.gridy = 3;
        panelSettings.add(disableCrosshairSwitch, gbc_disableCrosshairSwitch);

        JLabel lblSkybox = new JLabel("Skybox:");
        GridBagConstraints gbc_lblSkybox = new GridBagConstraints();
        gbc_lblSkybox.anchor = GridBagConstraints.EAST;
        gbc_lblSkybox.insets = new Insets(0, 0, 5, 5);
        gbc_lblSkybox.gridx = 0;
        gbc_lblSkybox.gridy = 3;
        panelSettings.add(lblSkybox, gbc_lblSkybox);

        cmbSkybox = new JComboBox<>();
        GridBagConstraints gbc_cmbSkybox = new GridBagConstraints();
        gbc_cmbSkybox.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbSkybox.insets = new Insets(0, 0, 5, 5);
        gbc_cmbSkybox.gridx = 1;
        gbc_cmbSkybox.gridy = 3;
        panelSettings.add(cmbSkybox, gbc_cmbSkybox);
        
                JLabel lblViewmodels = new JLabel("Viewmodels:");
                GridBagConstraints gbc_lblViewmodels = new GridBagConstraints();
                gbc_lblViewmodels.anchor = GridBagConstraints.EAST;
                gbc_lblViewmodels.insets = new Insets(0, 0, 5, 5);
                gbc_lblViewmodels.gridx = 0;
                gbc_lblViewmodels.gridy = 4;
                panelSettings.add(lblViewmodels, gbc_lblViewmodels);
                
                panelViewmodelOptions = new JPanel();
                FlowLayout fl_panelViewmodelOptions = (FlowLayout) panelViewmodelOptions.getLayout();
                fl_panelViewmodelOptions.setAlignment(FlowLayout.LEFT);
                fl_panelViewmodelOptions.setVgap(0);
                fl_panelViewmodelOptions.setHgap(0);
                GridBagConstraints gbc_panelViewmodelOptions = new GridBagConstraints();
                gbc_panelViewmodelOptions.insets = new Insets(0, 0, 5, 5);
                gbc_panelViewmodelOptions.fill = GridBagConstraints.BOTH;
                gbc_panelViewmodelOptions.gridx = 1;
                gbc_panelViewmodelOptions.gridy = 4;
                panelSettings.add(panelViewmodelOptions, gbc_panelViewmodelOptions);
                
                rdbtnViewmodelsOn = new JRadioButton("On");
                buttonGroupViewmodels.add(rdbtnViewmodelsOn);
                panelViewmodelOptions.add(rdbtnViewmodelsOn);
                
                rdbtnViewmodelsOff = new JRadioButton("Off");
                buttonGroupViewmodels.add(rdbtnViewmodelsOff);
                panelViewmodelOptions.add(rdbtnViewmodelsOff);
                
                rdbtnViewmodelsDefault = new JRadioButton("Default");
                buttonGroupViewmodels.add(rdbtnViewmodelsDefault);
                panelViewmodelOptions.add(rdbtnViewmodelsDefault);
        
                JLabel lblViewmodelFov = new JLabel("Viewmodel FOV:");
                GridBagConstraints gbc_lblViewmodelFov = new GridBagConstraints();
                gbc_lblViewmodelFov.anchor = GridBagConstraints.EAST;
                gbc_lblViewmodelFov.insets = new Insets(0, 0, 5, 5);
                gbc_lblViewmodelFov.gridx = 2;
                gbc_lblViewmodelFov.gridy = 4;
                panelSettings.add(lblViewmodelFov, gbc_lblViewmodelFov);
        
                spinnerViewmodelFov = new JSpinner();
                spinnerViewmodelFov.setModel(new SpinnerNumberModel(70, 55, 90, 1));
                GridBagConstraints gbc_spinnerViewmodelFov = new GridBagConstraints();
                gbc_spinnerViewmodelFov.anchor = GridBagConstraints.WEST;
                gbc_spinnerViewmodelFov.insets = new Insets(0, 0, 5, 5);
                gbc_spinnerViewmodelFov.gridx = 3;
                gbc_spinnerViewmodelFov.gridy = 4;
                panelSettings.add(spinnerViewmodelFov, gbc_spinnerViewmodelFov);

        disableCombatText = new JCheckBox("Disable Combat Text");
        GridBagConstraints gbc_disableCombatText = new GridBagConstraints();
        gbc_disableCombatText.anchor = GridBagConstraints.WEST;
        gbc_disableCombatText.insets = new Insets(0, 0, 5, 0);
        gbc_disableCombatText.gridx = 4;
        gbc_disableCombatText.gridy = 4;
        panelSettings.add(disableCombatText, gbc_disableCombatText);

        enableParticles = new JCheckBox("Enable Custom Particle Effects");
        GridBagConstraints gbc_enableParticles = new GridBagConstraints();
        gbc_enableParticles.anchor = GridBagConstraints.WEST;
        gbc_enableParticles.insets = new Insets(0, 0, 5, 0);
        gbc_enableParticles.gridx = 4;
        gbc_enableParticles.gridy = 1;
        panelSettings.add(enableParticles, gbc_enableParticles);

        disableHitSounds = new JCheckBox("Disable Hit Sounds");
        GridBagConstraints gbc_disableHitSounds = new GridBagConstraints();
        gbc_disableHitSounds.weightx = 2.0;
        gbc_disableHitSounds.anchor = GridBagConstraints.WEST;
        gbc_disableHitSounds.insets = new Insets(0, 0, 5, 0);
        gbc_disableHitSounds.gridx = 4;
        gbc_disableHitSounds.gridy = 5;
        panelSettings.add(disableHitSounds, gbc_disableHitSounds);

        disableVoiceChat = new JCheckBox("Disable Voice Chat");
        GridBagConstraints gbc_disableVoiceChat = new GridBagConstraints();
        gbc_disableVoiceChat.anchor = GridBagConstraints.WEST;
        gbc_disableVoiceChat.insets = new Insets(0, 0, 5, 0);
        gbc_disableVoiceChat.gridx = 4;
        gbc_disableVoiceChat.gridy = 6;
        panelSettings.add(disableVoiceChat, gbc_disableVoiceChat);

        disableAnnouncer = new JCheckBox("Disable Announcer");
        GridBagConstraints gbc_disableAnnouncer = new GridBagConstraints();
        gbc_disableAnnouncer.anchor = GridBagConstraints.WEST;
        gbc_disableAnnouncer.insets = new Insets(0, 0, 5, 0);
        gbc_disableAnnouncer.gridx = 4;
        gbc_disableAnnouncer.gridy = 7;
        panelSettings.add(disableAnnouncer, gbc_disableAnnouncer);

        disableDominationSounds = new JCheckBox("Disable Domination and Revenge Sounds");
        GridBagConstraints gbc_disableDominationSounds = new GridBagConstraints();
        gbc_disableDominationSounds.anchor = GridBagConstraints.WEST;
        gbc_disableDominationSounds.insets = new Insets(0, 0, 5, 0);
        gbc_disableDominationSounds.gridx = 4;
        gbc_disableDominationSounds.gridy = 8;
        panelSettings.add(disableDominationSounds, gbc_disableDominationSounds);

        disableSteamCloud = new JCheckBox("Disable Steam Cloud (recommended)");
        GridBagConstraints gbc_disableSteamCloud = new GridBagConstraints();
        gbc_disableSteamCloud.insets = new Insets(0, 0, 5, 0);
        gbc_disableSteamCloud.anchor = GridBagConstraints.WEST;
        gbc_disableSteamCloud.gridx = 4;
        gbc_disableSteamCloud.gridy = 9;
        panelSettings.add(disableSteamCloud, gbc_disableSteamCloud);

        panelBottom = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        GridBagConstraints gbc_panelBottom = new GridBagConstraints();
        gbc_panelBottom.anchor = GridBagConstraints.WEST;
        gbc_panelBottom.gridwidth = 4;
        gbc_panelBottom.insets = new Insets(0, 0, 0, 5);
        gbc_panelBottom.fill = GridBagConstraints.VERTICAL;
        gbc_panelBottom.gridx = 0;
        gbc_panelBottom.gridy = 11;
        panelSettings.add(panelBottom, gbc_panelBottom);

        panelSettingsOptions = new JPanel();
        panelBottom.add(panelSettingsOptions);
        panelSettingsOptions.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        FlowLayout flowLayout_1 = (FlowLayout) panelSettingsOptions.getLayout();
        flowLayout_1.setVgap(0);
        flowLayout_1.setHgap(0);

        btnSaveSettings = new JButton("Save");
        panelSettingsOptions.add(btnSaveSettings);
        
        btnLoadSettings = new JButton("Load");
        panelSettingsOptions.add(btnLoadSettings);

        btnDefaultSettings = new JButton("Defaults");
        panelSettingsOptions.add(btnDefaultSettings);

        panelMovieOptions = new JPanel();
        panelBottom.add(panelMovieOptions);
        panelMovieOptions.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Movie Folder", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        FlowLayout fl_panelMovieOptions = (FlowLayout) panelMovieOptions.getLayout();
        fl_panelMovieOptions.setVgap(0);
        fl_panelMovieOptions.setHgap(0);

        btnChangeMovieFolder = new JButton("Change...");
        panelMovieOptions.add(btnChangeMovieFolder);

        btnOpenMovieFolder = new JButton("Open");
        panelMovieOptions.add(btnOpenMovieFolder);

        btnClearMovieFolder = new JButton("Clear");
        panelMovieOptions.add(btnClearMovieFolder);

        btnStartTf = new JButton("Start Team Fortress 2");
        btnStartTf.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStartTf.setIconTextGap(10);
        btnStartTf.setIcon(new ImageIcon(LawenaView.class.getResource("/ui/tf2full.png")));
        GridBagConstraints gbc_btnStartTf = new GridBagConstraints();
        gbc_btnStartTf.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnStartTf.gridx = 4;
        gbc_btnStartTf.gridy = 11;
        panelSettings.add(btnStartTf, gbc_btnStartTf);

        JPanel panelVdm = new JPanel();
        tabbedPane.addTab("VDM", null, panelVdm, null);
        GridBagLayout gbl_panelVdm = new GridBagLayout();
        gbl_panelVdm.columnWidths = new int[]{0};
        gbl_panelVdm.rowHeights = new int[]{0};
        gbl_panelVdm.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panelVdm.rowWeights = new double[]{Double.MIN_VALUE};
        panelVdm.setLayout(gbl_panelVdm);

        JPanel panelAbout = new JPanel();
        tabbedPane.addTab("About", null, panelAbout, null);
        GridBagLayout gbl_panelAbout = new GridBagLayout();
        gbl_panelAbout.columnWidths = new int[] {
                0, 0
        };
        gbl_panelAbout.rowHeights = new int[] {
                0, 0
        };
        gbl_panelAbout.columnWeights = new double[] {
                0.0, Double.MIN_VALUE
        };
        gbl_panelAbout.rowWeights = new double[] {
                0.0, Double.MIN_VALUE
        };
        panelAbout.setLayout(gbl_panelAbout);

        btnRestart = new JButton("Restart");
        GridBagConstraints gbc_btnRestart = new GridBagConstraints();
        gbc_btnRestart.gridx = 0;
        gbc_btnRestart.gridy = 0;
        panelAbout.add(btnRestart, gbc_btnRestart);
    }

    public JComboBox<String> getCmbResolution() {
        return cmbResolution;
    }

    public JComboBox<String> getCmbSkybox() {
        return cmbSkybox;
    }

    public JButton getBtnRestart() {
        return btnRestart;
    }

    public JComboBox<String> getCmbHud() {
        return cmbHud;
    }

    public ButtonGroup getButtonGroupViewmodels() {
        return buttonGroupViewmodels;
    }

    public JComboBox<String> getCmbFramerate() {
        return cmbFramerate;
    }

    public JComboBox<String> getCmbQuality() {
        return cmbQuality;
    }

    public JSpinner getSpinnerViewmodelFov() {
        return spinnerViewmodelFov;
    }

    public JCheckBox getEnableMotionBlur() {
        return enableMotionBlur;
    }

    public JCheckBox getEnableParticles() {
        return enableParticles;
    }

    public JCheckBox getDisableCrosshair() {
        return disableCrosshair;
    }

    public JCheckBox getDisableCrosshairSwitch() {
        return disableCrosshairSwitch;
    }

    public JCheckBox getDisableCombatText() {
        return disableCombatText;
    }

    public JCheckBox getDisableHitSounds() {
        return disableHitSounds;
    }

    public JCheckBox getDisableVoiceChat() {
        return disableVoiceChat;
    }

    public JCheckBox getDisableAnnouncer() {
        return disableAnnouncer;
    }

    public JCheckBox getDisableDominationSounds() {
        return disableDominationSounds;
    }

    public JCheckBox getDisableSteamCloud() {
        return disableSteamCloud;
    }

    public JButton getBtnStartTf() {
        return btnStartTf;
    }

    public JLabel getLblResolution() {
        return lblResolution;
    }

    public JLabel getLblFrameRate() {
        return lblFrameRate;
    }

    public JLabel getLblSkyboxPreview() {
        return lblSkyboxPreview;
    }
    public JLabel getLblPreview() {
        return lblPreview;
    }
    public JButton getBtnSaveSettings() {
        return btnSaveSettings;
    }
    public JButton getBtnLoadSettings() {
        return btnLoadSettings;
    }
    public JButton getBtnDefaultSettings() {
        return btnDefaultSettings;
    }
    public JButton getBtnChangeMovieFolder() {
        return btnChangeMovieFolder;
    }
    public JButton getBtnOpenMovieFolder() {
        return btnOpenMovieFolder;
    }
    public JButton getBtnClearMovieFolder() {
        return btnClearMovieFolder;
    }
    public JRadioButton getRdbtnViewmodelsOn() {
        return rdbtnViewmodelsOn;
    }
    public JRadioButton getRdbtnViewmodelsOff() {
        return rdbtnViewmodelsOff;
    }
    public JRadioButton getRdbtnViewmodelsDefault() {
        return rdbtnViewmodelsDefault;
    }
}