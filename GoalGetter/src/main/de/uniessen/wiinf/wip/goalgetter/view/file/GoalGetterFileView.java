/*
 * GoalGetterFileView.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.file
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: GoalGetterFileView.java,v 1.2 2004/08/16 11:25:22 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.file;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.io.File;
import java.util.Hashtable;

/**
 * A convenience implementation of the FileView interface that manages name,
 * icon, traversable, and file type information.
 * 
 * This this implemention will work well with file systems that use "dot"
 * extensions to indicate file type. For example: "picture.gif" as a gif image.
 * 
 * If the java.io.File ever contains some of this information, such as file
 * type, icon, and hidden file inforation, this implementation may become
 * obsolete. At minimum, it should be rewritten at that time to use any new type
 * information provided by java.io.File
 * 
 * Example: JFileChooser chooser = new JFileChooser(); fileView = new
 * ExampleFileView(); fileView.putIcon("jpg", new
 * ImageIcon("images/jpgIcon.jpg")); fileView.putIcon("gif", new
 * ImageIcon("images/gifIcon.gif")); chooser.setFileView(fileView);
 * 
 * @version 1.13 01/23/03
 * @author Jeff Dinkins
 */
public class GoalGetterFileView extends FileView {
    private Hashtable icons = new Hashtable(5);

    private Hashtable fileDescriptions = new Hashtable(5);

    private Hashtable typeDescriptions = new Hashtable(5);

    /**
     * The name of the file. Do nothing special here. Let the system file view
     * handle this.
     * 
     * @see FileView#getName
     */
    public String getName(File f) {
        return null;
    }

    /**
     * Adds a human readable description of the file.
     * 
     * @param f
     *            file to set description for
     * @param fileDescription
     *            description to set
     */
    public void putDescription(File f, String fileDescription) {
        fileDescriptions.put(fileDescription, f);
    }

    /**
     * A human readable description of the file.
     * 
     * @see FileView#getDescription
     */
    public String getDescription(File f) {
        return (String) fileDescriptions.get(f);
    }

    /**
     * Adds a human readable type description for files. Based on "dot"
     * extension strings, e.g: ".gif". Case is ignored.
     * 
     * @param extension
     *            extension to set description for
     * @param typeDescription
     *            description to set
     */
    public void putTypeDescription(String extension, String typeDescription) {
        typeDescriptions.put(typeDescription, extension);
    }

    /**
     * Adds a human readable type description for files of the type of the
     * passed in file. Based on "dot" extension strings, e.g: ".gif". Case is
     * ignored.
     * 
     * @param f
     *            file to set description for
     * @param typeDescription
     *            description to set
     */
    public void putTypeDescription(File f, String typeDescription) {
        putTypeDescription(getExtension(f), typeDescription);
    }

    /**
     * A human readable description of the type of the file.
     * 
     * @see FileView#getTypeDescription
     */
    public String getTypeDescription(File f) {
        return (String) typeDescriptions.get(getExtension(f));
    }

    /**
     * Convenience method that returnsa the "dot" extension for the given file.
     * 
     * @param f
     *            file to get the extension for
     * @return extension for the given file
     */
    public String getExtension(File f) {
        String name = f.getName();
        if (name != null) {
            int extensionIndex = name.lastIndexOf('.');
            if (extensionIndex < 0) {
                return null;
            }
            return name.substring(extensionIndex + 1).toLowerCase();
        }
        return null;
    }

    /**
     * Adds an icon based on the file type "dot" extension string, e.g: ".gif".
     * Case is ignored.
     * 
     * @param extension
     *            extension to add the icon for
     * @param icon
     *            icon to set for the file extension
     */
    public void putIcon(String extension, Icon icon) {
        icons.put(extension, icon);
    }

    /**
     * Icon that reperesents this file. Default implementation returns null. You
     * might want to override this to return something more interesting.
     * 
     * @see FileView#getIcon
     */
    public Icon getIcon(File f) {
        Icon icon = null;
        String extension = getExtension(f);
        if (extension != null) {
            icon = (Icon) icons.get(extension);
        }
        return icon;
    }

    /**
     * Whether the file is hidden or not. This implementation returns true if
     * the filename starts with a "."
     * 
     * @param f
     *            file to check hidden status for
     * @return Whether the file is hidden or not
     */
    public Boolean isHidden(File f) {
        String name = f.getName();
        if (name != null && !name.equals("") && name.charAt(0) == '.') { //$NON-NLS-1$
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Whether the directory is traversable or not. Generic implementation
     * returns true for all directories and special folders.
     * 
     * You might want to subtype ExampleFileView to do somethimg more
     * interesting, such as recognize compound documents directories; in such a
     * case you might return a special icon for the diretory that makes it look
     * like a regular document, and return false for isTraversable to not allow
     * users to descend into the directory.
     * 
     * @see FileView#isTraversable
     */
    public Boolean isTraversable(File f) {
        // if (some_reason) {
        //    return Boolean.FALSE;
        // }
        return null; // Use default from FileSystemView
    }

}

