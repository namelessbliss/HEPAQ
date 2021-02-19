package com.example.prototipo.ui.vacuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prototipo.R;
import com.example.prototipo.common.Constants;
import com.example.prototipo.common.SharedPreferencesManager;
import com.example.prototipo.common.Vacuna;
import com.example.prototipo.retrofit.response.atenciones.ResponseAtenciones;
import com.example.prototipo.ui.LoginActivity;
import com.example.prototipo.ui.atenciones.MyAtencionRecyclerViewAdapter;
import com.example.prototipo.ui.resultados.EcografiaFragment;
import com.example.prototipo.ui.resultados.LaboratorioFragment;
import com.example.prototipo.ui.resultados.ResultadosActivity;
import com.example.prototipo.ui.resultados.SimulacionFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VacunaActivity extends AppCompatActivity {

    Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;

    String fecha = "2021-03-01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacuna);
        //Ocultar barra de toolbar
        getSupportActionBar().hide();

        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        adapter.addFragment(new VacunaAvisoFragment(), "Confirmación");
        adapter.addFragment(new HistorialVacunaFragment(), "Historial");
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
}