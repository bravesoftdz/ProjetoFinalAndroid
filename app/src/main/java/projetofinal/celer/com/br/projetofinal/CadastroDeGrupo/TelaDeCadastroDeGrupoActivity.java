package projetofinal.celer.com.br.projetofinal.CadastroDeGrupo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import projetofinal.celer.com.br.projetofinal.CadastroDeLogin.TelaDeCadastroActivity;
import projetofinal.celer.com.br.projetofinal.R;

public class TelaDeCadastroDeGrupoActivity extends Activity {
    EditText edtId, edtNomeDoGrupo, edtMonitorResponsavel,edtLocalDeAtuacao, edtDecricaoAtividades;
    TextView tvCadastrarUsuario;
    CadastroDeGrupoDAO dao;

    final int MENU_SALVAR = 1;
    final int MENU_BUSCAR = 2;
    final int MENU_ALTERAR = 3;
    final int MENU_EXCLUIR = 4;
    final int MENU_LISTAR = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_cadastro_de_grupo);

        dao = new CadastroDeGrupoDAO(this);

        edtId = findViewById(R.id.telaDeCadastroDeGrupo_edtId);
        edtNomeDoGrupo = findViewById(R.id.TelaDeCadastroDeGrupo_edtNomeDoGrupo);
        edtMonitorResponsavel = findViewById(R.id.telaDeCadastroDeGrupo_edtMonitorResponsavel);
        edtLocalDeAtuacao = findViewById(R.id.telaDeCadastroDeGrupo_edtLocalDeAtuacao);
        edtDecricaoAtividades = findViewById(R.id.telaDeCadastroDeGrupo_edtDescricaoDeAtividades);


        Intent it = getIntent();
        Long id = it.getLongExtra(CadastroDeGrupo.ID,0);

        if (id!=0){
            edtId.setText(String.valueOf(id));
            buscarGrupo();
        }

        tvCadastrarUsuario = findViewById(R.id.telaDeCadastroDeGrupo_tvCadastrarUsuaio);
        tvCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it;
                it = new Intent(TelaDeCadastroDeGrupoActivity.this,TelaDeCadastroActivity.class);
                startActivity(it);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_SALVAR,0,R.string.menu_salvar);
        menu.add(0,MENU_BUSCAR,0,R.string.menu_buscar);
        menu.add(0,MENU_ALTERAR,0,R.string.menu_alterar);
        menu.add(0,MENU_EXCLUIR,0,R.string.menu_excluir);
        menu.add(0,MENU_LISTAR,0,R.string.menu_listar);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(this, "Voce licou em salvar!!", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 2) {
            Toast.makeText(this, "Voce Clicou em buscar!!", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 3) {
            Toast.makeText(this, "Voce Clicou em alterar!!", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 4) {
            Toast.makeText(this, "Voce Clicou em excluir!!", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 5) {
            Toast.makeText(this, "Voce Clicou em listar!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_SALVAR:
                salvarGrupo();
                break;

            case MENU_BUSCAR:
                buscarGrupo();
                break;

            case MENU_ALTERAR:
                alterarGrupo();
                break;

            case MENU_EXCLUIR:
                excluirGrupo();
                break;

            case MENU_LISTAR:
                listar();
                break;

        }
        return true;
    }
    public void salvarGrupo(){
        CadastroDeGrupo cadastroDeGrupo = new CadastroDeGrupo();

        cadastroDeGrupo.setNomeDoGrupo(edtNomeDoGrupo.getText().toString());
        cadastroDeGrupo.setMonitorResponsavel(edtMonitorResponsavel.getText().toString());
        cadastroDeGrupo.setLocalDeAtuacao(edtLocalDeAtuacao.getText().toString());
        cadastroDeGrupo.setDescricaoDasAtividades(edtDecricaoAtividades.getText().toString());


        dao.salvar(cadastroDeGrupo);
        finish();
        Log.i("appmain","passou salvar");
        Log.i("lista",cadastroDeGrupo.getMonitorResponsavel());
    }
    public void buscarGrupo(){

        CadastroDeGrupo cadastroDeGrupo = dao.buscar(edtId.getText().toString());

        edtNomeDoGrupo.setText(cadastroDeGrupo.getNomeDoGrupo());
        edtMonitorResponsavel.setText(cadastroDeGrupo.getMonitorResponsavel());
        edtLocalDeAtuacao.setText(cadastroDeGrupo.getLocalDeAtuacao());
        edtDecricaoAtividades.setText(cadastroDeGrupo.getDescricaoDasAtividades());


        Log.i("appmain","passou buscar");

    }
    public void alterarGrupo(){
        CadastroDeGrupo cadastroDeGrupo = new CadastroDeGrupo();

        cadastroDeGrupo.setNomeDoGrupo(edtNomeDoGrupo.getText().toString());
        cadastroDeGrupo.setMonitorResponsavel(edtMonitorResponsavel.getText().toString());
        cadastroDeGrupo.setLocalDeAtuacao(edtLocalDeAtuacao.getText().toString());
        cadastroDeGrupo.setDescricaoDasAtividades(edtDecricaoAtividades.getText().toString());



        dao.alterar(cadastroDeGrupo);
        finish();

    }
    public void excluirGrupo(){

        dao.excluir(edtId.getText().toString());
        finish();
    }

    public void listar(){
        Intent it = new Intent(this, TelaDeCadastroDeGrupoListActivity.class);
        // startActivity(it);
        startActivityForResult(it, 5);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
