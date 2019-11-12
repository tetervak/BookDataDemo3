package ca.javateacher.bookdatademo3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ca.javateacher.bookdatademo3.R;

public class AboutFragment extends DialogFragment {

  public static final String TAG = "AboutFragment";

  public AboutFragment() {
    // Required empty public constructor
  }

  public static AboutFragment newInstance() {
    return new AboutFragment();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Log.d(TAG, "onCreateDialog() called");
    // create a new AlertDialog Builder
    AlertDialog.Builder builder =
        new AlertDialog.Builder(getActivity());

    builder.setTitle(R.string.app_name);
    builder.setMessage(R.string.author);

    builder.setPositiveButton(android.R.string.ok, null);

    return builder.create();
  }
}
