package com.crystal.mundomascota.Vista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.mundomascota.R;
import com.crystal.mundomascota.Modelo.common.Utilidades;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarComentarioActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTituloActionBarHard;
    EditText etNombreContacto, etEmailContacto, etMensajeContacto;
    String nombre, email, mensaje;
    Button btnEnviar;
    Properties properties;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_comentario);
        Utilidades.ocultarBarraEstado(getWindow());

        inicializar();
        eventos();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void eventos() {
        btnEnviar.setOnClickListener(this);
    }

    private void inicializar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.ActionBarContacto);
        setSupportActionBar(myToolbar);

        tvTituloActionBarHard = findViewById(R.id.tvTituloActionBarHard);
        tvTituloActionBarHard.setText(getResources().getString(R.string.menu_contacto));

        etNombreContacto = findViewById(R.id.etNombreContacto);
        etEmailContacto = findViewById(R.id.etEmailContacto);
        etMensajeContacto = findViewById(R.id.etMensajeContacto);
        btnEnviar = findViewById(R.id.btnEnviar);

        etNombreContacto.requestFocus();
        properties = new Properties();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnEnviar){
            try {
                validarDatos();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void validarDatos() throws UnsupportedEncodingException, MessagingException {
        consultarEditText();
        if(nombre.isEmpty()){
           etNombreContacto.setError(getResources().getString(R.string.nombre_invalido));
        }else if(email.isEmpty()){
            etEmailContacto.setError(getResources().getString(R.string.email_invalido));
        }else if(!email.contains("@")){
            etEmailContacto.setError(getResources().getString(R.string.email_invalido_arr));
        }else if(mensaje.isEmpty()){
            etMensajeContacto.setError(getResources().getString(R.string.mensaje_invalido));
        }else{
            sendEmail();
        }
    }

    public void sendEmail() throws MessagingException {
        propiedades();

        MimeMessage mimeMessage = new MimeMessage(session);

        mimeMessage.setText(crearMensaje());
        mimeMessage.setFrom(new InternetAddress(Utilidades.CORREO_ENVIA));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Utilidades.CORREO_ENVIA));
        mimeMessage.setSubject(getResources().getString(R.string.correo_subject));

        Transport transport = session.getTransport("smtp");
        transport.connect(properties.getProperty("mail.user"), properties.getProperty("mail.password"));
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();

        mensajeSimpleDialog(getResources().getString(R.string.exito), getResources().getString(R.string.correo_enviado_exito));
    }

    private String crearMensaje() {
        return "Mi nombre es: "+nombre+"\n" +
                "Correo: "+email+"\n"+
                "Mensaje: "+mensaje;
    }

    private void propiedades() {
        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", Utilidades.CORREO_ENVIA);
        properties.put("mail.password", Utilidades.CONTRASENA_ENVIA);

        session = Session.getInstance(properties, null);
    }

    private void consultarEditText() {
        nombre = etNombreContacto.getText().toString();
        email = etEmailContacto.getText().toString();
        mensaje = etMensajeContacto.getText().toString();
    }

    public void mensajeSimpleDialog(String titulo, String msj){
        int icon = R.drawable.msj_alert_30;
        if (titulo.equals(getResources().getString(R.string.error))){
            icon = R.drawable.msj_error_30;
        } else if(titulo.equals(getResources().getString(R.string.exito))){
            icon = R.drawable.msj_exito_30;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(EnviarComentarioActivity.this);
        builder.setTitle(titulo)
                .setCancelable(false)
                .setMessage(msj)
                .setIcon(icon)
                .setPositiveButton("Aceptar", (dialog, which) ->
                {
                    if(titulo.equals(getResources().getString(R.string.exito))){
                        etNombreContacto.setText("");
                        etEmailContacto.setText("");
                        etMensajeContacto.setText("");
                        etNombreContacto.requestFocus();
                    }
                    dialog.dismiss();
                });
        AlertDialog alerta = builder.create();
        alerta.show();
    }
}