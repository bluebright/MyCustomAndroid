package kr.co.bluebright.www.myexperiment.common.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.concurrent.TimeUnit;

import kr.co.bluebright.www.myexperiment.R;
import kr.co.bluebright.www.myexperiment.core.LanguageContextWrapper;
import kr.co.bluebright.www.myexperiment.core.MyApplication;
import kr.co.bluebright.www.myexperiment.databinding.ActivityBaseBinding;


public class BaseActivity extends AppCompatActivity {


    private static final long BACK_PRESS_TIME_INTERVAL = TimeUnit.SECONDS.toMillis(2);
    private long mBackPressedTime;

    protected Context appContext;
    protected ActivityBaseBinding baseBinding;
    protected MyApplication myApplication;
    protected DrawerLayout endSideDrawerLayout;

    protected boolean backPressedAppExit = false;


    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LanguageContextWrapper.wrap(newBase);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        myApplication = MyApplication.getInstance();

        appContext = myApplication.getContextFromApp();

        Toolbar reverseToolbar = baseBinding.reverseToolbar.reverseToolbarBody;
        setSupportActionBar(reverseToolbar);

        endSideDrawerLayout = baseBinding.drawerLayout;

        EndDrawerToggle toggle = new EndDrawerToggle(
                this, endSideDrawerLayout, reverseToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        endSideDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        baseBinding.navView.setNavigationItemSelectedListener(item -> {
            final int id = item.getItemId();

            switch (id) {
                default:
                    break;
            }

            endSideDrawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });

        baseBinding.reverseToolbar.buttonBackward.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        if (endSideDrawerLayout != null && endSideDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            endSideDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
            if (backPressedAppExit) {
                if (mBackPressedTime + BACK_PRESS_TIME_INTERVAL > System.currentTimeMillis()) {
                    super.onBackPressed();
                    return;
                } else {
                    Toast.makeText(getBaseContext(), R.string.msg_app_exit_click_one_time_more, Toast.LENGTH_SHORT).show();
                }
                mBackPressedTime = System.currentTimeMillis();
            } else
                super.onBackPressed();
        }
    }

    /**
     * <p><b>IMPORTANT!!</b></p>
     * Methods that must be called to use data binding in inherited classes
     *
     * @param resId Child Activity's layout resource id
     * @return DataBinding value of child Activity's layout
     * @see ViewDataBinding
     * @since 1.0.0(1)
     */
    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return DataBindingUtil.inflate(getLayoutInflater(), resId, baseBinding.areaContent, true);
    }

}
