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


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import icy.gui.frame.progress.AnnounceFrame;
import icy.roi.ROI;
import net.miginfocom.swing.MigLayout;



public class ROIFrames extends JFrame  implements ActionListener { 

	/**
	 * 
	 */


	private static final long serialVersionUID = 1L;
	ROI roi = null;
	private JPanel contentPane;
	private JTextField textfield;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ROIFrames(ROI renameroi) {
		super("Select a ROI or create a new one");
		this.roi = renameroi;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("ROI Name here!!");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][][][grow]", "[][][]"));

		textfield = new JTextField();
		contentPane.add(textfield, "cell 2 1,growx");
		textfield.setColumns(10);
		textfield.setText(roi.getName());
		textfield.setEditable(true);
		this.setVisible(true);
		
		JComboBox<String> box = new JComboBox<String>();
		box.setEditable(true);
		AutoCompletion.enable(box);
		contentPane.add(box, "cell 2 2,growx");

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int x = (int) rect.getMaxX() - getWidth();
		int y = 0;
		setLocation(x, y);


		ArrayList<String> namearray = null;

		try {

			//URL url = getClass().getResource("C:/Users/SudipBhandari/Aindra/ROIhelper/src/plugins/sudip/roihelper/ROInames.txt");
			//File file = new File("C:/Users/SudipBhandari/Aindra/ROIhelper/src/plugins/sudip/roihelper/ROInames.txt");
			//File file = new File ("/home/hari/Downloads/icy/ROInames.txt");
			File file = new File("E:/ROInames.txt");
			InputStream namelist = new FileInputStream(file);
			InputStreamReader is = new InputStreamReader(namelist);
			BufferedReader br = new BufferedReader(is);

			namearray = new ArrayList<String>();
			namearray.add("dummy");

			int index=0;

			while (true)
			{
				String temp = br.readLine();
				//new AnnounceFrame("reading file");
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
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// TODO Auto-generated method stub

				String s = textfield.getText();


				//new AnnounceFrame("name changing");
				if (!s.isEmpty())
					roi.setName(s);
				roi.setSelected(false);  //unselect roi to avoid another trigger for same unselection
				//new AnnounceFrame("name changed");
               
				//append s to the ROInames.txt file

				try {
					//Files.write(Paths.get("C:/Users/SudipBhandari/Aindra/ROIhelper/src/plugins/sudip/roihelper/ROInames.txt"), (s+"\n").getBytes(), StandardOpenOption.APPEND);
					Files.write(Paths.get("E:/ROInames.txt"), (s+"\n").getBytes(), StandardOpenOption.APPEND);

					
				}catch (IOException enew) {
					//exception handling left as an exercise for the reader
					enew.printStackTrace();
				}

				System.out.println("item added to the list successfully");

				//reloading contents
				ArrayList<String> namearrayone = null;

				try {
					//URL url = getClass().getResource("C:/Users/SudipBhandari/Aindra/ROIhelper/src/plugins/sudip/roihelper/ROInames.txt");
					//File file = new File("/home/hari/Downloads/icy/ROInames.txt");
					//File file = new File("C:/Users/SudipBhandari/Aindra/ROIhelper/src/plugins/sudip/roihelper/ROInames.txt");
					File file = new File("E:/ROInames.txt");
					InputStream namelist = new FileInputStream(file);
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



				(testing.frame).dispose();
			
			}
			 
		});
		contentPane.add(btnSubmit, "cell 4 1");


		

		Iterator<String> it = namearray.iterator();
		while (it.hasNext())
		{
			String tempo = (String) it.next();
			box.addItem(tempo);
		}

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}



}




















