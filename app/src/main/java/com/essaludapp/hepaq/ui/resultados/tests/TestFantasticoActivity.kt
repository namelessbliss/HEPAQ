package com.essaludapp.hepaq.ui.resultados.tests

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.essaludapp.hepaq.R
import com.essaludapp.hepaq.common.Constants
import com.essaludapp.hepaq.common.SharedPreferencesManager
import com.essaludapp.hepaq.retrofit.HEPAQClient
import com.essaludapp.hepaq.retrofit.HEPAQService
import com.essaludapp.hepaq.retrofit.request.RequestRegistrarTest
import com.essaludapp.hepaq.retrofit.response.tests.Dataform
import com.essaludapp.hepaq.retrofit.response.tests.ResponseRegistrarTests
import com.quickbirdstudios.surveykit.*
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.survey.SurveyView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestFantasticoActivity : AppCompatActivity() {

    private lateinit var survey: SurveyView
    private lateinit var container: ViewGroup
    private lateinit var resultado: ViewGroup
    private lateinit var toolbar: Toolbar
    private lateinit var tvPuntaje: TextView
    private lateinit var tvMensaje: TextView
    private lateinit var btnRegresar: Button

    private lateinit var hepaqService: HEPAQService
    private lateinit var hepaqClient: HEPAQClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fantastico)

        //Ocultar barra de toolbar
        supportActionBar!!.hide()
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        survey = findViewById(R.id.survey_view)
        container = findViewById(R.id.surveyContainer)
        resultado = findViewById(R.id.resultado)
        tvPuntaje = findViewById(R.id.tvPuntaje)
        tvMensaje = findViewById(R.id.tvMensaje)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnRegresar.setOnClickListener {
            onBackPressed()
        }

        setupSurvey(survey)
    }

    private fun setupSurvey(surveyView: SurveyView?) {
        val steps = listOf(
            InstructionStep(
                title = this.resources.getString(R.string.intro_title),
                text = this.resources.getString(R.string.intro_text),
                buttonText = this.resources.getString(R.string.intro_start)
            ),
            QuestionStep(
                title = this.resources.getString(R.string.familia_amigos_title),
                text = this.resources.getString(R.string.familia_amigos_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.familia_amigos_title),
                text = this.resources.getString(R.string.familia_amigos_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.familia_amigos_title),
                text = this.resources.getString(R.string.familia_amigos_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.familia_amigos_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.actividad_fisica_asociativa),
                text = this.resources.getString(R.string.actividad_fisica_asociativa_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_1_op2))
                    )
                )
            ),

            QuestionStep(
                title = this.resources.getString(R.string.actividad_fisica_asociativa),
                text = this.resources.getString(R.string.actividad_fisica_asociativa_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.actividad_fisica_asociativa),
                text = this.resources.getString(R.string.actividad_fisica_asociativa_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.actividad_fisica_asociativa_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.nutricion_alimentacion),
                text = this.resources.getString(R.string.nutricion_alimentacion_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.nutricion_alimentacion),
                text = this.resources.getString(R.string.nutricion_alimentacion_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.nutricion_alimentacion),
                text = this.resources.getString(R.string.nutricion_alimentacion_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.nutricion_alimentacion_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.tabaco_dependencia),
                text = this.resources.getString(R.string.tabaco_dependencia_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.tabaco_dependencia),
                text = this.resources.getString(R.string.tabaco_dependencia_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.tabaco_dependencia),
                text = this.resources.getString(R.string.tabaco_dependencia_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.tabaco_dependencia_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.alcohol),
                text = this.resources.getString(R.string.alcohol_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.alcohol_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.alcohol_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.alcohol_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.alcohol),
                text = this.resources.getString(R.string.alcohol_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.alcohol_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.alcohol_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.alcohol_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.alcohol),
                text = this.resources.getString(R.string.alcohol_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.alcohol_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.alcohol_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.alcohol_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.sueno_estres),
                text = this.resources.getString(R.string.sueno_estres_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.sueno_estres),
                text = this.resources.getString(R.string.sueno_estres_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.sueno_estres),
                text = this.resources.getString(R.string.sueno_estres_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.sueno_estres_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.trabajo_personalidad),
                text = this.resources.getString(R.string.trabajo_personalidad_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.trabajo_personalidad),
                text = this.resources.getString(R.string.trabajo_personalidad_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.trabajo_personalidad),
                text = this.resources.getString(R.string.trabajo_personalidad_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.trabajo_personalidad_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.introspeccion),
                text = this.resources.getString(R.string.introspeccion_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.introspeccion),
                text = this.resources.getString(R.string.introspeccion_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.introspeccion),
                text = this.resources.getString(R.string.introspeccion_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.introspeccion_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.salud_sexualidad),
                text = this.resources.getString(R.string.salud_sexualidad_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.salud_sexualidad),
                text = this.resources.getString(R.string.salud_sexualidad_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.salud_sexualidad),
                text = this.resources.getString(R.string.salud_sexualidad_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.salud_sexualidad_preg_3_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.otros),
                text = this.resources.getString(R.string.otros_preg_1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.otros_preg_1_op0)),
                        TextChoice(this.resources.getString(R.string.otros_preg_1_op1)),
                        TextChoice(this.resources.getString(R.string.otros_preg_1_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.otros),
                text = this.resources.getString(R.string.otros_preg_2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.otros_preg_2_op0)),
                        TextChoice(this.resources.getString(R.string.otros_preg_2_op1)),
                        TextChoice(this.resources.getString(R.string.otros_preg_2_op2))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.otros),
                text = this.resources.getString(R.string.otros_preg_3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.otros_preg_3_op0)),
                        TextChoice(this.resources.getString(R.string.otros_preg_3_op1)),
                        TextChoice(this.resources.getString(R.string.otros_preg_3_op2))
                    )
                )
            ),

            CompletionStep(
                title = this.resources.getString(R.string.finish_question_title),
                text = this.resources.getString(R.string.finish_question_text),
                buttonText = this.resources.getString(R.string.finish_question_submit)
            )
        )

        val task = OrderedTask(steps = steps)

        var posisicion = 0;
        var suma = 0;
        surveyView!!.onSurveyFinish = { taskResult: TaskResult, reason: FinishReason ->
            if (reason == FinishReason.Completed) {
                taskResult.results.forEach { stepResult ->
                    //Log.e("ASDF", "answer ${stepResult.results.firstOrNull()!!.stringIdentifier}")
                    suma += calcularResultado(
                        posisicion,
                        stepResult.results.firstOrNull()!!.stringIdentifier
                    )
                    posisicion++;
                }
                suma *= 2

                //Almacena preferences del login
                SharedPreferencesManager.setStringValue(
                    Constants.TEST_FANTAS_SCORE,
                    suma.toString()
                )
                container.removeAllViews()
                container.visibility = View.INVISIBLE

                tvPuntaje.text = suma.toString()
                tvMensaje.text = getMensajeSegun(suma)

                resultado.visibility = View.VISIBLE

                hepaqClient = HEPAQClient.getInstance()
                hepaqService = hepaqClient.getHEPAQService()

                val doc = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO)
                val test = RequestRegistrarTest(Dataform(doc, suma, 2))

                registrarTest(test)
            }
        }

        val configuration = SurveyTheme(
            themeColorDark = ContextCompat.getColor(this, R.color.soft_blue),
            themeColor = ContextCompat.getColor(this, R.color.cyan_normal),
            textColor = ContextCompat.getColor(this, R.color.cyan_text),
            /*abortDialogConfiguration = AbortDialogConfiguration(
                    title = R.string.title,
                    message = R.string.message,
                    neutralMessage = R.string.no,
                    negativeMessage = R.string.yes
            )*/
        )


        surveyView.start(task, configuration)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            survey.backPressed()
            true
        } else false
    }

    open fun registrarTest(registrarTest: RequestRegistrarTest) {
        val s = registrarTest.getDataform().toString()
        val call: Call<ResponseRegistrarTests> = hepaqService.registrarTest(s)
        call.enqueue(object : Callback<ResponseRegistrarTests> {
            override fun onResponse(
                call: Call<ResponseRegistrarTests>,
                responseRegistrar: Response<ResponseRegistrarTests>
            ) {
                if (responseRegistrar.isSuccessful) {
                    if (responseRegistrar.body()!!.isConfirmado) {
                        //TODO
                    } else {
                        //TODO
                    }
                }
            }

            override fun onFailure(call: Call<ResponseRegistrarTests>, t: Throwable) {
                if (t.message.equals(Constants.NET_ERROR, ignoreCase = true)) {
                    //TODO
                    println("ERROR DE CONEXION")
                } else {
                    //TODO
                    println("No se pudo confirmar, contacte al administrador")
                }
            }
        })
    }

    private fun calcularResultado(pregunta: Int, respuesta: String): Int {
        return when (pregunta) {
            1 -> if (respuesta.equals(
                    getString(R.string.familia_amigos_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.familia_amigos_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            2 -> if (respuesta.equals(
                    getString(R.string.familia_amigos_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.familia_amigos_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            3 -> if (respuesta.equals(
                    getString(R.string.familia_amigos_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.familia_amigos_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            4 -> if (respuesta.equals(
                    getString(R.string.actividad_fisica_asociativa_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.actividad_fisica_asociativa_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            5 -> if (respuesta.equals(
                    getString(R.string.actividad_fisica_asociativa_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.actividad_fisica_asociativa_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            6 -> if (respuesta.equals(
                    getString(R.string.actividad_fisica_asociativa_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.actividad_fisica_asociativa_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            7 -> if (respuesta.equals(
                    getString(R.string.nutricion_alimentacion_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.nutricion_alimentacion_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            8 -> if (respuesta.equals(
                    getString(R.string.nutricion_alimentacion_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.nutricion_alimentacion_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            9 -> if (respuesta.equals(
                    getString(R.string.nutricion_alimentacion_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.nutricion_alimentacion_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            10 -> if (respuesta.equals(
                    getString(R.string.tabaco_dependencia_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.tabaco_dependencia_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            11 -> if (respuesta.equals(
                    getString(R.string.tabaco_dependencia_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.tabaco_dependencia_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            12 -> if (respuesta.equals(
                    getString(R.string.tabaco_dependencia_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.tabaco_dependencia_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            13 -> if (respuesta.equals(
                    getString(R.string.alcohol_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.alcohol_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            14 -> if (respuesta.equals(
                    getString(R.string.alcohol_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.alcohol_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            15 -> if (respuesta.equals(
                    getString(R.string.alcohol_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.alcohol_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            16 -> if (respuesta.equals(
                    getString(R.string.sueno_estres_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.sueno_estres_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            17 -> if (respuesta.equals(
                    getString(R.string.sueno_estres_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.sueno_estres_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            18 -> if (respuesta.equals(
                    getString(R.string.sueno_estres_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.sueno_estres_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            19 -> if (respuesta.equals(
                    getString(R.string.trabajo_personalidad_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.trabajo_personalidad_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            20 -> if (respuesta.equals(
                    getString(R.string.trabajo_personalidad_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.trabajo_personalidad_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            21 -> if (respuesta.equals(
                    getString(R.string.trabajo_personalidad_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.trabajo_personalidad_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            22 -> if (respuesta.equals(
                    getString(R.string.introspeccion_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.introspeccion_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            23 -> if (respuesta.equals(
                    getString(R.string.introspeccion_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.introspeccion_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            24 -> if (respuesta.equals(
                    getString(R.string.introspeccion_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.introspeccion_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            25 -> if (respuesta.equals(
                    getString(R.string.salud_sexualidad_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.salud_sexualidad_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            26 -> if (respuesta.equals(
                    getString(R.string.salud_sexualidad_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.salud_sexualidad_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            27 -> if (respuesta.equals(
                    getString(R.string.salud_sexualidad_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.salud_sexualidad_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            28 -> if (respuesta.equals(
                    getString(R.string.otros_preg_1_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.otros_preg_1_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            29 -> if (respuesta.equals(
                    getString(R.string.otros_preg_2_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.otros_preg_2_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            30 -> if (respuesta.equals(
                    getString(R.string.otros_preg_3_op0),
                    ignoreCase = true
                )
            ) 0 else if (respuesta.equals(
                    getString(R.string.otros_preg_3_op1),
                    ignoreCase = true
                )
            ) 1 else 2
            else -> 0
        }
    }

    private fun getMensajeSegun(suma: Int): String? {
        return if (suma >= 103) "Felicitaciones. Tienes un estilo de vida Fantástico" else if (suma >= 85 && suma <= 102) "Buen trabajo. Estás en el camino correcto." else if (suma >= 74 && suma <= 84) "Adecuado, estás bien." else "Algo bajo, podrías mejorar"
    }
}

