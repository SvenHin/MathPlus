package com.svenhaakon.s315318s305204mappe1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ExitDialog extends DialogFragment {
    private DialogClickListener callback;

    public interface DialogClickListener{
        public void onYesClick();
        public void onNoClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (DialogClickListener)getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.exitDialog).setPositiveButton(R.string.exitDialogOk,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                callback.onYesClick();
                            }
                        }
                )
                .setNegativeButton(R.string.exitDialogNotOk,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                callback.onNoClick();
                            }
                        }
                )
                .create();
    }
}
