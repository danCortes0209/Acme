/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import static controllers.ControllerProducto.copiarArchivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sax.DBConnection;
import models.ModelUsuario;
import views.ViewUsuario;

/**
 *
 * @author RoseLandjlord
 */
public class ControllerUsuario implements ActionListener {

    private ModelUsuario modelUsuario;
    private ViewUsuario viewUsuario;
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");

    public ControllerUsuario(ModelUsuario modelUsuario, ViewUsuario viewUsuario) {
        this.modelUsuario = modelUsuario;
        this.viewUsuario = viewUsuario;
        this.viewUsuario.jbtnAgregar.addActionListener(this);
        this.viewUsuario.jbtnAnterior.addActionListener(this);
        this.viewUsuario.jbtnEditar.addActionListener(this);
        this.viewUsuario.jbtnEliminar.addActionListener(this);
        this.viewUsuario.jbtnSiguiente.addActionListener(this);
        this.viewUsuario.jbtnUltimo.addActionListener(this);
        this.viewUsuario.jbntPrimero.addActionListener(this);
        this.viewUsuario.jbtnNuevo.addActionListener(this);
        this.viewUsuario.setVisible(true);
        init_view();

    }

    public void init_view() {

        modelUsuario.initValues();

        showValues();

    }

    private void showValues() {
        this.viewUsuario.jtfId.setText("" + modelUsuario.getId_admin());
        this.viewUsuario.jPassword.setText("" + modelUsuario.getContrasena());
        this.viewUsuario.jtfUsuario.setText("" + modelUsuario.getUsuario());
        this.viewUsuario.jtfNombre.setText("" + modelUsuario.getNombre());
        this.viewUsuario.jtfAp_pat.setText("" + modelUsuario.getAp_pat());
        this.viewUsuario.jtfAp_mat.setText("" + modelUsuario.getAp_mat());
        this.viewUsuario.jComboBoxEstado.setSelectedItem("" + modelUsuario.getEstado());
        this.viewUsuario.jComboBoxNivel.setSelectedItem("" + modelUsuario.getNivel());
    }

    public void Guardar() {
        try {
            String id_admin = this.viewUsuario.jtfId.getText();
            String nombre = this.viewUsuario.jtfNombre.getText();
            String ap_pat = this.viewUsuario.jtfAp_pat.getText();
            String ap_mat = this.viewUsuario.jtfAp_mat.getText();
            String usuario = this.viewUsuario.jtfUsuario.getText();
            String contrasena = this.viewUsuario.jPassword.getText();
            this.viewUsuario.jComboBoxEstado.getSelectedItem();
            this.viewUsuario.jComboBoxNivel.getSelectedItem();
            String sql = "insert into admin(nombre,apellido_pat,apellido_mat,usuario,contrasena,nivel,estado) values (" + "'" + nombre + "','" + ap_pat + "','" + ap_mat + "','" + usuario + "','" + contrasena + "','" + this.viewUsuario.jComboBoxNivel.getSelectedItem() + "','" + this.viewUsuario.jComboBoxEstado.getSelectedItem() + "');";

            System.out.println("Nombre " + nombre);
            System.out.println("SQL " + sql);

            conection.executeUpdate(sql);

            conection.executeQuery("Select * from admin");
            Primero();
            showValues();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.viewUsuario, "No hay usuario seleccionado");
        }

    }

    public void Primero() {
        modelUsuario.moveFirst();
        showValues();
    }

    public void Siguiente() {
        modelUsuario.moveNext();
        showValues();

    }

    public void Anterior() {
        modelUsuario.movePrevious();
        showValues();
    }

    public void ultimo() {
        modelUsuario.moveLast();
        showValues();
    }

    public void Eliminar() {
        try {
            String id_admin = this.viewUsuario.jtfId.getText();
            conection.executeUpdate("delete from admin where id_admin=" + id_admin);
            conection.executeQuery("Select * from admin order by id_admin");
            this.viewUsuario.jtfId.setText("");
            this.viewUsuario.jPassword.setText("");
            this.viewUsuario.jtfUsuario.setText("");
            this.viewUsuario.jtfNombre.setText("");
            this.viewUsuario.jtfAp_pat.setText("");
            this.viewUsuario.jtfAp_mat.setText("");
            this.viewUsuario.jComboBoxEstado.setSelectedItem("");
            this.viewUsuario.jComboBoxNivel.setSelectedItem("");
            //this.viewProductos.JlImagen.setIcon(null);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay usuario seleccionado");
        }
    }

    public void Editar() {
        String id_admin = this.viewUsuario.jtfId.getText();
        String nombre = this.viewUsuario.jtfNombre.getText();
        String ap_pat = this.viewUsuario.jtfAp_pat.getText();
        String ap_mat = this.viewUsuario.jtfAp_mat.getText();
        String usuario = this.viewUsuario.jtfUsuario.getText();
        String contrasena = this.viewUsuario.jPassword.getText();
        this.viewUsuario.jComboBoxEstado.getSelectedItem();
        this.viewUsuario.jComboBoxNivel.getSelectedItem();
        try {
            conection.executeUpdate("update admin set nombre='" + nombre + "',apellido_pat='" + ap_pat + "',apellido_mat='" + ap_mat + "',usuario='" + usuario + "',contrasena='" + contrasena + "',nivel='" + this.viewUsuario.jComboBoxNivel.getSelectedItem() + "',estado='" + this.viewUsuario.jComboBoxEstado.getSelectedItem() + "' where id_admin'" + id_admin + "';");
            this.viewUsuario.jtfId.setText("");
            this.viewUsuario.jPassword.setText("");
            this.viewUsuario.jtfUsuario.setText("");
            this.viewUsuario.jtfNombre.setText("");
            this.viewUsuario.jtfAp_pat.setText("");
            this.viewUsuario.jtfAp_mat.setText("");
            this.viewUsuario.jComboBoxEstado.setSelectedItem("");
            this.viewUsuario.jComboBoxNivel.setSelectedItem("");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "No hay usuario seleccionado ");
        }
    }

    public void nuevo() {
        this.viewUsuario.jtfId.setText("");
        this.viewUsuario.jPassword.setText("");
        this.viewUsuario.jtfUsuario.setText("");
        this.viewUsuario.jtfNombre.setText("");
        this.viewUsuario.jtfAp_pat.setText("");
        this.viewUsuario.jtfAp_mat.setText("");
        this.viewUsuario.jComboBoxEstado.setSelectedItem("");
        this.viewUsuario.jComboBoxNivel.setSelectedItem("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.viewUsuario.jbtnAgregar) {
            Guardar();
        } else if (ae.getSource() == this.viewUsuario.jbtnAnterior) {
            Anterior();
        } else if (ae.getSource() == this.viewUsuario.jbtnEditar) {
            Editar();
        } else if (ae.getSource() == this.viewUsuario.jbtnEliminar) {
            Eliminar();
        } else if (ae.getSource() == this.viewUsuario.jbtnSiguiente) {
            Siguiente();
        } else if (ae.getSource() == this.viewUsuario.jbtnUltimo) {
            ultimo();
        } else if (ae.getSource() == this.viewUsuario.jbntPrimero) {
            Primero();
        } else if (ae.getSource() == this.viewUsuario.jbtnNuevo) {
            nuevo();
        }
    }
}
