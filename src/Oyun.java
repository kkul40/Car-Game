
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Oyun extends JPanel implements KeyListener, ActionListener{

    
    Timer timer = new Timer(2,this);
    
    private int ekstraSkor = 0;
    private int bolum = 1;
    private int skor = 0;
    private int skorsec ;
    private int skorsec2 ;
    private int haritaSayisi = 0;
    private boolean nextBolum = true;
    private boolean oyun = true;
    private boolean oyunbaşla = false;
    private int kalanHak = 3;
    
    private int arabaX = 214;
    private int arabadirX = 24;
    private int arabaY = 600;
    private boolean arabadirXX = false;
    private boolean arabadir_XX = false;
    private boolean arabaKont = true;
    
    private int serit1Y = 0;
    private int serit11Y = 64;
    
    private int serit2Y = 300;
    private int serit22Y = 364;
    
    private int serit3Y = 600;
    private int serit33Y = 664;
    
    private int serit0Y = -300;
    private int serit00Y = -236;
    private int seritdirY = 4;
   
    
    
    private BufferedImage image;
    
    private BufferedImage arabasag;
    private BufferedImage arabasol;
    private BufferedImage image2 ;
    private BufferedImage firat;
    private boolean duman = false;
    private BufferedImage dumanimg ;
    private int yon = 0;
    
    
    private boolean esc = false;
    
    private String harita[][]= new String[54][3];
    
    private int dusman1Kontrol = -200;
    private int dusman2Kontrol = -750;
    private int mesafe = 900;
    private int dusman1 = dusman1Kontrol;
    private int dusman2 = dusman2Kontrol;
    private int X, XX, X2, XX2= 600;
    
    private int dusmanKontrol = 3;
    private int dusmanKontrol2 = 5;
    
    private int dusmanHız = 8;
    
    Random r = new Random();
    
    public Oyun() {
        
        // RESİMLER
        try {
            image = ImageIO.read(this.getClass().getResource("araba.png"));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            image2 = ImageIO.read(this.getClass().getResource("dusman.png"));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            arabasol = ImageIO.read(this.getClass().getResource("arabasol.png"));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            arabasag = ImageIO.read(this.getClass().getResource("arabasag.png"));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dumanimg = ImageIO.read(this.getClass().getResource("duman.png"));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            firat = ImageIO.read(this.getClass().getResource("firat.png"));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        // ARKA PLAN RENGİ
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        
    }

    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);

        // DÜŞMAN OLUŞTURMA
        if (nextBolum) {
            nextBolum = false;
            HaritaOluştur();
        }
        
        if (dusmanKontrol2 <=49) {
            if (dusmanKontrol2 == 49 && dusman2 >=mesafe) {
                if (bolum >= 4) {
                    
                }else{
                    bolum++;
                    ekstraSkor +=10;
                    dusmanHız+=4;
                    mesafe +=225;
                    dusman2Kontrol -=125;
                }
                nextBolum = true;
                
                dusmanKontrol = 3;
                dusmanKontrol2 = 5;
                dusman1 = dusman1Kontrol;
                dusman2 = dusman2Kontrol;
            }
            
            if (dusman1 >=mesafe) {
                dusman1 = dusman1Kontrol;
                dusmanKontrol += 4;
                skor += skorsec+ekstraSkor;
            }
            
            if (dusman2 >=mesafe) {
                dusman2 = dusman1Kontrol;
                dusmanKontrol2 += 4;
                skor += skorsec2+ekstraSkor;
            }       
        
            // A. SATIR

            // 0. durum
            if ("|".equals(harita[dusmanKontrol][0]) && "|".equals(harita[dusmanKontrol][1]) && "X".equals(harita[dusmanKontrol][2])) {
                g.drawImage(image2, 400, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                X=400; XX=600;
                skorsec = 5;
            } //1. durum
            else if ("|".equals(harita[dusmanKontrol][0]) && "X".equals(harita[dusmanKontrol][1]) && "|".equals(harita[dusmanKontrol][2])) {
                g.drawImage(image2, 200, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                X=200; XX=600;
                skorsec = 5;
            }//2. durum
            else if ("X".equals(harita[dusmanKontrol][0]) && "|".equals(harita[dusmanKontrol][1]) && "|".equals(harita[dusmanKontrol][2])) {
                g.drawImage(image2, 0, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                X=0; XX=600;
                skorsec = 5;
            }//3. durum
            else if ("|".equals(harita[dusmanKontrol][0]) && "X".equals(harita[dusmanKontrol][1]) && "X".equals(harita[dusmanKontrol][2])) {
                g.drawImage(image2, 200, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                g.drawImage(image2, 400, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                X=200; XX=400;
                skorsec = 10;
            }//4. durum
            else if ("X".equals(harita[dusmanKontrol][0]) && "X".equals(harita[dusmanKontrol][1]) && "|".equals(harita[dusmanKontrol][2])) {
                g.drawImage(image2, 0, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                g.drawImage(image2, 200, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                X=0; XX=200;
                skorsec = 10;
            }//5. durum
            else if ("X".equals(harita[dusmanKontrol][0]) && "|".equals(harita[dusmanKontrol][1]) && "X".equals(harita[dusmanKontrol][2])) {
                g.drawImage(image2, 0, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                g.drawImage(image2, 400, dusman1, image2.getWidth()*3, image2.getHeight()*3, this);
                X=0; XX=400;
                skorsec = 10;
            }else{
                X = 600;
                XX = 600;
                X2 = 600;
                XX2 = 600;
            }
            // A+2. SATIR

            // 0. durum
            if ("|".equals(harita[dusmanKontrol2][0]) && "|".equals(harita[dusmanKontrol2][1]) && "X".equals(harita[dusmanKontrol2][2])) {
                g.drawImage(image2, 400,dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                X2=400; XX2=600;
                skorsec2 = 5;
            } //1. durum
            else if ("|".equals(harita[dusmanKontrol2][0]) && "X".equals(harita[dusmanKontrol2][1]) && "|".equals(harita[dusmanKontrol2][2])) {
                g.drawImage(image2, 200, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                X2=200; XX2=600;
                skorsec2 = 5;
            }//2. durum
            else if ("X".equals(harita[dusmanKontrol2][0]) && "|".equals(harita[dusmanKontrol2][1]) && "|".equals(harita[dusmanKontrol2][2])) {
                g.drawImage(image2, 0, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                X2=0; XX2=600;
                skorsec2 = 5;
            }//3. durum
            else if ("|".equals(harita[dusmanKontrol2][0]) && "X".equals(harita[dusmanKontrol2][1]) && "X".equals(harita[dusmanKontrol2][2])) {
                g.drawImage(image2, 200, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                g.drawImage(image2, 400, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                X2=200; XX2=400;
                skorsec2 = 10;
            }//4. durum
            else if ("X".equals(harita[dusmanKontrol2][0]) && "X".equals(harita[dusmanKontrol2][1]) && "|".equals(harita[dusmanKontrol2][2])) {
                g.drawImage(image2, 0, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                g.drawImage(image2, 200, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                X2=0; XX2=200;
                skorsec2 = 10;
            }//5. durum
            else if ("X".equals(harita[dusmanKontrol2][0]) && "|".equals(harita[dusmanKontrol2][1]) && "X".equals(harita[dusmanKontrol2][2])) {
                g.drawImage(image2, 0, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                g.drawImage(image2, 400, dusman2, image2.getWidth()*3, image2.getHeight()*3, this);
                X2=0; XX2=400;
                skorsec2 = 10;
            }else{
                X = 600;
                XX = 600;
                X2 = 600;
                XX2 = 600;
            }
        }
        
        
        // SERİT 1
        g.setColor(Color.gray);
        g.fillRect(190, serit1Y, 3, 200);
        g.fillRect(410, serit11Y, 3, 200);
        
        // SERİT 2
        g.fillRect(190, serit2Y, 3, 200);
        g.fillRect(410, serit22Y, 3, 200);
        
        // SERİT 3
        g.fillRect(190, serit3Y, 3, 200);
        g.fillRect(410, serit33Y, 3, 200);
        
        // SERİT 4
        g.fillRect(190, serit0Y, 3, 200);
        g.fillRect(410, serit00Y, 3, 200);
        
        // SAĞ - SOL ŞERİT ÇİZGİLERİ
        g.setColor(Color.gray);
        g.fillRect(0, 0, 10, 900);
        g.fillRect(584, 0, 10, 900);
        
        // ARABA YÖNLENDİRME
        switch (yon) {
            case 0:
                g.drawImage(image, arabaX, arabaY, image.getWidth()*3, image.getHeight()*3,this);
                break;
            case 1:
                g.drawImage(arabasag, arabaX+40, arabaY+20, image.getWidth()*5/2, image.getHeight()*5/2,this);
                break;
            case 2:
                g.drawImage(arabasol, arabaX, arabaY+20, image.getWidth()*5/2, image.getHeight()*5/2,this);
                break;
        }
        
        // SKOR VE BÖLÜM TABLOSU
        if (bolum >= 4) {
            g.setColor(Color.white);
            g.setFont(new Font("mono", Font.BOLD,20));
            g.drawString("Sonsuz", 40, 30);
            g.drawString("Skor : "+skor, 420, 30);
        }else {
            g.setColor(Color.white);
            g.setFont(new Font("mono", Font.BOLD,20));
            g.drawString("Bölüm : "+bolum, 20, 30);
            g.drawString("Skor : "+skor, 420, 30);
        }
        
        // DUMAN EFEKTİ
        if (duman) {
            g.drawImage(dumanimg, arabaX, arabaY-100, 200, 200 ,this);
        }
        g.drawImage(firat, 275, 10, firat.getWidth()/11, firat.getHeight()/11,this);
        
        // OYUN YANMA EKRANI
        if (!oyun) {
            oyun = false;
            if (kalanHak == 0) {
                g.setColor(Color.red);
                g.setFont(new Font("mono", Font.BOLD, 30));
                g.drawString("Oyun Bitti, Skorun : "+skor, 150, 300);
                g.setFont(new Font("mono", Font.BOLD,20));
                g.drawString("Başlamak için 'ENTER' tuşlayınız", 150, 360); 
            }else{
                g.setColor(Color.yellow);
                g.setFont(new Font("mono", Font.BOLD, 30));
                g.drawString("Yandınız, Kalan Hakkınız : "+kalanHak, 120, 300);
                g.setFont(new Font("mono", Font.BOLD,20));
                g.drawString("Devam etmek için 'ENTER' tuşlayınız", 130, 360); 
            }
            
            
            g.setFont(new Font("mono", Font.BOLD,15));
            g.drawString("Çıkmak İçin 'ESC' tuşlayınız", 200, 410); 
        }
        
        
        // OYUN BAŞLATMA EKRANI
        if (!oyunbaşla) {
            g.setColor(Color.green);
            g.setFont(new Font("mono", Font.BOLD, 30));
            g.drawString("Başlamak için 'ENTER' tuşlayınız", 70, 360); 
        }
    }
    
    @Override
    public void repaint() {
        super.repaint();
    }
    
     @Override
    public void keyPressed(KeyEvent e) {
        
        int c = e.getKeyCode();
        
        // SAĞ - SOL HAREKETLERİ
        
        if (c == KeyEvent.VK_LEFT) {
            if (arabaX <114) {
                
            }else if (arabaKont) {
                solGit();
                arabadir_XX= true;
            }
        }
        else if (c == KeyEvent.VK_RIGHT) {
            
            if (arabaX > 314) {
                
            }else if (arabaKont){
                sagGit();
            }
        }
        
        // OYUN BAŞLATMA VE YENİ OYUN OLUŞTURMA
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            timer.start();
            oyunbaşla = true;
            
            if (!oyun) {
                oyun = true;
                arabadir_XX = false;
                arabadirXX = false;
                arabaKont = true;
                arabaX = 214;
                yon = 0;
                duman = false;
                
                if (kalanHak == 0) {
                    skor = 0;
                    dusmanKontrol = 3;
                    dusmanKontrol2 = 5;
                    bolum = 1;
                    kalanHak = 3;
                    dusmanHız = 8;
                            
                }
                dusman1 = dusman1Kontrol;
                dusman2 = dusman2Kontrol;
                
                System.out.println("--- Skor ve Bölüm Sıfırlandı ---");
                HaritaOluştur();
                System.out.println("");
                
            }
        }
        
        
        // OYUN DURAKLATMA
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
             
            esc();
            
            if (!oyun) {
                System.out.println("Oyundan Çıkılıyor");
                System.exit(0);
            }
        }
    }

    public boolean isOyun() {
        return oyun;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if (oyun) {
        
            // ARABA SAĞ VE SOL ANİMASYONU
            if (arabadirXX) {
                arabaX+=arabadirX;
                
                if (arabaX == 14) {
                    arabaKont = true;
                    arabadirXX = false;
                    yon = 0;
                    
                }
                if (arabaX == 214) {
                    arabaKont = true;
                    arabadirXX = false;
                    yon = 0;
                    
                }
                if (arabaX >= 400) {
                    arabaKont = true;
                    arabadirXX = false;
                    yon = 0;
                    
                }
            }
            if (arabadir_XX) {
                arabaX-=arabadirX;
                if (arabaX <= 14) {
                    arabaKont = true;
                    arabadir_XX = false;
                    yon = 0;
                    
                }
                if (arabaX == 214) {
                    arabaKont = true;
                    arabadir_XX = false;
                    yon = 0;
                    
                }
                if (arabaX == 414) {
                    arabaKont = true;
                    arabadir_XX = false;
                    yon = 0;
                    
                }
            }
            
            // BİRİM KAYDIRMA KISMI
            serit1Y += seritdirY;
            serit2Y += seritdirY;
            serit3Y += seritdirY;
            serit0Y += seritdirY;

            serit11Y += seritdirY;
            serit22Y += seritdirY;
            serit33Y += seritdirY;
            serit00Y += seritdirY;

            if (serit33Y == 964){
                serit1Y = 0;
                serit2Y = 300;
                serit3Y = 600;
                serit0Y = -300;

                serit11Y = 64;
                serit22Y = 364;
                serit33Y = 664;
                serit00Y = -236;
            }
            
            dusman1 += dusmanHız;
            dusman2 += dusmanHız;
            
            // CARPMA ALGILAMA
            if (new Rectangle(X, dusman1, image2.getWidth()*2, image2.getHeight()*2).intersects(new Rectangle(arabaX, arabaY, image.getWidth()*2, image.getHeight()*2))) {
                    duman = true;
                    oyun = false;
                    kalanHak--;
            }
            if (new Rectangle(XX, dusman1, image2.getWidth()*2, image2.getHeight()*2).intersects(new Rectangle(arabaX, arabaY, image.getWidth()*2, image.getHeight()*2))) {
                    duman = true;
                    oyun = false;
                    kalanHak--;
            }
            if (new Rectangle(X2, dusman2, image2.getWidth()*2, image2.getHeight()*2).intersects(new Rectangle(arabaX, arabaY, image.getWidth()*2, image.getHeight()*2))) {
                    duman = true;
                    oyun = false;
                    kalanHak--;
            }
            if (new Rectangle(XX2, dusman2, image2.getWidth()*2, image2.getHeight()*2).intersects(new Rectangle(arabaX, arabaY, image.getWidth()*2, image.getHeight()*2))) {
                    duman = true;
                    oyun = false;
                    kalanHak--;
                    
            }
        }
        
        repaint();
        
    }
    
    public void sagGit(){
        if (oyun) {
            arabaX += arabadirX;
            arabadirXX = true;
            yon = 1;
            arabaKont = false;
            
        }
    }
    
    public void solGit(){
        if (oyun) {
            arabaX -= arabadirX;
            arabadir_XX = true;
            yon = 2;
            arabaKont = false;
            
        }
    }
    
    public void esc(){
        /*
        if (esc) {
            esc = false;
            seritdirY = 1;
            dusmandirY = 1;
        }else{
            esc = true;
            seritdirY = 0;
            dusmandirY = 0;

        }
        */
    }
    
    public void HaritaOluştur (){
        
        haritaSayisi++;
        
        System.out.println(haritaSayisi+". Harita Oluşturuldu ***");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 54; j++) {
                harita[j][i] = "|";
            }
        }
        
        // DUŞMAN KONUMLARI
        for (int i = 3; i < 50; i+=2) {
            
            int rnd = r.nextInt(6);
            
            switch(rnd){
                case 0 :
                    harita[i][2] = "X";
                    break;
                case 1 :
                    harita[i][1] = "X";
                    break;
                case 2 :
                    harita[i][0] = "X";
                    break;
                case 3 :
                    harita[i][1] = "X";
                    harita[i][2] = "X";
                    break;
                case 4 :
                    harita[i][0] = "X";
                    harita[i][1] = "X";
                    break;
                case 5 :
                    harita[i][0] = "X";
                    harita[i][2] = "X";
                    break;
            }
        }
        
        
        // HARİTA YAZDIRMA
        for (int i = 0; i < 54; i++) {
            for (int j = 0; j < 3; j++) {

                System.out.print(harita[i][j] + " ");
            }
            System.out.println();
            
        }
        
        /*
        0-001
        1-010
        2-100
        3-011
        4-110
        5-101
        */
    }
}    

