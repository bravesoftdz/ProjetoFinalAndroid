package projetofinal.celer.com.br.projetofinal.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {

	// Nome do banco
	private static final String NOME_BANCO = "projetoFinal";
	// Controle de versao
	private static final int VERSAO_BANCO = 4;
	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
			"DROP TABLE IF EXISTS tbl_cadastro;","DROP TABLE IF EXISTS tbl_grupo;"
																		 };

	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"CREATE TABLE tbl_cadastro(usuario text primary key,  email text, senha text);","CREATE TABLE tbl_grupo(_id integer primary key,  nomeDoGrupo text, monitorResponsave text,localDeAtuacao text,idadeInicialDeEntrada integer,idadeFinalDeSaida integer);"

	};

	private static SQLiteDatabase db;

	public static SQLiteDatabase getDB(Context ctx) {
		if (db == null) {			
			SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
			db = dbHelper.getWritableDatabase();
		}
		return db;
		
	}
	
	

}
