/*
 * ResultsPanel.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: ResultsPanel.java,v 1.3 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import java.awt.Insets;
import java.io.StringWriter;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.printing.HTMLTableWriter;
import com.jgoodies.uifextras.printing.PrintableDocument;
import com.jgoodies.uifextras.util.UIFactory;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Project;
import de.uniessen.wiinf.wip.goalgetter.model.Resources;
import de.uniessen.wiinf.wip.goalgetter.model.tablemodel.ActionContainerTableModel;
import de.uniessen.wiinf.wip.goalgetter.model.tablemodel.AlternativeContainerTableModel;
import de.uniessen.wiinf.wip.goalgetter.model.tablemodel.GoalContainerTableModel;

/**
 * ActionContainerByGoal
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public class ResultsPanel extends AbstractEditor {

    private static final long serialVersionUID = 1L;

    private JEditorPane editor;

    /**
     * Constructs a <code>AlternativeContainerEditor</code>
     */
    public ResultsPanel() {
        super(Resources.ACTION_ICON, "Bericht");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#build()
     */
    protected void build() {

    	
    	FormLayout layout = new FormLayout("0:grow:0.9");//$NON-NLS-1$
        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), this);
        builder.setBorder(Borders.EMPTY_BORDER);
        builder.appendRow(new RowSpec("fill:0dlu:grow")); //$NON-NLS-1$
        // java.awt.Component overviewPane = new JScrollPane(overviewTable);
        builder.add(buildContent());
       
    }

    private JComponent buildContent() {
        editor = new JEditorPane();
        editor.setMargin(new Insets(10, 10, 5, 2));
        editor.setEditable(false);
        editor.setContentType("text/html"); //$NON-NLS-1$
        JScrollPane scrollPane = UIFactory.createStrippedScrollPane(editor);        
        return scrollPane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getDomainClass()
     */
    public Class getDomainClass() {
        return Project.class;
    }

    /**
     * Convenience function for {@link ActionContainerEditor#getModel()}
     * including the correct typecast.
     * 
     * @return ActionContainer
     */
    private Project getProject() {
        return (Project) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getTitleSuffix()
     */
    protected String getTitleSuffix() {
        return ""; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateModel()
     */
    protected void updateModel() {
        //        ActionContainerByGoalTableModel actm = new
        // ActionContainerByGoalTableModel(
        //                getActionContainer().getActionByGoalListModel());
        //        overviewTable.setModel(actm);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateView()
     */
    protected void updateView() {
        editor.setText(getContent());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#activate()
     */
    public void activate() {

        super.activate();
        updateView();
    }

    private String getGoalTable() {
        StringWriter stw = new StringWriter();
        HTMLTableWriter tabWriter = new HTMLTableWriter();
       
        GoalContainerTableModel gctm = new GoalContainerTableModel(getProject()
                .getGoalContainer().getGoals());
        System.out.println(gctm);
        tabWriter.writeTable(stw, gctm);
        return stw.toString();

    }

    private String getAlternativeTable() {
        StringWriter stw = new StringWriter();
        HTMLTableWriter tabWriter = new HTMLTableWriter();

        AlternativeContainerTableModel actm = new AlternativeContainerTableModel(
                getProject().getAlternativeContainer()
                        .getAlternativesListModel());
        tabWriter.writeTable(stw, actm);
        return stw.toString();

    }

    private String getActionTables() {
        StringBuffer stBuf = new StringBuffer();

        HTMLTableWriter tabWriter = new HTMLTableWriter();

        Iterator iterator = getProject().getAlternativeContainer()
                .getAlternatives().iterator();

        while (iterator.hasNext()) {
            StringWriter stw = new StringWriter();
            Alternative anAlternative = (Alternative) iterator.next();
            stBuf.append("<h3>" + anAlternative.getIdentifier() + "</h3>"); //$NON-NLS-1$ //$NON-NLS-2$

            ActionContainerTableModel actm = new ActionContainerTableModel(
                    getProject().getActionContainer().getActionsFor(
                            anAlternative));
            tabWriter.writeTable(stw, actm);

            stBuf.append(stw.toString());
        }      
        return stBuf.toString();
    }

    private String getContent() {
        StringBuffer sBuf = new StringBuffer();
        sBuf.append("<b>Klaus ist die beste Wahl.</b><br>");
        sBuf
                .append("<h2 style=\"background-color:#"+lightbackgroundColor()+"\">" + ResourceUtils.getString("project.goalContainer.name.text") + "</h2>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        sBuf.append(getGoalTable());
        sBuf
                .append("<h2 style=\"background-color:#"+lightbackgroundColor()+"\">" + ResourceUtils.getString("project.alternativeContainer.name.text") + "</h2>");//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        sBuf.append(getAlternativeTable());
        sBuf
                .append("<h2 style=\"background-color:#"+lightbackgroundColor()+"\">" + ResourceUtils.getString("project.actionContainer.name.text") + "</h2>");//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        sBuf.append(getActionTables());
        return sBuf.toString();
    }

    public PrintableDocument getPrintableDocument() {
        updateView();
         return new PrintableDocument("Auswertung", editor, false);
    }

    private String lightbackgroundColor() {
     return "cbdaf0";
    }

}