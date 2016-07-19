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



import icy.gui.dialog.MessageDialog;
import icy.gui.frame.progress.AnnounceFrame;
import icy.gui.frame.progress.ToolTipFrame;
import icy.gui.main.GlobalROIListener;
import icy.gui.plugin.PluginRichToolTip;
import icy.image.IcyBufferedImage;
import icy.plugin.PluginDescriptor;
import icy.plugin.abstract_.PluginActionable;
import icy.roi.ROI;
import icy.roi.ROIEvent;
import icy.roi.ROIListener;
import icy.roi.ROIEvent.ROIEventType;
import icy.sequence.Sequence;
import icy.sequence.SequenceEvent;
import icy.sequence.SequenceListener;
import icy.roi.*;

import icy.roi.ROI2D;

import java.awt.Color;
import java.awt.Event;
import java.io.IOException;
import java.util.*;

public class MyPlugin extends PluginActionable  implements ROIListener, SequenceListener {
	
	
	
	
	Sequence sequence = null;
	int alternator = 1;
	@Override
	public void run() {
		
		sequence = getActiveSequence();
		
		List<ROI> roi = sequence.getROIs();
		
		
		
		
//		torename.addListener( new ROIListener(){
//			
//			public void roiChanged(ROIEvent event)
//			{
//				testing obj1 = new testing(torename);
//				obj1.runtesting();
//			}
//		});
		
		
		Iterator<ROI> itr = roi.iterator();
		
		
		
		while (itr.hasNext())
		{
			ROI temp=(ROI)itr.next();
			temp.addListener (this);
	
	}

	
}

	@Override
	public void roiChanged(ROIEvent eventt) {
		// TODO Auto-generated method stub
		ROI changedroi = eventt.getSource();
	    ROIEvent.ROIEventType event =  eventt.getType();
	      //if (testing.frame!=null)  (testing.frame).dispose();
	      if (event == ROIEvent.ROIEventType.PROPERTY_CHANGED) {}
	      else if ( event == ROIEventType.SELECTION_CHANGED )
		 {
		if (changedroi.isSelected()){
	    testing obj =  testing.getInstance();
		obj.initParam(changedroi);
		
		
	    obj.runtesting();}
		 }
		
	}

	@Override
	public void sequenceChanged(SequenceEvent sequenceEvent) {
		// TODO Auto-generated method stub
		
//		new AnnounceFrame("sequence event triggered");
//		
//		Sequence seq = sequenceEvent.getSequence();
//		
//		new AnnounceFrame("Loaded images in sequence=="+seq.getNumImage());
//		//if (seq!=null) new AnnounceFrame("sequence is not null");
//		
//		ROI torename = seq.getFocusedROI();
//		
//		if (torename==null) new AnnounceFrame("Still roi is null ");
//		
//		testing obj1 = new testing(torename);
//		
//		
//		obj1.runtesting();
		
	}
	
	

	@Override
	public void sequenceClosed(Sequence sequence) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws IOException {
        Process process = new ProcessBuilder("E:\\software\\Icy\\icy.exe").start();
    }
	
	
	
	
	
}

