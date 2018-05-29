package projetofinal.celer.com.br.projetofinal;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaDeCadastroActivity extends Activity {
    EditText edtUsuario, edtEmail, edtSenha;
    Button btnSalvar, btnCancelar;
    CadastroDAO dao;

    final int MENU_SALVAR = 1;
    final int MENU_BUSCAR = 2;
    final int MENU_ALTERAR = 3;
    final int MENU_EXCLUIR = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_cadastro);

        dao = new CadastroDAO(this);


        edtUsuario = findViewById(R.id.telaDeCadastro_edtNome);
        edtEmail = findViewById(R.id.telaDeCadastro_edtEmail);
        edtSenha = findViewById(R.id.telaDeCadastro_edtSenha);

        Intent it = getIntent();
        String usuario = it.getStringExtra(Cadastro.USUARIO);


        btnSalvar = findViewById(R.id.telaDeCadastro_btnSalvar);
        btnCancelar = findViewById(R.id.telaDeCadastro_btnCancelar);

    }
    // pega o evento do botão cancelar e fecha a tela
    public void cancelar (View view){
        finish();
    }


public void salvar (View view){
    Cadastro cadastro= new Cadastro();

    cadastro.setUsuario(edtUsuario.getText().toString());
    cadastro.setEmail(edtEmail.getText().toString());
    cadastro.setSenha(edtSenha.getText().toString());

    dao.salvar(cadastro);
    Log.i("appmain","passou salvar" );
    finish();
}
    public void buscarCadatro(){

       Cadastro cadastro = dao.buscar(edtUsuario.getText().toString());

        edtUsuario.setText(cadastro.getUsuario());
       edtEmail.setText(cadastro.getEmail());
       edtSenha.setText(cadastro.getSenha());

    }




    @Override
    protected void onResume() {
        super.onResume();
        Log.i("appmain","passou resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("appmain","passou start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("appmain","passou pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("appmain","passou stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("appmain","passou destroy");
    }
}

