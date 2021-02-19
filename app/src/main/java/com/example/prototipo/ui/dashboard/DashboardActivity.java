package com.example.prototipo.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.common.SharedPreferencesManager;
import com.example.prototipo.retrofit.HEPAQClient;
import com.example.prototipo.retrofit.HEPAQService;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;
import com.example.prototipo.ui.ProfileActivity;
import com.example.prototipo.ui.atenciones.AtencionesActivity;
import com.example.prototipo.ui.atenciones.MyAtencionRecyclerViewAdapter;
import com.example.prototipo.ui.resultados.EcografiaFragment;
import com.example.prototipo.ui.resultados.LaboratorioFragment;
import com.example.prototipo.ui.resultados.ResultadosActivity;
import com.example.prototipo.ui.resultados.SimulacionFragment;
import com.example.prototipo.ui.vacuna.VacunaActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageView ivUser;
    TextView tvUser;
    BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    public ViewPager viewPager;

    private HEPAQClient hepaqClient;
    private HEPAQService hepaqService;

    private List<ResponseAtenciones> atencionesList;
    private VitalesFragment vitalesFragment = new VitalesFragment();
    private MentalFragment mentalFragment = new MentalFragment();
    private FisiologicoFragment fisiologicoFragment = new FisiologicoFragment();
    private FisicoFragment fisicoFragment = new FisicoFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_atencion:
                    goToActivity(new AtencionesActivity());
                    return true;
                case R.id.navigation_vacuna:
                    goToActivity(new VacunaActivity());
                    return true;
                case R.id.navigation_resultados:
                    goToActivity(new ResultadosActivity());
                    return true;
                case R.id.navigation_acerca:
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();
        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivUser.setOnClickListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        retrofitInit();
        setDatosMedicos();
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        ivUser = findViewById(R.id.imageViewUser);
        tvUser = findViewById(R.id.tvUser);
        bottomNavigationView = findViewById(R.id.nav_view);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewUser:
                goToActivity(new ProfileActivity());
                break;
            case R.id.tvUser:
                goToActivity(new ProfileActivity());
                break;
            default:
                break;

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(vitalesFragment, "Vitales");
        adapter.addFragment(fisiologicoFragment, "Fisiológico");
        adapter.addFragment(mentalFragment, "Mental");
        adapter.addFragment(fisicoFragment, "Físico");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void goToActivity(AppCompatActivity activity) {
        Intent intent = new Intent(DashboardActivity.this, activity.getClass());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    private void retrofitInit() {
        hepaqClient = hepaqClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void setDatosMedicos() {
        String documento = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO);

        Call<List<ResponseAtenciones>> call = hepaqService.getAllAtenciones(documento);

        call.enqueue(new Callback<List<ResponseAtenciones>>() {
            @Override
            public void onResponse(Call<List<ResponseAtenciones>> call, Response<List<ResponseAtenciones>> response) {
                if (response.isSuccessful()) {
                    atencionesList = response.body();
                    try {
                        SharedPreferencesManager.setStringValue(Constants.FREC_CARDIACA, atencionesList.get(0).getAtencionMedicoObj().getFrecCardiaca() + "");
                        SharedPreferencesManager.setStringValue(Constants.FREC_RESP, atencionesList.get(0).getAtencionMedicoObj().getFrecRespiratoria() + "");
                        vitalesFragment.visibilidad(false);
                    } catch (Exception e) {
                        vitalesFragment.visibilidad(true);
                    }

                    try {
                        SharedPreferencesManager.setStringValue(Constants.IMC, atencionesList.get(0).getAtencionMedicoObj().getiMC() + "");
                        fisicoFragment.visibilidad(false);
                    } catch (Exception e) {
                        fisicoFragment.visibilidad(false);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<ResponseAtenciones>> call, Throwable t) {
                vitalesFragment.visibilidad(true);
                fisicoFragment.visibilidad(false);
            }
        });
    }
}