
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



import icy.gui.frame.progress.AnnounceFrame;
import icy.roi.*;

public class testing {
	
	 static ROIFrames frame = null;
	 ROI roi = null;
	 private static testing instance = null; 
	 
	testing (ROI roi)
	{
		this.roi = roi;
	}
	
	testing() {}
	
	public static testing getInstance()
	{
		if (instance == null)
		{
			instance = new testing();
		}
		return instance;
	}
	
	public void initParam(ROI roi)
	{
		this.roi =  roi;
	}
	
	public void runtesting(){
		
		if (frame!=null)  frame.dispose();  
		
			frame = new ROIFrames(roi);
		  
		
		
	}
	
}
