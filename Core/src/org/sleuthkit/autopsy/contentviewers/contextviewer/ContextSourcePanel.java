/*
 * Autopsy Forensic Browser
 *
 * Copyright 2020 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.contentviewers.contextviewer;

import java.util.ArrayList;
import java.util.List;
import org.sleuthkit.autopsy.contentviewers.contextviewer.ContextViewer.DateTimePanel;
import org.sleuthkit.autopsy.contentviewers.layout.ContentViewerDefaults;
import org.sleuthkit.autopsy.directorytree.DirectoryTreeTopComponent;
import org.sleuthkit.datamodel.BlackboardArtifact;
import static org.sleuthkit.datamodel.BlackboardArtifact.ARTIFACT_TYPE.TSK_ASSOCIATED_OBJECT;

/**
 * Displays additional context for the selected file, such as its source, and
 * usage, if known.
 *
 */
public final class ContextSourcePanel extends javax.swing.JPanel implements DateTimePanel {

    private static final long serialVersionUID = 1L;

    // defines a list of artifacts that provide context for a file
    private static final List<BlackboardArtifact.ARTIFACT_TYPE> SOURCE_CONTEXT_ARTIFACTS = new ArrayList<>();

    static {
        SOURCE_CONTEXT_ARTIFACTS.add(TSK_ASSOCIATED_OBJECT);
    }

    private final BlackboardArtifact sourceContextArtifact;

    private final Long dateTime;

    /**
     * Creates new form ContextViewer
     */
    public ContextSourcePanel(String sourceName, String sourceText, BlackboardArtifact associatedArtifact, Long dateTime) {

        initComponents();
        sourceContextArtifact = associatedArtifact;
        setSourceName(sourceName);
        setSourceText(sourceText);
        this.dateTime = dateTime;
    }

    @Override
    public Long getDateTime() {
        return dateTime;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSourceGoToResultButton = new javax.swing.JButton();
        jSourceNameLabel = new javax.swing.JLabel();
        jSourceTextLabel = new javax.swing.JLabel();

        setBackground(ContentViewerDefaults.getPanelBackground());
        setMaximumSize(new java.awt.Dimension(495, 55));
        setMinimumSize(new java.awt.Dimension(300, 55));
        setPreferredSize(new java.awt.Dimension(495, 55));

        org.openide.awt.Mnemonics.setLocalizedText(jSourceGoToResultButton, org.openide.util.NbBundle.getMessage(ContextSourcePanel.class, "ContextSourcePanel.jSourceGoToResultButton.text")); // NOI18N
        jSourceGoToResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSourceGoToResultButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jSourceNameLabel, org.openide.util.NbBundle.getMessage(ContextSourcePanel.class, "ContextSourcePanel.jSourceNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jSourceTextLabel, org.openide.util.NbBundle.getMessage(ContextSourcePanel.class, "ContextSourcePanel.jSourceTextLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSourceNameLabel)
                .addGap(36, 36, 36)
                .addComponent(jSourceTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jSourceGoToResultButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSourceNameLabel)
                    .addComponent(jSourceTextLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSourceGoToResultButton)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSourceGoToResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSourceGoToResultButtonActionPerformed

        final DirectoryTreeTopComponent dtc = DirectoryTreeTopComponent.findInstance();

        // Navigate to the source context artifact.
        if (sourceContextArtifact != null) {
            dtc.viewArtifact(sourceContextArtifact);
        }
    }//GEN-LAST:event_jSourceGoToResultButtonActionPerformed

    /**
     * Sets the source label string.
     *
     * @param nameLabel String value for source label.
     */
    private void setSourceName(String nameLabel) {
        jSourceNameLabel.setText(nameLabel);
    }

    /**
     * Sets the source text string.
     *
     * @param text String value for source text.
     */
    private void setSourceText(String text) {
        jSourceTextLabel.setText(text);
        showSourceButton(!text.isEmpty());
        showSourceText(true);
    }

    private void showSourceText(boolean show) {
        jSourceTextLabel.setVisible(show);
    }

    private void showSourceButton(boolean show) {
        jSourceGoToResultButton.setVisible(show);
        jSourceGoToResultButton.setEnabled(show);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jSourceGoToResultButton;
    private javax.swing.JLabel jSourceNameLabel;
    private javax.swing.JLabel jSourceTextLabel;
    // End of variables declaration//GEN-END:variables
}
