/*
 * Created on 14.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonas Sprenger
 */
public class SensitivityElements {
	
	Map values,orginals;
	String name;
	
	
	/**
	 * @param costs
	 * @param name
	 * @param goal
	 */
	public SensitivityElements(String name) {
		super();
		this.name = name;
		values = new HashMap();
		orginals = new HashMap();
		
	}
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
	
}
