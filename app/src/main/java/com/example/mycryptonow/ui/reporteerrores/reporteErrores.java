package com.example.mycryptonow.ui.reporteerrores;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mycryptonow.R;
import com.example.mycryptonow.db.Realtime;
import com.example.mycryptonow.interfaces.Respuesta;
import com.example.mycryptonow.models.Ingresos;
import com.example.mycryptonow.models.MensajeChat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class reporteErrores extends Fragment implements View.OnClickListener {

    private ReporteErroresViewModel mViewModel;
    private ImageButton btnENVIAR;
    ListView ListaMensajes;
    private EditText etMensaje;
    private Realtime bd = new Realtime();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DateFormat formato;
    Calendar cal;

    static ArrayList<MensajeChat> Mensajes = new ArrayList<MensajeChat>();
    static ArrayList<String> ListaMsj = new ArrayList<String>();

    public static reporteErrores newInstance() {
        return new reporteErrores();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.reporte_errores_fragment, container, false);
        ButtonComponentes(root);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        formato = new SimpleDateFormat("HH:mm:ss");
        cal = Calendar.getInstance();
        displayChatMessages();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReporteErroresViewModel.class);
        // TODO: Use the ViewModel
    }

    private void ButtonComponentes(View root){
        btnENVIAR = root.findViewById(R.id.btn_enviar_user);
        ListaMensajes =  root.findViewById(R.id.chat_errores_user);
        etMensaje = root.findViewById(R.id.etMensajes);
        btnENVIAR.setOnClickListener(this);
    }

    private void displayChatMessages() {
        databaseReference.child("mensajes_chat_errores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                ListaMsj.clear();
                Mensajes.clear();
                for(DataSnapshot objSnapshot: snapshot.getChildren()){
                    System.out.println("-->" + FirebaseAuth.getInstance().getCurrentUser().getUid()+"=="+objSnapshot.getKey());
                    if(FirebaseAuth.getInstance().getCurrentUser().getUid().toString().equals(objSnapshot.getKey().toString())){
                        for(DataSnapshot objSnapshot2: objSnapshot.getChildren()){
                            MensajeChat mensaje = new MensajeChat();
                            mensaje.fromSnapshot(objSnapshot2);
                            ListaMsj.add(mensaje.getHora()+"-->" +mensaje.getMensaje());
                            Mensajes.add(mensaje);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ListaMsj);
                            ListaMensajes.setAdapter(adapter);
                        }
                    }
                }

            }

            public void onCancelled(@NonNull DatabaseError error){}

        });
    }

    private void mLimpiar(){
        etMensaje.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enviar_user:
                if (etMensaje.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Escribe un Mensaje", Toast.LENGTH_LONG).show();

                } else {
                    String msj = etMensaje.getText().toString();
                    String hora = formato.format(cal.getTime());
                    MensajeChat NuevoMensaje = new MensajeChat(msj, FirebaseAuth.getInstance().getCurrentUser().getUid(),hora);
                    databaseReference.child("mensajes_chat_errores").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(NuevoMensaje);
                    displayChatMessages();
                    mLimpiar();
                }
                break;

        }
    }
}