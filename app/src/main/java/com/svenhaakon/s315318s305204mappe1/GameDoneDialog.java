package com.svenhaakon.s315318s305204mappe1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameDoneDialog extends DialogFragment {
    private DialogClickListener callback;

    public interface DialogClickListener{
        public void gameDoneOnYesClick();
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
                .setTitle(R.string.gameDoneDialog).setPositiveButton(R.string.gameDoneDialogOk,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                callback.gameDoneOnYesClick();
                            }
                        }
                )
                .create();
    }
}
