package com.example.sangil.testrecipe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by sangil on 2015-12-03.
 */
public class Tab3 extends Fragment {

    private static final int REQ_CODE_PICK_IMAGE = 0;
    private static final String TEMP_PHOTO_FILE = "temp.jpg";
    Context mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "사진첩", Toast.LENGTH_SHORT)
                .show();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlayout_03, container, false);
        ImageButton plus_Button = (ImageButton) view.findViewById(R.id.plus_button);
        ImageButton upload_Button = (ImageButton) view.findViewById(R.id.upload_button);
        plus_Button.setOnClickListener(mClickListener);
        upload_Button.setOnClickListener(mClickListener);
        return view;
    }

    public Button.OnClickListener mClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.plus_button:
                    Toast.makeText(getActivity(), "추가하기", Toast.LENGTH_SHORT)
                            .show();
                    AlertDialog dialog = createDialogBox( v);
                    dialog.show();
                    break;
                case R.id.upload_button:
                    Toast.makeText(getActivity(), "업로드", Toast.LENGTH_SHORT)
                            .show();
                    AlertDialog dialog2 = createDialogBox2(v);
                    dialog2.show();
                    break;

            }
        }
    };
    @Override
    public void onPause() {
        super.onPause();
    }

    private AlertDialog createDialogBox(View view) {

        final CharSequence[] items = {"앨범에서 가져오기", "사진찍기"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());     // 여기서 this는 Activity의 this
        builder.setTitle("추가하기")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index) {
                            case 0:
                                Toast.makeText(getActivity(), "앨범에서 가져오기", Toast.LENGTH_SHORT)
                                        .show();
                                Intent intent = new Intent(
                                        Intent.ACTION_GET_CONTENT,      // 또는 ACTION_PICK
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");              // 모든 이미지
                                intent.putExtra("crop", "true");        // Crop기능 활성화
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());     // 임시파일 생성
                                intent.putExtra("outputFormat",         // 포맷방식
                                        Bitmap.CompressFormat.JPEG.toString());
                                Log.d("여기", "왔다");
                                startActivityForResult(intent, REQ_CODE_PICK_IMAGE);
                                Log.d("여기", "왔다2");
                                dialog.cancel();
                                break;

                            case 1:
                                Toast.makeText(getActivity(), "사진찍기", Toast.LENGTH_SHORT)
                                        .show();
                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                camera.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(camera);

                                startActivityForResult(camera, REQ_CODE_PICK_IMAGE);
                                dialog.dismiss();

                                break;

                        };
                    }
                });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
        return dialog;
    }

    private Uri getTempUri() {
        return Uri.fromFile(getTempFile());
    }
    private File getTempFile() {
        if (isSDCARDMOUNTED()) {
            File f = new File(Environment.getExternalStorageDirectory(), // 외장메모리 경로
                    TEMP_PHOTO_FILE);
            try {
                f.createNewFile();      // 외장메모리에 temp.jpg 파일 생성
            } catch (IOException e) {
            }

            return f;
        } else
            return null;
    }

    private boolean isSDCARDMOUNTED() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED))
            return true;

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageData) {
        Log.d("여기", "왔다3");
        super.onActivityResult(requestCode, resultCode, imageData);
        Toast.makeText(getActivity(), "앨범나오기", Toast.LENGTH_SHORT)
                .show();
        Log.d("여기", "왔다4");

        switch (requestCode) {
            case REQ_CODE_PICK_IMAGE:
                if (resultCode == getActivity().RESULT_OK) {
                    if (imageData != null) {
                        String filePath = Environment.getExternalStorageDirectory()
                                + "/temp.jpg";

                        System.out.println("path" + filePath); // logCat으로 경로확인.
                        ImageView _image = (ImageView) getActivity().findViewById(R.id.image1);
                        Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

                        // temp.jpg파일을 Bitmap으로 디코딩한다.
                        Bitmap resized = Bitmap.createScaledBitmap(selectedImage, 100, 100, true);
                        _image.setImageBitmap(resized);


                        _image.setScaleType(ImageView.ScaleType.FIT_XY);
                        _image.setImageBitmap(selectedImage);
                        // temp.jpg파일을 이미지뷰에 씌운다.
                    }
                }
                break;
        }
    }

    private AlertDialog createDialogBox2(View view) {

        final CharSequence[] items = {"Instargram에 업로드", "Facebook에 업로드"};
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());     // 여기서 this는 Activity의 this
        builder.setTitle("업로드")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener() {    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index) {
                        switch (index) {
                            case 0:
                                Toast.makeText(getActivity(), "Instargram에 업로드", Toast.LENGTH_SHORT)
                                        .show();
                                dialog.cancel();
                                break;

                            case 1:
                                Toast.makeText(getActivity(), "Facebook에 업로드", Toast.LENGTH_SHORT)
                                        .show();
                                dialog.dismiss();
                                break;
                        };
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
        return dialog;
    }
}

