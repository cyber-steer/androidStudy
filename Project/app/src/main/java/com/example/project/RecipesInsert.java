package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.manager.DbConect;
import com.example.project.manager.SessionManager;

import java.util.ArrayList;

public class RecipesInsert extends AppCompatActivity {
    LinearLayout meterial, formalities;
    ArrayList<Integer[]> meterialId;
    ArrayList<Integer> formalitiesId;
    Button btnLogout, btnSave, btnCancle, btnMeterialAdd, btnMeterialRemove,btnFormalitiesRemove,btnFormalitiesAdd;
    LinearLayout memberLayout, nonMemberLayout, toolbarLayout;
    TextView toolbarName, userName;
    EditText etBase, etVoluem, etBaseVoluem, etFormalities, etRecipesName,etRecipesProof, etRecipesContent;
    DrawerLayout drawerLayout;
    Spinner spinner;
    String base;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_insert);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbarName = findViewById(R.id.toolbarName);
        toolbarName.setText("레시피");
        toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.removeViewAt(3);
        toolbarLayout.removeViewAt(2);
        toolbarName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        sessionManager = new SessionManager(getApplicationContext());

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(
            this,R.array.bases_array, android.R.layout.simple_spinner_dropdown_item
        );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                base =adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etRecipesName = findViewById(R.id.etRecipesName);
        etRecipesContent = findViewById(R.id.etRecipesContent);
        etBase = findViewById(R.id.etBase);
        etVoluem = findViewById(R.id.etVoluem);
        etBaseVoluem = findViewById(R.id.etBaseVoluem);
        etFormalities = findViewById(R.id.etFormalities);
        etRecipesProof = findViewById(R.id.etRecipesProof);

        meterialId = new ArrayList<>();
        formalitiesId = new ArrayList<>();

        meterial = findViewById(R.id.meterial);
        formalities = findViewById(R.id.formalities);

        btnMeterialAdd = findViewById(R.id.btnMeterialAdd);
        btnMeterialAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) createLinearLayoutMeterial(view.getContext(), (LinearLayout) meterial.getChildAt(meterial.getChildCount()-1));
                meterial.addView(linearLayout);
            }
        });
        btnMeterialRemove = findViewById(R.id.btnMeterialRemove);
        btnMeterialRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(meterial.getChildCount()>2){
                    LinearLayout original =  (LinearLayout) meterial.getChildAt(meterial.getChildCount()-1);
                    Button originalRemove = (Button) original.getChildAt(2);
                    Button originalAdd = (Button) original.getChildAt(3);
                    original.removeViewAt(3);
                    original.removeViewAt(2);
                    LinearLayout linearLayout = (LinearLayout) meterial.getChildAt(meterial.getChildCount()-2);
                    linearLayout.addView(originalRemove);
                    linearLayout.addView(originalAdd);
                    meterialId.remove(meterialId.size()-1);
                    meterial.removeViewAt(meterial.getChildCount()-1);
                }
                else{
                    Toast.makeText(RecipesInsert.this, "최소한 하나이상은 입력해야합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFormalitiesAdd = findViewById(R.id.btnFormalitiesAdd);
        btnFormalitiesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) createLinearLayoutFormalities(view.getContext(),(LinearLayout) formalities.getChildAt(formalities.getChildCount()-1));
                formalities.addView(linearLayout);
            }
        });
        btnFormalitiesRemove = findViewById(R.id.btnFormalitiesRemove);
        btnFormalitiesRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formalities.getChildCount()>2){
                    LinearLayout original =  (LinearLayout) formalities.getChildAt(formalities.getChildCount()-1);
                    Button originalRemove = (Button) original.getChildAt(2);
                    Button originalAdd = (Button) original.getChildAt(3);
                    original.removeViewAt(3);
                    original.removeViewAt(2);
                    LinearLayout linearLayout = (LinearLayout) formalities.getChildAt(formalities.getChildCount()-2);
                    linearLayout.addView(originalRemove);
                    linearLayout.addView(originalAdd);
                    formalitiesId.remove(formalitiesId.size()-1);
                    formalities.removeViewAt(formalities.getChildCount()-1);
                }
                else{
                    Toast.makeText(RecipesInsert.this, "최소한 하나이상은 입력해야합니다", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meterialStr = etBase.getText().toString().trim();
                String voluemStr = etBaseVoluem.getText().toString().trim()+"-"+etVoluem.getText().toString().trim();
                String formalitiesStr=etFormalities.getText().toString().trim();
                String recipesName = etRecipesName.getText().toString().trim();
                String recipesContent = etRecipesContent.getText().toString().trim();
                String proof = etRecipesProof.getText().toString().trim();

                String check="true";
                if(meterialStr.equals("")||voluemStr.equals("")||formalitiesStr.equals("")||recipesName.equals("")||recipesContent.equals("")||proof.equals("")){
                    check="false";
                }

                for(int i=0;i<meterialId.size();i++){
                    meterialStr += "-";
                    EditText editText = findViewById(meterialId.get(i)[0]);
                    String str = editText.getText().toString();
                    if (str.equals("")){
                        check = "false";
                    }
                    meterialStr += str;
                }
                for(int i=0;i<meterialId.size();i++){
                    voluemStr += "-";
                    EditText editText = findViewById(meterialId.get(i)[1]);
                    String str = editText.getText().toString();
                    if (str.equals("")){
                        check = "false";
                    }
                    voluemStr += str;
                }
                for(int i=0;i<formalitiesId.size();i++){
                    formalitiesStr += "-";
                    EditText editText = findViewById(formalitiesId.get(i));
                    String str = editText.getText().toString();
                    if (str.equals("")){
                        check = "false";
                    }
                    formalitiesStr += str;
                }
                if(base.equals("베이스를 선택하세요")){
                    check = "nobase";
                }
                if(check.equals("false")){
                    Toast.makeText(RecipesInsert.this, "비어있는 입력이 있습니다", Toast.LENGTH_SHORT).show();
                }
                else if(check.equals("nobase")){
                    Toast.makeText(RecipesInsert.this, "베이스를 선택하세요", Toast.LENGTH_SHORT).show();

                }
                else{
                    String msg = recipesName+","+proof+","+recipesContent+","+meterialStr+","+voluemStr+","+formalitiesStr+","+base;

                    DbConect conect = new DbConect();
                    String result="";
                    try{
                        result = conect.execute("insertRecipes","recipecontent",msg).get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    if(result.equals("faill")){
                        Toast.makeText(RecipesInsert.this, "저장하지 못했습니다", Toast.LENGTH_SHORT).show();
                    }
                    else if(result.equals("error")){
                        Toast.makeText(RecipesInsert.this, "에러발생", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //정상 처리 결과
                        startActivity(new Intent(getApplicationContext(), Recipes.class));
                    }


                }
                System.out.println("1st : "+meterialStr);
                System.out.println("2st : "+voluemStr);
                System.out.println("3st : "+formalitiesStr);
                System.out.println(recipesName);
                System.out.println(recipesContent);
                System.out.println(recipesName+","+recipesContent+","+ meterialStr +","+voluemStr+","+formalitiesStr);
            }
        });

        btnCancle = findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Recipes.class));
            }
        });

        userName = findViewById(R.id.nickName);
        memberLayout = findViewById(R.id.memberLayout);
        nonMemberLayout = findViewById(R.id.nonMemberLayout);
        if(sessionManager.getLogin()){
            memberLayout.setVisibility(View.VISIBLE);
            nonMemberLayout.setVisibility(View.INVISIBLE);
            btnLogout = findViewById(R.id.btnLogout);
            userName.setText(sessionManager.getNickName());
        }else{
            memberLayout.setVisibility(View.INVISIBLE);
            nonMemberLayout.setVisibility(View.VISIBLE);
        }

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("로그아웃");
                builder.setMessage("정말 로그아웃하시겠습니까?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sessionManager.setLogin(false);
                        sessionManager.setId("");
                        sessionManager.setPwd("");
                        sessionManager.setNickName("");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    @SuppressLint("ResourceType")
    public View createLinearLayoutMeterial(Context context, LinearLayout original){
        LinearLayout parent = (LinearLayout) original.getParent();
        LinearLayout linearLayout = new LinearLayout(context);
        EditText name = new EditText(context);
        EditText voluem = new EditText(context);

        Button originalRemove = (Button) original.getChildAt(2);
        Button originalAdd = (Button) original.getChildAt(3);
        original.removeViewAt(3);
        original.removeViewAt(2);

        int nameWidth = original.getChildAt(0).getLayoutParams().width;
        int voluemWidth = original.getChildAt(1).getLayoutParams().width;

        int leftPadding = original.getChildAt(0).getPaddingLeft();
        int topPadding = original.getChildAt(0).getPaddingTop();
        int rightPadding = original.getChildAt(0).getPaddingRight();
        int bottomPadding = original.getChildAt(0).getPaddingBottom();

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        name.setId(10+parent.getChildCount()-2);
        name.setHint("재료");
        name.setTextSize(16);
        name.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        name.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_outline_rectangle));

        voluem.setId(20+parent.getChildCount()-2);
        voluem.setHint("용량(ml)");
        voluem.setTextSize(16);
        voluem.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        voluem.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_outline_rectangle));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(original.getLayoutParams());
        params.topMargin = 24;
        linearLayout.setLayoutParams(params);

        LinearLayout.LayoutParams nameParam = (new LinearLayout.LayoutParams(nameWidth, params.height));
        nameParam.rightMargin = 24;
        name.setLayoutParams(nameParam);

        LinearLayout.LayoutParams voluemParam = (new LinearLayout.LayoutParams(voluemWidth, params.height));
        voluemParam.rightMargin = 24;
        voluem.setLayoutParams(voluemParam);

        linearLayout.addView(name);
        linearLayout.addView(voluem);
        linearLayout.addView(originalRemove);
        linearLayout.addView(originalAdd);

        Integer[] id = new Integer[2];
        id[0] = name.getId();
        id[1] = voluem.getId();

        meterialId.add(id);

        return linearLayout;
    }
    public LinearLayout createLinearLayoutFormalities(Context context, LinearLayout original){
        LinearLayout parent = (LinearLayout) original.getParent();
        LinearLayout linearLayout = new LinearLayout(context);
        TextView textView = new TextView(context);
        EditText editText = new EditText(context);

        int tvWidth = original.getChildAt(0).getLayoutParams().width;
        int tvHeight = original.getChildAt(0).getLayoutParams().height;
        int width = original.getChildAt(1).getLayoutParams().width;
        int height = original.getChildAt(1).getLayoutParams().height;
        int leftPadding = original.getChildAt(1).getPaddingLeft();
        int topPadding = original.getChildAt(1).getPaddingTop();
        int rightPadding = original.getChildAt(1).getPaddingRight();
        int bottomPadding = original.getChildAt(1).getPaddingBottom();

        Button originalRemove = (Button) original.getChildAt(2);
        Button originalAdd = (Button) original.getChildAt(3);
        original.removeViewAt(3);
        original.removeViewAt(2);

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(original.getLayoutParams());
        params.topMargin = 24;
        linearLayout.setLayoutParams(params);

        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(tvWidth,tvHeight);
        textParam.rightMargin = 24;
        textView.setLayoutParams(textParam);
        textView.setText((parent.getChildCount())+"");
        textView.setTextSize(16);

        editText.setId(30+parent.getChildCount()-2);
        editText.setHint(parent.getChildCount()+"번째 순서");
        editText.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_outline_rectangle));
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(width,height);
        editParams.rightMargin = 24;
        editText.setLayoutParams(editParams);
        editText.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);



        linearLayout.addView(textView);
        linearLayout.addView(editText);
        linearLayout.addView(originalRemove);
        linearLayout.addView(originalAdd);

        formalitiesId.add(editText.getId());

        return linearLayout;
    }


    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickRecipes(View view){
        MainActivity.redirectActivity(this,Recipes.class);
    }
    public void ClickFavorite(View view){
        if(sessionManager.getLogin()){
            MainActivity.redirectActivity(this,Favorite.class);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("로그인");
            builder.setMessage("즐겨찾기는 로그인을 해야 이용가능합니다 로그인 하겠습니까?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    public void ClickSignUp(View view){
        MainActivity.redirectActivity(this,SignUp.class);
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
    }
    public void ClickSignIn(View view){
        MainActivity.redirectActivity(this,SignIn.class);
    }

    public void ClickBoard(View view){
        recreate();
    }
    public void ClickInfo(View view){
        MainActivity.redirectActivity(this, Info.class);
    }

    public void ClickExit(View view){
        MainActivity.exit(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}