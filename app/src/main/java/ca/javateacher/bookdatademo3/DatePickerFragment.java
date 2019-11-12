/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import ca.javateacher.bookdatademo3.R;

@SuppressWarnings("ConstantConditions")
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    static final String TAG = "DatePickerFragment";
    static final String DATE = "date";

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    static DatePickerFragment newInstance(Date date) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(DATE, date);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        Date date;
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(DATE)) {
            date = (Date) getArguments().getSerializable(DATE);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.setTitle(R.string.date_label);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
      if(getTargetFragment() != null){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Intent intent = new Intent();
        intent.putExtra(DATE, calendar.getTime());
        getTargetFragment()
            .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
      }
    }
}
