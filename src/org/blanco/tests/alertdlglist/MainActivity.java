package org.blanco.tests.alertdlglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    Dialog dlg = null;
    
	@Override
	protected void onStart() {
		Builder builder = new AlertDialog.Builder(getBaseContext());
		builder.setCancelable(true);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.array.opciones);
		builder.setAdapter(adapter, this);
		builder.create().show();
		super.onStart();
	}

	@Override
	public void onClick(DialogInterface dlg, int id) {
		Toast.makeText(getBaseContext(), "Idpressed: "+id, Toast.LENGTH_LONG).show();
	}
    
    
    
}