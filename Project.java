import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.BasicStroke;
import java.awt.Graphics2D;


public class Project extends Applet{
	
	private Button btnRect;
	private Button btnOval;
	private Button btnLine;
	private Button btnBrush;
	private Button btnEraser;
	private Button btnClearAll;
	private Button btnUndo;
	private Button btnRedo;
	private Button btnRed;
	private Button btnGreen;
	private Button btnBlue;
	private Checkbox checksolid;
	public int startx, starty, endx, endy , width, height, cursorx, cursory;
	public String mode = "Brush";
	public boolean solid = false;
	public static Color color = Color.black;

		// creating and initializing the arraylists
	static ArrayList<Shapes> arr= new ArrayList<Shapes>();
	static ArrayList<Shapes> redo= new ArrayList<Shapes>();
	
	public void init(){
		
		// initializing buttons and checkboxes
	btnRect = new Button("Rectangle");
	btnBrush = new Button("Brush");
	btnOval = new Button("Oval");
	btnLine = new Button("Line");
	btnEraser = new Button("Eraser");
	btnClearAll = new Button("Clear All");
	btnUndo = new Button("Undo");
	btnRedo = new Button("Redo");
	btnRed = new Button("Red");
	btnGreen = new Button("Green");
	btnBlue = new Button("Blue");
	checksolid = new Checkbox("Solid");

		// adding action listeners for buttons and checkboxes
	btnRed.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				color = Color.red;
			}
		});
	btnGreen.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				color = Color.green;
			}
		});
	btnBlue.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				color = Color.blue;
			}
		});
			
	btnRect.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				mode = "Rectangle";
			}
		});	
		
	btnOval.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				mode = "Oval";
			}
		});	
		
	btnLine.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				mode = "Line";
			}
		});	
		
	btnBrush.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				mode = "Brush";
			}
		});	
		
	btnEraser.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				mode = "Eraser";
			}
		});	
		
	btnClearAll.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				mode = "Clearall";
				redo.clear();
				for (int i =0; i < arr.size(); i++){
					redo.add(arr.get(i));
				}
				arr.clear();
				repaint();
			}
		});	
		
	btnUndo.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				if (mode == "Clearall" && redo.size() > 0){
					for (int i =0; i< redo.size(); i++){
						arr.add(redo.get(i));
					}
					redo.clear();
					repaint();
				}
				else if (arr.size() > 0){
				mode = "Undo";
				int undo = arr.size()-1;
				redo.add(arr.get(undo));
				arr.remove(undo);
				repaint();
				}
			}
		});
		
	btnRedo.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				 if (redo.size() > 0){
				mode = "Redo";
				int re = redo.size()-1;
				arr.add(redo.get(re));
				redo.remove(re);
				repaint();
				}
			}
		});
	
	checksolid.addItemListener(new ItemListener() { 
            public void itemStateChanged(ItemEvent e){ 
                if (checksolid.getState()) { 
                    solid = true; 
                } 
                else { 
                    solid = false; 
                } 
            } 
        });
		
		// adding buttons, checkboxes and listeners to the applet
		add(btnRect);
		add(btnOval);
		add(btnLine);
		add(btnBrush);
		add(btnEraser);
		add(btnClearAll);
		add(btnUndo);
		add(btnRedo);
		add(btnRed);
		add(btnGreen);
		add(btnBlue);
		add(checksolid);
		
		MyMouseListener mouseListener = new MyMouseListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}
	
	public void paint(Graphics g){
			// drawing the shapes in the arraylist
		for(Shapes s1 : arr){
			s1.draw(g);
			}
			
				// drawing the shape that is being currently drawed
			switch (mode) {
				case "Rectangle":
						(new Rectangle(Math.min(startx, endx), Math.min(starty, endy), 
						Math.abs(endx - startx), Math.abs(endy - starty), solid, color)).draw(g);
				break;
				
				case "Oval":
						(new Oval(Math.min(startx, endx), Math.min(starty, endy), 
						Math.abs(endx - startx), Math.abs(endy - starty), solid, color)).draw(g);
				break;
				
				case "Line":
						(new Line(startx, starty, endx, endy, solid, color)).draw(g);
				break;
				
				case "Brush":
						(new Brush(startx, starty, 
						endx, endy, solid, color)).draw(g);
				break;
				
				case "Eraser":
						(new Cursor(cursorx, cursory, 
						25, 25, solid, color)).draw(g);	
						(new Eraser(startx, starty, 
						25, 25, solid, Color.white)).draw(g);

				break;

				}
	}
		// class for handeling the mouselisteners
	class MyMouseListener extends MouseAdapter{
		Shapes sh = null;
			public void mouseMoved(MouseEvent e){
				switch (mode) {
						// to show the eraser shape whenever you choose it and move the mouse cursor
					case "Eraser":
					cursorx = e.getX();
					cursory = e.getY();
						if (color == Color.black){ 
							color = Color.red;
							sh = new Cursor(cursorx, cursory, 25, 25, solid, color);
							color = Color.black;
							repaint();
							}
						else {
							sh = new Cursor(cursorx, cursory, 25, 25, solid, color);
							repaint();
							}
					}
			}
			public void mouseDragged(MouseEvent e){
				switch (mode) {
					case "Brush":
						endx = e.getX();
						endy = e.getY();
						arr.add(new Brush(startx, starty,
						endx, endy, solid, color));
						startx = endx;
						starty = endy;
					break;				
				
					case "Eraser":
						startx = e.getX();
						starty = e.getY();
						arr.add(new Eraser(startx, starty,
						25, 25, solid, Color.white));
					break;
					}
				endx = e.getX();
				endy = e.getY();
				repaint();
			}
			
			public void mousePressed(MouseEvent e){
				startx = e.getX();
				starty = e.getY();
				
					/* reassign the new values of x and y to the endx and endy
					to prevent them from being the same coordinates used in the last shape*/
				endx = e.getX();
				endy = e.getY();
				repaint();
				}

			public void mouseReleased(MouseEvent e) {
				endx = e.getX();
				endy = e.getY();

				Shapes sh = null;

				switch (mode) {
					case "Rectangle":
						sh = new Rectangle(Math.min(startx, endx), Math.min(starty, endy), 
						Math.abs(endx - startx), Math.abs(endy - starty), solid, color);
						arr.add(sh);
						repaint();
						break;
						
					case "Oval":
						sh = new Oval(Math.min(startx, endx), Math.min(starty, endy), 
						Math.abs(endx - startx), Math.abs(endy - starty), solid, color);
						arr.add(sh);
						repaint();
						break;
						
					case "Line":
						sh= new Line(startx, starty, endx, endy, solid, color);
						arr.add(sh);
						repaint();
						break;

				}
			}
	}
}
		// abstract class Shapes with abstract method draw
	abstract class Shapes{
			private int x1, y1, x2, y2;
			protected boolean s = false;
			private Color c;
			
			public void setX1(int x){
				x1 = x;
			}	
			public void setY1(int y){
				y1 = y;
			}	
			public void setX2(int x){
				x2 = x;
			}	
			public void setY2(int y){
				y2 = y;
			}
			public void setC(Color c){
				c = c;
			}	
			public int getX1(){
				return x1;
			}	
			public int getY1(){
				return y1;
			}	
			public int getX2(){
				return x2;
			}	
			public int getY2(){
				return y2;
			}
			public Color getC(){
				return c;
			}
			public Shapes(int x1, int y1, int x2, int y2, boolean s, Color c){
				this.x1= x1;
				this.y1=y1;
				this.x2=x2;
				this.y2=y2;
				this.s=s;
				this.c = c;
			}
			public abstract void draw(Graphics g);		
	} 
		// concrete classes inherites from the abstract class Shapes
	class Rectangle extends Shapes{
		public Rectangle(int x1, int y1, int x2, int y2, boolean s, Color c){
			super(x1, y1, x2, y2, s, c);
		}
		public void draw(Graphics g){
			if (s){
				g.setColor(this.getC());
				g.fillRect(this.getX1(), this.getY1(), this.getX2(), this.getY2());}
			else {
				g.setColor(this.getC());
				g.drawRect(this.getX1(), this.getY1(), this.getX2(), this.getY2());}
			}
		}	
		
	class Oval extends Shapes{
		public Oval(int x1, int y1, int x2, int y2, boolean s, Color c){
			super(x1, y1, x2, y2, s, c);
		}
		public void draw(Graphics g){
			if (s){
				g.setColor(this.getC());
				g.fillOval(this.getX1(), this.getY1(), this.getX2(), this.getY2());}
			else {
				g.setColor(this.getC());
				g.drawOval(this.getX1(), this.getY1(), this.getX2(), this.getY2());}
			}
	}	
		
	class Line extends Shapes{
		public Line(int x1, int y1, int x2, int y2, boolean s, Color c){
			super(x1, y1, x2, y2, s, c);
		}
		
		public void draw(Graphics g){
			g.setColor(this.getC());
			g.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());}
	}		
		
	class Brush extends Shapes{
		public Brush(int x1, int y1, int x2, int y2, boolean s, Color c){
			super(x1, y1, x2, y2, s, c);
		}
		
		public void draw(Graphics g){
				// making a thick stroke to the brush
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(2));
			g2.setColor(this.getC());
			g2.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2() );}	
	}
	
	class Eraser extends Shapes{
		public Eraser(int x1, int y1, int x2, int y2, boolean s, Color c){
			super(x1, y1, x2, y2, s, c);
		}
		
		public void draw(Graphics g){
			g.setColor(this.getC());
			g.fillOval(this.getX1(), this.getY1(), this.getX2(), this.getY2() );}	
	}	
	
	class Cursor extends Shapes{
		public Cursor(int x1, int y1, int x2, int y2, boolean s, Color c){
			super(x1, y1, x2, y2, s, c);
		}
		
		public void draw(Graphics g){
			g.setColor(this.getC());
			g.drawOval(this.getX1(), this.getY1(), this.getX2(), this.getY2() );}	
	}
		

// Sherif Ashraf..