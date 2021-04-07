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
import com.quickbirdstudios.surveykit.*
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.survey.SurveyView

class TestConocimientoActivity : AppCompatActivity() {

    private lateinit var survey: SurveyView
    private lateinit var container: ViewGroup
    private lateinit var resultado: ViewGroup
    private lateinit var toolbar: Toolbar
    private lateinit var tvPuntaje: TextView
    private lateinit var tvMensaje: TextView
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_conocimiento)

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
                        title = this.resources.getString(R.string.cono_title),
                        text = this.resources.getString(R.string.cono_text),
                        buttonText = this.resources.getString(R.string.intro_start)
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p1_t),
                        text = this.resources.getString(R.string.cono_p1),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p1_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p1_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p1_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p1_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p2_t),
                        text = this.resources.getString(R.string.cono_p2),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p2_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p2_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p2_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p2_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p3_t),
                        text = this.resources.getString(R.string.cono_p3),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p3_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p3_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p3_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p3_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p4_t),
                        text = this.resources.getString(R.string.cono_p4),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p4_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p4_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p4_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p4_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p5_t),
                        text = this.resources.getString(R.string.cono_p5),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p5_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p5_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p5_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p5_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p6_t),
                        text = this.resources.getString(R.string.cono_p6),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p6_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p6_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p6_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p6_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p7_t),
                        text = this.resources.getString(R.string.cono_p7),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p7_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p7_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p7_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p7_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p8_t),
                        text = this.resources.getString(R.string.cono_p8),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p8_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p8_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p8_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p8_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p9_t),
                        text = this.resources.getString(R.string.cono_p9),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p9_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p9_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p9_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p9_op4))
                                )
                        )
                ),
                QuestionStep(
                        title = this.resources.getString(R.string.cono_p10_t),
                        text = this.resources.getString(R.string.cono_p10),
                        answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                                textChoices = listOf(
                                        TextChoice(this.resources.getString(R.string.cono_p10_op1)),
                                        TextChoice(this.resources.getString(R.string.cono_p10_op2)),
                                        TextChoice(this.resources.getString(R.string.cono_p10_op3)),
                                        TextChoice(this.resources.getString(R.string.cono_p10_op4))
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
                    suma += calcularResultado(posisicion, stepResult.results.firstOrNull()!!.stringIdentifier)
                    posisicion++;
                }

                //Almacena preferences del login
                SharedPreferencesManager.setStringValue(Constants.TEST_CONO_SCORE, suma.toString())
                container.removeAllViews()
                container.visibility = View.INVISIBLE

                tvPuntaje.text = suma.toString()
                tvMensaje.text = getMensajeSegun(suma)

                resultado.visibility = View.VISIBLE
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

    private fun calcularResultado(pregunta: Int, res: String): Int {
        return when (pregunta) {
            1 -> if (res.equals(getString(R.string.cono_p1_op4), ignoreCase = true)) 2 else 0
            2 -> if (res.equals(getString(R.string.cono_p2_op2), ignoreCase = true)) 2 else 0
            3 -> if (res.equals(getString(R.string.cono_p3_op4), ignoreCase = true)) 2 else 0
            4 -> if (res.equals(getString(R.string.cono_p4_op4), ignoreCase = true)) 2 else 0
            5 -> if (res.equals(getString(R.string.cono_p5_op4), ignoreCase = true)) 2 else 0
            6 -> if (res.equals(getString(R.string.cono_p6_op4), ignoreCase = true)) 2 else 0
            7 -> if (res.equals(getString(R.string.cono_p7_op4), ignoreCase = true)) 2 else 0
            8 -> if (res.equals(getString(R.string.cono_p8_op4), ignoreCase = true)) 2 else 0
            9 -> if (res.equals(getString(R.string.cono_p9_op4), ignoreCase = true)) 2 else 0
            10 -> if (res.equals(getString(R.string.cono_p10_op4), ignoreCase = true)) 2 else 0
            else -> 0
        }
    }

    private fun getMensajeSegun(suma: Int): String? {
        return if (suma >= 46) "Existe un alto nivel de estrés percibido"
        else if (suma >= 25 && suma <= 45) "Nivel de estrés moderado"
        else "Nivel de estrés bajo"
    }
}