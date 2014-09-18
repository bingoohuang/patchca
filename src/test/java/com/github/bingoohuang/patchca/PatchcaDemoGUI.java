package com.github.bingoohuang.patchca;

import com.github.bingoohuang.patchca.service.Captcha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class PatchcaDemoGUI extends JDialog implements ActionListener {

    private static final long serialVersionUID = 6698906953413370733L;
    private BufferedImage img;
    private JButton reloadButton;

    public PatchcaDemoGUI() {
        super.setTitle("Patchca demo");
        setResizable(false);
        setSize(500, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - this.getSize().width) / 2;
        int y = (dim.height - this.getSize().height) / 2;
        setLocation(x, y);
        JPanel bottom = new JPanel();
        reloadButton = new JButton("Next");
        reloadButton.addActionListener(this);
        bottom.add(reloadButton);
        add(BorderLayout.SOUTH, bottom);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) {
            createImage();
        }
        if (img != null) {
            g.drawImage(img, 20, 30, this);
        }
    }

    public void createImage() {
        // Captcha captcha = new ConfigurableCaptchaService().getCaptcha();
        Captcha captcha = Patchca.next();
        if (captcha.getWord().equals(captcha.getChallenge())) {
            setTitle(captcha.getTips() + " 答:" + captcha.getChallenge());
        } else {
            setTitle(captcha.getTips() + " 问:" + captcha.getWord() + " 答:" + captcha.getChallenge());
        }

        img = captcha.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == reloadButton) {
            createImage();
            repaint();
        }

    }

    public static void main(String[] args) {
        PatchcaDemoGUI f = new PatchcaDemoGUI();
        f.setVisible(true);
    }

}
