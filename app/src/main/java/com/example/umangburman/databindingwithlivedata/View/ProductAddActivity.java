package com.example.umangburman.databindingwithlivedata.View;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.umangburman.databindingwithlivedata.Model.Product;
import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.ViewModel.ProductViewModel;
import com.example.umangburman.databindingwithlivedata.databinding.ActivityProductAddBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ProductAddActivity extends AppCompatActivity {

    Bitmap selectedImage;
    ImageView imageView;
    Uri imageData;
    byte[] smallData;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private ActivityProductAddBinding binding;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.initFirebase();
        binding = DataBindingUtil.setContentView(ProductAddActivity.this, R.layout.activity_product_add);

        binding.setLifecycleOwner(this);
        binding.setProductViewModel(productViewModel);

        imageView = findViewById(R.id.imageView);

        productViewModel.getProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {

                if(product.validate()){

                    Toast.makeText(ProductAddActivity.this,"Please fill in the Blanks",Toast.LENGTH_LONG).show();
                }else if(product.validateCategory()){
                        if(productViewModel.is_created()){

                            Toast.makeText(ProductAddActivity.this,"Product Added",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ProductAddActivity.this,ProductListActivity.class);
                            startActivity(intent);
                            finish();
                        }
                }
                else {
                        binding.addCategory.setError("Not Correct Category");
                    }
            }
        });
    }

    public void selectImage(View view){
    //for permissions
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else{
            Intent intenToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intenToGalery,2);
        }
    }
    //for permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intenToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intenToGalery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //after permissions
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){

            imageData = data.getData();
            try {
                if(Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                    Bitmap smallImage = makeSmallerImage(selectedImage,300);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    smallImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    smallData = bytes.toByteArray();

                }else{
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);
                    Bitmap smallImage = makeSmallerImage(selectedImage,300);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    smallImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    smallData = bytes.toByteArray();

                    if (imageData != null) {

                        UUID uuid = UUID.randomUUID();
                        final String imageName = "images/" +uuid + ".jpg";

                        storageReference.child(imageName).putBytes(smallData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                                newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        productViewModel.setUrl(uri.toString());
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProductAddActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public Bitmap makeSmallerImage(Bitmap image, int maximumSize) {

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image,width,height,true);
    }

}
