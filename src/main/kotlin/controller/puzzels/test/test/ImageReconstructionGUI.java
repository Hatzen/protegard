package controller.puzzels.test.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;

public class ImageReconstructionGUI extends JFrame {

    private final List<Image> images = new ArrayList<>();
    private final JPanel imagePanel;
    private final JPanel controlPanel;

    public ImageReconstructionGUI() {
        super("Image Reconstruction");
        setLayout(new BorderLayout());

        // Create image panel and add it to the frame
        imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        add(imagePanel, BorderLayout.CENTER);

        // Create control panel and add it to the frame
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Images:"));
        JTextField imagesField = new JTextField(10);
        imagesField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] imageEntries = imagesField.getText().split(",");
                for (String imageEntry : imageEntries) {
                    // ToDO use real image imageEntry
                    Image image = new Image();
                    images.add(image);

                    // Add image to the panel and control the display
                    addImage(image);
                }

                // Update the frame with the new images
                updateFrame();
            }
        });
        imagesField.setText("");

        this.setVisible(true);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ImageFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        Image image = new Image();

        JPanel imagePanel = new JPanel();
        imagePanel.add(new JLabel(new ImageIcon(image.getEncodedImage())));

        frame.getContentPane().add(imagePanel);
        frame.setVisible(true);
    }

    private void addImage(Image image) {
        // Create a new label for the image
        JLabel imageLabel = new JLabel();

        // Set the size of the image to fit the label
        int width = 200;
        int height = 200;

        if (image.getWidth() > width || image.getHeight() > height) {
            double aspectRatio = (double) image.getWidth() / image.getHeight();
            if (aspectRatio < 1.0) {
                width = height * aspectRatio;
            } else {
                height = width / aspectRatio;
            }
        }

        // Set the size of the label
        imageLabel.setPreferredSize(new Dimension(width, height));

        // Add the image to the label
        ImageIcon icon = new ImageIcon(image.getEncodedImage());
        imageLabel.setIcon(icon);

        // Create a panel for the image with a mouse listener to detect clicks
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        imagePanel.addMouseListener(new ImageMouseListener(image, imageLabel));

        // Add the image panel to the main image panel
        imagePanel.setPreferredSize(new Dimension(width, height));
        addImagePanel(imagePanel);

    }

    private void addImagePanel(JPanel imagePanel) {
        // Remove any existing images from the frame
        for (Component component : imagePanel.getParent().getComponents()) {
            if (component instanceof JLabel) {
                component.setBounds(0, 0, 0, 0);
                component.setVisible(false);
            }
        }

        // Add the new image panel to the frame
        imagePanel.setBounds(50 + i * 200, 50 + j * 200, 200, 200);

        // Update the display of the frame
        updateFrame();
    }

    private void updateFrame() {
        // Remove any existing images from the frame
        for (Component component : imagePanel.getParent().getComponents()) {
            if (component instanceof JLabel) {
                component.setBounds(0, 0, 0, 0);
                component.setVisible(false);
            }
        }

        // Create panels for each image in the list and add them to the frame
        int i = 0;
        int j = 0;
        for (Image image : images) {
            if (i == 5 && j == 4) {
                break; // Stop adding more images when we hit the last row/column
            }

            JPanel imagePanel = new JPanel();
            addImagePanel(imagePanel);
            i++;
            if (i == 5 || image.getWidth() > 200) {
                i = 0;
                j++;
            }
        }
    }

    private class ImageMouseListener implements MouseListener {

        private final Image image;
        private final JLabel imageLabel;

        public ImageMouseListener(Image image, JLabel imageLabel) {
            this.image = image;
            this.imageLabel = imageLabel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            // Check if the click is close enough to a corner of the image
            if (Math.abs(image.getX() - x) < 20 || Math.abs(image.getY() - y) < 20) {
                // Check if the click is within 10% of the center of the image
                double distance = Math.sqrt(Math.pow(x - image.getX(), 2) + Math.pow(y - image.getY(), 2));
                if (distance < 100 * image.getWidth()) {

                    // Set a new position for the image based on the mouse click
                    image.setPos(x, y);
                    imageLabel.setIcon(new ImageIcon(image.getEncodedImage()));

                    // Update the frame with the new image
                    addImagePanel(imageLabel);

                }
            } else {
                // If not close enough to a corner, rotate the image by 90 degrees clockwise
                double angle = Math.atan2(-image.getY(), image.getX());
                image.rotate(angle);
                imageLabel.setIcon(new ImageIcon(image.getEncodedImage()));
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class Image {

        private double x = 0;
        private double y = 0;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        private byte[] getEncodedImage() {
            // This method should encode the image as a byte array
            // For simplicity, we will just generate a random 3x3 image for now
            Random rand = new Random();
            byte[][] pixels = new byte[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    pixels[i][j] = (byte) rand.nextInt(256);
                }
            }

            // Convert the pixel array to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[9];
            int bytesRead;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    bytesRead = pixels[i][j] + (pixels[(i + 1) % 3][j] << 8) + (pixels[(i + 2) % 3][(j + 1) % 3] << 16);
                    buffer[7 - i * 3 - j] = (byte)(bytesRead & 0xFF);
                }
            }

            return bos.toByteArray();
        }

        private double getWidth() {
            // This method should return the width of the image
            // For simplicity, we will just return a fixed value for now
            return 10;
        }

    }
}