/**
 * 
 */
package org.hyperdata.scute.source.popup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import org.hyperdata.scute.rdf.RdfUtils;

/**
 * @author danny
 *
 * MAY NOT BE NEEDED
 */
public class Prefixes extends DefaultComboBoxModel {

	private Map<String, String> map = RdfUtils.getCommonPrefixes();
	private  List<String> names = new ArrayList<String>();
	
	public Prefixes(){
		names.addAll(map.keySet());
	}
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return names.get(index);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return names.size();
	}

	public String getSelectedValue() {
		return map.get(getSelectedItem());
	}
}
