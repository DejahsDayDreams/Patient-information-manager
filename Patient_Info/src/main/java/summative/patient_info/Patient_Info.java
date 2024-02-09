

package summative.patient_info;

 //imports
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter; // allows editing of files
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.*;

public abstract  class Patient_Info extends JFrame implements ActionListener
{

 // Declaring our variables    
         JFrame Frame;
         
         //JTable and search fucntion panel
        JPanel myPanel;
        
        
        JButton MyBtn;
        JButton SaveBtn;
        
        JTextField SearchField;
       JLabel SearchLabel;
       JTable UserTable;
       JScrollPane TableDisplay;
       TableModel Model; // Used to contain temporary data, Part of MVC     
       TableRowSorter Sorter; // Help arrange the filtered data in a row instead of single cell(a record)
    
       //adding the patients information panel
       JPanel InfoPanel;
       
       JLabel Addpat;
       JLabel NameLabel;
       JLabel AddressLabel;
       JLabel ContLabel;
       JLabel IDLabel;
       JLabel info;
       
       
       JTextField NameTF;
       JTextField AddressTF;
       JTextField IDTF;
       JTextField ContTF;
       
       
       
       
           Patient_Info() throws IOException
            {
                Frame = new JFrame();
                
                
               // Table and serch panel
             // Frame Setup        
             myPanel = new JPanel();
             myPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
             myPanel.setBackground(Color.LIGHT_GRAY);
    
             SearchLabel = new JLabel("Search: ");
             SearchField = new JTextField();
             
             SaveBtn = new JButton("Save Patient Information");
            
             

             //table set up
             String[] ColumnNames = {"Full Name", "Address", "Contact Number", "ID Number", "Severity of Injury"};
             Object[][] TableData ={
                 {"Samantha Kitchener", "CTU Pretoria", "0736148044", "9509190113085", "Green"},
                 {"Samantha Jarvis", "Manchester", "0734164408","9209190113085", "Yellow"},
                 {"Kyle Kitchener", "CTU Pretoria", "0730070025", "9411160113085", "Red"},
                 {"Louize Kitchener", "Krugersdorp", "0828781154", "7308070113085", "Yellow"}
                 
                 
                 
             };
              
                Model = new DefaultTableModel(TableData, ColumnNames);
                Sorter = new TableRowSorter(Model);
                
                
                UserTable = new JTable(Model);
           
                UserTable.setRowSorter(Sorter);
                
                UserTable.setSelectionBackground(Color.LIGHT_GRAY);
                
                TableDisplay = new JScrollPane(UserTable);
                
                
                //adding the elements
                BoxLayout boxlayout = new BoxLayout(myPanel, BoxLayout.Y_AXIS);
                myPanel.setLayout(boxlayout);
                add(myPanel, BorderLayout.WEST);
                
                myPanel.add(TableDisplay);
      
                UserTable.setBorder(BorderFactory.createEmptyBorder(100, 5, 0, 5));
                UserTable.setPreferredSize(new Dimension(500,300));
                UserTable.setMaximumSize(new Dimension(800,500));
                
                TableDisplay.setPreferredSize(new Dimension(500,300));
                TableDisplay.setMaximumSize(new Dimension(800,500));
                
                myPanel.add( SaveBtn);
                
      
                myPanel.add(SearchLabel);
                myPanel.add(SearchField);
                 SearchField.setPreferredSize(new Dimension(300, 20));
                 SearchField.setMaximumSize(new Dimension(400, 40));
                 SearchField.setAlignmentX(Component.LEFT_ALIGNMENT);
                 SearchLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                 SearchField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                
                
         
            
                
               // Setting up the Frame
                setTitle("Patient Information");
     
                    
      
                    //Search Functionality
                    SearchField.getDocument().addDocumentListener(new DocumentListener()
                    {
                        
                    @Override
                    public void insertUpdate(DocumentEvent de)
                    {
                        Search(SearchField.getText());
                    }
                     @Override
                    public void removeUpdate(DocumentEvent de)
                    {
                        Search(SearchField.getText());
                    }
                     @Override
                    public void changedUpdate(DocumentEvent de)
                    {
                        Search(SearchField.getText());
                    }

                    public void Search( String i)
                    {
                        if(i.length() ==0)
                        {
                            Sorter.setRowFilter(null);
                        }
                        else
                        {
                            Sorter.setRowFilter(RowFilter.regexFilter(i));
                        }
                    }
   
                });
                    
                    
                    
                  // Saving to a text file  
             SaveBtn.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               try{
                //the file path
               File file = new File("C:\\Users\\SammyAntha\\Documents\\NetBeansProjects\\Patient_Info\\Data.txt");
               //if the file not exist create one
               if(!file.exists()){
                   file.createNewFile();
               }
               
               FileWriter fw = new FileWriter(file.getAbsoluteFile());
               BufferedWriter bw = new BufferedWriter(fw);
               
               //loop for jtable rows
               for(int i = 0; i < UserTable.getRowCount(); i++){
                   //loop for jtable column
                   for(int j = 0; j < UserTable.getColumnCount(); j++){
                       bw.write(UserTable.getModel().getValueAt(i, j)+" ");
                   }
                   //break line at the begin 
                   //break line at the end 
                   bw.write("\n_________\n");
               }
               //close BufferedWriter
               bw.close();
               //close FileWriter 
               fw.close();
               JOptionPane.showMessageDialog(null, "Information Saved");
               
               }catch(Exception ex){
                   ex.printStackTrace();
               }
           }
        });
                    
                    
                    
                    
                 
                     
                        // adding a new patients information panel
                     InfoPanel = new JPanel();
                     BoxLayout boxlayout1 = new BoxLayout(InfoPanel, BoxLayout.Y_AXIS);
                     InfoPanel.setLayout(boxlayout1);
                     InfoPanel.setBackground(Color.LIGHT_GRAY);
                     InfoPanel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));
                     InfoPanel.setAlignmentX(LEFT_ALIGNMENT);
                     
                    
                     InfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                     
                     Addpat = new JLabel("Add a new Patient:");
                     Addpat.setFont(new Font("Arial", Font.BOLD, 20));
                     
                    NameLabel = new JLabel("Full Name:");
                    NameTF = new JTextField();
                    NameTF.setPreferredSize(new Dimension(100, 10));
                    NameTF.setMaximumSize(new Dimension(400, 40));
                    NameTF.setAlignmentX(Component.LEFT_ALIGNMENT);
                   
                   
                    
                    AddressLabel = new JLabel("Address:");
                    AddressTF = new JTextField();
                    AddressTF.setPreferredSize(new Dimension(100, 10));
                    AddressTF.setMaximumSize(new Dimension(400, 40));
                    AddressTF.setAlignmentX(Component.LEFT_ALIGNMENT);
              
                    
                    IDLabel = new JLabel("ID Number:");
                    IDTF = new JTextField();
                    IDTF.setPreferredSize(new Dimension(100, 10));
                    IDTF.setMaximumSize(new Dimension(400, 40));
                    IDTF.setAlignmentX(Component.LEFT_ALIGNMENT);
                    
                    
                    ContLabel = new JLabel("Contact Number:");
                    ContTF = new JTextField();
                    ContTF.setPreferredSize(new Dimension(100, 10));
                    ContTF.setMaximumSize(new Dimension(400, 40));
                    ContTF.setAlignmentX(Component.LEFT_ALIGNMENT);
                    
                    
                    
                    info = new JLabel(" Please select the severity on the table after adding patient");
                    TableColumn testColumn = UserTable.getColumnModel().getColumn(4);
                    JComboBox<String> comboBox = new JComboBox<>();
                    comboBox.addItem("Green");
                    comboBox.addItem("Yellow");
                    comboBox.addItem("Red");
                    
                    testColumn.setCellEditor(new DefaultCellEditor(comboBox));

                    
                    
                     MyBtn = new JButton("Add Patient");
                     MyBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
                     MyBtn.setPreferredSize(new Dimension(120, 10));
                     MyBtn.setMaximumSize(new Dimension(400, 50));
                     
                     //Adding data from a TextField to the table
                     MyBtn.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent ae)
                        {
                            
                            String s = "";
                        boolean exists = false;
                        for (int i = 0; i < UserTable.getRowCount(); i++) {
                            s = UserTable.getValueAt(i, 4).toString().trim();

                            if (IDTF.getText().equals("")) {
                                
                            } else {
                                if (IDTF.getText().equals(s)) {
                                    exists = true;
                                    break;
                                }
                            }
                        }
                        if (!exists) {
                            String data1 = NameTF.getText();
                            String data2 = AddressTF.getText();
                            String data3 = ContTF.getText();
                            String data4 = IDTF.getText();
                          
                             

                            Object[] row = { data1, data2, data3, data4 };

                            DefaultTableModel model = (DefaultTableModel) UserTable.getModel();

                            model.addRow(row);
                        } else {
                            JOptionPane.showMessageDialog(null, "ID Number already exists.");
                        }
                         
                        NameTF.setText("");
                        AddressTF.setText("");
                        ContTF.setText("");
                        IDTF.setText("");
                        }

                    
                    });
                     
                     
                     

                
                    //adding the elements
                add(InfoPanel, BorderLayout.CENTER);
                InfoPanel.add(Addpat);
                Addpat.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                
                InfoPanel.add(NameLabel);
               
                InfoPanel.add(NameTF);
                
                InfoPanel.add(AddressLabel);
                
                InfoPanel.add(AddressTF);
                
                InfoPanel.add(IDLabel);
               
                InfoPanel.add(IDTF);
                
                InfoPanel.add(ContLabel);
               
                InfoPanel.add(ContTF);
              
                InfoPanel.add(info);
                info.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 15));
                
                InfoPanel.add(MyBtn);
                MyBtn.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
                
                
                    
                

                
                myPanel.setVisible(true);
                InfoPanel.setVisible(true);
                Frame.pack();
            }
           
           
           
       //Main method
                   
            public static void main(String[ ] args) throws IOException
            {
            Patient_Info info = new Patient_Info() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            };
            info.setSize(1000,450);
            info.setVisible(true);
            
              
            }

 
}
