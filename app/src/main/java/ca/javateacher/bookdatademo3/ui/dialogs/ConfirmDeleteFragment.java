/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ca.javateacher.bookdatademo3.R;

@SuppressWarnings("ConstantConditions")
public class ConfirmDeleteFragment extends DialogFragment {

  public static final String TAG = "ConfirmDeleteFragment";

  @SuppressWarnings("unused")
  public static ConfirmDeleteFragment newInstance() {
    return new ConfirmDeleteFragment();
  }

  public ConfirmDeleteFragment() {
    // Required empty public constructor
  }

  @Override
  @NonNull
  public Dialog onCreateDialog(Bundle bundle) {

    // create a new AlertDialog Builder
    AlertDialog.Builder builder =
        new AlertDialog.Builder(getActivity());

    builder.setTitle(R.string.confirm_delete_title);
    builder.setMessage(R.string.confirm_delete_message);

    builder.setPositiveButton(
        android.R.string.yes, (dialog, button) -> confirmed());
    builder.setNegativeButton(android.R.string.no, null);

    // return the AlertDialog
    return builder.create();
  }

  private void confirmed() {
    if(getTargetFragment() != null){
      getTargetFragment()
          .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
    }
  }

  public static void show(Fragment targetFragment, int requestCode) {
    ConfirmDeleteFragment confirmDeleteFragment = ConfirmDeleteFragment.newInstance();
    confirmDeleteFragment.setTargetFragment(targetFragment, requestCode);
    FragmentManager fragmentManager = targetFragment.getFragmentManager();
    confirmDeleteFragment.show(fragmentManager, ConfirmDeleteFragment.TAG);
  }
}
