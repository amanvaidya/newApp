package com.audit_new.scanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.audit_new.scanner.encryption.EncrypterDecrypter;
import com.audit_new.scanner.service.DAO.LoginDAO;
import com.audit_new.scanner.service.pojo.LoginResult;
import com.audit_new.scanner.service.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtLoginName;
    EditText edtPassword;
    EditText urlEt;
    Button btnLogin;
    ProgressBar progressBar;
    SharedPreferences sp, pref;
    SharedPreferences.Editor editor;
    private String secretKey = "RCS19082019#1$@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        editor = pref.edit();

        ApiUtils.BASE_URL = sp.getString("BASE_URL", "");

        if (sp.getBoolean("logged", false)) {
            Intent intent = new Intent(LoginActivity.this, home.class);

            startActivity(intent);
        }
        edtLoginName = findViewById(R.id.editText);
        edtPassword = findViewById(R.id.editText2);
        urlEt = findViewById(R.id.domain_name);
        btnLogin = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_name = edtLoginName.getText().toString();
                String password = edtPassword.getText().toString();
                String url = urlEt.getText().toString();

                if (validateLogin(login_name, password, url)) {
                    ApiUtils.BASE_URL = url;
                    sp.edit().putString("BASE_URL", url).apply();
                    doLogin(login_name, password);
                }
            }
        });

    }

    private boolean validateLogin(String login_name, String password, String url) {
        if (url == null || url.trim().length() == 0 || !isValid(url)) {
            Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return false;
        }
        if (!checkSlash(url)) {
            Toast.makeText(this, "URL provided must end with slash", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return false;
        }

        if (login_name == null || login_name.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return false;
        }
        return true;
    }

    private boolean isValid(String urlString) {
        return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
    }

    private boolean checkSlash(String url) {
        return url.substring(url.length() - 1).equalsIgnoreCase("/");
    }

    private void doLogin(final String login_name, final String password) {
        String encLoginName = EncrypterDecrypter.encrypt(login_name, secretKey);
        String encPassword = EncrypterDecrypter.encrypt(password, secretKey);
        progressBar.setVisibility(View.VISIBLE);
        LoginDAO userService = ApiUtils.getUserService();
        Call<LoginResult> call = userService.login(encLoginName, encPassword);

        try {
            call.enqueue(new Callback<LoginResult>() {

                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    LoginResult resObj = response.body();

                    if (resObj == null) {
                        progressBar.setVisibility(View.GONE);
                        ApiUtils.BASE_URL = "";
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String emp_name = resObj.getEmp_name();
                    String emp_id = resObj.getEmp_id();
                    String auth_token = resObj.getAuth_code();

                    if (!emp_name.equals("") && !emp_name.equals(null)) {
                        Intent intent = new Intent(LoginActivity.this, home.class);
                        sp.edit().putBoolean("logged", true).apply();
                        editor.putString("username", login_name);
                        editor.putString("emp_name", emp_name);
                        editor.putString("emp_id", emp_id);
                        editor.putString("auth_token", auth_token);
                        editor.commit();
                        progressBar.setVisibility(View.GONE);
                        startActivity(intent);

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    t.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    ApiUtils.BASE_URL = "";
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}