/*
 * Created on 14.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

/**
 * All Elements handled in @see de.uniessen.wiinf.wip.goalgetter.view.sensitivity.SensitivityAnalysisChart
 * 
 * @author Jonas Sprenger
 */
public class SensitivityElements {
	
	/**
	 * Comment for <code>values</code>
	 * saves all given values and changes.
	 */
	Map values;
	
	/**
	 * Comment for <code>orginals</code>
	 * just for the orgiginal values. this value cant be changed
	 */
	Map orginals;
	
	String name;
		
	
	/**Constructor
	 * @param name name of the sensitivityElement
	 */
	public SensitivityElements(String name) {
		super();
		this.name = name;
		values = new HashMap();
		orginals = new HashMap();
		
	}
	/**
	 * @param name
	 * @param value
	 * add Values to the value Map
	 */
	public void addValues(String name,Object value){
		values.put(name,value);	
		orginals.put(name,value);
	}
	
	
	
	/**
	 * @return Returns the values.
	 */
	public Map getValues() {
		return values;
	}
	/**
	 * @return Returns the values.
	 */
	public Map getOrginalValues() {
		return orginals;
	}
	/**
	 * @param values The values to set.
	 */
	public void setValues(Map values) {
		this.values = values;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 */
	public void reset() {
		values.clear();
		values.putAll(orginals);
		
	}
}

