
import javax.swing.JFrame;

public class OyunEkrani extends JFrame {
    
    public static void main(String[] args) {
        
        OyunEkrani ekran = new OyunEkrani();
        
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setTitle("Araba Oyunu");
        
        ekran.setSize(600,900);
        ekran.setLocation(300,100);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        
        oyun.setFocusCycleRoot(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        
        ekran.setVisible(true);
    }
}
