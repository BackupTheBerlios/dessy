/*
 * Created on 05.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.goalgetter.overviewTable;

import java.util.ArrayList;

/**
 * @author Jonas Sprenger
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OverviewTableEntry extends Object{
	
	private String name;
	ArrayList list;
		
	/** Creates a new instance of GeoGraphicObject */
	public OverviewTableEntry(String name,Object value) {
		list = new ArrayList();
		this.name = name;
		list.add(new TableElement(name,value,java.lang.String.class));

	}
	/**
	 * adds a new TableElement to the inner List of this Object
	 * @param name
	 * @param value
	 */
	public void newElement(String name,Object value, Class c) {
		list.add(new TableElement(name,value,c));
	
	}
	public void newElement(TableElement te) {
			list.add(te);
	
		}

	public TableElement getElement(int i) {
		if(i < list.size())
		return (TableElement)list.get(i);
		else return null;
	}
	public TableElement getElementByName(String n) {
		int i = 0;
		while(i < list.size()){
			if(((TableElement)list.get(i)).getName().equals(n))
			{
				return (TableElement)list.get(i);
			}
		else i++;
		}
		 return null;
		}
	public String getName() {
		return name;
	}
	public String [] getColNames(){
		String[] colNames = new String[list.size()];
		for(int i=0;i<list.size();i++)
			colNames[i] = ((TableElement)list.get(i)).getName(); 
		return colNames;
	}
	public Class [] getColTypes(){
			Class[] types = new Class[list.size()];
			for(int i=0;i<list.size();i++)
				types[i] = ((TableElement)list.get(i)).getType(); 
			return types;
		}
	public int  Size(){
				return list.size();
			}
	/**
	 * @author sprenger
	 * 
	 * Klasse dient als Platzhalter für die Zelleneigenschaften des Elementes
	 */
	public class TableElement {
		Object value;
		Class type;
		String name;

		public TableElement(String n, Object v, Class t) {
			this.name = n;
			this.value = v;
			this.type = t;
		}

		public String getName() {
			return name;
		}
		public Object getValue() {
			return value;
		}
		public Class getType() {
			return type;
		}
		public void setName(String n) {
			name = n;
		}
		public void setValue(Object o) {
			value = o;
		}
		public void setType(Class c) {
			type = c;
		}
	}
	
	
}
