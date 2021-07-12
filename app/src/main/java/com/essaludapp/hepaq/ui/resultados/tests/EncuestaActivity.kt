package com.essaludapp.hepaq.ui.resultados.tests

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.essaludapp.hepaq.R
import com.essaludapp.hepaq.common.Constants
import com.essaludapp.hepaq.common.SharedPreferencesManager
import com.essaludapp.hepaq.retrofit.HEPAQClient
import com.essaludapp.hepaq.retrofit.HEPAQService
import com.essaludapp.hepaq.retrofit.request.RequestRegistrarEncuesta
import com.essaludapp.hepaq.retrofit.response.encuesta.ResponseConfirmarEncuesta
import com.essaludapp.hepaq.retrofit.response.tests.Dataform
import com.essaludapp.hepaq.retrofit.response.tests.PreguntasEncuesta
import com.google.gson.Gson
import com.quickbirdstudios.surveykit.*
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.survey.SurveyView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EncuestaActivity : AppCompatActivity() {

    private lateinit var survey: SurveyView
    private lateinit var container: ViewGroup
    private lateinit var resultado: ViewGroup
    private lateinit var toolbar: Toolbar
    private lateinit var btnRegresar: Button

    private lateinit var hepaqService: HEPAQService
    private lateinit var hepaqClient: HEPAQClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_encuesta)

        //Ocultar barra de toolbar
        supportActionBar!!.hide()
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        survey = findViewById(R.id.survey_view)
        container = findViewById(R.id.surveyContainer)
        resultado = findViewById(R.id.resultado)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnRegresar.setOnClickListener {
            onBackPressed()
        }

        setupSurvey(survey)
    }

    private fun setupSurvey(surveyView: SurveyView?) {
        val steps = listOf(
            InstructionStep(
                title = this.resources.getString(R.string.encuesta_title),
                text = this.resources.getString(R.string.encuesta_text),
                buttonText = this.resources.getString(R.string.intro_start)
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_tipo_title),
                text = this.resources.getString(R.string.encuesta_tipo),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_tipo_op1)),
                        TextChoice(this.resources.getString(R.string.encuesta_tipo_op2)),
                        TextChoice(this.resources.getString(R.string.encuesta_tipo_op3)),
                        TextChoice(this.resources.getString(R.string.encuesta_tipo_op4)),
                        TextChoice(this.resources.getString(R.string.encuesta_tipo_op5)),
                        TextChoice(this.resources.getString(R.string.encuesta_tipo_op6))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_grado_title),
                text = this.resources.getString(R.string.encuesta_grado),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_grado_op1)),
                        TextChoice(this.resources.getString(R.string.encuesta_grado_op2)),
                        TextChoice(this.resources.getString(R.string.encuesta_grado_op3)),
                        TextChoice(this.resources.getString(R.string.encuesta_grado_op4))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_ocupacion),
                text = this.resources.getString(R.string.encuesta_profesion),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.TextAnswerFormat(
                    maxLines = 1,
                    hintText = "Escriba aquí su opcupación"
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p1),
                text = this.resources.getString(R.string.encuesta_preg1),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_si)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p2),
                text = this.resources.getString(R.string.encuesta_preg2),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_exce)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_buena)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_regular)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_mala)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_pesima))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p3),
                text = this.resources.getString(R.string.encuesta_preg3),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_exce)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_buena)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_regular)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_mala)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_pesima))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p4),
                text = this.resources.getString(R.string.encuesta_preg4),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_siempre)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_casis)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_aveces)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_casin)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_nunca))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p5),
                text = this.resources.getString(R.string.encuesta_preg5),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_siempre)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_casis)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_aveces)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_casin)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_nunca))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p6),
                text = this.resources.getString(R.string.encuesta_preg6),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_siempre)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_casis)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_aveces)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_casin)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_nunca))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p7),
                text = this.resources.getString(R.string.encuesta_preg7),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_si)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p8),
                text = this.resources.getString(R.string.encuesta_preg8),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_si)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p9),
                text = this.resources.getString(R.string.encuesta_preg9),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_si)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p10),
                text = this.resources.getString(R.string.encuesta_preg10),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_si)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.encuesta_title_p11),
                text = this.resources.getString(R.string.encuesta_preg11),
                nextButton = this.resources.getString(R.string.survey_next_button_text),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.encuesta_resp_exce)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_buena)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_regular)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_mala)),
                        TextChoice(this.resources.getString(R.string.encuesta_resp_pesima))
                    )
                )
            ),

            CompletionStep(
                title = this.resources.getString(R.string.finish_question_title),
                text = this.resources.getString(R.string.finish_survey_text),
                buttonText = this.resources.getString(R.string.finish_question_submit)
            )
        )

        val task = OrderedTask(steps = steps)

        var posicion = 0
        var tipo_usuario = ""
        var grado_instruccion = ""
        var ocupacion = ""
        val preguntasEncuesta = PreguntasEncuesta()
        surveyView!!.onSurveyFinish = { taskResult: TaskResult, reason: FinishReason ->
            if (reason == FinishReason.Completed) {
                taskResult.results.forEach { stepResult ->
                    when (posicion) {
                        1 -> tipo_usuario = stepResult.results.firstOrNull()!!.stringIdentifier
                        2 -> grado_instruccion = stepResult.results.firstOrNull()!!.stringIdentifier
                        3 -> ocupacion = stepResult.results.firstOrNull()!!.stringIdentifier
                        4 -> preguntasEncuesta.pregunta_1 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        5 -> preguntasEncuesta.pregunta_2 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        6 -> preguntasEncuesta.pregunta_3 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        7 -> preguntasEncuesta.pregunta_4 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        8 -> preguntasEncuesta.pregunta_5 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        9 -> preguntasEncuesta.pregunta_6 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        10 -> preguntasEncuesta.pregunta_7 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        11 -> preguntasEncuesta.pregunta_8 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        12 -> preguntasEncuesta.pregunta_9 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        13 -> preguntasEncuesta.pregunta_10 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                        14 -> preguntasEncuesta.pregunta_11 =
                            stepResult.results.firstOrNull()!!.stringIdentifier
                    }

                    posicion++
                }

                //Almacena preferences del login
                //SharedPreferencesManager.setBooleanValue(Constants.ENCUESTA_CONTESTADA, true)

                container.removeAllViews()
                container.visibility = View.INVISIBLE

                resultado.visibility = View.VISIBLE

                hepaqClient = HEPAQClient.getInstance()
                hepaqService = hepaqClient.getHEPAQService()

                val doc = SharedPreferencesManager.getStringValue(Constants.PREF_DOCUMENTO)
                val encuesta = RequestRegistrarEncuesta(
                    Dataform(
                        doc,
                        tipo_usuario,
                        grado_instruccion,
                        ocupacion,
                        preguntasEncuesta
                    )
                )

                registrarEncuesta(encuesta)
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

    open fun registrarEncuesta(registrarEnc: RequestRegistrarEncuesta) {
        val call: Call<ResponseConfirmarEncuesta> = hepaqService.registrarEncuesta(registrarEnc)
        call.enqueue(object : Callback<ResponseConfirmarEncuesta> {
            override fun onResponse(
                call: Call<ResponseConfirmarEncuesta>,
                responseRegistrar: Response<ResponseConfirmarEncuesta>
            ) {
                if (responseRegistrar.isSuccessful) {
                    if (responseRegistrar.body()!!.isConfirmado) {
                        SharedPreferencesManager.setBooleanValue(
                            Constants.ENCUESTA_CONTESTADA,
                            true
                        )
                    } else {
                        try {
                            Toast.makeText(
                                this@EncuestaActivity,
                                "Ocurrio un error al registrar la encuesta",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {

                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseConfirmarEncuesta>, t: Throwable) {
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            survey.backPressed()
            true
        } else false
    }
}