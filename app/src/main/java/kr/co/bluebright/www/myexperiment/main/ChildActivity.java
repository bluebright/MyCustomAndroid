package kr.co.bluebright.www.myexperiment.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import kr.co.bluebright.www.myexperiment.R;
import kr.co.bluebright.www.myexperiment.common.view.BaseActivity;
import kr.co.bluebright.www.myexperiment.databinding.ActivityChildBinding;


public class ChildActivity extends BaseActivity {

    private ActivityChildBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = putContentView(R.layout.activity_child);

        setTitle(BaseActivity.class.getSimpleName());
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        baseBinding.reverseToolbar.textToolbarTitle.setText(title);
    }
}
