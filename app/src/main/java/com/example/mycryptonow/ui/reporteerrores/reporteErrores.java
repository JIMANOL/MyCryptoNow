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
import android.widget.SimpleAdapter;
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
import java.util.HashMap;

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
    static ArrayList<String> ListaHrs = new ArrayList<String>();
    //ArrayList<HashMap<String,String>> maps=new ArrayList<>();
    //HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
    String[] from={"mensaje","hora"};//string array
    int[] to={R.id.texto_mensaje,R.id.hora_mensaje};//int array of views id's

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
                ArrayList<HashMap<String,String>> maps=new ArrayList<>();
                //HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
                for(DataSnapshot objSnapshot: snapshot.getChildren()){
                    System.out.println("-->" + FirebaseAuth.getInstance().getCurrentUser().getUid()+"=="+objSnapshot.getKey());
                    if(FirebaseAuth.getInstance().getCurrentUser().getUid().toString().equals(objSnapshot.getKey().toString())){
                        for(DataSnapshot objSnapshot2: objSnapshot.getChildren()){
                            MensajeChat mensaje = new MensajeChat();
                            mensaje.fromSnapshot(objSnapshot2);
                            System.out.println(mensaje.getHora()+"-->" +mensaje.getMensaje());
                            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
                            hashMap.put("mensaje",mensaje.getMensaje());
                            hashMap.put("hora",mensaje.getHora());
                            maps.add(hashMap);//add the hashmap into arrayList

                            SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),maps,R.layout.msjpropio,from,to);//Create object and set the parameters for simpleAdapter
                            ListaMensajes.setAdapter(simpleAdapter);//sets the adapter for listView

                            ListaMsj.add(mensaje.getHora()+"-->" +mensaje.getMensaje());
                            Mensajes.add(mensaje);
                            //ListaMensajes.setSelection(ListaMensajes.getAdapter().getCount()-1);
                            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ListaMsj);
                            //ListaMensajes.setAdapter(adapter);
                        }
                    }
                }
                //SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),maps,R.layout.msjpropio,from,to);//Create object and set the parameters for simpleAdapter
                //ListaMensajes.setAdapter(simpleAdapter);//sets the adapter for listView
            }

            public void onCancelled(@NonNull DatabaseError error){}

        });
        /*
        ArrayList<HashMap<String,String>> maps=new ArrayList<>();
        for (int i=0;i<Mensajes.size();i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
            hashMap.put("mensaje",Mensajes.get(i).getMensaje());
            hashMap.put("hora",Mensajes.get(i).getHora());
            maps.add(hashMap);//add the hashmap into arrayList
        }
        String[] from={"mensaje","hora"};//string array
        int[] to={R.id.texto_mensaje,R.id.hora_mensaje};//int array of views id's
        SimpleAdapter simpleAdapter=new SimpleAdapter(this.getContext(),maps,R.layout.msjpropio,from,to);//Create object and set the parameters for simpleAdapter
        ListaMensajes.setAdapter(simpleAdapter);//sets the adapter for listView
        */
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