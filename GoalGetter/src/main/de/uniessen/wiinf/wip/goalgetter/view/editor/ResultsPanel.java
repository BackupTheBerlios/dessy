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
 * $Id: ResultsPanel.java,v 1.5 2004/10/05 10:11:38 moleman Exp $
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
 * Presents the current state of the decision process in a printable summary
 * view. The view contains an overview of the Goals dimensions, the alternatives
 * and a matrix of the actions taken and their respective costs.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.5 $
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
        if (getProject().isFilled()) {
            sBuf.append("<b>"); //$NON-NLS-1$
            sBuf.append("\"<em>"); //$NON-NLS-1$
            sBuf.append(getProject().bestAlternative().getIdentifier());
            sBuf.append("</em>\""); //$NON-NLS-1$
            sBuf.append(" ist die beste Wahl.");
            sBuf.append("</b><br>"); //$NON-NLS-1$
        }

        sBuf
                .append("<h2 style=\"background-color:#" + lightbackgroundColor() + "\">" + ResourceUtils.getString("project.goalContainer.name.text") + "</h2>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        sBuf.append(getGoalTable());
        sBuf
                .append("<h2 style=\"background-color:#" + lightbackgroundColor() + "\">" + ResourceUtils.getString("project.alternativeContainer.name.text") + "</h2>");//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        sBuf.append(getAlternativeTable());
        sBuf
                .append("<h2 style=\"background-color:#" + lightbackgroundColor() + "\">" + ResourceUtils.getString("project.actionContainer.name.text") + "</h2>");//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        sBuf.append(getActionTables());
        return sBuf.toString();
    }

    public PrintableDocument getPrintableDocument() {
        updateView();
        return new PrintableDocument("Auswertung", editor, false);
    }

    /**
     * Returns a light background color for alternate highlighting or as a
     * background for the headers
     * 
     * @return light background color in hex values
     */
    private String lightbackgroundColor() {
        return "cbdaf0";
    }

}