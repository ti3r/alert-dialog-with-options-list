/**
 * alert-dialog-with-options is a small android application that shows how to 
 * display and alert dialog and execute a defined function based on the selected
 * option.
 * Copyright (C) 2011  Alexandro Blanco <ti3r.bubblenet@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.blanco.tests.alertdlglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
    Button btn = null;
    AlertDialog dialog = null;
	
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Crear un boton y agregarlo a la pantalla principal para iniciar el dialogo
        btn = new Button(getBaseContext());
        btn.setText("Show dialog");
        addContentView(btn, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT));
        btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				displayDialog();
			}
		});
    }
    
    public void displayDialog(){
    	Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setNegativeButton("Cancel", new OnClickListener() {//dimiss en cancel button
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("Ok", this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
				R.layout.dialog_list_item, //El id del resource donde esta el layout a usar
				R.id.dialog_list_item_text, //El id del textview que sera reemplazado con el item
				getBaseContext().getResources().getStringArray(R.array.opciones));//Las optiones para el adapter		
		builder.setTitle("Opciones");
		builder.setAdapter(adapter, this);
		dialog = builder.create();
		dialog.show();
    }
    
    public void showOnToast(Object item){
    	Toast.makeText(this, "Toast "+item.toString(), Toast.LENGTH_LONG).show();
    }
    
    public void showOnDialog(Object item){
    	Builder b = new AlertDialog.Builder(this);
    	b.setTitle("Dialog").setMessage(item.toString()).setCancelable(true).create().show();
    }
    
	@Override
	public void onClick(DialogInterface dlg, int idx) {
		//Se puede utilizar el indice del objeto seleccionado en la lista para
		//retraerlo desde el dialogo y utilizarlo o comparar el indice seleccionado
		Object item = dialog.getListView().getAdapter().getItem(idx);
		switch(idx){
		case 0:
		case 2:
			showOnToast(item);
			break;
		case 1:
			showOnDialog(item);
		}
	}
    
    
    
}