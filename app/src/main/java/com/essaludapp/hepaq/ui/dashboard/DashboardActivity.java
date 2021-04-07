package com.essaludapp.hepaq.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.essaludapp.hepaq.R;
import com.essaludapp.hepaq.common.Constants;
import com.essaludapp.hepaq.common.SharedPreferencesManager;
import com.essaludapp.hepaq.retrofit.HEPAQClient;
import com.essaludapp.hepaq.retrofit.HEPAQService;
import com.essaludapp.hepaq.retrofit.response.atenciones.ResponseAtenciones;
import com.essaludapp.hepaq.ui.ProfileActivity;
import com.essaludapp.hepaq.ui.acerca.AcercaActivity;
import com.essaludapp.hepaq.ui.atenciones.AtencionesActivity;
import com.essaludapp.hepaq.ui.resultados.ResultadosActivity;
import com.essaludapp.hepaq.ui.vacuna.VacunaActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import net.khirr.android.privacypolicy.PrivacyPolicyDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
                    goToActivity(new AcercaActivity());
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

        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        //  Params: context, termsOfService url, privacyPolicyUrl
        PrivacyPolicyDialog dialog = new PrivacyPolicyDialog(this,
                "",
                "");

        dialog.setOnClickListener(new PrivacyPolicyDialog.OnClickListener() {
            @Override
            public void onAccept(boolean isFirstTime) {
                Log.e("DashboardActivity", "Policies accepted");
            }

            @Override
            public void onCancel() {
                Log.e("DashboardActivity", "Policies not accepted");
                finish();
            }
        });

        dialog.addPoliceLine("Yo, "+SharedPreferencesManager.getStringValue(Constants.PREF_NOMBRE) + " " + SharedPreferencesManager.getStringValue(Constants.PREF_APELLIDO) +" identificado con DNI No "+SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO)+", en mi condición de paciente, autorizo al Personal y/o Equipo del PROGRAMA DE REFORMA DE VIDA, a practicar los procedimientos de :");
        dialog.addPoliceLine("•\tTamizaje de Toma de muestra Sanguínea. /Valoración antropométrica/ Signos Vitales.\n" +
                "•\tExámenes de Laboratorio de :\n" +
                "•\tGlucosa, Trigliceridos, Colesterol, HDL, LDL, Hemoglobina, Hematocrito.\n" +
                "•\tSesiones de Intervención.\n" +
                "•\tReevaluacion.\n" +
                "•\tReconocimiento y Clausura.\n");
        dialog.addPoliceLine("Conocedor de la resolución 090 de Gerencia General ESSALUD 2016, en donde se detalla las funciones y actividades del equipo de reforma de vida por lo que aceptó bajo mi voluntad ser parte de la iniciativa de trabajo Interinstitucional y conocedor de la firma de la alianza estratégica me comprometo a ser partícipe de todas las actividades que involucre el presente programa.\n" +
                "Finalmente autorizo que durante el procedimiento el cual soy sometido, según sea el caso se puedan utilizar técnicas  e instrumentos  que garanticen evidencia científica y pedagógica porque también entiendo que  los hospitales de  ESSALUD como este, según  el nivel de atención  son instituciones docentes que trabajan con personal de salud en formación, capacitación y entrenamiento.\n" +
                "En forma voluntaria y en pleno uso  de mis facultades mentales, físicas, y de mi entendimiento, libre de coerción o alguna otra influencia  indebida  y habiendo sido debidamente  informado sobre las actividades del programa REFORMA DE VIDA, por lo que firmo el presente consentimiento informado  entendiendo las declaraciones arriba descritas.\n");
        dialog.addPoliceLine("Fecha: mes "+ (mes+1)+" , año "+ano+", hora "+hora+" : "+minuto+" ");

        //  Customizing (Optional)
        dialog.setTitleTextColor(Color.parseColor("#222222"));
        dialog.setAcceptButtonColor(ContextCompat.getColor(this, R.color.blue_pressed));
        dialog.setAcceptText("Acepto");
        dialog.setCancelText("No Acepto");

        //  Title
        dialog.setTitle("CONSENTIMIENTO INFORMADO");
        dialog.setTermsOfServiceSubtitle("TOMA DE MUESTRA SANGUINEA U OTROS PROCEDIMIENTOS MEDICOS DEL PROGRAMA REFORMA DE VIDA");

        //  {terms}Terms of Service{/terms} is replaced by a link to your terms
        //  {privacy}Privacy Policy{/privacy} is replaced by a link to your privacy policy
        dialog.setTermsOfServiceSubtitle("Si haces clic en Acepto, afirmas estar de acuerdo e implica que has leído los términos mencionados.");

        dialog.show();


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
        tvUser.setOnClickListener(this);
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
        //apter.addFragment(fisiologicoFragment, "Fisiológico");
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
                        SharedPreferencesManager.setStringValue(Constants.PRES_SANGUINEA_SYS, atencionesList.get(0).getAtencionMedicoObj().getPresionArterialMm() + "");
                        SharedPreferencesManager.setStringValue(Constants.PRES_SANGUINEA_DIA, atencionesList.get(0).getAtencionMedicoObj().getPresionArterialHg() + "");
                        vitalesFragment.visibilidad(false);
                    } catch (Exception e) {
                        vitalesFragment.visibilidad(false);
                    }

                    try {
                        SharedPreferencesManager.setStringValue(Constants.IMC, atencionesList.get(0).getAtencionMedicoObj().getiMC() + "");
                        SharedPreferencesManager.setStringValue(Constants.PERIMETRO_ABDOMINAL, atencionesList.get(0).getAtencionMedicoObj().getPerAbdominal() + "");
                        fisicoFragment.visibilidad(false);
                    } catch (Exception e) {
                        fisicoFragment.visibilidad(false);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<ResponseAtenciones>> call, Throwable t) {
                vitalesFragment.visibilidad(false);
                fisicoFragment.visibilidad(false);
            }
        });
    }

    private int calcularResultado(int pregunta, String res){
        switch (pregunta){
            case 1:
                if (res.equalsIgnoreCase(getString(R.string.cono_p1_op4)))
                    return 2;
                else
                    return 0;
            case 2:
                if (res.equalsIgnoreCase(getString(R.string.cono_p1_op2)))
                    return 2;
                else
                    return 0;
            case 3:
                if (res.equalsIgnoreCase(getString(R.string.cono_p3_op4)))
                    return 2;
                else
                    return 0;
            case 4:
                if (res.equalsIgnoreCase(getString(R.string.cono_p4_op4)))
                    return 2;
                else
                    return 0;
            case 5:
                if (res.equalsIgnoreCase(getString(R.string.cono_p5_op4)))
                    return 2;
                else
                    return 0;
            case 6:
                if (res.equalsIgnoreCase(getString(R.string.cono_p6_op4)))
                    return 2;
                else
                    return 0;
            case 7:
                if (res.equalsIgnoreCase(getString(R.string.cono_p7_op4)))
                    return 2;
                else
                    return 0;
            case 8:
                if (res.equalsIgnoreCase(getString(R.string.cono_p8_op4)))
                    return 2;
                else
                    return 0;
            case 9:
                if (res.equalsIgnoreCase(getString(R.string.cono_p9_op4)))
                    return 2;
                else
                    return 0;
            case 10:
                if (res.equalsIgnoreCase(getString(R.string.cono_p10_op4)))
                    return 2;
                else
                    return 0;
            default:
                return 0;
        }
    }
}