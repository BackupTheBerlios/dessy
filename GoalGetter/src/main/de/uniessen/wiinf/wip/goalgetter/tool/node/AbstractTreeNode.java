/*
 * AbstractTreeNode.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool.node
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 * See Readme file for detailed license
 * 
 * $Id: AbstractTreeNode.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.tree.TreeNode;

/**
 * 
 * The abstract superclass for all tree node classes
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public abstract class AbstractTreeNode implements NavigationNode {

    /**
     * Refers to this node's parent node.
     * 
     * @see #getParent()
     */
    private final NavigationNode parent;

    /**
     * Holds the list of this node's child nodes.
     * 
     * @see #getChildAt(int)
     * @see #getChildCount()
     * @see #children()
     */
    private final List children;

    /**
     * Refers to the model that is associated with this node. For example a
     * GoalNode has an associated Goal instance.
     * 
     * @see #getModel()
     */
    private final Object model;

    // Instance Creation ******************************************************

    /**
     * Constructs a tree node for the given parent and model.
     * 
     * @param parent
     *            this node's parent
     * @param model
     *            the domain object associated with this node
     */
    public AbstractTreeNode(NavigationNode parent, Object model) {
        this.parent = parent;
        this.model = model;
        children = new ArrayList();
    }

    // Implementing the TreeNode Interface ************************************

    /**
     * Returns true if the receiver allows children.
     * 
     * @return true if the receiver allows children
     */
    public boolean getAllowsChildren() {
        return true;
    }

    /**
     * Returns the child <code>TreeNode</code> at index
     * <code>childIndex</code>.
     * 
     * @return the child at the specified index
     */
    public TreeNode getChildAt(int childIndex) {
        return (TreeNode) children.get(childIndex);
    }

    /**
     * Returns the number of children <code>TreeNode</code> s the receiver
     * contains.
     * 
     * @return the number of children
     */
    public int getChildCount() {
        return children.size();
    }

    /**
     * Returns the children of the receiver as an <code>Enumeration</code>.
     * 
     * @return the children of the receiver as an <code>Enumeration</code>.
     */
    public Enumeration children() {
        return Collections.enumeration(children);
    }

    /**
     * Returns the index of <code>node</code> in the receivers children. If
     * the receiver does not contain <code>node</code>, -1 will be returned.
     * 
     * @return the index of the given node, or -1 if it is not a child
     */
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    /**
     * Returns the parent <code>TreeNode</code> of the receiver.
     * 
     * @return the parent <code>TreeNode</code> of the receiver.
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * Returns true if the receiver is a leaf.
     * 
     * @return true if the receiver is a leaf.
     */
    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    // Implementing the NavigationNode Interface ******************************

    /**
     * Returns this node's name. Subclasses typically implement this method by
     * returning the model's name or identifier.
     * 
     * @return this node's name
     */
    abstract public String getName();

    /**
     * Returns this node's icon for the given selection state.
     * 
     * @return this node's icon for the given selection state
     */
    public Icon getIcon(boolean selected) {
        return UIManager
                .getIcon(selected ? "Tree.openIcon" : "Tree.closedIcon");
    }

    /**
     * Returns this node's model. Subclasses may provide a type-safe method with
     * a more specific return type that is the model's class. For example, the
     * <code>GoaleNode</code> may implement <code>getGoal</code>.
     * 
     * @return this node's model
     */
    public Object getModel() {
        return model;
    }

    // Misc *******************************************************************

    /**
     * Adds the given child to the list of this node's children.
     * 
     * @param child
     *            the node to add as this node's child
     */
    public void add(NavigationNode child) {
        children.add(child);
    }

    /**
     * Returns a string representation for this node. Currently it prints the
     * class info and node name.
     * 
     * @return a string representation for this node
     */
    public String toString() {
        return super.toString() + ':' + getName();
    }

}