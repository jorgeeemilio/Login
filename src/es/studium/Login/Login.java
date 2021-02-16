package es.studium.Login;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login implements WindowListener, ActionListener
{
	Frame ventanaLogin = new Frame("Login");
	Dialog dialogoLogin = new Dialog(ventanaLogin, "Error", true); // Es modal
	
	Label lblUsuario = new Label("Usuario:");
	Label lblClave = new Label("Clave:");
	Label lblError = new Label("Credenciales Incorrectas!!!!");
	TextField txtUsuario = new TextField(20);
	TextField txtClave = new TextField(20);
	Button btnAceptar = new Button("Acceder");
	Button btnLimpiar = new Button("Limpiar");
	
	public Login()
	{
		ventanaLogin.setLayout(new FlowLayout());
		
		ventanaLogin.add(lblUsuario);
		ventanaLogin.add(txtUsuario);
		ventanaLogin.add(lblClave);
		txtClave.setEchoChar('*');
		ventanaLogin.add(txtClave);
		btnAceptar.addActionListener(this);
		ventanaLogin.add(btnAceptar);
		btnLimpiar.addActionListener(this);
		ventanaLogin.add(btnLimpiar);
		ventanaLogin.addWindowListener(this);
		
		ventanaLogin.setSize(250,125);
		ventanaLogin.setLocationRelativeTo(null);
		ventanaLogin.setResizable(false);
		ventanaLogin.setVisible(true);
	}

	public static void main(String[] args)
	{
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent botonPulsado)
	{
		if(botonPulsado.getSource().equals(btnLimpiar))
		{
			txtUsuario.selectAll();
			txtUsuario.setText("");
			txtClave.selectAll();
			txtClave.setText("");
			txtUsuario.requestFocus();
		}
		else if(botonPulsado.getSource().equals(btnAceptar))
		{
			// Conectar BD
			// Buscar lo que el usuario ha escrito en los TextField
			// Si existe en la BD, mostrar Menú Principal
			// Si no existe en la BD, mostrar Diálogo de Error
			// Desconectar la BD
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{}

	@Override
	public void windowClosed(WindowEvent arg0)
	{}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}

	@Override
	public void windowIconified(WindowEvent arg0)
	{}

	@Override
	public void windowOpened(WindowEvent arg0)
	{}
}
