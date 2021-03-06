package ui;

import importData.ContainerAreaInfo;
import importData.ImportData;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by leko on 2016/1/19.
 */
public class ContainerAreaFrame extends JFrame {
    private JPanel panelCenter;
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTable tableWQL;


    ContainerAreaFrame() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);//居中显示
        {
            this.panelCenter = new JPanel();
            this.panelCenter.setBorder(new TitledBorder(null, "箱区", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            this.contentPane = new JPanel();
            this.contentPane.setLayout(new BorderLayout(0, 0));
            this.contentPane.add(this.panelCenter, BorderLayout.CENTER);
            setContentPane(this.contentPane);
            this.panelCenter.setLayout(new BorderLayout(0, 0));
            {
                {
                    this.scrollPane = new JScrollPane();
                    this.panelCenter.add(this.scrollPane, BorderLayout.CENTER);
                    {
                        this.tableWQL = new JTable();
                        this.scrollPane.setViewportView(this.tableWQL);
//                        DefaultTableModel tableModel = new DefaultTableModel();
                        TableModel tableModel = new TableModel();
                        //增加列名
                        ArrayList<String> colList = new ArrayList<String>(Arrays.asList("箱区ID", "箱区左下角坐标", "箱区左上角坐标", "箱区右下角坐标", "箱区右上角坐标", "箱区倍位数","层数","排数","箱区吊机类型", "靠近海测吊机移动速度", "靠近陆侧吊机移动速度", "靠近海测吊机工作效率", "靠近陆侧吊机工作效率"));
                        for (String col : colList) {
                            System.out.println(col);
                            tableModel.addColumn(col);
                        }

                        //增加内容
                        java.util.List<ContainerAreaInfo> containerAreaInfoList = ImportData.containerAreaInfoList;
                        System.out.print("生成内容");
                        for (ContainerAreaInfo containerAreaInfo : containerAreaInfoList) {
                            Object[] rowData = new Object[13];
                            rowData[0] = containerAreaInfo.getID();
                            rowData[1] = containerAreaInfo.getLOCATIONLB();
                            rowData[2] = containerAreaInfo.getLOCATIONLH();
                            rowData[3] = containerAreaInfo.getLOCATIONRB();
                            rowData[4] = containerAreaInfo.getLOCATIONRH();
                            rowData[5] = containerAreaInfo.getVBYNUM();
                            rowData[6] = containerAreaInfo.getVTRNUM();
                            rowData[7] = containerAreaInfo.getVRWNUM();
                            rowData[8] = containerAreaInfo.getSCTYPE();
                            rowData[9] = containerAreaInfo.getASCBOTTOMSPEED();
                            rowData[10] = containerAreaInfo.getASCTOPSPEED();
                            rowData[11] = containerAreaInfo.getWORKEFFICIENCYB();
                            rowData[12] = containerAreaInfo.getWORKEFFICIENCYT();
                            tableModel.addRow(rowData);
                        }
                        this.tableWQL.setModel(tableModel);
                    }
                }
                final TableRowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<javax.swing.table.TableModel>(
                        tableWQL.getModel());
                tableWQL.setRowSorter(sorter);
            }
        }
    }
}
