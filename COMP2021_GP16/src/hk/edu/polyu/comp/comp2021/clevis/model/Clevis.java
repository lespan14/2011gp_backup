	private static int GroupCounter = 0;
	/*
	public JFrame frame;
	public Canvas canvas;
	private String title;
	private int width;
	private int height;
	public Clevis(){
    	this.frame = new JFrame();	
    	this.canvas = new Canvas();
    	this.width = 500;
    	this.height = 800;
    	this.title = "Testing";
    	init();
	}
	
    public Clevis(String title, int width, int height){
    	this.frame = new JFrame();	
    	this.canvas = new Canvas();
    	this.width = width;
    	this.height = height;
    	this.title = title;
    	init();
    	}
    
    public void init() {
    	this.frame.setTitle(title);
    	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.frame.setSize(width,height);
    	this.frame.setLocationRelativeTo(null);
    	this.frame.setResizable(false);
    	this.frame.setVisible(true);
    	
    	//JPanel pan = new JPanel();
    	//pan.setLayout(new FlowLayout(FlowLayout.CENTER, 200,200));
    	//pan.setBackground(Color.BLACK);
    	//this.frame.add(pan,BorderLayout.NORTH);
    	this.canvas.setBackground(Color.WHITE);
    	this.canvas.setPreferredSize(new Dimension(width,height));
    	this.canvas.setMaximumSize(new Dimension(width,height));
    	this.canvas.setMinimumSize(new Dimension(width,height));
    	this.frame.add(canvas);
    	this.frame.pack();
    }
    */
	
	public static class NameShape {
            private String name;
            private Shape shape;
            private Boolean grouped = false;
            private int groupID;
            public NameShape (String name, Shape shape){
            this.name=name;
            this.shape=shape;
        }
        public String getName(){return name;}
        public Shape getShape(){return shape;}
        
        public void grouped(int ID){
        	grouped = true;
        	this.groupID = ID;}
        
        public void ungrouped(){grouped = false;}
    }
	
	public static class Groups {
        private String name;
        private int groupID;
        private List<NameShape> shapes= new ArrayList<>();
        public Groups(String name, int groupID){
        	this.name = name;
        	this.groupID = groupID;
        }
        
    public void add(NameShape adder) {
        this.shapes.add(adder);
    }
    public void remove(NameShape adder) {
    	this.shapes.remove(adder);
    }
    public String getName() {
    	return this.name;
    }
    public int getID() {
    	return this.groupID;
    }
	
}
	
	
	public List<Groups> groups = new ArrayList<>();
	public List<NameShape> shapes= new ArrayList<>();

	public void rectangle (String n, double x, double y, double w, double h){
        	// g.drawRect(x, y, w, h);
		shapes.add(new NameShape(n, new Rectangle2D.Double(x, y, w, h)));
    	}

	public void circle (String n, double x, double y, double r){
        	// g.drawOval(x, y, r, r);
		shapes.add(new NameShape(n, new Ellipse2D.Double(x, y, r, r)));
    	}
    
	public void square (String n, double x, double y, double l){
       	 	// g.drawRect(x, y, l, l);
		shapes.add(new NameShape(n, new Rectangle2D.Double(x, y, l, l)));
   	}

	public void line (String n, double x1, double y1, double x2, double y2){
        	// g.drawLine(x1, y1, x2, y2);
		shapes.add(new NameShape(n, new Line2D.Double(x1, y1, x2, y2)));
    	}
	
	public void group (String n, String[] nList) {
		Groups temp = new Groups(n, GroupCounter);
		for (String i : nList) {
			for (int j = 0; j< shapes.size(); j++) {
				if (shapes.get(j).getName() == i) {
					temp.add(shapes.get(j));
					shapes.get(j).grouped(GroupCounter);
					break;
				}
			}
		}
		groups.add(temp);
		GroupCounter++;
	}
	
	public void ungroup (String n) {
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).getName() == n) {
				Groups temp = groups.get(i);
				for (int y =0; y < temp.shapes.size(); y++) {
					temp.shapes.get(y).ungrouped();
				}
				groups.remove(i);
				break;
			}
		}
	}
	

	/*
	//// Class used to define the shapes to be drawn
	public void paint(Graphics g){

        	ArrayList<Color> shapeStroke = new ArrayList<Color>();
        	Graphics2D graphSettings = (Graphics2D) g;
        	// Defines the line width of the stroke
        	graphSettings.setStroke(new BasicStroke(2));
        	// Iterators created to cycle through strokes and fills
        	Iterator<Color> strokeCounter = shapeStroke.iterator();
        	shapeStroke.add(Color.GREEN);
		
        	for (NameShape s: shapes){
            	graphSettings.setPaint(strokeCounter.next());
            	graphSettings.draw(s.getShape());
        	}
    	

	}
	*/
