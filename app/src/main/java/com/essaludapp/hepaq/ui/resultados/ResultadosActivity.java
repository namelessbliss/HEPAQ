package com.essaludapp.hepaq.ui.resultados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;
import com.essaludapp.hepaq.retrofit.response.encuesta.ResponseConfirmarEncuesta;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosActivity extends AppCompatActivity {

    Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;

    private HEPAQClient hepaqClient;
    private HEPAQService hepaqService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        retrofitInit();
        getEncuesta();
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LaboratorioFragment(), "Laboratorio");
        adapter.addFragment(new EcografiaFragment(), "Ecografia");
        adapter.addFragment(new SimulacionFragment(), "Instrumentos PRV");
        viewPager.setAdapter(adapter);
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

    private void retrofitInit() {
        hepaqClient = hepaqClient.getInstance();
        hepaqService = hepaqClient.getHEPAQService();
    }

    private void getEncuesta() {
        String documento = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO);

        Call<ResponseConfirmarEncuesta> call = hepaqService.confirmarEncuesta(documento);

        call.enqueue(new Callback<ResponseConfirmarEncuesta>() {
            @Override
            public void onResponse(Call<ResponseConfirmarEncuesta> call, Response<ResponseConfirmarEncuesta> response) {
                if (response.isSuccessful()) {
                    if (response.body().isConfirmado()) {
                        try {
                            SharedPreferencesManager.setBooleanValue(Constants.ENCUESTA_CONTESTADA, true);
                        } catch (Exception e) {
                        }
                    } else {
                        SharedPreferencesManager.setBooleanValue(Constants.ENCUESTA_CONTESTADA, false);
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseConfirmarEncuesta> call, Throwable t) {
                System.out.println("error");
            }
        });
    }
}