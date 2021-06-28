package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ConnectionService;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.PersonService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * The type App.
 */
public class App {
    private JPanel mainPanel;
    private JPanel connexionPanel;
    private JButton connexionBtn;
    private JPanel menuPanel;
    private JPanel studentPanel;
    private JPanel coursePanel;
    private JPanel studentListPanel;
    private JPanel addStudentPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton addStudentBtn;
    private JButton StudentBtn;
    private JButton CourseBtn;
    private JButton studentListBtn;
    private JButton returnBtn;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton modifyStudent;
    private JButton deleteStudent;
    private JButton returnBtn1;
    private JTextField textField8;
    private JTextField textField9;
    private JButton addCourse;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton associerButton;
    private JButton returnBtn2;
    private JButton returnBtn3;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JButton createStudent;
    private JPanel infoStudentPanel;
    private JTextField hiddenTextField1;
    private JTextField textField16;
    private JPasswordField passwordField2;
    private JTable table1;
    private JScrollPane scrollPane1;
    private JButton logoutBtn;
    private JPanel averagePanel;
    private JButton returnBtn4;
    private JButton averageBtn;
    private JTextField textField17;
    private JPanel markPanel;
    private JButton markBtn;
    private JComboBox comboBoxCourseMark;
    private JComboBox comboBoxStrudentMark;
    private JTextField textFieldMark;
    private JTextField textFieldAppreciationMark;
    private JButton addBtnMark;
    private JButton returnBoutton6;
    private JPanel contentChart;
    private JPanel contentPieChart;
    private JComboBox comboBox4;


    /**
     * Instantiates a new App.
     */
    public App() {
        //Vue connexion
        connexionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textField1.getText();
                String pwd = passwordField1.getText();
                //Check if user set all input
                if (!login.isEmpty()  && !pwd.isEmpty()) {
                    ConnectionService sc = new ConnectionService();
                    PersonService sp = new PersonService();

                    int idConnexion = 0;
                    try {
                        idConnexion = sc.checkConnection(textField1.getText(), passwordField1.getText());

                        PersonDTO p = sp.get(idConnexion);
                        Role r = p.getRole();
                        if (r.getNum() == 1 || r.getNum() == 2) {
                            connexionPanel.setVisible(false);
                            menuPanel.setVisible(true);
                            if (r.getNum() == 1) {
                                studentListBtn.setVisible(true);
                            } else {
                                studentListBtn.setVisible(false);
                            }
                        }
                    } catch (ExceptionService es) {
                        JOptionPane.showMessageDialog(null, es.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
                }
                //JOptionPane.showMessageDialog(null, textField1.getText());
            }
        });
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                connexionPanel.setVisible(true);
                textField1.setText("");
                passwordField1.setText("");
            }
        });

        //Vue menu
        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                addStudentPanel.setVisible(true);
            }
        });
        StudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                studentPanel.setVisible(true);

                //Add item in combobox student
                PersonService ps = new PersonService();
                comboBox1.removeAll();
                comboBox1.removeAllItems();
                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            comboBox1.addItem(new Item(p.getId(), p.getFirstname() + " " + p.getLastname()));
                        }
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }
            }
        });
        CourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                coursePanel.setVisible(true);

                //Add item in combobox course
                CourseService cs = new CourseService();
                comboBox3.removeAll();
                comboBox3.removeAllItems();
                try {
                    for(CourseDTO c : cs.getAll()){
                        comboBox3.addItem(new Item(c.getId(), c.getCourseSubject()));
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }

                //Add item in combobox student
                PersonService ps = new PersonService();

                comboBox2.removeAll();
                comboBox2.removeAllItems();
                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            comboBox2.addItem(new Item(p.getId(), p.getFirstname() + " " + p.getLastname()));
                        }
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }
            }
        });



        studentListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                studentListPanel.setVisible(true);
                // TableModel's column names
                //todo sur de garde le bouton supprimer car créer des bugs
                String[] columnNames = {
                        "Nom", "Prénom", "Email", "Adresse", "Téléphone", "Date de naissance", "Action1"
                };
                PersonService ps = new PersonService();
                int nbStudent = 0;
                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            nbStudent++;
                        }
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }

                Object[][] data = new Object[nbStudent][7];
                int count = 0;
                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            data[count][0] = p.getFirstname();
                            data[count][1] = p.getLastname();
                            data[count][2] = p.getMailAddress();
                            data[count][3] = p.getAddress();
                            data[count][4] = p.getPhoneNumber();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date dob = sdf.parse(((StudentDTO) p).getDateOfBirth().toString());
                                String strDate = sdf.format(dob);
                                data[count][5] = strDate;
                            } catch (ParseException parseException) {
                                parseException.printStackTrace();
                            }


                            data[count][6] = "Gérer l'étudiant";


                            count++;
                        }
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }


                StudentTableModel modele =new StudentTableModel(data,columnNames);
                table1.setModel(modele);
                table1.getTableHeader().setEnabled(false);


                TableCellRenderer tableRenderer;
                tableRenderer = table1.getDefaultRenderer(JButton.class);
                table1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
                //Design grid tableau
                table1.setRowHeight(30);
                //Design header tableau
                table1.getTableHeader().setBackground(new Color(46, 64, 83));
                table1.getTableHeader().setFont(new Font("Segoe UI ",0, 16));
                table1.getTableHeader().setForeground(new Color(255, 255,255));
                table1.getTableHeader().setSize(70, 45);

                table1.getColumn("Action1").setCellRenderer( new DefaultTableCellRenderer() {
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { setText(value.toString()); setBackground(new Color(255, 193 ,7));
                        return this;
                    }
                });



                // Action qui récupérer tous les click sur la table
                ListSelectionModel cellSelectionModel = table1.getSelectionModel();
                cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


                cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        // Permet d'écouter l'action que sur le click
                        if (e.getValueIsAdjusting() == true) {
                            String columnsSelect = null;
                            int rowSelect = 0;
                            String personSelect = null;

                            //On récupérer toutes les ligne et colonnes
                            int[] selectedRow = table1.getSelectedRows();
                            int[] selectedColumns = table1.getSelectedColumns();


                            //On parcours chaque ligne et chaque colonnes
                            for (int i = 0; i < selectedRow.length; i++) {
                                for (int j = 0; j < selectedColumns.length; j++) {
                                    //On récupére la colonne séléctionner
                                    columnsSelect = table1.getColumnName(selectedColumns[j]);
                                    //On récupére la ligne séléctionner
                                    rowSelect = selectedRow[i];
                                }
                            }

                            // Bouton Modifier séléctionner
                            if(columnsSelect == "Action1"){
                                //Permet de récupérer l'email de l'utilisateur

                                personSelect = (String) modele.getValueAt(rowSelect, 2);

                                studentPanel.setVisible(true);
                                studentListPanel.setVisible(false);

                                //Add item in combobox student
                                PersonService ps = new PersonService();
                                comboBox1.removeAllItems();
                                try {
                                    for(PersonDTO p : ps.getAll()){
                                        //On filtre toutes les Personne de type Student et on regarde si sont email corresponds a celui de la ligne sélectionner
                                        if(p instanceof StudentDTO ) {
                                            Item i = new Item(p.getId(), p.getFirstname() + " " + p.getLastname());
                                            comboBox1.addItem(i);
                                            if(p.getMailAddress().equals(personSelect)){
                                                comboBox1.setSelectedItem(i);
                                            }
                                        }
                                    }
                                } catch (ExceptionService es) {
                                    JOptionPane.showMessageDialog(null, es.getMessage());
                                }


                            }

                        }
                    }

                });
            }
        });



        //User interactions
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TF8 et TF9
                if (textField8.getText().isEmpty() || textField9.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Un des champs est vide");
                } else {
                    //If nb hours is not numeric return msgbox
                    try {
                        float f = Float.parseFloat(textField9.getText());

                        //Create course with parameters
                        CourseService cs = new CourseService();
                        CourseDTO c = new CourseDTO(textField8.getText(), f);
                        cs.create(c);
                        comboBox3.addItem(textField8.getText());
                        textField8.setText("");
                        textField9.setText("");
                    } catch (ExceptionService es) {
                        JOptionPane.showMessageDialog(null, es.getMessage());
                    }
                }
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox c = (JComboBox) e.getSource();

                Item item = (Item) c.getSelectedItem();

                if (item != null) {
                    //On affiche les informations utilisateurs
                    PersonService ps = new PersonService();
                    PersonDTO p = null;
                    try {
                        p = ps.get(item.getId());
                    } catch (ExceptionService es) {
                        JOptionPane.showMessageDialog(null, es.getMessage());
                    }
                    if (p instanceof StudentDTO) {
                        String s = String.valueOf(p.getId());
                        hiddenTextField1.setText(s);
                        textField2.setText(p.getLastname());
                        textField3.setText(p.getFirstname());
                        textField4.setText(p.getMailAddress());
                        textField5.setText(p.getAddress());
                        textField6.setText(p.getPhoneNumber());
                        textField17.setText(Double.toString(((StudentDTO) p).getAverage()));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = sdf.parse(((StudentDTO) p).getDateOfBirth().toString());
                            String strDate = sdf.format(date);
                            textField7.setText(strDate);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }

                        passwordField2.setText(p.getPassword());
                    }
                }
            }
        });
        deleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonService ps = new PersonService();
                try {
                    ps.delete(parseInt(hiddenTextField1.getText()));
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }

                hiddenTextField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");

                comboBox1.removeItemAt(comboBox1.getSelectedIndex());
            }
        });
        createStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TF10 à TF15
                if (textField10.getText() == "" || textField11.getText() == "" || textField12.getText() == "" || textField13.getText() == "" || textField14.getText() == "" || textField15.getText() == "" || textField16.getText() == "") {
                    JOptionPane.showMessageDialog(null, "Un des champs est vide");
                } else {
                    //If nb hours is not numeric return msgbox
                    try {
                        //Create course with parameters
                        PersonService sp = new PersonService();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                        Date auj = sdf.parse(textField15.getText());
                        //sp.create(textField11.getText(), textField12.getText(), textField13.getText(), textField14.getText(), textField10.getText(), textField16.getText(), 4, auj ,"");
                        textField10.setText("");
                        textField11.setText("");
                        textField12.setText("");
                        textField13.setText("");
                        textField14.setText("");
                        textField15.setText("");
                        textField16.setText("");
                        JOptionPane.showMessageDialog(null, "L'étudiant à bien été crée");
                    } catch (NumberFormatException | ParseException nfe) {
                        JOptionPane.showMessageDialog(null, "Un des paramètres à pas a été renseigné");
                    }
//                    catch (ExceptionService es) {
//                        JOptionPane.showMessageDialog(null, es.getMessage());
//                    }
                }
            }
        });
        associerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = (Item) comboBox3.getSelectedItem();
                int idCourse = item.getId();
                Item item2 = (Item) comboBox2.getSelectedItem();
                int idStudent = item2.getId();

                PersonService ps = new PersonService();
                try {
                    ps.linkToCourse(idStudent, idCourse);
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }
            }
        });


        ActionListener modifyStudentAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TF2 à TF7
                if (textField2.getText() == "" || textField3.getText() == "" || textField4.getText() == "" || textField5.getText() == "" || textField6.getText() == "" || textField7.getText() == "" || passwordField2.getText() == "") {
                    JOptionPane.showMessageDialog(null, "Un des champs est vide");
                } else {
                    //If nb hours is not numeric return msgbox
                    try {
                        //Create course with parameters
                        PersonService sp = new PersonService();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                        Date auj = sdf.parse(textField7.getText());
//                        sp.update(textField3.getText(), textField4.getText(), textField5.getText(), textField6.getText(), textField2.getText(), passwordField2.getText(), 4, auj ,"", Double.parseDouble(textField17.getText()));
                        JOptionPane.showMessageDialog(null, "Les informations de l'étudiant ont bien été modifié");
                    } catch (NumberFormatException | ParseException nfe) {
                        JOptionPane.showMessageDialog(null, "Un des paramètres n'a pas été renseigné");
                    }
//                    catch (ExceptionService es) {
//                        JOptionPane.showMessageDialog(null, es.getMessage());
//                    }
                }
            }
        };

        modifyStudent.addActionListener(modifyStudentAction);

        //Btn return
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                studentPanel.setVisible(false);
            }
        });
        returnBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                coursePanel.setVisible(false);
            }
        });
        returnBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                studentListPanel.setVisible(false);
            }
        });
        returnBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                addStudentPanel.setVisible(false);
            }
        });
        returnBtn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                averagePanel.setVisible(false);
            }
        });
        averageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                averagePanel.setVisible(true);
                JFreeChart histo = ChartFactory.createBarChart("Classement des étudiants par moyenne", "Moyenne", "Etudiant", createDataset(), PlotOrientation.VERTICAL, true, true,false);
                histo.getPlot().setBackgroundPaint(new Color(39,55,70));
                histo.setBackgroundPaint(new Color(39,55,70));
                histo.getLegend().setBackgroundPaint(new Color(39,55,70));
                histo.getTitle().setPaint(new Color(255,255,255));
                //CategoryPlot plot = histo.getCategoryPlot();
                //plot.getRenderer().setSeriesPaint(0, new Color(217,83,79));
               // plot.getRenderer().setSeriesPaint(1, new Color(240,173,78));
               // plot.getRenderer().setSeriesPaint(2, new Color(92,184,92));
                //plot.getRenderer().setSeriesPaint(3, new Color(2,117,216));
                 ///Get instance of CategoryPlot /
                 CategoryPlot plot = histo.getCategoryPlot();

                // Change Bar colors */
                BarRenderer renderer = (BarRenderer) plot.getRenderer();

                renderer.setSeriesPaint(0, new Color(217,83,79));
                renderer.setSeriesPaint(1, new Color(240,173,78));
                renderer.setSeriesPaint(2, new Color(92,184,92));
                renderer.setSeriesPaint(3, new Color(2,117,216));
                renderer.setDrawBarOutline(false);
                renderer.setItemMargin(0);
                renderer.setLegendTextPaint(0,new Color(255,255,255));
                renderer.setLegendTextPaint(1,new Color(255,255,255));
                renderer.setLegendTextPaint(2,new Color(255,255,255));
                renderer.setLegendTextPaint(3,new Color(255,255,255));

                plot.getDomainAxis().setLabelPaint(new Color(255,255,255));
                plot.getRangeAxis().setLabelPaint(new Color(255,255,255));
                plot.getDomainAxis().setTickLabelPaint(new Color(255,255,255));
                plot.getRangeAxis().setTickLabelPaint(new Color(255,255,255));
                plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());


                /***XYPlot xyPlot = histo.getXYPlot();
                ((CategoryPlot) plot).getDomainAxis().setLabelPaint(new Color(255,255,255));
                xyPlot.getDomainAxis().setLabelPaint(new Color(255,255,255));
                xyPlot.getRangeAxis().setLabelPaint(new Color(255,255,255));**/




                ChartPanel chartPanel = new ChartPanel(histo);
                chartPanel.setBackground(new Color(39,55,70));
                contentChart.removeAll();
                contentChart.add(chartPanel,BorderLayout.CENTER);
                contentChart.validate();

                JFreeChart pieChart = ChartFactory.createPieChart("Niveau des étudiants par moyenne", createPieDataset());
                pieChart.getPlot().setBackgroundPaint(new Color(39,55,70));
                pieChart.getLegend().setBackgroundPaint(new Color(39,55,70));
                pieChart.setBackgroundPaint(new Color(39,55,70));
                pieChart.getTitle().setPaint(new Color(255,255,255));
                pieChart.getLegend().setItemPaint(new Color(255,255,255));
                pieChart.getPlot().setOutlinePaint(null);
                PiePlot plot1 = (PiePlot)pieChart.getPlot();
                plot1.setSectionPaint(0, new Color(217,83,79));
                plot1.setSectionPaint(1, new Color(240,173,78));
                plot1.setSectionPaint(2, new Color(92,184,92));
                plot1.setSectionPaint(3, new Color(2,117,216));

                ChartPanel chartPanel2 = new ChartPanel(pieChart);
                chartPanel2.setBackground(new Color(39,55,70));
                contentPieChart.removeAll();
                contentPieChart.add(chartPanel2,BorderLayout.CENTER);
                contentPieChart.validate();
            }
        });

        markBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                markPanel.setVisible(true);

                //Add item in combobox course
                CourseService cs = new CourseService();
                comboBoxCourseMark.removeAll();
                comboBoxCourseMark.removeAllItems();
                try {
                    for(CourseDTO c : cs.getAll()){
                        comboBoxCourseMark.addItem(new Item(c.getId(), c.getCourseSubject()));
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }

                //Add item in combobox student
                PersonService ps = new PersonService();

                comboBoxStrudentMark.removeAll();
                comboBoxStrudentMark.removeAllItems();
                try {
                    for(PersonDTO p : ps.getAll()){
                        if(p instanceof StudentDTO) {
                            comboBoxStrudentMark.addItem(new Item(p.getId(), p.getFirstname() + " " + p.getLastname()));
                        }
                    }
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }
            }
        });
        addBtnMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = (Item) comboBoxCourseMark.getSelectedItem();
                int idCourse = item.getId();
                Item item2 = (Item) comboBoxStrudentMark.getSelectedItem();
                int idStudent = item2.getId();

                MarkService ps = new MarkService();
                try {
                    ps.create(new MarkDTO( idStudent, idCourse, Float.parseFloat(textFieldMark.getText()), textFieldAppreciationMark.getText()));
                    JOptionPane.showMessageDialog(null, "Note ajoutee avec succes.");
                    textFieldMark.setText("");
                    textFieldAppreciationMark.setText("");
                    comboBoxCourseMark.setSelectedIndex(-1);
                    comboBoxStrudentMark.setSelectedIndex(-1);
                } catch (ExceptionService es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                }
            }
        });
        returnBoutton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                markPanel.setVisible(false);
            }
        });
    }

    private DefaultCategoryDataset createDataset() {
            int badStudent = 0;
            int averageStudent = 0;
            int goodStudent = 0;
            int excellentStudent = 0;
            //Add item in combobox student
            PersonService ps = new PersonService();
            try {
                for(PersonDTO p : ps.getAll()){
                    if(p instanceof StudentDTO) {
                        if(((StudentDTO) p).getAverage() < 8){
                            badStudent++;
                        }else if(((StudentDTO) p).getAverage() >= 8 && ((StudentDTO) p).getAverage() < 12){
                            averageStudent++;
                        }else if(((StudentDTO) p).getAverage() >= 12 && ((StudentDTO) p).getAverage() < 17){
                            goodStudent++;
                        }else if(((StudentDTO) p).getAverage() >= 17){
                            excellentStudent++;
                        }
                    }
                }
            } catch (ExceptionService es) {
                JOptionPane.showMessageDialog(null, es.getMessage());
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(badStudent, "Etudiant mauvais (0 à 8)", "Etudiant mauvais (0 à 8)");
            dataset.addValue(averageStudent, "Etudiant moyen (8 à 12)", "Etudiant moyen (8 à 12)");
            dataset.addValue(goodStudent, "Etudiant bon (12 à 17)", "Etudiant bon (12 à 17)");
            dataset.addValue(excellentStudent, "Etudiant excellent (17 à 20)", "Etudiant excellent (17 à 20)");
        return dataset;
    }

    private DefaultPieDataset createPieDataset() {
        int badStudent = 0;
        int averageStudent = 0;
        int goodStudent = 0;
        int excellentStudent = 0;
        //Add item in combobox student
        PersonService ps = new PersonService();
        try {
            for(PersonDTO p : ps.getAll()){
                if(p instanceof StudentDTO) {
                    if(((StudentDTO) p).getAverage() < 8){
                        badStudent++;
                    }else if(((StudentDTO) p).getAverage() >= 8 && ((StudentDTO) p).getAverage() < 12){
                        averageStudent++;
                    }else if(((StudentDTO) p).getAverage() >= 12 && ((StudentDTO) p).getAverage() < 17){
                        goodStudent++;
                    }else if(((StudentDTO) p).getAverage() >= 17){
                        excellentStudent++;
                    }
                }
            }
        } catch (ExceptionService es) {
            JOptionPane.showMessageDialog(null, es.getMessage());
        }

        int total = badStudent + averageStudent + goodStudent + excellentStudent;

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Etudiant mauvais (0 à 8)", (badStudent * 100 / total));
        dataset.setValue("Etudiant moyen (8 à 12)", (averageStudent * 100 / total));
        dataset.setValue("Etudiant bon (12 à 17)", (goodStudent * 100 / total));
        dataset.setValue("Etudiant excellent (17 à 20)", (excellentStudent * 100 / total));
        return dataset;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Gestion d'établissement");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.decode("#343a40"));
    }
}

/**
 * The type Item renderer.
 */
class ItemRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        if (value != null) {
            Item item = (Item) value;
            setText(item.getDescription().toUpperCase());
        }
        if (index == -1) {
            Item item = (Item) value;
            setText("" + item.getId());
        }
        return this;
    }
}

/**
 * The type Student table model.
 */
class StudentTableModel extends AbstractTableModel {
    private Object[][] data;
    private String[] columnNames;
    private int rowIndex;


    /**
     * Instantiates a new Student table model.
     *
     * @param data        the data
     * @param columnNames the column names
     */
    public StudentTableModel(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    /**
     * Returns the number of rows in the table model.
     */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the table model.
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the column name for the column index.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns data type of the column specified by its index.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }



}


/**
 * The type J table button renderer.
 */
class JTableButtonRenderer implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;

    /**
     * Instantiates a new J table button renderer.
     *
     * @param renderer the renderer
     */
    public JTableButtonRenderer(TableCellRenderer renderer) {
        defaultRenderer = renderer;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Component)
            return (Component)value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}




