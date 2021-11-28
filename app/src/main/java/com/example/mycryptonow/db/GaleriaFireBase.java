package com.example.mycryptonow.db;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.mycryptonow.interfaces.Respuesta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GaleriaFireBase {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;
    private StorageReference mountainsRef;

    public GaleriaFireBase() {
        storageRef = storage.getReference();

    }

    public void subirImagen(Bitmap imagen, String nombreImagen, Respuesta respuesta){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        String path = FirebaseAuth.getInstance().getCurrentUser().getUid() +"/"+ nombreImagen;

        mountainsRef = storageRef.child(path);

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                respuesta.respuesta(null);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                respuesta.respuesta(new Object());
            }
        });
    }




}
