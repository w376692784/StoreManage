package View;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.ClientUser;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class storehouse_search extends JInternalFrame implements ActionListener,MouseListener{

    JLabel search_jbl,keywords_jbl,jbl3,jbl4,jbl5,jbl6;
    JTextField keywords_jtf;
    JButton search_jb,add_jb,delete_jb,update_jb;
    JTable table;
    JScrollPane jsp;
    JComboBox jcb;
    JPanel jp1,jp2,jp3;
    String Wno;
    int row = -1;

    public storehouse_search(User u)
    {
        init(u);
        try {
            fillTable(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void init(User u)
    {
        jp1 = new JPanel();
        search_jbl = new JLabel("�ֿ���:");
        keywords_jtf = new JTextField(20);
        search_jb = new JButton("��ѯ");
        search_jb.addActionListener(this);
        jp1.add(search_jbl);
        jp1.add(keywords_jtf);
        jp1.add(search_jb);

        jp2 = new JPanel();
        jsp = new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{},new String[]{"�ֿ���","�ֿ�����","�ֿ��С","�ֿ����ÿռ�","�ֿ���ÿռ�","�ֿ��ַ"}));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        table.setPreferredScrollableViewportSize(new Dimension(750,250));
        table.addMouseListener(this);

        jsp.setViewportView(table);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setVisible(true);
        jp2.add(jsp);
//
//        UserTable = new JTable();
//        UserTable.setModel(new DefaultTableModel(new Object[][]{},new String[]{"�û����","�û���","����","Ȩ��"}));
//        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
//        r.setHorizontalAlignment(JLabel.CENTER);
//        UserTable.setDefaultRenderer(Object.class,r);
//        UserTable.setPreferredScrollableViewportSize(new Dimension(450,100));


        jp3 = new JPanel();
        add_jb = new JButton("���Ӳֿ�");
        add_jb.addActionListener(this);
        delete_jb = new JButton("ɾ���ֿ�");
        delete_jb.addActionListener(this);
        update_jb = new JButton("�޸Ĳֿ�");
        update_jb.addActionListener(this);
        if(u.getType() == 3)
        {
            update_jb.setEnabled(false);
            add_jb.setEnabled(false);
            delete_jb.setEnabled(false);
        }
        jp3.add(update_jb);
        jp3.add(add_jb);
        jp3.add(delete_jb);

        this.add(jp1,"North");
        this.add(jp2,"Center");
        this.add(jp3,"South");
        this.setSize(600,500);
//        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setVisible(true);
//        jcb = new JComboBox();
//        jcb.setModel(new DefaultComboBoxModel(new String[] {"","��Ʒ���","��Ʒ����","���ͱ��","��������","�ֿ���","����"}));
//        jcb.setSelectedIndex(-1);

    }

    void fillTable(String name) throws Exception {
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Message ms = new Message();
        ms.setCon(String.valueOf(keywords_jtf.getText()));
        ms.setMesType(MessageType.message_select_storehouseTable);

        ClientUser clientUser = new ClientUser();

        Vector v = clientUser.getTable(ms);
        for(int i=0;i<v.size()/6;i++)
        {
            Vector v2 = new Vector();
            for (int j=0;j<6;j++) {
                v2.addElement(v.get(j+i*6));
            }
            dtm.addRow(v2);
        }
//        dtm.addRow(v);
    }

//    public static void main(String [] args)
//    {
//        new storehouse_search();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search_jb)
        {
            try {
                fillTable(String.valueOf(keywords_jtf.getText()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == add_jb)
        {
            new storehouse_add();
        }
        else if(e.getSource() == delete_jb)
        {
            Message ms = new Message();
            ms.setCon(Wno);
            ms.setMesType(MessageType.message_delete_storehouseTable);
            ClientUser clientUser = new ClientUser();
            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"ɾ���ɹ�");
                try {
                    fillTable(null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else JOptionPane.showMessageDialog(this,"ɾ��ʧ��");
        }
        else if(e.getSource() == update_jb)
        {
            if(row == -1)
            {
                JOptionPane.showMessageDialog(this,"��ѡ��ֿ�");
                return;
            }
            Vector v = new Vector();
            v.addElement(Wno);
            v.addElement(String.valueOf(table.getValueAt(row,1)));
//            v.addElement(String.valueOf(table.getValueAt(row,2)));
            v.addElement(String.valueOf(table.getValueAt(row,5)));
            new storehouse_update(v);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == table)
        {
            row = table.getSelectedRow();
            Wno = String.valueOf(table.getValueAt(row,0));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
