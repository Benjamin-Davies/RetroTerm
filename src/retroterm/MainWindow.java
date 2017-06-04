package retroterm;

import java.awt.*;
import java.awt.image.*;
import javax.swing.JFrame;

public class MainWindow {

  public static void main(String[] args) {
    MainWindow mw = new MainWindow();
    mw.run();
  }

  private JFrame frame;
  private Canvas canvas;
  private BufferedImage texture;
  private BufferStrategy bs;
  private Graphics g;

  private String title = "RetroTerm";
  private int width = 640, height = 480;

  private int cursorX = 0, cursorY = 0;
  private char[][] characters;

  public MainWindow() {
    initWindow();
    characters = new char[40][30];
  }

  private void initWindow() {
    frame = new JFrame(title);
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height));
    canvas.setMinimumSize(new Dimension(width, height));
    canvas.setMaximumSize(new Dimension(width, height));
    canvas.setBackground(Color.black);
    frame.add(canvas);
    
    frame.pack();
  }

  public void run() {
    frame.setVisible(true);
    
    //texture = util.ImageLoader.loadImage("res/sprite.png")

    bs = canvas.getBufferStrategy();
    if (bs == null)
      canvas.createBufferStrategy(2);

    println("aA");

    while (true) {
      bs = canvas.getBufferStrategy();
      g = bs.getDrawGraphics();

      g.setColor(Color.white);

      for (int i = 0; i < characters.length; i++) {
        char[] column = characters[i];
        for (int j = 0; j < column.length; j++) {
          char c = column[j];
          if (c != 0)
            g.drawString(String.valueOf(c), i * 16, j * 16 + 16);
        }
      }

      bs.show();
      g.dispose();
    }
  }

  public void print(char c) {
    if (c == '\r') {
      cursorX = 0;
      return;
    }
    if (c == '\n') {
      cursorY++;
      return;
    }

    try {
      characters[cursorX][cursorY] = c;
    } catch (ArrayIndexOutOfBoundsException e) {
      return;
    }

    cursorX++;

    System.out.println((int)c);
    
  }
  public void print(String s) {
    for (int i = 0; i < s.length(); i++)
      print(s.charAt(i));
  }
  public void print(Object o) {
    print(o.toString());
  }
  
  public void println(String s) {
    print(s + "\r\n");
  }
  public void println(Object o) {
    println(o.toString());
  }

}

