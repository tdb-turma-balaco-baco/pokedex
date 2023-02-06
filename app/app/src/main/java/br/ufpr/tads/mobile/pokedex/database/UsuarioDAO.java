package br.ufpr.tads.mobile.pokedex.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.exception.DatabaseException;
import br.ufpr.tads.mobile.pokedex.model.Usuario;

public class UsuarioDAO {
    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context) {
        this.context = context;
    }

    public UsuarioDAO open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public void insert(Usuario usuario) throws DatabaseException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstants.Database.ID, usuario.getId());
        contentValues.put(AppConstants.Database.NOME, usuario.getNome());
        contentValues.put(AppConstants.Database.LOGIN, usuario.getLogin());

        try {
            database.insert(AppConstants.Database.TABLE_NAME, null, contentValues);
        } catch (SQLException e) {
            String mensagem = String.format("Erro inserindo usuário=%s", usuario.toString());
            throw new DatabaseException(mensagem, e);
        }
    }

    public Usuario fetch() throws DatabaseException {
        String[] columns = new String[] { AppConstants.Database.ID, AppConstants.Database.NOME, AppConstants.Database.LOGIN };

        try {
            Cursor cursor = database.query(AppConstants.Database.TABLE_NAME, columns, null, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                return parseUsuario(cursor);
            }

            return null;
        } catch (SQLException e) {
            throw new DatabaseException("Erro recuperando usuário", e);
        }
    }

    private Usuario parseUsuario(Cursor cursor) {
        int indexId = cursor.getColumnIndex(AppConstants.Database.ID);
        int indexNome = cursor.getColumnIndex(AppConstants.Database.NOME);
        int indexLogin = cursor.getColumnIndex(AppConstants.Database.LOGIN);

        Usuario usuario = new Usuario();

        if (indexId > -1 && indexNome > -1 && indexLogin > -1) {
            usuario.setId(cursor.getString(indexId));
            usuario.setNome(cursor.getString(indexNome));
            usuario.setLogin(cursor.getString(indexLogin));
        }

        return usuario;
    }
}
