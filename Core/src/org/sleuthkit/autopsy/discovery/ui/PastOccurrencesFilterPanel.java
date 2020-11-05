/*
 * Autopsy
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
package org.sleuthkit.autopsy.discovery.ui;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepository;
import org.sleuthkit.autopsy.coreutils.ThreadConfined;
import org.sleuthkit.autopsy.discovery.search.AbstractFilter;
import org.sleuthkit.autopsy.discovery.search.SearchData;
import org.sleuthkit.autopsy.discovery.search.SearchData.Frequency;
import org.sleuthkit.autopsy.discovery.search.SearchData.Type;
import org.sleuthkit.autopsy.discovery.search.SearchFiltering;

/**
 * Panel to allow configuration of the Past Occurrences filter.
 */
final class PastOccurrencesFilterPanel extends AbstractDiscoveryFilterPanel {

    private static final long serialVersionUID = 1L;
    private final Type type;

    /**
     * Creates new form PastOccurrencesFilterPanel.
     */
    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    PastOccurrencesFilterPanel(Type type) {
        initComponents();
        this.type = type;
        setUpFrequencyFilter();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pastOccurrencesCheckbox = new javax.swing.JCheckBox();
        crFrequencyScrollPane = new javax.swing.JScrollPane();
        crFrequencyList = new javax.swing.JList<>();

        org.openide.awt.Mnemonics.setLocalizedText(pastOccurrencesCheckbox, org.openide.util.NbBundle.getMessage(PastOccurrencesFilterPanel.class, "PastOccurrencesFilterPanel.pastOccurrencesCheckbox.text")); // NOI18N
        pastOccurrencesCheckbox.setMaximumSize(new java.awt.Dimension(150, 25));
        pastOccurrencesCheckbox.setMinimumSize(new java.awt.Dimension(150, 25));
        pastOccurrencesCheckbox.setPreferredSize(new java.awt.Dimension(150, 25));
        pastOccurrencesCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastOccurrencesCheckboxActionPerformed(evt);
            }
        });

        setMinimumSize(new java.awt.Dimension(250, 30));
        setPreferredSize(new java.awt.Dimension(250, 30));

        crFrequencyScrollPane.setPreferredSize(new java.awt.Dimension(27, 27));

        crFrequencyList.setModel(new DefaultListModel<Frequency>());
        crFrequencyList.setEnabled(false);
        crFrequencyList.setVisibleRowCount(5);
        crFrequencyScrollPane.setViewportView(crFrequencyList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crFrequencyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(crFrequencyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pastOccurrencesCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastOccurrencesCheckboxActionPerformed
        crFrequencyList.setEnabled(pastOccurrencesCheckbox.isSelected());
    }//GEN-LAST:event_pastOccurrencesCheckboxActionPerformed

    /**
     * Initialize the frequency filter.
     */
    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    private void setUpFrequencyFilter() {
        int count = 0;
        DefaultListModel<SearchData.Frequency> frequencyListModel = (DefaultListModel<SearchData.Frequency>) crFrequencyList.getModel();
        frequencyListModel.removeAllElements();
        if (!CentralRepository.isEnabled()) {
            if (type != Type.DOMAIN) {
                for (SearchData.Frequency freq : SearchData.Frequency.getOptionsForFilteringWithoutCr()) {
                    frequencyListModel.add(count, freq);
                }
            }
        } else {
            for (SearchData.Frequency freq : SearchData.Frequency.getOptionsForFilteringWithCr()) {
                if (type != Type.DOMAIN || freq != SearchData.Frequency.KNOWN) {
                    frequencyListModel.add(count, freq);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Frequency> crFrequencyList;
    private javax.swing.JScrollPane crFrequencyScrollPane;
    private javax.swing.JCheckBox pastOccurrencesCheckbox;
    // End of variables declaration//GEN-END:variables

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    void configurePanel(boolean selected, int[] indicesSelected) {
        boolean canBeFilteredOn = type != Type.DOMAIN || CentralRepository.isEnabled();
        pastOccurrencesCheckbox.setEnabled(canBeFilteredOn);
        pastOccurrencesCheckbox.setSelected(selected && canBeFilteredOn);

        if (pastOccurrencesCheckbox.isEnabled() && pastOccurrencesCheckbox.isSelected()) {
            crFrequencyScrollPane.setEnabled(true);
            crFrequencyList.setEnabled(true);
            if (indicesSelected != null) {
                crFrequencyList.setSelectedIndices(indicesSelected);
            }
        } else {
            crFrequencyScrollPane.setEnabled(false);
            crFrequencyList.setEnabled(false);
        }
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    JCheckBox getCheckbox() {
        return pastOccurrencesCheckbox;
    }

    @Override
    JLabel getAdditionalLabel() {
        return null;
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @NbBundle.Messages({"PastOccurrencesFilterPanel.error.text=At least one value in the past occurrence filter must be selected."})
    @Override
    String checkForError() {
        if (pastOccurrencesCheckbox.isSelected() && crFrequencyList.getSelectedValuesList().isEmpty()) {
            return Bundle.PastOccurrencesFilterPanel_error_text();
        }
        return "";
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    JList<?> getList() {
        return crFrequencyList;
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    AbstractFilter getFilter() {
        if (pastOccurrencesCheckbox.isSelected()) {
            return new SearchFiltering.FrequencyFilter(crFrequencyList.getSelectedValuesList());
        }
        return null;
    }
}
