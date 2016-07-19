/*This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package plugins.sudip.roihelper;



import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import icy.gui.frame.progress.AnnounceFrame;
import icy.roi.ROI;





public class ROIFrame  extends JFrame implements ActionListener  {
    
    
	/**
	 * 
	 */
	
	
	
	ROI roi = null;
	
	
	//try singleton for roiframe
	
//	ROIFrame() {}
//	
//	private static ROIFrame instance = null;
//	public static ROIFrame getInstance()
//	{
//		if (instance == null)
//			instance = new ROIFrame();
//		return instance;
//	}
//	
//	public void initParam(ROI roi)
//	{
//		this.roi = roi;
//	}
	// end of singleton for roiframe
	
	private static final long serialVersionUID = -3446073542294365460L;

	/**
	 * have to call this frame once the event is triggered for the ROI
	 */
	ROIFrame(ROI renameroi){
		
		super("Select or define the given ROI: ");
		
		this.roi = renameroi;
		
		
        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        Dimension frameSize = new Dimension();
       
        
        frameSize.width = 500;
        frameSize.height = 500;
       
        setSize(new Dimension(278, 296));
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - getWidth();
        int y = 0;
        setLocation(x, y);
        
        
        
        setResizable(true);
        setVisible(true);
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        Dimension windowSize = new Dimension(getPreferredSize());
        
        
        
        
        
        
        ArrayList<String> namearray = null;
        
       
        
        	
        try {
        	
        InputStream namelist = new FileInputStream("C:/Users/SudipBhandari/Aindra/MyPlugin/src/plugins/sudip/myplugin/ROInames.txt");
        InputStreamReader is = new InputStreamReader(namelist);
        BufferedReader br = new BufferedReader(is);
        
        namearray = new ArrayList<String>();
        namearray.add("dummy");
        
        int index=0;
        
        while (true)
        {
        	String temp = br.readLine();
        	if (temp==null) break;
        	if (!namearray.contains(temp))
        	namearray.add(temp);
        	index++;
        }
        
        br.close();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        
        
        Collections.sort(namearray);
        JComboBox<String> box = new JComboBox<>();
        
       
        
        Iterator<String> it = namearray.iterator();
        while (it.hasNext())
        {
        	String tempo = (String) it.next();
        	box.addItem(tempo);
        }
        
        AutoCompletion.enable(box);   //autocompletion enabled
        box.setEditable(true);
        
       
        JTextField textfield =  new JTextField();
        textfield.setVisible(true);
        
        this.setVisible(true);
        box.setVisible(true);
        
        box.addActionListener(new ActionListener(){
        	
        	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JComboBox jc = (JComboBox) arg0.getSource();
        		Object selected = jc.getSelectedItem();
        		String selectedname = (String) selected;
				textfield.setText(selectedname);
			}
        });
             
        
        
        
        
        Dimension dim = new Dimension(100,100);
        textfield.setPreferredSize(dim);
        
        JButton button =  new JButton("submit");
        
        
        
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String s = textfield.getText();
				
				
				new AnnounceFrame("name changing");
			    if (!s.isEmpty())
				roi.setName(s);
			    roi.setSelected(false);  //unselect roi to avoid another trigger for same unselection
				new AnnounceFrame("name changed");
				
					//append s to the ROInames.txt file
					
					try {
					    Files.write(Paths.get("C:/Users/SudipBhandari/Aindra/MyPlugin/src/plugins/sudip/myplugin/ROInames.txt"), (s+"\n").getBytes(), StandardOpenOption.APPEND);
					    
					}catch (IOException enew) {
					    //exception handling left as an exercise for the reader
						enew.printStackTrace();
					}
					
					System.out.println("item added to the list successfully");
					
					//reloading contents
					ArrayList<String> namearrayone = null;
					
					 try {
				        	
					        InputStream namelist = new FileInputStream("C:/Users/SudipBhandari/Aindra/MyPlugin/src/plugins/sudip/myplugin/ROInames.txt");
					        InputStreamReader is = new InputStreamReader(namelist);
					        BufferedReader br = new BufferedReader(is);
					        
					        namearrayone = new ArrayList<String>();
					        
					        
					        int index=0;
					        
					        while (true)
					        {
					        	String temp = br.readLine();
					        	if (temp==null) break;
					        	if (!namearrayone.contains(temp))
					        	namearrayone.add(temp);
					        	index++;
					        }
					        Collections.sort(namearrayone);
					         br.close(); 
					        }
					        catch (Exception ex)
					        {
					        	ex.printStackTrace();
					        }
					        
					        
					        
					       
					        
					        Iterator<String> it = namearrayone.iterator();
					        while (it.hasNext())
					        {
					        	String tempo = (String) it.next();
					        	box.addItem(tempo);
					        }
					       
					
					//finished reloading contents
					
				//close the frame
				       
					        
					       
				
			}
			
		
		});
        
        
        
        getContentPane().add(box);
        getContentPane().add(textfield);
        getContentPane().add(button);
        
        
        
        
        
    }
//    
//        public static void main(String args[]){
//            new MarksFrame();
//    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
