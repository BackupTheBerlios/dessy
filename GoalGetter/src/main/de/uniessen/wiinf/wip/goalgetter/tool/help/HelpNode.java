/*
 * HelpNode.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool.help
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
 * $Id: HelpNode.java,v 1.3 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool.help;

import java.net.URL;

import javax.swing.Icon;

import com.jgoodies.uif.util.ResourceUtils;

/**
 * 
 * Instances of this class define help nodes in the dynamic help. A help node is
 * either a chapter or topic, where only topics have an attached URL.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */

public final class HelpNode {

    /**
     * Constant for a chapter item
     */
    public static final int CHAPTER_ITEM = 0;

    /**
     * Constant for a topic item
     */
    public static final int TOPIC_ITEM = 1;

    private static final String BOOK_ICON_ID = "com.jgoodies.help.openBook.icon"; //$NON-NLS-1$

    private static final String TOPIC_ICON_ID = "com.jgoodies.help.topic.icon"; //$NON-NLS-1$

    private static final Icon BOOK_ICON = ResourceUtils.getIcon(BOOK_ICON_ID);

    private static final Icon TOPIC_ICON = ResourceUtils.getIcon(TOPIC_ICON_ID);

    private final String name;

    private final int type;

    private final URL url;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>HelpNode</code> with the given name, type, and URL.
     * 
     * @param name
     *            name of the helpnode to construct
     * @param type
     *            type of the helpnode to construct (either
     *            {@link HelpNode#CHAPTER_ITEM}or {@link HelpNode#TOPIC_ITEM}
     * @param url
     *            url where the contents for this help node are stored
     */
    private HelpNode(String name, int type, URL url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    /**
     * Creates and returns a chapter help node with the given name.
     * 
     * @param name
     *            name for the chapter to create
     * @return chapter help node
     */
    static HelpNode createChapter(String name) {
        return new HelpNode(name, CHAPTER_ITEM, null);
    }

    /**
     * Creates and returns a topic help node with the given name and path.
     * 
     * @param name
     *            name for the topic to create
     * @param path
     *            path for the topic to create
     * @return topic help node
     */
    static HelpNode createTopic(String name, String path) {
        URL url = ResourceUtils.getURL(path);
        return new HelpNode(name, TOPIC_ITEM, url);
    }

    /**
     * Creates and returns the help root node.
     * 
     * @return help root node
     */
    static HelpNode createRoot() {
        return createChapter("root");
    }

    // Accessors **************************************************************

    /**
     * @return <code>true</code> if this node is a chapter, <code>false</code>
     *         otherwise
     */
    public boolean isChapter() {
        return type == CHAPTER_ITEM;
    }

    public String toString() {
        return name;
    }

    /**
     * Answers the <code>HelpNode</code> s URL
     * 
     * @return URL
     */
    public URL getURL() {
        return url;
    }

    /**
     * checks and answers wether the <code>HelpNode</code> s URL matches the
     * given URL
     * 
     * @param aUrl
     *            URL to compare to the HelpNodes URL
     * @return <code>true</code> if the given URL matches the HelpNodes URL,
     *         <code>false</code> otherwise
     */
    boolean matches(URL aUrl) {
        return aUrl.equals(getURL());
    }

    /**
     * Answers the nodes Icon. Takes into consideration wether the node is a
     * chapter or a topic and its selection state
     * 
     * @param sel
     *            selection state. currently not evaluated.
     * @return Icon
     */
    public Icon getIcon(boolean sel) {
        return isChapter() ? BOOK_ICON : TOPIC_ICON;
    }

}