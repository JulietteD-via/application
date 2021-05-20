package com.example.camelia.ui.camera;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camelia.MainActivity;
import com.example.camelia.R;
import com.example.camelia.ui.camera.CameraViewModel;
import com.example.camelia.ui.plants.PlantsAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraFragment extends Fragment implements CameraAdapter.OnListItemClickListener {

    private CameraViewModel cameraViewModel;
    private RecyclerView cameraRecyclerView;

    String mcurrentPhotoPath;

    private Button btn_take;
    private ImageView imv_main;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cameraViewModel = new ViewModelProvider(this).get(CameraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_camera, container, false);

        btn_take=root.findViewById(R.id.btn_take);
        imv_main=root.findViewById(R.id.imv_main);

        cameraRecyclerView=root.findViewById(R.id.grid_id_results);
        cameraRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        cameraRecyclerView.hasFixedSize();

        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imv_main.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mcurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void identifyPlant() throws Exception {
        String apiKey = "EmjagCO8Arqbwa5oK7EVFi4ebSZE2siHBdUJItQdIku4b8mQs1";

        JSONObject data = new JSONObject();
        data.put("api_key", apiKey);

        JSONArray images = new JSONArray();
        images.put(base64EncodeFromFile("filename"));
        data.put("images", images);

        // add modifiers
        JSONArray modifiers = new JSONArray()
                .put("similar_images");
        data.put("modifiers", modifiers);

        // add language
        data.put("plant_language", "en");

        // add details
        JSONArray plantDetails = new JSONArray()
                .put("common_names");
        data.put("plant_details", plantDetails);

        sendPostRequest("https://api.plant.id/v2/identify", data);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String base64EncodeFromFile(String fileString) throws Exception {
        File file = new File(fileString);
        FileInputStream fis = new FileInputStream(file);
        String res = Base64.getEncoder().encodeToString(fis.readAllBytes());
        fis.close();
        return res;
    }

    public static String sendPostRequest(String urlString, JSONObject data) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();
        os.write(data.toString().getBytes());
        os.close();

        InputStream is = con.getInputStream();
        String response = new String(is.readAllBytes());

        System.out.println("Response code : " + con.getResponseCode());
        System.out.println("Response : " + response);
        con.disconnect();
        return response;
    }


    @Override
    public void onClick(String plant_name) {
        //Search for plants with this name and load Plant Activity

    }
}
