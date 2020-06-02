package kr.co.bluebright.www.myexperiment.main;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kr.co.bluebright.www.myexperiment.R;
import kr.co.bluebright.www.myexperiment.common.util.Hexadecimal_InputFilter;
import kr.co.bluebright.www.myexperiment.common.util.InputFilterValueObserver;
import kr.co.bluebright.www.myexperiment.databinding.ActivityEditTextBinding;

public class EditTextActivity extends AppCompatActivity {

    /**
     *
     */
    private ActivityEditTextBinding binding;

    /**
     *
     */
    private InputMethodManager inputMethodManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_text, null);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        InputFilterValueObserver inputFilterModel = new InputFilterValueObserver();
        binding.setInputFilterModel(inputFilterModel);
        binding.edit1.setFilters(new InputFilter[]{inputFilterModel});

        Hexadecimal_InputFilter hexInputFilter = new Hexadecimal_InputFilter(false);
        binding.edit2.setFilters(new InputFilter[]{hexInputFilter});
        //binding.edit2.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

    }

    /**
     * @see <a href=https://itpangpang.xyz/304>Tistory blog</a>
     */
    private void clearEditTextFocus(EditText target) {

        if (inputMethodManager != null && target != null) {
            inputMethodManager.hideSoftInputFromWindow(target.getWindowToken(), 0);
            target.clearFocus();
        }

    }

}
