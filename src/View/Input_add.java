package View;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import Model.checkEmpty;

public class Input_add extends JFrame implements ActionListener,ItemListener{

    JLabel Gno,Gname,Tno,Tname,Sno,Wno,Wname,Inum;
    JTextField Gno_jtf,Gname_jtf,Tno_jtf,Tname_jtf,Sno_jtf,Wno_jtf,Inum_jtf;
    JComboBox Gno_jcb,Gname_jcb,Tno_jcb,Sno_jcb,Wname_jcb;
    JButton enter_jb,cancle_jb;
    Vector v1,v2,v3,v4;


    public Input_add()
    {
        Gno = new JLabel("������");
        Gname = new JLabel("��������");
        Tno = new JLabel("�������ͺ�");
        Tname = new JLabel("��������");
        Sno = new JLabel("��Ӧ�̱��");
        Wno = new JLabel("��Ųֿ��");
        Wname = new JLabel("�ֿ�����");
        Inum = new JLabel("��������");

        Gno_jtf = new JTextField(20);
        Gno_jtf.setEditable(false);
        Tno_jtf = new JTextField(20);
        Tno_jtf.setEditable(false);
        Tname_jtf = new JTextField(20);
        Tname_jtf.setEditable(false);
        Sno_jtf = new JTextField(20);
        Sno_jtf.setEditable(false);
        Wno_jtf = new JTextField(20);
        Wno_jtf.setEditable(false);
        Inum_jtf = new JTextField(20);

        enter_jb = new JButton("ȷ��");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("����");
        cancle_jb.addActionListener(this);

        Message ms = new Message();
        Message ms2 = new Message();
        ms.setMesType(MessageType.message_select_goods);
        ms2.setMesType(MessageType.message_select_storehouseTable);
        ClientUser clientUser = new ClientUser();
        v1 = clientUser.getTable(ms);
        v2 = new Vector();
        v3 = clientUser.getTable(ms2);
        v4 = new Vector();
        int i = 1;
        while(i<v1.size())
        {
            v2.addElement(v1.get(i));
            i+=5;
        }
        i = 1;
        while(i < v3.size())
        {
            v4.addElement(v3.get(i));
            i+=4;
        }

        Gname_jcb = new JComboBox(v2);
        Gname_jcb.setPreferredSize(new Dimension(120,18));
        Gname_jcb.setSelectedIndex(-1);
        Gname_jcb.addItemListener(this);

        Wname_jcb = new JComboBox(v4);
        Wname_jcb.setPreferredSize(new Dimension(120,18));
        Wname_jcb.setSelectedIndex(-1);
        Wname_jcb.addItemListener(this);

        this.setLayout(new FlowLayout());
        this.add(Gno);
        this.add(Gno_jtf);
        this.add(Gname);
        this.add(Gname_jcb);
        this.add(Tno);
        this.add(Tno_jtf);
        this.add(Tname);
        this.add(Tname_jtf);
        this.add(Sno);
        this.add(Sno_jtf);
        this.add(Wno);
        this.add(Wno_jtf);
        this.add(Wname);
        this.add(Wname_jcb);
        this.add(Inum);
        this.add(Inum_jtf);
        this.add(enter_jb);
        this.add(cancle_jb);
        this.setSize(220,250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }






    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(Wname_jcb.getSelectedItem()==null || Gname_jcb.getSelectedItem()==null || Inum_jtf.getText()==null)
            {
                JOptionPane.showMessageDialog(this,"�벹ȫ�հ���");
            }
            Vector v = new Vector();
            v.addElement((String)Gno_jtf.getText());
            v.addElement((String)Gname_jcb.getSelectedItem());
            v.addElement((String)Tno_jtf.getText());
            v.addElement((String)Tname_jtf.getText());
            v.addElement((String)Sno_jtf.getText());
            v.addElement((String)Wno_jtf.getText());
            v.addElement((String)Inum_jtf.getText());
            v.addElement(new java.util.Date().toString());

            Message m = new Message();
            m.setV(v);
            m.setMesType(MessageType.message_insert_InputTable);
            ClientUser clientUser = new ClientUser();
            if(clientUser.SendInfo(m)!=0)
            {
                JOptionPane.showMessageDialog(this,"���ɹ�");
            }
            else JOptionPane.showMessageDialog(this,"���ʧ��");
//            if()
        }
        else if(e.getSource() == cancle_jb)
        {
            Wname_jcb.setSelectedIndex(-1);
            Gname_jcb.setSelectedIndex(-1);
            Gno_jtf.setText("");
            Tno_jtf.setText("");
            Tname_jtf.setText("");
            Sno_jtf.setText("");
            Wno_jtf.setText("");

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getItemSelectable() == Gname_jcb)
        {
            String Gname = (String)Gname_jcb.getSelectedItem();
            for(int i = 1;i<v1.size();i+=5)
            {
                if(v1.get(i).equals(Gname))
                {
                    Gno_jtf.setText((String)v1.get(i-1));
                    Tno_jtf.setText((String)v1.get(i+1));
                    Tname_jtf.setText((String)v1.get(i+2));
                    Sno_jtf.setText((String)v1.get(i+3));
                }
            }

        }
        else if(e.getItemSelectable() == Wname_jcb)
        {
            String Wname = (String)Wname_jcb.getSelectedItem();
            for(int i = 1;i<v3.size();i++)
            {
                if(v3.get(i).equals(Wname))
                {
                    Wno_jtf.setText((String)v3.get(i-1));
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new Input_add();
    }


}